$(function() {


	
	
	$("#edit_table_body").on("click", ".searchproduct", function() {

		var currentTr = $(this).closest("tr");
		var url = "/product_selectProductList.action";
		$.dialog.open(url, {
			id : 'ajxxList',
			title : '进货信息',
			width : 900,
			height : 460,
			close : function() {
				var json = $.dialog.data("jsonString");
				if (json) {
					currentTr.find("[tag=pid]").val(json.id);
					currentTr.find("[tag=costPrice]").val(json.costPrice);
					currentTr.find("[tag=name]").val(json.name);
					currentTr.find("[tag=brandName]").html(json.brandName);
				}
			}
		})
	}).on(
			"change",
			"[tag=costPrice],[tag=number]",
			function() {
				var currentTr = $(this).closest("tr");
				var costPrice = currentTr.find("[tag=costPrice]").val();
				var number = currentTr.find("[tag=number]").val();
				if (number && costPrice) {
					currentTr.find("[tag=amount]").html(
							(costPrice * number).toFixed(2))
				}

			}).on("click",".removeItem",function(){
				var currentTr = $(this).closest("tr");
		
				if($("#edit_table_body tr").size() >1){
				currentTr.remove();
				}else {
					currentTr.find("[tag=pid]").val("");
					currentTr.find("[tag=costPrice]").val("");
					currentTr.find("[tag=name]").val("");
					currentTr.find("[tag=number]").val("");
					currentTr.find("[tag=remark]").val("");
					currentTr.find("[tag=brandName]").html("");
					currentTr.find("[tag=amount]").html("");
				}
			})

	// 从选择列表中回现值到orderBill input 界面
	/*
	 * $(".searchproduct").click(function() { var currentTr =
	 * $(this).closest("tr"); var url = "/product_selectProductList.action";
	 * $.dialog.open(url, { id : 'ajxxList', title : '进货信息', width : 900, height :
	 * 460, close : function() { var json = $.dialog.data("jsonString"); if
	 * (json) { currentTr.find("[tag=pid]").val(json.id);
	 * currentTr.find("[tag=costPrice]").val(json.costPrice);
	 * currentTr.find("[tag=name]").val(json.name);
	 * currentTr.find("[tag=brandName]").html(json.brandName); } } }) })
	 */
	// 计算数量跟价格的总价
	/*
	 * $("[tag=costPrice],[tag=number]").change( function() { var currentTr =
	 * $(this).closest("tr"); var costPrice =
	 * currentTr.find("[tag=costPrice]").val(); var number =
	 * currentTr.find("[tag=number]").val(); if (number && costPrice) {
	 * currentTr.find("[tag=amount]").html( (costPrice * number).toFixed(2)) } })
	 */

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

	$(".btn_submit").click(function() {
		$("#edit_table_body tr").each(function(index, item) {
			$(item).find("[tag=pid]").prop("name","orderBill.items["+index+"].product.id");
			$(item).find("[tag=costPrice]").prop("name","orderBill.items["+index+"].costPrice");
			$(item).find("[tag=number]").prop("name","orderBill.items["+index+"].number");
			$(item).find("[tag=remark]").prop("name","orderBill.items["+index+"].remark");
		})
		$("#editForm").submit();
	})
	

	

})