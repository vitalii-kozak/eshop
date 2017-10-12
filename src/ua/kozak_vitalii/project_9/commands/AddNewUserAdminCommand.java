package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.enums.UserType;
import ua.kozak_vitalii.project_9.exceptions.RegistrationException;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewUserAdminCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public AddNewUserAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("AddNewUserAdminCommand()");
        UserType userType;
        userType = UserType.valueOf(request.getParameter("user_type").toUpperCase());

        System.out.println("**************************************************************");
        System.out.println(request.getParameter("is_blocked").trim());


        boolean isBlocked = request.getParameter("is_blocked").trim().equals("value_1") ? true : false;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        try {
            boolean result = adminService.registerUser(login, password, passwordConfirmation, name, surname, isBlocked, userType);
            if (result) {

                    request.setAttribute("message", "Registration is successful. "+ login + " is added");
                    return "/admin_registration.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to register a new user");
            }
        } catch (RegistrationException e) {
            request.setAttribute("error", e.getMessage());
        }
        return "/admin_registration.jsp";
    }
}
