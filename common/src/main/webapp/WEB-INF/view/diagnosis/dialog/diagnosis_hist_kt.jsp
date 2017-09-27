<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal" id="diagnosisHistKtDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <img id="diagnosisHistKt_patientImage" src="${ctx }/assets/img/default-user.png" class="user-photo">
                <span class="user-name" id="diagnosisHistKt_patientName" ></span>
                <h4 class="modal-title modal-title2 ">肾移植史</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png"></div>
            </div>
			<!-- cache -->
			<form action="#" id="diagnosisHistKtForm" onsubmit="return saveDiagnosisHistKt(this);">
				<input type="hidden" name="id" />
				<input type="hidden" name="fkPatientId" id="diagnosisHistKt_patientId"/>
				<div class="modal-body">
	                <div class="dialog-wrap">
	                    <div class="list-group bg-white layerNode">
							<div class="list-group-item">
								<span class="list-group-item-title">开始日期：</span>
								<input type="text" class="input-style" id="diagnosisHistKt_startDateForm" name="startDateForm" onfocus="addDate(this, '', 'diagnosisHistKt_endDateForm')" readonly placeholder="开始日期" />
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">结束日期：</span>
								<input type="text" class="input-style" id="diagnosisHistKt_endDateForm" name="endDateForm" onfocus="addDate(this, 'diagnosisHistKt_startDateForm', '')" readonly placeholder="结束日期" />
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">结束原因：</span>
								<c:forEach var="obj" items="${syzEndReason}" varStatus="status">
									<label for="diagnosisHistKt_endReason_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistKt_endReason_${status.index }" name="endReason" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
								<textarea class="form-control hide" rows="1" id="diagnosisHistKt_otherEndReason" name="otherEndReason" maxlength="256"></textarea>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
								<textarea class="form-control" id="diagnosisHistKt_remark" name="remark" maxlength="256"></textarea>
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
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_kt.js?version=${version}"></script>