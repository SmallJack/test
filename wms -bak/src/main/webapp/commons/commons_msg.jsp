<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- 当有异常信息的时候,执行下面的代码 -->
<s:if test="hasActionErrors()">
	<script type="text/javascript">
		var msg = '<s:property value='errorMessages[0]'/>';
		art.dialog({
			title : '温馨提示',
			content : msg,
			ok : true
		});
	</script>
</s:if>
<s:if test="hasActionMessages()">
	<script type="text/javascript">
		var msg = "<s:property value='actionMessages[0]'/>";
		art.dialog({
			title : '温馨提示',
			content : msg,
			ok : true
		});
	</script>
</s:if>