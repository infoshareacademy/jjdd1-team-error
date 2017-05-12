<%--
  Created by IntelliJ IDEA.
  User: Krystian
  Date: 2017-04-23
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>

<div class="buttons">
    <form style="display: inline" method="post" action="/tripCost">

        <button type="submit" onclick="form.action='/tripCost';">Trip cost</button>

        <button type="submit" onclick="form.action='/trendy';">Optimal time for trip</button>
    </form>

    <form style="display: inline" method="get" action="/start">

        <button type="submit" name="initialData" value="">Change initial data</button>
    </form>
</div>


