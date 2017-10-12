<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
<html>
<jsp:include page="languages.jsp"/> <br>
<head>
    <h2> &nbsp;&nbsp; Authentication</h2>
</head>
<body>
<span>${message}</span>
<span class="error">${error}</span>
<form name="client registration" action="ShoppingServlet" method="POST">
<input type="hidden" name="action" value="CLIENT_REGISTRATION"><button class="myButton">registration</button>
</form>
<form name="authenticate" action="ShoppingServlet" method="POST">
<table>
    <tr>
        <td>Enter your username:</td><td><input id="user_name" class = "dataField" type="text" name="user_name" value=""/></td>
    </tr>
    <tr>
        <td>Enter your password:</td><td><input id="password" class = "dataField" type="password" name="password" value=""/><br/></td>
    </tr>
    <tr>
        <td></td><td align="right"><input type="hidden" name="action" value="AUTHENTICATE"> <input class="myButton" type="submit" value="submit"></td>
    </tr>
</table>
</form>
</body>
</html>
