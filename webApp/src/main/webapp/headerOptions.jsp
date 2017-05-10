<%--
  Created by IntelliJ IDEA.
  User: Krystian
  Date: 2017-04-23
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>

<form style="display: inline" method="post" action="/calc">
    <div class="buttons">
        <%--<button type="submit" name="additionalData" value="">Trip cost</button>--%>
        <button type="button" class="btn btn-info" value="Trip Button" href = '/tripCost'>Trip cost</button>

        <button type="button" class="btn btn-info" value="Trendy Button" href = '/trends'>Optimal time for trip</button>

        <button type="button" class="btn btn-info" value="Clear Button" href = '/data'>Change initial data</button>
    </div>
</form>


