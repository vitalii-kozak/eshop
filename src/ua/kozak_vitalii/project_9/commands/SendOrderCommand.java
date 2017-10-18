package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Order;
import ua.kozak_vitalii.project_9.domain.ProductOrder;
import ua.kozak_vitalii.project_9.domain.User;
import ua.kozak_vitalii.project_9.exceptions.RegistrationException;
import ua.kozak_vitalii.project_9.exceptions.WrongOrderDataException;
import ua.kozak_vitalii.project_9.service.AdminService;
import ua.kozak_vitalii.project_9.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class SendOrderCommand extends Command {
    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final ClientService clientService;

    public SendOrderCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("SendOrderCommand()");
        HttpSession session = request.getSession(true);
        User user = (User) request.getSession().getAttribute("user");

        List buylist=
                (List) session.getAttribute("shoppingcart");
        BigDecimal total = new BigDecimal("0.00");
        for (int i=0; i< buylist.size();i++) {
            ProductOrder anOrder = (ProductOrder) buylist.get(i);
            BigDecimal price= anOrder.getProduct().getPrice();
            int qty = anOrder.getProductQuantity();
            total = total.add(price.multiply(new BigDecimal(qty)));
        }

        try {
            boolean result = clientService.addNewOrder(buylist, user, total);
            if (result) {

                request.setAttribute("message", "Sending is successful. "+ "order is added");

                buylist.clear();
                session.setAttribute("shoppingcart", buylist);
                List productsList = clientService.getProducts();
                request.setAttribute("productslist", productsList);

                return "/client_page.jsp";

            } else {
                request.setAttribute("error", "An internal error occurred while trying to send a new order");
            }
        } catch (WrongOrderDataException e) {
            request.setAttribute("error", e.getMessage());
        }

        return "/client_page.jsp";
    }
}
