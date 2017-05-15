<%--
  Created by IntelliJ IDEA.
  User: igafalkowska
  Date: 11.05.17
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headersAndStyle.jsp" %>
<%@ include file="headerOptions.jsp" %>


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
    var dieselPopularity = ${dieselPopularity};
    var gasolinePopularity = ${gasolinePopularity};
    var fuelType = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ["popularity of diesel", "popularity of gasoline"],
            datasets: [
                {
                    data: [dieselPopularity, gasolinePopularity],
                    backgroundColor: [
                        "#FF6384",
                        "#36A2EB",
                        "#FFCE56"
                    ],
                    hoverBackgroundColor: [
                        "#FF6384",
                        "#36A2EB",
                        "#FFCE56"
                    ],
                }
            ]
        }
    });
</script>

<%@ include file="footer.jsp" %>
