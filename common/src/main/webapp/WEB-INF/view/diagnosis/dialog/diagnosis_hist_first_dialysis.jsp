<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="diagnosisHistFirstDialysisDialog" data-hide="#diagnosisHistFirstDialysisDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" id="diagnosisHistFirstDialysis_patientName"></div>
            <div class="fw-bold fs-18">首次透析</div>
            <div></div>
        </div>
        <div class="u-dialog-content" style="min-height: 160px;">
            <form action="#" id="diagnosisHistFirstDialysisForm" onsubmit="return false;">
                <input type="hidden" name="id" />
                <input type="hidden" name="fkPatientId" id="diagnosisHistFirstDialysis_patientId"/>
                <div class="contentcenter" style="width: 80%">
                    <div class="u-xt-12 mt-12">
                        <div class="u-list-text">
                            <div class="left">首次透析日期：</div>
                            <div class="right">
                                <input type="text" id="diagnosisHistFirstDialysis_firstTreatmentDate" name="firstTreatmentDateForm" readonly placeholder="首次透析日期" />
                            </div>
                        </div>
                    </div>
                    <div class="u-xt-12 mt-12">
                        <div class="u-list-text">
                            <div class="left">首次透析类型：</div>
                            <div class="right">
                                <c:forEach var="obj" items="${first_dialysis_method}" varStatus="status">
                                    <label class="u-radio mr-30">
                                        <input type="radio" name="firstTreatmentType" value="${obj.value}" />
                                        <span class="icon-radio"></span>${obj.name}
                                    </label>
                                </c:forEach>
                                <div data-error></div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#diagnosisHistFirstDialysisDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveDiagnosisHistFirstDialysis()" fill>保存</button>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_first_dialysis.js?version=${version}"></script>