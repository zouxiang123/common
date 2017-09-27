<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal" id="diagnosisHistPdDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <img id="diagnosisHistPd_patientImage" src="${ctx }/assets/img/default-user.png" class="user-photo">
                <span class="user-name" id="diagnosisHistPd_patientName" ></span>
                <h4 class="modal-title modal-title2 ">腹透史</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png"></div>
            </div>
			<!-- cache -->
			<form action="#" id="diagnosisHistPdForm" onsubmit="return saveDiagnosisHistPd(this);">
				<input type="hidden" name="id" />
				<input type="hidden" name="fkPatientId" id="diagnosisHistPd_patientId"/>
				<div class="modal-body">
	                <div class="dialog-wrap">
	                    <div class="list-group bg-white layerNode">
							<div class="list-group-item">
								<span class="list-group-item-title">开始日期：</span>
								<input type="text" class="input-style" id="diagnosisHistPd_startDateForm" name="startDateForm" readonly onfocus="addDate(this, '', 'diagnosisHistPd_endDateForm')" placeholder="开始日期"/>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">开始原因：</span>
								<c:forEach var="obj" items="${ftStartReason}" varStatus="status">
									<label for="diagnosisHistPd_startReason_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistPd_startReason_${status.index }" name="startReason" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
								<textarea class="form-control hide" rows="1" id="diagnosisHistPd_otherStartReason" name="otherStartReason" maxlength="256"></textarea>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">结束日期：</span>
								<input type="text" class="input-style" id="diagnosisHistPd_endDateForm" name="endDateForm" readonly onfocus="addDate(this, 'diagnosisHistPd_startDateForm', '')" placeholder="结束日期"/>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">结束原因：</span>
								<c:forEach var="obj" items="${ftEndReason}" varStatus="status">
									<label for="diagnosisHistPd_endReason_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistPd_endReason_${status.index }" name="endReason" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
								<textarea class="form-control hide" rows="1" id="diagnosisHistPd_otherEndReason" name="otherEndReason" maxlength="256"></textarea>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
								<textarea class="form-control" id="diagnosisHistPd_remark" name="remark" maxlength="256"></textarea>
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
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_pd.js?version=${version}"></script>