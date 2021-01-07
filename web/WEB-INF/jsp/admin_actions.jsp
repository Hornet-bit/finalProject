<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="/localization.MessagesBundle" var="loc"/>

<fmt:message bundle="${loc}" key="admin.button.create.subject" var="create_subject"/>
<fmt:message bundle="${loc}" key="admin.button.create.test" var="create_test"/>
<fmt:message bundle="${loc}" key="admin.button.look.all.tests" var="look_all_tests"/>
<fmt:message bundle="${loc}" key="admin.button.look.all.users" var="look_all_users"/>
<main>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="go_to_create_test">
        <input type="submit" class="btn btn-outline-info" value="${create_test}">
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="go_to_create_subject">
        <input type="submit" class="btn btn-outline-info" value="${create_subject}">
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="show_all_tests">
        <input type="submit" class="btn btn-outline-info" value="${look_all_tests}">
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="show_all_users">
        <input type="submit" class="btn btn-outline-info" value="${look_all_users}">
    </form>
    <!--
<%--<form action="controller" method="get">--%>
<%--    <input type="hidden" name="command" value="appoint_test">--%>
<%--    <input type="submit" value="назначить тест">--%>
<%--</form>--%>
-->

</main>

