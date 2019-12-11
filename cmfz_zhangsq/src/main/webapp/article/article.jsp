<%@ page pageEncoding="utf-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>

<script type="application/javascript">
    //配置KindEditor 初始化参数
    KindEditor.create('#editor_id', {
        filePostName:"imgFile",//设置上传图片的名称
        uploadJson:"${path}/article/articleUpload",  //指定上传图片的路径
        allowFileManager: true, //是否展示浏览图片空间
        width :'598px',// 宽度            可以设置px或%，比textarea输入框样式表宽度优先度高。数据类型: String默认值: textarea输入框的宽度
        height:'400px',//高度，           只能设置px，比textarea输入框样式表高度优先度高。数据类型: String默认值: textarea输入框的高度
        minWidth:598,//最小宽度，         单位为px。数据类型: Int 默认值: 650
        minHeight:100,//最小高度，       单位为px。数据类型: Int 默认值: 100
        resizeType:1,//是否可以拖动       2或1或0，2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动。
        fileManagerJson:'${path}/article/uploadInterspace',
        afterBlur:function () {  //在kindeditor失去焦点之后执行的内容
            this.sync();  //将kindeditor中的内容同步到表单中
        }
    });
</script>
<script>
    $(function(){
        $("#articleTable").jqGrid(
            {
                url : '${path}/article/selectArticleByPage',
                datatype : "json", //前台接收后台的格式
                autowidth:true, //自动适应宽度
                height:"auto",  //自动适应高度
                rowNum : 3, //初始没有显示条数
                styleUI:"Bootstrap",//设置样式风格
                rowList : [ 3,8, 10, 20, 30 ],     //可选每页显示条数
                pager : "#articleDiv",    //工具栏
                viewrecords : true, //定义是否要显示总记录数
                editurl:"${path}/article/compileArticle",//增删改路径
                colNames : [ 'id','封面', '标题','文章','创建时间','状态','上师id','操作'],
                colModel : [
                    {name : 'id',width : 55,align:"center"},
                    {name : 'cover',width : 60,align:"center",editable:true,
                        formatter:function(cellvalue, options, rowObject){
                            return "<img src='${path}/article/img/"+cellvalue+"' style='height:80px ;width:150px ;' />"
                        }
                    },
                    {name : 'title',width : 55,align:"center",editable:true},
                    {name : 'comtent',width : 55,align:"center",editable:true},
                    {name : 'uploadTime',width : 55,align:"center"},
                    {name : 'status',width : 55,align:"center",editable:true,edittype:"select",editoptions: {value:"yes:正常;no:冻结"}},
                    {name : 'guru_id',width : 55,align:"center"},
                    {name : 'operation',width : 50,align:"center"}
                ]
            });
        $("#articleTable").jqGrid('navGrid', '#articleDiv', {edit : true,add : true,del : true},
            {
                //执行修改后执行的方法
                //关闭对话框
                closeAfterAdd:true,
                afterSubmit:function (data) {
                    $.ajaxFileUpload({
                        url:"${path}/article/articleUpload",
                        datatype: "json",
                        type:"post",
                        fileElementId:"srcImg",
                        data:{id:data.responseText},
                        success:function () {
                            //刷新页面
                            $("#articleTable").trigger("reloadGrid");
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
                        url:"${path}/article/articleUpload",
                        datatype: "json",
                        type:"post",
                        fileElementId:"srcImg",
                        data:{id:data.responseText},
                        success:function () {
                            //刷新页面
                            $("#articleTable").trigger("reloadGrid");
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








    <!--展示文章信息-->
    $("#articleSelect").click(function () {
        //getGridParam:返回请求的参数信息
        //selrow:只读属性，最后选择行的id
        var rovId = $("#articleTable").jqGrid("getGridParam","selrow");
        if(rovId!=null){
            var rov = $("#articleTable").jqGrid("getRowData",rovId);
            $("#inlineStatus1").html(rov.status)
            $("#inputTitle").val(rov.title);
            KindEditor.html("#editor_id",rov.comtent);
            $("#articleButton").html("<button type='button' class='btn btn-primary' onclick='editarticleButton(\""+rovId+"\")'>修改</button>\n<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>")
            //展示模态框
            $("#myModal").modal("show");
        }else {
            //提示选择一行
            $("#myModal2").modal("show");
        }
    });




    <!--删除文章信息-->
    $("#articleDel").click(function () {
        $("#articleFile").html("")
        //getGridParam:返回请求的参数信息    selrow:只读属性，最后选择行的id
        var rovId = $("#articleTable").jqGrid("getGridParam","selrow");
        if(rovId!=null){
            var rov = $("#articleTable").jqGrid("getRowData",rovId);
            $.ajax({
                url:"${path}/article/compileArticle",
                type:"post",
                dataType:"text",
                data:{"id":rov.id,"oper":"del"},
                success:function (data) {
                    //关闭模态框
                    $("#myModal").modal("hide");
                    //刷新页面
                    $("#articleTable").trigger("reloadGrid")
                    return "hhh";
                }
            })
        }else {
            //提示选择一行
            $("#myModal2").modal("show");
        }
    });






    <!--添加文章按钮 展示模态框-->
    $("#articleAdd").click(function () {
        //清空表单
        $("#articleForm")[0].reset();
        //清空kindeditor
        KindEditor.html("#editor_id","");
        //展示模态框
        $("#myModal").modal("show");
        // 显示的按钮
        $("#articleButton").html("<button type='button' class='btn btn-primary'  onclick='addarticleButton()'>提交</button>\n" +
            "                    <button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>")
    });


    <!--添加文章-->
    function addarticleButton(){
        $.ajax({
            url:"${path}/article/compileArticle?oper=add",
            type:"post",
            dataType:"text",
            data:$("#articleForm").serialize(),
            success:function (data) {
                //关闭模态框
                $("#myModal").modal("hide");
                //刷新页面
                $("#articleTable").trigger("reloadGrid")
                return "hhh";
            }
        })
    }

    <!--修改文章-->
    function editarticleButton(id){
        $.ajax({
            url:"${path}/article/compileArticle?oper=edit&id="+id,
            type:"post",
            dataType:"text",
            data:$("#articleForm").serialize(),
            success:function (data) {
                //关闭模态框
                $("#myModal").modal("hide");
                //刷新页面
                $("#articleTable").trigger("reloadGrid")
                return "hhh";
            }
        })
    }


</script>


<%--带标题的面版--%>
<div class="panel panel-danger">
    <%--标题--%>
    <div class="panel-heading">
        <h3>文章信息</h3>
    </div>
    <%--内容--%>
    <div class="panel-body">


        <%--标签页--%>
        <div>
            <br>
            <!-- 导航选项卡 -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章信息</a></li>
            </ul>



            <!-- 选项卡窗格 -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <br>
                    <div>
                        <button id="articleSelect" class="btn btn-info" type="submit">文章信息</button>&emsp;
                        <button id="articleAdd" class="btn btn-success" type="submit">添加文章</button>&emsp;
                        <button id="articleDel" class="btn btn-warning" type="submit">删除文章</button>&emsp;
                    </div>
                    <br>
                    <%--表格--%>
                    <table id="articleTable"></table>
                    <div id="articleDiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">文章内容</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" id="articleForm">
                    <div class="form-group">
                        <label for="inputFile" class="col-sm-2 control-label">封面</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" id="inputFile" name="inputFile" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputTitle" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputTitle" placeholder="标题" name="title">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-10 col-lg-offset-2">
                            <label class="radio-inline">
                                <input type="radio" name="status" id="inlineStatus1" value="yes"> 展示
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" id="inlineStatus2" value="no"> 不展示
                            </label>
                        </div>
                    </div>


                    <div class="form-group">
                        <!--kindEditor编辑器的主体-->
                        <textarea id="editor_id" name="comtent"/>
                    </div>


                    <div class="modal-footer">
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10" id="articleButton">
                            </div>
                        </div>
                    </div>




                </form>
            </div>
        </div>
    </div>
</div>



<!--判断是否选中一行-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <!--模态框内容-->
            <div class="modal-body">
                <h3>请选择一行<span style="color: #bce8f1">  ε=ε=ε=(~￣▽￣)~</span></h3>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


