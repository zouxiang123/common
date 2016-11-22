<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>

<script src="${ctx }/framework/jquery/1.11.3/jquery.cookie.js"></script>
<title>登录</title>
</head>
<body class="bg-white">

	<nav class="navbar navbar-fixed-top">
		<div class="container-fluid clear-padding-margin">
			<div id="navbar-login" class="navbar-collapse">
				<a href="${ctx }/feedback.shtml"><img class="login-logo" src="${ctx}/assets/img/login-logo.png"><span class="pull-right login-nav-link">意见反馈</span> </a> 
				<a href="${ctx }/about.shtml"><span class="pull-right login-nav-link">关于我们</span></a> 
			</div>
		</div>
	</nav>

	<div class="container-fluid clear-padding-margin">
		<div class="row" style="margin-right: -15px !important;">
			<div class="col-sm-12 col-md-12">
				<div id="login-bg">
					<div class="login-tip-container refuse">
						<p class="login-tip login-text-shadow"></p>
						<p class="login-sub-tip login-text-shadow"></p>
					</div>

					<div class="login-container opacity-75 login-shadow refuse">
						<div class="login-user-photo">
							<img src="assets/img/login-photo.png" id="imagePath">
						</div>
						<div class="login-input-container">
							<form role="form-signin" action="${ctx }/login.shtml" onsubmit="return validate();" method="post" id="loginForm">
								<input type="hidden" name="isloginSubmit" value="true"/>
								<input type="hidden" name="redirectUrl" value="${redirectUrl }"/>
								<img class="login-username" id="imgUser" src="assets/img/login-username.png">
								<div class="login-username-line"></div>
								<input type="text" id="account" name="account" value="${account }" onblur="showNameAndPictureByInput()" class="login-input" placeholder="帐 号">
								  <span class="login-username-tip" id="userName"></span> 
								 <img class="login-password"
									src="assets/img/login-password.png">
								<div class="login-password-line"></div>
								<input type="password" id="password" name="password" value="${password }" class="login-input" placeholder="密 码">
								<input type="hidden" id="tenantId" name="tenantId" value="${tenantId }" />
								<div class="pull-left save-password-box">
		                            <input id="savePassword" class="input-bg-green" type="checkbox"  name="savePassword">
		                            <label for="savePassword" class="form-span save-password">十天内免登录</label>
		                        </div>
								<button type="submit" class="btn btn-def btn-bg-green login-btn disabled" id="loginFormSubmit">登 录</button>
							</form>
						</div>
					</div>
				</div>
				<div class="fill-parent center refuse">
	                <!-- <div class="front-page-title-wrap">
	                    <div class="front-page-title-line"></div>
	                    <div class="front-page-title">医院文化</div>
	                </div>
	
	                <div class="fill-parent center margin-bottom-36">
	                    <div class="hospital1"></div>
	                    <div class="hospital2"></div>
	                </div>
	
	                <div class="fill-parent center">
	                    <div class="front-page-sub-title margin-bottom-26">医院使命</div>
	                    <div class="front-page-sub-content margin-bottom-48">以人为本、关爱生命、提供人性化高品质的医疗保健服务</div>
	                </div>
	
	                <div class="fill-parent center">
	                    <div class="front-page-sub-title margin-bottom-26">核心价值观</div>
	                    <div class="front-page-sub-content margin-bottom-48">诚信仁爱 、求实创新、团结奉献 、和谐进取</div>
	                </div>
	
	                <div class="fill-parent center">
	                    <div class="front-page-sub-title margin-bottom-26">医院宗旨</div>
	                    <div class="front-page-sub-content margin-bottom-48">一切为了病人、服务人民奉献社会</div>
	                </div>
	
	                <div class="fill-parent center">
	                    <div class="front-page-sub-title margin-bottom-26">医院精神</div>
	                    <div class="front-page-sub-content margin-bottom-68">严谨、求实、团结、奉献</div>
	                </div>
	
	                <div class="fill-parent margin-bottom-64">
	                    <img src="assets/img/xytxzx.jpg" style="width: 100%;">
	                </div> -->
	
	                <div class="front-page-title-wrap">
	                    <div class="front-page-title-line"></div>
	                    <div class="front-page-title">产品介绍</div>
	                </div>
	
	                <div class="fill-parent center margin-top-5 margin-bottom-160">
	                    <div class="front-page-sub-title margin-bottom-20">学透通®血透智能系统功能模块展示</div>
	                    <img src="assets/img/soft-info.jpg" style="width: 100%;">
	                </div>
	            </div>
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

	<footer class="main-footer center refuse">
	    <div class="bottom-logo"></div>
	    <div class="footer-divide-line"></div>
	
	    <div style="position: relative;margin-right: 14.2%;margin-top: 0px;">
	        <span class="QR-code-txt" style="right: 176px;">微信公众号</span>
	        <span class="QR-code-txt" style="right: 0px;">学透通</span>
	    </div>
	    <div style="position: relative;margin-right: 13%;">
	        <img class="QR-code-wx" src="assets/img/wx.png">
	        <img class="QR-code-website" src="assets/img/website.png">
	    </div>
	
	    <div style="display: inline-block;">
	        <p class="footer-site-name" style="margin-left: -96px;">服务热线&nbsp;&nbsp;&nbsp;&nbsp;400-021-9859</p>
	        <p class="footer-site-info" style="margin-left: -150px;">学透通医疗科技（上海）有限公司  版权所有  2015-2015 </p>
	    </div>
	
	    <p class="footer-link"><a href="#">联系我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">移动客户端</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
	            href="#">风格模板</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">官方博客</a></p>
	</footer>

	<script type="text/javascript">
	  var index = parseInt(Math.random() * 10);
	  var picbgurl = "assets/img/login-bg/pc/";
	  var picbg = 5;
	  $("#login-bg").css("background-image", "url('" + picbgurl + ((index % picbg) + 1) +".jpg')");
	  
	  //var tip = ['健康心，人医情。','德厚业精，病人至亲。','让生命从此享受阳光。','心服务，馨感受，新希望。','医患同心，真情互动。'];
	  //var subTip = ['我们一直在努力，缘于生命是如此美丽。','凭实力给你健康，用爱心播撒阳光。','幸福，从健康开始；健康，从这里开始。','与科技同步，与健康同行。','您的灿烂笑容是我们不变的追求。'];
	  var tip = ['医院核心价值观'];
	  var subTip =['诚信仁爱 、求实创新、团结奉献 、和谐进取'];
    
	  $(document).ready(function() {
	    var width = $(window).width();
	    var height = $(window).height();
	    height = height - 66;
	    $("#login-bg").width(width);
	    $("#login-bg").height(height);
	    $(".login-tip-container").show();
	    $(".login-container").show();
	    $(".fill-parent").show();
	    $(".main-footer").show();
	    
	    $('.login-tip').html(tip[index % tip.length]);
	    $('.login-sub-tip').html(subTip[index % subTip.length]);
	    setInterval(function() {
	      index++;
	      $('.login-tip').html(tip[index % tip.length]);
	      $('.login-sub-tip').html(subTip[index % subTip.length]);
	      $("#login-bg").css("background-image", "url('" + picbgurl + ((index % picbg) + 1) +".jpg')");
	    }, 600000);

			$(window).scroll(function() {
				if ($(window).scrollTop() >= height) {
					$(".navbar").css("opacity", "0");
				} else {
					$(".navbar").css("opacity", (height - $(window).scrollTop()) / height * 1);
				}
			});

			function getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			}
			
			
		});
	  

		$(function() {
			$("#loginFormSubmit").removeClass("disabled");
			if (window.top != window.self) {
				top.location = self.location;
			}
			$("#account").val($.cookie('account'));
			if (isEmpty($("#password").val())) {
				$("#password").val($.cookie('password'));
			}
			if ($.cookie('savePwd') == "true") {
				$("#savePassword").attr("checked", true);
			} else {
				$("#savePassword").attr("checked", false);
			}
			if (!isEmpty("${errmsg}")) {
				showWarn("${errmsg}");
				return false;
			}
		});

		function validate() {
			if($("#loginFormSubmit").hasClass("disabled")){
				return false;
			}
			if (isEmpty($("#account").val())) {
				showWarn("请先输入您的帐号！");
				return false;
			}
			if (isEmpty($("#password").val())) {
				showWarn("请先输入您的密码！");
				return false;
			}
			if ($("#savePassword").is(":checked")) {
				$.cookie('password', $("#password").val(), {
					expires : 10,
					path : '/'
				});
			} else {
				$.removeCookie('password', { path: '/' });
			}
			$.cookie('account', $("#account").val(), {
				expires : 10,
				path : '/'
			});
			$.cookie('tenantId', $("#tenantId").val(), {
				expires : 10,
				path : '/'
			});
			$.cookie('savePwd', $("#savePassword").is(":checked"), {
				expires : 10,
				path : '/'
			});
			console.log($("#loginForm").serialize());
			return true;
		}
		//输入账号时，显示姓名和头像
		function showNameAndPictureByInput(){
			//将用户名清除
			$("#userName").text("");
			var userAccount = $("#account").val();///用户名
			var tenId = $("[name='tenantId']").val();//租户编号
			var data = "tenId="+tenId+"&account="+userAccount;
			$.ajax({
				url:ctx+"/showNameAndPictureByInput.shtml",
				data:encodeURI(data),
				type:'POST',
				dataType:'json',
				success:function(result){
					$("#userName").text(result.sysUser.name);
					$("#imagePath").attr("src",ctx+"/images"+result.sysUser.imagePath);
				}
			});
		}
	</script>
</body>
</html>
