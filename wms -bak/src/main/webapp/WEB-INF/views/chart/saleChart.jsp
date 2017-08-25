<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>
<script type="text/javascript"
	src="/js/plugins/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(function() {
		$(".btn_selectTime").click(function() {
			WdatePicker();
		})
		
		//显示柱状图
		$(".btn_bar").click(function(){
			var param = $("#searchForm").serialize();
			var url = "/chart_saleChartByBar.action?"+param;
			$.dialog.open(url, {
				id : 'ajxxList',
				title : '销售报表柱状图',
				width : 800,
				height : 600,
				close : function(){
					}
				});
		});
		
		
		//显示饼状图
		$(".btn_pie").click(function(){
			var param = $("#searchForm").serialize();
			var url = "/chart_saleChartByPie.action?"+param;
			$.dialog.open(url, {
				id : 'ajxxList',
				title : '销售报表柱状图',
				width : 800,
				height : 600,
				close : function(){
					}
				});
		});
	});
		

</script>
<title>PSS-采购订单管理管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="chart_saleChart" namespace="/"
		method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间

							<s:textfield type="text" name="sqo.beginDate"
								id="searchForm_sqo_beginDate"
								class="ui_input_txt02 btn_selectTime" />
							~

							<s:textfield type="text" name="sqo.endDate"
								id="searchForm_sqo_endDate"
								class="ui_input_txt02 btn_selectTime" />

							货品
							<s:textfield type="text" name="sqo.keyword"
								id="searchForm_sqo_endDate" class="ui_input_txt02" />
							客户
							<s:select name="sqo.clientId" list="#clients" listKey="id"
								listValue="name" headerKey="-1" headerValue="所有客户"
								id="searchForm_sqo_clientId" class="ui_select01" />
							品牌
							<s:select name="sqo.brandId" list="#brands" listKey="id"
								listValue="name" headerKey="-1" headerValue="所有品牌"
								id="searchForm_sqo_brandId" class="ui_select01" />
							分组
							<s:select name="sqo.groupByType" list="#gruopTypes"
								class="ui_select01" />
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"
								data-page="1" />
							<input type="button" value="柱状图报表" class="ui_input_btn01 btn_bar" />
							<input type="button" value="饼图报表" class="ui_input_btn01 btn_pie"/>
						</div>
					</div>
				</div>
				<div class="ui_content">
					<div class="ui_tb">
						<table class="table" cellspacing="0" cellpadding="0" width="100%"
							align="center" border="0">
							<tr>
								<th width="30">
									<input type="checkbox" id="all" />
								</th>
								<th>分组类型</th>
								<th>销售总数量</th>
								<th>销售总金额</th>
								<th>毛利润</th>
							</tr>
							<tbody>
								<s:iterator value="#listData">
									<tr class="data_tr">
										<td>
											<input type="checkbox" name="IDCheck" class="acb"
												data-id="<s:property value="id"/>" />
										</td>
										<td>
											<s:property value="groupByType" />
										</td>
										<td>
											<s:property value="totalNumber" />
										</td>
										<td>
											<s:property value="totalAmount" />
										</td>
										<td>
											<s:property value="grossProfit" />
										</td>

									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 分页条 -->
				<jsp:include page="/commons/commons_page.jsp"></jsp:include>
			</div>
	</s:form>
</body>
</html>
