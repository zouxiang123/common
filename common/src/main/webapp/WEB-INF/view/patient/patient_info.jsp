<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../common/head.jsp"%>
	<title>患者信息</title>
</head>
<body>
	<jsp:include page="../common/top_nav.jsp" flush="true"></jsp:include>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main" style="padding: 0px 20px 0px 20px;margin-top:-5px;" data-iframe-css="main">
			<form action="${ctx }/system/uploadImage.shtml" onsubmit="return uploadImg(this);" enctype="multipart/form-data" method="post" id ="imageUploadForm">
				<input type="file" id="up_img" name="image" style="display:none;" accept="image/*"/>
				<input type="hidden" id="id" name="id" value="${patient.id }">
			</form>
			<form id="patientForm" onsubmit="return patientFormSubmit(this);">
				<input type="hidden" id="tempImagePath" name="tempImagePath" />
				<jsp:include page="../doctor/patient_info.jsp"></jsp:include>
				<ul class="pager center-block">
					<button type="button" id="btnCancel" class="btn btn-ls" style="margin-top: 20px;" onclick="history.go(-1);">取消</button>
					<button type="button" id="btnSave" class="btn btn-def btn-ls" style="margin-top: 20px;" onclick="buttonSubmit(this);">保存</button>
				</ul>
			</form>
		</div>
		</div>
	</div>
	<script src="<%=basePath%>assets/js/birthday.js?version=${version}"></script>
	<script src="<%=basePath%>assets/js/patient/patient_info.js?version=${version}"></script>
	<script type="text/javascript">
	$(function() {
/* 		var menu = {
						"患者中心" : basePath + "patient/center/toCenter.shtml",
						"基本信息" : null
					};
		setBreadcrumb(menu); */
		
		$("#findPatientNavId").addClass("active");
		
	});
	
	function patientFormSubmit(form) {
 		if (!$("#patientForm").valid()) {
 			return false;
		}
		$.ajax({
			url : basePath + "doctor/savePatient.shtml",
			data : $(form).serialize(),
			type : "post",
			dataType : "json",
			loading : true,
			success : function(data) {// ajax返回的数据
				if (data && data.status) {
					window.location.href = basePath + "/patient/patientDetail.shtml?patientId=" + $("#id").val();
				}
				return false;
			}
		});
		return false;
	}
</script>
</body>
</html>
