<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<form id="pathologicDiagnosisForm" onsubmit="return formSubmit(this, 'ckdAki', true);">
	<input type="hidden" id="pathologicDiagnosisFormId" name="id" value="${pathologicDiagnosisResult.id}" /> 
	<input type="hidden" id="pathologicDiagnosisFormVersion" name="version" value="${pathologicDiagnosisResult.version}" />
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">是否进行了肾活检检查？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-item td-radio-margin">
				<input type="radio" id="hasRenalBiopsypathologicDiagnosisForm1" name="hasRenalBiopsy" value="true" <c:if test="${pathologicDiagnosisResult.hasRenalBiopsy}">checked</c:if> />
				<label for="hasRenalBiopsypathologicDiagnosisForm1" class="form-span form-radio-label">是</label>
				<input type="radio" id="hasRenalBiopsypathologicDiagnosisForm2" name="hasRenalBiopsy" value="false" <c:if test="${!pathologicDiagnosisResult.hasRenalBiopsy}">checked</c:if> />
				<label for="hasRenalBiopsypathologicDiagnosisForm2" class="form-span form-radio-label">否</label>
			</div>
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">原发性肾小球疾病</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${pathology_pgn}">
				<div class="box-style">
					<input id="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherPgn" maxlength="64">${pathologicDiagnosisResult.otherPgn }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="pathology_pgn" name="pgn" value="${pathologicDiagnosisResult.pgn }" />
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">继发性肾小球疾病</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${pathology_sgn}">
				<div class="box-style">
					<input id="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherSgn" maxlength="64">${pathologicDiagnosisResult.otherSgn }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="pathology_sgn" name="sgn" value="${pathologicDiagnosisResult.sgn }" />
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">遗传性及先天性肾病</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${pathology_hereditary_nephropathy}">
				<div class="box-style">
					<input id="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherHereditaryNephropathy" maxlength="64">${pathologicDiagnosisResult.otherHereditaryNephropathy }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="pathology_hereditary_nephropathy" name="hereditaryNephropathy" value="${pathologicDiagnosisResult.hereditaryNephropathy }" />
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">肾小管间质疾病</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${pathology_tin}">
				<div class="box-style">
					<input id="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}pathologicDiagnosisForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherTin" maxlength="64">${pathologicDiagnosisResult.otherTin }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="pathology_tin" name="tin" value="${pathologicDiagnosisResult.tin }" />
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">妊娠性肾病？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-item td-radio-margin">
				<input type="radio" id="hasNsp_pathologicDiagnosisForm1" name="hasNsp" value="true" <c:if test="${pathologicDiagnosisResult.hasNsp}">checked</c:if> />
				<label for="hasNsp_pathologicDiagnosisForm1" class="form-span form-radio-label">有</label>
				<input type="radio" id="hasNsp_pathologicDiagnosisForm2" name="hasNsp" value="false" <c:if test="${!pathologicDiagnosisResult.hasNsp}">checked</c:if> />
				<label for="hasNsp_pathologicDiagnosisForm2" class="form-span form-radio-label">无</label>
			</div>
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">移植肾疾病？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
		
			<div class="form-item td-radio-margin">
				<input type="radio" id="hasTkd_pathologicDiagnosisForm1" name="hasTkd" value="true" <c:if test="${pathologicDiagnosisResult.hasTkd}">checked</c:if> />
				<label for="hasTkd_pathologicDiagnosisForm1" class="form-span form-radio-label">有</label>
				<input type="radio" id="hasTkd_pathologicDiagnosisForm2" name="hasTkd" value="false" <c:if test="${!pathologicDiagnosisResult.hasTkd}">checked</c:if> />
				<label for="hasTkd_pathologicDiagnosisForm2" class="form-span form-radio-label">无</label>
			</div>
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">其他病因？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-group textarea-margin other">
				<textarea rows="3" class="form-control" name="otherReason" maxlength="256">${pathologicDiagnosisResult.otherReason}</textarea>
			</div>
		</div>
	</div>
	
	<button type="button" onclick="buttonSubmit(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>
