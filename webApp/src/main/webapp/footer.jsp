<%--
  Created by IntelliJ IDEA.
  User: SebastianLos
  Date: 22.04.2017
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>

<script src="vendor/Chart.bundle.js"></script>
<script src="vendor/jquery-3.2.1.js"></script>
<script src="vendor/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script type="text/javascript">
    $(document).ready(function () {

        $(".date-picker2").datepicker(
            {dateFormat: 'yy/mm/dd', maxDate: '+4Y'}
        );
        $(".date-picker1").datepicker(
            {
                dateFormat: 'yy/mm/dd', minDate: 0,
                onSelect: function (date) {
                    var date1 = $('.date-picker1').datepicker('getDate');
                    var date = new Date(Date.parse(date1));
                    date.setDate(date.getDate() + 1);
                    var newDate = date.toDateString();
                    newDate = new Date(Date.parse(newDate));
                    $('.date-picker2').datepicker("option", "minDate", newDate);
                }
            });

        $('#reg_form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                country: {
                    validators: {
                        notEmpty: {
                            message: 'Please select your destination country'
                        }
                    }
                },
                fuelType: {
                    validators: {
                        notEmpty: {
                            message: 'Please select the type of fuel used by your car'
                        }
                    }
                },
                fuelUsage: {
                    validators: {
                        notEmpty: {

                            message: 'Please state your car`s fuel usage'
                        }
                    }
                },
                fullDistance: {
                    validators: {
                        notEmpty: {
                            message: 'Please state the distance you wish to travel'
                        }
                    }
                },
                date1: {
                    validators: {
                        notEmpty: {
                            message: 'The Departure Date is required and cannot be empty'
                        },
                        date: {
                            maxDate: '+4Y',
                            format: 'YYYY/MM/DD',
                            message: 'The format is YYYY/MM/DD'
                        }
                    }
                },
                date2: {
                    validators: {
                        notEmpty: {
                            message: 'The Return Date is required and cannot be empty'
                        },
                        date: {
                            maxDate: '+4Y',
                            format: 'YYYY/MM/DD',
                            message: 'The format is YYYY/MM/DD'
                        }
                    }
                }
            }
        });

        $('.date-picker1').on('changeDate show', function (e) {
            $('#reg_form').bootstrapValidator('revalidateField', 'date1');
        });
        $('.date-picker2').on('changeDate show', function (e) {
            $('#reg_form').bootstrapValidator('revalidateField', 'date2');
        });
    });
</script>

<div style="position: absolute; bottom: 30px">
    <form>
        <div class="btn-group btn-group-justified" role="group" aria-label="..." >
            <div class="btn-group" role="group">
                <button id="one" class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="get" formaction="/report" name="countryAndCurrencyReport" value="">Country / currency report</button>
            </div>
            <div class="btn-group" role="group">
                <button id="two" class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="get" formaction="/report" name="fuelTypeReport" value="">Fuel report
                </button>
            </div>
            <div class="btn-group" role="group">
                <button id="three" class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="get" formaction="/report" name="usersReport" value="">Users report
                </button>
            </div>
            <div class="btn-group" role="group">
                <button id="four" class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="post" formaction="/fileUploader.jsp" name="fuelTypeReport" value="">Update files
                </button>
            </div>
            <div class="btn-group" role="group">
                <button id="five" class="btn btn-outline-inverse btn-lg" type="submit"
                        formmethod="post" formaction="/admin" name="adminPage" value="">Admin</button>
            </div>
        </div>
    </form>
</div>

<div id="footer">
    <div id="footercontent">infoShare Academy, Team ERROR</div>
</div>
<script src="/vendor/js/bootstrap.js"></script>

<%--hidding buttons when admin isn't logged in--%>
<script>
    var useremail = '${userEmail}';
    var obj = JSON.parse('${jsonAdminList}');

    for (i = 0; i < obj.length; i++) {
        $('#one').hide();
        $('#two').hide();
        $('#three').hide();
        $('#four').hide();
        $('#five').hide();
        if(useremail==obj[i]){
            $('#one').show();
            $('#two').show();
            $('#three').show();
            $('#four').show();
            $('#five').show();
            break;
        }
    }
</script>

</body>
</html>

