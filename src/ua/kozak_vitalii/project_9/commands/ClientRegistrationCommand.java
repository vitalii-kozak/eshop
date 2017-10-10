package ua.kozak_vitalii.project_9.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientRegistrationCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/client_registration.jsp";
    }
}
