<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<%@ include file="headerOptions.jsp" %>

<form method="post" action="/calc" class="form-horizontal" id="trendy_form">
<div class="data">
    <br>
    <label><b>Given data:</b></label>
    <li>Country: ${country}</li>
    <li>Currency:  ${currency}</li>
    <li>Fuel type:  ${fuelType}</li>
</div>
<div class="result">
    <br>
    <table id="trendy_table">
        <tr>
            <th>Month</th>
            <c:forEach items="${currencyTrendy}" var="monthValue">
                <td>${monthValue.key}</td>
            </c:forEach>
        </tr>
        <tr>
            <th>Currency deviations &#91;&#37;&#93;</th>
            <c:forEach items="${currencyTrendy}" var="monthValue">
                <td>${monthValue.value}</td>
            </c:forEach>
        </tr>
        <tr>
            <th>Petrol deviations &#91;&#37;&#93;</th>
            <c:forEach items="${petrolTrendy}" var="monthValue">
                <td>${monthValue.value}</td>
            </c:forEach>
        </tr>
    </table>
</div>

<h3>${conclusion}</h3>

<%@ include file="footer.jsp" %>