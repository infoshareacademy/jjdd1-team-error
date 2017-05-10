<%--
  Created by IntelliJ IDEA.
  User: Krystian
  Date: 2017-04-23
  Time: 09:29
  To change this template use File | Settings | File Templates.
--%>

<div class="buttons">
    <form style="display: inline" method="post" action="/data">
        <input type="hidden" name="country" value="${country}" />
        <input type="hidden" name="currency" value="${currency}" />
        <input type="hidden" name="fuelType" value="${fuelType}" />
        <input type="hidden" name="tripLength" value="${tripLength}" />
        <input type="hidden" name="trendPeriodFrom" value="${trendPeriodFrom}" />
        <input type="hidden" name="trendPeriodTill" value="${trendPeriodTill}" />
        <input type="hidden" name="date1" value="${date1}" />
        <input type="hidden" name="date2" value="${date2}" />
        <input type="hidden" name="fuelUsage" value="${fuelUsage}" />
        <input type="hidden" name="fullDistance" value="${fullDistance}" />

    <%--<button type="submit" name="additionalData" value="">Trip cost</button>--%>
        <button type="submit" name="tripCost" value="">Trip cost</button>
        <button type="submit" name="trendy" value="">Optimal time for trip</button>
    </form>

    <form style="display: inline" method="post" action="/calc">
        <input type="hidden" name="country" value="${country}" />
        <input type="hidden" name="currency" value="${currency}" />
        <input type="hidden" name="fuelType" value="${fuelType}" />
        <input type="hidden" name="tripLength" value="${tripLength}" />
        <input type="hidden" name="trendPeriodFrom" value="${trendPeriodFrom}" />
        <input type="hidden" name="trendPeriodTill" value="${trendPeriodTill}" />
        <input type="hidden" name="startingDays" value="${startingDays}" />
        <input type="hidden" name="date1" value="${date1}" />
        <input type="hidden" name="date2" value="${date2}" />
        <input type="hidden" name="fuelUsage" value="${fuelUsage}" />
        <input type="hidden" name="fullDistance" value="${fullDistance}" />

        <button type="submit" name="initialData" value="">Reset data</button>
    </form>
</div>

