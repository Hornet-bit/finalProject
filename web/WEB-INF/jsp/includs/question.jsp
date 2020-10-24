<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:out value="${count}"/>
<h1>Заполнение теста</h1>
<c:forEach var="i" begin="1" end="${count}">

    <c:out value="${i}"/>
    <c:import url="test_inc.jsp"/>
    <p/>
</c:forEach>
</body>
</html>
