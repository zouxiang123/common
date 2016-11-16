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
	min-width: 250px;
	padding-bottom: 20px;
	border-radius: 3px;
}

.ztree {
	margin: 20px 10px 20px 20px;
	border: 1px solid #EDEFF0;
	-webkit-box-shadow: 2px 2px 2px 2px #EDEFF0 inset;
	box-shadow: 2px 2px 2px 2px #EDEFF0 inset;
	border-radius: 10px;
	background: #fff;
	width: auto;
	height: auto;
	overflow-y: auto;
	overflow-x: auto;
}

.ztree li a input.rename {
	height: 16px;
	line-height: 16px;
	width: 80px;
	padding: 0;
	margin: 0;
	font-size: 12px;
	border: 1px #585956 solid;
	*border: 0px
}

.table>tbody tr.active td {
	background-color: #e7ecf0;
	color: #484848;
}
</style>
</head>
<body onresize="form_conf_obj.resizeTree();">
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12" style="padding:0px;">
				<div class="simple-line bg-white border-bottom-line">
					<span class="tabbar" onclick="window.location.href='${ctx }/dynamicForm/formBaseItems/view.shtml?sys=${sysOwner }'">基础数据维护</span>
			        <span class="tabbar active" onclick="window.location.href='${ctx }/dynamicForm/formItems/view.shtml?sys=${sysOwner }'">表单配置</span> 
			        <span style="margin-left: 20px;">所属系统：</span>
			        <select id="sys_owner">
						<c:forEach var="item" items="${sys_owner }">
							<option value="${item.itemCode }" <c:if test="${item.isChecked }">selected</c:if>>${item.itemName }</option>
						</c:forEach>
					</select>
					<div class="tab-action" id="refresh" style="padding: 10px 34px;">
						<div class="dividing-line"></div>
						<span>刷新缓存</span><img src="/pd/assets/img/refresh.png" class="refresh">
					</div>
				</div>
				<div class="fill-parent" id="contentDiv">
					<div class="col-sm-4 col-md-4 bg-white tree-block">
						<div>
							<ul id="itemsTree" class="ztree" data-ztree></ul>
						</div>
					</div>
					<div class="col-sm-4 col-md-4 bg-white tree-block" style="padding-left: 10px;">
						<div style="margin-top: 10px;">
							<span style="margin-left: 20px;">所属模块：</span> <select id="item_category">
							</select>
						</div>
						<div>
							<ul id="confTree" class="ztree" style="margin-top: 10px; margin-bottom: 10px;" data-ztree></ul>
						</div>
						<div class="fill-parent" style="margin: 0px 0px 10px 20px;">
							<span>记录单名称：</span><input id="formName" value="" placeholder="请输入记录单名称" />
						</div>
						<div style="float: left; position: relative; margin-left: 100px;">
							<button class='btn btn-def' onclick="form_conf_obj.saveConfTree();">保存</button>
						</div>
					</div>
					<div class="col-sm-4 col-md-4 bg-white tree-block" style="padding-left: 10px;">
						<div style="margin-top: 10px;">
							<span><span style="margin-left: 20px;">版本列表</span></span>
						</div>
						<div id="category_list" class="ztree" style="margin-top: 10px;">
							<table class="table table-border">
								<thead>
									<th>版本</th>
									<th>表单名称</th>
									<th>操作</th>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/assets/js/dynamicForm/form_items_conf.js?version=${version}"></script>
</body>
</html>