$(function() {
	record_obj.init();
});
var record_obj = {
	init : function() {
		record_obj.getList();
		record_obj.addEvents();
	},
	addEvents : function() {
		// 计算与身高相关指标
		$("#tableBody").on("change", "form [data-calcstature]", function() {
			var form = $(this.form);
			var statureMeasureWay = $("[name='statureMeasureWay']", form).val();
			var weight = $("[name='weight']", form).val();
			var measureValue = $("[name='statureMeasureValue']", form).val();
			if (isEmpty(measureValue) || isEmpty(statureMeasureWay))
				return;
			var data = "value=" + measureValue + "&measureWay=" + statureMeasureWay + "&patientId=" + $("#patientId").val() + "&weight=" + weight;
			record_obj.setCalcData(data, ctx + "/nutritionCalculate/getStature.shtml", form, $(this).attr("data-calcstature"));
		});
		// 计算上臂肌围数据
		$("#tableBody").on("change", "form [data-calcmamc]", function() {
			var form = $(this.form);
			var skinfoldPart = $("[name='skinfoldPart']", form).val();
			var skinfoldThickness = $("[name='skinfoldThickness']", form).val();
			var mac = $("[name='mac']", form).val();
			// 是皮褶部位肱二头肌时
			if (skinfoldPart != "1" || isEmpty(skinfoldThickness) || isEmpty(mac))
				return;
			var data = "tsf=" + skinfoldThickness + "&mac=" + mac;
			record_obj.setCalcData(data, ctx + "/nutritionCalculate/getMamc.shtml", form, $(this).attr("data-calcmamc"));
		});
		// 计算腰臀比数据
		$("#tableBody").on("change", "form [data-calcwhr]", function() {
			var form = $(this.form);
			var waist = $("[name='waist']", form).val();
			var hip = $("[name='hip']", form).val();
			if (isEmpty(waist) || isEmpty(hip))
				return;
			var data = "waist=" + waist + "&hip=" + hip;
			record_obj.setCalcData(data, ctx + "/nutritionCalculate/getWHR.shtml", form, $(this).attr("data-calcwhr"));
		});
		// 展开收起事件
		$("body").on("click", "[data-collapse]", function() {
			$(this).parent().next().toggle();
			$(this).parent().toggleClass("active");
			if ($(this).parent().hasClass("active")) {
				$(this).html("展开");
			} else {
				$(this).html("收起");
			}
		});
		// 需要计算分值
		$("#tableBody").on("click", "form [data-itemscore]", function() {
			var form = $(this.form);
			var totalScore = 0;
			$("[data-itemscore]:checked", form).each(function() {
				totalScore += parseInt($(this).data("itemscore"));
			});
			$("input[name='scores']", form).val(totalScore);
			$("[data-scoresspan]", form).text(totalScore);
		});
	},
	/** 获取并设置自动计算的结果字段的值 */
	setCalcData : function(data, url, form, calcNodesStr) {
		$.ajax({
			url : url,
			type : "post",
			data : encodeURI(data),
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					var calcNodes = calcNodesStr.split(",");
					for (var i = 0; i < calcNodes.length; i++) {
						$("[name='" + calcNodes[i] + "']", form).val(data[calcNodes[i]]);
						$("[data-" + calcNodes[i] + "span]", form).text(data[calcNodes[i]]);
					}
				}
			}
		});
	},
	getList : function() {
		var data = "sysOwner=" + $("#sysOwner").val() + "&scheduleId=" + $("#fkPatientScheduleId").val();
		if (!isEmpty($("#id").val())) {
			data += "&recordId=" + $("#id").val();
		}
		$.ajax({
			url : ctx + "/fuRecord/getList.shtml",
			type : "post",
			data : encodeURI(data),
			dataType : "json",
			loading : true,
			success : function(data) {
				var html = "";
				var needMappingFormData = false;
				if (data.status == 1 && !isEmptyObject(data.items)) {
					if (!isEmptyObject(data.items)) {
						needMappingFormData = true;
						for (var i = 0; i < data.items.length; i++) {
							var item = data.items[i];
							if (!isEmpty(item.recordType)) {
								html += '<div class="m-head bottomline1 m-t-30">';
								html += '<span class="pull-right fc-blue m-t-6 hand fs-14 p-r-5" data-collapse>收起</span>';
								html += '<p class="u-subtitle text-center fs-16">' + item.form.formTypeName + '</p>';
								html += '</div>';
								html += '<div>';
								html += '<form id="' + item.recordType + 'Id" data-form="' + item.recordType + '" onsubmit="return false;">';
								html += $("#" + item.recordType + "StaticHtml").html();
								html += '<div id="' + item.recordType + '">';
								if (!isEmptyObject(item.nodes)) {
									for (var t = 0; t < item.nodes.length; t++) {
										var node = item.nodes[t];
										html += '<div class="m-head bottomline m-t-8 p-l-5"><p class="u-subtitle text-left">' + node.itemName
														+ '</p></div>';
										if (!isEmptyObject(node.childNodes)) {
											var cols = node.displayCols;
											html += '<div class="col-xt-12 m-b-36">';
											for (var n = 0; n < node.childNodes.length; n++) {
												var childNode = node.childNodes[n];
												html += '<div class="col-xt-6 m-t-24">';
												html += '<div class="m-head"><p class="m-subnav">' + childNode.itemName + '</p></div>';
												html += '<div class="m-body">';
												html += record_obj.generateNodes(childNode.childNodes, childNode.needScore);
												html += '</div>';
												html += '</div>';
											}
											html += '</div>';
										}
									}
									if (item.recordType == "recordNa") {
										html += '<p class="u-subtitle fs-16 p-l-5">SGA 评分结果：';
										html += '<span class="f-span-4" data-scoresspan>8</span><span class="u-span-1 fs-16">分</span></p>';
										html += '<span class="u-span-1 p-l-5">改良法SGA 评估方法、分值。分值越高，代表营养越差。';
										html += '营养正常：7 分；轻－中度营养不良：8-15 分；重度营养不良：≥ 16 分。</span>';
									}
								}
								html += '</div>';
								html += '</form>';
								html += '</div>';
							}
						}
					}
				}
				$("#tableBody").html(html);
				if (needMappingFormData) {// 批量设定各表单的值
					$("form[data-form]").each(function() {
						var formType = $(this).attr("data-form");
						var jsonData;
						for (var i = 0; i < data.items.length; i++) {
							var item = data.items[i];
							if (formType == item.recordType) {
								if (isEmpty(item.record.id))// setting initial data
									item.record.fkFormId = item.form.fkFormId;
								jsonData = item.record;
								break;
							}
						}
						for ( var key in jsonData) {
							$("[data-" + key + "span]", this).text(convertEmpty(jsonData[key]));
						}
						mappingFormData(jsonData, $(this).attr("id"));
					});
				}
			}
		});
	},
	/** 保存随访记录单 */
	saveRecord : function() {
		var record = $("#recordBasicForm").serializeJson();
		$("form[data-form]").each(function() {
			var id = $(this).attr("data-form");
			var recordData = $(this).serializeJson();
			recordData.values = record_obj.getSaveNodes(id);
			record[id] = recordData;
		});
		$.ajax({
			url : ctx + "/fuRecord/saveRecord.shtml",
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify(record),
			type : "post",
			loading : true,
			dataType : "json",
			success : function(data) {
				if (data.status == 1) {
					showTips();
					goBack();
				}
			}
		});
	},
	/** 生成正文节点 */
	generateNodes : function(nodes, needScore) {
		var html = "";
		if (!isEmptyObject(nodes)) {
			if (nodes instanceof Array) {
				for (var i = 0; i < nodes.length; i++) {
					html += record_obj.getOneNodeHtml(nodes[i], needScore);
				}
			} else {
				html += record_obj.getOneNodeHtml(nodes, needScore);
			}
		}
		return html;
	},
	/** 生成单个节点的html */
	getOneNodeHtml : function(node, needScore) {
		var needAddChild = true;
		var html = "";
		var commonHtml = ' data-savenode data-pcode="' + node.pItemCode + '" data-code="' + node.itemCode;
		commonHtml += '" data-type="' + node.itemType + '" data-formid="' + node.fkFormId + '" ';
		if (needScore) {
			commonHtml += '" data-itemscore="' + node.score + '"';
		}
		switch (node.itemType) {
		case "label":
			html += '<label ' + commonHtml + '>' + node.itemName + '</label>';
			break;
		case "checkbox":
			html += '<label><input type="checkbox" name="' + node.pItemCode + '" value="' + node.itemCode + '" '
							+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '"/>' + node.itemName + '</label>';
			break;
		case "radio":
			html += '<div class="m-t-12">';
			html += '<span class="u-span-1 f-span-1">' + (needScore ? (node.score + "分") : "") + '</span>';
			html += '<label><input type="radio" name="' + node.pItemCode + '" value="' + node.itemCode + '" '
							+ (isEmpty(node.itemValue) ? "" : "checked") + commonHtml + '/>' + node.itemName + '</label>';
			html += '</div>';
			break;
		case "select":
			html += '<label>' + node.itemName + '</label><select name="' + node.itemCode + '" ' + commonHtml + '>'
							+ record_obj.generateNodes(node.childNodes) + '</select>';
			needAddChild = false;
			break;
		case "input":
			html += '<label>' + node.itemName + '</label><input type="' + node.dataType + '" name="' + node.itemCode + '" value="'
							+ convertEmpty(node.itemValue) + '" ' + commonHtml + '><span class="unit-style">' + convertEmpty(node.unitShow)
							+ '</span>';
			break;
		case "textarea":
			html += '<label>' + node.itemName + '</label><textarea name="' + node.itemCode + '" ' + commonHtml + '>' + convertEmpty(node.itemValue)
							+ '</textarea>';
			break;
		case "option":
			html += '<option value="' + node.itemCode + '" ' + (isEmpty(node.itemValue) ? "" : "selected") + commonHtml + '>' + node.itemName
							+ '</option>';
			break;
		default:
			break;
		}
		if (needAddChild)
			html += record_obj.generateNodes(node.childNodes);
		return html;
	},
	/** 保存form表单节点数据 */
	getSaveNodes : function(formId) {
		var items = [];
		var elements = $("#" + formId + " [data-savenode]");
		elements.filter("[data-type='checkbox']:checked,[data-type='radio']:checked,[data-type='option']:selected").each(function() {
			record_obj.addSaveItems(items, $(this).data("code"), $(this).data("code"), $(this).data("formid"));
			record_obj.addSiblingsLable(elements, items, $(this).data("pcode"));// 保存标签元素
		});
		elements.filter("[data-type='input'],[data-type='textarea']").each(function() {
			if (!isEmpty($(this).val())) {// 只保存有值的数据
				record_obj.addSaveItems(items, $(this).data("code"), $(this).val(), $(this).data("formid"));
				record_obj.addSiblingsLable(elements, items, $(this).data("pcode"));// 保存标签元素
			}
		});
		return items;
	},
	/** 添加标签元素 */
	addSiblingsLable : function(elements, items, code) {
		elements.filter("[data-type='label'][data-pcode='" + code + "']").each(function() {
			if (!record_obj.checkItemCodeExists(items, $(this).data("code")))
				record_obj.addSaveItems(items, $(this).data("code"), $(this).data("code"), $(this).data("formid"));
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
