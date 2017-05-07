<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<%@ include file="headerOptions.jsp" %>

<form method="post" action="/calc" class="form-horizontal" id="trendy_form">
<div class="data">
    <br>
    <label><b>Given data:</b></label>
    <li>Country: ${country}</li>
    <li>Currency:  ${currency}</li>
    <li>Fuel type:  ${fuelType}</li>
    <br>
</div>


<div>
    <form method="post" action="/calc" class="form-horizontal" id="reg_form">

        <!-- Select Basic -->

        <%--<div class="form-group">--%>
            <%--<label class="col-md-4 control-label">Fuel Type</label>--%>
            <%--<div class="col-md-5 selectContainer">--%>
                <%--<div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>--%>
                    <%--<select name="fuelType" class="form-control selectpicker" >--%>
                        <%--<option value="" >Please select your fuel type</option>--%>
                        <%--<option value="1">Diesel</option>--%>
                        <%--<option value="2">Gasoline</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <!-- Text input-->


        <!-- Text input-->

        <div class="form-group">
            <label class="col-md-4 control-label">Distance Traveled</label>
            <div class="col-md-5  inputGroupContainer">
                <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                    <input name="tripLength" placeholder="7" class="form-control" type="number" min="1">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label">Departure Date</label>
            <div class="col-md-5 inputGroupContainer">
                <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                    <input type="text" id="periodDateFrom" name="periodDateFrom" class="form-control date-picker1" />
                </div>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label">Return Date</label>
            <div class="col-md-5 inputGroupContainer">
                <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                    <input type="text" id="periodDateTill" name="periodDateTill" class="form-control date-picker2" />
                </div>
            </div>
        </div>


        <!-- Submit button -->
        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-2">
                <button type="submit" class="btn btn-warning" name="trendy" value="">Price Trends <span class="glyphicon glyphicon-send"></span></button>
            </div>
        </div>

        <%--</fieldset>--%>
    </form>

</div>

<div class="result">
    <br>
    <table id="trendy_table">
        <tr>
            <th>Date</th>
            <c:forEach items="${periodTrendy}" var="trend">
                <td>${trend.key}</td>
            </c:forEach>
        </tr>
        <tr>
            <th>Currency deviations &#91;&#37;&#93;</th>
            <c:forEach items="${periodTrendy}" var="trend">
                <td>${trend.value.get(0)}</td>
            </c:forEach>
        </tr>
        <tr>
            <th>Petrol deviations &#91;&#37;&#93;</th>
            <c:forEach items="${periodTrendy}" var="trend">
                <td>${trend.value.get(1)}</td>
            </c:forEach>
        </tr>
        <tr>
            <th>Sum &#91;&#37;&#93;</th>
            <c:forEach items="${periodTrendy}" var="trend">
                <td>${trend.value.get(2)}</td>
            </c:forEach>
        </tr>
    </table>
</div>

<h3>${conclusion}</h3>

    <script src="vendor/Chart.bundle.js"></script>
    <canvas id="myChart" width="400" height="400"></canvas>
    <script>
        var ctx = document.getElementById("myChart");
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    </script>


<%@ include file="footer.jsp" %>