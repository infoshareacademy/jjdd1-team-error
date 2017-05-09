<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="headersAndStyle.jsp" %>
<%@ include file="headerOptions.jsp" %>
<%@ page errorPage="exceptionHandling.jsp" %>

    <%--<div class="col-md-9">--%>

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
        <div class="result">
            <br>
            <h3>The cost of renting a car abroad (for the specified data) will be: ${fullCost}</h3>
        </div>
    <%--</div>--%>
<%--</div>--%>



<%@ include file="footer.jsp" %>
