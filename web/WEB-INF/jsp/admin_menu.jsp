<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:setLocale value="${sessionScope.locale}" scope="session" />
<c:bundle basename="resource.l10n" >
<ul id="main-menu">
    <li> &nbsp; </li>
    <li><form name="client registration" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="USER_INFO"> <input type="hidden" name="action" value="INFO"> <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.userInfo"/>"></form></li><li> &nbsp; </li>
    <li><form name="client registration" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="CLIENT_REGISTRATION"> <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.createUser"/>"></form></li><li> &nbsp; </li>
    <li><form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="UPDATE_USER"> <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.updateUser"/>"></form></li><li> &nbsp; </li>
    <li><form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="CATEGORY_CREATE_MENU"> <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.Category"/>"></form></li><li> &nbsp; </li>
    <li><form name="update user" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="PRODUCT_CREATE_MENU"> <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.Product"/>"></form></li><li> &nbsp; </li>
    <li><form name="logout" action="ShoppingServlet" method="post"><input type="hidden" name="action" value="LOGOUT"> <input class="myButtonMenu" type="submit" value="<c:message key="admin_menu.Logout"/>"></form></li><li> &nbsp; </li>
</ul>
</c:bundle>

