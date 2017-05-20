<%--
  Created by IntelliJ IDEA.
  User: Iga
  Date: 09.05.2017
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<%@ include file="headerOptions.jsp" %>


<div class="row" style="padding-bottom: 120px; margin:0;">
        <div class="col-md-6" style="padding:0px 100px;">
            <h3>Country report</h3>
            <canvas id="country" width="300" height="200"></canvas>
        </div>

        <div class="col-md-6" style="padding:0px 100px">
            <h3>Currency report</h3>
            <canvas id="currency" width="300" height="200"></canvas>
        </div>
</div>

<script src="vendor/Chart.bundle.js"></script>

<script>
    var chartColors = {
        blueFirstLevel: 'rgba(54, 162, 235, 0.2)',
        blueSecondLevel: 'rgba(54, 162, 235, 0.5)',
        blueThirdLevel: 'rgba(54, 162, 235, 0.8)'
    };

    var ctx = document.getElementById("country");
    var country = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                <c:forEach items="${countriesList}" var="country">
                "${country}",
                </c:forEach>
            ],
            datasets: [
                {
                    label: "popularity of country",
                    backgroundColor: [
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel
                    ],
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                    data: [
                        <c:forEach items="${countriesPopularityList}" var="countriesPopularity">
                        ${countriesPopularity},
                        </c:forEach>
                    ],
                }
            ]

        },
        options:{
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        stepSize: 1
                    }
                }]
            }
        }
    });

    var colorSecondLevel = 2;
    var colorThirdLevel = 5;
    var dataset = country.data.datasets[0];
    for (var i = 0; i < dataset.data.length; i++) {
        if (dataset.data[i] > colorSecondLevel && dataset.data[i] < colorThirdLevel) {
            dataset.backgroundColor[i] = chartColors.blueSecondLevel;
        }
        else if (dataset.data[i] > colorThirdLevel){
            dataset.backgroundColor[i] = chartColors.blueThirdLevel;
        }
    }
    country.update();
</script>

<script>
    var chartColors = {
        blueFirstLevel: 'rgba(54, 162, 235, 0.2)',
        blueSecondLevel: 'rgba(54, 162, 235, 0.5)',
        blueThirdLevel: 'rgba(54, 162, 235, 0.8)'
    };

    var ctx = document.getElementById("currency");
    var currency = new Chart(ctx, {
        type: 'bar',
        data: {
            labels:
                [
                    <c:forEach items="${currenciesList}" var="currency">
                    "${currency}",
                    </c:forEach>
                ],
            datasets: [
                {
                    label: "popularity of currency",
                    backgroundColor: [
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel,
                        chartColors.blueFirstLevel
                    ],
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1,
                    data: [
                        <c:forEach items="${currenciesPopularityList}" var="curPopularity">
                        ${curPopularity},
                        </c:forEach>
                    ],
                }
            ]
        },
        options:{
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        stepSize: 1
                    }
                }]
            }
        }
    });

    var colorSecondLevel = 2;
    var colorThirdLevel = 5;
    var dataset = currency.data.datasets[0];
    for (var i = 0; i < dataset.data.length; i++) {
        if (dataset.data[i] > colorSecondLevel && dataset.data[i] < colorThirdLevel) {
            dataset.backgroundColor[i] = chartColors.blueSecondLevel;
        }
        else if (dataset.data[i] > colorThirdLevel){
            dataset.backgroundColor[i] = chartColors.blueThirdLevel;
        }
    }
    currency.update();
</script>


<%@ include file="footer.jsp" %>
