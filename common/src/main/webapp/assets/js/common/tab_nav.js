var tab_nav = {
    style : null,
    init : function(style) {
        if (!isEmpty(style)) {
            this.style = style;
        }
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
        var tabHtml = "";
        if (isEmpty(this.style)) {// 使用默认样式
            tabHtml = '<span class="u-tab hand" data-url="' + p.url + '" data-refresh="' + p.refresh + '" data-target="' + p.id + '">' + p.name
                            + '<span class="u-tab-delete" onclick = "tab_nav.closeTab(event,this);"></span></span>';
        } else if (this.style == "btn-grey") {// 灰色按钮
            tabHtml = '<span class="u-btn-close-1" data-url="' + p.url + '" data-refresh="' + p.refresh + '" data-target="' + p.id + '">' + p.name
                            + '<span class="u-tab-delete mt-2" onclick = "tab_nav.closeTab(event,this);"></span></button>';
        }
        $("#tabsDiv").append(tabHtml);
        // 设置添加tab的来源id为当前激活的tabid
        $("#tabsDiv [data-target='" + p.id + "']").data("fromId", $("#tabsDiv [data-url].active").data("target"));
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
        if ($(el).data("fixed") == "1") {// 如果当前tab是固定tab,不能移除
            return false;
        }
        var formId = $(el).data("fromId");
        // 删除iframe body
        $("#" + el.data("target")).remove();
        // 删除tab
        $(el).remove();
        if (isEmpty(formId)) {// 没有来源id时，打开第一个
            $("#tabsDiv [data-url]:first").trigger("click");
        } else {// 有来源id时，打开来源tab
            $("#tabsDiv [data-target='" + formId + "']").trigger("click");
        }
    }
};
/**
 * 删除当前tab
 */
function removeActiveTab() {
    tab_nav.removeActiveTab();
}
/**
 * 添加tab
 */
function addTab(param) {
    tab_nav.addTab(param);
}