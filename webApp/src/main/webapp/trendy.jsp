<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<%@ include file="headerOptions.jsp" %>

<div>
    <br>
    <li>Country: ${country}</li>
    <li>Currency:  ${currency}</li>
    <li>Fuel type:  ${fuelType}</li>
    <br>
    <p><pre> ${trendForTrip}</pre></p>
</div>

<%@ include file="footer.jsp" %>