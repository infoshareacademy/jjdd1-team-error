
<form>

    <div class="btn-group btn-group-justified" role="group" aria-label="...">
        <div class="btn-group" role="group">
            <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post" formaction="/tripCost">Trip cost</button>
        </div>
        <div class="btn-group" role="group">
            <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="post" formaction="/trendy" >Optimal time</button>
        </div>
        <div class="btn-group" role="group">
            <button class="btn btn-outline-inverse btn-lg" type="submit" formmethod="get" formaction="/start" name="initialData" value="" >Change data</button>
        </div>
        <div class="btn-group" role="group">
            <button class="btn btn-outline-inverse btn-lg" type="submit"
                    <c:forEach items="${adminList}" var="admin">
                        <c:if test="${userMail != admin}"><c:out value="disabled='disabled'"/></c:if>
                    </c:forEach>
                    formmethod="post" formaction="/admin" name="adminPage" value="">Admin</button>
        </div>
    </div>


</form>



