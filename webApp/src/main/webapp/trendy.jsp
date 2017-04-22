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
<h2>Optimal time for a trip</h2>

<form style="display: inline" method="get" action="/calc">
    <button type="submit" name="additionalData" value="">Trip cost</button>

    <button type="submit" name="trendy" value="">Optimal time for trip</button>

    <button type="submit" name="initialData" value="">Change initial data</button>
</form>

<div>
    <br>
    <li>Country: ${country}</li>
    <li>Currency:  ${currency}</li>
    <li>Fuel type:  ${fuelType}</li>
    <br>
    <p><pre> ${trendForTrip}</pre></p>

</div>
</body>
</html>