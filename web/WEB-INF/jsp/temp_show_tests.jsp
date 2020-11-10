<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 10.11.2020
  Time: 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Все тесты</h1>

<form action="controller" method="get">
    <div>
        <c:forEach var="tempTest" items="${all_tests}">

            <fieldset>
                <c:out value="${tempTest.name}"></c:out>

                <c:out value="${tempTest.description}"></c:out>
                <input type="radio" name="radioBTN" value="${tempTest.id}">

            </fieldset>
        </c:forEach>
    </div>
    <input type="hidden" name="command" value="show_test_by_name">
    <input type="submit" value="показать этот тест">
    <p/>
<c:forEach var="tempTest" items="${li}">
<%--    <c:out value="${tempTest.id}"></c:out>--%>
    <c:out value="${tempTest.name}"></c:out>

</c:forEach>

</form>
</body>
</html>
