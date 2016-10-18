/**
 * Created by umiderrick on 2016/7/19.
 */
//将一个表单的数据返回成JSON对象
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var FormValidation = function () {

    // validation using icons
    var handleValidation = function() {
        // for more info visit the official plugin documentation:
        // http://docs.jquery.com/Plugins/Validation

        var form2 = $('#authform');
        var error2 = $('.alert-danger', form2);
        var success2 = $('.alert-success', form2);

        form2.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            rules: {
                permissionName: {
                    required: true
                },
                permissionSign: {
                    required:true
                },
                description:{
                    required :false
                }
            },
            messages:{

            },


            invalidHandler: function (event, validator) { //display error alert on form submit
                success2.hide();
                error2.show();
                Metronic.scrollTo(error2, -200);
            },


            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight

            },

            success: function (label, element) {
                var icon = $(element).parent('.input-icon').children('i');
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                icon.removeClass("fa-warning").addClass("fa-check");
            },

            submitHandler: function (form) {
                success2.show();
                error2.hide();
                //form[0].submit(); // submit the form
                var jsonauthinfo1=$("#authform").serializeObject();
                var jsonauthinfo=JSON.stringify(jsonauthinfo1);

                $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "rest/page/addauthpost",
                    data : jsonauthinfo,
                    dataType : "json",
                    success : function(data) {
                        if (data.success=="true") {
                            alert("新增成功:" + data.success);
                        }

                    },
                    error : function(data) {
                        alert("error");

                    }
                });
            }
        });


    };

    return {
        //main function to initiate the module
        init: function () {
            handleValidation();
        }

    };

}();