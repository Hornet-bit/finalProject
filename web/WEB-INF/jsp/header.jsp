
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>hider, не обращай внимания</h1>
<form>
    <input type="hidden" name="command" value="go_to_settings">
    <input type="submit" value="settings">
</form>

<form action="controller" method="post">

    <input type="hidden" name="command" value="logout">
    <input type="submit" value="logout">

</form>
