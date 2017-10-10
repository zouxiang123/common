<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>错误</title>
</head>
<body>
	<nav class="navbar navbar-fixed-top" style="display: none;" data-iframe="false">
		<div class="container-fluid">
			<div id="navbar" class="navbar-collapse">
				<ul class="nav navbar-nav">
					<li>
						<ol class="breadcrumb">
							<li><a href="javascript:history.go(-1);">返回</a></li>
						</ol>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../common/top_nav_inner.jsp" flush="true"></jsp:include>
			<div class="col-sm-12 col-md-12 main" style="padding-top: 15px; padding-bottom: 15px; padding-left: 7.5%; padding-right: 7.5%;" data-iframe-css="main">
				<div class="fill-parent bg-white center">
					<img style="margin-top: 44px;" src="${COMMON_SERVER_ADDR}/assets/img/403.png">
					<div style="margin-top: 16px;">
						<img src="${COMMON_SERVER_ADDR}/assets/img/face.png"> <span
							style="display: inline-block; margin-left: 18px; font-size: 16px; color: #a3a3a3; margin-bottom: 44px;">很抱歉,您没有访问权限...</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>