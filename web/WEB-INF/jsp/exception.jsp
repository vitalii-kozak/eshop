<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:bundle basename="resource.i18n" prefix="exception.">
<html>
  <jsp:include page="head_part.jsp"/>
  <body>
    <div class="main">
      <h2><fmt:message key="h2"/></h2>
      <jsp:include page="go_back.jsp"/>
        <fmt:message key="message"/>
    </div>
  </body>
</html>
</fmt:bundle>