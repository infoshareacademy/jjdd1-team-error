<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trip calculator</title>
    <link href="https://fonts.googleapis.com/css?family=Bree+Serif|Gloria+Hallelujah|Indie+Flower|Pacifico|Shadows+Into+Light" rel="stylesheet">
    <link rel="stylesheet" href="vendor/css/bootstrap.css">
    <link rel="stylesheet" href="vendor/css/bootstrap-theme.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="vendor/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="vendor/bootstrap-3.3.7-dist/css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="vendor/bootstrap-select-1.12.2/js/bootstrap-select.js"/>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css" />
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.bundle.js" />

    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>

    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="447589672882-lon09s9eq542cpusfm4njbkjcuhpgif7.apps.googleusercontent.com">
    <jsp:include page="sessionChecker.jsp" />
</head>

<body>
<div id="header" style="max-height: 60px;">
    <div id="logo">
        <p>Trip Calculator</p>
    </div>
</div>
<%@ include file="headerOptions.jsp" %>

<title>Admin Login Page</title>


<form method="post" action="/admin" class="form-horizontal" id="reg_form" style="padding-bottom:120px;">

    <%--<div class="form-group">--%>
        <%--<label class="col-md-4 control-label"></label>--%>
        <%--<div class="col-md-2">--%>
            <%--<button type="submit" class="btn btn-warning" onclick="form.action='/trendy';" >Price Trends <span class="glyphicon glyphicon-send"></span></button>--%>
        <%--</div>--%>
        <%--<div class="col-md-2">--%>
            <%--<button type="submit" class="btn btn-danger" onclick="form.action='/tripCost';">Trip Cost <span class="glyphicon glyphicon-send"></span></button>--%>
        <%--</div>--%>
    <%--</div>--%>

</form>



<script src="vendor/Chart.bundle.js"></script>
<script src="vendor/jquery-3.2.1.js"></script>
<script src="vendor/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>




<div style="position: absolute; bottom:100px;">
    <form>
        <div class="btn-group btn-group-justified" role="group" aria-label="..."
                <c:forEach items="${adminList}" var="admin">
                    <c:if test="${userMail != admin}"><c:out value="hidden='hidden'"/></c:if>
                </c:forEach>

        >
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="get" formaction="/report" name="countryAndCurrencyReport" value="">Country / currency report</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="get" formaction="/report" name="fuelTypeReport" value="">Fuel report
                </button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="get" formaction="/report" name="fuelTypeReport" value="">Change files
                </button>
            </div>
        </div>
    </form>
</div>

<div id="footer">
    <div class="car">
        <img  id="car-mirror" src="img/moving-car.png" alt="car">
    </div>
    <div id="footercontent" >infoShare Academy, Team ERROR</div>
</div>

<script src="/vendor/js/bootstrap.js"></script>
</body>
</html>


