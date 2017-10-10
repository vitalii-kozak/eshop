package ua.kozak_vitalii.project_9.servlets;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.commands.Command;
import ua.kozak_vitalii.project_9.enums.DaoType;
import ua.kozak_vitalii.project_9.enums.ServiceType;
import ua.kozak_vitalii.project_9.factories.CommandFactory;
import ua.kozak_vitalii.project_9.factories.DaoFactory;
import ua.kozak_vitalii.project_9.factories.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;


public class ShoppingServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ShoppingServlet.class);
    private ResourceBundle servletProperties = ResourceBundle.getBundle("resource.servlet_config");

    @Override
    public void init() throws ServletException {
        logger.info("Initializing Main Controller");
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("POST request: " + request.getPathInfo());
        handleRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("GET request: " + request.getPathInfo());
        handleRequest(request, response);
    }

    /**
     * Handles request that came from the client
     * Gets response string and passes it to RequestDispatcher
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     */
    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String result;
        if (session == null) {
            result = "/Error.jsp";
        } else {

            String action = request.getParameter("action");

            Command command = CommandFactory.getCommand(action);
            result = command.execute(request, response);

            if (result == null) {
                // error404 page non found
                result = "/Error.jsp";
            }
        }

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/jsp"  + result);
        rd.forward(request, response);
    }

}