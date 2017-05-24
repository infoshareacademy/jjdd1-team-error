<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.HashSet" %><%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 01:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="headAndStyle.jsp" %>
<%@ include file="header.jsp" %>
<%@ include file="headerOptions.jsp" %>
<%@ include file="car.jsp" %>

<form method="post" action="/trendy" class="form-horizontal" id="trendy_form">
<div class="data">
    <br>
    <label><b>Given data:</b></label>
    <li>Country: ${country}</li>
    <li>Currency:  <%= session.getAttribute("currency") %></li>
    <li>Fuel type:  <%= session.getAttribute("fuelTypeString") %></li>
    <li>Trip length:  <%= session.getAttribute("tripLength") %></li>
    <li>Date from:  <%= session.getAttribute("trendPeriodFrom") %></li>
    <li>Date till:  <%= session.getAttribute("trendPeriodTill") %></li>
    <li>Starting days:
        <c:forEach items="${startingDaysString}" var="trend">
            ${trend}
        </c:forEach>
    </li>
    <br>
</div>


<div>

    <!-- Text input-->

    <div class="form-group">
        <label class="col-md-4 control-label">Trip length</label>
        <div class="col-md-5  inputGroupContainer">
            <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-cog"></i></span>
                <input name="tripLength" class="form-control" type="number" min="1" value="<%= session.getAttribute("tripLength") %>"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-4 control-label">Date from</label>
        <div class="col-md-5 inputGroupContainer">
            <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input type="text" id="trendPEriodFrom" name="trendPeriodFrom" class="form-control date-picker1" value="<%= session.getAttribute("trendPeriodFrom") %>"/>
            </div>
        </div>
    </div>

    <!-- Text input-->
    <div class="form-group">
        <label class="col-md-4 control-label">Date till</label>
        <div class="col-md-5 inputGroupContainer">
            <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input type="text" id="periodDateTill" name="trendPeriodTill" class="form-control date-picker2" value="<%= session.getAttribute("trendPeriodTill") %>" />
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-4 control-label">Starting days</label>
    </div>
    <div>
        <label class="checkbox-inline">
            <% if (((HashSet<String>)session.getAttribute("startingDaysString")).contains("Monday")) { %>
                <input type="checkbox" name="startingDays" value="1" checked> Monday
            <% } else { %>
                <input type="checkbox" name="startingDays" value="1" > Monday
            <% } %>
        </label>
        <label class="checkbox-inline">
            <% if (((HashSet<String>)session.getAttribute("startingDaysString")).contains("Tuesday")) { %>
            <input type="checkbox" name="startingDays" value="2" checked> Tuesday
            <% } else { %>
            <input type="checkbox" name="startingDays" value="2" > Tuesday
            <% } %>
        </label>
        <label class="checkbox-inline">
            <% if (((HashSet<String>)session.getAttribute("startingDaysString")).contains("Wednesday")) { %>
            <input type="checkbox" name="startingDays" value="3" checked> Wednesday
            <% } else { %>
            <input type="checkbox" name="startingDays" value="3" > Wednesday
            <% } %>
        </label>
        <label class="checkbox-inline">
            <% if (((HashSet<String>)session.getAttribute("startingDaysString")).contains("Thursday")) { %>
            <input type="checkbox" name="startingDays" value="4" checked> Thursday
            <% } else { %>
            <input type="checkbox" name="startingDays" value="4" > Thursday
            <% } %>
        </label>
        <label class="checkbox-inline">
            <% if (((HashSet<String>)session.getAttribute("startingDaysString")).contains("Friday")) { %>
            <input type="checkbox" name="startingDays"  value="5" checked> Friday
            <% } else { %>
            <input type="checkbox" name="startingDays"  value="5" > Friday
            <% } %>
        </label>
        <label class="checkbox-inline">
            <% if (((HashSet<String>)session.getAttribute("startingDaysString")).contains("Saturday")) { %>
            <input type="checkbox" name="startingDays"  value="6" checked> Saturday
            <% } else { %>
            <input type="checkbox" name="startingDays"  value="6" > Saturday
            <% } %>
        </label>
        <label class="checkbox-inline">
            <% if (((HashSet<String>)session.getAttribute("startingDaysString")).contains("Sunday")) { %>
            <input type="checkbox" name="startingDays"  value="7" checked> Sunday
            <% } else { %>
            <input type="checkbox" name="startingDays"  value="7" > Sunday
            <% } %>
        </label>
    </div>

    <br>

    <!-- Submit button -->
    <div class="form-group">
        <label class="col-md-4 control-label"></label>
        <div class="col-md-2">
            <button type="submit" class="btn btn-warning" >Price Trends <span class="glyphicon glyphicon-send"></span></button>
        </div>
    </div>
</div>

</form>
    <script type="text/javascript">
        $(document).ready(function() {

            $(".date-picker2").datepicker(
                {dateFormat: 'yy/mm/dd'}
            );
            $(".date-picker1").datepicker(
                {
                    dateFormat: 'yy/mm/dd', minDate: 0,
                    onSelect: function (date) {
                        var periodDateFrom = $('.date-picker1').datepicker('getDate');
                        var date = new Date(Date.parse(periodDateFrom));
                        date.setDate(date.getDate() + 1);
                        var newDate = date.toDateString();
                        newDate = new Date(Date.parse(newDate));
                        $('.date-picker2').datepicker("option","minDate",newDate);
                    }
                });
            $('#trendy_form').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    tripLength: {
                        validators: {
                            notEmpty: {
                                message: 'Please state the distance you wish to travel'
                            }
                        }
                    },
                    periodDateFrom: {
                        validators: {
                            notEmpty: {
                                message: 'The Departure Date is required and cannot be empty'
                            },
                            date: {
                                format: 'YYYY/MM/DD',
                                message: 'The format is YYYY/MM/DD'
                            }
                        }
                    },
                    periodDateTill: {
                        validators: {
                            notEmpty: {
                                message: 'The Return Date is required and cannot be empty'
                            },
                            date: {
                                format: 'YYYY/MM/DD',
                                message: 'The format is YYYY/MM/DD'
                            }
                        }
                    }
                }
            });

            $('.date-picker1').on('changeDate show', function(e) {
                $('#reg_form').bootstrapValidator('revalidateField', 'periodDateFrom');
            });
            $('.date-picker2').on('changeDate show', function(e) {
                $('#reg_form').bootstrapValidator('revalidateField', 'periodDateTill');
            });
        });
    </script>


    <div id="container" style="min-width: 300px; height: 400px; margin: auto">
        <h2>Fuel and Currency Trends</h2>
        <h3><%= session.getAttribute("conclusion") %></h3>
    </div>

    <script>
        var json1 = <%= session.getAttribute("json1") %>;
        var json2 = <%= session.getAttribute("json2") %>;
        var dateValues = [];
        var currencyValues = [];
        var petrolValues = [];
        var sumValues = [];
        for (var i = 0; i < json1.length; i++) {
            dateValues.push(json1[i].year + "-" +json1[i].month + "-" + json1[i].day);
        }
        for (var i = 0; i < json2.length; i++) {
            currencyValues.push(json2[i][0]);
        }
        for (var i = 0; i < json2.length; i++) {
            petrolValues.push(json2[i][1]);
        }
        for (var i = 0; i < json2.length; i++) {
            sumValues.push(json2[i][2]);
        }
        $.getJSON('https://www.highcharts.com/samples/data/jsonp.php?filename=usdeur.json&callback=?', function (data) {

            Highcharts.chart('container', {
                chart: {
                    zoomType: 'x'
                },
                title: {
                    text: 'Currency and petrol percentage deviations for given period'
                },
                subtitle: {
                    text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                },
                xAxis: {
//                    type: 'datetime',
                    categories: dateValues
                },
                yAxis: {
                    title: {
                        text: 'Exchange rate'
                    }
                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    area: {
                        marker: {
                            radius: 2
                        },
                        lineWidth: 1,
                        states: {
                            hover: {
                                lineWidth: 1
                            }
                        },
                        threshold: null
                    }
                },

                series: [{
                    type: 'area',
                    name: 'Currency statistics',
                    color: '#f76664',
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    data: currencyValues
                }, {
                    type: 'area',
                    name: 'Petrol statistics',

                    fillColor: {
                        color: '#79c97c',
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    data: petrolValues
                }, {
                    type: 'area',
                    name: 'Full statistics',
                    color: '#39b0c9',
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    data: sumValues

                }]

            });
        });
    </script>

    <div class="container" style="padding-bottom:120px;">
        <h2>Fuel and Currency Trends</h2>
        <h3><%= session.getAttribute("conclusion") %></h3>
        <div>
            <canvas id="myChart"></canvas>
        </div>
    </div>

    <script src="vendor/Chart.bundle.js"></script>

<%@ include file="footer.jsp" %>