package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;

import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.exceptions.WrongProductDataException;
import ua.kozak_vitalii.project_9.exceptions.WrongUserDataException;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateProductInfoAdminCommand extends Command {

    private static final Logger logger = Logger.getLogger(LogOutCommand.class);
    private final AdminService adminService;

    public UpdateProductInfoAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        logger.debug("UpdateProductInfoAdminCommand");

        User user = (User) session.getAttribute("user");
        List productsList = adminService.getProducts();
        List categoriesList = adminService.getCategories();

        String productId = request.getParameter("product_id");
        String productName = request.getParameter("product_name");

        String productPriceString = request.getParameter("product_price");
        Category productCategory = GetCategoryFromRequest(request);
        BigDecimal productPrice;

        try {
            checkPrice(productPriceString);
            productPrice = new BigDecimal(productPriceString);
            Product newProduct = new Product(productName, productCategory, productPrice);
            newProduct.setId(new Long(productId.trim()).longValue());

            categoriesList = deleteCategoryFromList(categoriesList,newProduct.getCategory());
            categoriesList.add(0, newProduct.getCategory());

            request.setAttribute("product_categorieslist", categoriesList);
            request.setAttribute("productslist", productsList);

            try {
                boolean result = adminService.updateProduct(newProduct);
                if (result) {

                    request.setAttribute("message", "Changing is successful. "+ newProduct.getName() + " is updated");

                    request.setAttribute("product_id", newProduct.getId());
                    request.setAttribute("product_name", newProduct.getName());
                    request.setAttribute("product_price", newProduct.getPrice());

                    productsList = adminService.getProducts();
                    categoriesList = adminService.getCategories();

                    request.setAttribute("product_categorieslist", categoriesList);
                    request.setAttribute("productslist", productsList);

                    return "/product.jsp";

                } else {
                    request.setAttribute("error", "An internal error occurred while trying to update a product");
                    request.setAttribute("product_id", productId);
                    request.setAttribute("product_name", productName);
                    request.setAttribute("product_price", productPriceString);

                    categoriesList = deleteCategoryFromList(categoriesList,productCategory);
                    categoriesList.add(0, productCategory);
                    request.setAttribute("product_categorieslist", categoriesList);
                    request.setAttribute("productslist", productsList);

                    return "/product_edit.jsp";
                }
            } catch (WrongProductDataException e) {
                request.setAttribute("error", e.getMessage());

                request.setAttribute("product_id", productId);
                request.setAttribute("product_name", productName);
                request.setAttribute("product_price", productPriceString);

                productsList = adminService.getProducts();
                categoriesList = adminService.getCategories();
                categoriesList = deleteCategoryFromList(categoriesList,productCategory);
                categoriesList.add(0, productCategory);
                request.setAttribute("product_categorieslist", categoriesList);
                request.setAttribute("productslist", productsList);

                return "/product_edit.jsp";
            }

        } catch (WrongProductDataException e) {
            request.setAttribute("error", e.getMessage());

            request.setAttribute("product_id", productId);
            request.setAttribute("product_name", productName);
            request.setAttribute("product_price", productPriceString);

            productsList = adminService.getProducts();
            categoriesList = adminService.getCategories();
            categoriesList = deleteCategoryFromList(categoriesList,productCategory);
            categoriesList.add(0, productCategory);
            request.setAttribute("product_categorieslist", categoriesList);
            request.setAttribute("productslist", productsList);

            return "/product_edit.jsp";
        }
    }

    private Category GetCategoryFromRequest(HttpServletRequest request) {
        String items = request.getParameter("products.category_selected");
        StringTokenizer t = new StringTokenizer(items,"|");
        String itemId = t.nextToken();

        return adminService.getCategory(new Long(itemId.trim()).longValue());
    }

    private List<Category> deleteCategoryFromList(List<Category> old, Category category){
        List<Category> categories = new ArrayList<>();

        for (Category newCategory : old) {
            if (!newCategory.equals(category)) {
                categories.add(newCategory);
            }
        }
        return categories;
    }

    private void checkPrice(String price) throws WrongProductDataException {
        if (price.isEmpty() || price == null) {
            throw new WrongProductDataException("Price is a required field!");
        }

        Pattern
        pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(price);
        if (!matcher.find()) {
            throw new WrongProductDataException("Price must contain only digit");
        }
    }
}
