<%@ page session="true" import="java.util.*, ua.kozak_vitalii.project_9.domain.Category, ua.kozak_vitalii.project_9.domain.Product, ua.kozak_vitalii.project_9.domain.ProductOrder" %>
<html>
<head>
 <%@ page session="true"
          import="java.util.*, shopping.CD" %>
 <%@ page import="java.lang.reflect.Array" %>
 <html>
 <head>
  <title>Eshop1</title>
 </head>
<body bgcolor="#33CCFF">
<font face="Times New Roman,Times" size=+3>
 Your order
</font>
<hr><p>
 <center>
  <table border="0"
         cellpadding="0"
         width="100%" bgcolor="#FFFFFF">
   <tr>
    <td><b>PRODUCT_ID</b></td>
    <td><b>PRODUCT</b></td>
    <td><b>CATEGORY</b></td>
    <td><b>PRICE</b></td>
    <td><b>QUANTITY</b></td>
    <td></td>
   </tr>
   <%
    ArrayList buylist = (ArrayList) session.getAttribute("shopping.shoppingcart");
    String amount = (String) request.getAttribute("amount");
    for (int i=0; i < buylist.size();i++) {
     ProductOrder anOrder = (ProductOrder) buylist.get(i);
   %>
   <tr>
    <td><b><%= anOrder.getProduct().getId() %></b></td>
    <td><b><%= anOrder.getProduct().getName() %></b></td>
    <td><b><%= anOrder.getProduct().getCategory().getName() %></b></td>
    <td><b><%= anOrder.getProduct().getPrice() %></b></td>
    <td><b><%= anOrder.getProductQuantity() %></b></td>
   </tr>
   <%
    }
    session.invalidate();
   %>
   <tr>
    <td> </td>
    <td> </td>
    <td><b>TOTAL</b></td>
    <td><b>$<%= amount %></b></td>
    <td> </td>
   </tr>
  </table>
<p>
 <a href="/WEB-INF/jsp/EShop.jsp">Shop some more!</a>
 </center>
</body>
</html>