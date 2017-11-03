<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!--充分性评估弹框-->
<div class="u-mask" id="assayRecordChooseDialog" data-hide="#assayRecordChooseDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div></div>
            <div class="fs-18" title></div>
            <div></div>
        </div>
        <div class="u-dialog-content ml-10 mr-10">
            <div class="u-xt-12">
                <div class="u-xt-4">
                    <span class="opacity-5">日期</span>
                </div>
                <div class="u-xt-3">
                    <span class="opacity-5">检查结果</span>
                </div>
                <div class="u-xt-2">
                    <span class="opacity-5">提示</span>
                </div>
                <div class="u-xt-3">
                    <span class="opacity-5">参考值</span>
                </div>
            </div>
            <div id="assayRecordChooseDialogContent"></div>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#assayRecordChooseDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="assay_record_choose_dialog.ensure();" fill>确定</button>
        </div>
    </div>
</div>
<script src="${ctx }/assets/js/assay/assay_record_choose_dialog.js?version=${version}"></script>