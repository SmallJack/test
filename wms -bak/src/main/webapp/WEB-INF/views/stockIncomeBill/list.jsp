<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript"
	src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>

<script type="text/javascript">
	$(function() {
		$(".btn_selectTime").click(function() {
			WdatePicker();
		})
	})
</script>
<title>PSS-stockIncomeBill管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="stockIncomeBill" namespace="/"
		method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							业务时间

							<s:textfield type="text" name="qo.beginTime"
								id="searchForm_qo_beginDate"
								class="ui_input_txt02 btn_selectTime" />
							~

							<s:textfield type="text" name="qo.endTime"
								id="searchForm_qo_endDate" class="ui_input_txt02 btn_selectTime" />
							仓库
							<s:select name="qo.depotId" list="#depots" listKey="id"
								listValue="name" headerKey="-1" headerValue="所有仓库"
								id="searchForm_qo_depotId" class="ui_select01" />


							状态
							<s:select name="qo.status" list="#{0:'未审核',1:'已审核'}"
								id="searchForm_qo_status" class="ui_select01" headerKey="-1"
								headerValue="所有状态" />

						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"
								data-page="1" />
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								data-url="<s:url namespace="/" action="stockIncomeBill_input"/>" />
						</div>
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
							<th>编号</th>
							<th>业务时间</th>
							<th>仓库</th>
							<th>入库总数量</th>
							<th>入库总金额</th>
							<th>录入人</th>
							<th>录入时间</th>
							<th>审核人</th>
							<th>审核时间</th>
							<th>审核状态</th>
							<th>操作</th>
						</tr>
						<tbody>
							<s:iterator value="#result.data">
								<tr class="data_tr">
									<td>
										<input type="checkbox" name="IDCheck" class="acb"
											data-id="<s:property value="id"/>" />
									</td>
									<td>
										<s:property value="sn" />
									</td>
									<td>
										<s:property value="vdate" />
									</td>
									<td>
										<s:property value="depot.name" />
									</td>
									<td>
										<s:property value="totalNumber" />
									</td>
									<td>
										<s:property value="totalAmount" />
									</td>

									<td>
										<s:property value="inputUser.name" />
									</td>
									<td>
										<s:property value="inputTime" />
									</td>
									<td>
										<s:property value="auditor.name" />
									</td>
									<td>
										<s:property value="auditTime" />
									</td>

									<td>
										<s:if test="status == 0">
											<span style="color: green">未审核</span>
										</s:if>
										<s:else>
											<span style="color: red">已审核</span>
										</s:else>
									</td>

									<s:if test="status ==0">
										<td>
											<s:url namespace="" action="stockIncomeBill_audit"
												var="auditUrl">
												<s:param name="stockIncomeBill.id" value="id"></s:param>
											</s:url>
											<a href="javascript:;" class="btn_audit"
												data-url="<s:property value="#auditUrl"/>"> 审核 </a>
											<s:a namespace="" action="stockIncomeBill_input">
												<s:param name="stockIncomeBill.id" value="id"></s:param>
                                    		编辑
                                    	</s:a>
											<s:url namespace="/" action="stockIncomeBill_delete"
												var="deleteUrl">
												<s:param name="stockIncomeBill.id" value="id"></s:param>
											</s:url>
											<a href="javascript:;" class="btn_delete"
												data-url="<s:property value="#deleteUrl"/>"> 删除 </a>
										</td>
									</s:if>
									<s:else>
										<td>
											<s:a namespace="/" action="stockIncomeBill_show">
												<s:param name="stockIncomeBill.id" value="id"></s:param>
                                    		查看
                                    	</s:a>

										</td>
									</s:else>
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
