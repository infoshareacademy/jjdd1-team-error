<%--
  Created by IntelliJ IDEA.
  User: Krystian
  Date: 2017-04-23
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>

<%--<div class="row">--%>
    <%--<div class="col-md-3" style="padding-top: 20px">--%>
<form method="get" >
    <div class="btn-group btn-group-justified" role="group" aria-label="...">

        <%--<button type="submit" name="additionalData" value="">Trip cost</button>--%>

            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/tripCost" name="tripCost" value="/tripCost" >Trip cost</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/trendy" name="trendy" value="/trendy" >Optimal time for trip</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/start" name="initialData" value="/start" >Change initial data</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/report" name="countryAndCurrencyReport" value="/report">Country and currency report</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/report" name="fuelTypeReport" value="/report">Fuel type report</button>
            </div>
        <%--</div>--%>

    </div>
</form>
<%--</div>--%>



