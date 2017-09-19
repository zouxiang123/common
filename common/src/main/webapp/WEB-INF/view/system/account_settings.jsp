<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>账号设置</title>
<style>
    #autographView {
        margin: 0 auto;
		width: 265px;
		height: 110px;
		border: 1px solid #e0e0e0;
        float: right;
    }
    .clip-up {
        color: rgb(60,175,255);
        font-size: 18px;
        display: block;
        width: 135px;
        height: 33px;
        cursor: pointer;
    }
</style>
</head>
<body>
<c:if test="${login_user.roleType == '1' }">
   	<nav class="navbar navbar-fixed-top" data-iframe="false" style="display: none;">
		<div class="container-fluid">
			<div id="navbar" class="navbar-collapse">
				<ul class="nav navbar-nav">
					<li>
						<ol class="breadcrumb">
							<li><a href="javascript:history.go(-1);">返回</a></li>
							<li class="active">账户设置</li>
						</ol>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</c:if>
<div class="container-fluid">
<div class="row">
	<div class="fill-parent">
		<div class="col-sm-12 col-md-12 main" style="padding-top: 15px; padding-bottom: 15px; padding-left: 7.5%; padding-right: 7.5%;" data-iframe-css="main">
			<div class="center margin-bottom-5 bg-white">
				<div id="imgdiv">
					<img id="imgShow" class="personal-photo" src="${ctx }/images${user.imagePath}" onclick="jQuery('#up_img').click()" />
					 <img class="personal-photo camera hand" src="${ctx }/assets/img/camera.png" onclick="jQuery('#up_img').click()" />
					<form action="${ctx }/common/uploadImage.shtml?type=u" onsubmit="return uploadImg(this);" enctype="multipart/form-data" method="post" id ="imageUploadForm">
						<input type="file" class="user-photo-uploader" id="up_img" name="image" style="display:none;" accept="image/*"/>
					</form>
					<span class="user-name-uploader margin-bottom-10">${user.name }</span>
					<br/>
					<span class="user-info-uploader margin-bottom-20">${user.positionShow }</span>
				</div>
			</div>
			<div class="list-item margin-bottom-5 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">基本信息</span>
					<div class="tab-action toggle">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color">收 起</span>
					</div>
				</div>
				<div class="tab-body">
					<form id="basicForm" method="post" onsubmit="return false;">
						<input type="hidden" name="id" value="${user.id }" />
						<div class="fill-parent center">
							<div class="table-responsive">
								<table class="personal-table personal-table-margin table">
									<tbody>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">*&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
											<td width="10"></td>
											<td width="200" class="personal-value">
												${user.name }
												<input type="hidden" class="personal-input" name="name" value="${user.name }" placeholder="请输入姓名 " readonly maxlength="32"/>
											</td>
											<td width="300"></td>
										</tr>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">*&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
											<td width="10"></td>
											<td width="200" class="personal-value">
												<%-- <c:forEach var="obj" items="${sex}">
													<div class="item-box">
														<input type="radio" id="sex${obj.value}" name="sex" value="${obj.value}" <c:if test="${obj.isChecked}">checked="checked"</c:if> /> <label
															for="sex${obj.value}" class="form-span form-item-label">${obj.name}</label>
													</div>
												</c:forEach> --%>
												<c:if test="${user.sex == 'M'}">男</c:if>
												<c:if test="${user.sex == 'F'}">女</c:if>
												<input type="hidden" class="personal-input" name="sex" value="${user.sex }">
											</td>
											<td width="300"></td>
										</tr>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">*&nbsp;&nbsp;出生日期：</td>
											<td width="10"></td>
											<td width="200" class="personal-value">
												<%-- <select id="sel_year" class="selectpicker"></select>年 <select id="sel_month"
												class="selectpicker"></select>月 <select id="sel_day" class="selectpicker"></select>日 <input type="hidden" id="birthdayShow"
												name="birthdayShow" value="${user.birthdayShow}" /> --%>
												${user.birthdayShow }
												<input type="hidden" class="personal-input" name="birthdayShow" value="${user.birthdayShow }" readonly>
											</td>
											<td width="300"></td>
										</tr>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">*&nbsp;&nbsp;职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
											<td width="10"></td>
											<td width="200" class="personal-value">
												<%-- <c:if test="${empty professional_title}"><input class="personal-input" type="text" name="position" value="${user.position }" maxlength="32"/></c:if>
												<c:if test="${!empty professional_title}">
												<select name="position" class="selectpicker" value="${user.position}" >
													<option value="">--</option>
													<c:forEach var="item" items="${professional_title }">
														<option value="${item.value }" <c:if test="${item.isChecked }">selected="selected"</c:if> >${item.name }</option>
													</c:forEach>
												</select>
												</c:if> --%>
												<c:forEach var="item" items="${professional_title }">
													<c:if test="${item.isChecked }">${item.name }</c:if>
												</c:forEach>
												<input type="hidden" class="personal-input" name="position" value="${user.position }">
											<td width="300"></td>
										</tr>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">&nbsp;&nbsp;手机号 ：</td>
											<td width="10"></td>
											<td width="200" class="personal-value"><input type="text" class="personal-input" name="mobile" value="${user.mobile }"
												placeholder="请输入手机号 " maxlength="14" /></td>
											<td width="300"></td>
										</tr>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">其他联系方式 ：</td>
											<td width="10"></td>
											<td width="200" class="personal-value"><input type="text" class="personal-input" name="subPhone" value="${user.subPhone }"
												placeholder="请输入其他联系方式 " maxlength="64"/></td>
											<td width="300"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<ul class="pager center-block">
							<input type="button" onclick="basicForm(this);" class="btn btn-def btn-ls margin-bottom-20" value="保存" />
						</ul>
					</form>
				</div>
			</div>

			<div class="list-item margin-bottom-20 bg-white">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">修改密码</span>
					<div class="tab-action toggle">
						<div class="dividing-line"></div>
						<img src="${ctx }/assets/img/arrow-right.png" class="arrow-up"><span class="pull-right action-name default-color">收 起</span>
					</div>
				</div>
				<div class="tab-body">
					<form method="post" onsubmit="return false;" id="passwordForm">
						<div class="fill-parent center">
							<div class="table-responsive">
								<table class="personal-table personal-table-margin table">
									<tbody>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">*&nbsp;&nbsp;原密码：</td>
											<td width="10"></td>
											<td width="200" class="personal-value"><input type="password" class="personal-input" name="password" maxlength="128"/></td>
											<td width="300"></td>
										</tr>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">*&nbsp;&nbsp;新密码 ：</td>
											<td width="10"></td>
											<td width="200" class="personal-value"><input type="password" class="personal-input" name="newPassword" id="newPassword" maxlength="128"/></td>
											<td width="300"></td>
										</tr>
										<tr>
											<td width="300"></td>
											<td width="150" class="personal-title">*&nbsp;&nbsp;重复密码：</td>
											<td width="10"></td>
											<td width="200" class="personal-value"><input type="password" class="personal-input" name="newPasswordConfirm" maxlength="128"/></td>
											<td width="300"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<ul class="pager center-block">
							<input type="button" onclick="passwordForm(this);" class="btn btn-def btn-ls margin-bottom-20" value="保存" />
						</ul>
					</form>
				</div>
			</div>
		</div>
    </div>
</div>
</div>
<script src="${COMMON_SERVER_ADDR}/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${COMMON_SERVER_ADDR}/framework/uploadPreview.min.js?version=${version}"></script>
<script src="${ctx }/assets/js/system/account_settings.js?version=${version}"></script>
</body>
</html>