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
<h1>Предметы:</h1>

<c:set var="i" scope="page" value="0"></c:set>
<form action="controller" method="post">
    <c:forEach var="subj" items="${subjects}">
        <fieldset>
            <c:out value="${subj.name}"/>
<%--            <c:out value="${subj.description}"/>--%>
            <button value="${subj.name}" name="subj_name">детальнее</button>


            <input type="hidden" name="command" value="subj_details">
                <%--        <c:out value="${user.userId}"/>--%>
                <%--            ${i = i + 1}--%>

        </fieldset>
    </c:forEach>

</form>

</body>
</html>
