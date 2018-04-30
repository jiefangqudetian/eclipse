<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-静态数据演示</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
	<!-- 头部 -->
    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->
	<!-- 左边栏 -->
    <jsp:include page="../include/sider.jsp">
        <jsp:param name="param" value="charts_demo"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">柱状图</h3>
                </div>
                <div class="box-body">
                    <div id="bar" style="height: 300px;width: 100%"></div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">饼图</h3>
                        </div>
                        <div class="box-body">
                            <div id="pie" style="height: 300px;width: 100%"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">折线图</h3>
                        </div>
                        <div class="box-body">
                            <div id="line" style="height: 300px;width: 100%"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">多数据柱状图</h3>
                        </div>
                        <div class="box-body">
                            <div id="bar2" style="height: 300px;width: 100%"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">折线图 + 柱状图</h3>
                        </div>
                        <div class="box-body">
                            <div id="lineBar" style="height: 300px;width: 100%"></div>
                        </div>
                    </div>
                </div>
            </div>

			<div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">漏斗图</h3>
                </div>
                <div class="box-body">
                    <div id="funnelChart" style="height: 300px;width: 70%"></div>
                </div>
            </div>



        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
	<!-- 尾部 -->
    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->
<!-- javascript -->
<%@include file="../include/js.jsp"%>
<script src="/static/plugins/echarts/echarts.min.js"></script>
<script>
$(function () {
    var bar = echarts.init(document.getElementById("bar"));
    var pie = echarts.init($("#pie")[0]);
    var line = echarts.init($("#line")[0]);
    var bar2 = echarts.init($("#bar2")[0]);
    var lineBar = echarts.init($("#lineBar")[0]);
	var funnel = echarts.init($("#funnelChart")[0]);

    bar.setOption({
        title: {
            text: "2017\n上半年手机销售量统计图",
            left: 'center'
        },
        tooltip: {},
        legend: {
            data: ['销量'],
            left: 'right'
        },
        xAxis: {
            type: 'category',
            data: ['OPPO', "VIVO", "三星", "小米", "华为", "苹果"]
        },
        yAxis: {},
        series: {
            name: "销量",
            type: 'bar',
            data: [1000, 950, 600, 800, 750, 700]
        }
    });

     pie.setOption({
           title: {
               text: "网站访问来源统计"
           },
           series: {
               name: "销量",
               type: 'pie',
               data: [
                   {value:335, name:'直接访问'},
                   {value:310, name:'邮件营销'},
                   {value:274, name:'联盟广告'},
                   {value:235, name:'视频广告'},
                   {value:800, name:'搜索引擎'},
                   {value:1800, name:'朋友介绍'}
               ]
           }
       });

        line.setOption({
          title: {
              text: "2017上半年手机销售量统计图",
              left: 'center'
          },
          tooltip: {},
          legend: {
              data: ['销量'],
              left: 'right'
          },
          xAxis: {
              type: 'category',
              data: ['OPPO', "VIVO", "三星", "小米", "华为", "苹果"]
          },
          yAxis: {},
          series: {
              name: "销量",
              type: 'line',
              data: [1000, 950, 600, 800, 750, 700]
          }
      });

      bar2.setOption({
          title: {
              text: "2017上半年手机销售量统计图",
              left: 'left'
          },
          tooltip: {},
          legend: {
              data: ['苹果','三星',"Vivo","小米"],
              left: 'right'
          },
          xAxis: {
              type: 'category',
              data: ['1月', "2月", "3月", "4月", "5月", "6月"]
          },
          yAxis: {},
          series: [
              {
                  name: "苹果",
                  type: 'bar',
                  data: [1000, 950, 600, 800, 750, 700]
              },
              {
                  name: "三星",
                  type: 'bar',
                  data: [600, 1200, 500, 900, 250, 3000]
              },
              {
                  name: "ViVO",
                  type: 'line',
                  data: [300, 650, 100, 600, 750, 900]
              },
              {
                  name: "小米",
                  type: 'line',
                  data: [400, 550, 900, 1600, 550, 1900]
              }
          ]
      });

    lineBar.setOption({
         title: {
             text: "2017上半年手机销售量统计图",
             left: 'center'
         },
         tooltip: {},
         legend: {
             data: ['销量','总占有率'],
             left: 'right'
         },
         xAxis: {
             type: 'category',
             data: ['OPPO', "VIVO", "三星", "小米", "华为", "苹果"]
         },
         yAxis: {},
         series: [
             {
                 name: "销量",
                 type: 'bar',
                 data: [1000, 950, 600, 800, 750, 700]
             },
             {
                 name: "总占有率",
                 type: 'line',
                 data: [100, 250, 800, 200, 350, 500]
             }
         ]
     });
    
    funnel.setOption({
        title: {
            text: '漏斗图',
            subtext: '销售机会进度'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}%"
        },
        toolbox: {
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        legend: {
            data: ['初访','意向','报价','成交','暂时搁置']
        },
        calculable: true,
        series: [
            {
                name:'漏斗图',
                type:'funnel',
                left: '10%',
                top: 60,
                //x2: 80,
                bottom: 60,
                width: '80%',
                // height: {totalHeight} - y - y2,
                min: 0,
                max: 100,
                minSize: '0%',
                maxSize: '100%',
                sort: 'descending',
                gap: 2,
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    },
                    emphasis: {
                        textStyle: {
                            fontSize: 20
                        }
                    }
                },
                labelLine: {
                    normal: {
                        length: 10,
                        lineStyle: {
                            width: 1,
                            type: 'solid'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        borderColor: '#fff',
                        borderWidth: 1
                    }
                },
                data: [
                    {value: 20, name: '初访'},
                    {value: 40, name: '意向'},
                    {value: 60, name: '报价'},
                    {value: 808, name: '成交'},
                    {value: 1000, name: '暂时搁置'}
                ]
            }
        ]
    });
	$.get("/charts/process/count",function(data){
		if(data.state == 'success'){
			var dataArray = [];
			
			var array = data.data;
			for(var i = 0; i < array.length; i++){
				var obj = array[i];
				var message = {value:obj.count,name:obj.process};
				dataArray.push(message);
			}
			funnel.setOption({
			     series: [
			            {
			            	data: dataArray
			            }
			        ]
			})
		}
	});

});
</script>
</body>
</html>