<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="headersAndStyle.jsp" %>

<html>
    <body>
        <h1>Welcome to Trip Calculator</h1>
        <form method="get" action="/calc">
            <div>
                <button type="submit" name="start" value="">Start your calculation</button>
            </div>
        </form>
    </body>
</html>