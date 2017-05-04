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