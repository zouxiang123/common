var tab_nav = {
	init : function() {
		tab_nav.setHeight();
		tab_nav.addEvents();
		$("#tabsDiv [data-url]:first").trigger("click");
	},
	setHeight : function() {
		$("#basicIframeDiv iframe").height($(window).height() - ($("#tabsBodyDiv").offset().top + 10));
	},
	addEvents : function() {
		$("#tabsDiv").on("click", "[data-url]", function() {
			var bodyId = $(this).data("target");
			$(this).addClass("active").siblings().removeClass("active");
			if ($("#" + bodyId).length > 0) {
				var refresh = $(this).data("refresh");
				if (refresh == "1") {
					var iframe = $("#" + bodyId).find("iframe")[0];
					iframe.onload = iframe.onreadystatechange = function() {// 添加界面加载完成事件
						if (this.readyState && this.readyState != 'complete')
							return;
						else {
							$("#" + bodyId).removeClass("hide");
						}
					};
					$("#" + bodyId).find("iframe").attr("src", $(this).data("url"));
				} else {
					$("#" + bodyId).removeClass("hide");
				}
			} else {
				tab_nav.addIframe(bodyId, $(this).data("url"));
			}
			$("#" + bodyId).siblings().addClass("hide");
		});
		$(window).on("resize", function() {
			var height = $(window).height() - ($("#tabsBodyDiv").offset().top + 10);
			$("iframe").height(height);
		});
	},
	addTab : function(param) {
		var p = {
			id : new Date().getTime(),
			name : "new tab",
			url : null,
			refresh : "1"
		};
		$.extend(p, param);
		if ($("#tabsDiv [data-target='" + p.id + "']").length > 0) {// 已经存在，不添加新窗口
			$("#tabsDiv [data-target='" + p.id + "']").click();
			return false;
		}
		if (isEmpty(p.url)) {
			showWarn("添加的tab页url不能为空");
			return false;
		}
		if ($("#tabsDiv [data-url]").length > 10) {
			showWarn("您打开的页面过多");
			return false;
		}
		$("#tabsDiv").append(
						'<span class="u-tab hand" data-url="' + p.url + '" data-refresh="' + p.refresh + '" data-target="' + p.id + '">' + p.name
										+ '<span class="u-tab-delete" onclick = "tab_nav.closeTab(event,this);"></span></span>');
		$("#tabsDiv [data-target='" + p.id + "']").trigger("click");
	},
	addIframe : function(id, url) {
		var body = $("#basicIframeDiv").clone(true);
		$(body).removeClass("hide");
		$(body).attr("id", id);
		$(body).find("iframe").attr("src", url);
		$("#tabsBodyDiv").append(body);
	},
	closeTab : function(event, el) {
		tab_nav.removeActiveTab($(el).parent());
		stopEventBubble(event);
	},
	removeActiveTab : function(el) {
		if (isEmpty(el)) {
			el = $("#tabsDiv [data-url].active");
		}
		// 删除iframe body
		$("#" + el.data("target")).remove();
		// 删除tab
		$(el).remove();
		$("#tabsDiv [data-url]:first").trigger("click");
	}
};