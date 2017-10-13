package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReturnPageEditProductCreationCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public ReturnPageEditProductCreationCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("ReturnPageEditProductCreationCommand()");
        List productsList = adminService.getProducts();
        List categoriesList = adminService.getCategories();

        Product newProduct = GetProductFromRequest(request);
        categoriesList = deleteCategoryFromList(categoriesList,newProduct.getCategory());
        categoriesList.add(0, newProduct.getCategory());

        request.setAttribute("product_categorieslist", categoriesList);
        request.setAttribute("productslist", productsList);

        request.setAttribute("product_id", newProduct.getId());
        request.setAttribute("product_name", newProduct.getName());
        request.setAttribute("product_price", newProduct.getPrice());

        return "/product_edit.jsp";
    }

    private Product GetProductFromRequest(HttpServletRequest request) {
        String items = request.getParameter("products.product_selected");
        StringTokenizer t = new StringTokenizer(items,"|");
        String itemId = t.nextToken();

        return adminService.getProduct(new Long(itemId.trim()).longValue());
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
}
