$(document).ready(function() {
	template_obj.init();
});
var template_obj = {
	init : function() {
		template_obj.addEvents();
		template_obj.getList();
		template_obj.addValidate();
	},
	addEvents : function() {
		$("#templateTypeList [data-share]").on("click", function(event) {
			$(this).addClass("active").siblings().removeClass("active");
			template_obj.getList($(this).data("share"));
			stopEventBubble(event);
		});
		$("#addTemplate").on("click", function(event) {
			detail_obj.showDetail(null, false);
			stopEventBubble(event);
		});
		$("#detailsList").on("click", "[data-formtype]", function(event) {
			var checkedEl = $(this).parent().find("input[name$='isChecked']");
			if ($(this).hasClass("active")) {
				$(this).removeClass("active");
				checkedEl.val(false);
			} else {
				$(this).addClass("active");
				checkedEl.val(true);
			}
			stopEventBubble(event);
		});
		$("#detailsList").on("change", "[data-formtypeselect]", function(event) {
			var checkedEl = $(this).parent().find("input[name$='isChecked']");
			if (isEmpty($(this).val())) {
				$(this).removeClass("active");
				checkedEl.val(false);
			} else {
				$(this).addClass("active");
				checkedEl.val(true);
			}
			stopEventBubble(event);
		});
		$("#templateList").on("click", "[data-more]", function(event) {
			var menu = $(this).parent().find("[data-menulist]");
			menu.hasClass("hide") ? menu.removeClass("hide") : menu.addClass("hide");
			stopEventBubble(event);
		});
		$("body").on("click", function() {
			$("#templateList [data-menulist]:not('.hide')").addClass("hide");
		});
		$("#batchSetForm [daterangepicker]").daterangepicker({
			"singleDatePicker" : true,
			"showDropdowns" : true,
			"locale" : {
				format : "YYYY-MM-DD"
			}
		});
	},
	getList : function(type) {
		type = isEmpty(type) ? ($("#templateTypeList [data-share].active").data("share")) : type;
		$.ajax({
			url : ctx + "/fuScheduleTemplate/getList.shtml",
			type : "post",
			data : encodeURI((isEmpty(type) ? ("ownerId=" + $("#ownerId").val()) : ("isShared=" + type)) + "&sysOwner=" + $("#sysOwner").val()),
			dataType : "json",
			loading : true,
			success : function(data) {
				var html = "";
				if (data.status == 1) {
					for (var i = 0; i < data.items.length; i++) {
						html += template_obj.generateRecordDiv(data.items[i], type);
					}
				}
				$("#templateList").html(html);
			}
		});
	},
	generateRecordDiv : function(item, type) {
		var html = '<div class="col-xt-6 p-r-13 p-b-7" data-id="' + item.id + '">';
		html += '<div class="xt-bq height-55 rel">';
		if (isEmpty(type)) {// 显示我的模板时
			html += '<div class="layer_menu_list m-r-110 m-t--8 hide" data-menulist>';
			html += '<ul><li>';
			html += '<a onclick="template_obj.showBatchSet(this,\'' + item.id + '\');">批量设定</a>';
			html += '<a onclick="template_obj.shareRecord(this,\'' + item.id + '\');" data-flag="' + item.isShared + '">'
							+ (item.isShared ? "取消共享" : "共享模板") + '</a>';
			html += '<a onclick="template_obj.delRecord(this,\'' + item.id + '\');">删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</a>';
			html += '</li></ul>';
			html += '</div>';
			html += '<p class="xt-user-name margin-top-5">' + convertEmpty(item.templateName) + '</p>';
			html += '<label class="xt-check-label2 pull-right m-t-2" onclick="detail_obj.showDetail(\'' + item.id + '\',false);">编辑</label>';
			html += '<label class="xt-check-label2 pull-right m-t-2 xt-dot" data-more>…</label>';
		} else {// 显示共享模板时
			html += '<p class="xt-user-name margin-top-5">' + convertEmpty(item.templateName) + '</p>';
			html += '<label class="xt-check-label2 pull-right m-t-2" onclick="detail_obj.showDetail(\'' + item.id + '\',true);">详情</label>';
		}
		html += '</div>';
		html += '</div>';
		return html;
	},
	showBatchSet : function(el, id) {
		$(el).parent().parent().parent().addClass("hide");
		$("#batchSetForm input[name='id']").val(convertEmpty(id));
		$.ajax({
			url : ctx + "/fuPatientSchedule/getUnUseTemplatePatients.shtml",
			type : "post",
			data : encodeURI("sysOwner=" + $("#sysOwner").val()),
			dataType : "json",
			loading : true,
			success : function(data) {
				var html = "";
				if (data.status == 1 && !isEmptyObject(data.items)) {
					for (var i = 0; i < data.items.length; i++) {
						var item = data.items[i];
						html += '<div><label><input type="checkbox" value="' + item.id + '" name="idStr" />' + item.name + '</label><div>';
					}
				}
				$("#patientList").html(html);
				$("#batchSetDialog").modal("show");
			}
		});
	},
	batchSet : function(el) {
		if (!$("#batchSetForm").valid())
			return false;
		$.ajax({
			url : ctx + "/fuScheduleTemplate/batchSet.shtml",
			type : "post",
			data : $("#batchSetForm").serialize(),
			dataType : "json",
			loading : true,
			loadingMsg : "保存中，请稍后",
			success : function(data) {
				if (data.status == 1) {
					$("#batchSetDialog").modal("hide");
					showTips();
				} else {
					showWarn("请选择应用患者");
				}
			}
		});
	},
	shareRecord : function(el, id) {
		var flag = !$(el).data("flag");
		$.ajax({
			url : ctx + "/fuScheduleTemplate/updateShare.shtml",
			type : "post",
			data : "id=" + id + "&shareFlag=" + flag,
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					$(el).parent().parent().parent().addClass("hide");
					showTips(flag ? "共享成功" : "取消共享成功", 1000);
					$(el).data("flag", flag);
					$(el).text(flag ? "取消共享" : "共享模板");
				}
			}
		});
	},
	delRecord : function(el, id) {
		$.ajax({
			url : ctx + "/fuScheduleTemplate/delById.shtml",
			type : "post",
			data : "id=" + id,
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					template_obj.getList();
					showTips("删除成功", 1000);
				}
			}
		});
	},
	saveRecord : function() {
		if (!$("#templateForm").valid())
			return false;
		$.ajax({
			url : ctx + "/fuScheduleTemplate/save.shtml",
			type : "post",
			data : $("#templateForm").serialize(),
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					template_obj.getList();
					showTips("保存成功");
					$("#detailDialog").modal("hide");
				} else if (data.status == 2) {
					showWarn("模板计划不能为空");
				}
			}
		});
	},
	addValidate : function() {
		$('#templateForm').validate({
			rules : {
				templateName : {
					required : [ "模板名称" ]
				}
			}
		});
	}
};
var detail_obj = {
	followTypes : null,
	typeForms : null,
	followFormTypes : null,
	showDetail : function(id, isPreview) {
		detail_obj.resetDialog(id, isPreview);
		$("#detailDialogTitle").text(isPreview ? "模板详情" : (isEmpty(id) ? "新增随访模板" : "修改随访模板"));
		$.ajax({
			url : ctx + "/fuScheduleTemplate/getDetail.shtml",
			type : "post",
			data : encodeURI("id=" + convertEmpty(id) + "&sysOwner=" + $("#sysOwner").val()),
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					detail_obj.followTypes = data.followTypes;
					detail_obj.typeForms = data.typeForms;
					detail_obj.followFormTypes = data.followFormTypes;
					if (!isEmpty(data.item)) {
						$("#templateForm input[name='templateName']").val(data.item.templateName);
						$("#templateForm input[name='isShared']").val(data.item.isShared);
						for (var i = 0; i < data.details.length; i++) {
							var item = data.details[i];
							item.index = i;
							$("#detailsList").append(detail_obj.getElementHtml(item));
						}
					}
					$("#detailDialog").modal("show");
				}
			}
		});
	},
	resetDialog : function(id, isPreview) {
		$("#detailsList").html("");
		$("#templateForm input[name='templateName']").val("");
		$("#templateForm input[name='id']").val(convertEmpty(id));
		$("#templateForm input[name='isShared']").val(false);
		isPreview ? $("#addElementDiv").addClass("hide") : $("#addElementDiv").removeClass("hide");
		isPreview ? $("#saveTemplateButton").addClass("hide") : $("#saveTemplateButton").removeClass("hide");
	},
	delElement : function(el) {
		$(el).parent().parent().remove();
	},
	addElement : function(el) {
		var index = 0;
		if ($("#detailsList [data-element]").length > 0) {
			var el = $("#detailsList [data-element]:last");
			index = parseInt(el.attr("data-element")) + 1;
		}
		var item = {
			index : index,
			sysOwner : $("#sysOwner").val(),
			// 默认显示第一种类别
			followType : detail_obj.followTypes[0].itemCode
		};
		$("#detailsList").append(detail_obj.getElementHtml(item));
	},
	getElementHtml : function(item) {
		var html = '<div class="fill-parent clearfix" data-element="' + item.index + '">';
		html += '<input type="hidden" name="details[' + item.index + '].intervalType" value="d"/>';
		html += '<input type="hidden" name="details[' + item.index + '].sysOwner" value="' + item.sysOwner + '"/>';
		html += '<div class="sf-time"><span class="xt-title">' + (item.index == 0 ? "首次随访" : "距离上一次随访") + '</span>';
		if (item.index == 0) {
			html += '<input type="hidden" name="details[' + item.index
							+ '].taskInterval" value="0" class="tl-input hide" style="width: 50px;margin: 0px 5px 0px 8px;" /></div>';
		} else {
			html += '<input type="text" name="details[' + item.index + '].taskInterval" value="' + convertEmpty(item.taskInterval) + '" ';
			html += 'class="tl-input" style="width: 50px;margin: 0px 5px 0px 8px;" data-rule-isPInt="true" data-msg-isPInt="天数的值无效" maxlength="6"/>';
			html += '<span class="xt-title">天</span></div>';
		}
		html += '<div class="group-time-line" style="width: 500px; margin-bottom: 0;"><div class="time-img-line"></div>';
		html += '<div class="sf-delete" onclick="detail_obj.delElement(this);"></div>';
		html += '<select class="xt-select margin-top-12" name="details[' + item.index + '].followType">';
		for (var i = 0; i < detail_obj.followTypes.length; i++) {
			var type = detail_obj.followTypes[i];
			html += '<option value="' + type.itemCode + '" ' + (type.itemCode == item.followType ? "selected" : "") + '>' + type.itemName
							+ '</option>';
		}
		html += '</select>';
		for (var i = 0; i < detail_obj.followFormTypes.length; i++) {
			var formType = detail_obj.followFormTypes[i];
			var formList = detail_obj.typeForms[formType.itemCode];
			var formId;
			var isChecked = false;// 是否选中标识
			if (!isEmptyObject(item.forms)) {
				for (var n = 0; n < item.forms.length; n++) {
					if (item.forms[n].formType == formType.itemCode) {
						isChecked = true;
						formId = item.forms[n].fkFormId;
						break;
					}
				}
			}
			html += '<input type="hidden" name="details[' + item.index + '].forms[' + i + '].orderBy" value="' + formType.orderBy + '"/>';
			html += '<input type="hidden" name="details[' + item.index + '].forms[' + i + '].formType" value="' + formType.itemCode + '"/>';
			html += '<div class="item-box" style="min-width:0px !important;">';
			html += '<input type="hidden" name="details[' + item.index + '].forms[' + i + '].isChecked" value="' + isChecked + '"/>';
			if (!isEmptyObject(formList)) {
				html += '<select class="xt-select pull-right margin-top-12 m-r-6 ' + (isChecked ? "active" : "") + '" name="details[' + item.index
								+ '].forms[' + i + '].fkFormId" data-formtypeselect>';
				for (var t = 0; t < formList.length; t++) {
					var form = formList[t];
					html += '<option value="">' + formType.itemName + '</option>';
					html += '<option value="' + form.id + '" ' + (form.id == formId ? "selected" : "") + '>' + form.formName + '</option>';
				}
				html += '</select>';
			} else {
				html += '<label class="xt-check-label2 pull-right margin-top-12 ' + (isChecked ? "active" : "") + '" data-formtype>'
								+ formType.itemName + '</label>';
			}
			html += '</div>';
		}
		html += '</div>';
		html += '</div>';
		return html;
	}
};