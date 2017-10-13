<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="left">
    <table>
        <tr>
            <td>
                <form name="products_form" action="ShoppingServlet" method="POST">
                    <b>Choose a product :</b>
                    <select name=products.product_selected>
                        <c:forEach var="product" items="${productslist}">
                            <option>${product.presentation}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="EDIT_PRODUCT_LINK">
                    <input type="submit" name="Submit" value="Edit Product">
                </form>
            </td>
            <td>
                <form name="product_add_new" action="ShoppingServlet" method="POST">
                    <input type="hidden" name="action" value="PRODUCT_CREATE_LINK">
                    <input type="submit" name="Submit" value="Add new Product">
                </form>
            </td>
        </tr>
    </table>
</div>

