//加载当前日期
function loadDate() {
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
			+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 */
function switchSysBar(flag) {
	var side = $('#side');
	var left_menu_cnt = $('#left_menu_cnt');
	if (flag == true) { // flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({
			width : '280px'
		});
		$('#top_nav').css({
			width : '77%',
			left : '304px'
		});
		$('#main').css({
			left : '280px'
		});
	} else {
		if (left_menu_cnt.is(":visible")) {
			left_menu_cnt.hide(10, 'linear');
			side.css({
				width : '60px'
			});
			$('#top_nav').css({
				width : '100%',
				left : '60px',
				'padding-left' : '28px'
			});
			$('#main').css({
				left : '60px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_show.png');
		} else {
			left_menu_cnt.show(500, 'linear');
			side.css({
				width : '280px'
			});
			$('#top_nav').css({
				width : '77%',
				left : '304px',
				'padding-left' : '0px'
			});
			$('#main').css({
				left : '280px'
			});
			$("#show_hide_btn").find('img').attr('src',
					'/images/common/nav_hide.png');
		}
	}
}

$(function() {
	loadDate();
	// 显示侧边栏
	switchSysBar(true);
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
		switchSysBar();
	});
});

$(function() {
	$(".system_url").click(function() {
		// 为iframe标签的src属性赋值
		var url = $(this).data("url");
		$("#rightMain").attr("src", url);
	})
})

// 为li绑定点击事件
$(function() {
	$("#TabPage2 li").click(
			function() {
				// 先将所有的li的selected样式删除
				$("#TabPage2 li").removeClass("selected")
				// 为当前的li添加属性:class="selected"
				$(this).addClass("selected");

				// 将所有的图片地址设置为 如:1.jpg
				$.each($("#TabPage2 img"), function(index, item) {
					$(item).prop("src",
							"/images/common/" + (index + 1) + ".jpg")
				});

				// 切换当前的li中的img的图片:find
				// 使用index()获取到当前元素所在父元素中的索引:从0开始
				$(this).find("img").prop(
						"src",
						"/images/common/" + ($(this).index() + 1)
								+ "_hover.jpg");

				// 将模块中的图片修改了
				$("#nav_module img").prop(
						"src",
						"/images/common/module_" + ($(this).index() + 1)
								+ ".png");
				// 加载模块数据
				loadMenu($(this).data("rootmenu"));
			})
});

// 在点击菜单节点的时候调用该函数去加载对应的菜单树
function loadMenu(modelName) {
	// 使用[]取出对象中的属性的值,不能使用.
	$.fn.zTree.init($(".ztree"), setting, zNodes[modelName]);
}

// 菜单树
var setting = {
	data : {
		simpleData : {
			// 开启简单数据格式
			enable : true
		}
	},
	callback : {
		// 绑定节点点击事件
		onClick : function(event, treeId, treeNode) {
			if (treeNode.action) {
				// 发送请求
				$("#rightMain").attr("src", treeNode.action + ".action");
			}
		}
	},

	// 使用异步加载菜单
	async : {
		enable : true,
		url : "/systemMenu_getMenusByParentSn.action",
		autoParam : [ "sn=qo.parentSn" ]
	}
};

var system = [ {
	id : 12,
	pId : 0,
	name : "系统模块",
	isParent : true,
	sn : "system"
} ];
var business = [ {
	id : 11,
	pId : 0,
	name : "业务模块",
	isParent : true,
	sn : "business"
} ];
var chart = [ {
	id : 13,
	pId : 0,
	name : "报表模块",
	isParent : true,
	sn : "chart"
} ];
// 必须放在元素对象的后面
var zNodes = {
	system : system,
	business : business,
	chart : chart
};
$(document).ready(function() {
	$.fn.zTree.init($(".ztree"), setting, zNodes.business);
});
