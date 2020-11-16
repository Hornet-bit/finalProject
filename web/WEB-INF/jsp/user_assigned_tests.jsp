
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="header.jsp"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<h1>Мои назначенные тесты</h1>


<form action="controller" method="get">

    <div>
        <c:forEach var="tempTest" items="${list}">

            <fieldset>
                <c:out value="${tempTest.name}"></c:out>

                <c:out value="${tempTest.description}"></c:out>
                <input type="radio" name="radioBTN" value="${tempTest.id}">

            </fieldset>
        </c:forEach>
    </div>

    <input type="hidden" name="command" value="pass_test" id="command_id">
    <input type="submit" value="Пройти этот тест">
<%--    <input type="submit" value="назначить тест" onclick="document.getElementById('command_id').value ='go_to_appoint_test'">--%>
    <p/>


</form>

</body>
</html>
