<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>
<title>PSS-角色管理</title>
<style>
.alt td {
	background: black !important;
}
</style>

</head>
<body>
	<s:form id="searchForm" namespace="/" action="role">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>

						<div id="box_bottom">
							<input type="button" value="新增"
								data-url="<s:url namespace="/" action="role_input"/>"
								class="ui_input_btn01 btn_input" />
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
							<th>名称</th>
							<th>编码</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#result.data">
								<tr class="data_tr">
									<td>
										<input type="checkbox" name="IDCheck" class="acb"
											data-id="<s:property value="id" />" />
									</td>
									<td>
										<s:property value="id" />
									</td>
									<td>
										<s:property value="name" />
									</td>
									<td>
										<s:property value="sn" />
									</td>
									<td>
										<s:a namespace="/" action="role_input">编辑
											<s:param name="r.id" value="id"></s:param>
										</s:a>
										<s:url namespace="/" action="role_delete"
											var="deleteURL">
											<s:param name="r.id" value="id"></s:param>
										</s:url>
										<a href="javascript:;" data-id="<s:property value="id" />"
											data-url="<s:property value="deleteURL" />"
											class="btn_delete">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<!-- 分页条 -->
				<jsp:include page="/commons/commons_page.jsp"></jsp:include>
				<!-- 消息提示 -->
				<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
			</div>
		</div>
	</s:form>

</body>
</html>
