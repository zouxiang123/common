<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="diagnosisHistSurgeryDialog" data-hide="#diagnosisHistSurgeryDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" id="diagnosisHistSurgery_patientName"></div>
            <div class="fw-bold fs-18">手术史</div>
            <div></div>
        </div>
        <div class="u-dialog-content" style="min-height: 210px;">
          <form action="#" id="diagnosisHistSurgeryForm" onsubmit="return false;">
            <input type="hidden" name="id"/>
            <input type="hidden" name="fkPatientId" id="diagnosisHistSurgery_patientId"/>
                <div class="u-xt-12 pr-30">
                    <div class="u-list-text">
                        <div class="left">手术日期：</div>
                        <div class="right">
                            <input type="text" id="diagnosisHistSurgery_surgeryDateForm" name="surgeryDateForm" readonly placeholder="手术日期">
                            <div data-error></div>
                        </div>
                    </div>
                </div>
                <div class="u-xt-12 pr-30">
                    <div class="u-list-text">
                        <div class="left">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</div>
                        <div class="right">
                            <input type="text" id="diagnosisHistSurgery_surgeryName" name="surgeryName" maxlength="30" placeholder="名称"/>
                            <div data-error></div>
                        </div>
                    </div>
                </div>
                <div class="u-xt-12 pr-30">
                    <div class="u-list-text">
                        <div class="left">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</div>
                        <div class="right">
                            <div class="u-textarea-adaption mt-10" style="min-height: 80px">
                                <textarea id="diagnosisHistSurgery_remark" name="remark" maxlength="256"></textarea>
                            </div>
                            <div data-error></div>
                        </div>
                    </div>
                </div>
          </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#diagnosisHistSurgeryDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveDiagnosisHistSurgery();" fill>保存</button>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_surgery.js?version=${version}"></script>