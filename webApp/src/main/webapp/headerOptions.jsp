
<form>
    <div class="btn-group btn-group-justified" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button id="first" class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post" formaction="/tripCost">Trip cost</button>
        </div>
        <div class="btn-group" role="group">
            <button id="second" class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post" formaction="/trendy" >Optimal time</button>
        </div>
        <div class="btn-group" role="group">
            <button id="third" class="btn btn-outline-inverse btn-lg" type="submit" formmethod="get" formaction="/start" name="initialData" value="" >Change data</button>
        </div>
    </div>
</form>

<%--disabling buttons when admin isn't logged in--%>
<script>
    <%--var data = ${fuelUsage};--%>
    $('first').disable();
    $('second').disabled();
    $('third').prop('disabled', true);
    //    if(data!=""){
    //        $('#first').enable();
    //        $('#second').enable();
    //        $('#third').enable();
    //    }
</script>

