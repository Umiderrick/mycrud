var commandOK=0;

$(function() {
    var Index = (function() {
        var me = {};

        // 处理一级菜单点击
        me.handleMenuClick = function() {
            $('.page-sidebar-menu > li').click(function(e) {
                var menu = $('.page-sidebar-menu');
                var li = menu.find('li.active').removeClass('active');

                // 添加选中 打开的样式
                //$(this).addClass('active');
            });
        };

        //(2) 根据点击页面的超链接，加载指定的目标页面，转向控制器
        me.loadTargetPage = function() {
            $('.page-sidebar-menu li a').click(function(e) {
                e.preventDefault();
                var url = this.href;

                if (url != null && url != 'javascript:;') {
                    $('#main-content').load(url);
                }
            });
        };

        // 模态对话框，通过<input type="hidden" id="test" value="">传递参数
        me.okclick = function() {
            $('#portlet-config').on('show.bs.modal', function () {
                //alert("show");
                commandOK=0;
            });

            $('#portlet-config').on('shown.bs.modal', function () {
                //alert("shown");
            });

            $('#portlet-config').on('hide.bs.modal', function () {
                //alert("hide");
            });

            $('#portlet-config').on('hidden.bs.modal', function () {
                //alert("hidden");
            });

            $("#ok").click(function() {
                commandOK=1;
            });

            $("#cancel").click(function() {
                commandOK=0;
            })
        };

        me.init = function() {
            me.handleMenuClick();
            me.loadTargetPage();
            me.okclick();
        };

        return me;
    })();

    Index.init();
});