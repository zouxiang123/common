<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<c:forEach var="record" items="${ckdStages}" varStatus="status">
<div>
	<div class="content-editing-bar">
		<div class="not-selected"></div>
		<span class="content-select-title"><fmt:formatDate value="${record.ckdStage.updateTime }" pattern="yyyy-MM-dd" />
			${record.ckdStage.userName }</span>
		<div class="tab-action2" style="margin-top: 14px;">
			<span>打开</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-right.png" class="arrow-up">
		</div>
		<div class="tab-line"></div>
	</div>
	<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7;">
		<div class="table-responsive bg-white">
			<table class="table table-no-border">
				<tbody>
					<%-- <c:if test="${!empty record.ckdStage.ckdType}">
						<tr>
							<td width="40" class="td-top"></td>
							<td width="140" class="td-top"><span class="table-sub-title">CKD分期：</span></td>
							<td style="padding-top:4px;">
								<label><c:forEach var="obj" items="${record.ckd_type}">
									<c:if test="${obj.isChecked}"><span class="td-span-margin">类型：${obj.itemName}</span></c:if>
								</c:forEach></label>
								<label><c:forEach var="obj" items="${record.ckd_stage}">
									<c:if test="${obj.isChecked}"><span class="td-span-margin">分期：${obj.itemName}</span></c:if>
								</c:forEach></label>
							</td>
						</tr>
					</c:if> --%>
					<c:if test="${!empty record.ckdStage.ckdStage}">
						<tr>
							<td width="40" class="td-top"></td>
							<td width="140" class="td-top"><span class="table-sub-title">CKD分期：</span></td>
							<td style="padding-top:4px;">
								<label><c:forEach var="obj" items="${record.ckd_stage}">
									<c:if test="${obj.isChecked}"><span class="td-span-margin">分期：${obj.itemName}</span></c:if>
								</c:forEach></label>
							</td>
						</tr>
					</c:if>
					<c:if test="${!empty record.ckdStage.akiType}">
						<tr>
							<td width="40" class="td-top"></td>
							<td width="140" class="td-top"><span class="table-sub-title">AKI分期：</span></td>
							<td style="padding-top:4px;">
								<label><c:forEach var="obj" items="${record.aki_type}">
									<c:if test="${obj.isChecked}"><span class="td-span-margin">类型：${obj.itemName}</span></c:if>
								</c:forEach></label>
								<label><c:forEach var="obj" items="${record.aki_stage_rifle}">
									<c:if test="${obj.isChecked}"><span class="td-span-margin">分期：${obj.itemName}</span></c:if>
								</c:forEach></label>
								<label><c:forEach var="obj" items="${record.aki_stage}">
									<c:if test="${obj.isChecked}"><span class="td-span-margin">分期：${obj.itemName}</span></c:if>
								</c:forEach></label>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
</c:forEach>
