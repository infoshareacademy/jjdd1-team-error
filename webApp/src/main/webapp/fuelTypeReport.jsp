<%--
  Created by IntelliJ IDEA.
  User: igafalkowska
  Date: 11.05.17
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>


<c:choose>
    <c:when test="${fuelTypeStatistics != null}">

        <div class="row" style="padding-bottom: 120px; margin:0;">
            <div class="col-md-4"></div>
            <div class="col-md-4" style="padding:0px 60px;">
                <h3>Fuel type report</h3>
                <canvas id="fuelType" width="200" height="200"></canvas>
            </div>
            <div class="col-md-4"></div>
        </div>

        <script src="vendor/Chart.bundle.js"></script>
        <script>
            var ctx = document.getElementById("fuelType");

            var fuelType = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: [
                        <c:forEach items="${fuelTypeStatistics}" var="fuelType">
                            "${fuelType.key}",
                        </c:forEach>
                    ],
                    datasets: [
                        {
                            data: [
                                <c:forEach items="${fuelTypeStatistics}" var="fuelType">
                                    "${fuelType.value}",
                                </c:forEach>
                            ],
                            backgroundColor: [
                                "#FF6384",
                                "#36A2EB"
                            ],
                            hoverBackgroundColor: [
                                "#FF6384",
                                "#36A2EB"
                            ],
                        }
                    ]
                }
            });
        </script>

    </c:when>

    <c:otherwise>
        <h2 class="error">Reports module is not connected at the moment</h2>
    </c:otherwise>

</c:choose>

<%@ include file="footer.jsp" %>
