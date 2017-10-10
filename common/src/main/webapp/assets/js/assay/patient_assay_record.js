$(document).ready(function() {
    $("#routineCheckNavId").addClass("active");
    $("#assayTimesTable").css("max-height", $(window).height() - ($("#assayRecordBody").offset().top));
    getAssayDateData();
    addEvents();
});
/** 事件注册 */
function addEvents() {
    setReportDatePick($("#assayHistoryDialog #reportDateDiv"), {
        callback : function(s, e) {
            $("#assayHistoryDialog #reportDateDiv").data("startDate", s);
            $("#assayHistoryDialog #reportDateDiv").data("endDate", e);
            showAssayHistoryReport();
        }
    });

    $('#assayTimesRecord').on("click", "tr", function() {
        $(this).addClass("active").siblings().removeClass("active");
        getAssayRecord(true);
    });

    $("#assayCategory").on("click", "span[data-value]", function() {
        $(this).addClass("active").siblings().removeClass("active");
        getAssayRecord(false);
    });

    $("#diseaseDiv").on("click", ":checkbox", function() {
        var name = $(this).attr("name");
        if (name == "normal" || name == "unknown") {
            $("#diseaseDiv").find(":checkbox:not([name='" + name + "'])").prop("checked", false);
        } else {
            $("#diseaseDiv").find(":checkbox[name='normal'],:checkbox[name='unknown']").prop("checked", false);
        }
    });
}
function assayRecordMouseOver(element) {
    $("#assayRecord .datagrid").hide();
    if ($(element).attr("data-valueType") == 1)
        $(element).find(".datagrid").show();
}
function assayRecordMouseOut() {
    $("#assayRecord .datagrid").hide();
}
/** 设置记录单结果table的高度 */
function setRecordTableHeight() {
    $("#assayRecordTable").css("max-height", $(window).height() - $("#assayRecordTable").offset().top);
}

/** 获取所有化验时间数据 */
function getAssayDateData() {
    $.ajax({
        url : ctx + "/patient/assay/getAssayDateRecord.shtml",
        data : "fkPatientId=" + $("#patientId").val(),
        type : "post",
        loading : true,
        dataType : "json",
        success : function(data) {// ajax返回的数据
            var assayTimesRecordHtml = "";
            var assayRecordHtml = "";
            var assayCategoryHtml = "";
            if (data.status == 1) {
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    assayTimesRecordHtml += '<tr ' + (i == 0 ? ' class="active"' : '') + '" data-date="' + convertEmpty(item.assayDate) + '"><td>'
                                    + convertEmpty(item.assayDate) + '</td></tr>';
                }
                assayRecordHtml = getAssayRecordListHtml(data.records);
                assayCategoryHtml = getAssayCategoryHtml(data.categoryList);
            }
            $("#assayCategory").html(assayCategoryHtml);
            $("#assayTimesRecord").html(assayTimesRecordHtml);
            $("#assayRecord").html(assayRecordHtml);
            setRecordTableHeight();
        }
    });
}

/** 显示某个批次的数据 */
function getAssayRecord(needCategory) {
    var reqId = $("#assayCategory span[data-value].active").attr("data-value");
    if (needCategory)
        reqId = "";
    var assayDate = $("#assayTimesRecord tr.active").attr("data-date");
    $.ajax({
        url : ctx + "/patient/assay/getAssayRecord.shtml",
        data : "fkPatientId=" + $("#patientId").val() + "&reqId=" + encodeURI(reqId) + "&assayDate=" + assayDate + "&needCategory=" + needCategory,
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {// ajax返回的数据
            var assayRecordHtml = "";
            if (data.status == 1) {
                if (needCategory) {
                    $("#assayCategory").html(getAssayCategoryHtml(data.categoryList));
                    setRecordTableHeight();
                }
                assayRecordHtml = getAssayRecordListHtml(data.records);
            }
            $("#assayRecord").html(assayRecordHtml);
        }
    });
}

/** 生成化验单类别数据 */
function getAssayCategoryHtml(items) {
    var html = '<span class="tabbar tabbar2 active" data-value="">全部</span>';
    for ( var i in items) {
        html += '<span class="tabbar tabbar2" data-value="' + items[i].reqId + '">' + items[i].groupName + '</span>';
    }
    return html;
}

/** 生成化验单列表数据 */
function getAssayRecordListHtml(items) {
    var assayRecordHtml = "";
    var isAll = isEmpty($("#assayCategory span[data-value].active").attr("data-value")) ? true : false;
    if (isAll)
        var tempGroupIds = [];
    for (var t = 0; t < items.length; t++) {
        var item = items[t];
        if (isAll && !isEmpty(item.groupId)) {
            if (!checkGroupIdExists(tempGroupIds, item.groupId)) {
                tempGroupIds.push(item.groupId);
                assayRecordHtml += '<tr><td colspan="6" style="padding-left: 0px !important;" class="mail-list-bar"><div>' + item.groupName
                                + '</div></td></tr>';
            }
        }
        assayRecordHtml += '<tr data-valueType="' + convertEmpty(item.valueType)
                        + '" onmouseover="assayRecordMouseOver(this);" onmouseout="assayRecordMouseOut(this);">';
        assayRecordHtml += '<td width="10%">' + convertEmpty(item.itemCode) + '</td>';
        assayRecordHtml += '<td width="45%">' + convertEmpty(item.itemName) + '</td>';
        assayRecordHtml += '<td width="10%">' + convertEmpty(item.result) + '<div class="datagrid" onclick=\'showReportDialog("'
                        + convertEmpty(item.itemCode) + '","' + convertEmpty(item.itemName) + '","' + convertEmpty(item.unit) + '")\'></div></td>';
        var resultTip = "";
        if (item.resultTips == 3) {
            resultTip = '<div class="sign-high"></div>';
        } else if (item.resultTips == 4) {
            resultTip = '<div class="sign-low"></div>';
        }
        assayRecordHtml += '<td width="5%">' + convertEmpty(resultTip) + '</td>';
        assayRecordHtml += '<td width="15%">' + convertEmpty(item.reference).replace(new RegExp("<", 'gm'), "&lt;") + '</td>';
        assayRecordHtml += '<td width="10%">' + convertEmpty(item.unit) + '</td>';
        assayRecordHtml += '</tr>';
    }
    return assayRecordHtml;
}
/** 检查类别id是否已存在 */
function checkGroupIdExists(tempGroupIds, id) {
    for (var i = 0; i < tempGroupIds.length; i++) {
        if (tempGroupIds[i] == id)
            return true;
    }
    return false;
}

/** 保存检查结果汇总数据 */
function saveAssayResult(element) {
    var normal = $("#normal");
    var hav = $("#hav");
    var hbv = $("#hbv");
    var hcv = $("#hcv");
    var hev = $("#hev");
    var hiv = $("#hiv");
    var hsv = $("#hsv");
    var unknown = $("#unknown");
    if (normal.is(":checked") || hav.is(":checked") || hbv.is(":checked") || hcv.is(":checked") || hev.is(":checked") || hiv.is(":checked")
                    || hsv.is(":checked") || unknown.is(":checked") || normal.is(":checked")) {
        $("#showError").text("");
        $(element).blur();
        $.ajax({
            url : ctx + "/patient/assay/saveAssayResult.shtml",
            data : $("#assayResultForm").serialize(),
            type : "post",
            loading : true,
            dataType : "json",
            success : function(data) {// ajax返回的数据
                if (data.status == 1) {
                    showTips("保存成功");
                    $("#assayResultForm input[name='id']").val(data.id);
                }
            }
        });
    } else {
        $("#showError").css({
            color : "red"
        });
        $("#showError").text("请至少选择一项");
    }
}

/** 同步检查结果 */
function refrshAssayResult(element) {
    $.ajax({
        url : ctx + "/system/sysDbSource/downDB.shtml",
        data : $("#assayResultForm").serialize(),
        type : "post",
        loading : true,
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data.status == 1) {
                showTips("同步成功");
                getAssayDateData();// 刷新检验数据
            }
        }
    });
}

/** 显示某项报表历史数据的dialog */
function showReportDialog(itemCode, name, unit) {
    $(".datetime-tab-item").removeClass('active');// 样式控制
    $(".datetime-tab-item:eq(0)").addClass('active');// 样式控制
    $("#assayHistoryTitle").text(name + "历史数据");
    $('#assayHistoryDialog').off('shown.bs.modal').on('shown.bs.modal', function() {
        showAssayHistoryReport(itemCode, name, unit);
    }).modal('toggle');
}
