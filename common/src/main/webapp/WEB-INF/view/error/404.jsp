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
					<li><div class="home-page">
							<img src="${ctx }/assets/img/home-page.png" />
						</div></li>
					<li>
						<ol class="breadcrumb">
							<li><a href="javascript:history.go(-1);">返回</a></li>
						</ol>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="modal" id="SystemDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="modal-message-layout">
						<div id="modal-icon" class="modal-icon"></div>
						<span class="modal-message modal-messages"></span>
					</div>
				</div>
				<div class="modal-footer">
					<span class="dialog-btn-close dialog-btn-size" data-dismiss="modal">取消</span> <span class="dialog-btn-ok dialog-btn-size">确定</span>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<jsp:include page="../common/top_nav_inner.jsp" flush="true"></jsp:include>
			<div class="col-sm-12 col-md-12 main" style="padding-top: 15px; padding-bottom: 15px; padding-left: 7.5%; padding-right: 7.5%;" data-iframe-css="main">
				<div class="fill-parent bg-white center">
					<img style="margin-top: 44px;" src="${ctx }/assets/img/404.png">

					<div style="margin-top: 16px;">
						<img src="${ctx }/assets/img/face.png"> <span
							style="display: inline-block; margin-left: 18px; font-size: 16px; color: #a3a3a3; margin-bottom: 44px;">很抱歉,您访问地址出错了...</span>
					</div>
					<input type="hidden" id="message" value="${message }">
					<button id="feedback" type="button" class="btn btn-def" style="width: 150px; height: 48px; font-size: 16px; margin-bottom: 244px;">意见反馈</button>
					<button id="reload" type="button" class="btn btn-def" style="width: 150px; height: 48px; font-size: 16px; margin-bottom: 244px; margin-left: 20px;">重新加载</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(function() {
		$("#feedback").click(function() {
			$.ajax({
				url : ctx + "/saveFeedback.shtml",
				data : "content=" + $("#message").val(),
				type : "post",
				dataType : "json",
				success : function(data) {// ajax返回的数据
					var err = {
						"" : "提交成功"
					};
					var json = {
						messages : err,
						type : 'confirm'//warn info
					};
					SystemDialog.set(json);
					SystemDialog.callback(function() {
						history.go(-1);
					});
					SystemDialog.modal('show');
				}
			});
		});

		$("#reload").click(function() {
			window.location.reload(true);
		});
	});
</script>
</html>