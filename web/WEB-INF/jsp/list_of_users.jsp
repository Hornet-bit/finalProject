<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Title</title>
    <c:import url="header.jsp"/>
</head>
<body>
<h1>Тут список пользователей</h1>
<c:set var="i" scope="page" value="0"></c:set>
<form action="controller" method="post">
    <c:forEach var="user" items="${user_list}">
        <fieldset>
<%--            <c:out value="${i = i+ 1}"/>--%>
            <c:out value="${user.username}"/>
            <c:out value="${user.role}"/>
<%--            <input type="checkbox" name="set_check${i}" value="${user.userId}">--%>
            <button value="${user.username}" name="username">кнопка</button>


            <input type="hidden" name="command" value="change_role">
                <%--        <c:out value="${user.userId}"/>--%>
                <%--            ${i = i + 1}--%>

        </fieldset>
    </c:forEach>

</form>
</body>
</html>
