<%@ page session="true" import="java.util.*, ua.kozak_vitalii.project_9.domain.Category, ua.kozak_vitalii.project_9.domain.Product, ua.kozak_vitalii.project_9.domain.ProductOrder" %>
<%
 List buylist = (List) session.getAttribute("shopping.shoppingcart");
 if (buylist != null && (buylist.size() > 0)) {
%>
<center>
 <table border="0" cellpadding="0" width="100%" bgcolor="#FFFFFF">
  <tr>
   <td><b>PRODUCT_ID</b></td>
   <td><b>PRODUCT</b></td>
   <td><b>CATEGORY</b></td>
   <td><b>PRICE</b></td>
   <td><b>QUANTITY</b></td>
   <td></td>
  </tr>
  <%
   for (int index=0; index < buylist.size();index++) {
    ProductOrder anOrder = (ProductOrder) buylist.get(index);
  %>
  <tr>
   <td><b><%= anOrder.getProduct().getId() %></b></td>
   <td><b><%= anOrder.getProduct().getName() %></b></td>
   <td><b><%= anOrder.getProduct().getCategory().getName() %></b></td>
   <td><b><%= anOrder.getProduct().getPrice() %></b></td>
   <td><b><%= anOrder.getProductQuantity() %></b></td>
   <td>
    <form name="deleteForm"
          action="ShoppingServlet"
          method="POST">
     <input type="submit" value="Delete">
     <input type="hidden" name= "delindex" value='<%= index %>'>
     <input type="hidden" name="action" value="DELETE">
    </form>
   </td>
  </tr>
  <% } %>
 </table>
 <p>
 <form name="checkoutForm"
       action="ShoppingServlet"
       method="POST">
  <input type="hidden" name="action" value="CHECKOUT">
  <input type="submit" name="Checkout" value="Checkout">
 </form>
</center>
<% } %>