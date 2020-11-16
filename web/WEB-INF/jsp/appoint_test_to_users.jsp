<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:import url="header.jsp"/>
</head>
<body>
<h1>Выберите человека, которому нужно назначить тест</h1>
<c:set var="i" scope="page" value="0"></c:set>
<form action="controller" method="post">
    <c:forEach var="user" items="${user_list}">
        <fieldset>
            <c:out value="${i = i+ 1}"/>
            <c:out value="${user.username}"/>
            <c:out value="${user.role}"/>
            <input type="checkbox" name="set_check${i}" value="${user.userId}">
                <%--        <c:out value="${user.userId}"/>--%>
<%--            ${i = i + 1}--%>

        </fieldset>
    </c:forEach>
<%--    сделать различное отобрадение для просто списка пользователей и для назначеня им теста--%>
    <input type="hidden" name="command" value="appoint_test">
    <input type="submit" value="выбрать беднягам тесты">
    <input type="hidden" name="count" value="${i}">
</form>
</body>
</html>
