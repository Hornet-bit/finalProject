<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>greeting page</title>
    <h1>Приветствие</h1>
</head>
<body>


<form action="controller" name="go_to_registration" method="get">
    <input type="hidden" name="command" value="go_to_registration">

    <input type="submit" value="Registration">
</form>


<form action="controller" name="go_to_login" method="get">
    <input type="hidden" name="command" value="go_to_login">

    <input type="submit" value="login">
</form>
<p>
</p>

<form action="controller" name="change_language_ru" method="get">
    <input type="submit" value="ru">
</form>
<form action="controller" name="change_language_en" method="get">
    <input type="submit" value="en">
</form>

<%--<form action="controller" name="go_to_settings" method="get">--%>
<%--    <input type="submit" value="settings">--%>
<%--</form>--%>
</body>
</html>
