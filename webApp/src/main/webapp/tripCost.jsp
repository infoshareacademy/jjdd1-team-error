<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>
<%@ page errorPage="exceptionHandling.jsp" %>


        <div class="data">
            <br>
            <label><b>Given data:</b></label>
            <li>Country:  ${country}</li>
            <li>Currency:  ${currency}</li>
            <li>Fuel type:  ${fuelType}</li>
            <li>Date of departure:  ${date1}</li>
            <li>Date of return:  ${date2}</li>
            <li>Fuel usage:  ${fuelUsage}</li>
            <li>Distance:  ${fullDistance}</li>
        </div>
        <div class="result" style="padding-bottom:120px;">
            <br>
            <h3>The cost of renting a car abroad (for the specified data) will be: ${fullCost}</h3>
        </div>


<%@ include file="footer.jsp" %>
