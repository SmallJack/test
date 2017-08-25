<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>PSS-采购订单管理管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/echarts/echarts-all.js"></script>
<script type="text/javascript">
	$(function() {
		// 基于准备好的dom，初始化echarts图表
		var myChart = echarts.init(document.getElementById('main'));
		option = {
			title : {
				text : '销售报表',
				subtext : '<s:property value="#gruopType"/>',
				x : 'center'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '销售总量' ],
				x : 'left'
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data :  <s:property value="#gruopTypeList" escape="false"/>
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [
					{
						name : '销售量',
						type : 'bar',
						data :<s:property value="#totalAmountList" escape="false"/>,
						markPoint : {
							data : [ {
								type : 'max',
								name : '最大值'
							}, {
								type : 'min',
								name : '最小值'
							} ]
						},
						markLine : {
							data : [ {
								type : 'average',
								name : '平均值'
							} ]
						}
					} ]
		};

		// 为echarts对象加载数据 
		myChart.setOption(option);
	})
</script>

</style>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="height: 600px;width:800px"></div>
</body>
</html>
