<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="left">
 <form name="products_form" action="ShoppingServlet" method="POST">
  <b>Choose user :</b>
  <select name=products.product_selected>
      <c:forEach var="product" items="${productslist}">
          <option>${product.presentation}</option>
      </c:forEach>
  </select>
  <input type="hidden" name="action" value="SELECT_PRODUCT_CREATE">
  <input type="submit" name="Submit" value="Get Product">
 </form>
</div>

