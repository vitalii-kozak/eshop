package ua.kozak_vitalii.project_9.commands;

import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.domain.ProductOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AddToCartCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);

        List buylist=
                (List) session.getAttribute("shopping.shoppingcart");

        boolean match=false;
        ProductOrder aProductOrder = GetProduct(request);
        if (buylist==null) {
            //добавить первый товар в корзину
            buylist = new ArrayList(); //первый заказ
            buylist.add(aProductOrder);
        } else { // не первая покупка
            for (int i=0; i< buylist.size(); i++) {
                ProductOrder productOrder = (ProductOrder) buylist.get(i);
                if (productOrder.getProduct().getId().equals(aProductOrder.getProduct().getId())) {
                    productOrder.setProductQuantity(productOrder.getProductQuantity() + aProductOrder.getProductQuantity());
                    buylist.set(i, productOrder);
                    match = true;
                } //конец блока If
            } // конец блока For
            if (!match)
                buylist.add(aProductOrder);
        }
        session.setAttribute("shopping.shoppingcart", buylist);
        return "/EShop.jsp";
    }

    private ProductOrder GetProduct(HttpServletRequest req) {
        //представьте, что это скриптлет... ужасно, не правда ли?
        String myCd = req.getParameter("Product");
        String qty = req.getParameter("qty");
        StringTokenizer t = new StringTokenizer(myCd,"|");
        String productId = t.nextToken();
        String productName = t.nextToken();
        String categoryId = t.nextToken();
        String categoryName = t.nextToken();
        String price = t.nextToken();
        price = price.replace('$',' ').trim();
        Category category = new Category(categoryName);
        category.setId(new Long(categoryId.trim()).longValue());
        Product product = new Product(productName, category, new BigDecimal(price));
        product.setId(new Long(productId.trim()).longValue());
        ProductOrder productOrder = new ProductOrder(product, new Integer(qty.trim()));
        return productOrder;
    }

}


