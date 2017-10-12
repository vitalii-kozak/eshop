package ua.kozak_vitalii.project_9.commands;

import ua.kozak_vitalii.project_9.domain.ProductOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class CheckOutCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        List buylist=
                (List) session.getAttribute("shoppingcart");
        BigDecimal total = new BigDecimal("0.00");
        for (int i=0; i< buylist.size();i++) {
            ProductOrder anOrder = (ProductOrder) buylist.get(i);
            BigDecimal price= anOrder.getProduct().getPrice();
            int qty = anOrder.getProductQuantity();
            total = total.add(price.multiply(new BigDecimal(qty)));
            System.out.println(total);
        }
        total.add(new BigDecimal("0.005"));
        String amount = total.toString();
        int n = amount.indexOf('.');
        amount = amount.substring(0,n+3);
        request.setAttribute("amount",amount);
        return "/Checkout.jsp";
    }
}
