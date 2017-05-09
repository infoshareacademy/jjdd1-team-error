<%--
  Created by IntelliJ IDEA.
  User: Krystian
  Date: 2017-04-23
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>

<div class="buttons">
    <form style="display: inline" method="get" action="/calc">

            <%--<button type="submit" name="additionalData" value="">Trip cost</button>--%>
            <button type="submit" name="tripCost" value="">Trip cost</button>

            <button type="submit" name="trendy" value="">Optimal time for trip</button>
    </form>

    <form style="display: inline" method="get" action="/start">
        <button type="submit" name="initialData" value="">Change initial data</button>

    </form>
</div>


