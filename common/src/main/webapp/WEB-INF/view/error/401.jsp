<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>错误</title>
</head>
<body class="bg">
	<div class="error_bg">
		<div class="error">
			<img src="${ctx }/commonCss/images/c_03.png" class="fl">
			<div class="fl mt100 ml30">
				<h2 class="f24">用户尚未登录或登录信息已失效，正在跳转到登录页面。。。</h2>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			if (isFromIframe()){
				setTimeout(function(){
					top.location.reload(true);
				}, 1000);
			}
		});
	</script>
</body>
</html>