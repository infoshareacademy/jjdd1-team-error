<%@ include file="headersAndStyle.jsp" %>
<form method="get" action="/calc" class="form-horizontal" id="reg_form">

    <!-- Select Basic -->

    <div class="form-group">
        <label class="col-md-4 control-label">Country</label>
        <div class="col-md-6 selectContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                <select name="country" class="form-control selectpicker" >
                    <option value=" " >Please select your destination</option>
                    <c:forEach items="${countryList}" var="country">
                        <option value="${country}">${country}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>

    <!-- Select Basic -->

    <div class="form-group">
        <label class="col-md-4 control-label">Fuel Type</label>
        <div class="col-md-6 selectContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                <select name="fuelType" class="form-control selectpicker" >
                    <option value=" " >Please select your destination</option>
                    <option value="diesel">diesel</option>
                    <option value="gasoline">gasoline</option>
                </select>
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Fuel Usage</label>
        <div class="col-md-6  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="fuelUsage" placeholder="6.9" class="form-control" type="number" step="0.01">
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Distance Traveled</label>
        <div class="col-md-6  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="fullDistance" placeholder="666" class="form-control" type="number" min=1>
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Departure Date</label>
        <div class="col-md-6  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input  id="date1" type="text" placeholder="yyyy-mm-dd" class="form-control">
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label" >Return Date</label>
        <div class="col-md-6  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input id="date2" type="text" placeholder="yyyy-mm-dd" class="form-control">
            </div>
        </div>
    </div>

    <%--</fieldset>--%>
    <%--<legend> Account information </legend>--%>
    <%--<fieldset>--%>
        <%--<!-- Text input-->--%>
        <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label">E-Mail</label>--%>
            <%--<div class="col-md-6  inputGroupContainer">--%>
                <%--<div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>--%>
                    <%--<input name="email" placeholder="E-Mail Address" class="form-control"  type="text">--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>


        <%--<div class="form-group has-feedback">--%>
            <%--<label for="password"  class="col-md-4 control-label">--%>
                <%--Password--%>
            <%--</label>--%>
            <%--<div class="col-md-6  inputGroupContainer">--%>
                <%--<div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>--%>
                    <%--<input class="form-control" id="userPw" type="password" placeholder="password"--%>
                           <%--name="password" data-minLength="5"--%>
                           <%--data-error="some error"--%>
                           <%--required/>--%>
                    <%--<span class="glyphicon form-control-feedback"></span>--%>
                    <%--<span class="help-block with-errors"></span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="form-group has-feedback">--%>
            <%--<label for="confirmPassword"  class="col-md-4 control-label">--%>
                <%--Confirm Password--%>
            <%--</label>--%>
            <%--<div class="col-md-6  inputGroupContainer">--%>
                <%--<div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>--%>
                    <%--<input class="form-control {$borderColor}" id="userPw2" type="password" placeholder="Confirm password"--%>
                           <%--name="confirmPassword" data-match="#confirmPassword" data-minLength="5"--%>
                           <%--data-match-error="some error 2"--%>
                           <%--required/>--%>
                    <%--<span class="glyphicon form-control-feedback"></span>--%>
                    <%--<span class="help-block with-errors"></span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>


        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <button type="submit" class="btn btn-warning" >Send <span class="glyphicon glyphicon-send"></span></button>
            </div>
        </div>

    <%--</fieldset>--%>


    <div class="form-group">
        <label class="col-xs-3 control-label">Country</label>
        <div class="col-xs-5 selectContainer">
            <select class="form-control" name="country" multiple title="Choose 2-4 colors">
                <c:forEach items="${countryList}" var="country">
                    <option value="${country}">${country}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Language</label>
        <div class="col-xs-5 selectContainer">
            <select class="form-control" name="fuelType" >
                <option value="1">diesel</option>
                <option value="2">gasoline</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-5 col-xs-offset-3">
            <button type="submit" class="btn btn-default" name="initialization" value="">Submit</button>
        </div>
    </div>


    <%--<br>--%>
    <%--<div class="form-group" data-live-search="true">Country--%>
        <%--<select class="form-control" name="country" >--%>
            <%--<c:forEach items="${countryList}" var="country">--%>
                <%--<option value="${country}">${country}</option>--%>
            <%--</c:forEach>--%>
        <%--</select>--%>
    <%--</div>--%>

    <%--<br>--%>

    <%--<div class="form-group">--%>
        <%--<select class="form-control" name="fuelType" >--%>
            <%--<option value="1">diesel</option>--%>
            <%--<option value="2">gasoline</option>--%>
        <%--</select>--%>
    <%--</div>--%>

    <%--&lt;%&ndash;<div class="radio_input"> <input type="radio" name="fuelType" value="1" checked>diesel</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<div class="radio_input"> <input type="radio" name="fuelType" value="2">gasoline</div>&ndash;%&gt;--%>
    <%--<br>--%>
    <%--<div class="buttons"><button type="submit" name="initialization" value="">Submit</button></div>--%>
</form>


<%@ include file="footer.jsp" %>
