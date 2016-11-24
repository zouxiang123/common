<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body onresize="bodyResize();">
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
								<td style="width:30%;"><input type="button" onclick="resetSearch(this);" value="重置" /></td>
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
					<button class="btn btn-def" onclick="addUser();">新增</button>
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
								<form onsubmit="return updateUserInfo(this);" id="userInfoForm">
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
												<select id="sel_year" class="selectpicker"> </select><span class="personal-form-span form-span">年</span> 
												<select id="sel_month" class="selectpicker"> </select><span class="personal-form-span form-span">月</span> 
												<select id="sel_day" class="selectpicker"> </select><span class="personal-form-span form-span">日</span> 
												<input type="hidden" id="birthdayShow" name="birthdayShow" value="" />
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
									<button type="button" class="pull-left btn btn-def" style="margin-left: 27px;" onclick="buttonSubmit(this)">保 存</button>
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
	<script src="${COMMON_SERVER_ADDR}/assets/js/common/birthday.js?version=${version}"></script>
	<link href="${COMMON_SERVER_ADDR}/framework/jquery/scrollbar/perfect-scrollbar.min.css" rel="stylesheet">
	<script src="${COMMON_SERVER_ADDR}/framework/jquery/scrollbar/perfect-scrollbar.jquery.min.js" charset="UTF-8"></script>
	<script type="text/javascript">
		var accountExists = false;
		var formData;
		var checkedParentId = "";
		$(document).ready(function() {
			bodyResize();
			
			addEvents();
			refreshTable();
			setBirthday($("#birthdayShow").val());
			
			//添加校验
			addValidate();
		});
		/**动态设置body的高度 */
		function bodyResize(){
			$("#tableDiv").css("height",($(window).height()-$("#tableDiv").offset().top-65));
			$('#tableDiv').perfectScrollbar("update");
		};
		function addEvents() {
			$("#tableDiv").perfectScrollbar();
			
			$("#searchRow").on("keydown","#searchAccount,#searchMobile,#searchName",function(event) {
				if (event.keyCode == 13) {
					refreshTable($(this).attr("id"));
				}
			});
			
			$("input[name='roleId']").click(function(){
				if($(this).is(":checked")){
					var exists = false;
					var parentId= $(this).attr("parentId");
					$("input[name='roleId']:checked").each(function(){
						if(parentId != $(this).attr("parentId")){
							exists = true;
							return false;
						}
					});
					if(exists){
						showWarn("用户角色只能存在一种分类");
						return false;
					};
					if(checkedParentId.indexOf(parentId) == -1){
						getPositionHtml(parentId);
					}else{
						if(isEmpty(checkedParentId)){
							getPositionHtml(parentId);
						}
						checkedParentId = parentId;
					}
				}else{
					if($("input[name='roleId']:checked").length==0){
						$("#position").html("");
						checkedParentId = "";
					}
				}
			});
		}

		function resetSearch(element) {
			$("#searchRow").find("input[type='text']").val("");
			refreshTable();
		}
		
		function getPositionHtml(parentId){
			if(isEmpty(parentId)){
				return ;
			}
			if(parentId.indexOf('2') != -1){
				$("#position").html($("#doctorPosition").html());
			}else if(parentId.indexOf('3') != -1){
				$("#position").html($("#nursePosition").html());
			}else if(parentId.indexOf('1') != -1){
				$("#position").html('<input type="text" name="position" maxlength="32" value="管理员" maxlength="32"/>');
			} else if (parentId.indexOf('4') != -1){
				$("#position").html('<input type="text" name="position" maxlength="32" value="其他" maxlength="32" />');
			}
		}

		function refreshTable(componentId) {
			var account = $("#searchAccount").val();
			var name = $("#searchName").val();
			var mobile = $("#searchMobile").val();
			$.ajax({
				url : ctx + "/system/selectUserWithFilter.shtml",
				data : encodeURI("account=" + account + "&name=" + name + "&mobile=" + mobile),
				type : "post",
				loading : true,
				dataType : "json",
				success : function(items) {
					var tableHtml="";
					for (var i = 0; i < items.length; i++) {
						var item = items[i];
						tableHtml += "<tr class='table-default'>";
						tableHtml += "<td style='width:15%;'>" + convertEmpty(item.account) + "</td>";
						tableHtml += "<td style='width:15%;'>" + convertEmpty(item.name) + "</td>";
						tableHtml += "<td style='width:10%;'>" + convertEmpty(item.roleName) + "</td>";
						tableHtml += "<td style='width:15%;'>" + convertEmpty(item.mobileShow) + "</td>";
						tableHtml += "<td style='width:15%;'>" + convertEmpty(item.subPhone) + "</td>";
						tableHtml += "<td style='width:30%;'><a onclick=\"getFullItem('" + item.id + "');\">修改</a> <a onclick=\"deleteItem('" + item.id
										+ "');\">删除</a> <a onclick=\"resetPassword('" + item.id
										+ "');\">密码重置</a></td>";
						tableHtml += "</tr>";
					}
					$("#tableBody").html(tableHtml);
					$("#tableDiv").perfectScrollbar("update");
				}
			});
		}
		
		function addUser(){
			$("#userInfoForm")[0].reset();
			$("#userInfoForm").find("input[name='id']").val("");
			$("input[name='sex']:eq(0)").prop("checked",true);
			setBirthday();
			$("#modal-title").html("新增用户(默认密码:123456)");
			$("#position").html("");
			checkedParentId = "";
			$("#userInfo").modal("show");
		}
	
		function getFullItem(itemId) {
			$.ajax({
				url : ctx + "/system/getFullUser.shtml",
				data : "id=" + itemId,
				type : "post",
				dataType : "json",
				success : function(data) {// ajax返回的数据
					if (data) {
						getPositionHtml(data.parentRoleId);//获取职称的值
						checkedParentId = data.parentRoleId;
						mappingFormData(data,"userInfoForm");
						setBirthday(data.birthdayShow);
						$("#modal-title").html("用户信息修改");
						$("#userInfo").modal("show");
						formData = $("#userInfoForm").serialize();
					}
				}
			});
		}

		function deleteItem(itemId) {
			var json = {
				messages : {"delete":"您确定要删除用户吗？"},
				type : 'warn',// warn info
				cancelable : true
			};
			SystemDialog.set(json);
			SystemDialog.modal('show');
			SystemDialog.callback(function() {
				$.ajax({
					url : ctx + "/system/deleteUser.shtml",
					data : "id=" + itemId,
					type : "post",
					dataType : "json",
					success : function(data) {// ajax返回的数据
						if (data) {
							if (data.status == 1) {
								window.location.href = window.location.href;
							}
						}
						SystemDialog.modal("hide");
					}
				});
			});
		}
		
		function resetPassword(itemId){
			var json = {
				messages : {"reset":"您确定要重置该用户密码吗？"},
				type : 'info',// warn info
				cancelable : true
			};
			SystemDialog.set(json);
			SystemDialog.modal('show');
			SystemDialog.callback(function() {
				$.ajax({
					url : ctx + "/system/resetUser.shtml",
					data : "id=" + itemId,
					type : "post",
					dataType : "json",
					success : function(data) {// ajax返回的数据
						if (data) {
							if (data.status == 1) {
								alert("重置成功,新密码为 '123456'");
							}
						}
						SystemDialog.modal("hide");
						return;
					}
				});
			});
		}

		function setBirthday(date) {
			$.ymd_DatePicker({
				InitDate : isEmpty(date)?"1960-01-01":date,
				ResultSelector : "#birthdayShow"
			});
		};

		$("#account").change(function() {
			var account = $(this).val();
			if (!isEmpty(account)) {
				accountExists = false;
				$.ajax({
					url : ctx + "/system/checkAccountExists.shtml",
					data : "account=" + account,
					type : "post",
					dataType : "json",
					success : function(data) {// ajax返回的数据
						if (data) {
							if (data.status == 2) {
								showWarn("该用户已经存在");
								accountExists = true;
								return false;
							}else if(data.status == 1){
								accountExists = false;
								return false;
							}
						}
					}
				});
			}
		});

		function updateUserInfo(form) {
			var currentFormData = $(form).serialize();
			if(formData == currentFormData){
				return false;
			}
			if(!$('#userInfoForm').valid()){
				return false;
			}
			$.ajax({
				url : ctx + "/system/saveUser.shtml",
				data : currentFormData,
				type : "post",
				dataType : "json",
				loading : true,
				success : function(data) {// ajax返回的数据
					if (data) {
						if (data.status == 1) {
							refreshTable();
							$("#userInfo").modal("hide");
							return false;
						} else if (data.status == 2) {
							showWarn("用户角色不能为空");
							return false;
						} else if(data.status == 0){
							showWarn("账户名已存在");
							return false;
						}
					}
				}
			});
			return false;
		}
		function addValidate(){
			$('#userInfoForm').validate({
				onfocusout : false,
				// 校验字段
				rules : {
					account : {
						required : [ "账号" ]
					},
					name : {
						required : [ "姓名" ]
					},
					roleId : {
						required : ['角色']
					},
					birthdayShow:{
						date : ["生日"]
					},
					mobile : {
						digits : [ "手机号" ],
						customMaxlength : [ 11, "手机号" ]
					},
					subPhone : {
						customMaxlength : [ 64, "其他联系方式" ]
					}
				},
				messages : {
					mobile : {
						customMaxlength : "请输入正确的手机号",
							digits: "请输入正确的手机号"
					}
				},

				// 全部校验结果
				showErrors : function(errorMap, errorList) {
					showSystemDialog(errorMap);
				},
				submitHandler : function(form) {
					form.onsubmit();
				}
			});
		}
	</script>
</body>
</html>