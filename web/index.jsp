<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome Page</title>
</head>

<body>

<%--<c:redirect url="WEB-INF/jsp/greeting.jsp"></c:redirect>--%>
<c:redirect url="controller?command=greeting"></c:redirect>

</body>
</html>
