<%@ include file="headersAndStyle.jsp" %>
<form method="get" action="/calc">

<script>
    $(".dropdown-menu li a").click(function(){
        var selText = $(this).text();
        $(this).closest('div').find('button[data-toggle="dropdown"]').html(selText + ' <span class="caret"></span>');
    });
</script>

<div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
        Destination
        <span class="caret"></span>
    </button>

        <ol class="dropdown-menu" aria-labelledby="dropdownMenu1">
            <c:forEach items="${countryList}" var="country">
                <li><a href="${country}">${country}</a></li>
            </c:forEach>
            <%--<li role="separator" class="divider"></li>--%>
            <%--<li><a href="#">Separated link</a></li>--%>
        </ol>
</div>
    <br>
    <select name="country" >
        <c:forEach items="${countryList}" var="country">
            <option value="${country}">${country}</option>
        </c:forEach>
    </select>
    <br>
    <div class="radio_input"> <input type="radio" name="fuelType" value="1" checked>diesel</div>
    <div class="radio_input"> <input type="radio" name="fuelType" value="2">gasoline</div>
    <br>
    <div class="buttons"><button type="submit" name="initialization" value="">Submit</button></div>
</form>


<%@ include file="footer.jsp" %>
