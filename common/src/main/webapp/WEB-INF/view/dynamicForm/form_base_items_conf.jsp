<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理</title>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="${ctx }/framework/ztree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/framework/ztree/js/jquery.ztree.all.min.js"></script>
<style type="text/css">
.tree-block {
	min-width:250px;
	padding-bottom:20px;
	border-radius: 3px;
}
.ztree {
	margin: 20px 10px 20px 20px;
	border: 1px solid #EDEFF0;
	-webkit-box-shadow:2px 2px 2px 2px #EDEFF0 inset;
	box-shadow:2px 2px 2px 2px #EDEFF0 inset;
	border-radius: 10px;
	background: #fff;
	width:auto;
	height:auto;
	overflow-y: auto;
	overflow-x: auto;
}
.group-title{
	font-size: 16px;
    color: #484848;
    line-height: 45px;
    text-align: right;
    min-width: 150px;
    display: inline-block;
}
.disable{
	background:#EDEFF0;
}
.width-300{
	width: 300px;
}
.ztree li a input.rename {height:16px;line-height:16px; width:80px; padding:0; margin:0;
  font-size:12px; border:1px #585956 solid; *border:0px}
</style>
</head>
<body onresize="form_items_conf_obj.resizeTree();">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12" style="padding:0px;">
				<div class="simple-line bg-white border-bottom-line">
			        <span class="tabbar active" onclick="window.location.href='${ctx }/dynamicForm/formBaseItems/view.shtml?sys=${sysOwner }'">基础数据维护</span>
			        <span class="tabbar" onclick="window.location.href='${ctx }/dynamicForm/formItems/view.shtml?sys=${sysOwner }'">表单配置</span>
			        <span style="margin-left: 20px;">所属系统：</span>
			        <select id="sys_owner">
			        	<c:forEach var="item" items="${sys_owner }">
	          				<option value="${item.itemCode }" <c:if test="${item.isChecked }">selected</c:if> >${item.itemName }</option>
	          			</c:forEach>
			         </select>
			         <div class="tab-action" id="refresh" style="padding:10px 34px;">
						<div class="dividing-line"></div>
						<span>刷新缓存</span><img src="/pd/assets/img/refresh.png" class="refresh">
					</div>
			    </div>
			    <div class="fill-parent" id="contentDiv">
					<div class="col-sm-5 col-md-4 bg-white tree-block">
						<div>
							<ul id="itemsTree" class="ztree"></ul>
						</div>
					</div>
					<div class="col-sm-7 col-md-8 bg-white tree-block" style="padding-left:10px;">
						<form action="#" onsubmit="return false;" id="itemForm">
							<input type="hidden" name="id">
							<input type="hidden" name="pItemCode">
							<input type="hidden" name="itemCode">
							<input type="hidden" name="isLeaf">
							<input type="hidden" name="isFixed">
							<div class="list-group">
								<div class="list-group-item">
									<span class="group-title">父节点：</span>
		                            <input type="text" class="personal-input width-300 disable" name="itemDesc" readonly="readonly">
	                            </div>
								<div class="list-group-item">
									<span class="group-title">名称：</span>
		                            <input type="text" class="personal-input width-300" name="itemName" maxlength="64">
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">分组标签：</span>
									<input type="text" class="personal-input width-300" name="groupTag" maxlength="36">
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">显示几列：</span>
									<input type="number" class="personal-input width-300" name="displayCols" step="1" maxlength="9">
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">控件类型：</span>
                            		<select class="personal-input width-300" name="itemType">
                            			<c:forEach var="item" items="${form_element_type }">
                            				<option value="${item.itemCode }">${item.itemName }</option>
                            			</c:forEach>
                            		</select>
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">数据类型：</span>
                            		<select class="personal-input width-300" name="dataType">
                            			<c:forEach var="item" items="${form_item_data_type }">
                            				<option value="${item.itemCode }">${item.itemName }</option>
                            			</c:forEach>
                            		</select>
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">单位：</span>
                            		<select class="personal-input width-300" name="unit">
                            			<option value="">--</option>
                            			<c:forEach var="item" items="${form_item_unit }">
                            				<option value="${item.itemCode }">${item.itemName }</option>
                            			</c:forEach>
                            		</select>
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">取值范围：</span>
									<input type="number" class="personal-input width-100" name="minValue" maxlength="9">~<input type="number" class="personal-input width-100" name="maxValue" maxlength="9">
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">关联项：</span>
		                            <input type="text" class="personal-input width-300" name="fkCode" readonly="readonly">
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">是否有效：</span>
									<div class="group-radio" style="display: inline-block;">
										<label class="form-radio-label min-width-100"><input type="radio" name="isEnable" value="true" checked="checked">是</label>
										<label class="form-radio-label min-width-100"><input type="radio" name="isEnable" value="false">否</label>
									</div>
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">是否打分：</span>
									<div class="group-radio" style="display: inline-block;">
										<label class="form-radio-label min-width-100"><input type="radio" name="needScore" value="true" checked="checked">是</label>
										<label class="form-radio-label min-width-100"><input type="radio" name="needScore" value="false">否</label>
									</div>
	                            </div>
	                            <div class="list-group-item">
									<span class="group-title">分值：</span>
									<input type="text" class="personal-input width-300" name="score" maxlength="9">
	                            </div>
							</div>
						</form>
						<div style="float: left;position: relative;margin-left: 200px;margin-top:25px;">
							<button class='btn btn-def hide' onclick="form_items_conf_obj.saveItem(false);" id="saveItem">保存</button>
							<button class='btn btn-def hide' onclick="form_items_conf_obj.saveItem(true);" id="saveItemAndAdd">保存并添加</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/assets/js/dynamicForm/form_base_items_conf.js?version=${version}"></script>
</body>
</html>