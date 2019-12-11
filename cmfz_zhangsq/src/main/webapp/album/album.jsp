<%@ page pageEncoding="utf-8" contentType="text/html;utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    
$(function () {
    $("#albumTable").jqGrid({
        url : "${path}/album/selectAlbumByPage",   //查询访问后台路径
        datatype : "json",  //前台接收后台的格式
        autowidth:true, //自动适应宽度
        height:"auto",  //自动适应高度
        rowNum : 3, //初始没有显示条数
        styleUI:"Bootstrap",//设置样式风格
        rowList : [ 3,8, 10, 20, 30 ],     //可选每页显示条数
        pager : '#albumDiv',    //工具栏
        viewrecords : true, //定义是否要显示总记录数
        editurl:"${path}/album/compileAlbum",//增删改路径
        colNames : [ 'id', '标题', '封面', '评分', '作者','播音', '集数','内容','发表日期' ],
        colModel : [
            {name : 'id',index : 'id', width : 55,align : "center"},
            {name : 'title',index : 'title',width : 90,align : "center",editable:true},
            {name : 'coverImg',index : 'coverImg',width : 100,align : "center",editable:true,edittype:"file",
                formatter:function(cellvalue, options, rowObject){
                    return '<img height="50" width="100" src="${path}/album/img/'+cellvalue+'"/>'
                }
            },
            {name : 'score',index : 'score',width : 100,align : "center",editable:true,edittype:"select",editoptions: {value:"5:★★★★★;4:★★★★;3:★★★;2:★★;1:★"},
                formatter:function(cellvalue, options, rowObject){
                    switch (cellvalue) {
                        case "1":return "<span style='color: #d5b422'>★</span>";break;
                        case "2":return "<span style='color: #d5b422'>★★</span>";break;
                        case "3":return "<span style='color: #d5b422'>★★★</span>";break;
                        case "4":return "<span style='color: #d5b422'>★★★★</span>";break;
                        case "5":return "<span style='color: #d5b422'>★★★★★</span>";
                    }
                }
            },
            {name : 'author',index : 'author',width : 100,align : "center",editable:true},
            {name : 'broadcast',index : 'broadcast',width : 100,align : "center",editable:true},
            {name : 'count',index : 'count',width : 100,align : "center"},
            {name : 'countent',index : 'countent',width : 80,align : "center",editable:true},
            {name : 'pubDate',index : 'pubDate',width : 80,align : "center"},
        ],
        subGrid : true, //是否使用子表格
        subGridRowExpanded : function(subgrid_id, row_id) {
            //创建两唯一标识id
            var subgrid_table_id = subgrid_id + "_t";
            var pager_id = "p_" + subgrid_table_id;

            $("#" + subgrid_id).html(
                //根据唯一id创建表格
                "<table id='" + subgrid_table_id+ "' class='scroll'></table>" +
                //根据唯一id创建工具栏
                "<div id='"+ pager_id + "' class='scroll'></div>"
            );


            $("#" + subgrid_table_id).jqGrid({
                url : "${path}/chapter/selectChapterByPage?id="+row_id,
                datatype : "json", //前台接收后台的格式
                autowidth:true, //自动适应宽度
                height:"auto",  //自动适应高度
                rowNum : 3, //初始没有显示条数
                styleUI:"Bootstrap",//设置样式风格
                rowList : [ 3,8, 10, 20, 30 ],     //可选每页显示条数
                pager : "#" +  pager_id,    //工具栏
                viewrecords : true, //定义是否要显示总记录数
                editurl:"${path}/chapter/compileChapter?albumId="+row_id,//增删改路径
                colNames : [ 'id', '标题', '音频路径', '时长','大小',' 上传时间','操作' ],
                colModel : [
                    {name : 'id',width : 100,align : "center"},
                    {name : 'title',width : 100,align : "center",editable:true},
                    {name : 'src',width : 100,align : "center",editable:true,edittype:"file"},
                    {name : 'duration',width : 80,align : "center"},
                    {name : 'size',width : 80,align : "center"},
                    {name : 'uploadTime',width : 80,align : "center"},
                    {name : 'operation',width : 80,align : "center",
                        formatter:function(cellvalue, options, rowObject){
                            return  "<a href='#' onclick='chapterOperation(\""+rowObject.src+"\",\""+rowObject.src+"\")'><span class='glyphicon glyphicon-play-circle' /></a>&emsp;&emsp;"+
                                     "<a href='${path}/chapter/chapterOperation?operation=attachment&src="+rowObject.src+"'><span class='glyphicon glyphicon-download' /></a>"
                        }
                    }
                ]
            });

            //子表格工具栏
            $("#" + subgrid_table_id).jqGrid('navGrid',"#" + pager_id, {edit : true,add : true,del : true},
                {
                    //关闭对话框
                    closeAfterAdd:true,
                    afterSubmit:function (data) {
                        $.ajaxFileUpload({
                            url:"${path}/chapter/chapterUpload",
                            datatype: "json",
                            type:"post",
                            fileElementId:"src",
                            data:{id:data.responseText},
                            success:function () {
                                //刷新页面
                                $("#" + subgrid_table_id).trigger("reloadGrid");
                            }
                        });
                        return "hhh";
                    }
                },
                {
                    //关闭对话框
                    closeAfterAdd:true,
                    afterSubmit:function (data) {
                        $.ajaxFileUpload({
                            url:"${path}/chapter/chapterUpload",
                            datatype:"json",
                            type:"post",
                            fileElementId:"src",
                            data:{id:data.responseText},
                            success:function () {
                                //刷新页面
                                $("#" + subgrid_table_id).trigger("reloadGrid");
                            }
                        });
                        return "hhh";
                    }
                },
                {}
            );
        }
    });


    //父表格工具栏
    $("#albumTable").jqGrid('navGrid', '#albumDiv', {edit : true,add : true,del : true},
        {
            //执行修改后执行的方法
            //关闭对话框
            closeAfterAdd:true,
            afterSubmit:function (data) {
                $.ajaxFileUpload({
                    url:"${path}/album/bannerUpload",
                    datatype: "json",
                    type:"post",
                    fileElementId:"coverImg",
                    data:{id:data.responseText},
                    success:function () {
                        //刷新页面
                        $("#albumTable").trigger("reloadGrid");
                    }
                });
                return "hhh";
            }
        },
        {
            //执行添加之后执行方法
            //关闭对话框
            closeAfterAdd:true,
            afterSubmit:function (data) {
                $.ajaxFileUpload({
                    url:"${path}/album/bannerUpload",
                    datatype: "json",
                    type:"post",
                    fileElementId:"coverImg",
                    data:{id:data.responseText},
                    success:function () {
                        //刷新页面
                        $("#albumTable").trigger("reloadGrid");
                    }
                });
                return "hhh";
            }
        },
        {
            //执行删除后执行的方法
            //关闭对话框
            closeAfterAdd:true,
            afterSubmit:function (data) {
                if (data.responseText==''){

                    $("#myModalLabel").html("")
                    $("#myModal").modal("show");
                    $("#myAlbumDiv").attr("class","modal-body text-center alert alert-danger")
                    $("#myAlbumDiv").html(
                        '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> ' +
                        '<span class="sr-only">错误:</span>您的专辑里有音频不能删除！！');
                }
                return "hhh";
            }
        }
    )
})




    //音频下载和在线播放
    function chapterOperation(src,title) {

        $("#myModalLabel").html(title)
        $("#myModal").modal("show");
        $("#myAlbumDiv").attr("class","modal-body text-center")
        $("#myAlbumDiv").html('<audio id="myAubio" src="${path}/album/chapter/'+src+'" controls/>')
    }

    
    
    
</script>







<%--带标题的面版--%>
<div class="panel panel-warning">
    <%--标题--%>
    <div class="panel-heading">
        <h3>专辑信息</h3>
    </div>
    <%--内容--%>
    <div class="panel-body">
        <%--标签页--%>
        <div>
            <!-- 导航选项卡 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息</a></li>
            </ul>
            <!-- 选项卡窗格 -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <%--表格--%>
                    <table id="albumTable"></table>
                    <%--工具栏--%>
                    <div id="albumDiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content" id="modalContent">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <div class="modal-body text-center" id="myAlbumDiv">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

