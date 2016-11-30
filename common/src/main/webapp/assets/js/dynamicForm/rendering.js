var dfr_obj = {
	/** 生成正文节点 */
	generateNodes : function(nodes, pcode) {
		var html = "";
		if (!isEmptyObject(nodes)) {
			if (nodes instanceof Array) {
				for (var i = 0; i < nodes.length; i++) {
					if (isEmpty(pcode) && nodes[i].childValueNode == 1) {
						html += dfr_obj.getOneNodeHtml(nodes[i], nodes[i].itemCode);
					} else {
						html += dfr_obj.getOneNodeHtml(nodes[i], pcode);
					}
				}
			} else {
				if (isEmpty(pcode) && nodes.childValueNode == 1) {
					pcode = nodes.itemCode;
				}
				html += dfr_obj.getOneNodeHtml(nodes, pcode);
			}
		}
		return html;
	},
	/** 生成单个节点的html */
	getOneNodeHtml : function(node, pcode) {
		var pItemCode = isEmpty(pcode) ? node.pItemCode : pcode;
		var needAddChild = true;
		var html = "";
		var commonHtml = ' data-savenode data-pcode="' + pItemCode + '" data-code="' + node.itemCode;
		commonHtml += '" data-type="' + node.itemType + '" data-formid="' + node.fkFormId + '" ';
		if (node.needScore) {
			commonHtml += '" data-itemscore="' + node.score + '"';
		}
		switch (node.itemType) {
		case "label":
			if (node.displayStyle == "label_01") {
				html += '<div class="col-xt-12 bottomline1 m-t-30"><span class="pull-right fc-blue m-t-6 hand fs-14 p-r-5" data-collapse="">收起</span>';
				html += '<p class="u-subtitle text-center fs-16"></p></div>';
				html += '<div>' + dfr_obj.generateNodes(node.childNodes, pcode) + '</div>';
			} else if (node.displayStyle == "label_02") {
				html += '<div class="col-xt-12 bottomline m-t-8 p-l-5"><p class="u-subtitle text-left">' + node.itemName + '</p></div>';
				html += '<div class="col-xt-12 m-t-12">' + dfr_obj.generateNodes(node.childNodes, pcode) + '</div>';
			} else if (node.displayStyle == "label_03") {
				html += '<div class="col-xt-6">';
				html += '<div class="m-head"><p class="m-subnav">' + node.itemName + '</p></div>';
				html += '<div class="m-body m-b-24">';
				html += dfr_obj.generateNodes(node.childNodes, pcode);
				html += '</div>';
				html += '</div>';
			} else if (node.displayStyle == "label_10") {
				html += '<div  class="col-xt-12"><p class="u-subtitle fs-16 p-l-5">' + node.itemName;
				html += '<span class="f-span-4" data-' + node.groupTag + 'span ' + commonHtml + '></span><span class="u-span-1 fs-16">分</span>';
				html += '</p><div>';
			} else if (node.displayStyle == "label_11") {
				html += '<span class="u-span-1 p-l-5">' + node.itemName + '</span>';
			} else if (node.isLeaf) {
				html += '<div class="col-xt-4">';
				html += '<span class="u-span f-span-5">' + node.itemName + '</span>';
				html += '<span class="u-span f-span-3" ' + commonHtml + '>' + convertEmpty(node.itemValue) + '</span>';
				html += '<span class="u-span-1 f-span-2">' + convertEmpty(node.unitShow) + '</span>';
				html += '</div>';
			}
			needAddChild = false;
			break;
		case "checkbox":
			if (node.displayStyle == "checkbox_01") {
				html += '<div class="m-t-12">';
				html += '<span class="u-span-1 f-span-1">' + (node.needScore ? (node.score + "分") : "") + '</span>';
				html += '<label><input type="checkbox" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '"/>' + node.itemName + '</label>';
				html += '</div>';
			} else {
				html += '<span class="u-span-1 f-span-1">' + (node.needScore ? (node.score + "分") : "") + '</span>';
				html += '<label><input type="checkbox" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '"/>' + node.itemName + '</label>';
			}
			break;
		case "radio":
			if (node.displayStyle == "radio_01") {
				html += '<div class="m-t-12">';
				html += '<span class="u-span-1 f-span-1">' + (node.needScore ? (node.score + "分") : "") + '</span>';
				html += '<label><input type="radio" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '/>' + node.itemName + '</label>';
				html += '</div>';
			} else {
				html += '<span class="u-span-1 f-span-1">' + (node.needScore ? (node.score + "分") : "") + '</span>';
				html += '<label><input type="radio" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '/>' + node.itemName + '</label>';
			}
			break;
		case "select":
			html += '<div class="col-xt-4 m-t-12 dropdown">';
			html += '<span class="u-span f-span min-w-70">' + node.itemName + '</span>';
			html += '<select name="' + node.itemCode + '" ' + commonHtml + '>' + dfr_obj.generateNodes(node.childNodes, pcode) + '</select>';
			html += '</div>';
			needAddChild = false;
			break;
		case "input":
			if (node.displayStyle == "input_01") {
				html += '<div class="col-xt-4 m-t-12">';
				html += '<span class="u-span f-span min-w-70">' + node.itemName + '</span><input class="u-input m-l-14" type="' + node.dataType
								+ '" name="' + node.itemCode + '" value="' + convertEmpty(node.itemValue) + '" ' + commonHtml + '>';
				html += '<span class="u-span-1 f-span-2 min-w-30">' + convertEmpty(node.unitShow) + '</span>';
				html += '</div>';
			} else {
				if (!isEmpty(node.itemName.trim()))
					html += '<span class="u-span f-span min-w-70">' + node.itemName + '</span>';
				html += '<input class="u-input m-l-14" type="' + node.dataType + '" name="' + node.itemCode + '" value="'
								+ convertEmpty(node.itemValue) + '" ' + commonHtml + '>';
				if (!isEmpty(node.unit))
					html += '<span class="u-span-1 f-span-2 min-w-30">' + convertEmpty(node.unitShow) + '</span>';
			}
			break;
		case "textarea":
			html += '<label>' + node.itemName + '</label><textarea name="' + node.itemCode + '" ' + commonHtml + '>' + convertEmpty(node.itemValue)
							+ '</textarea>';
			break;
		case "option":
			html += '<option value="' + node.itemCode + '" ' + (isEmpty(node.itemValue) ? "" : "selected") + commonHtml + '>' + node.itemName
							+ '</option>';
			break;
		case "div":
			html += '<div>' + dfr_obj.generateNodes(node.childNodes, pcode) + '</div>';
			needAddChild = false;
			break;
		default:
			break;
		}
		if (needAddChild && !isEmptyObject(node.childNodes))
			html += dfr_obj.generateNodes(node.childNodes, pcode);
		return html;
	},
	/** 保存form表单节点数据 */
	getSaveNodes : function(id) {
		var items = [];
		var elements = $("#" + id + " [data-savenode]");
		elements.filter("[data-type='checkbox']:checked,[data-type='radio']:checked,[data-type='option']:selected").each(function() {
			dfr_obj.addSaveItems(items, $(this).data("code"), $(this).data("code"), $(this).data("formid"));
			dfr_obj.addSiblingsLable(elements, items, $(this).data("pcode"));// 保存标签元素
		});
		elements.filter("[data-type='input'],[data-type='textarea']").each(function() {
			if (!isEmpty($(this).val())) {// 只保存有值的数据
				dfr_obj.addSaveItems(items, $(this).data("code"), $(this).val(), $(this).data("formid"));
				dfr_obj.addSiblingsLable(elements, items, $(this).data("pcode"));// 保存标签元素
			}
		});
		elements.filter("[data-type='label']").each(function() {
			dfr_obj.addSaveItems(items, $(this).data("code"), $(this).find("[data-savevalue]").text(), $(this).data("formid"));
		});
		return items;
	},
	/** 添加标签元素 */
	addSiblingsLable : function(elements, items, code) {
		elements.filter("[data-type='label'][data-pcode='" + code + "']").each(function() {
			if (!dfr_obj.checkItemCodeExists(items, $(this).data("code")))
				dfr_obj.addSaveItems(items, $(this).data("code"), $(this).data("code"), $(this).data("formid"));
		});
	},
	/** 判断编号是否已存在 */
	checkItemCodeExists : function(items, code) {
		for (var i = 0; i < items.length; i++) {
			if (items[i].itemCode == code)
				return true;
		}
		return false;
	},
	/** 添加保存节点数据 */
	addSaveItems : function(items, code, value, formId) {
		items.push({
			itemCode : code,
			itemValue : value,
			fkFormId : formId
		});
	}
};