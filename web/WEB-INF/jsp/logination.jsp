<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>logination</title>
</head>
<body>
<form>
    <input type="hidden" name="command" value="authorization">
    <p>
        <input type="text" name="username" autocomplete="off" placeholder="name" required>
    </p>

    <p>
        <input type="password" name="password" autocomplete="off" placeholder="password" required>
    </p>
    <input type="submit" value="authorization">
</form>
</body>
</html>
