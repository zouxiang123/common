<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<%-- <jsp:include page="/common/common.jsp"></jsp:include> --%>
<title>错误</title>
</head>
<body >
	<h3><strong>错误信息：</strong></h3>
	<h4>
		<c:if test="${empty exception }">服务器出错!</c:if>
		<c:if test="${type == 'server-error' }">${type}：服务器未知异常</c:if>
		<c:if test="${type == 'number-format' }">${type}：数据转换异常</c:if>
		<c:if test="${type == 'null' }">${type}：空指针异常</c:if>
		<c:if test="${type == '404' }">${type}：找不到对应的资源</c:if>
	</h4>
	<div>肿么办，快去<a href="#">报告管理员</a></div>
	<br/>
	<div style="padding-left:50px;">${message}</div>
</body>
</html>