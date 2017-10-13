<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="languages.jsp"/> <br>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
    <h2> &nbsp;&nbsp; Registration</h2>
</head>
<body>

<ul id="main-menu">
    <li> &nbsp; </li>
    <li><input type="hidden" name="action" value="INFO"> <input class="myButtonMenu" type="submit" value="Info"></li><li> &nbsp; </li>
    <li><form name="client registration" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="CLIENT_REGISTRATION"> <input class="myButtonMenu" type="submit" value="Create User"></form></li><li> &nbsp; </li>
    <li><form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="UPDATE_USER"> <input class="myButtonMenu" type="submit" value="Update User"></form></li><li> &nbsp; </li>
    <li><form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="CATEGORY_CREATE_MENU"> <input class="myButtonMenu" type="submit" value="Category"></form></li><li> &nbsp; </li>
    <li><form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="PRODUCT_CREATE_MENU"> <input class="myButtonMenu" type="submit" value="Product"></form></li><li> &nbsp; </li>
    <li><form name="logout" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="LOGOUT"> <input class="myButtonMenu" type="submit" value="Logout"></form></li><li> &nbsp; </li>
</ul>

<span>${message}</span>
<span class="error">${error}</span>
</body>
</html>