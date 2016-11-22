<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<form id="cureComplicationForm" onsubmit="return formSubmit(this, 'otherDiagnosis', true);" >
	<input type="hidden" id="cureComplicationFormId" name="id" value="${cureSymptomAndCondition.id}" /> 
	<input type="hidden" id="cureComplicationFormVersion" name="version" value="${cureSymptomAndCondition.version}" />
	
<%-- 	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">治疗前合并症</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${main_symptom}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="otherSymptom" maxlength="64">${cureSymptomAndCondition.otherSymptom }</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="main_symptom" name="mainSymptom" value="${cureSymptomAndCondition.mainSymptom }" />
		</div>
	</div> --%>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title"> 请选择以下治疗前合并症  :</span>
			<div class="tab-action2" style="margin-top: 14px;">
				
			</div>
			<div class="tab-line"></div>
		</div>
	<!-- 	<div class="fill-parent bg-white content-editing-wrap">
			
		</div> -->
	</div>
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">1.骨矿物质代谢紊乱</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${gkwzdxwl_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm1${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="gkwzdxwl"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm1${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="gkwzdxwlOther" maxlength="64">${cureSymptomAndCondition.gkwzdxwlOther}</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">2.淀粉样变性</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${dfybx_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="dfypx"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="dfypxOther" maxlength="64">${cureSymptomAndCondition.dfypxOther}</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">3.呼吸系统</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${hxxt_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="hxxt" 
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="hxxtOther" maxlength="64">${cureSymptomAndCondition.hxxtOther }</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">4.心血管系统</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${xxgxt_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="xxgxt"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="xxgxtOther" maxlength="64">${cureSymptomAndCondition.xxgxtOther }</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">5.神经系统</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${sjxt_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="sjxt" 
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="sjxtOther" maxlength="64">${cureSymptomAndCondition.sjxtOther }</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">6.是否皮肤瘙痒？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-item td-radio-margin">
				<c:forEach var="obj" items="${pfsy_info}">
					<input id="${obj.pItemCode}crfForm${obj.itemCode}" type="radio" name="pfsy" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="${obj.pItemCode}crfForm${obj.itemCode}" class="form-span form-radio-label">${obj.itemName}</label>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">7.血液系统</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${xyxt_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="xyxt"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="xyxtOther" maxlength="64">${cureSymptomAndCondition.xyxtOther }</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">8.合并肿瘤</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${hbzl}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${hbzl_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="hbzl"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="hbzlOther" maxlength="64">${cureSymptomAndCondition.hbzlOther}</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">9.合并感染</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${hbgr_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="hbgr"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="hbgrOther" maxlength="64">${cureSymptomAndCondition.hbgrOther }</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">10.自身免疫性疾病</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${zsmyxjb_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="zsmyxjb"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="zsmyxjbOther" maxlength="64">${cureSymptomAndCondition.zsmyxjbOther }</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">11.内分泌及代谢系统</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<c:forEach var="obj" items="${nfmjdxxt_info}">
				<div class="box-style">
					<input id="${obj.pItemCode}cureComplicationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}" name="nfmjdxxt"
					 <c:if test="${obj.isChecked}">checked="checked"</c:if> />
					  <label
						for="${obj.pItemCode}cureComplicationForm${obj.itemCode}" class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="form-group textarea-margin other hide">
						<textarea rows="1" class="form-control" name="nfmjdxxtOther" maxlength="64">${cureSymptomAndCondition.nfmjdxxtOther }</textarea>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">12.治疗情况</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-group textarea-margin other">
				<textarea rows="3" class="form-control" name="treatmentCondition" maxlength="512">${cureSymptomAndCondition.treatmentCondition}</textarea>
			</div>
		</div>
	</div>
	
	<button type="button" onclick="buttonSubmit(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>

