package ua.kozak_vitalii.project_9.commands;

import org.apache.log4j.Logger;
import ua.kozak_vitalii.project_9.domain.Category;
import ua.kozak_vitalii.project_9.domain.Product;
import ua.kozak_vitalii.project_9.domain.ProductOrder;
import ua.kozak_vitalii.project_9.service.AdminService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AddToCartCommand extends Command {
    private static final Logger logger = Logger.getLogger(AdminService.class);
    private final AdminService adminService;

    public AddToCartCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("AddToCartCommand");
        HttpSession session = request.getSession(true);

        List buylist= (List) session.getAttribute("shoppingcart");

        boolean match=false;
        ProductOrder newProductOrder = GetProduct(request);
        if (buylist==null) {
            //добавить первый товар в корзину
            buylist = new ArrayList(); //первый заказ
            buylist.add(newProductOrder);
        } else { // не первая покупка
            for (int i=0; i< buylist.size(); i++) {
                ProductOrder productOrder = (ProductOrder) buylist.get(i);
                if (productOrder.getProduct().getId().equals(newProductOrder.getProduct().getId())) {
                    productOrder.setProductQuantity(productOrder.getProductQuantity() + newProductOrder.getProductQuantity());
                    buylist.set(i, productOrder);
                    match = true;
                }
            }
            if (!match)
                buylist.add(newProductOrder);
        }
        session.setAttribute("shoppingcart", buylist);

        List productsList = adminService.getProducts();
        request.setAttribute("productslist", productsList);
        request.setAttribute("buylist", buylist);
        return "/order.jsp";
    }

    private ProductOrder GetProduct(HttpServletRequest request) {
        String requestProduct = request.getParameter("order.product_selected");
        String qty = request.getParameter("qty");
        StringTokenizer t = new StringTokenizer(requestProduct,"|");
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


