<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<jsp:include page="../common/report_datepick.jsp" flush="true"></jsp:include>
<link rel="stylesheet" href="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.css">
<script type="text/javascript" src="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.js"></script>
<form action="" id="medicalHistoryForm" onsubmit="return formSubmit(this,'clinicalDiagnosis',true);">
	<input type="hidden" id="medicalHistoryFormId" name="id" value="${medicalHistory.id}" /> <input type="hidden" id="medicalHistoryFormVersion"
		name="version" value="${medicalHistory.version}" />

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">首次透析日期</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-item td-radio-margin" style="margin-left:-10px">
				<!-- <select id="YYYY_firstDialysisDate" class="selectpicker"></select><span class="personal-form-span form-span">年</span> 
				<select id="MM_firstDialysisDate" class="selectpicker"></select><span class="personal-form-span form-span">月</span> 
				<select id="DD_firstDialysisDate" class="selectpicker"></select><span class="personal-form-span form-span">日</span>  -->
				<input type="text" name="firstDialysisDateFrom"    value="${medicalHistory.firstDialysisDate_show}"  onfocus="addDate(this)">
				<input type="hidden" id="medicalHistoryForm_firstDialysisDate" name="firstDialysisDate_show" value="${medicalHistory.firstDialysisDate_show}" />
			</div>
		</div>
		
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">首次透析方式</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-left:40px">
			<div class="form-item td-radio-margin">
				<c:forEach var="obj" items="${first_dialysis_method}" varStatus="status">
					<input type="radio" id="medicalHistoryForm_firstDialysisMethod_${status.index }" name="firstDialysisMethod" value="${obj.value}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="medicalHistoryForm_firstDialysisMethod_${status.index }" class="form-span form-radio-label">${obj.name}</label>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div>
	<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">1.有无脑血管意外史？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-left:40px">
			<div class="form-item td-radio-margin"  id="hasCavRemark">
				<c:forEach var="obj" items="${hasCva}" varStatus="status">
					<input type="radio" id="medicalHistoryForm_hasCva_${status.index }" name="hasCva" value="${obj.value}"   onchange="historyRemark()"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="medicalHistoryForm_hasCva_${status.index }" class="form-span form-radio-label">${obj.name}</label>
				</c:forEach>
				<div  style="display: none" id="hasCavRemarkText">
					<textarea  rows="1" class="form-control" name="hascvaRemark" maxlength="64" >${medicalHistory.hascvaRemark}</textarea>
				</div>
			</div>
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">2.严重出血或出血倾向</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-left:40px">
			<c:forEach var="obj" items="${hemorrhage}">
				<div class="box-style">
					<input id="${obj.type}medicalHistoryForm${obj.value}" type="checkbox" value="${obj.value}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="${obj.type}medicalHistoryForm${obj.value}"
						class="form-span form-checkbox-label">${obj.name}</label>
				</div>
				<c:if test="${obj.value == '00'}">
						<div class="form-group textarea-margin other hide">
							<textarea rows="1" class="form-control" name="hemorrhageRemark" maxlength="64">${medicalHistory.hemorrhageRemark}</textarea>
						</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="hemorrhage" name="hemorrhage" />
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">3.严重心肺功能不全病史</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-left:40px">
			<c:forEach var="obj" items="${heart_defects}">
				<div class="box-style">
					<input id="${obj.type}medicalHistoryForm${obj.value}" type="checkbox" value="${obj.value}"  onchange="historyRemarkA()"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label for="${obj.type}medicalHistoryForm${obj.value}"
						class="form-span form-checkbox-label">${obj.name}</label>
				</div>
				<c:if test="${obj.value == '00'}">
							<div class="form-group textarea-margin other hide">
								<textarea rows="1" class="form-control" name="heartDefectsRemark" maxlength="64">${medicalHistory.heartDefectsRemark}</textarea>
							</div>
				</c:if>
			</c:forEach>
			<input type="hidden" id="heart_defects" name="heartDefects" />
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">4.有无外周血管疾病史？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap"  style="margin-left:40px">
			<div class="form-item td-radio-margin"  id="vascularDisease">
				<c:forEach var="obj" items="${hasVascularDisease}" varStatus="status">
					<input type="radio" id="medicalHistoryForm_hasVascularDisease_${status.index }" name="hasVascularDisease" value="${obj.value}"   onchange="historyRemarkA()"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="medicalHistoryForm_hasVascularDisease_${status.index }" class="form-span form-radio-label">${obj.name}</label>
				</c:forEach>
				<div >
							<textarea rows="1"   style="display: none;" id="vascularDiseaseText"  class="form-control" name="hasvasculardiseaseRemark" maxlength="64">${medicalHistory.hasvasculardiseaseRemark}</textarea>
				</div>
			</div>
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">5.有无严重感染或血源性传染病史？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap"  style="margin-left:40px">
			<div class="form-item td-radio-margin">
				<c:forEach var="obj" items="${hasSeriousDisease}" varStatus="status">
					<input type="radio" id="medicalHistoryForm_hasSeriousDisease_${status.index }" name="hasSeriousDisease" value="${obj.value}"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="medicalHistoryForm_hasSeriousDisease_${status.index }" class="form-span form-radio-label">${obj.name}</label>
				</c:forEach>
			</div>
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">6.有无精神病史？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap"  style="margin-left:40px">
			<div class="form-item td-radio-margin"  id="haspsychosisRemark">
				<c:forEach var="obj" items="${hasPsychosis}" varStatus="status">
					<input type="radio" id="medicalHistoryForm_hasPsychosis_${status.index }" name="hasPsychosis" value="${obj.value}"   onchange="historyRemarkB()"
						<c:if test="${obj.isChecked}">checked="checked"</c:if> />
					<label for="medicalHistoryForm_hasPsychosis_${status.index }" class="form-span form-radio-label">${obj.name}</label>
				</c:forEach>
				<div >
					<textarea  style="display: none;" id="haspsychosisRemarkRemarkText"    rows="1" class="form-control" name="haspsychosisRemark" maxlength="64"   id="psychosisRemarkRemarkText">${medicalHistory.haspsychosisRemark}</textarea>
				</div>	
			</div>
		</div>
	</div>
	
	<div id = "div_opt_id">
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">7.手术史</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
				<div class="tab-action hand margin-right-25" onclick="showAddOpt('addOpt');" >
								<div class="dividing-line"></div>
								<div class="new-btn"></div>
								<span class="pull-right action-name">添加数据</span>
				</div>
				<div class="form-item td-radio-margin" style="margin-left:40px" id="addOpt">
				<c:forEach var="miedical" items="${meList}">
				<table class="table tfoot > tr > td">
					<tr>
						<td>手术日期：<input type="text"   disabled="disabled"   value="${miedical.mhrStartTimeShow}" /> </td>
					</tr>
					<tr>
						<td>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:&nbsp;<input type="text"  disabled="disabled"   name="optName" value="${miedical.mhrStartreasonOrname}"/></td>
					</tr>
					<tr>
						<td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea    rows="1"  class="form-control"  name="optRemark"  maxlength="256"   disabled="disabled" >${miedical.mhrRemark}</textarea></td>
						<td></td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">8.血透史</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
				<div class="tab-action hand margin-right-25"  onclick="showAddXt('addXt');" >
								<div class="dividing-line"></div>
								<div class="new-btn"></div>
								<span class="pull-right action-name">添加数据</span>
				</div>
				<div class="form-item td-radio-margin" style="margin-left:40px" id="addXt">
				<c:forEach var="miedical" items="${xtList}">
				<table class="table tfoot > tr > td">
					<tr>
						<td>开始日期：<input type="text"  disabled="disabled"  name="xtStartTime_show"  value="${miedical.mhrStartTimeShow}"   readonly/> </td>
					</tr>
					<tr>
						<td class="form-item td-radio-margin">
							开始原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${xtStartReason}"   varStatus="status" >
								<div class="box-style">
									  <input  disabled="disabled"   type="radio" value="${obj.value}"  name="mhrMarkType[1].mhrStartreasonOrname${status.index }"  value="${obj.value}" 
									    <c:if test="${miedical.mhrStartreasonOrname==obj.value}">checked="checked" </c:if>  disabled="disabled"/>
									  <label for="${obj.type}medicalHistoryForm${obj.value}" class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
									<div class="form-group textarea-margin other hide">
										<textarea disabled="disabled"  rows="1" class="form-control"   name="xtStartReasonOther" maxlength="64">${miedical.mhrStartortherreason}</textarea>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					
					<tr>
						<td>结束日期：<input type="text"   disabled="disabled"   name="xtEndTime_show" value="${miedical.mhrEndTimeShow}"   readonly/> </td>
					</tr>
					<tr>
						<td class="form-item td-radio-margin">
							结束原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${xtEndReason}"   varStatus="status">
								<div class="box-style">
									  <input disabled="disabled"   type="radio" value="${obj.value}" name="xtEndReason${status.index }"
									  <c:if test="${miedical.mhrEndreason==obj.value}">checked="checked" </c:if>   disabled="disabled"/>
									  <label for="${obj.type}medicalHistoryForm${obj.value}" class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
									<div class="form-group textarea-margin other hide">
										<textarea disabled="disabled"  rows="1" class="form-control"   name="xtEndReasonOther" maxlength="64">${miedical.mhrEndotherreason}</textarea>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					
					<tr>
						<td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea disabled="disabled"  class="form-control"    name="xtRemark"  maxlength="256">${miedical.mhrRemark}</textarea></td>
						<td></td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">9.腹透史</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
				<div class="tab-action hand margin-right-25" onclick="showAddFt('addFt');" >
						<div class="dividing-line"></div>
						<div class="new-btn"></div>
						<span class="pull-right action-name">添加数据</span>
				</div>
				<div class="form-item td-radio-margin" style="margin-left:40px" id="addFt">
				<c:forEach var="miedical" items="${ftList}">
				<table class="table tfoot > tr > td">
					<tr>
						<td>开始日期：<input type="text"  name="ftStartTime_show"  disabled="disabled"   value="${miedical.mhrStartTimeShow}"   readonly/> </td>
					</tr>
					<tr>
						<td class="form-item td-radio-margin">
							开始原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${ftStartReason}"   varStatus="status" >
								<div class="box-style">
									  <input  disabled="disabled"   type="radio" value="${obj.value}"  name="mhrMarkType[1].mhrStartreasonOrname${status.index }"  value="${obj.value}" 
									    <c:if test="${miedical.mhrStartreasonOrname==obj.value}">checked="checked" </c:if>  disabled="disabled"/>
									  <label for="${obj.type}medicalHistoryForm${obj.value}" class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
									<div class="form-group textarea-margin other hide">
										<textarea disabled="disabled"  rows="1" class="form-control"   name="xtStartReasonOther" maxlength="64">${miedical.mhrStartortherreason}</textarea>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>结束日期：<input type="text"  disabled="disabled"   name="ftEndTime_show" value="${miedical.mhrEndTimeShow}"   readonly/> </td>
					</tr>
					
					<tr>
						<td class="form-item td-radio-margin">
							结束原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${ftEndReason}"  varStatus="status">
								<div class="box-style">
									  <input  disabled="disabled"   type="radio" value="${obj.value}" name="ftEndReason${status.index }"
									  <c:if test="${miedical.mhrEndreason==obj.value}">checked="checked"</c:if>  />
									  <label for="${obj.type}ftEndReason${obj.value}"  class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
									<div class="form-group textarea-margin other hide">
										<textarea rows="1" class="form-control"   disabled="disabled"   name="ftEndReasonOther" maxlength="64">${miedical.mhrEndotherreason}</textarea>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					
					<tr>
						<td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea disabled="disabled"  class="form-control"  name="ftRemark"  maxlength="256"  disabled="disabled">${miedical.mhrRemark}</textarea></td>
						<td></td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">10.肾移植史</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
				<div class="tab-action hand margin-right-25" onclick="showAddSyz('addSyz');" >
								<div class="dividing-line"></div>
								<div class="new-btn"></div>
								<span class="pull-right action-name">添加数据</span>
				</div>
				<div class="form-item td-radio-margin" style="margin-left:40px" id="addSyz">
				<c:forEach var="miedical" items="${syzList}">
				<table class="table tfoot > tr > td">
					<tr>
						<td>开始日期：<input type="text"  disabled="disabled"   name="syzStartTime_show" value="${miedical.mhrStartTimeShow}"   readonly/> </td>
					</tr>
					
					<tr>
						<td>结束日期：<input type="text"  disabled="disabled"    name="syzEndTime_show" value="${miedical.mhrEndTimeShow}"   readonly/> </td>
					</tr>
					
					<tr>
					  <td class="form-item td-radio-margin">
							结束原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 <c:forEach var="obj" items="${syzEndReason}"   varStatus="status">
								<div class="box-style">
									  <input    type="radio"  id="${obj.type}syzEndReason${obj.value}"  value="${obj.value}" name="syzEndReason${status.index }"   disabled="disabled" 
									  <c:if test="${obj.value==miedical.mhrEndreason}">checked="checked"</c:if>   />
									  <label for="${obj.type}syzEndReason${obj.value}" class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
									<div class="form-group textarea-margin other hide">
										<textarea rows="1" class="form-control"   disabled="disabled"    name="syzEndReasonOther" maxlength="64">${miedical.mhrEndotherreason}</textarea>
									</div>
								</c:if>
							</c:forEach>
						</td> 
					</tr>
					<tr>
						<td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea    class="form-control"  name="syzRemark"  disabled="disabled"   maxlength="256">${miedical.mhrRemark}</textarea></td>
						<td></td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">11.过敏史</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
				<div class="tab-action hand margin-right-25" onclick="showAddGm('addGm');" >
								<div class="dividing-line"></div>
								<div class="new-btn"></div>
								<span class="pull-right action-name">添加数据</span>
				</div>
				<div class="form-item td-radio-margin" style="margin-left:40px" id="addGm">
				<c:forEach var="miedical" items="${gmList}"  varStatus="status" >
				<table class="table tfoot > tr > td" >
					</tr>
						<td>录入日期：<input type="text"  disabled="disabled"  name="gmEnterTime_show" value="${miedical.mhrStartTimeShow}"   readonly/> </td>
					</tr>
					<tr>
						<td class="form-item td-radio-margin">
							过敏源：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${gmResouce}"   varStatus="status" >
								<div class="box-style">
									  <input  disabled="disabled"  type="radio"   id="${obj.type}gmReason${obj.value}${status.index}"   value="${obj.value}" name="gmResouce${status.index}"
									  <c:if test="${miedical.mhrEndreason==obj.value}">checked="checked"</c:if> />
									  <label for="${obj.type}gmReason${obj.value}${status.index}" class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
									<div class="form-group textarea-margin other hide">
										<textarea rows="1" class="form-control" name="gmResouceOther"  disabled="disabled"  maxlength="64">${miedical.mhrEndotherreason}</textarea>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:&nbsp;<input type="text"  name="gmName"  disabled="disabled"  value="${miedical.mhrStartreasonOrname}"/></td>
					</tr>
					<tr>
						<td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea  class="form-control"  name="gmRemark"  disabled="disabled"  maxlength="256">${miedical.mhrRemark}</textarea></td>
						<td></td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	</div>
	
	
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title" style="vertical-align: top;">12.传染病史</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
				<div class="tab-action hand margin-right-25" onclick="showAddCrb('addCrb');" >
								<div class="dividing-line"></div>
								<div class="new-btn"></div>
								<span class="pull-right action-name">添加数据</span>
				</div>
				<div class="form-item td-radio-margin" style="margin-left:40px"  id="addCrb">
				<c:forEach var="miedical" items="${crbList}">
				<table class="table tfoot > tr > td">
					<tr>
						<td>诊断日期：<input type="text"  value="${miedical.mhrStartTimeShow}" readonly  disabled="disabled"/> </td>
					</tr>
					<tr>
						<td class="form-item td-radio-margin">
							诊断名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${bs_crbzdmc}"  varStatus="status" >
								<div class="box-style"   name="crb">
									  <input id="${obj.type}crbEndReason${obj.value}${status.index}"   type="checkbox" value="${obj.value}"   name="ftEndReason"
									  <c:if test="${fn:contains(miedical.mhrStartreasonOrname,obj.value)}">checked="checked"</c:if>  disabled="disabled" />
									  <label for="${obj.type}crbEndReason${obj.value}${status.index}"   class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
									<div class="form-group textarea-margin other hide">
										<textarea rows="1" class="form-control"   maxlength="64">${miedical.mhrStartortherreason}</textarea>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</tr> 
					<tr>
						<td class="form-item td-radio-margin">
							活动状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${bs_crbhdzt}"  varStatus="status">
								<div class="box-style">
									  <input  type="radio"  id="${obj.type}crbEndReason${obj.value}"  value="${obj.value}" name="crbDiaStatus${status.index }"  
									  <c:if test="${miedical.activitystatus==obj.value}">checked="checked"</c:if>   disabled="disabled"/>
									  <label for="${obj.type}crbEndReason${obj.value}" class="form-span form-radio-label">${obj.name}</label>
								</div>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="">
							治疗情况：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="obj" items="${bs_crbzlqk}"  varStatus="status">
								<div class="box-style">
									  <input  type="radio"  id="${obj.type}crbEndReason${obj.value}"  value="${obj.value}" name="crbCase${status.index }"  disabled="disabled"
									  <c:if test="${miedical.mhrEndreason==obj.value}">checked="checked"</c:if> />
									  <label for="${obj.type}crbEndReason${obj.value}" class="form-span form-radio-label">${obj.name}</label>
								</div>
								<c:if test="${obj.value == '00'}">
										<div class="form-group textarea-margin other hide">
											<textarea rows="1"  class="form-control"  maxlength="64"  disabled="disabled">${miedical.mhrEndotherreason}</textarea>
										</div>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					
					<tr>
						<td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea  class="form-control" name="crbRemark"  maxlength="256"  disabled="disabled">${miedical.mhrRemark}</textarea></td>
						<td></td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</div>
	</div>

	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title">13.其它病史？</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>编辑</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap">
			<div class="form-group textarea-margin other">
				<textarea rows="3" class="form-control" name="other" maxlength="256">${medicalHistory.other}</textarea>
			</div>
		</div>
	</div>
	
	<button type="button" onclick="checkTime(this);" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">保存并下一步</button>
</form>

<script src="${ctx }/assets/js/system/medicalHistoryRemark.js?version=${version}"></script>