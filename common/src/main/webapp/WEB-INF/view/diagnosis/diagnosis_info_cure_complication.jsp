<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<c:forEach var="record" items="${cureSymptomAndConditions}">
	<div>
		<div class="content-editing-bar">
			<div class="selected2"></div>
			<span class="content-select-title"><fmt:formatDate value="${record.cureSymptomAndCondition.updateTime }" pattern="yyyy-MM-dd" />
				${record.cureSymptomAndCondition.userName }</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>收起</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-down.png" class="arrow-down">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7; display: block;">
			<div class="table-responsive bg-white">
				<table class="table table-no-border">
					<tbody>
						<c:if test="${!empty record.cureSymptomAndCondition.gkwzdxwl}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">骨矿物质代谢紊乱：</span></td>
								<td><c:forEach var="obj" items="${record.gkwzdxwl_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.gkwzdxwlOther}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.dfypx }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">淀粉样变性：</span></td>
								<td><c:forEach var="obj" items="${record.dfybx_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.dfypxOther}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.hxxt }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">呼吸系统：</span></td>
								<td><c:forEach var="obj" items="${record.hxxt_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.hxxtOther}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.xxgxt }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">心血管系统：</span></td>
								<td><c:forEach var="obj" items="${record.xxgxt_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.xxgxtOther}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.sjxt }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">神经系统：</span></td>
								<td><c:forEach var="obj" items="${record.sjxt_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.sjxtOther}</span>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${ !empty  record.cureSymptomAndCondition.pfsy && record.cureSymptomAndCondition.pfsy ==1}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">是否皮肤瘙痒?</span></td>
								<td><input type="radio" checked> <label class="form-span form-radio-label">是</label></td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.xyxt }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">血液系统：</span></td>
								<td><c:forEach var="obj" items="${record.xyxt_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.xyxtOther}</span>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.hbzl}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">合并肿瘤：</span></td>
								<td><c:forEach var="obj" items="${record.hbzl_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.hbzlOther}</span>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.hbgr }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">合并感染：</span></td>
								<td><c:forEach var="obj" items="${record.hbgr_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.hbgrOther}</span>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.zsmyxjb }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">自身免疫性疾病：</span></td>
								<td><c:forEach var="obj" items="${record.zsmyxjb_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.zsmyxjbOther}</span>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${!empty record.cureSymptomAndCondition.nfmjdxxt }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">内分泌及代谢系统：</span></td>
								<td><c:forEach var="obj" items="${record.nfmjdxxt_info}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.cureSymptomAndCondition.nfmjdxxtOther}</span>
										</c:if>
									</c:forEach>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty record.cureSymptomAndCondition.treatmentCondition}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">治疗情况：</span></td>
								<td><span class="td-span-margin">${ record.cureSymptomAndCondition.treatmentCondition}</span></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:forEach>
