<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:setLocale value="${sessionScope.locale}" scope="session" />
<c:bundle basename="resource.l10n" >
<elc:h2/>
<ul id="main-menu">
    <li><a href="${pageContext.request.contextPath}"><c:message key="main.menu"/></a>
    <li><a href="courses?course_type=current"><c:message key="courses.menu"/></a>
    <li><a href="archive"><c:message key="archive.menu"/></a>
    <li><a href="available_courses"><c:message key="available_courses.menu"/></a>
    <li><a href="logout"><c:message key="logout.menu"/></a>
</ul>
</c:bundle>
