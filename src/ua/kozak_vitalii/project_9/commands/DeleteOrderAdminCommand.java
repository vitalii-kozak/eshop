package ua.kozak_vitalii.project_9.commands;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Order;
import ua.kozak_vitalii.project_9.exceptions.WrongOrderDataException;
import ua.kozak_vitalii.project_9.service.AdminService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteOrderAdminCommand extends Command  {
    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public DeleteOrderAdminCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("DeleteOrderAdminCommand()");
        HttpSession session = request.getSession(true);

        List ordersList=(List) session.getAttribute("orderslist");
        //List ordersList=(List) request.getAttribute("orderslist");

        String del = request.getParameter("delindex");
        int index = (new Integer(del)).intValue();
        Order order = (Order) ordersList.get(index);

        try {
            boolean result = adminService.deleteOrder(order);
            if (result) {

                request.setAttribute("message", "Deleting is successful");
                ordersList.remove(index);
                session.setAttribute("orderslist", ordersList);
                return "/admin_orders.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to delete a order");
            }
        } catch (WrongOrderDataException e) {
            request.setAttribute("error", e.getMessage());
        }



        session.setAttribute("orderslist", ordersList);
        //request.setAttribute("orderslist", ordersList);

        return "/admin_orders.jsp";
    }
}
