<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script src="${ctx}/assets/js/home/search.js?version=${version}"></script>
<title>welcome to xtt</title>
</head>
<body>
	<jsp:include page="../common/home_top_nav.jsp"></jsp:include>
	<jsp:include page="../common/top_nav_inner.jsp" flush="true"></jsp:include>
	<%-- <jsp:include page="../common/home_left_nav.jsp" flush="true"></jsp:include> --%>

	<div class="col-sm-12 col-md-12 main" style="padding-right: 20px;" data-iframe-css="main">
		<div class="search-result-layout">
			<form class="navbar-form search-result-form" action="${ctx }/search.shtml" onsubmit="return isNotNew();" method="post">
				<input id="name" name="name" type="search" value="${name }" class="form-control search-input" placeholder="患者/医生/护士/新增">
				<button type="submit" class="btn search-button">搜 索</button>
			</form>
			<div class="navbar-def navbar-collapse under-line">
				<ul class="nav navbar-nav" id="tabNav">
					<li data-link="patient" class="active"><a href="#">患者</a></li>
					<li data-link="doctor"><a href="#">医生</a></li>
					<li data-link="nurse"><a href="#">护士</a></li>
				</ul>
			</div>
			<div class="table-responsive bg-white" id="patient">
				<table class="table">
					<tbody>
						<c:forEach items="${userList}" var="obj">
							<c:if test="${obj.roleName eq '患者'}">
								<tr class="table-default" onclick="openPersonInfo('${obj.id }','${obj.name }','${obj.roleName }')">
									<td width="18"></td>
									<td width="58"><img class="user-photo" src="${ctx}/images${obj.imagePath }"></td>
									<td width="212">${obj.name }</td>
									<td width="280">${obj.id }</td>
									<td width="50">${obj.sex }</td>
									<td align="right"><span class="patient-status">${obj.nextStepName }</span></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="table-responsive bg-white" id="doctor">
				<table class="table">
					<tbody>
						<c:forEach items="${userList}" var="obj">
							<c:if test="${obj.roleName eq '医生'}">
								<tr class="table-default" onclick="openPersonInfo('${obj.id }','${obj.name }','${obj.roleName }')">
									<td width="18"></td>
									<td width="58"><img class="user-photo" src="${COMMON_SERVER_ADDR}/common/showImage.shtml?fileName=${obj.imagePath }"></td>
									<td width="212">${obj.name }</td>
									<td width="280">${obj.id }</td>
									<td></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="table-responsive bg-white" id="nurse">
				<table class="table">
					<tbody>
						<c:forEach items="${userList}" var="obj">
							<c:if test="${obj.roleName eq '护士'}">
								<tr class="table-default" onclick="openPersonInfo('${obj.id }','${obj.name }','${obj.roleName }')">
									<td width="18"></td>
									<td width="58"><img class="user-photo" src="${COMMON_SERVER_ADDR}/common/showImage.shtml?fileName=${obj.imagePath }"></td>
									<td width="212">${obj.name }</td>
									<td width="280">${obj.id }</td>
									<td></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../common/home_footer.jsp" flush="true"></jsp:include>
</body>
</html>