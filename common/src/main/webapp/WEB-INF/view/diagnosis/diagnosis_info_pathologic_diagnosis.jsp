<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<c:forEach var="record" items="${pathologicDiagnosisResults}" varStatus="status">
	<div>
		<div class="content-editing-bar">
			<div class="not-selected"></div>
			<span class="content-select-title"><fmt:formatDate value="${record.pathologicDiagnosisResult.updateTime }" pattern="yyyy-MM-dd" />
				${record.pathologicDiagnosisResult.userName }</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>打开</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7;">
			<div class="table-responsive bg-white">
				<table class="table table-no-border">
					<tbody>

						<c:if test="${record.pathologicDiagnosisResult.hasRenalBiopsy}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="180" class="td-top"><span class="table-sub-title">是否进行了肾活检检查：</span></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">是</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty record.pathologicDiagnosisResult.pgn}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">原发性肾小球疾病：</span></td>
								<td><c:forEach var="obj" items="${record.pathology_pgn}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.pathologicDiagnosisResult.otherPgn}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${!empty record.pathologicDiagnosisResult.sgn }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">继发性肾小球疾病：</span></td>
								<td><c:forEach var="obj" items="${record.pathology_sgn}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.pathologicDiagnosisResult.otherSgn}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${!empty record.pathologicDiagnosisResult.hereditaryNephropathy }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">遗传性及先天性肾病：</span></td>
								<td><c:forEach var="obj" items="${record.pathology_hereditary_nephropathy}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.pathologicDiagnosisResult.otherHereditaryNephropathy}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${!empty record.pathologicDiagnosisResult.tin }">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">肾小管间质疾病：</span></td>
								<td><c:forEach var="obj" items="${record.pathology_tin}">
										<c:if test="${obj.isChecked && obj.itemValue != '00'}">
											<div class="box-style">
												<input type="checkbox" checked> <label class="form-span form-checkbox-label">${obj.itemName}</label>
											</div>
										</c:if>
										<c:if test="${obj.isChecked && obj.itemValue == '00'}">
											<span class="td-span-margin">其他：${ record.pathologicDiagnosisResult.otherTin}</span>
										</c:if>
									</c:forEach></td>
							</tr>
						</c:if>
						<c:if test="${record.pathologicDiagnosisResult.hasNsp}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">妊娠性肾病：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">有</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${record.pathologicDiagnosisResult.hasTkd}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">移植肾疾病：</label></td>
								<td>
									<div class="form-item td-radio-margin">
										<input type="radio" checked> <label class="form-span form-radio-label">有</label>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty record.pathologicDiagnosisResult.otherReason}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">其他病因：</span></td>
								<td><span class="td-span-margin">${ record.pathologicDiagnosisResult.otherReason}</span></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:forEach>
