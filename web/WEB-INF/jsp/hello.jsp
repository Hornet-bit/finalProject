<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="header.jsp"/>
    <title>hello</title>
</head>
<body>
<h1>Приветствую тебя, странник</h1>
<%--<c:out value="${user.username}" default="ничего"/>--%>
<%--<p/>--%>
<%--<c:out value="${user.role}" default="роль не определена"/>--%>



<form action="controller" method="post">
    <input type="hidden" name="command" value="view_assigned_tests">
    <input type="submit" value="посмотреть назначенные мне тесты">
</form>

<c:if test="${user.getUserRole() eq 'admin'}">
    <c:import url="admin_actions.jsp"/>
</c:if>
</body>
</html>
