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
</div>
<div class="result">
    <br>
    <table id="trendy_table">
        <tr>
            <th>Month</th>
            <c:forEach items="${currencyTrendy}" var="monthValue">
                <td>${monthValue.key}</td>
            </c:forEach>
        </tr>
        <tr>
            <th>Currency deviations &#91;&#37;&#93;</th>
            <c:forEach items="${currencyTrendy}" var="monthValue">
                <td>${monthValue.value}</td>
            </c:forEach>
        </tr>
        <tr>
            <th>Petrol deviations &#91;&#37;&#93;</th>
            <c:forEach items="${petrolTrendy}" var="monthValue">
                <td>${monthValue.value}</td>
            </c:forEach>
        </tr>
    </table>
</div>


    <div class="container">
        <h2>Fuel and Currency Trends</h2>
        <div>
            <canvas id="myChart"></canvas>
        </div>
    </div>



    <h3>${conclusion}</h3>

    <script src="vendor/Chart.bundle.js"></script>
    <script>
        var ctx = document.getElementById("myChart");
        var monthsInfo = [];
        <c:forEach items="${currencyTrendy}" var="monthValue">
        monthsInfo.push(${monthValue.key});
        </c:forEach>

        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: monthsInfo,


                <%--<c:forEach items="${currencyTrendy}" var="monthValue">--%>
                    <%--${monthValue.key}--%>
                <%--</c:forEach>,--%>
                datasets: [{
                    label: 'currency',
                    data: [1, 2, 3],

                    <%--${currencyValues},--%>

                    <%--<c:forEach items="${currencyTrendy}" var="monthValue">--%>
                        <%--${monthValue.value}--%>
                    <%--</c:forEach>,--%>
                    backgroundColor: "rgba(153,255,51,0.6)"
                }, {
                    label: 'fuel',
                    data: [3, 2, 1],

                    <%--${petrolValues},--%>

                    <%--<c:forEach items="${petrolTrendy}" var="monthValue">--%>
                        <%--${monthValue.value}--%>
                    <%--</c:forEach>,--%>
                    backgroundColor: "rgba(255,153,0,0.6)"
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