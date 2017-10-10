<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!-- 新  增 dialog -->
<div class="modal" id="newVah" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">新增通路历史</h4>
				<%-- <div class="dialog-close pull-right" data-dismiss="modal">
					<img src="${COMMON_SERVER_ADDR}/assets/img/dialog-close.png">
				</div> --%>
			</div>
			<div class="modal-body">
				<div class="dialog-body">
				<form id="newVahForm" method="post">
					<c:forEach var="obj" items="${vascular_access_his_type}" varStatus="status">
						<input id="newVahRadio${status.index }" type="radio" name="vahType" value="${obj.itemCode}" />
						<label for="newVahRadio${status.index }" class="form-span form-radio-label">${obj.itemName}</label>
					</c:forEach>
					<div class="group-input">
					<!-- 	<span>部位：</span> <input type="text" id="vahPosition" name="vahPosition" /><br />  -->
						<span>使用寿命：</span> <input type="text" id="serviceLife" name="serviceLife" maxlength="16" /> <br /> 
					<!-- 	<span>使用情况：</span> <input type="text" id="serviceCondition" name="serviceCondition" maxlength="256" /> <br />  -->
						<span>转归原因：</span> <input type="text" id="outcomeReason" name="outcomeReason" maxlength="256" /> <br />
					</div>
				</form>
				</div>
			</div>
			<div class="modal-footer">
				<div class="dialog-btn-wrap">
					<div class="dialog-btn">
						<button type="button" class="pull-right btn" style="margin-right: 6px;" data-dismiss="modal">取 消</button>
					</div>
					<div class="dialog-btn">
						<button type="button" class="pull-left btn btn-def" style="margin-left: 27px;" onclick="addVah()">确 定</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- end of 新  增 dialog -->

<form action="" id="pathwayEstablishEvaluationForm" onsubmit="return formSubmit(this,'buildPathway',true);">
	<input type="hidden" id="pathwayEstablishEvaluationFormId" name="id" value="${pathwayEstablishEvaluation.id}" /> <input type="hidden"
		name="fkPatientId" value="${patientId}" /> <input type="hidden" id="pathwayEstablishEvaluationFormPAId" name="fkPathwayActivityId"
		value="${fkPathwayActivityId}">

	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">病人优势侧</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${dominant_side}">
				<div class="form-item-box margin-bottom-10">
					<input id="dominantSide${obj.itemCode }" type="radio" name="dominantSide" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="dominantSide${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">心脏起搏器安装史</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${cardiac_pacemaker_his}">
				<div class="form-item-box">
					<input id="cardiacPacemakerHis${obj.itemCode }" type="radio" name="cardiacPacemakerHis" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="cardiacPacemakerHis${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">严重充血性心力衰竭病史</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasCongHeartFail}">
				<div class="form-item-box">
					<input id="hasCongHeartFail${obj.itemCode }" type="radio" name="hasCongHeartFail" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="hasCongHeartFail${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">有无外周动脉或静脉插管史</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasPicc}">
				<div class="form-item-box">
					<input id="hasPicc${obj.itemCode }" type="radio" name="hasPicc" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="hasPicc${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">有无糖尿病</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasMody}">
				<div class="form-item-box">
					<input id="hasMody${obj.itemCode }" type="radio" name="hasMody" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
						for="hasMody${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">止血药物使用历史</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body" id="usedAnastalticElement">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${used_anastaltic}">
				<div class="form-item-box margin-bottom-10">
					<input id="${obj.pItemCode}pathwayEstablishEvaluationForm${obj.itemCode}" type="checkbox" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="${obj.pItemCode}pathwayEstablishEvaluationForm${obj.itemCode}"
						class="form-span form-checkbox-label">${obj.itemName}</label>
				</div>
				<c:if test="${obj.itemCode == 00}">
					<div class="margin-right-16 other <c:if test="${!obj.isChecked}">hide</c:if>">
						<textarea class="form-control" rows="1" name="otherUsedAnastaltic" maxlength="64">${pathwayEstablishEvaluation.otherUsedAnastaltic}</textarea>
					</div>
				</c:if>
			</c:forEach>
			<input type="hidden" value="" id="used_anastaltic" name="usedAnastaltic" />
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">有无高凝状态</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasHypercoagulable}">
				<div class="form-item-box">
					<input id="hasHypercoagulable${obj.itemCode }" type="radio" name="hasHypercoagulable" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="hasHypercoagulable${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">合并疾病</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${combined_disease}">
				<div class="form-item-box">
					<input id="combinedDisease${obj.itemCode }" type="radio" name="combinedDisease" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="combinedDisease${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">血管通路历史</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color">编 辑</span>
		</div>
		<div class="tab-add-action" id="newVahShow">
			<span>新 增</span><img src="${COMMON_SERVER_ADDR}/assets/img/new-edit.png" class="new-edit">
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<div class="table-responsive">
				<table class="table table2">
					<tbody id="allVahHis">
						<c:forEach var="obj" items="${allVascularAccessHistory}" varStatus="status">
							<tr>
								<td width="35" class="col-status"><span class="ready"></span></td>
								<td class="col-time">${obj.createTimeShow}</td>
								<td width="60" class="col-img"><div class="col-img-line"></div> <img class="user-photo"
									src="${ctx}/images${obj.imagePath }"></td>
								<td width="85" class="col-name">${obj.itemName }</td>
								<td class="table-line"><span class="td-span-padding margin-top-10">通路类型：${obj.pItemCode}</span> 
								<%-- <span class="td-span-padding margin-top-10">部位：${obj.position}</span> --%>
									<span class="td-span-padding margin-top-10">使用时间：${obj.serviceLife}</span>
									<span class="td-span-padding margin-top-10">流量使用情况：${obj.serviceCondition}</span>
									<span class="td-span-padding margin-top-10">转归及原因：${obj.outcomeReason}</span></td>
								<!-- <td width='150' class='table-line col-btn'><button type='button' class='pull-right btn btn-def btn-bg-red'>删  除</button></td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div id="vahHisAdd" style="display: none;">
			</div>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">心脏瓣膜疾病或修补术</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasHvdOrRepair}">
				<div class="form-item-box">
					<input id="hasHvdOrRepair${obj.itemCode }" type="radio" name="hasHvdOrRepair" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="hasHvdOrRepair${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">是否准备肾移植</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${renalTransplant}">
				<div class="form-item-box">
					<input id="renalTransplant${obj.itemCode }" type="radio" name="renalTransplant" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="renalTransplant${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">有无Allen实验</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasAllenTest}">
				<div class="form-item-box">
					<input id="hasAllenTest${obj.itemCode }" type="radio" name="hasAllenTest" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="hasAllenTest${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">血管影像学检查</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${vas_image_exam_type}">
				<div class="form-item-box">
					<input id="vasImageExamType${obj.itemCode }" type="radio" name="vasImageExamType" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="vasImageExamType${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
			<div class="margin-right-16 margin-top-10">
				<textarea rows="2" class="form-control" placeholder="请输入检查结果" name="vasImageExamResult" maxlength="256">${pathwayEstablishEvaluation.vasImageExamResult}</textarea>
			</div>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">双侧上肢血压</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16 group-input margin-top-0 margin-bottom-0 padding-bottom-0">
			<span>左侧舒张压：</span><input type="text" name="leftLimbSystolicPressure" value="${pathwayEstablishEvaluation.leftLimbSystolicPressure}" /><span class="unit-style">mmHg</span><br /> 
			<span>左侧收缩压：</span><input type="text" name="leftLimbDiastolicPressure" value="${pathwayEstablishEvaluation.leftLimbDiastolicPressure}" /><span class="unit-style">mmHg</span><br /> 
			<span>右侧舒张压：</span><input type="text" name="rightLimbDiastolicPressure" value="${pathwayEstablishEvaluation.rightLimbDiastolicPressure}" /><span class="unit-style">mmHg</span><br /> 
			<span>右侧收缩压：</span><input type="text" name="rightLimbSystolicPressure" value="${pathwayEstablishEvaluation.rightLimbSystolicPressure}" /><span class="unit-style">mmHg</span><br />
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">水肿的评价</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${edema_evaluation}">
				<div class="form-item-box">
					<input id="edemaEvaluation${obj.itemCode }" type="radio" name="edemaEvaluation" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="edemaEvaluation${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">双上肢粗细的比较</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${up_limb_thickness}">
				<div class="form-item-box">
					<input id="upLimbThickness${obj.itemCode }" type="radio" name="upLimbThickness" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="upLimbThickness${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">束止血带显现静脉</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${tonqAppearedVein}">
				<div class="form-item-box">
					<input id="tonqAppearedVein${obj.itemCode }" type="radio" name="tonqAppearedVein" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="tonqAppearedVein${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">侧支静脉的显现</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${collateral_vein_appear}">
				<div class="form-item-box">
					<input id="collateralVeinAppear${obj.itemCode }" type="radio" name="collateralVeinAppear" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="collateralVeinAppear${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">有无中心静脉或外周静脉插管的迹象</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasVenousCannulaSign}">
				<div class="form-item-box">
					<input id="hasVenousCannulaSign${obj.itemCode }" type="radio" name="hasVenousCannulaSign" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="hasVenousCannulaSign${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">上肢、颈部、胸部手术或外伤史</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<div class="margin-right-16">
				<textarea rows="3" class="form-control" name="surgeryOrTraumaHis" maxlength="256">${pathwayEstablishEvaluation.surgeryOrTraumaHis}</textarea>
			</div>
		</div>
	</div>

	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">上肢、胸部、颈部手术或外伤的体征</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<div class="margin-right-16">
				<textarea rows="3" class="form-control" name="surgeryOrTraumaSign" maxlength="256">${pathwayEstablishEvaluation.surgeryOrTraumaSign }</textarea>
			</div>
		</div>
	</div>


	<div class="blank-div"></div>
	<div class="tab-header">
		<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">检查有无心力衰竭的证据</span>
		<div class="tab-action toggle">
			<div class="dividing-line"></div>
			<img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up"> <span class="pull-right action-name default-color">编 辑</span>
		</div>
	</div>
	<div class="tab-body">
		<div class="form-item margin-left-16">
			<c:forEach var="obj" items="${hasHeartFailEvidence}">
				<div class="form-item-box">
					<input id="hasHeartFailEvidence${obj.itemCode }" type="radio" name="hasHeartFailEvidence" value="${obj.itemCode}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="hasHeartFailEvidence${obj.itemCode }" class="form-span form-radio-label">${obj.itemName}</label>
				</div>
			</c:forEach>
		</div>
	</div>

	<button type="button" class="btn btn-def btn-ls center-block" style="margin-top: 20px;" onclick="buttonSubmit(this);">评估完成</button>
</form>

