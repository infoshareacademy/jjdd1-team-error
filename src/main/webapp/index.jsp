<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<html>
<head>
    <title>Calculate</title>
</head>
<body>

<form method="get" action="/calculate">
    Country: <input type="text" name="country" value="${country}">
    <%--<input type="submit">--%>
<%--</form>--%>
<%--<form method="get" action="/calculate"> --%>
    Currency: <input type="text" name="currency" value="${currency}">
    <%--<input type="submit">--%>
<%--</form>--%>
<%--<form method="get" action="/calculate"> --%>
    Fuel Type: <input type="text" name="fuelType" value="${fuelType}"> <input type="submit">
</form>

<div>
    <%--<c:forEach items="${wordList}" var="w">--%>
        <%--<li> ${w}</li>--%>

        <li> ${country}</li>
        <li> ${currency}</li>
        <li> ${fuelType}</li>

    <%--</c:forEach>--%>
</div>

</body>
</html>