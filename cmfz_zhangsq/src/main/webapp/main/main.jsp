<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/user/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- 表头 -->
            <div class="navbar-header">
                <a class="navbar-brand">持名法州管理系统</a>
            </div>

            <!-- 表尾 -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${sessionScope.AdminLogin!=null}">
                    <li><a href="#">欢迎:<span style="color: #0073ea"> ${sessionScope.AdminLogin.username}</span><span class="glyphicon glyphicon-user"/></a></li>
                    <li class="dropdown">
                        <a href="${path}/admin/exit"  role="button" >退出登录 <span class="glyphicon glyphicon-log-out"></span></a>
                    </li>
                    </c:if>
                    <c:if test="${sessionScope.AdminLogin==null}">
                       <li class="dropdown">
                            <a href="${path}/admin/exit"  role="button" >登录 <span class="glyphicon glyphicon-log-in">      </span></a>
                        </li>
                       <%--
                        <script>
                            location.href="${path}/login/login.jsp"
                        </script>
                        --%>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>




    <!--栅格系统-->
    <div class="col-lg-2 text-center">
        <!--左边手风琴部分-->
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

            <!--第一个手风琴-->
            <div class="panel panel-info">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                           用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <a class="btn btn-info" href="javascript:$('#mainID').load('${path}/user/user.jsp')" role="button">用户信息</a><br><br>
                        <a class="btn btn-info" href="javascript:$('#mainID').load('${path}/user/userCount.jsp')" role="button">用户统计</a><br><br>
                        <a class="btn btn-info" href="javascript:$('#mainID').load('${path}/user/userDistribution.jsp')" role="button">用户分布</a>
                    </div>
                </div>
            </div>

            <hr>

            <!--第二个手风琴-->
            <div class="panel panel-success">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <a class="btn btn-success"  href="javascript:$('#mainID').load('${path}/banner/banner.jsp')" role="button">轮播图信息</a>
                    </div>
                </div>
            </div>

            <hr>

            <!--第三个手风琴-->
            <div class="panel panel-warning">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <a class="btn btn-warning"  href="javascript:$('#mainID').load('${path}/album/album.jsp')" role="button">专辑信息</a>
                    </div>
                </div>
            </div>

            <hr>

            <!--第四个手风琴-->
            <div class="panel panel-danger">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <div class="panel-body">
                        <a class="btn btn-danger"  href="javascript:$('#mainID').load('${path}/article/article.jsp')" role="button">文章信息</a>
                    </div>
                </div>
            </div>

            <hr>

            <!--第五个手风琴-->
            <div class="panel panel-primary">
                <div class="panel-heading" role="tab" id="headingFive">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                            上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <div class="panel-body">
                        5
                    </div>
                </div>
            </div>

            <hr>

            <!--第六个手风琴-->
            <div class="panel panel-info">
                <div class="panel-heading" role="tab" id="headingSix">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                            上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                    <div class="panel-body">
                        5
                    </div>
                </div>
            </div>

        </div>


    </div>
    <div class="col-lg-10" id="mainID">
        <!--巨幕开始-->
        <div class="jumbotron">
            <c:if test="${sessionScope.AdminLogin!=null}">
                <h4>&emsp;&emsp;欢迎来到持名法州后台管理系统</h4>
            </c:if>
            <c:if test="${sessionScope.AdminLogin==null}">
                <h4 style="color: #d83030">&emsp;&emsp;您还没有登录</h4>
            </c:if>

        </div>
        <!--右边轮播图部分-->
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox" style=""  align="center">
                <div class="item active">
                    <img src="${pageContext.request.contextPath}/bootstrap/img/shouye.jpg"  alt="...">
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/bootstrap/img/2.png" alt="...">
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/bootstrap/img/3.png" alt="...">
                </div>
                <div class="item">
                    <img src="${pageContext.request.contextPath}/bootstrap/img/1.png" alt="...">
                </div>
            </div>

            <!-- 轮播图翻页 -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!--页脚-->
        <nav class="navbar navbar-default">
            <div class="container text-center">
                <h5>@百知教育  zhangsq@163.com</h5>
            </div>
        </nav>


    </div>
</body>
</html>
