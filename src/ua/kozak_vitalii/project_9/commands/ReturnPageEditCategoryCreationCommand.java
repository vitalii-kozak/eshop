package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.StringTokenizer;

public class ReturnPageEditCategoryCreationCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public ReturnPageEditCategoryCreationCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("ReturnPageCategoryCreationCommand()");
        List categoriesList = adminService.getCategories();
        request.setAttribute("categorieslist", categoriesList);

        Category newCategory = GetCategoryFromRequest(request);

        request.setAttribute("category_id", newCategory.getId());
        request.setAttribute("category_name", newCategory.getName());

        return "/category_edit.jsp";
    }

    private Category GetCategoryFromRequest(HttpServletRequest request) {
        String users = request.getParameter("categories.category_selected");
        StringTokenizer t = new StringTokenizer(users,"|");
        String userId = t.nextToken();

        return adminService.getCategory(new Long(userId.trim()).longValue());
    }
}
