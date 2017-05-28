<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>
<%@ include file="car.jsp" %>

<div class="data">
    <br>
    <ul class="list-group" style="width:500px;">
        <li class="list-group-item list-group-item-success text-center"><b>GIVEN DATA</b></li>
        <li class="list-group-item text-left">
            <span class="badge badge-success">${country}</span>Country:
        </li>
        <li class="list-group-item text-left">
            <span class="badge badge-success">${currency}</span>Currency:
        </li>
        <li class="list-group-item text-left">
            <span class="badge badge-success"><%= session.getAttribute("fuelTypeString") %></span>Fuel type:
        </li>
        <li class="list-group-item text-left">
            <span class="badge badge-success">${date1}</span>Departure date:
        </li>
        <li class="list-group-item text-left">
            <span class="badge badge-success">${date2}</span>Return date:
        </li>
        <li class="list-group-item text-left">
            <span class="badge badge-success">${fuelUsage}</span>Fuel usage:
        </li>
        <li class="list-group-item text-left">
            <span class="badge badge-success">${fullDistance}</span>Distance:
        </li>
    </ul>
    <br>
</div>

<div class="container" style="padding-bottom: 120px; margin: 0; margin:auto;">
    <ul class="list-group" style="width:500px; margin:auto">
        <li class="list-group-item text-left">
            <h3>The cost of renting a car abroad (for the specified data) will be:
                <span class="label label-success">${fullCost} PLN</span>
            </h3>
        </li>
    </ul>
    <br>
</div>

<%@ include file="footer.jsp" %>
