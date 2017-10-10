<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<c:forEach var="record" items="${otherDiagnosisResults}">
	<div>
		<div class="content-editing-bar">
			<div class="selected2"></div>
			<span class="content-select-title"><fmt:formatDate value="${record.otherDiagnosisResult.updateTime }" pattern="yyyy-MM-dd" />
				${record.otherDiagnosisResult.userName }</span>
			<div class="tab-action2" style="margin-top: 14px;">
				<span>收起</span><img src="${COMMON_SERVER_ADDR}/assets/img/arrow-down.png" class="arrow-down">
			</div>
			<div class="tab-line"></div>
		</div>
		<div class="fill-parent bg-white content-editing-wrap" style="margin-top: -5px; border-top: 1px solid #F7F7F7; display: block;">
			<div class="table-responsive bg-white">
				<table class="table table-no-border">
					<tbody>
						<c:if test="${!empty record.otherDiagnosisResult.content}">
							<tr>
								<td width="40" class="td-top"></td>
								<td width="140" class="td-top"><span class="table-sub-title">内容：</span></td>
								<td><span class="td-span-margin">${ record.otherDiagnosisResult.content}</span></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</c:forEach>
