<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal" id="diagnosisHistFirstDialysisDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <img id="diagnosisHistFirstDialysis_patientImage" src="${ctx }/assets/img/default-user.png" class="user-photo">
                <span class="user-name" id="diagnosisHistFirstDialysis_patientName" ></span>
                <h4 class="modal-title modal-title2 ">首次透析</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png"></div>
            </div>
			<!-- cache -->
			<form action="#" id="diagnosisHistFirstDialysisForm" onsubmit="return saveDiagnosisHistFirstDialysis(this);">
				<input type="hidden" name="id" />
				<input type="hidden" name="fkPatientId" id="diagnosisHistFirstDialysis_patientId"/>
				<div class="modal-body">
	                <div class="dialog-wrap">
	                    <div class="list-group bg-white layerNode">
	                        <div class="list-group-item">
	                            <span class="list-group-item-title">首次透析日期：</span>
	                            <input type="text" class="input-style" id="diagnosisHistFirstDialysis_firstTreatmentDate" name="firstTreatmentDateForm" onfocus="addDate(this)" readonly placeholder="首次透析日期" />
	                        </div>
	                        <div class="list-group-item">
	                            <span class="list-group-item-title">首次透析类型：</span>
								<c:forEach var="obj" items="${first_dialysis_method}" varStatus="status">
									<label for="diagnosisHistFirstDialysis_firstTreatmentType_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistFirstDialysis_firstTreatmentType_${status.index }" name="firstTreatmentType" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
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
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_first_dialysis.js?version=${version}"></script>