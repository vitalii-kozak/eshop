<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/>
<br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Products</h2>
</head>
<body>
<jsp:include page="admin_menu.jsp"/> <br>
<span>${message}</span>
<span class="error">${error}</span>
<br>
<jsp:include page="products.jsp" flush="true"/>

<form name="products_list" action="ShoppingServlet" method="POST">
    <fieldset>
        <table width="100%">
            <thead>
            <tr>
                <td><b>
                    <PRODUCTS/>
                </b></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${productslist}">
                <tr>
                    <td valign="center">${product.presentation}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </fieldset>
</form>
</body>
</html>