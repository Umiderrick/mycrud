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

        var form2 = $('#userform');
        var error2 = $('.alert-danger', form2);
        var success2 = $('.alert-success', form2);

        // 仅用于演示验证函数的编写方式
        // this.optional(element)==true,用于在该空控件为非必填项目时可以通过验证
        // value>=params[0] && value<=params[1];用户演示参数如何使用，可输入a和f之间的字母。
        jQuery.validator.addMethod("checkState",function(value, element,params){
            return this.optional(element)||value%2==0 && value%3==0 || value>=params[0] && value<=params[1];}, //要么为空，要么能够被2和3整除
            "必须能被2和3整除");

        form2.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            rules: {
                //id: {
                //    required: true
                //},
                username: {
                    required: true,
                    minlength: 2,
                    remote: {                                     // http://jqueryvalidation.org/remote-method/
                        url:"/rest/admin/checkuser",
                        type:"post",
                        //contentType : "json",                  // 此行必须删除，后台才能读到数据。
                        data: {
                            username: function(){
                                // alert($("#username").val());
                                return $("#username").val();
                            }
                        }
                    }
                },
                password: {
                    required: true
                },
                state: {
                    required: false,
                    //checkState: true                   // 自定义验证函数，期望true，如果为false则展示提示信息
                    checkState: ["a", "f"]               //
                }
            },
            messages:{
                username: {
                    required: "没有填写用户名",
                    minlength: jQuery.validator.format("用户名不能小于{0}个字符"),
                    remote: "用户名已经被注册"
                }
            },


            invalidHandler: function (event, validator) { //display error alert on form submit
                success2.hide();
                error2.show();
                Metronic.scrollTo(error2, -200);
            },

            //使用图标提示
            //errorPlacement: function (error, element) { // render error placement for each input type
            //    var icon = $(element).parent('.input-icon').children('i');
            //    icon.removeClass('fa-check').addClass("fa-warning");
            //    icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
            //},

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
                var jsonuserinfo1=$("#userform").serializeObject();
                var jsonuserinfo=JSON.stringify(jsonuserinfo1);

                $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "rest/page/adduserpost",
                    data : jsonuserinfo,
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
3}();