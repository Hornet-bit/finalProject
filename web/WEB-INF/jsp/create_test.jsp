<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create u own test</title>
</head>
<body>
<h1>Создай свой новый тест прямо тут!</h1>

<form action="controller" method="get">

    <fieldset>
        <input type="text" name="test_name" placeholder="Введите название теста">

        <input type="text" name="test_description" placeholder="Описание теста">

        <input type="number" name="count_of_question" placeholder="количество вопросов">

    </fieldset>

    <input type="hidden" name="command" value="create_test">
    <input name="add_sms" type="submit" value="Добавить вопрос">
</form>


</body>
</html>
