<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>
<%@ include file="car.jsp" %>


<title>Admin Login Page</title>

<form method="post" action="/admin" class="form-horizontal" id="admin_form1" style="padding-bottom:120px;">
    <div class="form-group">
        <label class="col-md-4 control-label">Input admin email</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="emailInput" class="form-control" type="email" value="${emailInput}">
                <span class="input-group-btn">
                    <button class="btn btn-secondary" type="submit" onclick="form.action='/admin';">Add admin</button>
                </span>
            </div>
        </div>
    </div>
</form>
<form method="post" action="/admin" class="form-horizontal" id="admin_form2" style="padding-bottom:120px;">
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
                    <button class="btn btn-secondary" type="submit" onclick="form.action='/admin';">Remove admin</button>
                </span>
            </div>
        </div>
    </div>
</form>
<form method="post" action="/admin" class="form-horizontal" id="admin_form3" style="padding-bottom:120px;">
    <div class="form-group row">
        <label class="col-md-4 control-label">Add a promoted country</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="countryInput" class="form-control" type="text" value="${countryInput}">
                <span class="input-group-btn">
                    <button class="btn btn-secondary" type="submit" onclick="form.action='/admin';">Add country</button>
                </span>
            </div>
        </div>
    </div>
</form>
<form method="post" action="/admin" class="form-horizontal" id="admin_form4" style="padding-bottom:120px;">
    <div class="form-group row">
        <label class="col-md-4 control-label">Remove a promoted country</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <select name="countryRemover" class="form-control selectpicker"  >
                    <option value="">Choose a country to remove</option>
                    <c:forEach items="${promotedCountryList}" var="onePromotedCountry">
                        <c:if test="${onePromotedCountry != selected}">
                            <option value="${onePromotedCountry}">${onePromotedCountry}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <span class="input-group-btn">
                    <button class="btn btn-secondary" type="submit" onclick="form.action='/admin';">Remove country</button>
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

<script type="text/javascript">
    $(document).ready(function() {
        $('#admin_form1').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                emailInput: {
                    validators: {
                        regexp: {
                            regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                            message: 'The value is not a valid email address'
                        },
                        notEmpty: {
                            message: 'Please input an email you wish to add'
                        }
                    }
                },
            }
        });

    });
    $(document).ready(function() {
        $('#admin_form2').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                emailRemover: {
                    validators: {
                        notEmpty: {
                            message: 'Please select an email to remove'
                        }
                    }
                }
            }
        });

    });
    $(document).ready(function() {
        $('#admin_form3').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                countryInput: {
                    validators: {
                        notEmpty: {
                            message: 'Please input a country you wish to add'
                        }
                    }
                }
            }
        });

    });
    $(document).ready(function() {
        $('#admin_form4').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                countryRemover: {
                    validators: {
                        notEmpty: {
                            message: 'Please select a country to remove'
                        }
                    }
                }
            }
        });
    });

</script>


<%@ include file="footer.jsp" %>
</body>
</html>


