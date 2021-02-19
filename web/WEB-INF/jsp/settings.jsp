<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>settings</title>
    <c:import url="header.jsp"/>
</head>
<body>
<h1>Settings</h1>
<form action = "controller" method="post" enctype="multipart/form-data">

    <input type = "file" name = "file" size = "50" />
    <br />
    <input type = "submit" value = "Upload File" onclick="x()"/>

    <input type="hidden" name="command" value="upload_image">
</form>
<script>
    function x() {
        window.opener.location.href = "hello.jsp";
        window.close();
    }
</script>
<%--<c:out value="u email = ${user.email}"/>--%>

<%--<script>--%>
<%--    var input;--%>
<%--    var count = 0;--%>


<%--    function makeField(id_elem) {--%>

<%--        if (count == 0) {--%>

<%--            input = document.createElement('input');--%>
<%--            const butt = document.getElementById(id_elem);--%>
<%--            butt.insertAdjacentElement("afterend", input);--%>
<%--            count = 1;--%>


<%--        } else {--%>
<%--            delField(id_elem)--%>
<%--        }--%>

<%--    }--%>

<%--    function delField(id_elem) {--%>
<%--        document.body.removeChild(input);--%>
<%--        document.body.removeAttribute(id_elem);--%>
<%--        id_elem.remove();--%>
<%--        count = 0;--%>
<%--    }--%>
<%--</script>--%>

<%--<button id="change_email" onclick="makeField(id)">Change e-mail</button>--%>
<%--<button id="change_email" onclick="delField()">delete</button>--%>
<p/>

<c:out value="u username = ${sessionScope.login}"/>

<%--<button id="change_username" onclick="makeField(id)">Change username</button>--%>
<%--<button id="change_username" onclick="delField()">delete</button>--%>


<%--<p/>--%>
<%--<c:out value="u password = ${user.password}"/>--%>
<%--<p/>--%>
<%--<button id="change_password" onclick="makeField(id)">Change password</button>--%>
<%--<c:out value="u name = ${user.name}"/>--%>
<%--<p/>--%>
<%--<c:out value="u middle name = ${user.middleName}"/>--%>
<%--<p/>--%>
<%--<c:out value="u surname = ${user.surname}"/>--%>
<%--<c:out value="${user.role}" default="роль не определена"/>--%>

</body>
</html>
