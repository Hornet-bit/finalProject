<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="/localization.MessagesBundle" var="loc"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.homepage" var="homepage"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.settings" var="settings"/>
<fmt:message bundle="${loc}" key="local.locbutton.name.logout" var="logout"/>
<fmt:message bundle="${loc}" key="header.button.ru" var="ru"/>
<fmt:message bundle="${loc}" key="header.button.en" var="en"/>

<%--<img src="file:///C:/Users/hp/Desktop/EpamProj/TestSystemFinish/web/img/1.jpg" alt="picture"/>--%>


   <nav class="navbar navbar-expand-lg navbar-light bg-dark">
       <img src="img/1.jpg" width="50" height="50" alt="pict"/>
    <!--           TODO поправить отображение юзера в навбаре-->
    <!--           <%--<c:redirect url="hello.jsp"/>--%>-->
    <c:out value="${user.username}" default="ничего"/>
    <c:out value="${user.userRole}"/>
    <div class="navbar-nav">
        <form class="form-inline mr-2">
            <input type="hidden" name="command" value="go_to_hallo_page">
            <input type="submit" class="btn btn-outline-info" value="${homepage}">
        </form>
        <form class="form-inline mr-2">
            <input type="hidden" name="command" value="go_to_settings">
            <input type="submit" class="btn btn-outline-info" value="${settings}">
        </form>
        <form class="form-inline" action="controller" method="post">
            <input type="hidden" name="command" value="logout">
            <input type="submit" class="btn btn-outline-info" value="${logout}">
        </form>
        <form>
            <input type="hidden" name="command" value="change_language" >
            <input type="hidden" name="local" value="en" >
            <input type="submit" class="btn btn-outline-info" value="${en}">
        </form>
        <form>
            <input type="hidden" name="command" value="change_language" >
            <input type="hidden" name="local" value="ru" >
            <input type="submit" class="btn btn-outline-info" value="${ru}">
        </form>
    </div>
</nav>