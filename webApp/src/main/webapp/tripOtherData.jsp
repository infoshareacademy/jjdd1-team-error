<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page errorPage="exceptionHandling.jsp" %>
<%@ include file="headersAndStyle.jsp" %>

    <h2>Additional data</h2>
    <form method="post" action="/calc">
        <div class="text_input">
            <span><label>Enter a date of departure in the format YYYYMMDD:</label> <input type="text" name="date1"></span>
            <br>
            <span><label>Enter a date of return in the format YYYYMMDD:</label> <input type="text" name="date2"></span>
            <br>
            <span><label>Enter fuel usage in liters per 100 km:</label> <input type="text" name="fuelUsage"></span>
            <br>
            <span><label>Enter the full distance (counted in km) you want to travel during your trip:</label> <input type="text" name="fullDistance"></span>
            <span class="buttons"><button type="submit" name="tripCost" value="">Submit</button></span>
        </div>

    </form>
<%@ include file="footer.jsp" %>
