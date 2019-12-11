<%@ page pageEncoding="utf-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){
        $("#userTable").jqGrid(
            {
                url : '${path}/user/selectUserByPage',
                datatype : "json", //前台接收后台的格式
                autowidth:true, //自动适应宽度
                height:"auto",  //自动适应高度
                rowNum : 3, //初始没有显示条数
                styleUI:"Bootstrap",//设置样式风格
                rowList : [ 3,8, 10, 20, 30 ],     //可选每页显示条数
                pager : "#userDiv",    //工具栏
                viewrecords : true, //定义是否要显示总记录数
                editurl:"${path}/user/compileUser",//增删改路径
                colNames : [ 'id', '头像','名字','昵称','密码','性别','状态','手机号','注册时间','地区'],
                colModel : [
                    {name : 'id',width : 55,align:"center"},
                    {name : 'picImg',width : 55,align:"center",editable:true,edittype:"file",
                        formatter:function(cellvalue, options, rowObject){
                            return '<img height="50" width="100" src="${path}/user/img/'+cellvalue+'"/>'
                        }
                    },
                    {name : 'name',width : 55,align:"center",editable:true},
                    {name : 'legalName',width : 55,align:"center",editable:true},
                    {name : 'password',width : 55,align:"center",editable:true},
                    {name : 'sex',width : 55,align:"center",editable:true,edittype:"select",editoptions: {value:"男:男;女:女"}},
                    {name : 'status',width : 50,align:"center"},
                    {name : 'phone',width : 50,align:"center",editable:true},
                    {name : 'regTime',width : 50,align:"center"},
                    {name : 'city',width : 50,align:"center",editable:true}
                ]
            });
        $("#userTable").jqGrid('navGrid', '#userDiv', {edit : true,add : true,del : true},
            {
                //执行修改后执行的方法
                //关闭对话框
                closeAfterAdd:true,
                afterSubmit:function (data) {
                    $.ajaxFileUpload({
                        url:"${path}/user/userUpload",
                        datatype: "json",
                        type:"post",
                        fileElementId:"picImg",
                        data:{id:data.responseText},
                        success:function () {
                            //刷新页面
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return "sdggasa";
                }
            },
            {
                //执行添加之后执行方法
                //关闭对话框
                closeAfterAdd:true,
                afterSubmit:function (data) {
                    $.ajaxFileUpload({
                        url:"${path}/user/userUpload",
                        datatype: "json",
                        type:"post",
                        fileElementId:"picImg",
                        data:{id:data.responseText},
                        success:function () {
                            //刷新页面
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return "sdfsfd";
                }
            },
            {
                //执行删除后执行的方法
            }
        );
    });



    /*用户导出功能*/
    $("#userExport").click(function () {
        $.ajax({
            url:"$ {path}/user/userExport",
            type:"post",
            dataType:"text",
            success:function (data) {
                alert(data)
            }
        })
    })












    /*    /!*修改轮播图状态*!/
        function updateStatus(id,status) {
            $.ajax({
                url:"$ {path}/user/updateStatus",
                type:"post",
                data:{id:id,status:status},
                success:function () {
                    $("#userTable").trigger("reloadGrid")
                }
            })
        }*/



</script>


<%--带标题的面版--%>
<div class="panel panel-info">
    <%--标题--%>
    <div class="panel-heading">
        <h3>文章信息</h3>
    </div>
    <%--内容--%>
    <div class="panel-body">
        <%--标签页--%>
        <div>
            <!-- 导航选项卡 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章信息</a></li>
            </ul>
            <!-- 选项卡窗格 -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">

                    <br>
                    <div>
                        <button id="userExport" class="btn btn-info" type="submit">导出用户信息</button>&emsp;
                        <button id="userImporting" class="btn btn-success" type="submit">导入用户信息</button>&emsp;
                    </div>
                    <br>

                    <%--表格--%>
                    <table id="userTable"></table>
                    <div id="userDiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>