<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div align="center">
    <table border="0" cellpadding="0" width="100%" bgcolor="#FFFFFF">
        <tr>
            <td><b>PRODUCT_ID</b></td>
            <td><b>PRODUCT</b></td>
            <td><b>CATEGORY</b></td>
            <td><b>PRICE</b></td>
            <td><b>QUANTITY</b></td>
            <td></td>
        </tr>
        <c:forEach var="anOrder" items="${sessionScope.shoppingcart}" varStatus="loop">
        <tr>
            <td><b>${anOrder.product.id}</b></td>
            <td><b>${anOrder.product.name}</b></td>
            <td><b>${anOrder.product.category.name}</b></td>
            <td><b>${anOrder.product.price }</b></td>
            <td><b>${anOrder.productQuantity }</b></td>
            <td>
                <form name="deleteForm"
                      action="ShoppingServlet"
                      method="POST">
                    <input type="submit" value="Delete">
                    <input type="hidden" name= "delindex" value="${loop.index}">
                    <input type="hidden" name="action" value="DELETE">
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
    <p>
    <form name="checkoutForm"
          action="ShoppingServlet"
          method="POST">
        <input type="hidden" name="action" value="CHECKOUT">
        <input type="submit" name="Checkout" value="Checkout">
    </form>
</div>