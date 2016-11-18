<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<form id="ckdStageForm"
	onsubmit="if (!checkCkdAki()) return false;return formSubmit(this, '<c:if test="${operation != 'secondAdd' }">cureComplication</c:if><c:if test="${operation == 'secondAdd' }">done</c:if>', true);">
	<input type="hidden" id="ckdStageFormId" name="id" value="${ckdStage.id}" /> <input type="hidden" id="ckdStageFormVersion" name="version"
		value="${ckdStage.version}" />


	<div class="list-item margin-bottom-20 bg-white">

		<div class="tab-body">
			<div class="form-item margin-left-16">
				<div class="form-item-box">
					<input id="ckdStageForm_ckd" type="radio" name="ckdAki" value="ckd" <c:if test="${!empty ckdStage.ckdStage}">checked</c:if>> <label
						for="ckdStageForm_ckd" class="form-span form-radio-label">CKD分期</label> <input id="ckdStageForm_aki" type="radio" name="ckdAki" value="aki"
						<c:if test="${!empty ckdStage.akiType}">checked</c:if>> <label for="ckdStageForm_aki" class="form-span form-radio-label">AKI分期</label>
				</div>
				<div class="form-checkbox-group padding-left-30" id="divCkd">
					<div class="form-checkbox-group padding-left-30">
						<div class="form-item-box">
							<c:forEach var="obj" items="${ckd_stage}">
								<input type="radio" id="ckdStageForm_ckdstage${obj.value}" name="ckdStage" value="${obj.value}" <c:if test="${obj.isChecked}">checked</c:if> />
								<label for="ckdStageForm_ckdstage${obj.value}" class="form-span form-checkbox-label">${obj.name}</label>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="form-checkbox-group padding-left-30" id="divAki">
					<div class="form-item-box">
						<c:forEach var="obj" items="${aki_type}">
							<input id="ckdStageForm_aki${obj.value}" type="radio" name="akiType" value="${obj.value}" <c:if test="${obj.isChecked}">checked</c:if> />
							<label for="ckdStageForm_aki${obj.value}" class="form-span form-checkbox-label">${obj.name}</label>
						</c:forEach>
					</div>
					<div class="form-checkbox-group padding-left-30" id="akiStageRifle">
						<div class="form-item-box">
							<c:forEach var="obj" items="${aki_stage_rifle}">
								<input type="radio" id="ckdStageForm_aki_stage_rifle${obj.value}" name="akiStage" value="${obj.value}"
									<c:if test="${obj.isChecked}">checked</c:if> />
								<label for="ckdStageForm_aki_stage_rifle${obj.value}" class="form-span form-checkbox-label">${obj.name}</label>
							</c:forEach>
						</div>
					</div>
					<div class="form-checkbox-group padding-left-30" id="akiStageAK">
						<div class="form-item-box">
							<c:forEach var="obj" items="${aki_stage}">
								<input type="radio" id="ckdStageForm_aki_stage${obj.value}" name="akiStage" value="${obj.value}" <c:if test="${obj.isChecked}">checked</c:if> />
								<label for="ckdStageForm_aki_stage${obj.value}" class="form-span form-checkbox-label">${obj.name}</label>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<button type="button" onclick="buttonSubmit(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>

