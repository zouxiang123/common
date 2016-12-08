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
		var col = node.displayCol;
		switch (node.itemType) {
		case "label":
			if (node.displayStyle == "label_01") {
				html += '<div class="col-xt-' + (isEmpty(col) ? 12 : col)
								+ ' bottomline1 m-t-30"><span class="pull-right fc-blue m-t-6 hand fs-14 p-r-5" data-collapse="">收起</span>';
				html += '<p class="u-subtitle text-center fs-16"></p></div>';
				html += '<div>' + dfr_obj.generateNodes(node.childNodes, pcode) + '</div>';
			} else if (node.displayStyle == "label_02") {
				html += '<div class="col-xt-' + (isEmpty(col) ? 12 : col) + ' bottomline m-t-8 p-l-5"><p class="u-subtitle text-left">'
								+ node.itemName + '</p></div>';
				html += '<div class="col-xt-' + (isEmpty(col) ? 12 : col) + ' m-t-12">' + dfr_obj.generateNodes(node.childNodes, pcode) + '</div>';
			} else if (node.displayStyle == "label_03") {
				html += '<div class="min-h-38 col-xt-' + (isEmpty(col) ? 6 : col) + '">';
				html += '<div class="m-head"><p class="m-subnav">' + node.itemName + '</p></div>';
				html += '<div class="m-body m-b-24">';
				html += dfr_obj.generateNodes(node.childNodes, pcode);
				html += '</div>';
				html += '</div>';
			} else if (node.displayStyle == "label_04") {
				html += '<div class="min-h-38 col-xt-' + (isEmpty(col) ? 6 : col) + '">';
				html += '<div class="inline-block"><span class="u-span f-span">' + node.itemName + '</span></div>';
				html += '<div class="inline-block">';
				html += dfr_obj.generateNodes(node.childNodes, pcode);
				html += '</div>';
				html += '</div>';
			} else if (node.displayStyle == "label_10") {
				html += '<div  class="min-h-38 col-xt-' + (isEmpty(col) ? 12 : col) + '"><p class="u-subtitle fs-16 p-l-5">' + node.itemName;
				html += '<span class="f-span-4" data-' + node.groupTag + 'span ' + commonHtml + '>' + convertEmpty(node.itemValue)
								+ '</span><span class="u-span-1 fs-16">分</span>';
				html += '</p><div>';
			} else if (node.displayStyle == "label_11") {
				html += '<span class="u-span-1 p-l-5" ' + commonHtml + '>' + node.itemName + '</span>';
			} else if (node.isLeaf) {
				html += '<div class="min-h-38 inline-block ' + (isEmpty(col) ? "" : ("col-xt-" + col)) + '">';
				html += '<span class="u-span f-span">' + node.itemName + '</span>';
				if (!isEmpty(node.itemValue))
					html += '<span class="u-span f-span-3" ' + commonHtml + '>' + node.itemValue + '</span>';
				if (!isEmpty(node.unitShow))
					html += '<span class="u-span-1 f-span-2">' + node.unitShow + '</span>';
				html += '</div>';
			} else {
				html += '<div class="m-head inline-block"><span class="u-span f-span">' + node.itemName + '</span></div>';
				html += '<div class="m-body inline-block">';
				html += dfr_obj.generateNodes(node.childNodes, pcode);
				html += '</div>';
			}
			needAddChild = false;
			break;
		case "checkbox":
			if (node.displayStyle == "checkbox_01") {
				html += '<div class="m-l-10 m-t-12 ' + (isEmpty(col) ? "" : ('col-xt-' + col + '')) + '">';
				if (node.needScore)
					html += '<span class="u-span f-span">' + (node.score + "分") + '</span>';
				html += '<label><input type="checkbox" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '"/>' + node.itemName + '</label>';
				html += '</div>';
			} else {
				html += '<div class="m-l-10 m-t-12 inline-block min-w-0 ' + (isEmpty(col) ? "" : ("col-xt-" + col)) + '">';
				if (node.needScore)
					html += '<span class="u-span-1 f-span-1">' + (node.score + "分") + '</span>';
				html += '<label><input type="checkbox" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '"/>' + node.itemName + '</label>';
				html += '</div>';
			}
			break;
		case "radio":
			if (node.displayStyle == "radio_01") {
				html += '<div class="m-l-10 m-t-12 ' + (isEmpty(col) ? "" : ('col-xt-' + col + '')) + '">';
				if (node.needScore)
					html += '<span class="u-span f-span">' + (node.score + "分") + '</span>';
				html += '<label><input type="radio" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '/>' + node.itemName + '</label>';
				html += '</div>';
			} else {
				html += '<div class="m-l-10 m-t-12 inline-block min-w-0 ' + (isEmpty(col) ? "" : ("col-xt-" + col)) + '">';
				if (node.needScore)
					html += '<span class="u-span f-span">' + (node.score + "分") + '</span>';
				html += '<label><input type="radio" name="' + pItemCode + '" value="' + node.itemCode + '" '
								+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '/>' + node.itemName + '</label>';
				html += '</div>';
			}
			break;
		case "select":
			html += '<div class="m-l-10 col-xt-' + (isEmpty(col) ? 4 : col) + ' dropdown m-t-12">';
			html += '<span class="u-span f-span">' + node.itemName + '</span>';
			html += '<select name="' + node.itemCode + '" ' + commonHtml + '>' + dfr_obj.generateNodes(node.childNodes, pcode) + '</select>';
			html += '</div>';
			needAddChild = false;
			break;
		case "input":
			var width = convertEmpty(node.displayWidth);
			if (!isEmpty(width)) {
				width = 'style="width:' + width + '"';
			}
			if (node.displayStyle == "input_01") {
				html += '<div class="m-l-10 col-xt-' + (isEmpty(col) ? 4 : col) + '">';
				if (!isEmpty(node.itemName.trim()))
					html += '<span class="u-span f-span m-l-14 min-w-0">' + node.itemName + '</span>';
				html += '<input class="u-input1" ' + width + ' type="' + node.dataType + '" name="' + node.itemCode + '" value="'
								+ convertEmpty(node.itemValue) + '" ' + commonHtml + '>';
				if (!isEmpty(node.unit))
					html += '<span class="u-span-1 f-span-2">' + convertEmpty(node.unitShow) + '</span>';
				html += '</div>';
			} else {
				html += '<div class="inline-block min-w-0 ' + (isEmpty(col) ? "" : ("col-xt-" + col)) + '">';
				if (!isEmpty(node.itemName.trim()))
					html += '<span class="u-span f-span min-w-0">' + node.itemName + '</span>';
				html += '<input class="u-input1" ' + width + ' type="' + node.dataType + '" name="' + node.itemCode + '" value="'
								+ convertEmpty(node.itemValue) + '" ' + commonHtml + '>';
				if (!isEmpty(node.unit))
					html += '<span class="u-span-1 f-span-2">' + convertEmpty(node.unitShow) + '</span>';
				html += '</div>';
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
			if (node.displayStyle == "div_01") {
				html += '<div class="inline-block ' + (isEmpty(col) ? "" : ("col-xt-" + col)) + '">' + dfr_obj.generateNodes(node.childNodes, pcode)
								+ '</div>';
			} else {
				html += '<div class="min-h-38 ' + (isEmpty(col) ? 'col-xt-12' : ('col-xt-' + col + '')) + '">'
								+ dfr_obj.generateNodes(node.childNodes, pcode) + '</div>';
			}
			needAddChild = false;
			break;
		default:
			break;
		}
		if (needAddChild && !isEmptyObject(node.childNodes))
			html += dfr_obj.generateNodes(node.childNodes, pcode);
		return html;
	},
	/** 生成正文节点 */
	generatePreviewNodes : function(nodes) {
		var html = "";
		if (!isEmptyObject(nodes)) {
			if (nodes instanceof Array) {
				for (var i = 0; i < nodes.length; i++) {
					html += dfr_obj.getPreviewOneNodeHtml(nodes[i]);
				}
			} else {
				html += dfr_obj.getPreviewOneNodeHtml(nodes);
			}
		}
		return html;
	},
	/** 生成单个节点的html */
	getPreviewOneNodeHtml : function(node) {
		var needAddChild = true;
		var html = "";
		var col = node.displayCol;
		switch (node.itemType) {
		case "label":
			if (node.displayStyle == "label_01") {
				html += '<div class="col-xt-' + (isEmpty(col) ? 12 : col)
								+ ' bottomline1 m-t-30"><span class="pull-right fc-blue m-t-6 hand fs-14 p-r-5" data-collapse="">收起</span>';
				html += '<p class="u-subtitle text-center fs-16"></p></div>';
				html += '<div>' + dfr_obj.generatePreviewNodes(node.childNodes) + '</div>';
			} else if (node.displayStyle == "label_02") {
				html += '<div class="col-xt-' + (isEmpty(col) ? 12 : col) + ' bottomline m-t-8 p-l-5"><p class="u-subtitle text-left">'
								+ node.itemName + '</p></div>';
				html += '<div class="col-xt-' + (isEmpty(col) ? 12 : col) + ' m-t-12">' + dfr_obj.generatePreviewNodes(node.childNodes) + '</div>';
			} else if (node.displayStyle == "label_03") {
				html += '<div class="col-xt-' + (isEmpty(col) ? 6 : col) + '">';
				html += '<div class="m-head"><p class="m-subnav">' + node.itemName + '</p></div>';
				html += '<div class="m-body m-b-24">';
				html += dfr_obj.generatePreviewNodes(node.childNodes);
				html += '</div>';
				html += '</div>';
			} else if (node.displayStyle == "label_04") {
				html += '<div class="col-xt-' + (isEmpty(col) ? 6 : col) + '">';
				html += '<div class="m-head inline-block"><p class="m-subnav">' + node.itemName + '</p></div>';
				html += '<div class="m-body inline-block">';
				html += dfr_obj.generatePreviewNodes(node.childNodes);
				html += '</div>';
				html += '</div>';
			} else if (node.displayStyle == "label_10") {
				html += '<div  class="col-xt-' + (isEmpty(col) ? 12 : col) + '"><p class="u-subtitle fs-16 p-l-5">' + node.itemName;
				html += '<span class="f-span-4" data-' + node.groupTag + 'span>' + convertEmpty(node.itemValue)
								+ '</span><span class="u-span-1 fs-16">分</span>';
				html += '</p><div>';
			} else if (node.displayStyle == "label_11") {
				html += '<span class="u-span-1 p-l-5">' + node.itemName + '</span>';
			} else if (node.isLeaf) {
				html += '<div class="col-xt-' + (isEmpty(col) ? 4 : col) + ' m-t-12 ">';
				html += '<span class="u-span f-span-5">' + node.itemName + '</span>';
				html += '<span class="u-span f-span-3">' + convertEmpty(node.itemValue) + '</span>';
				html += '<span class="u-span-1 f-span-2">' + convertEmpty(node.unitShow) + '</span>';
				html += '</div>';
			} else {
				html += '<div class="m-head inline-block"><p class="m-subnav">' + node.itemName + '</p></div>';
				html += '<div class="m-body inline-block">';
				html += dfr_obj.generatePreviewNodes(node.childNodes);
				html += '</div>';
			}
			needAddChild = false;
			break;
		case "checkbox":
			if (node.displayStyle == "checkbox_01") {
				html += '<div class="col-xt-4">';
				html += '<span class="u-span f-span-5">' + node.itemName + '</span>';
				if (node.needScore)
					html += '<span class="u-span-1 f-span-1">' + node.score + '分</span>';
				html += '</div>';
			} else {
				html += '<span class="u-span f-span-3">' + node.itemName + '</span>';
				if (node.needScore)
					html += '<span class="u-span-1 f-span-1">' + node.score + '分</span>';
			}
			break;
		case "radio":
			if (node.displayStyle == "radio_01") {
				html += '<div class="col-xt-4">';
				html += '<span class="u-span f-span-5">' + node.itemName + '</span>';
				if (node.needScore)
					html += '<span class="u-span-1 f-span-1">' + node.score + '分</span>';
				html += '</div>';
			} else {
				html += '<span class="u-span f-span-3">' + node.itemName + '</span>';
				if (node.needScore)
					html += '<span class="u-span-1 f-span-1">' + node.score + '分</span>';
			}
			break;
		case "select":
			html += '<span class="u-span f-span-3">' + node.itemName + '</span>';
			html += dfr_obj.generatePreviewNodes(node.childNodes);
			if (node.needScore)
				html += '<span class="u-span-1 f-span-1">' + node.score + '分</span>';
			needAddChild = false;
			break;
		case "input":
			if (node.displayStyle == "input_01") {
				html += '<div class="col-xt-4">';
				html += '<span class="u-span f-span-5">' + node.itemName + '</span>';
				html += '<span class="u-span f-span-3">' + convertEmpty(node.itemValue) + '</span>';
				if (!isEmpty(node.unit))
					html += '<span class="u-span-1 f-span-2">' + convertEmpty(node.unitShow) + '</span>';
				if (node.needScore)
					html += '<span class="u-span-1 f-span-1">' + node.score + '分</span>';
				html += '</div>';
			} else {
				if (!isEmpty(node.itemName.trim()))
					html += '<span class="u-span f-span-5">' + node.itemName + '</span>';
				html += '<span class="u-span f-span-3">' + convertEmpty(node.itemValue) + '</span>';
				if (!isEmpty(node.unit))
					html += '<span class="u-span-1 f-span-2">' + convertEmpty(node.unitShow) + '</span>';
			}
			break;
		case "textarea":
			html += '<span class="u-span f-span-5">' + node.itemName + '</span>';
			html += '<span class="u-span f-span-3">' + convertEmpty(node.itemValue) + '</span>';
			if (!isEmpty(node.unit))
				html += '<span class="u-span-1 f-span-2">' + convertEmpty(node.unitShow) + '</span>';
			if (node.needScore)
				html += '<span class="u-span-1 f-span-1">' + node.score + '分</span>';
			break;
		case "option":
			html += '<span class="u-span f-span-3">' + node.itemName + '</span>';
			break;
		case "div":
			html += '<div>' + dfr_obj.generatePreviewNodes(node.childNodes) + '</div>';
			needAddChild = false;
			break;
		default:
			break;
		}
		if (needAddChild && !isEmptyObject(node.childNodes))
			html += dfr_obj.generatePreviewNodes(node.childNodes);
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
			dfr_obj.addSaveItems(items, $(this).data("code"), $(this).text(), $(this).data("formid"));
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