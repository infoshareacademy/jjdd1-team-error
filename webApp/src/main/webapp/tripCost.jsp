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
<%@ include file="car.jsp" %>
<%@ page errorPage="exceptionHandling.jsp" %>

        <div class="data">
            <ul class="list-group" style="min-width:300px;">
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
    <div class="container" style="padding-bottom: 100px;">
        <ul class="list-group" style="min-width:300px;">
            <li class="list-group-item text-left">
                <h2>The cost of renting a car abroad (for the specified data) will be:
                <span class="label label-success">${fullCost} PLN</span></h2>
            <%--<li class="list-group-item text-left">--%>
                <%--<h2>The cost of renting a car abroad (for the specified data) will be:--%>
                <%--<span class="badge badge-success">${fullCost} PLN</span></h2>--%>
            </li>
        </ul>
        <br>
    </div>
        <%--<div class="result" style="padding-bottom:120px;">--%>
            <%--<br>--%>
            <%--<h3>The cost of renting a car abroad (for the specified data) will be: ${fullCost} PLN</h3>--%>
        <%--</div>--%>


<%@ include file="footer.jsp" %>
