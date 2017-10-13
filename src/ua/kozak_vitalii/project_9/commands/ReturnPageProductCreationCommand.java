package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReturnPageProductCreationCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public ReturnPageProductCreationCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("ReturnPageProductCreationCommand()");
        List productsList = adminService.getProducts();
        List categoriesList = adminService.getCategories();
        request.setAttribute("product_categorieslist", categoriesList);
        request.setAttribute("productslist", productsList);

        return "/product_create_new.jsp";
    }
}
