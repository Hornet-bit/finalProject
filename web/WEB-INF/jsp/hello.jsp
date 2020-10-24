<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<h1>Приветствую тебя, странник</h1>
<c:out value="${user.username}" default="ничего"/>
<p/>
<c:out value="${user.role}" default="роль не определена"/>

<form>
    <input type="hidden" name="command" value="go_to_settings">

    <input type="submit" value="settings">
</form>

<c:if test="${user.role eq 'admin'}">
    <c:import url="admin_actions.jsp"/>
</c:if>
</body>
</html>
