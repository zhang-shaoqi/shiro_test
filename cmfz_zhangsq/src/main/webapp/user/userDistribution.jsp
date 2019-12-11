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
            host:'hangzhou.goeasy.io',
            appkey:"BC-ed5b2995279f4d0a82d4a8d201004296"
        });

        goEasy.subscribe({
            channel: "userDistribution", //替换为您自己的channel
            onMessage: function (message) {
                var data = JSON.parse(message.content);
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('userDistributionMain'));

                // 指定图表的配置项和数据
                option = {
                    title : {
                        text: '用户分布图',
                        subtext: '纯属虚构',
                        left: 'center'
                    },
                    tooltip : {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data:['女','男']
                    },
                    visualMap: {
                        min: 0,
                        max: 100,
                        left: 'left',
                        top: 'bottom',
                        text:['高','低'],           // 文本，默认为数值文本
                        calculable : true
                    },
                    toolbox: {
                        show: true,
                        orient : 'vertical',
                        left: 'right',
                        top: 'center',
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    series : [
                        {
                            name: '女',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:data.woman
                        },
                        {
                            name: '男',
                            type: 'map',
                            mapType: 'china',
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:data.mans
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
                url:"${path}/user/userDistribution",
                type:"post",
                dataType:"json",
                success:function (data) {
                    $(function(){

                        // 基于准备好的dom，初始化echarts实例
                        var myChart = echarts.init(document.getElementById('userDistributionMain'));

                        // 指定图表的配置项和数据
                        option = {
                            title : {
                                text: '用户分布图',
                                subtext: '纯属虚构',
                                left: 'center'
                            },
                            tooltip : {
                                trigger: 'item'
                            },
                            legend: {
                                orient: 'vertical',
                                left: 'left',
                                data:['女','男']
                            },
                            visualMap: {
                                min: 0,
                                max: 100,
                                left: 'left',
                                top: 'bottom',
                                text:['高','低'],           // 文本，默认为数值文本
                                calculable : true
                            },
                            toolbox: {
                                show: true,
                                orient : 'vertical',
                                left: 'right',
                                top: 'center',
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            series : [
                                {
                                    name: '女',
                                    type: 'map',
                                    mapType: 'china',
                                    roam: false,
                                    label: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:data.woman
                                },
                                {
                                    name: '男',
                                    type: 'map',
                                    mapType: 'china',
                                    label: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:data.mans
                                }
                            ]
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);

                    });
                }
            })
        })
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="userDistributionMain" style="width: 600px;height:400px;"></div>

</body>
</html>