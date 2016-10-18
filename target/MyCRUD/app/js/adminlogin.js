var Login = function () {

    // 根据“记住我”，读取cookie中的用户名和密码，其中密码为加密密码
    var getUserCookie=function() {
        if ($.cookie("rmbUser") == "true") {
            $("#remember").attr("checked", true);
            $("#username").val($.cookie("userName"));
            $("#password").val($.cookie("passWord"));
        }
    };;

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
                    required: "Username is required."
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

                form.submit();                                                 // rest/admin/login
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

    return {
        init: function () {
            getUserCookie();
            handleLogin();
        }
    };

}();