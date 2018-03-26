<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<%@ include file="../common/head_new.jsp"%>
    <title>学透通</title>
</head>
<body>
	<input type="text" name="recordDateShow" value="2016-11-30">
	<input type="text" name="fkPatientId" value="101010000000001">
	<input type="text" name="sysOwner">
	<button onclick="queryRec()">点击</button>
	<div class="g-mainc center-block bg-white clearfix" style="margin-top:39px;" id="modeHtml">
           
    </div>
</body>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/nutrition_access.js"></script>
</html>
