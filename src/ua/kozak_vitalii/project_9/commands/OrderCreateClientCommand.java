package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderCreateClientCommand extends Command {

    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public OrderCreateClientCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("OrderCreateClientCommand()");

        List productsList = adminService.getProducts();
        request.setAttribute("productslist", productsList);

        return "/order.jsp";
    }
}
