<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<form id="otherDiagnosisForm" onsubmit="return formSubmit(this, 'done', true);" >
	<input type="hidden" id="otherDiagnosisFormId" name="id" value="${otherDiagnosisResult.id}" /> 
	<input type="hidden" id="otherDiagnosisFormVersion" name="version" value="${otherDiagnosisResult.version}" />
	
	<div>		
		<div class="content-editing-bar">
			<div class="selected"></div>
			<span class="content-select-title">其他诊断内容</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>收起</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-down.png" class="arrow-down">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="display: block;">
			<div class="form-group textarea-margin other">
				<textarea rows="3" class="form-control" name="content" maxlength="256">${otherDiagnosisResult.content}</textarea>
			</div>
		</div>
	</div>
	<button type="button" onclick="buttonSubmit(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>
