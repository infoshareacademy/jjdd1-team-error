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
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/calc" name="tripCost" value="/calc" >Trip cost</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/calc" name="trendy" value="/calc" >Optimal time for trip</button>
            </div>

            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/start" name="initialData" value="/start" >Change initial data</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formaction="/countryAndCurrency.jsp" name="initialData" value="">Country and currency raport</button>
            </div>
        <%--</div>--%>

    </div>
</form>
<%--</div>--%>



