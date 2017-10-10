<%@ page session="true" %>
<html>
<head>
 <title>Eshop1</title>
</head>
<body bgcolor="#33CCFF">
 <font face="Times New Roman,Times" size="+3">
  Eshop1
  <%@ page session="true" %>
  <html>
  <head>
   <title>Eshop1</title>
  </head>
  <body bgcolor="#33CCFF">
  <font face="Times New Roman,Times" size="+3">
   Chose product
  </font>
  <hr><p>
  <center>
   <form name="shoppingForm"
         action="ShoppingServlet"
         method="POST">
    <b>shopping.product:</b>
    <select name=Product>
     <option>1 | Toshiba | 1 | HDD | $104.95</option>
     <option>2 | Hitachi | 1 | HDD | $106.95</option>
     <option>3 | Western Digital | 1 | HDD | $106.95</option>
     <option>4 | Seagate | 1 | HDD | $103.95</option>
     <option>5 | Kingston | 1 | HDD | $104.95</option>
    </select>
    <b>Quantity: </b><input type="text" name="qty" SIZE="3" value=1>
    <input type="hidden" name="action" value="ADD">
    <input type="submit" name="Submit" value="Add to Cart">
   </form>
  </center>
  <p>
   <jsp:include page="Cart.jsp" flush="true" />
  </body>
  </html>