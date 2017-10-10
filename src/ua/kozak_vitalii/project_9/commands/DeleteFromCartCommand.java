package ua.kozak_vitalii.project_9.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteFromCartCommand extends Command  {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        List buylist=
                (List) session.getAttribute("shopping.shoppingcart");

        String del = request.getParameter("delindex");
        int d = (new Integer(del)).intValue();
        buylist.remove(d);

        session.setAttribute("shopping.shoppingcart", buylist);
        return "/EShop.jsp";
    }
}
