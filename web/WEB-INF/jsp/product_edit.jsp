<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/> <br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Product edit</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/> <br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<jsp:include page="products.jsp" flush="true" />
<form name="edit_a_product" action="ShoppingServlet" method="POST">
    <fieldset>
        <table>
            <tr>
                <td>Code:</td><td><input class = "dataField" id="product_id" type="text" readonly name="product_id" value="${product_id}"/><br/></td>
            </tr>
            <tr>
                <td>Name:</td><td><input class = "dataField" id="product_name" type="text" name="product_name" value="${product_name}"/><br/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input class="dataField" id="product_price" input type="number" min="0.01" max="1000000" step="0.01"
                           name="product_price" value="${product_price}"/><br/></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <form name="categories_form" action="ShoppingServlet" method="POST">
                        <select name=products.category_selected>
                            <c:forEach var="category" items="${product_categorieslist}">
                                <option>${category.presentation}</option>
                            </c:forEach>
                        </select>
                    </form>
                    <br/></td>
            </tr>
            <tr>
                <td></td><td><input type="hidden" name="action" value="UPDATE_PRODUCT_INFO"> <input type="submit" class="myButton" value="Update a product"/></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>