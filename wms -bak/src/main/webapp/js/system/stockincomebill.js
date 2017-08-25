$(function() {
	$("#edit_table_body").on("click", ".searchproduct", function() {
		// 获取到当前行
		var currentTr = $(this).closest("tr");
		var url = "/product_selectProductList.action";
		$.dialog.open(url, {
			id : 'ajxxList',
			title : '产品选择',
			width : 900,
			height : 460,
			close : function() {
				var json = $.dialog.data('jsonString');
				console.debug(json);
				$(currentTr).find("[tag=pid]").val(json.id);
				$(currentTr).find("[tag=costPrice]").val(json.costPrice);
				$(currentTr).find("[tag=name]").val(json.name);
				$(currentTr).find("[tag=brand]").html(json.brandName);
			}
		})
	}).on(
			"change",
			"[tag=costPrice],[tag=number]",
			function() {
				var currentTr = $(this).closest("tr");
				var costPrice = $(currentTr).find("[tag=costPrice]").val();
				var number = $(currentTr).find("[tag=number]").val();
				if (number && costPrice) {
					$(currentTr).find("[tag=amount]").html(
							(costPrice * number).toFixed(2));
				}
			}).on("click", ".removeItem", function() {
		var currentTr = $(this).closest("tr");

		if ($("#edit_table_body tr").size() > 1) {
			currentTr.remove();
		} else {
			currentTr.find("[tag=pid]").val("");
			currentTr.find("[tag=costPrice]").val("");
			currentTr.find("[tag=name]").val("");
			currentTr.find("[tag=number]").val("");
			currentTr.find("[tag=remark]").val("");
			currentTr.find("[tag=brandName]").html("");
			currentTr.find("[tag=amount]").html("");
		}
	})

	$(".appendRow").click(function() {
		var newTr = $("#edit_table_body tr:first").clone(true);
		newTr.find("[tag=pid]").val("");
		newTr.find("[tag=costPrice]").val("");
		newTr.find("[tag=name]").val("");
		newTr.find("[tag=number]").val("");
		newTr.find("[tag=remark]").val("");
		newTr.find("[tag=brandName]").html("");
		newTr.find("[tag=amount]").html("");
		newTr.appendTo($("#edit_table_body"));

	})

	$(".btn_submit").click(
			function() {
				$("#edit_table_body tr").each(
						function(index, item) {
							$(item).find("[tag=pid]").prop(
									"name",
									"stockIncomeBill.items[" + index
											+ "].product.id")
							$(item).find("[tag=costPrice]").prop(
									"name",
									"stockIncomeBill.items[" + index
											+ "].costPrice")
							$(item).find("[tag=remark]").prop(
									"name",
									"stockIncomeBill.items[" + index
											+ "].remark")
							$(item).find("[tag=number]").prop(
									"name",
									"stockIncomeBill.items[" + index
											+ "].number")
						})
				$("#editForm").submit();
			})

})
