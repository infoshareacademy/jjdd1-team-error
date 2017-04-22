<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
    <head>
        <title>Initial data</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <img alt="Trip calculator" src="img/logo.png"/>
        <h2>Initial data</h2>

        <form method="get" action="/calc">
            <div><label>Country:</label> <input type="text" name="country"></div>
            <br>
            <div> <input type="radio" name="fuelType" value="1" checked>diesel</div>
            <div> <input type="radio" name="fuelType" value="2">gasoline</div>
            <br>
            <div><button type="submit" name="initialization" value="">Submit</button></div>
        </form>
</body>
</html>