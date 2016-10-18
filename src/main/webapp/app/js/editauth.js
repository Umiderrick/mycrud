/**
 * Created by umiderrick on 2016/7/20.
 */
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

$(function() {

    var Index = (function() {
        var me = {};

        //(10) 单击编辑按钮，后通过ajax连接后台
        me.editpost = function() {
            $("#loginBut2").click(function(){

                var jsonroleinfo1=$("#authform2").serializeObject();
                var jsonroleinfo=JSON.stringify(jsonroleinfo1);
                alert(jsonroleinfo);

                $.ajax({
                    type : "POST",
                    contentType : "application/json",
                    url : "rest/page/editauthpost",
                    data : jsonroleinfo,
                    dataType : "json",
                    success : function(data) {
                        alert("修改成功!"+data.success);

                        if (data && data.success == "true") {
                            $('#EditDiv').html("hello");
                            $('#main-content').load("rest/page/manageauth");
                        }

                    },
                    error : function(data) {
                        alert("error");

                    }
                });
            });

        };

        me.init = function() {
            me.editpost();
        };

        return me;
    })();

    Index.init();
});