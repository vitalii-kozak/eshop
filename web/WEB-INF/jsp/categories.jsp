<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="left">

    <table>
        <tr>
            <td>
                <form name="categories_form" action="ShoppingServlet" method="POST">
                    <b>Choose category :</b>
                    <select name=categories.category_selected>
                        <c:forEach var="category" items="${categorieslist}">
                            <option>${category.presentation}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="EDIT_CATEGORY_LINK">
                    <input type="submit" name="Submit" value="Edit Category">
                </form>
            </td>
            <td>
            <form name="category_add_new" action="ShoppingServlet" method="POST">
                <input type="hidden" name="action" value="CATEGORY_CREATE_LINK">
                <input type="submit" name="Submit" value="Add new Category">
            </form>
            </td>
        </tr>
    </table>


</div>

