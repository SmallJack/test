<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link href="/js/plugins/fancyBox/jquery.fancybox.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/system/commonAll.js"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript"
	src="/js/plugins/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript"
	src="/js/plugins/fancyBox/jquery.fancybox.pack.js"></script>
<script type="text/javascript"
	src="/js/plugins/fancyBox/jquery.fancybox-buttons.js"></script>

<script type="text/javascript">
	$(function() {
		$(".fc").fancybox();

		$(".btn_select").click(function() {
			var json = $(this).data("json");
			$.dialog.data("jsonString", json);
			$.dialog.close();
		})
	})
</script>
<title>PSS-product管理</title>
<style>
.alt td {
	background: black !important;
}
</style>
</head>
<body>
	<!-- 消息提示 -->
	<jsp:include page="/commons/commons_msg.jsp"></jsp:include>
	<s:form id="searchForm" action="product_selectProductList" namespace="/" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							编码/名称
							<s:textfield name="qo.keyword" class="ui_input_txt02" />
							品牌
							<s:select list="#brands" listKey="id" listValue="name"
								name="qo.brandId" headerKey="-1" headerValue="全部"
								class="ui_select01" />
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page"
								data-page="1" />
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
							<th>货品图片</th>
							<th>货品名称</th>
							<th>货品编码</th>
							<th>品牌</th>
							<th>成本价格</th>
							<th>销售价格</th>
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
										<a class="fc" href="<s:property value="imagePath"/>"
											title="<s:property value="name" />"
											data-fancybox-group="button">
											<img src="<s:property value="smallImagePath" />"
												class="list_img">
										</a>

									</td>

									<td>
										<s:property value="name" />
									</td>
									<td>
										<s:property value="sn" />
									</td>
									<td>
										<s:property value="brand.name" />
									</td>
									<td>
										<s:property value="costPrice" />
									</td>
									<td>
										<s:property value="salePrice" />
									</td>

									<td>
										<input type="button" value="选择该商品" class="btn_select"
											data-json='<s:property value="jsonString" escape="false"/>'>
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
