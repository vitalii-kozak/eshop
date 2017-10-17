package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.ProductOrder;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class CheckOutCommand extends Command {
    private static final Logger logger = Logger.getLogger(AdminService.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("CheckOutCommand()");
        HttpSession session = request.getSession(true);

        List buylist=
                (List) session.getAttribute("shoppingcart");
        BigDecimal total = new BigDecimal("0.00");
        for (int i=0; i< buylist.size();i++) {
            ProductOrder anOrder = (ProductOrder) buylist.get(i);
            BigDecimal price= anOrder.getProduct().getPrice();
            int qty = anOrder.getProductQuantity();
            total = total.add(price.multiply(new BigDecimal(qty)));
        }
        total.add(new BigDecimal("0.005"));
        String amount = total.toString();
        int n = amount.indexOf('.');
        amount = amount.substring(0,n+3);

        request.setAttribute("amount",amount);

        return "/order_checkout.jsp";
    }
}
