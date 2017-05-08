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
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script type="text/javascript">
    $(document).ready(function() {

        $(".date-picker2").datepicker(
            {dateFormat: 'yy/mm/dd'}
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
                    $('.date-picker2').datepicker("option","minDate",newDate);
                }
            });

        $('#reg_form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                uploadfile: {
                    validators: {
                        notEmpty: {
                            message: 'You must select a valid payroll file to upload'
                        },
                        file: {
                            extension: 'txt,xls,csv',
                            type: 'text/plain,application/vnd.ms-excel,text/csv'
                        }
                    }
                },
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
                            format: 'YYYY/MM/DD',
                            message: 'The format is YYYY/MM/DD'
                        }
                    }
                }
//                email: {
//                    validators: {
//                        notEmpty: {
//                            message: 'Please supply your email address'
//                        },
//                        emailAddress: {
//                            message: 'Please supply a valid email address'
//                        }
//                    }
//                },
//                password: {
//                    validators: {
//                        identical: {
//                            field: 'confirmPassword',
//                            message: 'Confirm your password below - type same password please'
//                        }
//                    }
//                },
//                confirmPassword: {
//                    validators: {
//                        identical: {
//                            field: 'password',
//                            message: 'The password and its confirm are not the same'
//                        }
//                    }
//                },

            }
        });
//            .on('success.form.bv', function(e) {
//                $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
//                $('#reg_form').data('bootstrapValidator').resetForm();
//
//                // Prevent form submission
//                e.preventDefault();
//
//                // Get the form instance
//                var $form = $(e.target);
//                // Get the BootstrapValidator instance
//                var bv = $form.data('bootstrapValidator');
//
//                // Use Ajax to submit form data
//                $.post($form.attr('action'), $form.serialize(), function(result) {
//                    console.log(result);
//                }, 'json');
//            });

        $('.date-picker1').on('changeDate show', function(e) {
            $('#reg_form').bootstrapValidator('revalidateField', 'date1');
        });
        $('.date-picker2').on('changeDate show', function(e) {
            $('#reg_form').bootstrapValidator('revalidateField', 'date2');
        });
    });

</script>

<div id="footer">
    <div class="car">
        <img  id="car-mirror" src="img/moving-car.png" alt="car">
    </div>
    <div id="footercontent" >infoShare Academy, Team ERROR</div>
</div>
<script src="/vendor/js/bootstrap.js"></script>
</body
</html>

