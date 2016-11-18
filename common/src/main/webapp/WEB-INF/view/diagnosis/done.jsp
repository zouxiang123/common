<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>完成</title>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(function() {

		$(":checkbox").attr("disabled", true);
		$(":radio").attr("disabled", true);

 		$("#preview .tab-action.toggle").each(function() {
			var tabBody = $(this).parent().next();
			if (tabBody.addClass("tab-body")) {
				if ($(this).find("img").hasClass("arrow-up")) {
					$(this).find("span").html("编辑");
					$(this).parent().find(".tip-line").css("background", "#31AAFF");
					$(this).find("img").attr("src", contextPath + "/assets/img/arrow-down.png").removeClass("arrow-up").addClass("arrow-down");
					tabBody.show();
				}
			}
		}); 
		
		$(".personal-value").each(function() {
			if ($(this).text() == "") {
				$(this).text("无");
			}
		});
		
		$(".tab-body").each(function() {
			
			if ($(this).html().trim() == "") {
				$(this).parent().hide();
			}
		});
		
		$("#doneForm_btnFinish").bind(
						"click",
						function() {
							if (isEmpty($("#fkPatientDiagnosisId").val())) {
								showError("请先添加诊断信息");
								return false;
							}
							//删除url栈中的最后一条数据
							removeUrlStackLast();
							parent.showConfirm("确认完成所有的步骤吗？", function() {
									$.ajax({
									url : basePath + "doctor/saveFirstDone.shtml",
									data : "fkPatientDiagnosisId=" + $("#fkPatientDiagnosisId").val(),
									type : "post",
									dataType : "json",
									loading : true,
									success : function(data) {// ajax返回的数据
										if (data) {
											var barLen = window.parent.$('#contentBar').children("div").length;
											if (barLen == 8) {
											 	/* window.parent.window.location.href = contextPath + "/patient/getUnfinished.shtml"; */
												window.parent.window.location.href = basePath + "/patient/diagnosisInfo.shtml?patientId="
												+ window.parent.$("#fkPatientId").val(); 
											} else if (barLen == 4) {
											 	window.parent.window.location.href = contextPath + "/patient/diagnosisInfo.shtml?patientId=" + window.parent.$("#fkPatientId").val();
											}
											var tip = "<div id='tip' align='right'><font size='5px;' color='red'>恭喜你，保存成功</font></div>";
											$("#done").append(tip);
											$("#tip").show(300).delay(1200).hide(300);
										}
									}
								});
							});
						});
	});
	function jump(tabId) {
		parent.tabSwitch("contentBar", tabId);
	}
</script>
</head>
<body style="padding-top: 0px">
	<input type="hidden" id="fkPatientDiagnosisId" name="fkPatientDiagnosisId" value="${fkPatientDiagnosisId }" />
    <div class="modal" id="SystemDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
           <div class="modal-message-layout">
            <div id="modal-icon" class="modal-icon"></div>
            <span class="modal-message modal-messages"></span>
           </div>
          </div>
          <div class="modal-footer">
            <span class="dialog-btn-close dialog-btn-size" data-dismiss="modal">取消</span>
            <span class="dialog-btn-ok dialog-btn-size">确定</span>
          </div>
        </div>
      </div>
    </div>
	<div class="container-fluid" style="padding-right:0px">
		<div class="row" id="preview">

			<div class="list-item margin-bottom-20 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">基本信息</span>
					<div class="tab-action">
						<div class="dividing-line"></div>
					</div>
				</div>
				<div class="tab-body">
					<div class="fill-parent center">
						<div id="imgdiv" style="margin-bottom: -12px;">
							<c:if test="${empty patient.imagePath }">
								<img id="imgShow" class="personal-photo hand" onclick="up_img.click();" src="${ctx}/assets/img/default-user.png">
							</c:if>
							<c:if test="${!empty patient.imagePath }">
								<img id="imgShow" class="personal-photo hand" onclick="up_img.click();" src="${ctx}/images${patient.imagePath }">
							</c:if>
						</div>
						<p class="personal-name">${patient.name }</p>
						
						<div class="table-responsive">
							<table class="personal-table table" id="patient">
								<tbody>
									<tr>
										<td width="360"></td>
										<td width="150" class="personal-key">出生日期：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.birthdayShow }</td>
										<td></td>
										<td width="150" class="personal-key">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
										<td width="18"></td>
										<td width="200" class="personal-value"><c:if test="${ patient.sex=='M'}">男</c:if> <c:if test="${ patient.sex=='F'}">女</c:if></td>
										<td width="300"></td>
									</tr>
									
									<tr>
										<td width="360"></td>
										<td width="150" class="personal-key">证件类型：</td>
										<td width="18"></td>
										<td width="200" class="personal-value"><c:if test="${ patient.idType=='1'}">身份证号</c:if> <c:if test="${ patient.idType=='2'}">护照</c:if>
											<c:if test="${ patient.idType=='3'}">其它</c:if></td>
										<td></td>
										<td width="150" class="personal-key">证件号码：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.idNumber }</td>
										<td width="300"></td>
									</tr>

									<tr>
										<td width="360"></td>
										<td width="150" class="personal-key">联系方式：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.mobileShow }</td>
										<td></td>
										<td width="150" class="personal-key">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.age }岁</td>
										<td width="300"></td>
									</tr>

									<tr>
										<td width="360"></td>
										<td width="150" class="personal-key">出&nbsp;&nbsp;生&nbsp;&nbsp;地：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.provinceName }&nbsp;&nbsp;${patient.countyName }</td>
										<td></td>
										<td width="150" class="personal-key">详细地址：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.address }</td>
										<td width="300"></td>
									</tr>
									
									<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">&nbsp;&nbsp;工作单位：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.workUnit }</td>
												<td></td>
												<td width="150" class="personal-key">长期/临时患者：</td>
												<td width="18"></td>
												<td width="200" class="personal-value"><c:if test="${patient.isTemp }">临时</c:if>
													<c:if test="${patient.isTemp ==false }">长期</c:if></td>
												<td width="300"></td>
											</tr>
									
									<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">家属姓名：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyContacts }</td>
												<td></td>
												<td width="150" class="personal-key">家属电话一：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyMobileShow }</td>
												<td width="300"></td>
											</tr>
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">家属电话二：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyMobileShow2 }</td>
												<td></td>
												<td width="150" class="personal-key">家属电话三：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyMobileShow3 }</td>
												<td width="300"></td>
											</tr>

											<c:forEach var="ptCard" items="${patientCardList}" varStatus="status">
												<c:forEach var="obj" items="${medicare_card_type}">
													<c:if test="${obj.value==ptCard.cardType}">
														<c:if test="${status.index%2==0 }">
															<tr>
																<td width="360"></td>
																<td width="150" class="personal-key">${obj.name}：</td>
																<td width="18"></td>
																<td width="200" class="personal-value">${ptCard.cardNo}</td>
														</c:if>
														<c:if test="${status.index%2!=0 }">
															<td></td>
															<td width="150" class="personal-key">${obj.name}：</td>
															<td width="18"></td>
															<td width="200" class="personal-value">${ptCard.cardNo}</td>
															<td width="300"></td>
														</c:if>
														<c:if test="${status.index%2!=0 || status.last}">
															</tr>
														</c:if>
													</c:if>
												</c:forEach>
											</c:forEach>
									<%-- <tr>
										<td width="360"></td>
										<td width="150" class="personal-key">住院号：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.admissionNumber }</td>
										<td></td>
										<td width="150" class="personal-key">门诊号：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.outpatientNumber }</td>
										<td width="300"></td>
									</tr>
									<tr>
										<td width="360"></td>
										<td width="150" class="personal-key">医保卡类别：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.medicareCardTypeShow }</td>
										<td></td>
										<td width="150" class="personal-key">医保卡号：</td>
										<td width="18"></td>
										<td width="200" class="personal-value">${patient.medicareCard }</td>
										<td width="300"></td>
									</tr> --%>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="list-item margin-bottom-20 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">病史询问</span>
					<div class="tab-action">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color" onclick="jump('medicalHistory')">编 辑</span>
					</div>
				</div>
				<div class="tab-body" style="padding-left: 30px;">
					<c:if test="${ !empty medicalHistory.firstDialysisDate_show}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">首次透析日期：</span>
							<div class="form-item-box">
								<label class="form-span form-radio-label">${medicalHistory.firstDialysisDate_show }</label>
							</div>
						</div>
					</c:if>
					<c:if test="${ !empty medicalHistory.firstDialysisMethod}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">首次透析方式：</span>
							<c:forEach var="obj" items="${first_dialysis_method}">
								<c:if test="${obj.isChecked}">
									<input type="radio" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${ medicalHistory.hasCva}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">有无脑血管意外史：</span>
							<div class="form-item-box">
								<input type="radio" checked> <label class="form-span form-radio-label">有
										<c:if test="${ !empty medicalHistory.hascvaRemark}">: ${medicalHistory.hascvaRemark }</c:if>
								</label>
							</div>
						</div>
					</c:if>
					<c:if test="${ !empty medicalHistory.hemorrhage}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">严重出血或出血倾向：</span>
							<c:forEach var="obj" items="${hemorrhage}">
								<c:if test="${obj.isChecked}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}
										<c:if test="${ !empty medicalHistory.hemorrhageRemark&&obj.value=='00'}">: ${medicalHistory.hemorrhageRemark}</c:if>
									</label>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${ !empty medicalHistory.heartDefects}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">严重心肺功能不全病史：</span>
							<c:forEach var="obj" items="${heart_defects}">
								<c:if test="${obj.isChecked}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}
										<c:if test="${ !empty medicalHistory.heartDefectsRemark&&obj.value=='00'}">: ${medicalHistory.heartDefectsRemark}</c:if>
									</label>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${ medicalHistory.hasVascularDisease}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">有无外周血管疾病史：</span>
							<div class="form-item-box">
								<input type="radio" checked> <label class="form-span form-radio-label">有
									<c:if test="${ !empty medicalHistory.hasvasculardiseaseRemark}">: ${medicalHistory.hasvasculardiseaseRemark}</c:if>
								</label>
							</div>
						</div>
					</c:if>
					<c:if test="${ medicalHistory.hasSeriousDisease}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">有无严重感染或血源性传染病史：</span>
							<div class="form-item-box">
								<input type="radio" checked> <label class="form-span form-radio-label">有</label>
							</div>
						</div>
					</c:if>
					<c:if test="${ medicalHistory.hasPsychosis}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">有无精神病史：</span>
							<div class="form-item-box">
								<input type="radio" checked> <label class="form-span form-radio-label">有
									<c:if test="${ !empty medicalHistory.haspsychosisRemark}">: ${medicalHistory.haspsychosisRemark }</c:if>
								</label>
							</div>
						</div>
					</c:if>
					
					<c:if test="${!empty meList}">
						<c:forEach var="miedical" items="${meList}"  >
							<c:if test="${ !empty miedical.mhrStartTimeShow}">
								<div class="form-item">
									<span class="form-span form-title width-180 text-right" style="vertical-align: top;">手术史：</span>
									<div class="form-item-box">
										<label class="form-span form-radio-label">手术时间：${miedical.mhrStartTimeShow}</label>
										<c:if test="${ !empty miedical.mhrStartreasonOrname}">
											<label class="form-span form-radio-label">手术名称：${ miedical.mhrStartreasonOrname}</label>
										</c:if>
										<c:if test="${ !empty miedical.mhrRemark}">
											<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
										</c:if>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${!empty xtList}">
					<!-- 血透史 -->
						<c:forEach var="miedical" items="${xtList}"  >
							<c:if test="${ !empty miedical.mhrStartTimeShow}">
								<div class="form-item">
									<span class="form-span form-title width-180 text-right" style="vertical-align: top;">血透史：</span>
									<div class="form-item-box">
										<label class="form-span form-radio-label">开始时间：${ miedical.mhrStartTimeShow}</label>
										
										<c:forEach var="obj" items="${xtStartReason}">
												<c:if test="${!empty miedical.mhrStartreasonOrname&& obj.value==miedical.mhrStartreasonOrname}">
													<label class="form-span form-radio-label">开始原因：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label><br/>
												</c:if>
												<c:if test="${!empty miedical.mhrStartreasonOrname && miedical.mhrStartreasonOrname == '00'&& obj.value == '00'}">
													<label class="form-span form-radio-label">其他开始原因：${miedical.mhrStartortherreason}</label><br/>
												</c:if>
										</c:forEach>
										<label class="form-span form-radio-label">结束时间：${miedical.mhrEndTimeShow}</label>
										
										<c:forEach var="obj" items="${xtEndReason}">
												<c:if test="${!empty miedical.mhrEndreason && obj.value ==miedical.mhrEndreason}">
													<label class="form-span form-radio-label">结束原因：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label><br/>
												</c:if>
												<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.value == '00'}">
													<label class="form-span form-radio-label">其他结束原因：${miedical.mhrEndotherreason}</label><br/>
												</c:if>
										</c:forEach>
										<c:if test="${ !empty miedical.mhrRemark}">
											<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
										</c:if>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${!empty ftList}">
						<!-- 腹透史 -->
						<c:forEach var="miedical" items="${ftList}"  >
							<c:if test="${ !empty miedical.mhrStartTimeShow}">
								<div class="form-item">
									<span class="form-span form-title width-180 text-right"  style="vertical-align: top;">腹透史：</span>
									<div class="form-item-box">
									
										<label class="form-span form-radio-label">开始时间：${ miedical.mhrStartTimeShow}</label><br/>
										
										<c:forEach var="obj" items="${ftStartReason}">
												<c:if test="${!empty miedical.mhrStartreasonOrname&& obj.value==miedical.mhrStartreasonOrname}">
													<label class="form-span form-radio-label">开始原因：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label><br/>
												</c:if>
												<c:if test="${!empty miedical.mhrStartreasonOrname && miedical.mhrStartreasonOrname == '00'&& obj.value == '00'}">
													<label class="form-span form-radio-label">其他开始原因：${miedical.mhrStartortherreason}</label><br/>
												</c:if>
										</c:forEach>
										
										
										<label class="form-span form-radio-label">结束时间：${miedical.mhrEndTimeShow}</label>
										<br/>
										<c:forEach var="obj" items="${ftEndReason}">
												<c:if test="${!empty miedical.mhrEndreason && obj.value ==miedical.mhrEndreason}">
													<label class="form-span form-radio-label">结束原因：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label>
												</c:if>
												<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.value == '00'}">
													<label class="form-span form-radio-label">其他结束原因：${ miedical.mhrEndotherreason}</label>
												</c:if>
										</c:forEach>
										
										<br/>
										<c:if test="${ !empty  miedical.mhrRemark}">
											<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
										</c:if>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${!empty syzList}">
						<!-- 肾移植史 -->
						<c:forEach var="miedical" items="${syzList}"  >
							<c:if test="${ !empty miedical.mhrStartTimeShow}">
								<div class="form-item">
									<span class="form-span form-title width-180 text-right"  style="vertical-align: top;">肾移植史：</span>
									<div class="form-item-box">
										<label class="form-span form-radio-label">开始时间：${ miedical.mhrStartTimeShow}</label></br>
										<label class="form-span form-radio-label">结束时间：${miedical.mhrEndTimeShow}</label></br>
										
										<c:forEach var="obj" items="${syzEndReason}">
												<c:if test="${!empty miedical.mhrEndreason && obj.value ==miedical.mhrEndreason}">
													<label class="form-span form-radio-label">结束原因：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label>
												</c:if>
												<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.value == '00'}">
													<label class="form-span form-radio-label">其他结束原因：${ miedical.mhrEndotherreason}</label>
												</c:if>
										</c:forEach>
										
										<br/>
										<c:if test="${ !empty  miedical.mhrRemark}">
											<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
										</c:if>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${!empty gmList}">
						<!-- 过敏史 -->
						<c:forEach var="miedical" items="${gmList}"  >
							<c:if test="${ !empty miedical.mhrStartTimeShow}">
								<div class="form-item">
									<span class="form-span form-title width-180 text-right"  style="vertical-align: top;">过敏史：</span>
									<div class="form-item-box">
											<label class="form-span form-radio-label">录入日期：${ miedical.mhrStartTimeShow}</label><br/>
										
											<c:forEach var="obj" items="${gmResouce}">
												<c:if test="${!empty miedical.mhrEndreason && obj.value ==miedical.mhrEndreason}">
													<label class="form-span form-radio-label">过敏源：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label>
												</c:if>
												<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.value == '00'}">
													<label class="form-span form-radio-label">其他过敏源：${ miedical.mhrEndotherreason}</label>
												</c:if>
											</c:forEach>
										
											<c:if test="${  !empty miedical.mhrStartreasonOrname}">
												<label class="form-span form-radio-label">名称：${ miedical.mhrStartreasonOrname}</label>
											</c:if>
											<br/>
											<c:if test="${ !empty miedical.mhrRemark}">
												<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
											</c:if>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${!empty crbList}">
						<!-- 传染病史 -->
						<c:forEach var="miedical" items="${crbList}"  >
							<c:if test="${ !empty miedical.mhrStartTimeShow}">
								<div class="form-item">
									<span class="form-span form-title width-180 text-right" style="vertical-align: top;">传染病史：</span>
									<div class="form-item-box">
											<label class="form-span form-radio-label">诊断日期：${ miedical.mhrStartTimeShow}</label><br/>
											
											<label class="form-span form-radio-label">诊断名称：</label>
											<c:forEach var="obj" items="${bs_crbzdmc}">
												<c:if test="${!empty miedical.mhrStartreasonOrname&& fn:contains(miedical.mhrStartreasonOrname,obj.value)}">
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label>
												</c:if>
												<c:if test="${!empty miedical.mhrStartreasonOrname && fn:contains(miedical.mhrStartreasonOrname,'00')&& obj.value == '00'}">
													<label class="form-span form-radio-label">其他诊断名称：${ miedical.mhrStartortherreason}</label>
												</c:if>
											</c:forEach><br/>
											
											<c:forEach var="obj" items="${bs_crbhdzt}">
												<c:if test="${!empty miedical.activitystatus && obj.value != miedical.activitystatus}">
													<label class="form-span form-radio-label">活动状态：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label>
												</c:if>
											</c:forEach><br/>
											
											<c:forEach var="obj" items="${bs_crbzlqk}">
												<c:if test="${!empty miedical.mhrEndreason && obj.value ==miedical.mhrEndreason}">
													<label class="form-span form-radio-label">治疗情况：</label>
													<input type="checkbox" checked>
													<label class="form-span form-checkbox-label">${obj.name}</label>
												</c:if>
												<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.value == '00'}">
													<span class="td-span-margin">其他治疗情况：${ miedical.mhrEndotherreason}</span>
												</c:if>
											</c:forEach><br/>
											<c:if test="${ !empty miedical.mhrRemark}">
												<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
											</c:if>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
	
					<c:if test="${ !empty medicalHistory.other}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right" style="vertical-align: top;">其它病因：</span>
							<div class="form-item-box">
								<label class="form-span form-radio-label">${ medicalHistory.other}</label>
							</div>
						</div>
					</c:if>
				</div>
			</div>


			<div class="list-item margin-bottom-20 bg-white" id="clinicalDiagnosis">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">临床诊断</span>
					<div class="tab-action">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color" onclick="jump('clinicalDiagnosis')">编 辑</span>
					</div>
				</div>
				<div class="tab-body" style="padding-left: 30px;">
					<c:choose>
						<c:when test="${ clinicalDiagnosisResult.type=='1'}">
							<div class="list-item margin-bottom-5 margin-top-5 bg-white">
								<div class="tab-body">
									<div class="form-item margin-left-16">
										<input type="radio" checked> <label class="form-span form-radio-label">慢性肾功能衰竭</label>
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${ clinicalDiagnosisResult.type=='2'}">
							<div class="list-item margin-bottom-5 margin-top-5 bg-white">
								<div class="tab-body">
									<div class="form-item margin-left-16">
										<input type="radio" checked> <label class="form-span form-radio-label">慢性肾功能不全急性加重</label>
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${ clinicalDiagnosisResult.type=='3'}">
							<div class="list-item margin-bottom-5 margin-top-5 bg-white">
								<div class="tab-body">
									<div class="form-item margin-left-16">
										<input type="radio" checked> <label class="form-span form-radio-label">急性肾功能衰竭</label>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>
					<c:if test="${ clinicalDiagnosisResult.type=='1'}">
						<c:if test="${ !empty crf.pgn}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">原发性肾小球疾病：</span>
								<c:forEach var="obj" items="${clinical_pgn}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ crf.otherPgn}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${ !empty crf.sgn}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">继发性肾小球疾病：</span>
								<c:forEach var="obj" items="${clinical_sgn}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ crf.otherSgn}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${ !empty crf.hereditaryNephropathy}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">遗传性及先天性肾病：</span>
								<c:forEach var="obj" items="${clinical_hereditary_nephropathy}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ crf.otherHereditaryNephropathy}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${ !empty crf.tin}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">肾小管间质疾病：</span>
								<c:forEach var="obj" items="${clinical_tin}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ crf.otherTin}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${ crf.urologicNeoplasms}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">有无泌尿系肿瘤：</span>
								<div class="form-item-box">
									<input type="radio" checked> <label class="form-span form-radio-label">有</label>
								</div>
							</div>
						</c:if>
						<c:if test="${ !empty crf.unAndStone}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">泌尿系统感染和结石：</span>
								<c:forEach var="obj" items="${un_and_stone}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ crf.otherUnAndStone}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${ !empty crf.renalResection}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">肾脏切除术后：</span>
								<c:forEach var="obj" items="${renal_resection}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ crf.otherRenalResection}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${ crf.unknownReason}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">是否原因不明：</span>
								<div class="form-item-box">
									<input type="radio" checked> <label class="form-span form-radio-label">是</label>
								</div>
							</div>
						</c:if>
						<c:if test="${ !empty crf.otherReason}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">其它病因：</span>
								<div class="form-item-box">
									<label class="form-span form-radio-label">${ crf.otherReason}</label>
								</div>
							</div>
						</c:if>
					</c:if>
					<c:if test="${ clinicalDiagnosisResult.type=='2'}">
						<c:if test="${ !empty seriousCrf.reason}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">造成原因：</span>
								<c:forEach var="obj" items="${serious_crf_reason}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ seriousCrf.otherReason}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
					</c:if>
					<c:if test="${ clinicalDiagnosisResult.type=='3'}">
						<c:if test="${ !empty arf.reason}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">造成原因：</span>
								<c:forEach var="obj" items="${arf_reason}">
									<c:if test="${obj.isChecked && obj.value != '00'}">
										<input type="checkbox" checked>
										<label class="form-span form-checkbox-label">${obj.name}</label>
									</c:if>
									<c:if test="${obj.isChecked && obj.value == '00'}">
										<span class="td-span-margin">其他：${ arf.otherReason}</span>
									</c:if>
								</c:forEach>
							</div>
						</c:if>
						<c:if test="${arf.unknownReason}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">是否原因不明：</span>
								<div class="form-item-box">
									<input type="radio" checked> <label class="form-span form-radio-label">是</label>
								</div>
							</div>
						</c:if>
						<c:if test="${ !empty arf.otherReason}">
							<div class="form-item">
								<span class="form-span form-title width-180 text-right">其它病因：</span>
								<div class="form-item-box">
									<label class="form-span form-radio-label">${ arf.otherReason}</label>
								</div>
							</div>
						</c:if>
					</c:if>
				</div>
			</div>

			<div class="list-item margin-bottom-20 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">病理诊断</span>
					<div class="tab-action">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color" onclick="jump('pathologicDiagnosis')">编 辑</span>
					</div>
				</div>
				<div class="tab-body" style="padding-left: 30px;">
					<c:if test="${ pathologicDiagnosisResult.hasRenalBiopsy}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">是否进行了肾活检检查：</span>
							<div class="form-item-box">
								<input type="radio" checked> <label class="form-span form-radio-label">是</label>
							</div>
						</div>
					</c:if>

					<c:if test="${ !empty pathologicDiagnosisResult.pgn}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">原发性肾小球疾病：</span>
							<c:forEach var="obj" items="${pathology_pgn}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${ pathologicDiagnosisResult.otherPgn}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>

					<c:if test="${ !empty pathologicDiagnosisResult.sgn}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">继发性肾小球疾病：</span>
							<c:forEach var="obj" items="${pathology_sgn}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${ pathologicDiagnosisResult.otherSgn}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>

					<c:if test="${ !empty pathologicDiagnosisResult.hereditaryNephropathy}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">遗传性及先天性肾病：</span>
							<c:forEach var="obj" items="${pathology_hereditary_nephropathy}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${ pathologicDiagnosisResult.otherHereditaryNephropathy}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>

					<c:if test="${ !empty pathologicDiagnosisResult.tin}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">肾小管间质疾病：</span>
							<c:forEach var="obj" items="${pathology_tin}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${ pathologicDiagnosisResult.otherTin}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${ pathologicDiagnosisResult.hasNsp}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">妊娠性肾病：</span>
							<div class="form-item-box">
								<input type="radio" checked> <label class="form-span form-radio-label">有</label>
							</div>
						</div>
					</c:if>
					<c:if test="${ pathologicDiagnosisResult.hasTkd}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">移植肾疾病：</span>
							<div class="form-item-box">
								<input type="radio" checked> <label class="form-span form-radio-label">有</label>
							</div>
						</div>
					</c:if>
					<c:if test="${ !empty pathologicDiagnosisResult.otherReason}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">其它病因：</span>
							<div class="form-item-box">
								<label class="form-span form-radio-label">${ pathologicDiagnosisResult.otherReason}</label>
							</div>
						</div>
					</c:if>
				</div>
			</div>

			<div class="list-item margin-bottom-20 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">CKD/AKI</span>
					<div class="tab-action">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color" onclick="jump('ckdAki')">编 辑</span>
					</div>
				</div>
				<div class="tab-body" style="padding-left: 30px;">
					<%-- <c:if test="${ !empty ckdStage.ckdType}">
						<div class="form-item margin-left-16">
							<div class="form-item-box">
								<label class="form-span form-radio-label">CKD分期</label>
							</div>
							<c:forEach var="obj" items="${ckd_type}">
								<c:if test="${obj.isChecked}">
									<div class="form-item-box">
										<span class="form-span form-title width-180 text-right">类型：</span>
										<label class="form-span form-radio-label">${obj.name}</label>
									</div>
								</c:if>
							</c:forEach>
							<c:forEach var="obj" items="${ckd_stage}">
								<c:if test="${obj.isChecked}">
									<div class="form-item-box">
										<span class="form-span form-title width-180 text-right">分期：</span>
										<label class="form-span form-radio-label">${obj.name}</label>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</c:if> --%>
					<c:if test="${ !empty ckdStage.ckdStage}">
						<div class="form-item margin-left-16">
							<div class="form-item-box">
								<label class="form-span form-radio-label">CKD分期</label>
							</div>
							<c:forEach var="obj" items="${ckd_stage}">
								<c:if test="${obj.isChecked}">
									<div class="form-item-box">
										<span class="form-span form-title width-180 text-right">分期：</span>
										<label class="form-span form-radio-label">${obj.name}</label>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${ !empty ckdStage.akiType}">
						
						<div class="form-item margin-left-16">
							<div class="form-item-box">
								<label class="form-span form-radio-label">AKI分期</label>
							</div>
							<c:forEach var="obj" items="${aki_type}">
								<c:if test="${obj.isChecked}">
									<div class="form-item-box">
										<span class="form-span form-title width-180 text-right">类型：</span>
										<label class="form-span form-radio-label">${obj.name}</label>
									</div>
								</c:if>
							</c:forEach>
							<c:forEach var="obj" items="${aki_stage_rifle}">
								<c:if test="${obj.isChecked}">
									<div class="form-item-box">
										<span class="form-span form-title width-180 text-right">分期：</span>
										<label class="form-span form-radio-label">${obj.name}</label>
									</div>
								</c:if>
							</c:forEach>
							<c:forEach var="obj" items="${aki_stage}">
								<c:if test="${obj.isChecked}">
									<div class="form-item-box">
										<span class="form-span form-title width-180 text-right">分期：</span>
										<label class="form-span form-radio-label">${obj.name}</label>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
				</div>
			</div>

			<div class="list-item margin-bottom-20 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">治疗前合并症</span>
					<div class="tab-action">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color" onclick="jump('cureComplication')">编 辑</span>
					</div>
				</div>
				<div class="tab-body" style="padding-left: 30px;">
					<c:if test="${ !empty cureSymptomAndCondition.gkwzdxwl}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">骨矿物质代谢紊乱：</span>
							<c:forEach var="obj" items="${gkwzdxwl_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.gkwzdxwlOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${ !empty cureSymptomAndCondition.dfypx}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">淀粉样变性：</span>
							<c:forEach var="obj" items="${dfybx_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.dfypxOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${ !empty cureSymptomAndCondition.hxxt}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">呼吸系统：</span>
							<c:forEach var="obj" items="${hxxt_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.hxxtOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					
					<c:if test="${ !empty cureSymptomAndCondition.xxgxt}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">心血管系统：</span>
							<c:forEach var="obj" items="${xxgxt_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.xxgxtOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${ !empty cureSymptomAndCondition.sjxt}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">神经系统：</span>
							<c:forEach var="obj" items="${sjxt_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.sjxtOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${ !empty  cureSymptomAndCondition.pfsy && cureSymptomAndCondition.pfsy ==1}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">是否皮肤瘙痒?</span>
							<input type="radio" checked> <label class="form-span form-radio-label">是</label>
						</div>
					</c:if>
					
					<c:if test="${ !empty cureSymptomAndCondition.xyxt}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">血液系统：</span>
							<c:forEach var="obj" items="${xyxt_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.xyxtOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					
					<c:if test="${ !empty cureSymptomAndCondition.hbzl}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">合并肿瘤:</span>
							<c:forEach var="obj" items="${hbzl_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.hbzlOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					
					<c:if test="${ !empty cureSymptomAndCondition.hbgr}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">合并感染：</span>
							<c:forEach var="obj" items="${hbgr_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.hbgrOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					
					<c:if test="${ !empty cureSymptomAndCondition.zsmyxjb}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">自身免疫性疾病：</span>
							<c:forEach var="obj" items="${zsmyxjb_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.zsmyxjbOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					
					<c:if test="${ !empty cureSymptomAndCondition.nfmjdxxt}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">内分泌及代谢系统：</span>
							<c:forEach var="obj" items="${nfmjdxxt_info}">
								<c:if test="${obj.isChecked && obj.value != '00'}">
									<input type="checkbox" checked>
									<label class="form-span form-checkbox-label">${obj.name}</label>
								</c:if>
								<c:if test="${obj.isChecked && obj.value == '00'}">
									<span class="td-span-margin">其他：${cureSymptomAndCondition.nfmjdxxtOther}</span>
								</c:if>
							</c:forEach>
						</div>
					</c:if>
					
					<c:if test="${ !empty cureSymptomAndCondition.treatmentCondition}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">治疗情况：</span>
							<div class="form-item-box">
								<label class="form-span form-radio-label">${ cureSymptomAndCondition.treatmentCondition}</label>
							</div>
						</div>
					</c:if>
				</div>
			</div>

			<div class="list-item margin-bottom-20 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">其他诊断</span>
					<div class="tab-action">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color" onclick="jump('otherDiagnosis')">编 辑</span>
					</div>
				</div>
				<div class="tab-body" style="padding-left: 30px;">
					<c:if test="${ !empty otherDiagnosisResult.content}">
						<div class="form-item">
							<span class="form-span form-title width-180 text-right">内容：</span>
							<div class="form-item-box">
								<label class="form-span form-radio-label">${ otherDiagnosisResult.content}</label>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			<button type="button" class="btn btn-def btn-ls center-block" style="margin-top: 20px;" id="doneForm_btnFinish">完成</button>
		</div>
	</div>

</body>
</html>
