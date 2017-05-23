<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Iga
  Date: 09.05.2017
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>

<c:choose>
    <c:when test="${countryStatistics != null && currencyStatistics != null}">

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
                        <c:forEach items="${countryStatistics}" var="country">
                        "${country.key}",
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
                                <c:forEach items="${countryStatistics}" var="country">
                                ${country.value},
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
                            <c:forEach items="${currencyStatistics}" var="currency">
                            "${currency.key}",
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
                                <c:forEach items="${currencyStatistics}" var="currency">
                                ${currency.value},
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

    </c:when>

    <c:otherwise>
<h2 class="error">Reports module is not connected at the moment</h2>
    </c:otherwise>

</c:choose>

<%@ include file="footer.jsp" %>
