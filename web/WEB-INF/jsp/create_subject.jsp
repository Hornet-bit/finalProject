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
<h1>Создание предмета</h1>

<form action="controller" name="create_subj" method="get">

    <input type="hidden" name="command" value="create_new_subject">

    <input type="text" name="name" placeholder="name" autocomplete="off">
    <input type="text" name="description" placeholder="description" autocomplete="off">
    <input type="submit" value="create subj">

</form>

</body>
</html>
