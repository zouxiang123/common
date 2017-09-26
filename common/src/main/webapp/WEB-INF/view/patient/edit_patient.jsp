<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>编辑信息</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12" style="padding:0px;">
				<form action="${ctx }/system/uploadImage.shtml" onsubmit="return uploadImg(this);" enctype="multipart/form-data" method="post" id ="imageUploadForm">
					<input type="file" id="up_img" name="image" style="display:none;" accept="image/*"/>
					<input type="hidden" id="id" name="id" value="${patient.id }">
				</form>
				<form id="patientForm" onsubmit="return false;">
					<input type="hidden" id="tempImagePath" name="tempImagePath" />
					<jsp:include page="edit_patient_basic.jsp"></jsp:include>
					<ul class="pager center-block">
						<button type="button" id="btnCancel" class="btn btn-ls" style="margin-top: 20px;" onclick="window.location.reload(true);">取消</button>
						<button type="button" id="btnSave" class="btn btn-def btn-ls" style="margin-top: 20px;" onclick="savePatient();">保存</button>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	</script>
</body>
</html>
