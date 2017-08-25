$(function() {
	$("#selectAll").click(function() {
		$(".allPermissions option").appendTo($(".selectedPermissions"));
	});

	$("#deselectAll").click(function() {
		$(".allPermissions").append($(".selectedPermissions option"));
	});

	$("#select").click(
			function() {
				$(".allPermissions option:selected").appendTo(
						$(".selectedPermissions"));
			});

	$("#deselect").click(function() {
		$(".allPermissions").append($(".selectedPermissions option:selected"));
	});
})
$(function() {
	$("#mselectAll").click(function() {
		$(".allMenus option").appendTo($(".selectedMenus"));
	});

	$("#mdeselectAll").click(function() {
		$(".allMenus").append($(".selectedMenus option"));
	});

	$("#mselect").click(function() {
		$(".allMenus option:selected").appendTo($(".selectedMenus"));
	});

	$("#mdeselect").click(function() {
		$(".allMenus").append($(".selectedMenus option:selected"));
	});
})

// 在页面加载完成之后,将已经分配的权限从左下拉框中删除
$(function() {
	// 获取到已经选中的权限的id
	var ids = $.map($(".selectedPermissions option"), function(option) {
		return $(option).val();
	});

	// 通过循环遍历,判断当前得每个权限是否存在,存在:删除
	$.each($(".allPermissions option"), function(index, option) {
		// 判断当前的迭代出来的option的value值是否在上面获取到的数组中
		if ($.inArray($(option).val(), ids) >= 0) {
			$(option).remove();
		}
	})

	// 在提交表单的时候将分配的权限全部选中
	$("#editForm").submit(function() {
		$(".selectedPermissions option").prop("selected", true);
	});
})

// 在页面加载完成之后,将已经分配的权限从左下拉框中删除
$(function() {
	// 获取到已经选中的权限的id
	var ids = $.map($(".selectedMenus option"), function(option) {
		return $(option).val();
	});

	// 通过循环遍历,判断当前得每个权限是否存在,存在:删除
	$.each($(".allMenus option"), function(index, option) {
		// 判断当前的迭代出来的option的value值是否在上面获取到的数组中
		if ($.inArray($(option).val(), ids) >= 0) {
			$(option).remove();
		}
	})

	// 在提交表单的时候将分配的权限全部选中
	$("#editForm").submit(function() {
		$(".selectedMenus option").prop("selected", true);
	});
})