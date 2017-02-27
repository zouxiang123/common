var index = 0;// 集合下标
var xtStartReason = "";// 血透开始原因
var xtEndReason = "";// 血透结束原因
var ftEndReason = "";// 腹透结束原因
var syzEndReason = "";// 肾移植结束原因
var gmResouce = "";// 过敏原因
var bs_crbzdmc = "";// 传染病诊断名称
var bs_crbhdzt = "";// 传染病活动状态
var bs_crbzlqk = "";// 传染病治疗情况

function getAllMedical() {
    $.ajax({
        url : ctx + "/patient/diagnosis/newPatient.shtml",
        type : "post",
        dataType : "json",
        async : false,
        success : function(data) {
            xtStartReason = data.xtStartReason;
            xtEndReason = data.xtEndReason;
            ftStartReason = data.ftStartReason;
            ftEndReason = data.ftEndReason;
            syzEndReason = data.syzEndReason;
            gmResouce = data.gmResouce;
            bs_crbzdmc = data.bs_crbzdmc;
            bs_crbhdzt = data.bs_crbhdzt;
            bs_crbzlqk = data.bs_crbzlqk;
        }
    });
}

// ==========================================================
// 初始化数据
$(function() {
    historyRemark();
    historyRemarkA();
    historyRemarkB();
    getAllMedical()
    showAddOpt("addOpt");
    showAddXt("addXt");
    showAddFt("addFt");
    showAddSyz("addSyz");
    showAddGm("addGm");
    showAddCrb("addCrb");
    // ==========================================================
    // 7.手术
    var optTime_show = $("#optTime_show"); // 7.手术日期
    var opt_Name = $("#opt_Name"); // 7.手术名称
    dateShow(optTime_show); // 手术日期
    // ==========================================================
    // 8.血透
    var xtStartTime_show = $("#xtStartTime_show"); // 8.血透开始日期
    var xtEndTime_show = $("#xtEndTime_show"); // 8.血透结束日期
    dateShow(xtStartTime_show); // 血透开始日期
    dateShow(xtEndTime_show); // 血透结束日期
    // ==========================================================
    // 9.腹透
    var ftStartTime_show = $("#ftStartTime_show"); // 9.腹透开始日期
    var ftEndTime_show = $("#ftEndTime_show"); // 9.腹透结束日期
    dateShow(ftStartTime_show); // 腹透开始日期
    dateShow(ftEndTime_show); // 腹透结束日期
    // ==========================================================
    // 10.肾移
    var syzStartTime_show = $("#syzStartTime_show"); // 10.肾移植开始日期
    var syzEndTime_show = $("#syzEndTime_show"); // 10.肾移植结束日期
    dateShow(syzStartTime_show); // 肾移植开始日期
    dateShow(syzEndTime_show); // 肾移植结束日期
    // ==========================================================
    // 11.传染病
    var crbDiaTime_show = $("#crbDiaTime_show"); // 11.传染病诊断日期
    dateShow(crbDiaTime_show); // 传染病诊断日期
    // ==========================================================
    // 12.过敏
    var gmEnterTime_show = $("#gmEnterTime_show"); // 12.过敏录入日期
    dateShow(gmEnterTime_show); // 过敏录入日期

    // 点击整个form表单的时候进行校验，逻辑：病史各个输入框中如果有数据则日期必填，否则都可以为空
    $("#medicalHistoryForm").click(function() {
        // 7.手术
        if (!isEmpty(opt_Name.val()) && isEmpty(optTime_show.val())) {
            optTime_show.focus().select();
        }
        // 8.血透
        var xtStartReason = $("input:radio[name='xtStartReason']:checked"); // 开始原因
        if (!isEmpty(xtStartReason.val()) && isEmpty(xtStartTime_show.val())) {
            xtStartTime_show.focus().select();
        }
        var xtEndReason = $("input:radio[name='xtEndReason']:checked"); // 结束原因
        if (!isEmpty(xtEndReason.val()) && isEmpty(xtEndTime_show.val())) {
            xtEndTime_show.focus().select();
        }
        // 9.腹透
        var ftEndReason = $("input:radio[name='ftEndReason']:checked"); // 结束原因
        if (!isEmpty(ftEndReason.val()) && isEmpty(ftStartTime_show.val())) {
            ftStartTime_show.focus().select();
        }
        if (!isEmpty(ftEndReason.val()) && isEmpty(ftEndTime_show.val())) {
            ftEndTime_show.focus().select();
        }
        // 10.肾移
        var syzEndReason = $("input:radio[name='syzEndReason']:checked"); // 结束原因
        if (!isEmpty(syzEndReason.val()) && isEmpty(syzStartTime_show.val())) {
            syzStartTime_show.focus().select();
        }
        if (!isEmpty(syzEndReason.val()) && isEmpty(syzEndTime_show.val())) {
            syzEndTime_show.focus().select();
        }
        // 11.传染病
        var crbDiaName = $("input:checkbox[name='crbDiaName']:checked"); // 结束原因
        if (!isEmpty(crbDiaName.val()) && isEmpty(crbDiaTime_show.val())) {
            crbDiaTime_show.focus().select();
        }
        // 12.过敏
        var gmResouce = $("input:radio[name='gmResouce']:checked"); // 结束原因
        if (!isEmpty(gmResouce.val()) && isEmpty(gmEnterTime_show.val())) {
            gmEnterTime_show.focus().select();
        }
    });
});

function dateShow(ele) {
    $(ele).daterangepicker({
        "startDate" : ele.val(),
        "singleDatePicker" : true,
        "autoUpdateInput" : true,
        "showDropdowns" : true,
        "clearBtn" : true,
        "alwaysShowCalendars" : true,
        "locale" : {
            format : "YYYY-MM-DD",
            applyLabel : "确认",
            cancelLabel : "取消",
            daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ]
        }
    });

    $("input[daterangepicker]").daterangepicker({
        "startDate" : "",
        "singleDatePicker" : true,
        "autoUpdateInput" : true,
        "showDropdowns" : true,
        "clearBtn" : true,
        "alwaysShowCalendars" : true,
        "locale" : {
            format : "YYYY-MM-DD",
            applyLabel : "确认",
            cancelLabel : "取消",
            daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ]
        }
    });
}

/** 添加项手术史 */
function showAddOpt(dialogId) {
    var temHtml = "";
    temHtml += '<table class="table tfoot > tr > td" >';
    temHtml += '<input type="hidden"   name="mhrMarkType[' + index + '].mhrMark"   value="opt">';
    temHtml += '<tr><td>手术日期：<input type="text"    onfocus="addDate(this)"   name="mhrMarkType[' + index + '].mhrStartTimeShow" /> </td></tr>';
    temHtml += '<tr><td>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:&nbsp;<input type="text" name="mhrMarkType[' + index
                    + '].mhrStartreasonOrname" /></td></tr>';
    temHtml += '<tr><td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea rows="1" class="form-control"   name="mhrMarkType[' + index
                    + '].mhrRemark"  maxlength="256"></textarea></td><td></td></tr></table>';
    $("#" + dialogId).append(temHtml);
    temHtml = "";
    index++;
}
/** 添加项血透史 */
function showAddXt(dialogId) {
    var temHtml = "";
    temHtml += '<table class="table tfoot > tr > td" >';
    temHtml += '<input type="hidden"   name="mhrMarkType[' + index + '].mhrMark"   value="xt">';
    temHtml += '<tr><td>开始日期：<input type="text"  data-xtStartTime  onfocus="addDate(this)"   name="mhrMarkType[' + index
                    + '].mhrStartTimeShow"  /> </td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">开始原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < xtStartReason.length; i++) {
        var obj = xtStartReason[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'medicalHistoryForm' + obj.value + index
                        + '"  type="radio"  onclick="clickXtStartReason(this);"  value="' + obj.value + '"    name="mhrMarkType[' + index
                        + '].mhrStartreasonOrname" />';
        temHtml += '<label for="' + obj.type + 'medicalHistoryForm' + obj.value + index + '"  class="form-span form-radio-label">' + obj.name
                        + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrStartortherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td>结束日期：<input type="text"  data-xtEndTime  name="mhrMarkType[' + index
                    + '].mhrEndTimeShow"  onfocus="addDate(this)"/> </td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">结束原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < xtEndReason.length; i++) {
        var obj = xtEndReason[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'medicalHistoryForm' + obj.value + index + '" type="radio" value="' + obj.value
                        + '"  name="mhrMarkType[' + index + '].mhrEndreason" onclick="clickXtEndReason(this);" />';
        temHtml += '<label for="' + obj.type + 'medicalHistoryForm' + obj.value + index + '" class="form-span form-radio-label">' + obj.name
                        + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrEndotherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea  class="form-control"  name="mhrMarkType[' + index
                    + '].mhrRemark"  maxlength="256"></textarea></td><td></td></tr></table>';
    $("#" + dialogId).append(temHtml);
    temHtml = "";
    index++;
}
/** 添加腹透史 */
function showAddFt(dialogId) {
    var temHtml = "";
    temHtml += '<table class="table tfoot > tr > td" >';
    temHtml += '<input type="hidden"   name="mhrMarkType[' + index + '].mhrMark"   value="ft">';
    temHtml += '<tr><td>开始日期：<input type="text"  name="mhrMarkType[' + index
                    + '].mhrStartTimeShow"  data-ftStartTime  onfocus="addDate(this)" /> </td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">开始原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < ftStartReason.length; i++) {
        var obj = ftStartReason[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'medicalHistoryForm' + obj.value + index
                        + '"  type="radio"  onclick="clickXtStartReason(this);"  value="' + obj.value + '"    name="mhrMarkType[' + index
                        + '].mhrStartreasonOrname" />';
        temHtml += '<label for="' + obj.type + 'medicalHistoryForm' + obj.value + index + '"  class="form-span form-radio-label">' + obj.name
                        + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrStartortherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td>结束日期：<input type="text"  name="mhrMarkType[' + index
                    + '].mhrEndTimeShow" data-ftEndTime  onfocus="addDate(this)" /> </td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">结束原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:forEach var="obj" items="${ftEndReason}"><div class="box-style">';
    for (var i = 0; i < ftEndReason.length; i++) {
        var obj = ftEndReason[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'ftEndReason' + obj.value + index + '" type="radio" value="' + obj.value
                        + '"  name="mhrMarkType[' + index + '].mhrEndreason" onclick="clickXtEndReason(this);" />';
        temHtml += '<label for="' + obj.type + 'ftEndReason' + obj.value + index + '" class="form-span form-radio-label">' + obj.name
                        + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrEndotherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea id="ft_Remark" class="form-control"  name="mhrMarkType[' + index
                    + '].mhrRemark"  maxlength="256"></textarea></td><td></td></tr></table>';
    $("#" + dialogId).append(temHtml);
    index++;
}
/** 添加肾移植透史 */
function showAddSyz(dialogId) {
    var temHtml = "";
    temHtml += '<table class="table tfoot > tr > td" >';
    temHtml += '<input type="hidden"   name="mhrMarkType[' + index + '].mhrMark"   value="syz">';
    temHtml += '<tr><td>开始日期：<input type="text"  data-syzStartTime  name="mhrMarkType[' + index
                    + '].mhrStartTimeShow"  onfocus="addDate(this)"  /> </td></tr>';
    temHtml += '<tr><td>结束日期：<input type="text"  data-syzEndTime  name="mhrMarkType[' + index
                    + '].mhrEndTimeShow"  onfocus="addDate(this)" /> </td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">结束原因：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < syzEndReason.length; i++) {
        var obj = syzEndReason[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'syzEndReason' + obj.value + index + '" type="radio" value="' + obj.value
                        + '"  name="mhrMarkType[' + index + '].mhrEndreason" onclick="clickXtEndReason(this);" />';
        temHtml += '<label for="' + obj.type + 'syzEndReason' + obj.value + index + '" class="form-span form-radio-label">' + obj.name
                        + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrEndotherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea  class="form-control"  name="mhrMarkType[' + index
                    + '].mhrRemark"  maxlength="256"></textarea></td><td></td></tr></table>';
    $("#" + dialogId).append(temHtml);
    var temHtml = "";
    index++;
}
/** 添加过敏史 */
function showAddGm(dialogId) {
    var temHtml = "";
    temHtml += '<table class="table tfoot > tr > td" >';
    temHtml += '<input type="hidden"   name="mhrMarkType[' + index + '].mhrMark"   value="gm">';
    temHtml += '<tr><td>录入日期：<input type="text"  name="mhrMarkType[' + index + '].mhrStartTimeShow"  onfocus="addDate(this)" /> </td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">过敏源：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < gmResouce.length; i++) {
        var obj = gmResouce[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'gmReason' + obj.value + index + '" type="radio" value="' + obj.value
                        + '"  name="mhrMarkType[' + index + '].mhrEndreason" onclick="clickXtStartReason(this);" />';
        temHtml += '<label for="' + obj.type + 'gmReason' + obj.value + index + '" class="form-span form-radio-label">' + obj.name + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrEndotherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:&nbsp;<input type="text" id="gm_Name" name="mhrMarkType[' + index
                    + '].mhrStartreasonOrname" /></td></tr>'
    temHtml += '<tr><td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea  class="form-control"  name="mhrMarkType[' + index
                    + '].mhrRemark"  maxlength="256"></textarea></td><td></td></tr></table>';
    $("#" + dialogId).append(temHtml);
    temHtml = "";
    index++;
}
/** 添加传染病史 */
function showAddCrb(dialogId) {
    var temHtml = "";
    temHtml += '<table class="table tfoot > tr > td" >';
    temHtml += '<input type="hidden"   name="mhrMarkType[' + index + '].mhrMark"   value="crb">';
    temHtml += '<tr><td>诊断日期：<input type="text"  name="mhrMarkType[' + index + '].mhrStartTimeShow"  onfocus="addDate(this)" /> </td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">诊断名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < bs_crbzdmc.length; i++) {
        var obj = bs_crbzdmc[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'crbReason' + obj.value + index + '" type="checkbox" value="' + obj.value
                        + '"  name="mhrMarkType[' + index + '].mhrStartreasonOrname" onchange="clickCrbStartReason(this);" />';
        temHtml += '<label for="' + obj.type + 'crbReason' + obj.value + index + '" class="form-span form-radio-label">' + obj.name
                        + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrStartortherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td class="form-item td-radio-margin">活动状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < bs_crbhdzt.length; i++) {
        var obj = bs_crbhdzt[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'crbHdzt' + obj.value + index + '" type="radio" value="' + obj.value
                        + '"  name="mhrMarkType[' + index + '].activityStatus" onclick="clickXtEndReason(this);" />';
        temHtml += '<label for="' + obj.type + 'crbHdzt' + obj.value + index + '" class="form-span form-radio-label">' + obj.name + '</label></div>';
    }
    temHtml += '<tr><td class="">治疗情况：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    for (var i = 0; i < bs_crbzlqk.length; i++) {
        var obj = bs_crbzlqk[i];
        temHtml += '<div class="box-style"><input id="' + obj.type + 'crbZlqk' + obj.value + index + '" type="radio" value="' + obj.value
                        + '"  name="mhrMarkType[' + index + '].mhrEndreason" onclick="clickCrbZlqk(this);" />';
        temHtml += '<label for="' + obj.type + 'crbZlqk' + obj.value + index + '" class="form-span form-radio-label">' + obj.name + '</label></div>';
    }
    temHtml += '<div class="form-group textarea-margin other hide"><textarea rows="1" class="form-control" name="mhrMarkType[' + index
                    + '].mhrEndotherreason" maxlength="64"></textarea></div>';
    temHtml += '</td></tr>';
    temHtml += '<tr><td>备 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:<textarea  class="form-control"  name="mhrMarkType[' + index
                    + '].mhrRemark"  maxlength="256"></textarea></td><td></td></tr></table>';
    $("#" + dialogId).append(temHtml);
    temHtml = "";
    index++;
}

// 血透开始、过敏源原因==================================================
function clickXtStartReason(dom) {
    var va = $(dom).val();
    var textArea = $(dom).parent().parent().find("div:eq(4)");
    if (va == "00") {
        textArea.attr("class", "form-group textarea-margin other");
    } else {
        textArea.attr("class", "form-group textarea-margin other hide");
    }
}
// 血透、腹透、肾移植结束原因、传染病活动状态===================================
function clickXtEndReason(dom) {
    var va = $(dom).val();
    var textArea = $(dom).parent().parent().find("div:eq(2)");
    if (va == "00") {
        textArea.attr("class", "form-group textarea-margin other");
    } else {
        textArea.attr("class", "form-group textarea-margin other hide");
    }
}
// 传染病诊断名称========================================================
function clickCrbStartReason(dom) {
    var va = $(dom);
    var textArea = $(dom).parent().parent().find("div:eq(6)");
    // 获取当前节点的name
    var obj = document.getElementsByName(va.attr("name"));
    // 获取复选的内容
    var check_val = [];
    for (k in obj) {
        if (obj[k].checked)
            check_val.push(obj[k].value);
    }
    if (check_val.indexOf("00") != -1) {
        textArea.attr("class", "form-group textarea-margin other");
    } else {
        textArea.attr("class", "form-group textarea-margin other hide");
    }
}
// 传染病治疗情况==============================================
function clickCrbZlqk(dom) {
    var va = $(dom).val();
    var textArea = $(dom).parent().parent().find("div:eq(3)");
    if (va == "00") {
        textArea.attr("class", "form-group textarea-margin other");
    } else {
        textArea.attr("class", "form-group textarea-margin other hide");
    }
}
function checkTime(dom) {
    // 血透时间校验
    var xtEndTime = $("input[data-xtEndTime]").val();
    var xtStartTime = $("input[data-xtStartTime]").val();
    if (!isEmpty(xtStartTime)) {
        if (xtEndTime < xtStartTime) {
            showWarn("血透史结束日期不能小于开始日期");
            return;
        }
    }
    // 腹透时间校验
    var ftEndTime = $("input[data-ftEndTime]").val();
    var ftStartTime = $("input[data-ftStartTime]").val();
    if (!isEmpty(ftStartTime)) {
        if (ftEndTime < ftStartTime) {
            showWarn("腹透史结束日期不能小于开始日期");
            return;
        }
    }
    // 肾移植时间校验
    var syzEndTime = $("input[data-syzEndTime]").val();
    var syzStartTime = $("input[data-syzStartTime]").val();
    if (!isEmpty(syzStartTime)) {
        if (syzEndTime < syzStartTime) {
            showWarn("肾移植史结束日期不能小于开始日期");
            return;
        }
    }
    if (checkMedicalHistory())
        buttonSubmit(dom);
}

function historyRemark() {
    // 有无脑血管史
    var hasCavRemark = $("#hasCavRemark").find('input:radio:checked').val();
    if (hasCavRemark == "true" || hasCavRemark == 1) {
        $("#hasCavRemarkText").css("display", "block");
    } else if (hasCavRemark == "false") {
        $("#hasCavRemarkText").css("display", "none");
    }
}

function historyRemarkA() {
    // 有无外周血管疾病史
    var vascularDisease = $("#vascularDisease").find('input:radio:checked').val();
    if (vascularDisease == "true" || vascularDisease == 1) {
        $("#vascularDiseaseText").css("display", "block");
    } else {
        $("#vascularDiseaseText").css("display", "none");
    }
}
function historyRemarkB() {
    // 有无精神病史？
    var haspsychosisRemark = $("#haspsychosisRemark").find('input:radio:checked').val();
    if (haspsychosisRemark == "true" || haspsychosisRemark == 1) {
        $("#haspsychosisRemarkRemarkText").css("display", "block");
    } else {
        $("#haspsychosisRemarkRemarkText").css("display", "none");
    }
}

/* 设置时间 */
function addDate(dom) {
    $(dom).daterangepicker({
        "singleDatePicker" : true,
        "showDropdowns" : true,
        "autoUpdateInitInput" : false,
        "locale" : {
            format : "YYYY-MM-DD"
        }
    });
}
