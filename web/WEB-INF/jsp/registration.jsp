<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller" name="regForm" method="get">
    <input type="hidden" name="command" value="create_new_user">
    <p>
        <input type="text" name="username" autocomplete="off" placeholder="name" required>
    </p>
    <p>
        <input type="password" name="password" autocomplete="off" placeholder="password" required>
    </p>
    <input type="submit" value="Registration">
</form>

</body>
</html>
