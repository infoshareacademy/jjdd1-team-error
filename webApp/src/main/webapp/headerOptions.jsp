<%--
  Created by IntelliJ IDEA.
  User: Krystian
  Date: 2017-04-23
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>

<%--<div class="row">--%>
    <%--<div class="col-md-3" style="padding-top: 20px">--%>

<div class="btn-group btn-group-justified" role="group" aria-label="...">
    <form method="get" action="/calc">
        <%--<button type="submit" name="additionalData" value="">Trip cost</button>--%>

            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" name="tripCost" value="">Trip cost</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" name="trendy" value="">Optimal time for trip</button>
            </div>
    </form>

    <form method="get" action="/start">
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" name="initialData" value="">Change initial data</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" name="initialData" value="">Country and currency raport</button>
            </div>
        <%--</div>--%>
    </form>
</div>
<%--</div>--%>



