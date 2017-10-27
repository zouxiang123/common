<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="iframeFullScreenDialog" data-hide="#iframeFullScreenDialog">
    <div class="u-dialog" fullScreen>
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" data-title1></div>
            <div class="fw-bold fs-18" data-title></div>
            <div class="u-float-r">
                <i class="icon-close" data-hide="#iframeFullScreenDialog"></i>
            </div>
        </div>
        <div class="u-dialog-content">
            <iframe frameborder="no" width="100%" scrolling="yes" allowtransparency="yes"></iframe>
        </div>
        <div class="u-dialog-footer text-center">
            <button type="button" data-btn="cancel">取消</button>
            <button type="button" class="u-btn-blue" data-btn="save" fill>保存</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    /**
     * 显示全屏dialog
     * 
     * @param param {
     *            title:标题, title1:小标题, saveCall：保存事件回调（返回iframe窗口）,url:请求地址;cancelCall:取消回调 }
     */
     function showIframeFullScreenDialog(param){
        var dialog = $("#iframeFullScreenDialog");
        dialog.find("[data-title]").text(convertEmpty(param.title));
        dialog.find("[data-title1]").text(convertEmpty(param.title1));
        dialog.find("iframe").attr("src", convertEmpty(param.url));
        dialog.find("[data-btn]").off("click").on("click", function() {
            var iframe = dialog.find("iframe")[0];
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if($(this).data("btn")=="cancel") {
                if(!isEmpty(param.cancelCall)){
                    param.cancelCall(iframeWin);
                }else{
                    hiddenMe($("#iframeFullScreenDialog"));
                }
            } else if ($(this).data("btn")=="save") {
                if (!isEmpty(param.saveCall)) {
                    param.saveCall(iframeWin);
                }else{
                    hiddenMe($("#iframeFullScreenDialog"));
                }
            }
        });
        var iframeDocument = dialog.find("iframe")[0].contentDocument || dialog.find("iframe")[0].contentWindow.document;
        iframeDocument.documentElement.innerHTML = "";
        dialog.find("iframe").height($(window).height() - 155);
        popDialog(dialog);
    };
</script>
