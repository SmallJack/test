//来全局得禁用这个功能。
jQuery.ajaxSettings.traditional = true;

$(function() {
	// 在页面加载成功之后,将所有的复选框取消选中状态
	$(":checkbox").prop("checked", false);
	// 点击新增按钮跳转到编辑页面
	$(".btn_input").click(function() {
		window.location.href = $(this).data("url");
	});
	// 分页查询
	$(".btn_page").click(
			function() {
				// 获取到当前页的数据设置到表单中
				// var pageNo = $(this).attr("data-page");
				// 使用data("xxx")方法获取元素上面的data-xxx属性的值
				var pageNo = $(this).data("page")
						|| $("input[name='qo.currentPage']").val();
				$("input[name='qo.currentPage']").val(pageNo);
				// 提交表单
				$("#searchForm").submit();
			});
	$("select[name='qo.pageSize']").change(function() {
		$("input[name='qo.currentPage']").val(1);
		// 提交表单
		$("#searchForm").submit();
	});

	// 批量删除
	$(".btn_batchDelete").click(function() {
		var url = $(this).data("url");
		// 获取到选中的复选框
		var checkedBox = $(".acb:checked");
		// 获取到复选框对应的数据的id,存到数组中
		// 返回jQuery对象,需要从中取出转成对应的DOM对象
		var ids = checkedBox.map(function(index, item) {
			return $(item).data("id");
		}).get();
		if (ids.length == 0) {
			art.dialog({
				title : '温馨提示',
				content : '请选择要删除的数据',
				ok : true
			})
			return;
		}
		art.dialog({
			title : '温馨提示',
			content : '删除的数据不能恢复,确定要删除吗?',
			ok : function() {
				// 发送一个ajax请求将数据删除
				$.get(url, {
					ids : ids
				}, function(data) {
					// 执行成功给用户一个提示
					art.dialog({
						title : '温馨提示',
						content : data,
						ok : function() {
							// 删除成功,重新刷新一次页面
							window.location.reload();
						}
					});
				})
			},
			cancel : true,
			okVal : '是',
			cancelVal : '否',
			icon : 'warning'
		});
	});

	// 删除数据
	$(".btn_delete").click(function() {
		var url = $(this).data("url");
		// 给用户一个删除之前的确认信息
		art.dialog({
			title : '温馨提示',
			content : '删除的数据不能恢复,确定要删除吗?',
			ok : function() {
				// 发送一个ajax请求将数据删除
				$.get(url, function(data) {
					// 执行成功给用户一个提示
					art.dialog({
						title : '温馨提示',
						content : data,
						ok : function() {
							// 删除成功,重新刷新一次页面
							window.location.reload();
						}
					});
				})
			},
			cancel : true,
			okVal : '是',
			cancelVal : '否',
			icon : 'warning'
		});
	});
	
	// 审核
	$(".btn_audit").click(function() {
		var url = $(this).data("url");
		// 给用户一个删除之前的确认信息
		art.dialog({
			title : '温馨提示',
			content : '确定要审核吗?',
			ok : function() {
				// 发送一个ajax请求将数据删除
				$.get(url, function(data) {
					// 执行成功给用户一个提示
					art.dialog({
						title : '温馨提示',
						content : data,
						ok : function() {
							// 删除成功,重新刷新一次页面
							window.location.reload();
						}
					});
				})
			},
			cancel : true,
			okVal : '是',
			cancelVal : '否',
			icon : 'warning'
		});
	});
	

	// 全选/全不选效果
	$("#all").change(function() {
		// 将页面中所有的class为acb的复选框设置为all复选框的状态
		var checked = $(this).prop("checked");
		$(".acb").prop("checked", checked);
	});
});

// 鼠标的悬停效果
$(function() {
	$(".data_tr").mouseover(function() {
		$(this).css("background-color", "#D8D8D8");
	});
	$(".data_tr").mouseout(function() {
		$(this).css("background-color", "#FFFFFF");
	});
})