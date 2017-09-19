var system_dialog = {
    /**
     * 取消回调
     */
    cancelCall : null,
    /**
     * 确定回调
     */
    confirmCall : null,
    /**
     * 初始化参数
     */
    initParam : {
        title : "提示",
        content : "",
        level : "info",// 事件级别（info,warn,error）
        cancelCall : null,
        confirmCall : null
    },
    /**
     * 事件初始化
     */
    init : function() {
        this.createDialog();
        this.addEvents();
    },
    /**
     * 生成dialog内容
     */
    createDialog : function() {
        var html = '';
        html += '<div class="u-mask" id="systemDialog" data-hide="#systemDialog">';
        html += '<div class="u-dialog" min>';
        html += '<div class="u-dialog-header">';
        html += '<div></div>';
        html += '<div systemdialog-title>提示</div>';
        html += '<div><i class="icon-close" data-hide="#systemDialog"></i></div>';
        html += '</div>';
        html += '<div class="u-dialog-content" systemdialog-content>';
        html += '</div>';
        html += '<div class="u-dialog-footer">';
        html += '<button type="button" systemdialog-btn="cancel">取消</button>';
        html += '<button type="button" class="u-btn-blue" fill systemdialog-btn="confirm">确定</button>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        document.write(html);
    },
    /**
     * 注册事件
     */
    addEvents : function() {
        /**
         * 确定取消事件
         */
        $("#systemDialog").on("click", "[systemdialog-btn]", function() {
            var type = $(this).attr("systemdialog-btn");
            if (type == "cancel") {
                if (!isEmpty(system_dialog.cancelCall)) {
                    system_dialog.cancelCall();
                }
            } else if (type == "confirm") {
                if (!isEmpty(system_dialog.confirmCall)) {
                    system_dialog.confirmCall();
                }
            }
            $("#systemDialog").hide();
        })
    },
    /**
     * 显示系统dialog
     * 
     * @param param
     */
    show : function(param) {
        var dialogEl = $("#systemDialog");
        dialogEl.find("[systemdialog-btn='cancel']").show();
        var params = $.extend({}, this.initParam, param);
        switch (params.level) {
        case "info":
            if (isEmpty(param.title)) {
                params.title = "提示";
            }
            break;
        case "warn":
            if (isEmpty(param.title)) {
                params.title = "警告";
            }
            break;
        case "error":
            if (isEmpty(param.title)) {
                params.title = "错误";
            }
            break;
        default:
            break;
        }
        dialogEl.find("[systemdialog-title]").text(params.title);
        dialogEl.find("[systemdialog-content]").text(params.content);
        this.cancelCall = params.cancelCall;
        this.confirmCall = params.confirmCall;
        // 不存在回调时，只显示确定按钮
        if (isEmpty(params.cancelCall) && isEmpty(params.confirmCall)) {
            dialogEl.find("[systemdialog-btn='cancel']").hide();
        }
        popDialog(dialogEl);
    }
}
/**
 * 初始化系统dialog
 */
system_dialog.init();
