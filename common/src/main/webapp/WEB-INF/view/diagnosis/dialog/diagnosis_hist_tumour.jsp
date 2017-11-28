<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="diagnosisHistTumourDialog" data-hide="#diagnosisHistTumourDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" id="diagnosisHistTumour_patientName"></div>
            <div class="fw-bold fs-18">肿瘤史</div>
            <div></div>
        </div>
        <div class="u-dialog-content" style="min-height: 160px;">
        <form action="#" id="diagnosisHistTumourForm" onsubmit="return saveDiagnosisHistTumour(this);">
            <input type="hidden" name="id" />
            <input type="hidden" name="fkPatientId" id="diagnosisHistTumour_patientId"/>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">诊断日期：</div>
                    <div class="right">
                        <input type="text" id="diagnosisHistTumourForm_recordDateShow" name="recordDateShow" placeholder="请选择诊断日期" readonly="readonly"/>
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">诊断类别：</div>
                    <div class="right">
                        <div class="u-textarea-adaption mt-10" style="min-height: 80px">
                            <textarea name="recordType" maxlength="256"></textarea>
                        </div>
                        <div data-error></div>
                    </div>
                </div>
            </div>
        </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#diagnosisHistTumourDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveDiagnosisHistTumour();" fill>保存</button>
        </div>
    </div>
</div>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_tumour.js?version=${version}"></script>