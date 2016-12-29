<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style type="text/css">

input::-moz-placeholder {
  color: #ddd !important;
}
input:-ms-input-placeholder {
  color: #ddd !important;
}
input::-webkit-input-placeholder {
  color: #ddd !important;
}
</style>
<script src="${COMMON_SERVER_ADDR}/framework/jquery/1.11.3/jquery.cookie.js"></script>
<title>登录</title>
</head>
<body style="padding-top:0px;">
	<div class="p-t-20 p-b-21 p-l-42 p-r-20 bg-rgba-black f-w-100" style="height:100px;position: absolute;">
		<div class="pull-left" style="" >
			<div class="u-img-61 bg-white b-r-50 pull-left text-center">
				<img src="${COMMON_SERVER_ADDR}/assets/img/ui/87DEF51B-18CF-49F0-BD09-001CC6FFFD27@1x.png" alt="">
			</div>
			<div class="pull-left text-center m-l-15 m-t-5">
				<p class="fc-white fs-24 ">学透通®腹透智能系统</p>
				<p class="fc-white">www.xuetoutong.com</p>
			</div>
		</div>
		<div class="pull-right" style="line-height:60px;">
			<a class="fc-white u-a-none" href="${ctx }/about.shtml">关于我们</a>
			<a class="fc-white m-l-28 u-a-none" href="${ctx }/feedback.shtml">意见反馈</a>
		</div>
	</div>
	<div class="u-bg-img">
		<div class="u-logo-zs">
			<div class="f-m-auto f-w-300">
				<div class="f-w-300 p-20">
					<img class="f-w-100" src="${COMMON_SERVER_ADDR}/assets/img/ui/F8570149-1713-4988-B8AA-0F3749B81566@1x.png">
				</div>
				<div class="text-center fs-20 m-t-5 f-w-300">
					学透通®腹透智能系统
				</div>
				<form role="form-signin" action="${ctx }/login.shtml" onsubmit="return validate();" method="post" id="loginForm">
					<input type="hidden" name="isloginSubmit" value="true"/>
					<input type="hidden" name="redirectUrl" value="${redirectUrl }"/>
					<input type="hidden" id="tenantId" name="tenantId" value="${tenantId }" />
					<div class="fs-18 m-t-43 u-lst-50">
						<input placeholder="请输入您的账号" id="account" name="account" value="${account }" class="u-input-none placeholder" type="text" onFocus="if(value==''){this.style.color='#000';this.placeholder='';}" onBlur="if(value==''){this.style.color='#999';this.placeholder='请输入您的账号';}"/>
					</div>
					<div class="fs-18 m-t-12 u-lst-50">
						<input placeholder="请输入您的密码" id="password" name="password" value="${password }" class="u-input-none placeholder" type="password" onFocus="if(value==''){this.style.color='#000';this.placeholder='';}" onBlur="if(value==''){this.style.color='#999';this.placeholder='请输入您的密码';}"/>
					</div>
					<div class="m-t-40 fc-grey">
						<label for="savePassword">
							<input id="savePassword" class="u-checkbox f-checkbox" type="checkbox"  name="savePassword">
							十天内免登录
	                    </label>
					</div>
					<button type="submit" class="bg-blue b-r-4 text-center fs-18 fc-white m-t-21 u-btn-46 f-cp" id="loginFormSubmit">登&nbsp;&nbsp;录</button>
				</form>
			</div>
		</div>
		<div class="text-center f-p-absolute fc-white f-opa8 f-w-100" style="bottom:20px;">
			<p class="fs-16 m-b-6">服务热线 400-021-9859</p>
			<p class="fs-12">学透通医疗科技（上海）有限公司 版权所有 2015-<span id="year"></span></p>
		</div>
	</div>
	<script type="text/javascript">
		  function resizeView() {
		  	var width = $(window).width();
		    var height = $(window).height();
		
		    $(".u-bg-img").width(width);
		    $(".u-bg-img").height(height);
		  }
		$(function() {
			$("#year").text(new Date().getFullYear());
			resizeView();
		  	$(window).resize(function(event) {
		  		resizeView();
		  	});
			var width = $(window).width();
		    var height = $(window).height();

		    $(".u-bg-img").width(width);
		    $(".u-bg-img").height(height);
			
			$("#loginFormSubmit").removeClass("disabled");
			if (window.top != window.self) {
				top.location.reload(true);
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
