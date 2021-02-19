<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">

<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="/localization.MessagesBundle" var="loc"/>
    <fmt:message bundle="${loc}" key="label.nickname" var="nickname"/>
    <fmt:message bundle="${loc}" key="label.password" var="password"/>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Login</title>
</head>
<style>
    main {
        margin-top: 250px;
    }

    form {
        width: 360px;
        margin: 0 auto;
    }
</style>

<body class="text-center">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<main>
    <form>
        <input type="hidden" name="command" value="authorization">
        <div class="form-group">
            <label for="exampleInputEmail1">${nickname}</label>
            <input type="text" class="form-control" id="exampleInputEmail1" name="username" aria-describedby="emailHelp" placeholder="Enter nickname" required autocomplete="off">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">${password}</label>
            <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password" required>
        </div>
        <input type="submit" class="btn btn-outline-info" value="Login">
    </form>
</main>
</div>

<style>
    .error{
        color: red;
    }
</style>

<div class="error">
    <c:out value="${error}"/>
</div>
</body>

</html>