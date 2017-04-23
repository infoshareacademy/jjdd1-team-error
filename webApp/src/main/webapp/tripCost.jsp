<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%@ include file="headersAndStyle.jsp" %>
<%@ include file="headerOptions.jsp" %>
<%@ page errorPage="exceptionHandling.jsp" %>

<div>
    <br>
    <li>Country:  ${country}</li>
    <li>Currency:  ${currency}</li>
    <li>Fuel type:  ${fuelType}</li>
    <li>Date of departure:  ${date1}</li>
    <li>Date of return:  ${date2}</li>
    <li>Fuel usage:  ${fuelUsage}</li>
    <li>Distance:  ${fullDistance}</li>
    <br>
    <h3>The cost of renting a car abroad (for the specified data) will be: ${fullCost}</h3>
</div>
<%@ include file="footer.jsp" %>
