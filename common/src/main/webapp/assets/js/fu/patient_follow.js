$(function() {
	follow_obj.init();
});
var follow_obj = {
	init : function() {
		paging_obj.addPaging("patientList", "loadMoreBtn", $(window), function() {
			follow_obj.getPatients(false);
		}, 10);
		follow_obj.getPatients(true);
		follow_obj.addEvents();
	},
	addEvents : function() {
		$('#queryDiv [data-type]').on("click", function() {
			$(this).addClass("active").siblings().removeClass("active");
			paging_obj.resetPaging("patientList");
			follow_obj.getPatients(true);
		});
		$("#patientSearch").on("change", function() {
			paging_obj.resetPaging("patientList");
			follow_obj.getPatients(true);
		});
		$("#initialList").on("click", "[data-initial]", function() {
			paging_obj.resetPaging("patientList");
			$(this).addClass("active").siblings().removeClass("active");
			follow_obj.getPatients(false);
		});
		$("body").on("click", function() {
			$("#patientList [data-menulist]:not('.hide')").addClass("hide");
		});
		$("#batchSetForm [daterangepicker]").daterangepicker({
			"singleDatePicker" : true,
			"showDropdowns" : true,
			"locale" : {
				format : "YYYY-MM-DD"
			}
		});
	},
	getPatients : function(needInitial) {
		var type = $("#queryDiv [data-type].active").data("type");
		var name = $("#patientSearch").val();
		var data = "type=" + type + "&sysOwner=" + $("#sysOwner").val() + "&needInitial=" + needInitial;
		data += isEmpty(name) ? "" : ("&name=" + name);
		if (!needInitial)
			data += "&initial=" + $("#initialList [data-initial].active").data("initial");
		data += "&" + paging_obj.getPagingData("patientList").str;
		$.ajax({
			url : ctx + "/fuPatientFollow/getPatients.shtml",
			type : "post",
			data : encodeURI(data),
			dataType : "json",
			loading : true,
			success : function(data) {
				var html = "";
				var initialHtml = "";
				if (data.status == 1 && !isEmptyObject(data.record)) {
					var items = data.record.results;
					for (var i = 0; i < items.length; i++) {
						var item = items[i];
						html += follow_obj.getPatientDiv(item);
					}
				}
				if (needInitial) {
					var initials = data.initials;
					initials.sort();
					initialHtml += '<div class="search-result-item2 active" style="width: 75px;" data-initial=""><span>全部患者</span></div>';
					for (var i = 0; i < initials.length; i++) {
						initialHtml += '<div class="search-result-item2" data-initial="' + initials[i] + '"><span>' + initials[i] + '</span></div>';
					}
					$("#initialList").html(initialHtml);
				}
				$("#patientList").append(html);
				paging_obj.setStatus("patientList", data.record.totalPage);
			}
		});
	},
	getPatientDiv : function(item) {
		var type = $("#queryDiv [data-type].active").data("type");
		var moreList = [];
		var btnEvent = {};
		var object = {
			makePlan : {
				title : "制定计划",
				event : "follow_obj.showBatchSet('" + item.id + "');"
			},
			modifyPlan : {
				title : "修改计划",
				event : "window.location.href='" + ctx + "/fuPatientSchedule/view.shtml?sys=" + $("#sysOwner").val() + "&id=" + item.id + "';"
			}
		};
		if (type == "today" || type == "overdue") {// 今日随访或者随访逾期
			moreList.push({
				event : isEmpty(item.scheduleId) ? object.makePlan.event : object.modifyPlan.event,
				title : isEmpty(item.scheduleId) ? object.makePlan.title : object.modifyPlan.title
			});
			var data = "scheduleId=" + item.scheduleId + (isEmpty(item.recordId) ? "" : ("&id=" + item.recordId));
			btnEvent.event = "window.location.href='" + ctx + "/fuRecord/view.shtml?" + encodeURI(data) + "';";
			btnEvent.title = isEmpty(item.recordId) ? "随访" : "记录单";
		} else {
			btnEvent.event = isEmpty(item.scheduleId) ? object.makePlan.event : object.modifyPlan.event;
			btnEvent.title = isEmpty(item.scheduleId) ? object.makePlan.title : object.modifyPlan.title;
		}
		var html = '';
		html += '<div class="col-xt-6 p-r-13 p-b-7" data-patient="' + item.id + '" data-recordid="' + item.recordId + '">';
		html += '<div class="xt-bq height-88 rel">';
		if (moreList.length > 0) {
			html += '<div class="layer_menu_list m-r-133 m-t--8 hide" data-menulist><ul><li>';
			for (var i = 0; i < moreList.length; i++) {
				html += '<a onclick="' + moreList[i].event + '">' + moreList[i].title + '</a>';
			}
			html += '</li></ul></div>';
		}
		html += '<img class="xt-user-photo pull-left" src="' + ctx + "/images/" + item.imagePath + '">';
		html += '<div class="xt-user-info margin-top-5">';
		html += '<p class="xt-user-name">' + item.name + '</p>';
		html += '<p class="xt-user-sex">' + item.sexShow + '</p>';
		html += '<p class="xt-user-time">' + item.followDateShow + '</p>';
		html += '</div>';
		html += '<label class="xt-check-label2 pull-right m-t-2" onclick="' + btnEvent.event + '">' + btnEvent.title + '</label>';
		if (moreList.length > 0)
			html += '<label class="xt-check-label2 pull-right m-t-2 xt-dot" onclick="follow_obj.showMenu(event,this);">…</label>';
		html += '<div class="fill-parent float-left m-t-4" style="overflow: hidden;height: 32px;">';
		// html += '<span class="tag clearfix">CKD分期数据</span>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		return html;
	},
	showMenu : function(event, el) {
		var menu = $(el).parent().find("[data-menulist]");
		menu.hasClass("hide") ? menu.removeClass("hide") : menu.addClass("hide");
		stopEventBubble(event);
	},
	showBatchSet : function(id) {
		$("#batchSetForm input[name='idStr']").val(convertEmpty(id));
		$.ajax({
			url : ctx + "/fuScheduleTemplate/getList.shtml",
			type : "post",
			data : encodeURI("sysOwner=" + $("#sysOwner").val() + "&ownerId" + $("#ownerId").val()),
			dataType : "json",
			loading : true,
			success : function(data) {
				var html = "";
				if (data.status == 1 && !isEmptyObject(data.items)) {
					for (var i = 0; i < data.items.length; i++) {
						var item = data.items[i];
						html += '<div><label><input type="radio" value="' + item.id + '" name="id" />' + item.templateName + '</label><div>';
					}
				}
				$("#templateList").html(html);
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
					follow_obj.getPatients(false);
					$("#batchSetDialog").modal("hide");
					showTips();
				} else {
					showWarn("请选择应用模板");
				}
			}
		});
	}
};