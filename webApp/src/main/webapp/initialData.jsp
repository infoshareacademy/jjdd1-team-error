<%@ include file="headersAndStyle.jsp" %>


<form method="post" action="/afterInitial" class="form-horizontal" id="reg_form" style="padding-bottom:120px;">

    <div class="form-group">
        <label class="col-md-4 control-label">Country</label>
        <div class="col-md-5 selectContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                <select name="country" class="form-control selectpicker"  >
                    <option value="" >Please select your destination</option>
                    <c:forEach items="${countryList}" var="oneCountry">
                        <c:choose>
                            <c:when test="${oneCountry == country}" >
                                <option value="${oneCountry}" selected="${oneCountry}">${oneCountry}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${oneCountry}" >${oneCountry}</option>
                            </c:otherwise>
                        </c:choose>
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
                <select name="fuelType" class="form-control selectpicker" value="${fuelType}">
                    <option value="" >Please select your fuel type</option>
                    <option value="1" <%= session.getAttribute("fuelTypeString") != null &&
                            session.getAttribute("fuelTypeString").equals("diesel")?"selected":"" %> >Diesel</option>
                    <option value="2" <%= session.getAttribute("fuelTypeString") != null &&
                            session.getAttribute("fuelTypeString").equals("gasoline")?"selected":"" %> >Gasoline</option>
                </select>
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Fuel Usage</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="fuelUsage" class="form-control" type="number" step="0.1" min="1" value="${fuelUsage}">
            </div>
        </div>
    </div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Distance Traveled</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="fullDistance" class="form-control" type="number" min="1" value="${fullDistance}">
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-4 control-label">Departure Date</label>
        <div class="col-md-5 inputGroupContainer">
            <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input type="text" id="date1" name="date1" class="form-control date-picker1" value="${date1}"/>
            </div>
        </div>
    </div>

    <!-- Text input-->
    <div class="form-group">
        <label class="col-md-4 control-label">Return Date</label>
        <div class="col-md-5 inputGroupContainer">
            <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input type="text" id="date2" name="date2" class="form-control date-picker2" value="${date2}"/>
            </div>
        </div>
    </div>



    <!-- Submit buttons -->
    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-2">
            <button type="submit" class="btn btn-warning" onclick="form.action='/trendy';" >Price Trends <span class="glyphicon glyphicon-send"></span></button>
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-danger" onclick="form.action='/tripCost';">Trip Cost <span class="glyphicon glyphicon-send"></span></button>
        </div>
    </div>

    <%--</fieldset>--%>
</form>


<%@ include file="footer.jsp" %>
