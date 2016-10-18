<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta charset="UTF-8">
        <title>datatable sample</title>
        <link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="assets/css/jquery.dataTables.min.css"/>
    </head>
    <body>

        <a href="/rest/page/usermanage" id="aaa">add</a>
        <div id="con1">content</div>
        <div id="con2">content</div>
        <div id="con3">
        <table id="tb" class="display" >
            <thead>
            <tr>
                <th>col1</th>
                <th>col2</th>
                <th>col3</th>
                <th>col4</th>
                <th>col5</th>
                <th>col6</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        </div>

        <script src="assets/plugins/jquery/jquery-1.11.1.js" type="text/javascript" charset="utf-8"></script>
        <script src="assets/plugins/jquery.dataTables.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>


        <!--(4)控制器跳转至该页面，并执行相应的js代码-->
        <script type="text/javascript" src="app/js/datatable_test.js"></script>

        <script>

            jQuery(document).ready(function() {

                //App.init();

                TableEditable.init();

            });

        </script>
        <!--
        <script type="text/javascript">
            $(document).ready(function() {
                var oTable=$("#tb").dataTable({
                    "bProcessing": false, // 是否显示取数据时的那个等待提示
                    "bServerSide": true,//这个用来指明是通过服务端来取数据
                    "fnServerData": retrieveData, // 获取数据的处理函数
                    "aoColumnDefs":[
                        {
                            "aTargets": [0],
                            "bSortable": false,
                        },
                        {
                            "aTargets": [4],
                            "mRender": function (data, type, full) {
                                return "<a class=\"edit\" href=\"/rest/page/usermanage2\">" + data + "</a>";
                            }
                        },
                        {
                            "aTargets": [5],
                            "mRender": function (data, type, full) {
                                return "<a class=\"delete\" href=\"/rest/page/usermanage2\">" + data + "</a>";
                            }
                        }
                    ]
                });

                $("#aaa").click(function(e) {
                    e.preventDefault();
                    var url = this.href;
                    $('#con1').load(url);
                    $('#con3').hide();

                });

                $(".edit").click(function(e) {
                    e.preventDefault();

                    if ($('#con2').is(":hidden"))
                    {
                        $('#con2').show();
                    }

                    var nRow = $(this).parents('tr')[0];
                    var aData = oTable.fnGetData(nRow);
                    alert(aData[0]);

                    var url = this.href;
                    $('#con2').load(url,{number1:aData[0].toString()});

                });

                $(".delete").click(function(e) {
                    e.preventDefault();
                    var url = this.href;
                    $('#con1').load(url);
                });

//                $('#tb a.edit').live('click', function (e) {
//                    e.preventDefault();
//
//                    var nRow = $(this).parents('tr')[0];
//                    alert(nRow);
//                });

            });

            // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
            function retrieveData(sSource111, aoData111, fnCallback111) {
                //alert(sSource111);
                //alert(JSON.stringify(aoData111));
                $.ajax({
                    url:"rest/page/tableDemoajax",
                    data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                    type : 'post',
                    dataType : 'json',
                    async : false,
                    success : function(result) {
                        fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                    },
                    error : function(msg) {
                    }
                });
            }
        </script>
-->

    </body>
</html>