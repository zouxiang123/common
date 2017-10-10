<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<input type="hidden" id="cdrParentVersion" value="${clinicalDiagnosisResult.version}" />
<div class="tab-body bg-white" style="margin-bottom: 5px;margin-right: 2px;">
	<!-- 临床诊断（ 慢性肾功能衰竭，急性肾功能衰竭） -->
	<div class="form-item" style="margin-left: -4px;">
		<c:forEach var="obj" items="${nephrosis_type}">
			<input id="clinical${obj.itemCode}" type="radio" name="type" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> />
			<label for="clinical${obj.itemCode}" class="form-span form-radio-label"> ${obj.itemName} </label>
		</c:forEach>
	</div>
</div>
<!-- -----------------------------------------------crf form --------------------------------------------  -->
<form id="crfForm" onsubmit="return formSubmit(this, 'pathologicDiagnosis', true);">
	<input type="hidden" id="crfFormId" name="id" value="${crf.id}" /> <input type="hidden" id="crfFormVersion" name="version" value="${crf.version}" />
	<input type="hidden" id="crfFormParentId" name="fkClinicalDiagnosisResultId" value="${crf.fkClinicalDiagnosisResultId}" /> 
	<input type="hidden" id="crfFormParentVersion" name="parentVersion" value="${clinicalDiagnosisResult.version}" />
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
			<c:forEach var="obj" items="${clinical_pgn}">
				<div class="box-style">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherPgn" maxlength="64">${crf.otherPgn }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="clinical_pgn" name="pgn" value="${crf.pgn }" />
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
			<c:forEach var="obj" items="${clinical_sgn}">
				<div class="box-style">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherSgn" maxlength="64">${crf.otherSgn }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" value="" id="clinical_sgn" name="sgn" />
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
			<c:forEach var="obj" items="${clinical_hereditary_nephropathy}">
				<div class="box-style">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherHereditaryNephropathy" maxlength="64">${crf.otherHereditaryNephropathy }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" value="" id="clinical_hereditary_nephropathy" name="hereditaryNephropathy" />
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
			<c:forEach var="obj" items="${clinical_tin}">
				<div class="box-style">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherTin" maxlength="64">${crf.otherTin }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" value="" id="clinical_tin" name="tin" />
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">有无泌尿系肿瘤？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-item td-radio-margin">
				<c:forEach var="obj" items="${urologicNeoplasms}">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="radio" name="urologicNeoplasms" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-radio-label">${obj.itemName}</label>
				</c:forEach>
			</div>
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">泌尿系统感染和结石</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${un_and_stone}">
				<div class="box-style">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherUnAndStone" maxlength="64">${crf.otherUnAndStone }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" value="" id="un_and_stone" name="unAndStone" />
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">肾脏切除术后</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${renal_resection}">
				<div class="box-style">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherRenalResection" maxlength="64">${crf.otherRenalResection }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" value="" id="renal_resection" name="renalResection" />
		</div>
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">是否原因不明？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-item td-radio-margin">
				<c:forEach var="obj" items="${crfUnknownReason}">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="radio" name="unknownReason" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-radio-label">${obj.itemName}</label>
				</c:forEach>
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
				<textarea rows="3" class="form-control" name="otherReason" maxlength="256">${crf.otherReason }</textarea>
			</div>
		</div>
	</div>
	<button type="button" onclick="buttonSubmit(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>
<!-- -----------------------------------------------serious crf form --------------------------------------------  -->
<form id="seriousCrfForm" onsubmit="return formSubmit(this, 'pathologicDiagnosis', true);">
	<input type="hidden" id="seriousCrfFormId" name="id" value="${seriousCrf.id}" /> <input type="hidden" id="seriousCrfFormVersion" name="version"
		value="${seriousCrf.version}" /> <input type="hidden" id="seriousCrfFormParentId" name="fkClinicalDiagnosisResultId"
		value="${seriousCrf.fkClinicalDiagnosisResultId}" /> <input type="hidden" id="seriousCrfFormParentVersion" name="parentVersion"
		value="${clinicalDiagnosisResult.version}" />

	<div>
		<div class="content-editing-bar">
			<div class="selected"></div>
			<span class="content-select-title">造成原因？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>收起</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-down.png" class="arrow-down">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="display: block;">
			<c:forEach var="obj" items="${serious_crf_reason}">
				<div class="box-style">
					<input id="${obj.pItemCode}seriousCrfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="${obj.pItemCode}seriousCrfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherReason">${seriousCrf.otherReason }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" value="${seriousCrf.reason }" id="serious_crf_reason" name="reason" />
		</div>
	</div>
	<button type="button" onclick="buttonSubmit(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>
<!-- -----------------------------------------------arf form --------------------------------------------  -->
<form id="arfForm" onsubmit="return formSubmit(this, 'pathologicDiagnosis', true);">
	<input type="hidden" id="arfFormId" name="id" value="${arf.id}" /> <input type="hidden" id="arfFormVersion" name="version" value="${arf.version}" />
	<input type="hidden" id="arfFormParentId" name="fkClinicalDiagnosisResultId" value="${arf.fkClinicalDiagnosisResultId}" /> <input type="hidden"
		id="arfFormParentVersion" name="parentVersion" value="${clinicalDiagnosisResult.version}" />
	<div class="panel-group" id="arfClinicalDiagnosisGroup">

		<div>
			<div class="content-editing-bar">
				<div class="not-selected"></div>
				<span class="content-select-title">造成原因？</span>
				<div class="tab-action2" style="margin-top: 14px;">
					<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
				</div>
				<div class="tab-line"></div>
			</div>
			<div class="fill-parent bg-white content-editing-wrap">
				<c:forEach var="obj" items="${arf_reason}">
					<div class="box-style">
						<input id="${obj.pItemCode}arfForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
							for="${obj.pItemCode}arfForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
					</div>
					<c:if test="${obj.itemCode == 00}">
						<div class="form-group textarea-margin other hide">
							<textarea rows="1" class="form-control" name="otherRenalResection">${arf.otherRenalResection }</textarea>
						</div>
					</c:if>
				</c:forEach>
				<input type="hidden" value="${arf.reason}" id="arf_reason" name="reason" />
			</div>
		</div>
		<div>
			<div class="content-editing-bar">
				<div class="not-selected"></div>
				<span class="content-select-title">是否原因不明？</span>
				<div class="tab-action2" style="margin-top: 14px;">
					<span>编辑</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
				</div>
				<div class="tab-line"></div>
			</div>
			<div class="fill-parent bg-white content-editing-wrap">
				<div class="form-item td-radio-margin">
					<c:forEach var="obj" items="${arfUnknownReason}">
						<input id="${obj.pItemCode}arfForm${obj.itemCode}" type="radio" name="unknownReason" value="${obj.itemCode}"
							<c:if test="${obj.isChecked}">checked="checked"</c:if> />
						<label for="${obj.pItemCode}arfForm${obj.itemCode}" class="form-span form-radio-label">${obj.itemName}</label>
					</c:forEach>
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
					<textarea rows="3" class="form-control" name="otherReason">${arf.otherReason }</textarea>
				</div>
			</div>
		</div>
	</div>
	<button type="button" onclick="buttonSubmit(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>
