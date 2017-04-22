<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>Trip calculator</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>TRIP CALCULATOR</h1>
<h2>Additional data</h2>

<form method="get" action="/calc">
    <div><label>Enter a date of departure in the format YYYYMMDD:</label> <input type="text" name="date1"></div>
    <div><label>Enter a date of return in the format YYYYMMDD:</label> <input type="text" name="date2"></div>
    <div><label>Enter fuel usage in liters per 100 km:</label> <input type="text" name="fuelUsage"></div>
    <div><label>Enter the full distance (counted in km) you want to travel during your trip:</label> <input type="text" name="fullDistance"></div>
    <div><button type="submit" name="tripCost" value="">Submit</button></div>
</form>
</body>
</html>
