
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>hider, не обращай внимания</h1>
<form>
    <input type="hidden" name="command" value="go_to_settings">
    <input type="submit" value="settings">
</form>

<c:if test="${user.role eq 'admin'}">
    <c:import url="admin_actions.jsp"/>
</c:if>
