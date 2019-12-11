<%@ page pageEncoding="utf-8" contentType="text/html; utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <script type="text/javascript" src="${path}/user/js/goEasy.js"></script>

    <!-- 引入 echarts.js -->
    <script src="${path}/user/js/echarts.js"></script>
    <script src="${path}/user/js/china.js"></script>
    <script type="text/javascript">

        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-ed5b2995279f4d0a82d4a8d201004296", //替换为您的应用appkey
        });

        goEasy.subscribe({
            channel: "userCount", //替换为您自己的channel
            onMessage: function (message) {
                console.log("Channel:" + message.channel + " content:" + message.content);
                var data = JSON.parse(message.content);
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('userCountMain'));
                // 指定图表的配置项和数据
                option = {
                    title : {
                        text: data.year+'年用户注册图',
                        subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['男','女']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'女',
                            type:'bar',
                            data:data.woman
                        },
                        {
                            name:'男',
                            type:'bar',
                            data:data.man
                        }

                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        });

    </script>


    <script>
        $(function () {
            $.ajax({
                url:"${path}/user/userCount",
                type:"post",
                dataType:"json",
                success:function (data) {

                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('userCountMain'));
                    // 指定图表的配置项和数据
                    option = {
                        title : {
                            text: data.year+'年用户注册图',
                            subtext: '纯属虚构'
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['男','女']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'女',
                                type:'bar',
                                data:data.woman
                            },
                            {
                                name:'男',
                                type:'bar',
                                data:data.man
                            }

                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })

            $("#userCountButton").click(function () {
                $.ajax({
                    url:"${path}/user/userCount",
                    type:"post",
                    data:$("#userCountForm").serialize(),
                    dataType:"json",
                    success:function (data) {

                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('userCountMain'));
                        // 指定图表的配置项和数据
                        option = {
                            title : {
                                text: data.year+'年用户注册图',
                                subtext: '纯属虚构'
                            },
                            tooltip : {
                                trigger: 'axis'
                            },
                            legend: {
                                data:['男','女']
                            },
                            toolbox: {
                                show : true,
                                feature : {
                                    dataView : {show: true, readOnly: false},
                                    magicType : {show: true, type: ['line', 'bar']},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            calculable : true,
                            xAxis : [
                                {
                                    type : 'category',
                                    data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                                }
                            ],
                            yAxis : [
                                {
                                    type : 'value'
                                }
                            ],
                            series : [
                                {
                                    name:'女',
                                    type:'bar',
                                    data:data.woman
                                },
                                {
                                    name:'男',
                                    type:'bar',
                                    data:data.man
                                }

                            ]
                        };
                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    }
                 })
             })
        })
    </script>
</head>
<body>
<div>
    <form class="form-inline" id="userCountForm">
        <div class="form-group">
            <label for="exampleInputName2">选择展示年份：</label>
            <input type="number" class="form-control" id="exampleInputName2" name="year" placeholder="输入您要查询的年份">
        </div>
        <button type="button" class="btn btn-info" id="userCountButton">确定</button>
    </form>
</div>

<br>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="userCountMain" style="width: 600px;height:400px;"></div>



</body>
</html>