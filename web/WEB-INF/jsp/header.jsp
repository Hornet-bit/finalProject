
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>header, не обращай внимания</h1>
<c:out value="${user.username}" default="ничего"/>
<%--<c:redirect url="hello.jsp"/>--%>

<form>
    <input type="hidden" name="command" value="go_to_hallo_page">
    <input type="submit" value="На главную">
</form>

<form>
    <input type="hidden" name="command" value="go_to_settings">
    <input type="submit" value="settings">
</form>

<form action="controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">

</form>
