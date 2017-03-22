<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
				<div class="table-responsive bg-white">
					<table class="table">
						<thead>
							<tr>
								<th style="width:15%;">帐号</th>
								<th style="width:15%;">用户名称</th>
								<th style="width:10%;">角色</th>
								<th style="width:15%;">电话号码</th>
								<th style="width:15%;">分机号</th>
								<th style="width:30%;">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr class="table-default" id="searchRow">
								<td style="width:15%;"><input type="text" id="searchAccount" placeholder="通过账户名搜索"/></td>
								<td style="width:15%;"><input type="text" id="searchName" placeholder="通过用户名搜索"/></td>
								<td style="width:10%;"></td>
								<td style="width:15%;"><input type="text" id="searchMobile" placeholder="通过手机号搜索"/></td>
								<td style="width:15%;"></td>
								<td style="width:30%;"><input type="button" onclick="user_list.reset(this);" value="重置" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="table-responsive bg-white" id="tableDiv" style="position:relative;width: 100%;">
					<table class="table">
						<tbody id="tableBody">
						</tbody>
					</table>
				</div>
				<div class="text-center margin-top-15">
					<button class="btn btn-def" onclick="user_list.addUser();">新增</button>
				</div>
			</div>

			<div class="modal" id="userInfo" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog" style="width:auto;max-width:800px;">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="modal-title"></h4>
							<div class="dialog-close pull-right m-t--33" data-dismiss="modal">
								<img src="${COMMON_SERVER_ADDR}/assets/img/dialog-close.png">
							</div>
						</div>
						<div class="modal-body">
							<div class="dialog-body">
								<form onsubmit="return false;" id="userInfoForm">
								<div class="table-responsive">
									<table class="personal-table table table-align-left">
									<input type="hidden" name="id" value="" /> 
									<tbody>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;账&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
											<td width="10"></td>
											<td width="150" class="personal-value">
												<input type="text" name="account" id="account" required="required" maxlength="32" placeholder="请输入登陆账号"/>
											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
											<td width="10"></td>
											<td width="150" class="personal-value">
												<input type="text" name="name" required="required"  maxlength="32" placeholder="请输入姓名"/>
											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
											<td width="10"></td>
											<td width="150" class="personal-value">
												<c:forEach var="obj" items="${sex}">
													<div class="item-box">
														<input id="sexRadio${obj.itemCode}" type="radio" name="sex" value="${obj.itemCode}" <c:if test="${obj.isChecked}">checked="checked"</c:if> />
														<label for="sexRadio${obj.itemCode}" class="form-span form-item-label">${obj.itemName}</label>
													</div>
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;出生日期：</td>
											<td width="10"></td>
											<td width="200" class="personal-value">
												<input type="text" id="birthdayShow" name="birthdayShow" value="" placeholder="请选择您的生日"/>
											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;手&nbsp;机&nbsp;号：</td>
											<td width="10"></td>
											<td width="150" class="personal-value">
												<input type="text" name="mobile" placeholder="请输入手机号"/>
											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">其他联系方式：</td>
											<td width="10"></td>
											<td width="150" class="personal-value">
												<input type="text" name="subPhone" maxlength="64" placeholder="请输入其他联系方式"/>
											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;角&nbsp;&nbsp;&nbsp;&nbsp;色：</td>
											<td width="10"></td>
											<td width="150" class="personal-value">
												<c:forEach var="role" items="${roleList }">
													<div class="item-box">
														<input id="roleIdChecbox${role.id}" type="checkbox" name="roleId" value="${role.id}" parentId = "${role.parentId }" />
														<label for="roleIdChecbox${role.id}" class="form-span form-item-label">${role.name }</label>
													</div>
												</c:forEach>
											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;职&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
											<td width="10"></td>
											<td width="150" class="personal-value" id="position">
<!-- 												<input type="text" name="position" maxlength="32"/>
-->											</td>
										</tr>
										<tr>
											<td width="30" class="personal-title">*&nbsp;&nbsp;所属系统：</td>
											<td width="10"></td>
											<td width="150" class="personal-value">
												<select name="sysOwner" class="selectpicker" value="${user.sysOwner}" >
													<option value="">--</option>
													<c:forEach var="item" items="${sys_owner }">
														<option value="${item.itemCode }">${item.itemName }</option>
													</c:forEach>
												</select>									
											</td>
										</tr>
									 </tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<div class="dialog-btn-wrap">
								<div class="dialog-btn">
									<button type="button" class="pull-right btn" style="margin-right: 6px;" data-dismiss="modal">取 消</button>
								</div>
								<div class="dialog-btn">
									<button type="button" class="pull-left btn btn-def" style="margin-left: 27px;" onclick="user_list.save()">保 存</button>
								</div>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div>
		<div id="doctorPosition" style="display:none;">
			<select name="position" class="selectpicker" value="${user.position}" >
				<option value="">--</option>
				<c:forEach var="item" items="${doctor_professional_title }">
					<option value="${item.itemCode }">${item.itemName }</option>
				</c:forEach>
			</select>
		</div>
		<div id="nursePosition" style="display:none;">
			<select name="position" class="selectpicker" value="${user.position}" >
				<option value="">--</option>
				<c:forEach var="item" items="${nurse_professional_title }">
					<option value="${item.itemCode }">${item.itemName }</option>
				</c:forEach>
			</select>
		</div>
	</div>
    <jsp:include page="../common/daterangepicker_include.jsp"></jsp:include>
	<link href="${COMMON_SERVER_ADDR}/framework/jquery/scrollbar/perfect-scrollbar.min.css" rel="stylesheet">
	<script src="${COMMON_SERVER_ADDR}/framework/jquery/scrollbar/perfect-scrollbar.jquery.min.js" charset="UTF-8"></script>
    <script src="${ctx}/assets/js/system/user_list.js?version=${version }" charset="UTF-8"></script>
	<script type="text/javascript">
	user_list.init("${isGroup}");
	</script>
</body>
</html>