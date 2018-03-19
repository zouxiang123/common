<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>新增患者</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<input type="hidden" id="fkDialysisCampaignId" name="fkDialysisCampaignId" value="${fkDialysisCampaignId }" />
	<input type="hidden" id="fkPatientDiagnosisId" name="fkPatientDiagnosisId" value="${fkPatientDiagnosisId }" />
	<input type="hidden" id="fkPatientId" name="fkPatientId" value="${patientId }" />
	<input type="hidden" id="patientId" name="patientId" value="${patientId }" />
	<input type="hidden" id="isDraft" name="isDraft" value="${patientDiagnosis.isDraft }" />
	<input type="hidden" id="lastStep" value="${patientDiagnosis.lastStep }" />
	<input type="hidden" id="tabId" name="tabId" value="${tabId }" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12" style="padding:0px;">
				<div class="content-editing-bar" style="list-style: none;margin-bottom: 0px;border-bottom: 1px solid #d9e0e6;border-top: 1px solid #d9e0e6;" id="contentBar">
					<c:if test="${operation != 'secondAdd' }">
						<div class="content-editing-item" data-link="#patientInfo">基本信息</div>
						<div class="content-editing-item" data-link="#medicalHistory">病史询问</div>
					</c:if>
					<div class="content-editing-item" data-link="#clinicalDiagnosis">原发病诊断</div>
					<div class="content-editing-item" data-link="#pathologicDiagnosis">病理诊断</div>
					<div class="content-editing-item" data-link="#ckdAki">CKD/AKI</div>
					<c:if test="${operation != 'secondAdd' }">
						<div class="content-editing-item" data-link="#cureComplication">治疗前合并症</div>
						<div class="content-editing-item" data-link="#otherDiagnosis">其他诊断</div>
					</c:if>
					<div class="content-editing-item" data-link="#done" onclick="reloadPreview();">完成</div>
				</div>

				<div id="patientInfo">
					<form action="${ctx }/system/uploadImage.shtml" onsubmit="return uploadImg(this);" enctype="multipart/form-data" method="post" id ="imageUploadForm" style="display:none;">
						<input type="file" id="up_img" name="image" style="display: none;" accept="image/*"/>
						<input type="hidden" id="patientId" name="patientId" value="${patientId }" />
					</form>
					<form id="patientForm" action="#" name="patientForm" onsubmit="return patientFormSubmit(this);">
						<input type="hidden" id="tempImagePath" name="tempImagePath" />
						<jsp:include page="../patient/edit_patient_basic.jsp"></jsp:include>
						<button type="button" id="patientForm_btnSave" class="btn btn-def btn-ls center-block" style="margin-top: 20px;" onclick="buttonSubmit(this);">保存并下一步</button>
					</form>
					<script type="text/javascript">
						function patientFormSubmit(form) {
							$.ajax({
								url : ctx + "/patient/diagnosis/savePatient.shtml",
								data : $(form).serialize(),
								type : "post",
								dataType : "json",
								loading : true,
								success : function(data) {// ajax返回的数据
									if (data && data.status) {
										if ($("#fkPatientDiagnosisId").val() == "") {
											$("#fkPatientDiagnosisId").val(data.fkPatientDiagnosisId);
											$("#fkPatientId").val(data.fkPatientId);
											$("#id").val(data.fkPatientId);
											$("#medicalHistoryFormfkDialysisCampaignId").val(data.fkDialysisCampaignId);
										}
										gotoPage("medicalHistory", true, $("#fkPatientDiagnosisId").val());
									};
									return false;
								}
							});
							return false;
						}
					</script>
				</div>
				<div id="medicalHistory">
					<jsp:include page="medical_history.jsp" flush="true"></jsp:include>
				</div>
				<div id="clinicalDiagnosis">
					<jsp:include page="clinical_diagnosis.jsp" flush="true"></jsp:include>
				</div>
				<div id="pathologicDiagnosis">
					<jsp:include page="pathologic_diagnosis.jsp" flush="true"></jsp:include>
				</div>
				<div id="ckdAki">
					<jsp:include page="ckd_aki.jsp" flush="true"></jsp:include>
				</div>
				<div id="cureComplication">
					<jsp:include page="cure_complication.jsp" flush="true"></jsp:include>
				</div>
				<div id="otherDiagnosis">
					<jsp:include page="other_diagnosis.jsp" flush="true"></jsp:include>
				</div>
				<div id="done">
					<iframe id="ifrDone" src="" width="100%" height="100%" frameborder="no" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
				</div>
			</div>
		</div>
		<script src="${ctx}/assets/js/diagnosis/new_patient.js?version=${version}"></script>
</body>
</html>
