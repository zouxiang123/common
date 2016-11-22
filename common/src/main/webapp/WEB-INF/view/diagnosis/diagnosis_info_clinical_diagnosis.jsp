<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$("#btnAddCd").bind("click", function() {
			window.location.href = "../doctor/newPatient.shtml?patientId=${patientId}";
		});
	});
//-->
</script>
<c:forEach var="record" items="${crfs}" varStatus="status">
	<div id="<fmt:formatDate value="${record.arf.updateTime }" pattern="yyyy-MM-dd" />">
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title"><fmt:formatDate value="${record.crf.updateTime }" pattern="yyyy-MM-dd" /> ${record.crf.userName }
				慢性肾功能衰竭</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>打开</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7;">
			<div class="table-responsive bg-white">
				<table class="table table-no-border">
					<tbody>
						<c:if test="${ !empty record.crf.pgn}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">原发性肾小球疾病：</span></td>
								<td><c:forEach var="obj" items="${record.clinical_pgn}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.crf.otherPgn}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.crf.sgn}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">继发性肾小球疾病：</span></td>
								<td><c:forEach var="obj" items="${record.clinical_sgn}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.crf.otherSgn}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.crf.hereditaryNephropathy}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">遗传性及先天性肾病：</span></td>
								<td><c:forEach var="obj" items="${record.clinical_hereditary_nephropathy}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.crf.otherHereditaryNephropathy}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.crf.tin}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">肾小管间质疾病：</span></td>
								<td><c:forEach var="obj" items="${record.clinical_tin}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.crf.otherTin}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${ record.crf.urologicNeoplasms}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">有无泌尿系肿瘤：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">有</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.crf.unAndStone}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">泌尿系统感染和结石：</span></td>
								<td><c:forEach var="obj" items="${record.un_and_stone}">
									<c:if test="${obj.isChecked && obj.itemValue != '00'}">
										<div class="box-style">
											<input type="checkbox" checked>
											<label class="form-span form-checkbox-label">${obj.itemName}</label>
										</div>
									</c:if>
									<c:if test="${obj.isChecked && obj.itemValue == '00'}">
										<span class="td-span-margin">其他：${ record.crf.otherUnAndStone}</span>
									</c:if>
								</c:forEach></td>
							</tr>
						</c:if>
						
						<c:if test="${ !empty record.crf.renalResection}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">肾脏切除术后：</span></td>
								<td><c:forEach var="obj" items="${record.renal_resection}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.crf.otherRenalResection}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${ !empty  record.crf.unknownReason}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">是否原因不明：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">是</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.crf.otherReason}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">其他病因：</span></td>
								<td><span class="td-span-margin">${ record.crf.otherReason}</span></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:forEach>
<c:forEach var="record" items="${seriousCrfs}" varStatus="status">
	<div id="<fmt:formatDate value="${record.seriousCrf.updateTime }" pattern="yyyy-MM-dd" />">
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title"><fmt:formatDate value="${record.seriousCrf.updateTime }" pattern="yyyy-MM-dd" />
				${record.seriousCrf.userName } 慢性肾功能不全急性加重</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>打开</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>

			<c:if test="${empty crfs && status.index==0&&!patient.delFlag}">
				<div class="tab-def-action">
					<span id="btnAddCd"> <c:if test="${!record.seriousCrf.isDraft}">新增</c:if> <c:if test="${record.seriousCrf.isDraft}">继续录入</c:if>
					</span> <img src="${ctx }/assets/img/new-edit.png" class="new-edit">
				</div>
			</c:if>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7;">
			<div class="table-responsive bg-white">
				<table class="table table-no-border">
					<tbody>
						<c:if test="${ !empty record.seriousCrf.reason}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">造成原因：</span></td>
								<td><c:forEach var="obj" items="${record.serious_crf_reason}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.seriousCrf.otherReason}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:forEach>
<c:forEach var="record" items="${arfs}" varStatus="status">
	<div id="<fmt:formatDate value="${record.arf.updateTime }" pattern="yyyy-MM-dd" />">
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title"><fmt:formatDate value="${record.arf.updateTime }" pattern="yyyy-MM-dd" /> ${record.arf.userName }
				急性肾功能衰竭</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>打开</span><img src="${ctx }/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>

			<c:if test="${empty crfs && empty seriousCrfs &&  status.index==0&&!patient.delFlag}">
				<div class="tab-def-action">
					<span id="btnAddCd"> <c:if test="${!record.arf.isDraft}">新增</c:if> <c:if test="${record.arf.isDraft}">继续录入</c:if>
					</span> <img src="${ctx }/assets/img/new-edit.png" class="new-edit">
				</div>
			</c:if>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7;">
			<div class="table-responsive bg-white">
				<table class="table table-no-border">
					<tbody>
						<c:if test="${!empty record.arf.reason}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">造成原因：</span></td>
								<td><c:forEach var="obj" items="${record.arf_reason}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.arf.otherReason}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${record.arf.unknownReason}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">是否原因不明：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">是</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${ !empty record.arf.otherReason}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">其他病因：</span></td>
								<td><span class="td-span-margin">${ record.arf.otherReason}</span></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:forEach>