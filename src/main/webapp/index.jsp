<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
    <head>
        <title>Trip calculator</title>
    </head>
    <body>
        <h1>TRIP CALCULATOR</h1>

        <form method="get" action="/usa">
            <div><label>Country:</label> <input type="text" name="country" value="${country}"></div>
            <div><label>Currency:</label> <input type="text" name="currency" value="${currency}"></div>
            <div><label>Kind of fuel:</label> <input type="text" name="kindOfFuel" value="${kindOfFuell}"></div>
            <div><input type="submit"></div>
        </form>

        <div>

            <li> ${country}</li>
            <li> ${currency}</li>
            <li> ${kindOfFuel}</li>

        </div>
</body>
</html>