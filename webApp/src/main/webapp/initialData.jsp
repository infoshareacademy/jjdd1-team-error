<%@ include file="headersAndStyle.jsp" %>

<form method="get" action="/calc">
    <select name="country" >
        <c:forEach items="${countriesAndCurrencies}" var="country">
            <option value="${country.key}">${country.key}</option>
        </c:forEach>
    </select>
    <br>
    <div class="radio_input"> <input type="radio" name="fuelType" value="1" checked>diesel</div>
    <div class="radio_input"> <input type="radio" name="fuelType" value="2">gasoline</div>
    <br>
    <div class="buttons"><button type="submit" name="initialization" value="">Submit</button></div>
</form>

<%@ include file="footer.jsp" %>
