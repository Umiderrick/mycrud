var Login = function () {

    // 根据“记住我”，读取cookie中的用户名和密码，其中密码为加密密码
    var getUserCookie=function() {
        if ($.cookie("rmbUser") == "true") {
            $("#remember").attr("checked", true);
            $("#username").val($.cookie("userName"));
            $("#password").val($.cookie("passWord"));
        }
    };

    // 登录表单验证：前端验证后提交
    var handleLogin = function() {
        $('.login-form').validate({
            errorElement: 'span',                                 // default input error message container
            errorClass: 'help-block',                             // default input error message class
            focusInvalid: false,                                 // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "没有填写用户名",
                },
                password: {
                    required: "Password is required."
                }
            },

            invalidHandler: function (event, validator) {         //display error alert on form submit
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function (element) {                       // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                var passwordInput = $('[name="password"]');                    // 获取密码文本框对象
                if (passwordInput.val().length<64) {                           // 未加密的密码长度小于64
                    passwordInput.val(sha256_digest(passwordInput.val()));     // 将未加密的密码加密
                }

                form.submit();                                                 // rest/user/login
            }
        });

        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit();
                }
                return false;
            }
        });
    };;

    var handleForgetPassword = function () {
        $('.forget-form').validate({
            errorElement: 'span',                                 //default input error message container
            errorClass: 'help-block',                             // default input error message class
            focusInvalid: false,                                 // do not focus the last invalid input
            ignore: "",
            rules: {
                email: {
                    required: true,
                    email: true
                }
            },

            messages: {
                email: {
                    required: "Email is required."
                }
            },

            invalidHandler: function (event, validator) {         //display error alert on form submit

            },

            highlight: function (element) {                       // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                form.submit();
            }
        });

        $('.forget-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    $('.forget-form').submit();
                }
                return false;
            }
        });

        jQuery('#forget-password').click(function () {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();
        });

        jQuery('#back-btn').click(function () {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
        });

    };;

    var handleRegister = function () {

        function format(state) {
            if (!state.id) return state.text; // optgroup
            return "<img class='flag' src='../../assets/global/img/flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
        }


        $("#select2_sample4").select2({
            placeholder: '<i class="fa fa-map-marker"></i>&nbsp;Select a Country',
            allowClear: true,
            formatResult: format,
            formatSelection: format,
            escapeMarkup: function (m) {
                return m;
            }
        });


        $('#select2_sample4').change(function () {
            $('.register-form').validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
        });


        $('.register-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                //fullname: {
                //    required: true
                //},
                //email: {
                //    required: true,
                //    email: true
                //},
                //address: {
                //    required: true
                //},
                //city: {
                //    required: true
                //},
                //country: {
                //    required: true
                //},
                rusername: {
                    required: true,
                    minlength: 2,
                    remote: {                                     // http://jqueryvalidation.org/remote-method/
                        url:"/rest/user/checkuser",
                        type:"post",
                        //contentType : "json",                  // 此行必须删除，后台才能读到数据。
                        data: {
                            username: function(){
                                //alert($("#register_username").val());
                                return $("#register_username").val();
                            }
                        }
                    }
                },
                password: {
                    required: true
                },
                rpassword: {
                    equalTo: "#register_password"
                },
                tnc: {
                    required: true
                }
            },

            messages: { // custom messages for radio buttons and checkboxes
                rusername: {
                    required: "没有填写用户名",
                    minlength: jQuery.validator.format("用户名不能小于{0}个字符"),
                    remote: "用户名已经被注册"
                },
                tnc: {
                    required: "Please accept TNC first."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit

            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                if (element.attr("name") == "tnc") { // insert checkbox errors after the container
                    error.insertAfter($('#register_tnc_error'));
                } else if (element.closest('.input-icon').size() === 1) {
                    error.insertAfter(element.closest('.input-icon'));
                } else {
                    error.insertAfter(element);
                }
            },

            submitHandler: function (form) {
                var rgPasswordInput = $('[name="passwords"]');           // 获取密码文本框对象
                var rPasswordInput = $('[name="rpassword"]');            // 获取密码文本框对象

                if (rgPasswordInput.val().length<64 && rPasswordInput.val().length<64) {   // 未加密的密码长度小于64
                    rgPasswordInput.val(sha256_digest(rgPasswordInput.val()));             // 将未加密的密码加密
                    rPasswordInput.val(sha256_digest(rPasswordInput.val()));               // 将未加密的密码加密
                }

                form.submit();                                           // rest/user/register
            }
        });

        $('.register-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit();
                }
                return false;
            }
        });

        jQuery('#register-btn').click(function () {
            jQuery('.login-form').hide();
            jQuery('.register-form').show();
        });

        jQuery('#register-back-btn').click(function () {
            jQuery('.login-form').show();
            jQuery('.register-form').hide();
        });
    };;

    return {
        init: function () {
            getUserCookie();                                      // 读取记住我Cookie
            handleLogin();                                        // 登录
            handleForgetPassword();                               // 忘记密码
            handleRegister();                                     // 注册
        }
    };

}();