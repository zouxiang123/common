<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
				<h2 class="f24">您访问的网页出错了！</h2>
				<ul>
					<li>可能原因：网址不存在、DNS服务器未响应、网络连接异常</li>
					<li>建议操作：检查网址是否正确，或刷新重试</li>
					<li class="mt30"><input type="button" class="btn" value="返回首页"></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>