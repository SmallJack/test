$(function() {
	$(".btn_reload").click(function() {
		var url = $(this).data("url");
		var permissionUrl = $(this).data("permissionUrl");
		$.get(url, function(data) {
			art.dialog({
				title : '温馨提示',
				content : data,
				ok : function() {
					window.location.href = permissionUrl;
				}
			})
		});
	});
})