package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.exceptions.WrongCategoryDataException;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddNewCategoryCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public AddNewCategoryCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("AddNewCategoryCommand()");

        String name = request.getParameter("name");
        try {
            boolean result = adminService.addNewCategory(name);
            if (result) {
                List categoriesList = adminService.getCategories();
                request.setAttribute("categorieslist", categoriesList);
                request.setAttribute("message", "Category creation is successful. " + name + " is added");
                return "/category.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to register a new category");
            }
        } catch (WrongCategoryDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        List categoriesList = adminService.getCategories();
        request.setAttribute("categorieslist", categoriesList);
        return "/category_create_new.jsp";
    }
}
