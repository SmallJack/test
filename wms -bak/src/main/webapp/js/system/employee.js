// 数据校验
$(function() {
	$("#editForm").validate({
		// 校验规则
		rules : {
			'e.name' : {
				required : true,
				minlength : 4
			},
			'e.password' : {
				required : true,
				minlength : 4
			},
			'repassword' : {
				equalTo : '#password'
			},
			'e.email' : 'email',
			'e.age' : {
				range : [ 18, 60 ]
			}
		},
		// 错误信息
		messages : {
			'e.name' : {
				required : '用户名必填',
				minlength : '长度至少4位'
			},
			'e.password' : {
				required : '密码必填',
				minlength : "长度至少4位"
			},
			'repassword' : {
				equalTo : '必须和上面的密码一致'
			},
			'e.email' : '必须为邮箱的格式',
			'e.age' : {
				range : '必须是18到60之间'
			}
		}
	});
})

$(function() {
	$("#selectAll").click(function() {
		$(".allRoles option").appendTo($(".selectedRoles"));
	});

	$("#deselectAll").click(function() {
		$(".allRoles").append($(".selectedRoles option"));
	});

	$("#select").click(function() {
		$(".allRoles option:selected").appendTo($(".selectedRoles"));
	});

	$("#deselect").click(function() {
		$(".allRoles").append($(".selectedRoles option:selected"));
	});
})

// 在页面加载完成之后,将已经分配的权限从左下拉框中删除
$(function() {
	// 获取到已经选中的权限的id
	var ids = $.map($(".selectedRoles option"), function(option) {
		return $(option).val();
	});

	// 通过循环遍历,判断当前得每个权限是否存在,存在:删除
	$.each($(".allRoles option"), function(index, option) {
		// 判断当前的迭代出来的option的value值是否在上面获取到的数组中
		if ($.inArray($(option).val(), ids) >= 0) {
			$(option).remove();
		}
	})

	// 在提交表单的时候将分配的权限全部选中
	$("#editForm").submit(function() {
		$(".selectedRoles option").prop("selected", true);
	});
})