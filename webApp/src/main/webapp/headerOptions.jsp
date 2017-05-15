
<form>
    <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post" formaction="/tripCost">Trip cost</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post" formaction="/trendy" >Optimal time for trip</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="get" formaction="/start" name="initialData" value="" >Change initial data</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="get" formaction="/report" name="countryAndCurrencyReport" value="">Country and currency report</button>
            </div>
            <div class="btn-group" role="group">
                <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="get" formaction="/report" name="fuelTypeReport" value="">Fuel type report</button>
            </div>
    </div>
</form>



