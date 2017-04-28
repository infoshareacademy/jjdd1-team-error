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
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


<script type="text/javascript">
    $(document).ready(function() {
        $('#embeddingDatePicker1').datepicker({
            format: 'yyyymmdd'
        })
            .on('changeDate', function(e) {
                // Revalidate the date when user change it
                $('#reg_form').bootstrapValidator('revalidateField', 'embeddingDatePicker1');
            });
        $('#embeddingDatePicker')
            .datepicker({
                format: 'mm/dd/yyyy'
            })
            .on('changeDate', function(e) {
                // Set the value for the date input
                $("#selectedDate").val($("#embeddingDatePicker").datepicker('getFormattedDate'));

                // Revalidate it
                $('#reg_form').bootstrapValidator('revalidateField', 'selectedDate');
            });

        $('#reg_form').bootstrapValidator({
            // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
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
                    // The hidden input will not be ignored
                    excluded: false,
                    validators: {
                        notEmpty: {
                            message: 'The date is required'
                        },
                        date: {
                            format: 'YYYYMMDD',
                            message: 'The date is not a valid format'
                        }
                    }
                },
                date11: {
                    validators: {
                        notEmpty: {
                            message: 'The date is required'
                        },
                    }
                },
                selectedDate: {
                    // The hidden input will not be ignored
                    excluded: false,
                    validators: {
                        notEmpty: {
                            message: 'The date is required'
                        },
                        date: {
                            format: 'MM/DD/YYYY',
                            message: 'The date is not a valid'
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
//
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
        })

            .on('success.form.bv', function(e) {
                $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
                $('#reg_form').data('bootstrapValidator').resetForm();

                // Prevent form submission
                e.preventDefault();

                // Get the form instance
                var $form = $(e.target);
                // Get the BootstrapValidator instance
                var bv = $form.data('bootstrapValidator');

                // Use Ajax to submit form data
                $.post($form.attr('action'), $form.serialize(), function(result) {
                    console.log(result);
                }, 'json');
            });
        $('#date1').on('dp.change dp.show', function(e) {
            $('#reg_form').bootstrapValidator('revalidateField', 'date1');
        });
    });

</script>


</body>
</html>

