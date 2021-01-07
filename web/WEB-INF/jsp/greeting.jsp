<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <c:set var="page" scope="session" value="greeting.jsp"/>

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="/localization.MessagesBundle" var="loc"/>
    <fmt:message bundle="${loc}" key="local.message" var="message"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>


    <!-- Required meta tags -->
    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Greeting</title>
</head>
<style>
    main {
        margin-top: 250px;
    }
</style>

<body class="text-center">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<main>
    <h1>Lorem Ipsum</h1>
    <p class="lead">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.</p>
    <div class="btn-group" role="group" aria-label="Basic example">
        <form action="controller" class="mr-2" name="go_to_registration" method="get">
            <input type="hidden" name="command" value="go_to_registration">
            <input type="submit" class="btn btn-outline-info" value="Registration">
        </form>
        <form action="controller" name="go_to_login" method="get">
            <input type="hidden" name="command" value="go_to_login">
            <input type="submit" class="btn btn-outline-info" value="логин">
        </form>
    </div>
    <br>
    <div class="btn-group mt-2" role="group" aria-label="Button group with nested dropdown">
        <div class="btn-group" role="group">
            <button id="btnGroupDrop1" type="button" class="btn btn-outline-info dropdown-toggle btn-sm" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Language </button>
            <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">

                <form action="controller" name="change_language" method="get">
                    <input type="submit" class="dropdown-item" value="${ru_button}">
                    <input type="hidden" name="command" value="change_language" >
                    <input type="hidden" name="local" value="ru" >
                </form>

                <form action="controller" name="change_language" method="get">
                    <input type="submit" class="dropdown-item" value="${en_button}">
                    <input type="hidden" name="command" value="change_language" >
                    <input type="hidden" name="local" value="en" >
                </form>



            </div>
        </div>
    </div>

</main>
</body>

</html>