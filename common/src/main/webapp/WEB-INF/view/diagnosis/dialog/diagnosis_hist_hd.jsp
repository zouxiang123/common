<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal" id="diagnosisHistHdDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <img id="diagnosisHistHd_patientImage" src="${ctx }/assets/img/default-user.png" class="user-photo">
                <span class="user-name" id="diagnosisHistHd_patientName" ></span>
                <h4 class="modal-title modal-title2 ">血透史</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png"></div>
            </div>
			<!-- cache -->
			<form action="#" id="diagnosisHistHdForm" onsubmit="return saveDiagnosisHistHd(this);">
				<input type="hidden" name="id" />
				<input type="hidden" name="fkPatientId" id="diagnosisHistHd_patientId"/>
				<div class="modal-body">
	                <div class="dialog-wrap">
	                    <div class="layerNode bg-white list-group">
							<div class="list-group-item">
								<span class="list-group-item-title">开始日期：</span>
								<input type="text" class="input-style" id="diagnosisHistHd_startDateForm" name="startDateForm" onfocus="addDate(this, '', 'diagnosisHistHd_endDateForm')" readonly placeholder="开始日期"/>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">开始原因：</span>
								<c:forEach var="obj" items="${xtStartReason}" varStatus="status">
									<label for="diagnosisHistHd_startReason_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistHd_startReason_${status.index }" name="startReason" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
								<textarea class="form-control hide" rows="1" id="diagnosisHistHd_otherStartReason" name="otherStartReason" maxlength="256"></textarea>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">结束日期：</span>
								<input type="text" class="input-style" id="diagnosisHistHd_endDateForm" name="endDateForm" onfocus="addDate(this, 'diagnosisHistHd_startDateForm', '')" readonly placeholder="结束日期"/>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">结束原因：</span>
								<c:forEach var="obj" items="${xtEndReason}" varStatus="status">
									<label for="diagnosisHistHd_endReason_${status.index }" class="form-span">
										<input type="radio" class="u-radio-1" id="diagnosisHistHd_endReason_${status.index }" name="endReason" value="${obj.value}" />${obj.name}
									</label>
								</c:forEach>
								<textarea class="form-control hide" rows="1" id="diagnosisHistHd_otherEndReason" name="otherEndReason" maxlength="256"></textarea>
							</div>
							<div class="list-group-item">
								<span class="list-group-item-title">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
								<textarea class="form-control" id="diagnosisHistHd_remark" name="remark" maxlength="256"></textarea>
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
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_hd.js?version=${version}"></script>