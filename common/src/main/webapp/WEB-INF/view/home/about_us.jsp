<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>学透通</title>
</head>

<body>

	<nav class="navbar navbar-fixed-top" data-iframe="false" style="display: none;">
		<div class="container-fluid">
			<div id="navbar" class="navbar-collapse">
				<ul class="nav navbar-nav">
					<li>
						<ol class="breadcrumb">
							<li><a href="javascript:history.go(-1);">返回</a></li>
							<li class="active">关于我们</li>
						</ol>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 bg-grey main-wrap margin-top-20" style="padding-right: 16px;" id="mainContent">
				<div class="fill-parent bg-white text-center" style="height: 733px;">
					<img class="about-logo" src="${COMMON_SERVER_ADDR}/assets/img/pad/about-logo.png">
					<p class="app-name">学透通®血液透析智能系统</p>
					<p class="app-version">v1.0</p>
					<div class="contact-us">
						<p>
							<span class="contact-us-key">客服：</span><span class="contact-us-value">400-021-9859</span>
						</p>
						<p>
							<span class="contact-us-key">首席客服：</span><span class="contact-us-value">13903756265-周先生</span>
						</p>
						<p>
							<span class="contact-us-key">邮箱：</span><span class="contact-us-value">xtt@xuetoutong.com</span>
						</p>
						<p>
							<span class="contact-us-key">传真：</span><span class="contact-us-value">021-65657718</span>
						</p>
						<p>
							<span class="contact-us-key">地址：</span><span class="contact-us-value">恒丰北路100号林顿大厦2402-2405室</span>
						</p>
					</div>
					<p class="company-info">学透通医疗科技(上海)有限公司</p>
					<p class="site-info">www.xuetoutong.com</p>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		if (isFromIframe()) {
			$("#mainContent").css("margin-top", "0px");
			$("#mainContent").css("padding", "15px 15px 0px 15px");
		}
	</script>
</body>
</html>
