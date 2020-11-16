
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>result test</title>
    <c:import url="header.jsp"/>
</head>
<body>
    <c:forEach var="answer" items="${answers}">

        <c:out value="${answer.getTextQuestion()}"/>
        <c:out value="${answer.isRightAnswer()}"/>
        <p/>
    </c:forEach>

<c:out value="${mark}"/>

</body>
</html>
