<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="diagnosisHistPestilenceDialog" data-hide="#diagnosisHistPestilenceDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" id="diagnosisHistPestilence_patientName"></div>
            <div class="fw-bold fs-18">传染病史</div>
            <div></div>
        </div>
        <div class="u-dialog-content">
        <form action="#" id="diagnosisHistPestilenceForm" onsubmit="return false;">
            <input type="hidden" name="id" />
            <input type="hidden" name="fkPatientId" id="diagnosisHistPestilence_patientId"/>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">诊断日期：</div>
                    <div class="right">
                        <input type="text" id="diagnosisHistPestilence_diagnosticDateForm" name="diagnosticDateForm" readonly placeholder="诊断日期" />
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">诊断名称：</div>
                    <div class="right">
                        <c:forEach var="obj" items="${bs_crbzdmc}" varStatus="status">
                            <label class="u-radio mr-20">
                                <input type="radio" name="diagnosticName" value="${obj.value}" />
                                <span class="icon-radio"></span>${obj.name}
                            </label>
                        </c:forEach>
                        <div class="u-textarea-adaption mb-6 hide" data-other>
                            <textarea style="width: 100%;" rows="1" id="diagnosisHistPestilence_otherDiagnosticName" name="otherDiagnosticName" maxlength="256"></textarea>
                        </div>
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">活动状态：</div>
                    <div class="right">
                        <c:forEach var="obj" items="${bs_crbhdzt}" varStatus="status">
                            <label class="u-radio u-xt-4 mr-0">
                                <input type="radio" name="activityState" value="${obj.value}" />
                                <span class="icon-radio"></span>${obj.name}
                            </label>
                        </c:forEach>
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">治疗状态：</div>
                    <div class="right">
                       <c:forEach var="obj" items="${bs_crbzlqk}" varStatus="status">
                            <label class="u-radio mr-20">
                                <input type="radio" name="treatment" value="${obj.value}" />
                                <span class="icon-radio"></span>${obj.name}
                            </label>
                        </c:forEach>
                        <div class="u-textarea-adaption mb-6 hide" data-other>
                            <textarea style="width: 100%;" rows="1" id="diagnosisHistPestilence_otherTreatment" name="otherTreatment" maxlength="256"></textarea>
                        </div>
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</div>
                    <div class="right">
                        <div class="u-textarea-adaption" style="min-height: 80px">
                            <textarea id="diagnosisHistPestilence_remark" name="remark" maxlength="256"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#diagnosisHistPestilenceDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveDiagnosisHistPestilence();" fill>保存</button>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_pestilence.js?version=${version}"></script>