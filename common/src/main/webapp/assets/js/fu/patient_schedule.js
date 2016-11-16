$(function() {
	schedule_obj.init();
});
var schedule_obj = {
	followTypes : null,// 随访类别
	typeForms : null,// 表单类型
	followFormTypes : null,// 随访表单类别
	init : function() {
		schedule_obj.getList();
		schedule_obj.addEvents();
	},
	addEvents : function() {
		$("#savePlan").on("click", function(event) {
			schedule_obj.savePlan();
			stopEventBubble(event);
		});
		$("#delPlan").on("click", function(event) {
			showWarn("您确定要删除计划么？", function() {
				schedule_obj.delPlan();
			});
			stopEventBubble(event);
		});
		$("#planList").on("click", "[data-formtype]", function(event) {
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
		$("#planList").on("change", "[data-formtypeselect]", function(event) {
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
	},
	addDatePick : function() {
		$("#planList [daterangepicker]").each(function() {
			var index = $(this).attr("daterangepicker");
			var startDate = $(this).parent().find("input[name$='followDateShow']").val();
			var predate = $(this).attr("predate");
			$(this).daterangepicker({
				"startDate" : startDate,
				"minDate" : isEmpty(predate) ? null : moment(predate, "YYYY/MM/DD").add(1, 'days'),
				"singleDatePicker" : true,
				"showDropdowns" : true,
				"autoUpdateInput" : false,
				"autoUpdateInitInput" : false,
				"locale" : {
					format : "YYYY/MM/DD"
				}
			}, function(start, end, label) {
				var startStr = start.format("YYYY/MM/DD");
				if (index == "0") {// 如果是更新首次随访日期，更新后续时间
					$("#planList [data-element]").each(function() {
						var interval = $(this).find("input[name$='taskInterval']").val();
						$(this).find("input[name$='followDateShow']").val(startStr);
						if ($(this).attr("data-element") != "0") {// 如果不是第一个，更新时间
							$(this).find("[predate]").attr("predate", startStr);
							var day = moment(startStr, "YYYY/MM/DD");
							day.add(interval, 'days');
							$(this).find("[followdateSpan]").text(day.format("YYYY/MM/DD"));
							$(this).find("[daterangepicker]").data("daterangepicker").minDate = moment(startStr, "YYYY/MM/DD").add(1, 'days');
						}
					});
				} else {
					var ele = $("#planList [data-element='" + index + "']");
					var pre = ele.find("[predate]").attr("predate");
					ele.find("input[name$='followDateShow']").val(startStr);
					ele.find("[followdateSpan]").text(startStr);
					// 重新计算日期间的时间差
					var s = moment(pre, "YYYY/MM/DD");
					var e = moment(startStr, "YYYY/MM/DD");
					ele.find("input[name$='taskInterval']").val(e.diff(s, 'days'));
				}
			});
		});
	},
	savePlan : function() {
		if ($("#planForm").valid()) {
			$.ajax({
				url : ctx + "/fuPatientSchedule/saveSchedule.shtml",
				type : "post",
				data : $("#planForm").serialize(),
				dataType : "json",
				loading : true,
				success : function(data) {
					if (data.status == 1) {
						showTips("保存成功");
						goBack();
					} else if (data.status == 2) {
						showTips("患者计划不能为空");
					} else {
						showTips("删除失败，请联系管理员");
					}
				}
			});
		}
	},
	delPlan : function() {
		$.ajax({
			url : ctx + "/fuPatientSchedule/delSchedule.shtml",
			type : "post",
			data : encodeURI("id=" + $("#patientId").val() + "&sysOwner=" + $("#sysOwner").val()),
			dataType : "json",
			loading : true,
			success : function(data) {
				if (data.status == 1) {
					goBack();
					showTips("删除成功");
				} else if (data.status == 2) {
					showTips("患者id不能为空");
				} else {
					showTips("删除失败，请联系管理员");
				}
			}
		});
	},
	getList : function() {
		$.ajax({
			url : ctx + "/fuPatientSchedule/getSchedule.shtml",
			type : "post",
			data : encodeURI("id=" + $("#patientId").val() + "&sysOwner=" + $("#sysOwner").val()),
			dataType : "json",
			loading : true,
			success : function(data) {
				var html = "";
				if (data.status == 1) {
					schedule_obj.followTypes = data.followTypes;
					schedule_obj.typeForms = data.typeForms;
					schedule_obj.followFormTypes = data.followFormTypes;
					var predate = "";
					for (var i = 0; i < data.items.length; i++) {
						var item = data.items[i];
						if (!isEmpty(item.recordId)) {
							predate = new Date(item.recordDate).pattern("yyyy/MM/dd");
						} else if (isEmpty(predate)) {
							predate = item.followDateShow;
						}
						item.index = i;
						item.preDate = (i == 0 ? "" : predate);
						html += schedule_obj.getElementHtml(item);
					}
				}
				$("#planList").html(html);
				schedule_obj.addDatePick();
			}
		});
	},
	getFormList : function(el) {
		var sysOwner = $("#sysOwner").val();
		var category = $(el).val();
		if (isEmpty(sysOwner) || isEmpty(category))
			return;
		var html = "";
		if (!isEmptyObject(schedule_obj.typeForms[category])) {
			for (var i = 0; i < schedule_obj.typeForms[category].length; i++) {
				var item = schedule_obj.typeForms[category][i];
				html += "<option value='" + item.id + "'>" + item.formName + "</option>";
			}
		}
		$(el).parent().find("[data-formId]").val("");
		$(el).parent().find("[data-formId]").html(html);
	},
	getElementHtml : function(item) {
		var html = '<div class="fill-parent ' + (item.index == 0 ? "border-bottom-line" : "") + ' clearfix" data-element="' + item.index + '">';
		if (!isEmpty(item.recordId)) {// 已随访表单显示
			html += '<div class="sf-time">';
			html += '<span class="xt-title">已随访</span> <span class="xt-title m-l-10" predate="' + new Date(item.recordDate).pattern("yyyy/MM/dd")
							+ '">' + new Date(item.recordDate).pattern("yyyy/MM/dd") + '</span>';
			html += '</div>';
			html += '<div class="group-time-line" style="width: 520px; margin-bottom: 0;">';
			html += '<div class="time-img-line"></div>';
			html += '<div class="sf-finish"></div>';
			html += '<label class="xt-check-label2 margin-top-12">' + schedule_obj.getFollowTypeName(item) + '</label>';
			var event = "window.location.href='" + ctx + "/fuRecord/view.shtml?" + encodeURI("scheduleId=" + item.id + "&id=" + item.recordId) + "';";
			html += '<label class="xt-check-label2 pull-right margin-top-12" onclick="' + event + '">随访记录单</label>';
			html += '<div class="group-time-content">';
			html += '<textarea rows="2" class="sf-textarea readonly" readonly>' + convertEmpty(item.recordSummary) + '</textarea>';
			html += '</div>';
			html += '</div>';
		} else {
			html += '<input type="hidden" name="records[' + item.index + '].fkPatientId" value="' + item.fkPatientId + '"/>';
			html += '<input type="hidden" name="records[' + item.index + '].intervalType" value="d"/>';
			html += '<input type="hidden" name="records[' + item.index + '].sysOwner" value="' + item.sysOwner + '"/>';
			html += '<div class="sf-time"><span class="xt-title">' + (item.index == 0 ? "首次随访" : "离上次随访") + '</span>';
			if (item.index == 0) {
				html += '<input type="hidden" name="records[' + item.index + '].taskInterval" value="0"/>';
				html += '<input type="text" class="tl-input" name="records[' + item.index + '].followDateShow" value="' + item.followDateShow
								+ '" predate="' + item.preDate + '" daterangepicker="' + item.index + '"/>';
			} else {
				html += '<input type="hidden" name="records[' + item.index + '].followDateShow" value="' + item.followDateShow + '"/>';
				html += '<input type="text" name="records[' + item.index + '].taskInterval" value="' + convertEmpty(item.taskInterval) + '" ';
				html += 'class="tl-input" style="width: 50px;margin: 0px 5px 0px 8px;" readonly predate="' + item.preDate + '" daterangepicker="'
								+ item.index + '" required data-msg-required="距离上次随访天数不能为空"/>';
				html += '<span class="xt-title">天</span>';
				html += '<p class="xt-title gray-span" followdateSpan>' + item.followDateShow + '</p>';
			}
			html += '</div>';
			html += '<div class="group-time-line" style="width: 520px; margin-bottom: 0;"><div class="time-img-line"></div>';
			if (item.index != 0) {// 第一个不能删除
				html += '<div class="sf-delete" onclick="schedule_obj.delElement(this);"></div>';
			}
			html += '<select class="xt-select margin-top-12" name="records[' + item.index
							+ '].followType" onchange="schedule_obj.getFormList(this);">';
			for (var i = 0; i < schedule_obj.followTypes.length; i++) {
				var type = schedule_obj.followTypes[i];
				html += '<option value="' + type.itemCode + '" ' + (type.itemCode == item.followType ? "selected" : "") + '>' + type.itemName
								+ '</option>';
			}
			html += '</select>';
			for (var i = 0; i < schedule_obj.followFormTypes.length; i++) {
				var formType = schedule_obj.followFormTypes[i];
				var formList = schedule_obj.typeForms[formType.itemCode];
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
				html += '<input type="hidden" name="records[' + item.index + '].forms[' + i + '].orderBy" value="' + formType.orderBy + '"/>';
				html += '<input type="hidden" name="records[' + item.index + '].forms[' + i + '].formType" value="' + formType.itemCode + '"/>';
				html += '<div class="item-box" style="min-width:0px !important;">';
				html += '<input type="hidden" name="records[' + item.index + '].forms[' + i + '].isChecked" value="' + isChecked + '"/>';
				if (!isEmptyObject(formList)) {
					html += '<select class="xt-select pull-right margin-top-12 m-r-6 ' + (isChecked ? "active" : "") + '" name="records['
									+ item.index + '].forms[' + i + '].fkFormId" data-formtypeselect>';
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
		}
		html += '</div>';
		return html;
	},
	addElement : function(el) {
		var predate = "";
		var index = 0;
		if ($("#planList [data-element]").length > 0) {
			var el = $("#planList [data-element]:last");
			index = parseInt(el.attr("data-element")) + 1;
			predate = el.find("[predate]").attr("predate");
			if (isEmpty(predate)) {// 代表只有一次随访计划，而且是新增的
				predate = el.find("input[name $='followDateShow']").val();
			}
		}
		var item = {
			index : index,
			fkPatientId : $("#patientId").val(),
			sysOwner : $("#sysOwner").val(),
			taskInterval : 30,// 默认距离上次30天
			followDateShow : moment(predate, "YYYY/MM/DD").add(30, "days").format("YYYY/MM/DD"),
			preDate : predate,
			// 默认显示第一种类别
			followType : schedule_obj.followTypes[0].itemCode
		};
		$("#planList").append(schedule_obj.getElementHtml(item));
		schedule_obj.addDatePick();
	},
	delElement : function(el) {
		$(el).parent().parent().remove();
	},
	getFollowTypeName : function(item) {
		var name = "";
		for (var i = 0; i < schedule_obj.followTypes.length; i++) {
			var type = schedule_obj.followTypes[i];
			if (type.itemCode == item.followType) {
				name = type.itemName;
				break;
			}
		}
		return name;
	}
};