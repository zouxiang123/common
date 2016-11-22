<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>诊断信息</title>
<%@ include file="../common/head.jsp"%>
<script src="${ctx }/assets/js/diagnosis/diagnosis.js?version=${version}"></script>
</head>
<body>
	<input type="hidden" id="lastStep" value="${dialysisCampaign.lastStep }" />
	<input type="hidden" id="patientId" value="${patientId }" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12" style="padding:0px;">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">诊断记录</span>
					<div class="pull-right" style="margin-top: -8px;" id="reportDateDiv"></div>
					<div class="tab-def-action" onclick='window.location.href = "${ctx }/patient/diagnosis/newPatient.shtml?patientId=${patientId }"'>
						<span>
							<c:if test="${!patientDiagnosis.isDraft}">新增</c:if> <c:if test="${patientDiagnosis.isDraft}">继续录入</c:if>
						</span>
						<img src="${ctx }/assets/img/new-edit.png" class="new-edit">
					</div>
				</div>
				<ul class="content-editing-bar" style="list-style: none;margin-bottom: 0px;border-bottom: 1px solid #d9e0e6;">
					<li class="content-editing-item-6 hand" data-link="medicalHistory"><span class="active" style="margin-left: 15px;">病史</span>
						<div class="tab-line" style="right: 0px; position: absolute; float: inherit; margin-right: 0px;"></div></li>
					<li class="content-editing-item-6 hand" data-link="clinicalDiagnosis"><span style="margin-left: 0px;">临床诊断</span>
						<div class="tab-line" style="right: 0px; position: absolute; float: inherit; margin-right: 0px;"></div></li>
					<li class="content-editing-item-6 hand" data-link="pathologicDiagnosis"><span style="margin-left: 0px;">病理诊断</span>
						<div class="tab-line" style="right: 0px; position: absolute; float: inherit; margin-right: 0px;"></div></li>
					<li class="content-editing-item-6 hand" data-link="ckdAki"><span style="margin-left: 0px;">CKD/AKI分期</span>
						<div class="tab-line" style="right: 0px; position: absolute; float: inherit; margin-right: 0px;"></div></li>
					<li class="content-editing-item-6 hand" data-link="cureComplication"><span style="margin-left: 10px;">治疗前合并症</span>
						<div class="tab-line" style="right: -20px; position: absolute; float: inherit; margin-right: 0px;"></div></li>
					<li class="content-editing-item-6 hand" data-link="otherDiagnosis"><span style="margin-left: 20px;">其他诊断</span> <label class="badge-tab"></label></li>
				</ul>
				<div id="medicalHistory">
					<jsp:include page="diagnosis_info_medical_history.jsp" flush="true"></jsp:include>
				</div>
				<div id="clinicalDiagnosis">
					<jsp:include page="diagnosis_info_clinical_diagnosis.jsp" flush="true"></jsp:include>
				</div>
				<div id="pathologicDiagnosis">
					<jsp:include page="diagnosis_info_pathologic_diagnosis.jsp" flush="true"></jsp:include>
				</div>
				<div id="ckdAki">
					<jsp:include page="diagnosis_info_ckd_aki.jsp" flush="true"></jsp:include>
				</div>
				<div id="cureComplication">
					<jsp:include page="diagnosis_info_cure_complication.jsp" flush="true"></jsp:include>
				</div>
				<div id="otherDiagnosis">
					<jsp:include page="diagnosis_info_other_diagnosis.jsp" flush="true"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</body>
</html>