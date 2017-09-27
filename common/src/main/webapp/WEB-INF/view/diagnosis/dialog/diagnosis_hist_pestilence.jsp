<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal" id="diagnosisHistPestilenceDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <img id="diagnosisHistPestilence_patientImage" src="${ctx }/assets/img/default-user.png" class="user-photo">
                <span class="user-name" id="diagnosisHistPestilence_patientName" ></span>
                <h4 class="modal-title modal-title2 ">传染病史</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png"></div>
            </div>
			<!-- cache -->
			<form action="#" id="diagnosisHistPestilenceForm" onsubmit="return saveDiagnosisHistPestilence(this);">
				<input type="hidden" name="id" />
				<input type="hidden" name="fkPatientId" id="diagnosisHistPestilence_patientId"/>
				<div class="modal-body">
	                <div class="dialog-wrap">
	                    <div class="list-group bg-white layerNode">
							<div class="list-group-item">
								<span class="list-group-item-title">诊断日期：</span>
								<input type="text" class="input-style" id="diagnosisHistPestilence_diagnosticDateForm" name="diagnosticDateForm" readonly onfocus="addDate(this)" placeholder="诊断日期" />
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">诊断名称：</span>
								<c:forEach var="obj" items="${bs_crbzdmc}" varStatus="status">
									<label for="diagnosisHistPestilence_diagnosticName_${status.index }" class="form-span">
										<input type="checkbox" class="u-checkbox-1" id="diagnosisHistPestilence_diagnosticName_${status.index }" name="diagnosticName" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
								<textarea class="form-control hide" rows="1" id="diagnosisHistPestilence_otherDiagnosticName" name="otherDiagnosticName" maxlength="256"></textarea>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">活动状态：</span>
								<c:forEach var="obj" items="${bs_crbhdzt}" varStatus="status">
									<label for="diagnosisHistPestilence_activityState_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistPestilence_activityState_${status.index }" name="activityState" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">治疗情况：</span>
								<c:forEach var="obj" items="${bs_crbzlqk}" varStatus="status">
									<label for="diagnosisHistPestilence_treatment_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistPestilence_treatment_${status.index }" name="treatment" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
								<textarea class="form-control hide" rows="1" id="diagnosisHistPestilence_otherTreatment" name="otherTreatment" maxlength="256"></textarea>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
								<textarea class="form-control" id="diagnosisHistPestilence_remark" name="remark" maxlength="256"></textarea>
							</div>
							<div data-error></div>
	                    </div>
	                </div>
	            </div>
	            <div class="modal-footer dialog-footer">
	                <div class="center">
	                    <button type="button" class="btn btn-can dialog-button" data-dismiss="modal">取消</button>
	                    <button type="button" class="btn btn-def dialog-button" onclick="buttonSubmit(this)">确定</button>
	                </div>
	            </div>
         	</form>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_pestilence.js?version=${version}"></script>