<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div>
        <img alt="Trip calculator" src="img/logo.png"/>
        <h2>${title}</h2>
    </div>

    <div>
        <form style="display: inline" method="get" action="/calc">
            <button type="submit" name="additionalData" value="">Trip cost</button>

            <button type="submit" name="trendy" value="">Optimal time for trip</button>

            <button type="submit" name="initialData" value="">Change initial data</button>
        </form>
    </div>

