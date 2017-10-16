package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.enums.UserType;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticateCommand extends Command{

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public AuthenticateCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("AuthenticateCommand()");
        User user = null;
        try {
             user = adminService.login(request.getParameter("user_name"), request.getParameter("password"));
        } catch (Throwable theException) {
            System.out.println(theException);
        }

        if (user == null) {
            request.setAttribute("error", "No user with such data found. Wrong login or password!");
            return "/index.jsp";

        }else if (user.getIsBlocked()) {
            request.setAttribute("error", "Warning! User is banned!");
            return "/index.jsp";

        } else {
            request.getSession().setAttribute("user", user);
            request.setAttribute("message", user.getName());
            if (user.getUserType() == UserType.ADMIN) {
                return "/admin_page.jsp";
            }
            return "/client_page.jsp";
        }
    }
}
