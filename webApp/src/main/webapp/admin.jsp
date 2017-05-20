<%@ include file="headersAndStyle.jsp" %>

<title>Admin Login Page</title>


<form method="post" action="/admin" class="form-horizontal" id="reg_form" style="padding-bottom:120px;">

    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-2">
            <button type="submit" class="btn btn-warning" onclick="form.action='/trendy';" >Price Trends <span class="glyphicon glyphicon-send"></span></button>
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-danger" onclick="form.action='/tripCost';">Trip Cost <span class="glyphicon glyphicon-send"></span></button>
        </div>
    </div>

</form>

<%@ include file="footer.jsp" %>


