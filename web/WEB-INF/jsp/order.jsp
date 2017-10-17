<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="languages.jsp"/>
<br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Products</h2>
</head>
<body>
<jsp:include page="client_menu.jsp"/> <br>
<span>${message}</span>
<span class="error">${error}</span>
<br>

<div align="left">
    <table>
        <tr>
            <td>
                <form name="products_form" action="ShoppingServlet" method="POST">
                    <b>Choose a product :</b>
                    <select name=order.product_selected>
                        <c:forEach var="product" items="${productslist}">
                            <option>${product.presentation}</option>
                        </c:forEach>
                    </select>
                    <b>Quantity: </b><input type="text" name="qty" SIZE="3" value=1>
                    <input type="hidden" name="action" value="ADD">
                    <input type="submit" name="Submit" value="Add to Cart">
                </form>
            </td>
            <td>

            </td>
        </tr>
    </table>
</div>

    <fieldset>
        <jsp:include page="Cart.jsp" flush="true" />
    </fieldset>

</body>
</html>