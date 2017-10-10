<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>意见反馈</title>
</head>
<body>
	<nav class="navbar navbar-fixed-top" style="display:none;" data-iframe="false" >
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
			<div class="col-sm-12 col-md-12 bg-grey main-wrap margin-top-20" id="mainContent">
				<form id="frm" action="${ctx }/saveFeedback.shtml" method="post">
					<div class="main-layout">
						<div class="tab-header">
							<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">联系方式</span>
						</div>
						<div class="tab-body">
							<textarea id="tel" name="tel" class="textarea-style" rows="1" placeholder="邮件/QQ/电话" maxlength="20"></textarea>
						</div>
						<div class="tab-header">
							<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">反馈内容</span>
						</div>
						<div class="tab-body">
							<textarea id="content" name="content" class="textarea-style" rows="4" placeholder="请输入内容" maxlength="512"></textarea>
						</div>
					</div>

					<button type="button" id="btnSave" class="btn btn-def btn-ls center-block" style="margin-top: 20px;">提 交</button>
				</form>
			</div>
		</div>
	</div>

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
	
	<script type="text/javascript">
		if(isFromIframe()){
			$("#mainContent").css("margin-top","0px");
			$("#mainContent").css("padding","15px 15px 0px 15px");
		}
	
		$('#frm').validate({
			onfocusout : false,
			rules : {
				tel : {
					required : [ "邮件/QQ/电话" ]
				},
				content : {
					required : [ "内容" ]
				}
			},
	
			// 全部校验结果
			showErrors : function(errorMap, errorList) {
				showSystemDialog(errorMap);
			}
		});
		
		$("#btnSave").click(function() {
			
			if ($("#frm").valid()) {
				$.ajax({
					url : ctx + "/saveFeedback.shtml",
					type : "post",
					dataType : "json",
					data : $("#frm").serialize(),
					success : function(data) {// ajax返回的数据
						if (data == 1) {
							var err = {"": "提交成功"};
					        var json = {
					          messages: err,
					          type: 'confirm'//warn info
					        };
					        SystemDialog.set(json);
					        SystemDialog.callback(function() {
								history.go(-1);
					        });
							SystemDialog.modal('show');
						}
					}
				});
			}
		});
	</script>
</body>
</html>