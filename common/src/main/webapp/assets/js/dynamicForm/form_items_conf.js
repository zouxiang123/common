$(document).ready(function() {
	form_conf_obj.init();
});

/** 左补0 */
function lPad(str, bit) {
	if (isEmpty(bit)) {
		bit = 3;
	}
	var countLength = (str + "").length;
	for (var i = bit; i > countLength; i--) {
		str = "0" + str;
	}
	return str;
}
var form_conf_obj = {
	sysOwner : null,
	category : null,
	init : function() {
		form_conf_obj.sysOwner = $("#sys_owner").val();
		form_conf_obj.addEvents();
		form_conf_obj.resizeTree();
		form_conf_obj.initTreeSettings();
		form_conf_obj.getAllTreeData(false);
	},
	// tree显示区域设置
	resizeTree : function() {
		$(".tree-block").css("height", ($(window).height() - $("#contentDiv").offset().top - 50));
		$(".ztree").css("height", $(".tree-block").height() - 20);
		$("#confTree").css("height", $("#confTree").height() - 80);
	},
	addEvents : function() {
		$("#sys_owner").on("change", function() {
			form_conf_obj.sysOwner = $(this).val();
			form_conf_obj.getAllTreeData(false);
		});
		$("#item_category").on("change", function() {
			form_conf_obj.category = $(this).val();
			form_conf_obj.getAllTreeData(true);
		});
		$("#refresh").on("click", function() {
			if (isEmpty(form_conf_obj.sysOwner)) {
				return;
			}
			$.ajax({
				url : ctx + "/dynamicForm/formItems/refresh.shtml",
				data : encodeURI("sysOwner=" + form_conf_obj.sysOwner),
				type : "post",
				dataType : "json",
				loading : true,
				success : function(data) {
					if (data.status == 1) {
						showTips("刷新成功");
					} else {
						showWarn("刷新失败，请联系管理员");
					}
				}
			});
		});
		$("#category_list").on("click", "tr", function() {
			$(this).addClass("active").siblings().removeClass("active");
			form_conf_obj.loadTreeData("confTree");
		});
	},
	getFormCategoryList : function(sysOwner) {
		$.ajax({
			url : ctx + "/dynamicForm/formItems/getFormCategory.shtml",
			type : "post",
			data : "sysOwner=" + sysOwner,
			dataType : "json",
			async : false,
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					var html = "";
					if (!isEmptyObject(data.items)) {
						for (var i = 0; i < data.items.length; i++) {
							var item = data.items[i];
							html += "<option value='" + item.itemCode + "'>" + item.itemName + "</option>";
						}
					}
					$("#item_category").html(html);
					form_conf_obj.category = $("#item_category").val();
				}
			}
		});
	},
	getAllTreeData : function(onlyNeedCategory) {
		if (!onlyNeedCategory) {// 如果是刷新所有树数据
			form_conf_obj.getFormCategoryList(form_conf_obj.sysOwner);
		}
		var category = form_conf_obj.category;
		$.each($(".ztree[data-ztree]"), function() {
			if (!(onlyNeedCategory && !$(this).data("needCategory"))) {// 清空tree的数据
				$.fn.zTree.init($("#" + $(this).attr("id")), $("#" + $(this).attr("id")).data("settings"), []);
			}
			var needLoad = false;
			if (!onlyNeedCategory) {// 加载所有数据
				if (!($(this).data("needCategory") && isEmpty(category)))
					needLoad = true;
			} else {
				if ($(this).data("needCategory") && !isEmpty(category))
					needLoad = true;
			}
			if (needLoad) {
				form_conf_obj.loadTreeData($(this).attr("id"));
				form_conf_obj.getFormRecordList();
			}
		});
	},
	/** 初始化树配置 */
	initTreeSettings : function() {
		var itemTreeEvents = {
			beforeDrop : function(treeId, treeNodes, targetNode, moveType) {
				if (treeId == "itemsTree")// 不允许拖到基础数据树
					return false;
				if (targetNode.level >= 5) {// 树的层级不能超过六层
					showTips("超过最大允许的层级数");
					return false;
				}
				// 拖拽的节点不是根节点时
				if (!(isEmptyObject(targetNode) || (moveType != "inner" && !targetNode.parentTId))) {
					if (form_conf_obj.checkItemExists(treeNodes, treeId, targetNode.category)) {
						showTips("节点在" + targetNode.getPath()[0].name + "中已存在");
						return false;
					}
					var index = 1;
					if (!isEmptyObject(targetNode.children)) {// 获取树最后一个节点的id
						var lastId = targetNode.children[targetNode.children.length - 1].id;
						index = parseInt(lastId.substring(lastId.length - 3, lastId.length)) + 1;
					}
					for (var i = 0; i < treeNodes.length; i++) {
						// convertToTargetNode(treeNodes[i], targetNode.id + lPad(index + i), index + i);
						form_conf_obj.convertToTargetNode(treeNodes[i], targetNode.category);
					}
					return true;
				} else {
					return false;
				}
			}
		};
		var settings = {
			view : {
				selectedMulti : true,
				dblClickExpand : true
			},
			check : {
				enable : false
			},
			data : {
				key : {
					title : "title",
					checked : "checked"
				},
				simpleData : {
					enable : true,
					rootPId : "0"
				}
			},
			edit : {
				drag : {
					isCopy : true,
					isMove : false
				},
				enable : true,
				showRemoveBtn : false,
				showRenameBtn : false
			},
			callback : {
				// beforeDrag : zTreeBeforeDrag,
				beforeDrop : itemTreeEvents.beforeDrop
			}
		};
		$("#itemsTree").data("settings", settings);
		$("#itemsTree").data("needCategory", false);
		$("#itemsTree").data("url", ctx + "/dynamicForm/formBaseItems/getItems.shtml");
		// 表单树配置
		var confTreeEvents = {
			/** 设置表单配置单树按钮显示 */
			showRemoveBtn : function(treeId, treeNode) {
				return treeNode.pId != '0';
			},
			/** 表单配置树节点删除 */
			beforeRemove : function(treeId, treeNode) {
				if ($("#category_list tr.active").length > 0) {// 修改操作
					var treeObj = $.fn.zTree.getZTreeObj("confTree");
					var nodes = treeObj.transformToArray(treeNode);
					var allNew = true;
					for (var i = 0; i < nodes.length; i++) {
						if (allNew && !nodes[i].isNew)
							allNew = false;
					}
					if (!allNew) {
						var flag = false;
						var datas = [];
						for (var i = 0; i < nodes.length; i++) {
							if (nodes[i].pId == '0')// 根节点不能删除
								continue;
							datas.push(form_conf_obj.convertNode(nodes[i], false));
						}
						if (datas.length == 0)
							flag = false;
						$.ajax({
							url : ctx + "/dynamicForm/formItems/delConf.shtml",
							type : "post",
							contentType : 'application/json;charset=utf-8',
							data : JSON.stringify(datas),
							dataType : "json",
							async : false,
							loading : true,
							success : function(data) {
								if (data.status == 1) {
									showTips("删除成功^_^");
									flag = true;
								} else if (data.status == 2) {
									var errorObj = {};
									for (var i = 0; i < data.errorList.length; i++) {
										errorObj[i] = data.errorList[i];
									}
									showSystemDialog(errorObj);
								}
							}
						});
						return flag;
					} else {
						return true;
					}
				} else {
					return true;
				}

			},
			/** 表单配置树拖拽处理,排序用 */
			beforeDrop : function(treeId, treeNodes, targetNode, moveType) {
				// 如果是根节点
				if (isEmptyObject(targetNode) || (moveType != "inner" && !targetNode.parentTId)) {
					return false;
				}
				if (targetNode.level >= 5 && moveType == "inner") {// 树的层级不能超过六层
					showTips("超过最大允许的层级数", 1000);
					return false;
				}
				if (treeNodes[0].category == targetNode.category) {
					// 只允许同类别之间的节点移动
					return true;
				}
				return false;
			}

		};
		var confSettings = cloneObject(settings);
		confSettings.check.enable = true;
		confSettings.check.chkboxType = {
			"Y" : "ps",
			"N" : "s"
		};
		confSettings.edit.removeTitle = '删除节点';
		confSettings.edit.showRemoveBtn = confTreeEvents.showRemoveBtn;
		confSettings.edit.drag.isCopy = false;
		confSettings.edit.drag.isMove = true;
		confSettings.edit.showRenameBtn = true;
		confSettings.callback.beforeRemove = true;
		confSettings.callback.beforeDrop = confTreeEvents.beforeDrop;
		$("#confTree").data("settings", confSettings);
		$("#confTree").data("needCategory", true);
		$("#confTree").data("url", ctx + "/dynamicForm/formItems/getItems.shtml");
	},
	/** 判断单类别itemCode在随访树中是否已存在 */
	checkItemExists : function(treeNodes, treeId, category) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var allNodes = treeObj.getNodesByParam("category", category, null);
		var nodes = treeObj.transformToArray(treeNodes);
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].itemType == "div") {
				continue;
			}
			for (var t = 0; t < allNodes.length; t++) {
				if (nodes[i].id == allNodes[t].id)
					return true;
			}
		}
		return false;
	},
	/** 将基础库的节点转换成随访树节点 */
	convertToTargetNode : function(node, category) {
		node.tempId = null;// 清空数据库对应的id
		node.isNew = true;
		node.category = category;
		if (node.itemType == "div") {// 如果是div节点，重新生成id
			node.id = "div_" + new Date().getTime();
		}
		/*node.id = id;*/// 无需重新生成id
		/*node.orderBy = orderBy;*/
		if (!isEmptyObject(node.children)) {// 拖拽的节点包含子节点时
			for (var i = 0; i < node.children.length; i++) {
				/*	node.children[i].pId = node.id;// 重新生成子节点pId
					id = node.id + lPad(i + 1);// 重新生成子节点Id
					convertToTargetNode(node.children[i], id, i + 1);*/
				form_conf_obj.convertToTargetNode(node.children[i], category);
			}
		}
	},
	/** 加载树数据 */
	loadTreeData : function(id) {
		var sysOwner = form_conf_obj.sysOwner;
		var category = form_conf_obj.category;
		if (isEmpty(id) || isEmpty(sysOwner) || ($("#" + id).data("needCategory") && isEmpty(category))) {
			$.fn.zTree.init($("#" + id), $("#" + id).data("settings"), []);
			return;
		}
		var data = "sysOwner=" + sysOwner + "&category=" + category;
		if (id == "itemsTree") {// 如果是基础数据树，isEnable属性
			data += "&isEnable=true";
		}
		if ($("#category_list tr.active").length > 0) {
			var formId = $("#category_list tr.active").data("id");
			data += "&fkFormId=" + formId;
		}
		$.ajax({
			url : $("#" + id).data("url"),
			data : encodeURI(data),
			type : "post",
			dataType : "json",
			loading : true,
			// async : false,
			success : function(data) {
				var nodes = [];
				if (id == "itemsTree") {// 如果是基础数据树，添加div节点供拖拽
					nodes.push(form_conf_obj.convertNode({
						itemCode : "1",
						pItemCode : "0",
						itemName : "div节点",
						itemType : "div",
						isEnable : true,
						sysOwner : sysOwner,
						title : "div节点，供布局使用"
					}, true));
				}
				for (var i = 0; i < data.items.length; i++) {
					var item = data.items[i];
					// 将对象转换为tree对象
					nodes.push(form_conf_obj.convertNode(item, true));
				}
				if (nodes.length == 0) {
					if (!isEmpty(form_conf_obj.category)) {
						nodes.push({
							id : form_conf_obj.category,
							pId : "0",
							sysOwner : form_conf_obj.sysOwner,
							category : form_conf_obj.category,
							name : $("#item_category option:selected").text(),
							title : "请修改记录单名称",
							checked : true,
							isNew : true
						});
					}
				}
				$.fn.zTree.init($("#" + id), $("#" + id).data("settings"), nodes);
				var treeObj = $.fn.zTree.getZTreeObj(id);
				var expandNodes = treeObj.getNodesByParam("pId", "0");
				for (var i = 0; i < expandNodes.length; i++) {// 只展开根节点
					treeObj.expandNode(expandNodes[i], true);
				}
				// $.fn.zTree.getZTreeObj(id).expandAll(true);
			}
		});
	},
	/** 保存随访单配置 */
	saveConfTree : function() {
		var formName = $("#formName").val();
		if (isEmpty(formName)) {
			showWarn("表单名称不能为空");
			return false;
		}
		var treeObj = $.fn.zTree.getZTreeObj("confTree");
		var nodes = treeObj.transformToArray(treeObj.getNodes());
		var datas = [];
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].pId == '0') {
				nodes[i].name = formName;
			}
			nodes[i].isLeaf = !nodes[i].isParent;
			nodes[i].orderBy = nodes[i].getIndex() + 1;
			nodes[i].itemLevel = nodes[i].level;
			nodes[i].itemPath = form_conf_obj.getNodePath(nodes[i].getPath());
			datas.push(form_conf_obj.convertNode(nodes[i], false));
		}
		if (datas.length == 0)
			return;
		var saveData = {
			"records" : datas,
			"formName" : formName
		};
		if ($("#category_list tr.active").length > 0) {
			var formId = $("#category_list tr.active").data("id");
			saveData["formId"] = formId;
		}
		$.ajax({
			url : ctx + "/dynamicForm/formItems/saveConf.shtml",
			type : "post",
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(saveData),
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					form_conf_obj.loadTreeData("confTree");
					form_conf_obj.getFormRecordList();
					showTips("保存成功^_^");
				} else if (data.status == 2) {
					var errorObj = {};
					for (var i = 0; i < data.errorList.length; i++) {
						errorObj[i] = data.errorList[i];
					}
					showSystemDialog(errorObj);
				}
			}
		});
	},
	getFormRecordList : function() {
		var sysOwner = form_conf_obj.sysOwner;
		var category = form_conf_obj.category;
		if (isEmpty(sysOwner) && isEmpty(category))
			return;
		$.ajax({
			url : ctx + "/dynamicForm/form/getList.shtml",
			type : "post",
			data : encodeURI("sysOwner=" + sysOwner + "&category=" + category),
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					var html = "";
					if (!isEmptyObject(data.items)) {
						for (var i = 0; i < data.items.length; i++) {
							var item = data.items[i];
							var cacheData = "data-id='" + item.id + "' data-sysowner='" + item.sysOwner + "'";
							html += "<tr class='center' " + cacheData + ">";
							html += "<td>" + item.version + "</td>";
							html += "<td>" + item.formName + "</td>";
							html += "<td>";
							html += "<button onclick='form_conf_obj.delVersionConf(this);' class='btn btn-def' style='width: 70px;'>删除</button>";
							html += "<button onclick='form_conf_obj.getConfDetail(this);' class='btn btn-def' style='width: 70px;'>详情</button>";
							html += "</td>";
							html += "</tr>";
						}
					}
					$("#category_list tbody").html(html);
				}
			}
		});
	},
	getConfDetail : function(el) {
		var tr = $(el).parent().parent();
		var id = tr.data("id");
		window.open(ctx + "/dynamicForm/form/preview.shtml?id=" + id);
	},
	delVersionConf : function(el) {
		var tr = $(el).parent().parent();
		var id = tr.data("id");
		var sysOwner = tr.data("sysowner");
		$.ajax({
			url : ctx + "/dynamicForm/form/delById.shtml",
			type : "post",
			data : encodeURI("id=" + id + "&sysOwner=" + sysOwner),
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					showTips("删除成功");
					tr.remove();
					form_conf_obj.getAllTreeData(true);
				} else {
					showTips("删除失败,该版本正在使用");
				}
			}
		});
	},
	/** 获取节点全路径 */
	getNodePath : function(nodes) {
		if (isEmptyObject(nodes) || nodes.length < 2)
			return "";
		var arr = new Array(nodes.length - 1);
		for (var i = 0; i < nodes.length - 1; i++) {
			arr[i] = nodes[i].id;
		}
		return arr.toString();
	},
	/** 将后台对象转为tree对象 */
	convertNode : function(item, toTree) {
		var treeFields = [ "id", "pId", "name", "tempId", "checked" ];
		var objFields = [ "itemCode", "pItemCode", "itemName", "id", "isEnable" ];

		var fromFields;
		var toFields;
		if (toTree) {
			fromFields = objFields;
			toFields = treeFields;
		} else {
			fromFields = treeFields;
			toFields = objFields;
		}
		var node = {};
		var mappedCnt = 0;
		point: for ( var key in item) {
			if (mappedCnt <= fromFields.length) {// 已经转换的总数小于等于需要转换的总数时
				for (var i = 0; i < fromFields.length; i++) {
					if (key == fromFields[i]) {
						node[toFields[i]] = item[key];
						mappedCnt++;
						continue point;
					}
				}
			}
			node.isNew = false;
			node[key] = item[key];
		}
		return node;
	},
	updateSelectedNode : function(forChild) {
		var treeObj = $.fn.zTree.getZTreeObj("confTree");
		var nodes = treeObj.getSelectedNodes();
		var displayStyle = $("#displayStyle").val();
		var displayCol = $("#displayCol").val();
		if (!isEmpty(displayCol) && (displayCol < 1 || displayCol > 12)) {
			showWarn("显示的列数无效");
			return;
		}
		for (var i = 0; i < nodes.length; i++) {
			var node = nodes[i];
			if (forChild) {
				if (!nodes[i].isLeaf) {
					for (var t = 0; t < node.children.length; t++) {
						node.children[t].displayStyle = displayStyle;
						node.children[t].displayCol = displayCol;
						treeObj.updateNode(node.children[t]);
					}
				}
			} else {
				node.displayStyle = displayStyle;
				node.displayCol = displayCol;
				treeObj.updateNode(node);
			}
		}
		showTips("更新成功", 500);
	}
};
