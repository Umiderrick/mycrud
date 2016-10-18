/**
 * Created by umiderrick on 2016/7/20.
 */
var oTable;

var TableEditable = function () {
    return {
        init: function () {

            if (oTable==undefined || oTable=="undefined" || oTable==null)
            {

            }
            else
            {
                oTable.fnDestroy();
            }

            var table = $('#tba');

            oTable=table.dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"]                            // change per page values here
                ],
                "pageLength": 5,
                "bStateSave":true,
                "bFilter":false,
                "bPaginate":true,
                "sPaginationType":"full_numbers",                 //two_button
                "bProcessing": false,                            // 是否显示取数据时的那个等待提示
                "bServerSide": true,                             //这个用来指明是通过服务端来取数据
                "columns":[
                    {"data":"id"},
                    {"data":"permissionName"},
                    {"data":"permissionSign"},
                    {"data":"description"},
                    {"data":"url"}
                ],
                "fnServerData": retrieveData,                     // 获取数据的处理函数
                "fnDrawCallback":fnRefresh,                       // 初始化，上一页，下一页，搜索等都会触发该事件
                "aoColumnDefs":[
                    {
                        "aTargets": [0],
                        "bSortable": false
                    },
                    {
                        "aTargets": [5],
                        "mRender": function (data, type, full) {
                            return "<a class=\"edit\" href=\"/rest/page/editauth\">" + "编辑" + "</a>"; //(7) 设置“编辑”超链接
                        }
                    },
                    {
                        "aTargets": [6],
                        "mRender": function (data, type, full) {
                            return "<a class=\"delete\" href=\"#portlet-config\" data-toggle=\"modal\" class=\"config\">" + "删除" + "</a>";
                        }
                    }
                ],
                // m410,特别要求
                "aoColumns":[
                    {
                        "mData":"id",
                        "mRender":function(data,type,full){
                            var arr=[];
                            str=full.toString();                  // full:一行记录所有数据
                            arr=str.split(",");
                            return arr[0];
                        }
                    }
                ]
            });

            //
            function fnRefresh() {
                $('#tba a.edit').on('click', function (e) {
                    e.preventDefault();

                    var nRow = $(this).parents('tr')[0];          // 获得当前选中“编辑”的行号
                    var aData = oTable.fnGetData(nRow);           // 根据行号获取行数据

                    var url="/rest/page/editauth";
                    $('#EditDiv').load(url,{number1:aData[0].toString(),number2:aData[1].toString()}); // 将行数据中第一个数据，也就是“编号”作为参数number1进行传递
                });

                $('#tba a.delete').on('click', function (e) {
                    e.preventDefault();
                    var nRow = $(this).parents('tr')[0];
                    var aData = oTable.fnGetData(nRow);
                    $.ajax({
                        url: "rest/page/deleteauth/"+aData[0],
                        type: 'delete',
                        async: false,
                        success: function (result) {
                            $("#test").attr("value",aData[0].toString()); // 通过隐藏标签传递数据<input type="hidden" id="test" value="">
                            $('#main-content').load("rest/page/manageauth");
                        },
                        error: function (msg) {
                        }
                    });
                });
            }
            // (6) 通过ajax获取表格中的数据：3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
            // sSource2：
            // aoData2: datatable基本数据，用于传递给后台
            // fnCallback2:回调函数，成功获取数据后，通过该函数绑定datatable
            function retrieveData(sSource2, aoData2, fnCallback2) {
                //alert(JSON.stringify(aoData2));
                $.ajax({
                    url: "rest/page/datapermissiontableajax",
                    data: {"aoData": JSON.stringify(aoData2)},    //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                    type: 'post',
                    dataType: 'json',
                    async: false,
                    success: function (result) {
                        //alert(JSON.stringify(result));
                        fnCallback2(result);                      //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                    },
                    error: function (msg) {
                    }
                });
            }
            var nEditing = null;
        }
    };
}();