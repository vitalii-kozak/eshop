<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" errorPage="exception.jsp" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="languages.jsp"/> <br>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css"/>
	<h2> &nbsp;&nbsp; Registration</h2>
</head>
  <body>
  <span>${message}</span>
  <span class="error">${error}</span>
  <br>
	  <form name="registration" action="ShoppingServlet" method="POST">
		<fieldset>
		  <span class="error">${error}</span>
		  <table>
			<tr>
			  <td>Login:</td><td><input id="login" class = "dataField" type="text" name="login" value="${login}"/><br/></td>
			</tr>
			<tr>
			  <td>Password:</td><td><input class = "dataField" id="password" type="password" name="password"/><br/></td>
			</tr>
			<tr>
			  <td>Password confirmation:</td><td><input class = "dataField" id="password_confirmation" type="password" name="password_confirmation"/><br/></td>
			</tr>
			<tr>
			  <td>Name:</td><td><input class = "dataField" id="name" type="text" name="name" value=""/><br/></td>
			</tr>
			<tr>
			  <td>Surname:</td><td><input class = "dataField" id="surname" type="text" name="surname" value=""/><br/></td>
			</tr>
                <tr>
                  <td>user_type:</td>
                    <td>
                      <input type="radio" name="user_type" value="client" checked/> CLIENT
                      <br/>
                    </td>
                </tr>
			<tr>
			  <td></td><td><input type="hidden" name="action" value="ADD_NEW_USER"> <input type="submit" class="myButton" value="Add new user"/></td>
			</tr>
		  </table>
		</fieldset>
	  </form>
  </body>
</html>
