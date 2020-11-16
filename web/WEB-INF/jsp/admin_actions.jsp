<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="go_to_create_test">
        <input type="submit" class="btn btn-outline-info" value="Создать тест">
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="create_subject">
        <input type="submit" class="btn btn-outline-info" value="Create subject">
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="show_all_tests">
        <input type="submit" class="btn btn-outline-info" value="Все тесты">
    </form>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="show_all_users">
        <input type="submit" class="btn btn-outline-info" value="Все пользователи">
    </form>
    <!--
<%--<form action="controller" method="get">--%>
<%--    <input type="hidden" name="command" value="appoint_test">--%>
<%--    <input type="submit" value="назначить тест">--%>
<%--</form>--%>
-->

</main>

