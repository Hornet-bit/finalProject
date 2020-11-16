<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%--<fmt:setLocale value="${sessionScope.local}"/>--%>
<%--<fmt:setBundle basename="/localization.MessagesBundle" var="loc"/>--%>
<%--<fmt:message bundle="${loc}" key="local.locbutton.name.homepage" var="homepage"/>--%>
<%--<fmt:message bundle="${loc}" key="local.locbutton.name.settings" var="settings"/>--%>
<%--<fmt:message bundle="${loc}" key="local.locbutton.name.logout" var="logout"/>--%>

<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <!--           TODO поправить отображение юзера в навбаре-->
    <!--           <%--<c:redirect url="hello.jsp"/>--%>-->
    <c:out value="${user.username}" default="ничего"/>
    <c:out value="${user.userRole}"/>
    <div class="navbar-nav">
        <form class="form-inline mr-2">
            <input type="hidden" name="command" value="go_to_hallo_page">
            <input type="submit" class="btn btn-outline-info" value="На главную">
        </form>
        <form class="form-inline mr-2">
            <input type="hidden" name="command" value="go_to_settings">
            <input type="submit" class="btn btn-outline-info" value="Settings">
        </form>
        <form class="form-inline" action="controller" method="post">
            <input type="hidden" name="command" value="logout">
            <input type="submit" class="btn btn-outline-info" value="LogOut">
        </form>
    </div>
</nav>