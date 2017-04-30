<%--
  Created by IntelliJ IDEA.
  User: igafalkowska
  Date: 28.04.17
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>You need to log in to start your calculation</h3>
<form action="/login" method="post">
    <div>Login: <input type="text" name="username"></div>
    <div>Password: <input type="password" name="password"></div>
    <input type="hidden" name="referrer" value="${param.url}">
    <div><input type="submit" value="Log in"></div>
</form>
</body>
</html>
