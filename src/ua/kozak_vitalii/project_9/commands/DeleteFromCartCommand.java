package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteFromCartCommand extends Command  {
    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public DeleteFromCartCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("DeleteFromCartCommand()");
        HttpSession session = request.getSession(true);

        List buylist=
                (List) session.getAttribute("shoppingcart");

        String del = request.getParameter("delindex");
        int d = (new Integer(del)).intValue();
        buylist.remove(d);

        session.setAttribute("shoppingcart", buylist);

        List productsList = adminService.getProducts();
        request.setAttribute("productslist", productsList);
        return "/order.jsp";
    }
}
