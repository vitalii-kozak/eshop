package ua.kozak_vitalii.project_9.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class LogOutCommand extends Command {

    private static final Logger logger = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        logger.debug("LogOutCommand");
        session.removeAttribute("user");
        session.invalidate();
        return "/index.jsp";
    }
}
