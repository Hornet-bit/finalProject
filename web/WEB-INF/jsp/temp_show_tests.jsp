<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="en">

<head>

    <c:import url="header.jsp"/>
    <c:set var="page" scope="session" value="temp_show_tests.jsp"/>


    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="/localization.MessagesBundle" var="loc"/>

    <fmt:message bundle="${loc}" key="show.tests.title" var="title"/>
    <fmt:message bundle="${loc}" key="show.tests.name" var="test_name"/>
    <fmt:message bundle="${loc}" key="show.tests.description" var="test_description"/>
    <fmt:message bundle="${loc}" key="show.tests.button.show" var="show_test"/>
    <fmt:message bundle="${loc}" key="show.tests.button.appoint" var="appoint_test"/>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>All tests</title>
</head>
<body class="text-center">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<main>
    <h1>${title}</h1>
    <form action="controller" method="get">

        <table class="table">
            <thead>
            <tr>
                <th scope="col">${test_name}</th>
                <th scope="col">${test_description}</th>
                <th scope="col">#</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tempTest" items="${all_tests}">
                <fieldset>
                    <tr>
                        <td>${tempTest.name}</td>
                        <td>${tempTest.description}</td>
                        <td><input type="radio" name="radioBTN" value="${tempTest.id}"></td>
                    </tr>
                </fieldset>
            </c:forEach>
            </tbody>
        </table>

        <input type="hidden" name="command" value="show_test_by_name" id="command_id">
        <input type="submit" class="btn btn-outline-info" value="${show_test}">
        <input type="submit" class="btn btn-outline-info" value="${appoint_test}" onclick="document.getElementById('command_id').value ='go_to_appoint_test'">


        <!--
<%--    <c:forEach var="tempTest" items="${li}">--%>
<%--        &lt;%&ndash;    <c:out value="${tempTest.id}"></c:out>&ndash;%&gt;--%>
<%--        <c:out value="${tempTest.name}">--%>

<%--        </c:out>--%>

<%--    </c:forEach>--%>
-->

    </form>
</main>
</body>

</html>