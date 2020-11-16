<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <c:import url="header.jsp"/>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Hello</title>
</head>
<body class="text-center">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<h1>Приветствую тебя, странник</h1>
<!--
<%--<c:out value="${user.username}" default="ничего"/>--%>
<%--<p/>--%>
<%--<c:out value="${user.role}" default="роль не определена"/>--%>
-->
<main>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="view_assigned_tests">
        <input type="submit" class="btn btn-outline-info" value="Посмотреть назначенные мне тесты">
    </form>
</main>
<c:if test="${user.getUserRole() eq 'admin'}">
    <c:import url="admin_actions.jsp"/>
</c:if>
</body>

</html>