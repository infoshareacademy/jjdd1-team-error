<%@ include file="headersAndStyle.jsp" %>
<div class="row" style="margin:0; padding-bottom:120px; padding-top:50px;">
<div class="col-md-4" style="padding:0;">
    <img class="photo" src="img/photo2.jpg" alt="car">
</div>
<div class="col-md-4" style="padding:0;">
    <h1>Welcome to Trip Calculator!</h1>
    <form method="get" action="/calc">
        <div class="buttons">
                <button type="submit" name="start" value="" class="btn btn-primary btn-lg">Start your calculation</button>
        </div>
    </form>
</div>
<div class="col-md-4" style="padding:0;" >
    <img class="photo" src="img/photo1.jpg" alt="car">
</div>
</div>
<div style="clear:both;"></div>
<%@ include file="footer.jsp" %>