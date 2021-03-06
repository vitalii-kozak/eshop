package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.enums.UserType;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientRegistrationCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("ClientRegistrationCommand()");
        User user = (User) request.getSession().getAttribute("user");

        if ((user != null) && (user.getUserType() == UserType.ADMIN)) {
            return "/admin_registration.jsp";
        }
        return "/client_registration.jsp";
    }
}
