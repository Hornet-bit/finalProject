
<%@ page contentType="text/html;charset=UTF-8" import="by.epamtc.birukov.entity.UserRegForm" language="java" %>
<html>
<head>
    <title>MAIN jsp</title>
</head>
<body>
<jsp:useBean id="user" class="by.epamtc.birukov.entity.UserRegForm" scope="request"/>
<h1>
    Hello <jsp:getProperty name="user" property="name"/>
</h1>
pass:  <jsp:getProperty name="user" property="password"/>


<i>
<jsp:getProperty name="user" property="age"/>
</i>
<%--<h2>--%>
<%--<jsp:getProperty name="user" property="name"/>--%>
<%--</h2>--%>
<form action="index.jsp" method="get">
    <input type="submit" value="на главную">
</form>
</body>
</html>