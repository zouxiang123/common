var paginationObject = null;
$(function() {
    $("#routineCheckNavId").addClass("active");
    $("#assaycardScroll").height($(window).height() - ($("#assaycardScroll").offset().top + 10));
    addEvents();
    searchDialogHidden();
    paginationObject = pagination.addPaging({
        bodyId : "assaycard",
        scrollEl : $("#assaycardScroll"),
        callback : function() {
            getAssayDateData();
        },
        pageSize : 20
    });
    getAssayDateData();
});

/** 事件注册 */
function addEvents() {
    // 初始化时间控件(带时间段选择)
    $("#searchAssayHistory").find("[name='startDate'],[name='endDate']").daterangepicker({
        "singleDatePicker" : true,
        "showDropdowns" : true,
        "locale" : {
            format : "YYYY-MM-DD"
        }
    }, function(start, end, label) {
        $("#dateSelect").find("[type='radio' ]").each(function(index, element) {
            $("#dateSelect").find("[type='radio']").eq(index).removeAttr("checked");
        });
    });

    // 初始化时间控件(不带时间段选择)
    $("#searchForm #startDate,#searchForm #endtDate,#assayDaye").daterangepicker({
        "singleDatePicker" : true,
        "showDropdowns" : true,
        "locale" : {
            format : "YYYY-MM-DD"
        }
    });

    // 初始化选中事件
    $("#dateSelect input[type='radio']").change(function() {
        selectItemcodeHistoryDate();
    });

    $('#assaycard').on("click", ".card", function() {
        $(this).addClass("active").siblings().removeClass("active");
        getAssayRecord(true);
    });

    $("#assayCategory").on("click", "span[data-value]", function() {
        $(this).addClass("active").siblings().removeClass("active");
        getAssayRecord(false);
    });

    $("#assayResultForm").on("click", ":checkbox", function() {
        var name = $(this).attr("name");
        if (name == "normal" || name == "unknown") {
            $("#assayResultForm").find(":checkbox:not([name='" + name + "'])").prop("checked", false);
        } else {
            $("#assayResultForm").find(":checkbox[name='normal'],:checkbox[name='unknown']").prop("checked", false);
        }
    });

    // 注册检验结果中是否显示统计数据
    $("#shownum").change(function() {
        var itemCode = $("#itemCode").val();
        var name = $("#name").val();
        var unit = $("#unit").val();
        if ($(this).is(':checked')) {
            isshow = true;
            showAssayHistoryReport(itemCode, name, unit, isshow);
        } else {
            isshow = false;
            showAssayHistoryReport(itemCode, name, unit, isshow);
        }
    });

    // 默认加载当天前的30天内
    var date = new Date();
    date.setFullYear(date.getFullYear() - 1);
    date.setDate(date.getDate() + 1);
    $("#searchForm #startDate").val(new Date(date).pattern("yyyy-MM-dd"));

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
var tenantId = $("#tenantId").val();
/** 获取所有化验时间数据 */
function getAssayDateData() {
    var data = "?fkPatientId=" + $("#patientId").val() + "&" + paginationObject.getPagingData().str;
    data += "&" + $("#searchForm").serialize();
    $
                    .ajax({
                        url : ctx + "/assay/patientAssayRecord/getAssayDateRecord.shtml" + data,
                        // data : data,
                        type : "post",
                        loading : true,
                        dataType : "json",
                        success : function(data) {// ajax返回的数据
                            var assayTimesRecordHtml = "";
                            var assayRecordHtml = "";
                            var assayCategoryHtml = "";
                            var assayTimesRecordTheadHtml = "";
                            if (data.status == 1) {
                                var labTimeFlag = data.labTimeFlag;
                                if (!isEmpty(data.items)) {
                                    for (var i = 0; i < data.items.length; i++) {
                                        var item = data.items[i];
                                        if (labTimeFlag == 1) {
                                            assayTimesRecordHtml += '<div class="card"><div class="bb-line p-l-15 p-r-15 carditem clearfix"> <span class="float-left text-ellipsis width-60pre font-bold" title="'
                                                            + convertEmpty(data.items[i].groupName)
                                                            + '">'
                                                            + convertEmpty(data.items[i].groupName)
                                                            + '</span><span class="float-right text-ellipsis width-40pre"  title="'
                                                            + convertEmpty(data.items[i].reqId)
                                                            + '">'
                                                            + convertEmpty(data.items[i].reqId)
                                                            + '</span></div>';
                                            assayTimesRecordHtml += '<input type="hidden" value="' + data.items[i].reqId + '" id="data_reqId">'
                                            assayTimesRecordHtml += '<div class="p-l-15 p-r-15 carditem"><span class="m-r-10">采集时间：</span><span>'
                                                            + data.items[i].strSampleTime + '</span></div></div>';
                                        }
                                        if (labTimeFlag == 2) {
                                            assayTimesRecordHtml += '<div class="card"><div class="bb-line p-l-15 p-r-15 carditem clearfix"> <span class="float-left text-ellipsis width-60pre font-bold" title="'
                                                            + convertEmpty(data.items[i].groupName)
                                                            + '">'
                                                            + convertEmpty(data.items[i].groupName)
                                                            + '</span><span class="float-right text-ellipsis width-40pre"  title="'
                                                            + convertEmpty(data.items[i].reqId)
                                                            + '">'
                                                            + convertEmpty(data.items[i].reqId)
                                                            + '</span></div>';
                                            assayTimesRecordHtml += '<input type="hidden" value="' + data.items[i].reqId + '" id="data_reqId">'
                                            assayTimesRecordHtml += '<div class="p-l-15 p-r-15 carditem"><span class="m-r-10">报告时间：</span><span>'
                                                            + data.items[i].strReportTime + '</span></div></div>';
                                        }
                                        if (labTimeFlag == 3) {
                                            assayTimesRecordHtml += '<div class="card"><div class="bb-line p-l-15 p-r-15 carditem clearfix"> <span class="float-left text-ellipsis width-60pre font-bold" title="'
                                                            + convertEmpty(data.items[i].groupName)
                                                            + '">'
                                                            + convertEmpty(data.items[i].groupName)
                                                            + '</span><span class="float-right text-ellipsis width-40pre"  title="'
                                                            + convertEmpty(data.items[i].reqId)
                                                            + '">'
                                                            + convertEmpty(data.items[i].reqId)
                                                            + '</span></div>';
                                            assayTimesRecordHtml += '<input type="hidden" value="' + data.items[i].reqId + '" id="data_reqId">'
                                            assayTimesRecordHtml += '<div class="p-l-15 p-r-15 carditem"><span class="m-r-10">采集时间：</span><span>'
                                                            + data.items[i].strSampleTime + '</span></div>';
                                            assayTimesRecordHtml += '<div class="p-l-15 p-r-15 carditem"><span class="m-r-10">报告时间：</span><span>'
                                                            + data.items[i].strReportTime + '</span></div></div>';
                                        }
                                    }
                                }
                                // 如果左边化验单没数据时候，删除化验详单
                                if (data.items.length == 0) {
                                    $("#assayRecord").html('');
                                }
                                if (!isEmpty(data.records) && (data.records).length > 0 && (data.record.pageNo) == 1) {
                                    assayRecordHtml = getAssayRecordListHtml(data.records);
                                    $("#assayRecord").html(assayRecordHtml);
                                }
                                if (!isEmpty(data.categoryList)) {
                                    assayCategoryHtml = getAssayCategoryHtml(data.categoryList);
                                }
                            }
                            $("#assayTimesRecordThead").html(assayTimesRecordTheadHtml);
                            $("#assayCategory").html(assayCategoryHtml);
                            $("#assaycard").append(assayTimesRecordHtml);
                            // setRecordTableHeight();
                            paginationObject.setTotalPage(data.record.totalPage);
                            // 默认选中第一个
                            if (data.items.length > 0)
                                switchTestType();
                        }
                    });
}

/** 显示某个批次的数据 */
function getAssayRecord(needCategory) {
    var reqId = $("#assaycard .card.active #data_reqId").val();
    /*if (needCategory)
        reqId = "";*/
    // 判断当前选中是检查日期，还是报告日期，获取对应的日期，进行判断
    var option = getTestDate();

    // var assayDate = $("#assayTimesRecord tr.active").attr("data-date");
    $.ajax({
        url : ctx + "/assay/patientAssayRecord/getAssayRecord.shtml",
        // "&assayDate=" + assayDate +
        data : "fkPatientId=" + $("#patientId").val() + "&reqId=" + reqId + "&needCategory=" + needCategory + "" + option,
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {// ajax返回的数据
            var assayRecordHtml = "";
            if (data.status == 1) {
                assayRecordHtml = getAssayRecordListHtml(data.records);
                $("#assayRecord").html(assayRecordHtml);
            }

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
    var delFlag = $('#delFlag').val();
    var assayRecordHtml = "";
    for (var t = 0; t < items.length; t++) {
        var item = items[t];
        if (!isEmpty(item.result)) {
            assayRecordHtml += '<tr data-valueType="' + convertEmpty(item.valueType)
                            + '" onmouseover="assayRecordMouseOver(this);" onmouseout="assayRecordMouseOut(this);"><td style="width: 15%">'
                            + convertEmpty(item.itemCode) + '</td>';
            assayRecordHtml += '<td style="width: 25%">' + convertEmpty(item.itemName) + '</td>';
            assayRecordHtml += '<td style="width: 15%">' + convertEmpty(item.result) + '<div class="datagrid" onclick=\'showReportDialog("'
                            + convertEmpty(item.itemCode) + '","' + convertEmpty(item.itemName) + '","' + convertEmpty(item.unit)
                            + '")\'></div></td>';
            assayRecordHtml += '<td style="width: 5%">';
            if (item.resultTips == 3) {
                assayRecordHtml += '<div class="sign-high"></div>';
            } else if (item.resultTips == 4) {
                assayRecordHtml += '<div class="sign-low"></div>';
            }
            assayRecordHtml += '</td>';
            assayRecordHtml += '<td style="width: 20%">' + convertEmpty(item.reference) + '</td>';
            assayRecordHtml += '<td style="width: 10%">' + convertEmpty(item.sampleClass) + '</td>';
            assayRecordHtml += '<td style="width: 10%">' + (item.diaAbFlag == '2' ? '是' : '否');
            if (delFlag == "true") {
                assayRecordHtml += '<span class="float-right default-active-color" style="cursor: pointer" onclick=\'showAssayRecordDiaAbFlag("'
                                + item.diaAbFlag + '","' + item.reqId + '","' + item.inspectionId + '")\'>编辑</span>';
            }
            assayRecordHtml += '</td></tr>';
        }

    }
    item = items[0];
    if (items.length == 0) {
        $("#editAssayRecord").hide();
    } else {
        var createUserId = item.createUserId;
        if (createUserId == '1') {
            $("#editAssayRecord").hide();
        } else {
            $("#editAssayRecord").show();
            $("#editAssayRecord").attr("onclick", "selectByGroupId('" + item.reqId + "');");
        }
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
    var checkedEl = $("#assayResultForm").find(':checkbox:checked')
    if (checkedEl.length > 0) {
        $("#showError").text("");
        $(element).blur();
        var chk_value = [];// 定义一个数组
        checkedEl.each(function() {// 遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push($(this).attr("data-text"));// 将选中的值添加到数组chk_value中
        });
        showConfirm("传染病标志:" + chk_value, function() {
            $.ajax({
                url : ctx + "/assay/patientAssayRecord/saveAssayResult.shtml",
                data : $("#assayResultForm").serialize(),
                type : "post",
                loading : true,
                dataType : "json",
                success : function(data) {// ajax返回的数据
                    if (data.status == 1) {
                        showTips("保存成功");
                        $("#assayResultDialog").modal("hide");
                        $("#assayResultForm input[name='id']").val(data.id);
                    }
                }
            });
            SystemDialog.modal('hide');
        });
    } else {
        $("#showError").css({
            color : "red"
        })
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
    $("#itemCode").val(itemCode);
    $("#name").val(name);
    $("#unit").val(unit);
    $(".datetime-tab-item").removeClass('active');// 样式控制
    $(".datetime-tab-item:eq(0)").addClass('active');// 样式控制
    $("#assayHistoryTitle").text(name + "历史数据");
    $('#assayHistoryDialog').off('shown.bs.modal').on('shown.bs.modal', function() {
        // showAssayHistoryReport(itemCode, name, unit);
        // 默认选中一年
        $("#dateSelect input[type='radio'][value='y']").attr("checked", "true");
        selectItemcodeHistoryDate(itemCode, name, unit);
        showAssayHistoryReport(itemCode, name, unit, true);
    }).modal('toggle');
}
/** 显示添加项dialog */
function showAddDialog(dialogId) {
    $("#saveType").val("save");
    $("#dialogTitle").text("添加化验项信息");
    $("#" + dialogId).modal("show");
    addValidate();
    $("div[data-hide='hide']").css("display", "block");
    getAssayCategory();
}
// 选中类型
$("#groupId").change(function() {
    $(document).data("itemCode", "");
    getAssayitem();
});
/** 获取化验单类别数据 */
function getAssayCategory() {
    $.ajax({
        url : ctx + "/assay/hospDict/listManualAddGroup.shtml",
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {

            var htmlSelect = '';
            for (var i = 0; i < data.items.length; i++) {
                var item = data.items[i];
                htmlSelect += '<option value="' + item.groupId + '">' + item.groupName + '</option>';
            }
            $("#groupId").html(htmlSelect);
            if (data.status == 1) {
                getAssayitem();
            }
        }
    });
}

var addTr = function otherTr() {
    var htmlTr = '';
    htmlTr += '<tr>';
    htmlTr += '<td><input data-val="itemName" class="u-input-120" type="text"></td>';
    htmlTr += '<td><input data-val="itemCode" class="u-input-50" type="text"></td>';
    htmlTr += '<td><input data-val="result" class="u-input-50" type="text"></td>';
    htmlTr += '<td class="text-center">' + '<input data-val="minValue" class="u-input-50" type="text"> / '
                    + '<input data-val="maxValue" class="u-input-50" type="text">' + '</td>';
    htmlTr += '<td class="text-center"><input data-val="unit" class="u-input-50" type="text"></td>';
    htmlTr += '<td class="text-center">'
                    + '<img onclick="addOtherTr()" src="../../assets/img/ui/add.png" alt="" width="20" height="20" class="m-r-5">'
                    + '<img onclick="removeTr(this)" src="../../assets/img/ui/delete1.png" alt="" width="20" height="20"></td></tr>';
    return htmlTr;
};
/**
 * 手动添加化验项表头
 * 
 * @param groupId
 *            化验单编号
 */
function tableThs(groupId) {
    var htmlTh = "";
    htmlTh += "<tr>";
    htmlTh += '<th style="width: 25%">项目名称</th>';
    htmlTh += '<th style="width: 20%">项目代号</th>';
    htmlTh += '<th style="width: 20%">检查结果</th>';
    htmlTh += '<th style="width: 20%">参考值</th>';
    if (groupId == "others") {
        htmlTh += '<th style="width: 15%;">添加/删除</th>';
    }
    if (groupId == "update") {
        htmlTh += '<th  style="width: 15%;">删除</th>';
    }
    htmlTh += "</tr>";
    $("#thValue").html(htmlTh);
}
/**
 * 其他选项加入新行
 */
function addOtherTr() {
    $("#trValue").append(addTr);
}
/**
 * 其他选项删除选中行
 * 
 * @param dom
 */
function removeTr(dom) {
    var link = $(dom).parents("tr");
    link.remove();
}
/** 获取table数据 */
function getAssayitem() {
    var groupId = convertEmpty($("#groupId").val());
    if (groupId == "others") {
        $("#assayName").attr("name", "assayName");
        // 表头
        tableThs(groupId);
        // 关闭非其他选项
        $("#otherGroupId").css("display", "block ");
        // 化验项主体
        $("#trValue").empty();
        var htmlTr = addTr;
        $("input[data-val='result']").attr("name", "assayResule");
        $("#trValue").html(htmlTr);
    } else {
        $("#assayName").removeAttr("name");
        tableThs(groupId);
        // 关闭其他选项
        $("#otherGroupId").css("display", "none");
        // 化验项主体
        $("#trValue").empty();
        if (isEmpty(groupId)) {
            $("#trValue").append("");
        } else {
            $
                            .ajax({
                                url : ctx + "/assay/hospDict/getAssayList.shtml",
                                type : "post",
                                data : {
                                    groupId : groupId
                                },
                                dataType : "json",
                                loading : true,
                                success : function(data) {
                                    var htmlTr = '';
                                    if (data.status == 1) {
                                        for (var i = 0; i < data.items.length; i++) {
                                            var item = data.items[i];
                                            htmlTr += '<tr>';
                                            htmlTr += '<input type="hidden" data-reference="reference" value="' + item.reference + '"></td>';
                                            htmlTr += '<input type="hidden" data-id="id" value=' + item.id + '></td>';
                                            htmlTr += '<td data-val="itemName">' + item.itemName + '</td>';
                                            htmlTr += '<td data-val="itemCode">' + item.itemCode + '</td>';
                                            htmlTr += '<td><input data-val="result" type="text" style="width: 100%;height: 28px" class="border-gray text-center"></td>';
                                            htmlTr += '<td data-val="unit" class="text-center">' + convertEmpty(item.reference)
                                                            + convertEmpty(item.unit) + '</td>';
                                        }
                                    }
                                    $("#trValue").append(htmlTr);
                                }
                            });
        }
    }
}

/**
 * 自动计算提示
 * 
 * @param cResult
 * @param cResultTipe
 * @param cMinValue
 * @param cMaxValue
 * @returns {String}
 */
function showTip(cResult, cMinValue, cMaxValue) {
    var cResultTipe = "";
    // 判断不为空且是数字
    if (!isEmpty(cResult) && isNaN(cResult)) {
        cResultTipe = "2";
    } else {
        // 都不为空
        if (!isEmpty(cMaxValue) && !isEmpty(cMinValue)) {
            if (parseFloat(cResult) < parseFloat(cMinValue) && parseFloat(cResult) > parseFloat(cMaxValue)) {
                cResultTipe = "1";
            } else if (parseFloat(cResult) > parseFloat(cMaxValue)) {
                cResultTipe = "3"
            } else if (parseFloat(cResult) < parseFloat(cMinValue)) {
                cResultTipe = "4"
            } else {
                cResultTipe = "2"
            }
            return cResultTipe;
        } else if (!isEmpty(cMinValue)) {
            parseFloat(cResult) < parseFloat(cMinValue) ? cResultTipe = "4" : cResultTipe = "1";
        } else if (!isEmpty(cMaxValue)) {
            parseFloat(cResult) > parseFloat(cMaxValue) ? cResultTipe = "3" : cResultTipe = "1";
        } else {
            cResultTipe = "2";
        }
    }
    return cResultTipe;
}

/**
 * 获取录入的化验信息
 * 
 * @returns {String}
 */
function getPatientAssessValue(groupId) {
    var patientAssessHtml = "";
    var cGroupName = convertEmpty($("#groupId").find("option:selected").text());
    var cGroupId = "";
    var cAssayDate = $("#assayDaye").val();
    var fkPatientId = $("#patientId").val();
    var cResultTipe = "";// 提示
    var cName = "";// 名称
    var cCode = "";// 代号
    var cResult = "";// 检查结果
    var cUnit = "";// 单位
    var cReference = "";// 参考值
    var cMinValue = "";// 最小值
    var cMaxValue = "";// 最大值
    var cId = "";
    var reqId = "";
    var id = "";
    $("#trValue>tr")
                    .each(
                                    function(i, obj) {
                                        if (groupId == "update") {
                                            cGroupId = $(obj).find("td[data-groupId]").attr("data-groupId");
                                            cName = $(obj).find("td[data-val='itemName']").text();
                                            cCode = $(obj).find("td[data-val='itemCode']").text();
                                            cResult = $(obj).find("td>input[data-val='result']").val();
                                            cUnit = $(obj).find("td[data-val='unit']").text();
                                            cMinValue = $(obj).find("td>span[data-val='minValue']").text();
                                            cMaxValue = $(obj).find("td>span[data-val='maxValue']").text();
                                            cResultTipe = showTip(cResult, cMinValue, cMaxValue);
                                            cGroupName = $(obj).find("img[data-groupName]").attr("data-groupName");
                                            cId = $(obj).find("img[data-mark]").attr("data-mark");
                                            cReference = $(obj).find("input[data-reference='reference']").val();
                                            reqId = $(obj).find("#reqId").val();
                                            id = $(obj).find("#id").val();
                                        } else if (groupId == "others") {
                                            cGroupId = convertEmpty($("#groupId").val());
                                            cGroupName = $("#otherGroupId>input").val();
                                            cName = $(obj).find("td>input[data-val='itemName']").val();
                                            cCode = $(obj).find("td>input[data-val='itemCode']").val();
                                            cResult = $(obj).find("td>input[data-val='result']").val();
                                            cUnit = $(obj).find("td>input[data-val='unit']").val();
                                            cMinValue = $(obj).find("td>input[data-val='minValue']").val();
                                            cMaxValue = $(obj).find("td>input[data-val='maxValue']").val();
                                            cResultTipe = showTip(cResult, cMinValue, cMaxValue);
                                            cReference = $(obj).find("input[data-reference='reference']").val();
                                        } else {
                                            id = $(obj).find("input[data-id='id']").val();
                                            cGroupId = convertEmpty($("#groupId").val());
                                            cGroupName = $("#groupId option:selected").text();
                                            cName = $(obj).find("td[data-val='itemName']").text();
                                            cCode = $(obj).find("td[data-val='itemCode']").text();
                                            cResult = $(obj).find("td>input[data-val='result']").val();
                                            cUnit = $(obj).find("td[data-val='unit']").text();
                                            cMinValue = $(obj).find("td>span[data-val='minValue']").text();
                                            cMaxValue = $(obj).find("td>span[data-val='maxValue']").text();
                                            cResultTipe = showTip(cResult, cMinValue, cMaxValue);
                                            cReference = $(obj).find("input[data-reference='reference']").val();
                                        }
                                        // 非空数据插入
                                        if (!isEmpty(cResult) && "" != cResult) {
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].reqId=' + reqId : '&dHL[' + i
                                                            + '].reqId=' + reqId);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].id=' + id : '&dHL[' + i + '].id=' + id);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].patientAssayId=' + cId : '&dHL[' + i
                                                            + '].patientAssayId=' + cId);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].resultTips=' + cResultTipe : '&dHL[' + i
                                                            + '].resultTips=' + cResultTipe);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].fkPatientId=' + fkPatientId : '&dHL[' + i
                                                            + '].fkPatientId=' + fkPatientId);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].assayDateStr=' + cAssayDate : '&dHL[' + i
                                                            + '].assayDateStr=' + cAssayDate);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].groupId=' + cGroupId : '&dHL[' + i
                                                            + '].groupId=' + cGroupId);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].groupName=' + cGroupName : '&dHL[' + i
                                                            + '].groupName=' + cGroupName);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].minValue=' + convertEmpty(cMinValue)
                                                            : '&dHL[' + i + '].minValue=' + convertEmpty(cMinValue));
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].maxValue=' + convertEmpty(cMaxValue)
                                                            : '&dHL[' + i + '].maxValue=' + convertEmpty(cMaxValue));
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].itemName=' + cName : '&dHL[' + i
                                                            + '].itemName=' + cName);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].itemCode=' + cCode : '&dHL[' + i
                                                            + '].itemCode=' + cCode);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].result=' + cResult : '&dHL[' + i
                                                            + '].result=' + cResult);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].unit=' + cUnit : '&dHL[' + i + '].unit='
                                                            + cUnit);
                                            patientAssessHtml += (patientAssessHtml == '' ? 'dHL[' + i + '].reference=' + cReference : '&dHL[' + i
                                                            + '].reference=' + cReference);
                                        }
                                    });
    return patientAssessHtml;
}

function refreshMonthReport() {
    var dateStr = $("#assayDaye").val();
    $.ajax({
        url : ctx + "/report/assay/subInterface/refreshMonthReport.shtml",
        type : "post",
        data : "tenantId=" + tenantId + "&dateStr=" + dateStr,
        dataType : "json",
        success : function(data) {
        }
    });
}

/**
 * 手动录入化验项信息
 */
function insertPatientAssay() {
    var type = $("#saveType").val();
    // 判断是新增操作还是保存操作
    if (type == "save") {
        var groupId = convertEmpty($("#groupId").val());
        var patientAssess = getPatientAssessValue(groupId);
        // alert(patientAssess);
        if (!$('#addAssay').valid()) {
            return false;
        }
        if (!isEmpty(patientAssess)) {
            $.ajax({
                url : ctx + "/assay/patientAssayRecord/insertPatientAssay.shtml",
                type : "post",
                data : patientAssess,
                dataType : "json",
                success : function(data) {
                    if (data.status) {
                        $("#addPatientAssay").modal("hide");
                        $("#assaycard").html("");
                        getAssayDateData();
                        showTips("保存成功");
                        location.reload();
                        // refreshMonthReport();
                    }
                }
            });
        } else {
            showTips("请输入化验结果");
        }
    } else {
        var groupId = "update";
        console.log('更新数据')
        var patientAssess = getPatientAssessValue(groupId);
        if (!isEmpty(patientAssess)) {
            $.ajax({
                url : ctx + "/assay/patientAssayRecord/updatePatientAssay.shtml",
                type : "post",
                data : patientAssess,
                dataType : "json",
                success : function(data) {
                    if (data.status) {
                        $("#addPatientAssay").modal("hide");
                        getAssayRecord(true);
                        showTips("编辑成功");
                        location.reload();
                    }
                }
            });
        } else {
            $("#addPatientAssay").modal("hide");
            showTips("暂无修改内容");
        }
    }

}

/**
 * 查询所有手动录入的化验项(编辑化验项)
 * 
 * @param groupId
 */
function selectByGroupId(reqId) {
    $.ajax({
        url : ctx + "/assay/patientAssayRecord/selectByReqId.shtml",
        type : "post",
        data : "reqId=" + reqId + "&fkPatientId=" + $("#patientId").val(),
        dataType : "json",
        success : function(data) {
            if (data.status) {
                $("#addAssay #groupId").val();
                $("#saveType").val("update");
                $("#addPatientAssay").modal("show");
                $("div[data-hide='hide']").css("display", "none");
                $("#dialogTitle").text("编辑化验项信息");
                tableThs("update");
                var htmlTr = '';
                var patientAssayList = data.patientAssayList;
                for (var i = 0; i < patientAssayList.length; i++) {
                    var item = patientAssayList[i];
                    htmlTr += '<tr>';
                    htmlTr += '<td  data-groupId="' + item.groupId + '" data-val="itemName">' + item.itemName + '</td>';
                    htmlTr += '<td  data-val="itemCode"><input type="hidden" value="' + item.reqId
                                    + '" id="reqId" name="reqId" /><input type="hidden" data-id="id" value="' + item.id + '" id="id" name="id" /> '
                                    + item.itemCode + '</td>';
                    htmlTr += '<td><input data-val="result" style="width: 100%;height: 28px" class="border-gray text-center" type="text" value="'
                                    + convertEmpty(item.result) + '"></td>';
                    htmlTr += '<td class="text-center"><span  data-val="minValue">' + convertEmpty(item.reference) + '</span></td>';
                    htmlTr += '<td class="text-center">' + '<img data-groupName="' + item.groupName + '" data-mark="' + item.id
                                    + '" onclick="removeTr(this),removeById(\'' + item.id
                                    + '\');" src="../../assets/img/ui/delete1.png" alt="" width="20" height="20"></td></tr>';
                }
                if (patientAssayList.length != 0) {
                    item = patientAssayList[0];
                    $("#addAssay #groupId").html('<option value="' + item.groupId + '">' + item.groupName + '</option>');
                    var rangepicker = $("#addAssay #assayDaye").data("daterangepicker");
                    rangepicker.setStartDate(new Date(item.reportTime));
                    rangepicker.setEndDate(new Date(item.reportTime));
                }
                $("#trValue").html(htmlTr);
            }
        }
    });
}

/**
 * 删除录入的化验项
 * 
 * @param id
 */
function removeById(id) {
    $.ajax({
        url : ctx + "/assay/patientAssayRecord/deleteById.shtml",
        type : "post",
        data : "id=" + id,
        dataType : "json",
        success : function(data) {
            if (data.status) {
                getAssayRecord(true);
                showTips("删除成功");
            }
        }
    });
}

function addValidate() {
    $('#addAssay').validate({
        onfocusout : false,
        rules : {
            assayDaye : {
                required : [ "报告日期" ]
            },
            assayName : {
                required : [ "化验单名称" ]
            }
        },
        highlight : function(element, errorClass, validClass) { // element出错时触发
            if (!$(element).hasClass(errorClass))
                $(element).addClass(errorClass);
        },
        unhighlight : function(element, errorClass) { // element通过验证时触发
            if ($(element).hasClass(errorClass))
                $(element).removeClass(errorClass);
        },
        errorPlacement : function(error, element) {
            var obj = getValidateErrorObj($(element));
            $(error[0]).css("padding-right", "30px");
            console.log(JSON.stringify(error));
            $(element).find("[data-error]").append(error)
        }
    });
};

/*****************************************************************************************************************************************************
 * 
 * /** 选中化验项，历史数据
 * 
 * @param itemCode
 *            化验项
 * @param name
 *            化验项名称
 * @param unit
 *            化验项单位
 * @param timeType
 *            时间单位
 * 
 */
function selectItemcodeHistoryDate(itemCode, name, unit, timeType) {
    var section = $("#searchAssayHistory input[type='radio']:checked").val();
    var startDate = moment();
    var endDate = moment();
    if ("w" == section) {// 周
        startDate = moment().subtract(7, 'days');
    } else if ("m" == section) {// 月
        startDate = moment().subtract(1, 'month').add(1, 'days');
    } else if ("y" == section) {// 年
        startDate = moment().subtract(1, 'year').add(1, 'days');
    }
    var startPicker = $("#searchAssayHistory").find("[name='startDate']").data("daterangepicker");
    var endPicker = $("#searchAssayHistory").find("[name='endDate']").data("daterangepicker");
    startPicker.setStartDate(startDate);
    startPicker.setEndDate(startDate);
    endPicker.setStartDate(endDate);
    endPicker.setEndDate(endDate);

}
/**
 * 默认选中化验时间项目
 */
function switchTestType() {
    $("#assaycard .card").removeClass("active");
    $("#assaycard .card").eq(0).addClass("active");
}
/**
 * 切换tab 报告日期，检查日期
 */
function switchTestTab(obj) {
    $(".u-tab-head>span").removeClass("active");
    var option = $(obj).attr("option");
    $(obj).addClass("active");

    var trArrays = $(".u-tab-content tr");

    // 报告日期
    if (option == "report") {
        switchTestDate(0, trArrays);

        // 检查日期
    } else {
        switchTestDate(1, trArrays);
    }
    // 默认选中第一行中的td
    switchTestDateClick($(".u-tab-content tr")[0]);

    // 调用加载数据列表
    getAssayRecord(true);
}

/**
 * 切换日期
 */
function switchTestDate(tdIndex, trArrays) {
    $.each(trArrays, function(i, tr) {
        $.each(tr.children, function(j, td) {
            if (tr.children.length > 1) {
                if (tdIndex == j) {
                    $(td).show();
                } else {
                    $(td).hide();
                }
            } else {
                $(td).show();
            }
        });
    });
}

/**
 * 点击日期，动态切换选中状态数据
 * 
 * @param obj
 */
function switchTestDateClick(obj) {
    var trArrays = $(".u-tab-content tr");
    $.each(trArrays, function(i, tr) {
        $.each(tr.children, function(j, td) {
            $(td).removeClass("active");
        });
    });
    if (!isEmpty(obj)) {
        $.each(obj.children, function(j, td) {
            var display = $(td).css('display');
            if (display != 'none') {
                $(td).addClass("active");
            }
        });
    }
}

function searchDialogShow() {
    $("#searchDialog").show();

}
function searchDialogHidden() {
    $("#searchDialog").hide();
}

function searchSubmit() {
    var timeType = $('input:radio[name="dateType"]:checked').val();
    if (isEmpty(timeType)) {
        showWarn("请选择时间类型");
        return false;
    }
    var startDate = $("#searchForm #startDate").val();
    var endtDate = $("#searchForm #endtDate").val();
    paginationObject.resetPaging();
    $("#assaycard").html("");
    getAssayDateData(timeType, startDate, endtDate);
    $("#searchDialog").hide();
}

function showAssayResultDialog() {
    $("#showError").text("");
    assayResultDialog(function() {
        // 设置dialog离顶部高度
        $("#assayResultDialog").css("margin", "30px auto");
        // 设置dialog宽度为80%
        $("#assayResultDialog .modal-dialog").css("width", "450px");
        // 设置dialog 内容高度
        var dialog_height = 240;
        $("#assayResultDialog .modal-content").css("height", dialog_height + 16 + "px");
        $("#assayResultDialog .dialog-wrap .list-group").css("height", (dialog_height - 118) + "px");
        // show dialog
        $("#assayResultDialog").modal("show");
    });
}

/**
 * 显示标签
 */
function assayResultDialog(callback) {
    $.ajax({
        url : ctx + "/assay/patientAssayRecord/record.shtml",
        data : "patientId=" + $("#patientId").val(),
        type : "post",
        loading : true,
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data.assayResult) {
                mappingFormData(data.assayResult, "assayResultForm");
                if (!isEmpty(callback)) {
                    callback();
                }
            }
        }
    })
}
/**
 * 获取化验日期数据
 */
function getTestDate() {
    var trArrays = $(".u-tab-content tr");
    var option = "";
    // 获取选中tab类型 选中日期
    var date = $(".u-tab-content tr>td.active").text();
    // 报告日期
    if ($(".u-tab-head>span.active").attr("option") == "report") {
        option = "&reportTimeShow=" + date;
    } else {// 送检日期
        option = "&sampleTimeShow=" + date;
    }

    return option;
}
// 显示是否血透后弹框
function showAssayRecordDiaAbFlag(diaAbFlag, reqId, inspectionId) {
    $("#bakReqId").val(reqId);
    $("#bakDiaAbFlag").val(diaAbFlag);
    $("#bakInspectionId").val(inspectionId);
    $("#isAllToHDAfter").attr("checked", false);
    $("#diaAbFlagRadioDiv").find("input[name='diaAbFlag'][value='" + diaAbFlag + "']").prop("checked", true);
    $("#assayRecordDiaAbFlag").modal("show");
}

// 手动更新透前透后
function updateDiaAbFlag() {
    var diaAbFlag = $("#diaAbFlagRadioDiv").find('input[name=diaAbFlag]:checked').val();
    var bakDiaAbFlag = $("#bakDiaAbFlag").val();
    var isAllToHDAfter = $('input[name=isAllToHDAfter]:checked').val();
    if (diaAbFlag == bakDiaAbFlag && !isAllToHDAfter) {
        showTips("暂无修改内容");
        $("#assayRecordDiaAbFlag").modal("hide");
        return;
    }
    var reqId = $("#bakReqId").val();
    var patientId = $("#patientId").val();
    var data = "diaAbFlag=" + diaAbFlag + "&reqId=" + reqId + "&fkPatientId=" + patientId;
    if (!isAllToHDAfter) {
        var inspectionId = $("#bakInspectionId").val();
        data += "&inspectionId=" + inspectionId
    }
    $.ajax({
        url : ctx + "/assay/patientAssayRecord/updateHandDiaAbFlag.shtml",
        data : data,
        type : "post",
        loading : true,
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data.status) {
                $("#assayRecordDiaAbFlag").modal("hide");
                getAssayRecord(true);
                showTips("编辑成功");
            }
        }
    })
}
