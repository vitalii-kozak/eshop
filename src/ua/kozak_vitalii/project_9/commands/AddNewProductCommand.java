package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.exceptions.WrongProductDataException;
import ua.kozak_vitalii.project_9.service.AdminService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.StringTokenizer;

public class AddNewProductCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public AddNewProductCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("AddNewProductCommand()");

        String product_name = request.getParameter("product_name");
        String product_price = request.getParameter("product_price");
        Category product_category = GetCategoryFromRequest(request);

        try {
            boolean result = adminService.addNewProduct(product_name, product_category.getId(), new BigDecimal(product_price));
            if (result) {
                List productsList = adminService.getProducts();
                List categoriesList = adminService.getCategories();
                request.setAttribute("product_categorieslist", categoriesList);
                request.setAttribute("productslist", productsList);
                request.setAttribute("message", "Product creation is successful. " + product_name + " is added");
                return "/product.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to create a new product");
            }
        } catch (WrongProductDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        List productsList = adminService.getProducts();
        List categoriesList = adminService.getCategories();
        request.setAttribute("product_categorieslist", categoriesList);
        request.setAttribute("productslist", productsList);
        return "/product_create_new.jsp";
    }

    private Category GetCategoryFromRequest(HttpServletRequest request) {
        String items = request.getParameter("products.category_selected");
        StringTokenizer t = new StringTokenizer(items,"|");
        String itemId = t.nextToken();

        return adminService.getCategory(new Long(itemId.trim()).longValue());
    }
}
