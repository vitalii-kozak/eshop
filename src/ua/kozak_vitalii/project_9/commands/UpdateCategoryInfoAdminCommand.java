package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.exceptions.WrongCategoryDataException;
import ua.kozak_vitalii.project_9.exceptions.WrongUserDataException;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateCategoryInfoAdminCommand extends Command {

    private static final Logger logger = Logger.getLogger(LogOutCommand.class);
    private final AdminService adminService;

    public UpdateCategoryInfoAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        logger.debug("UpdateCategoryInfoAdminCommand");

        User user = (User) session.getAttribute("user");
        List categoriesList = adminService.getCategories();
        request.setAttribute("categorieslist", categoriesList);

        String categoryId = request.getParameter("category_id");
        Category newCategory = adminService.getCategory(new Long(categoryId.trim()).longValue());
        String categoryName = request.getParameter("category_name");
        newCategory.setName(categoryName);


        try {
            boolean result = adminService.updateCategory(newCategory);
            if (result) {

                request.setAttribute("message", "Changing is successful. "+ newCategory.getName() + " is updated");

                request.setAttribute("category_id", newCategory.getId());
                request.setAttribute("category_name", newCategory.getName());

                categoriesList = adminService.getCategories();
                request.setAttribute("categorieslist", categoriesList);
                return "/category.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to register a new user");
            }
        } catch (WrongCategoryDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("category_id", newCategory.getId());
        request.setAttribute("category_name", newCategory.getName());

          return "/category_edit.jsp";
    }
}
