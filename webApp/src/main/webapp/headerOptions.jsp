<form>
    <div class="btn-group btn-group-justified" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button id="first" class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post"
                    formaction="/tripCost">Trip cost
            </button>
        </div>
        <div class="btn-group" role="group">
            <button id="second" class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post"
                    formaction="/trendy">Optimal time
            </button>
        </div>
        <div class="btn-group" role="group">
            <button id="third" class="btn btn-outline-inverse btn-lg" type="submit" formmethod="get" formaction="/start"
                    name="initialData" value="">Change data
            </button>
        </div>
    </div>
</form>

<%--disabling buttons when main data has not been given--%>
<script>
    var country = '${country}';
    var currency = '${currency}';
    var fuelTypeString = '<%= session.getAttribute("fuelTypeString") %>';
    var date1 = '${date1}';
    var date2 = '${date2}';
    var fuelUsage = '${fuelUsage}';
    var fullDistance = '${fullDistance}';

    $('#first').prop('disabled', true);
    $('#second').prop('disabled', true);
    if (country != "" && currency != "" && fuelTypeString != "" && date1 != ""
        && date2 != "" && fuelUsage != "" && fullDistance != "") {
        $('#first').prop('disabled', false);
        $('#second').prop('disabled', false);
    }
</script>

