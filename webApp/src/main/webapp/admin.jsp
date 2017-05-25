<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>
<%@ include file="car.jsp" %>


<title>Admin Login Page</title>


<form method="post" action="/admin" class="form-horizontal" id="reg_form" style="padding-bottom:120px;">
    <div class="form-group">
        <label class="col-md-4 control-label">Input admin email</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="emailInput" class="form-control" type="text" value="${emailInput}">
                <span class="input-group-btn">
                    <button class="btn btn-secondary" type="submit" onclick="form.action='/admin';">Add Admin</button>
                </span>
            </div>
        </div>
    </div>
</form>
<form method="post" action="/admin" class="form-horizontal" id="reg_form" style="padding-bottom:120px;">
    <div class="form-group">
        <label class="col-md-4 control-label">Remove admin email</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <select name="emailRemover" class="form-control selectpicker"  >
                    <option value="">Choose an admin to remove</option>
                    <c:forEach items="${adminList}" var="oneEmail">
                        <c:if test="${oneEmail != selected}">
                            <option value="${oneEmail}">${oneEmail}</option>
                        </c:if>
                    </c:forEach>
                </select>

                <span class="input-group-btn">
                    <button class="btn btn-secondary" type="submit" onclick="form.action='/admin';">Remove Admin</button>
                </span>
            </div>
        </div>
    </div>
</form>

<script src="vendor/Chart.bundle.js"></script>
<script src="vendor/jquery-3.2.1.js"></script>
<script src="vendor/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script src="/vendor/js/bootstrap.js"></script>

<%@ include file="footer.jsp" %>
</body>
</html>


