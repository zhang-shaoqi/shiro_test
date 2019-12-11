<%@ page pageEncoding="utf-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@ page import="java.lang.*" %>
<script>
    $(function(){
        $("#bannerTable").jqGrid(
            {
                url : '${path}/banner/selectBannerByPage',
                datatype : "json",
                viewrecords:true,
                rowNum : 3,
                rowList : [ 3,5,10, 20, 30 ],
                pager : '#bannerDiv',
                styleUI:"Bootstrap",
                autowidth:true,
                height:"auto",
                editurl:"${path}/banner/compileBanner",
                colNames : [ 'id', '路径', '状态', '上传时间','描述'],
                colModel : [
                    {name : 'id',index : 'id',width : 55,align:"center"},
                    {name : 'srcImg',editable:true,index : 'invdate',width : 90,align:"center",edittype:"file",
                        formatter:function(cellvalue, options, rowObject){
                            return "<img src='${path}/banner/img/"+cellvalue+"' style='height:80px ;width:150px ;' />"
                        }
                    },
                    {name : 'status',index : 'amount',width : 80,align : "center",edittype:"select",editoptions: {value:"yes:展示;no:不展示"},editable:true,
                        formatter:function(cellvalue, options, rowObject){
                            if (cellvalue=="yes"){
                                return "<button class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>已展示</button>"
                            }else {
                                return "<button class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>未展示</button>"
                            }
                        }
                    },
                    {name : 'uploadTime',index : 'tax',width : 80,align : "center"},
                    {name : 'description',index : 'total',width : 80,align : "center",editable:true}
                ]
            });
        $("#bannerTable").jqGrid('navGrid', '#bannerDiv', {edit : true,add : true,del : true},
            {
                //执行修改后执行的方法
                //关闭对话框
                closeAfterAdd:true,
                afterSubmit:function (data) {
                    $.ajaxFileUpload({
                        url:"${path}/banner/bannerUpload",
                        datatype: "json",
                        type:"post",
                        fileElementId:"srcImg",
                        data:{id:data.responseText},
                        success:function () {
                            //刷新页面
                            $("#bannerTable").trigger("reloadGrid");
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
                         url:"${path}/banner/bannerUpload",
                         datatype: "json",
                         type:"post",
                         fileElementId:"srcImg",
                         data:{id:data.responseText},
                         success:function () {
                             //刷新页面
                             $("#bannerTable").trigger("reloadGrid");
                         }
                     });
                     return "hhh";
                }
            },
            {
                //执行删除后执行的方法
            }
        );
    });















    /*修改轮播图状态*/
    function updateStatus(id,status) {
        $.ajax({
            url:"${path}/banner/updateStatus",
            type:"post",
            data:{id:id,status:status},
            success:function () {
                $("#bannerTable").trigger("reloadGrid")
            }
        })
    }



</script>




<%--带标题的面版--%>
<div class="panel panel-success">
    <%--标题--%>
    <div class="panel-heading">
        <h3>轮播图信息</h3>
    </div>
    <%--内容--%>
    <div class="panel-body">
        <%--标签页--%>
        <div>
            <!-- 导航选项卡 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图信息</a></li>
            </ul>
            <!-- 选项卡窗格 -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <%--表格--%>
                    <table id="bannerTable"></table>
                    <div id="bannerDiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>