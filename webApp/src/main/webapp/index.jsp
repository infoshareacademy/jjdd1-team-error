<%@ include file="headersAndStyle.jsp" %>
<div class="row">
<div class="col-md-3">
    <img src="img/car.png" alt="car" style="width:80px; height:80px;">
</div>
<div class="col-md-6">
    <h1>Welcome to Trip Calculator!</h1>
    <form method="get" action="/calc">
        <div class="buttons">
                <button type="submit" name="start" value="" class="btn btn-primary btn-lg">Start your calculation</button>
        </div>
    </form>
</div>
<div class="col-md-3" >
    <img src="img/car.png" alt="car" style="width:80px; height:80px;">
</div>
</div>
<%--<div style="clear: both;"></div>--%>
<%@ include file="footer.jsp" %>