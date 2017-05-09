<%@ include file="headersAndStyle.jsp" %>
<form method="get" action="/calc" class="form-horizontal" id="reg_form">

    <!-- Select Basic -->

    <%--<div class="form-group">--%>
        <%--<label class="col-md-4 control-label">Input File</label>--%>
        <%--<div class="col-md-6 selectContainer">--%>
            <%--<input type="file" class="form-control" name="uploadfile" />--%>
            <%--<asp:Label ID="Label3" runat="server" Text="Navigate to the file you wish to upload" CssClass="label_under_text"></asp:Label>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="form-group">
        <label class="col-md-4 control-label">Country</label>
        <div class="col-md-5 selectContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                <select name="country" class="form-control selectpicker"  >
                    <option value="" >Please select your destination</option>
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
        <div class="col-md-5 selectContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                <select name="fuelType" class="form-control selectpicker" >
                    <option value="" >Please select your fuel type</option>
                    <option value="1">Diesel</option>
                    <option value="2">Gasoline</option>
                </select>
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Fuel Usage</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="fuelUsage" placeholder="6.9" class="form-control" type="number" step="0.1" min="1" >
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Distance Traveled</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="fullDistance" placeholder="666" class="form-control" type="number" min="1">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-4 control-label">Departure Date</label>
        <div class="col-md-5 inputGroupContainer">
            <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input type="text" id="date1" name="date1" class="form-control date-picker1" />
            </div>
        </div>
    </div>

    <!-- Text input-->
    <div class="form-group">
        <label class="col-md-4 control-label">Return Date</label>
        <div class="col-md-5 inputGroupContainer">
            <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input type="text" id="date2" name="date2" class="form-control date-picker2" />
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

        <!-- Submit buttons -->
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-2">
                <button type="submit" class="btn btn-warning" name="trendy" value="">Price Trends <span class="glyphicon glyphicon-send"></span></button>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-danger" name="tripCost" value="">Trip Cost <span class="glyphicon glyphicon-send"></span></button>
            </div>
        </div>

    <%--</fieldset>--%>
</form>


<%@ include file="footer.jsp" %>
