<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<c:forEach var="record" items="${medicalHistorys}">
	<div>
		<div class="content-editing-bar">
			<div class="selected2"></div>
			<span class="content-select-title"><fmt:formatDate value="${record.medicalHistory.updateTime }" pattern="yyyy-MM-dd" />
				${record.medicalHistory.userName }</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>收起</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-down.png" class="arrow-down">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7; display: block;">
			<div class="table-responsive bg-white">
				<table class="table table-no-border">
					<tbody>
						<c:if test="${!empty record.medicalHistory.firstDialysisDate_show}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">首次透析日期：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">${record.medicalHistory.firstDialysisDate_show}</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.medicalHistory.firstDialysisMethod}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">首次透析方式：</span></td>
								<td><c:forEach var="obj" items="${record.first_dialysis_method}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="radio" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						<c:if test="${record.medicalHistory.hasCva}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">有无脑血管意外史：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">有
												<c:if test="${ !empty record.medicalHistory.hascvaRemark}">: ${record.medicalHistory.hascvaRemark }</c:if>
										</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.medicalHistory.hemorrhage}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">严重出血或出血倾向：</span></td>
								<td>
									<c:forEach var="obj" items="${record.hemorrhage}">
										<c:if test="${obj.isChecked}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}
														<c:if test="${ !empty record.medicalHistory.hemorrhageRemark&&obj.itemValue=='00'}">: ${record.medicalHistory.hemorrhageRemark}</c:if>
												</label>
											</div>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.medicalHistory.heartDefects}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">严重心肺功能不全病史：</span></td>
								<td>
									<c:forEach var="obj" items="${record.heart_defects}">
										<c:if test="${obj.isChecked }">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}
														<c:if test="${ !empty record.medicalHistory.heartDefectsRemark&&obj.itemValue=='00'}">: ${record.medicalHistory.heartDefectsRemark}</c:if>
												</label>
											</div>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${ record.medicalHistory.hasVascularDisease}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">有无外周血管疾病史：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">有
											<c:if test="${ !empty record.medicalHistory.hasvasculardiseaseRemark}">: ${record.medicalHistory.hasvasculardiseaseRemark}</c:if>
										</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${ record.medicalHistory.hasSeriousDisease}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">有无严重感染或血源性传染病史：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">有</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${ record.medicalHistory.hasPsychosis}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">有无精神病史：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">有
												<c:if test="${ !empty record.medicalHistory.haspsychosisRemark}">: ${record.medicalHistory.haspsychosisRemark }</c:if>
										</label>
									</div>
								</td>
							</tr>
						</c:if>
						
						<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
					
					
				<!-- 手术史 -->
               	<c:forEach var="miedical" items="${meList}"  >
                    <c:if test="${!empty miedical.mhrStartTimeShow}">
                    <tr>
						<td width="40" class="td-top"></td>
						<td width="180" class="td-top"><span class="table-sub-title">手术史：</span></td>
						<td>
							<div class="form-item td-radio-margin">
								<label class="form-span form-radio-label">手术时间：${miedical.mhrStartTimeShow}</label>
								<c:if test="${ !empty miedical.mhrStartreasonOrname}">
									<label class="form-span form-radio-label">手术名称：${ miedical.mhrStartreasonOrname}</label>
								</c:if><br/>
								<c:if test="${ !empty miedical.mhrRemark}">
									<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
								</c:if>
							</div>
						 </td>
					</tr>
                    </c:if>
					</c:forEach>
					<!-- 血透史 -->
					<c:forEach var="miedical" items="${xtList}"  >
						<c:if test="${ !empty miedical.mhrStartTimeShow}">
						<tr>
							<td width="40" class="td-top"></td>
							<td width="180" class="td-top"><span class="table-sub-title">血透史：</span></td>
							<td>
								<div class="form-item td-radio-margin">
								<label class="form-span form-radio-label">开始时间：${miedical.mhrStartTimeShow}</label>
								
								<c:forEach var="obj" items="${record.xtStartReason}">
										<c:if test="${!empty miedical.mhrStartreasonOrname&& obj.itemValue==miedical.mhrStartreasonOrname}">
											<label class="">开始原因：</label>
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!empty miedical.mhrStartreasonOrname && miedical.mhrStartreasonOrname == '00'&& obj.itemValue == '00'}">
											<label class="form-span form-radio-label">其他开始原因：${miedical.mhrStartortherreason}</label>
										</c:if>
								</c:forEach>
								</br>
								<label class="form-span form-radio-label">结束时间：${miedical.mhrEndTimeShow}</label>
								<c:forEach var="obj" items="${record.xtEndReason}">
										<c:if test="${!empty miedical.mhrEndreason && obj.itemValue ==miedical.mhrEndreason}">
											<label class="">结束原因：</label>
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.itemValue == '00'}">
											<label class="form-span form-radio-label">其他结束原因：${miedical.mhrEndotherreason}</label>
										</c:if>
								</c:forEach>
								</br>
								<c:if test="${ !empty  miedical.mhrRemark}">
									<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
								</c:if>
							</div>
							 </td>
						</tr>
					</c:if>
					</c:forEach>
					<!-- 腹透史 -->
					<c:forEach var="miedical" items="${ftList}"  >
					<c:if test="${ !empty miedical.mhrStartTimeShow}">
						<tr>
							<td width="40" class="td-top"></td>
							<td width="180" class="td-top"><span class="table-sub-title">腹透史：</span></td>
							<td>
								<div class="form-item td-radio-margin">
							
								<label class="form-span form-radio-label">开始时间：${miedical.mhrStartTimeShow}</label>	
								<c:forEach var="obj" items="${record.ftStartReason}">
										<c:if test="${!empty miedical.mhrStartreasonOrname&& obj.itemValue==miedical.mhrStartreasonOrname}">
											<label class="">开始原因：</label>
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!empty miedical.mhrStartreasonOrname && miedical.mhrStartreasonOrname == '00'&& obj.itemValue == '00'}">
											<label class="form-span form-radio-label">其他开始原因：${miedical.mhrStartortherreason}</label>
										</c:if>
								</c:forEach>
								</br>
								<label class="form-span form-radio-label">结束时间：${ miedical.mhrEndTimeShow}</label>
								<c:forEach var="obj" items="${record.ftEndReason}">
										<c:if test="${!empty miedical.mhrEndreason && obj.itemValue ==miedical.mhrEndreason}">
											<label class="">结束原因：</label>
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.itemValue == '00'}">
											<label class="form-span form-radio-label">其他结束原因：${miedical.mhrEndotherreason}</label>
										</c:if>
								</c:forEach>
								</br>
								<c:if test="${ !empty miedical.mhrRemark}">
									<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
								</c:if>
							</div>
							 </td>
						</tr>
					</c:if>
					</c:forEach>
					<!-- 肾移植史 -->
					<c:forEach var="miedical" items="${syzList}"  >
					<c:if test="${ !empty  miedical.mhrStartTimeShow}">
							<tr>
							<td width="40" class="td-top"></td>
							<td width="180" class="td-top"><span class="table-sub-title">肾移植史：</span></td>
							<td>
								<div class="form-item td-radio-margin">
								<label class="form-span form-radio-label">开始时间：${miedical.mhrStartTimeShow}</label>
								<label class="form-span form-radio-label">结束时间：${ miedical.mhrEndTimeShow}</label>
								</br>
								<c:forEach var="obj" items="${record.syzEndReason}">
										<c:if test="${!empty miedical.mhrEndreason && obj.itemValue ==miedical.mhrEndreason}">
											<label class="">结束原因：</label>
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.itemValue == '00'}">
											<label class="form-span form-radio-label">其他结束原因：${miedical.mhrEndotherreason}</label>
										</c:if>
								</c:forEach>
								</br>
								<c:if test="${ !empty miedical.mhrRemark}">
									<label class="form-span form-radio-label">备注：${ miedical.mhrRemark}</label>
								</c:if>
								</div>
							 </td>
						</tr>
					</c:if>
					</c:forEach>
					<!-- 过敏史 -->
					<c:forEach var="miedical" items="${gmList}"  >
					<c:if test="${ !empty  miedical.mhrStartTimeShow}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">过敏史：</span></td>
								<td>
									<div class="form-item td-radio-margin">
									<label class="form-span form-radio-label"  style="display: block;">录入日期：${miedical.mhrStartTimeShow}</label>
									<label class="form-span form-radio-label">过敏源：</label>
									<c:forEach var="obj" items="${record.gmResouce}">
										<c:if test="${!empty miedical.mhrEndreason && obj.itemValue ==miedical.mhrEndreason}">
											<input type="checkbox"  checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.itemValue == '00'}">
											<label class="form-span form-checkbox-label"  style="display: block;">其他过敏源：${miedical.mhrEndotherreason}</label>
										</c:if>
									</c:forEach>
									<c:if test="${ !empty miedical.mhrStartreasonOrname}">
										<label class="form-span form-radio-label"  style="display: block;">名称：${ miedical.mhrStartreasonOrname}</label>
									</c:if>
									<c:if test="${ !empty miedical.mhrRemark}">
										<label class="form-span form-radio-label"  style="display: block;">备注：${miedical.mhrRemark}</label>
									</c:if>
									</div>
							    </td>
						</tr>
					</c:if>
					</c:forEach>
					<!-- 传染病史 -->
					<c:forEach var="miedical" items="${crbList}"  >
					<c:if test="${ !empty miedical.mhrStartTimeShow}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">传染病史：</span></td>
								<td>
									<div class="form-item td-radio-margin">
									<label class="form-span form-radio-label" >诊断日期：${ miedical.mhrStartTimeShow}</label><br/>
									
									<label class="form-span form-radio-label">诊断名称：</label>
									<c:forEach var="obj" items="${record.bs_crbzdmc}">
										<c:if test="${!empty miedical.mhrStartreasonOrname&& fn:contains(miedical.mhrStartreasonOrname,obj.itemValue)}">
												<input type="checkbox"  checked>
												<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!empty miedical.mhrStartreasonOrname && fn:contains(miedical.mhrStartreasonOrname,'00')&& obj.itemValue == '00'}">
											<label class="form-span form-checkbox-label">其他诊断名称：${miedical.mhrStartortherreason}</label>
										</c:if>
									</c:forEach>
									<br/>
									<c:forEach var="obj" items="${record.bs_crbhdzt}">
										<c:if test="${!empty miedical.activitystatus && obj.itemValue != miedical.activitystatus}">
											<label class="form-span form-radio-label">活动状态：</label>
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
									</c:forEach>
									<br/>
									<label class="form-span form-radio-label">治疗情况：</label>
									<c:forEach var="obj" items="${record.bs_crbzlqk}">
										<c:if test="${!empty miedical.mhrEndreason && obj.itemValue ==miedical.mhrEndreason}">
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</c:if>
										<c:if test="${!empty miedical.mhrEndotherreason && miedical.mhrEndotherreason == '00'&& obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ miedical.mhrEndotherreason}</span>
										</c:if>
									</c:forEach><br/>
									<c:if test="${ !empty miedical.mhrRemark}">
										<label class="form-span form-radio-label">备注：${miedical.mhrRemark}</label>
									</c:if>
									</div>
							    </td>
						</tr>
					</c:if>
					</c:forEach>
					
					
					<!-- End///////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
						
						<c:if test="${ !empty record.medicalHistory.other}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">其它病史：</span></td>
								<td><span class="td-span-margin">${ record.medicalHistory.other}</span></td>
							</tr>
						</c:if>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:forEach>
