/** dialog key 和 dialog名称的映射关系 */
var dialogMapper = {
    "beforeWeight" : "showBeforeWeightDialog",
    "evaluate" : "showEvaluateDialog",
    "prescription" : "showDialysisPrescriptionDialog",
    "confirm" : "showConfirmDialog",
    "check" : "showDialysisCheckDialog",
    "process" : "showProcessRecordDialog",
    "result" : "showDialysisResultDialog",
    "afterWeight" : "showAfterWeightDialog",
    "summary" : "showDialysisSummaryDialog",
    "complication" : "showComplicationDialog",
    "complicationPathWay" : "complicationPathWayDialog",
    "createMO" : "showCreateMODialog",
    "executeMO" : "showExecuteMODialog",
    "exception" : "showExceptionDialog",
    "stop" : "showDialysisStopDialog",
    "crossCheck" : "showCrossCheckDialog",
    "stageSummary" : "showStageSummaryDialog",
    "afterException" : "showDialysisAfterExceptionDialog",
    "cancel" : "showDialysisCancelDialog",
    "nurseEvaluation" : "showNurseEvaluationDialog",
    "jsNurseEvaluation" : "showJsNurseEvaluationDialog",
    "specialCare" : "special_care_dialog.show",
    "diagnosisHistFirstDialysis" : "showDiagnosisHistFirstDialysisDialog",
    "diagnosisHistSurgery" : "showDiagnosisHistSurgeryDialog",
    "diagnosisHistHd" : "showDiagnosisHistHdDialog",
    "diagnosisHistPd" : "showDiagnosisHistPdDialog",
    "diagnosisHistKt" : "showDiagnosisHistKtDialog",
    "diagnosisHistAllergy" : "showDiagnosisHistAllergyDialog",
    "diagnosisHistPestilence" : "showDiagnosisHistPestilenceDialog",
    "diagnosisHistTumour" : "showDiagnosisHistTumourDialog",
    "label" : "loadPatientEdit"
};
$(function() {
    $(".dialog-extend2").off("click").on("click", function() {
        if ($(this).hasClass("open-img")) {
            $(this).removeClass("open-img").addClass("extend-img");
            $(this).parent().next().hide();
        } else {
            $(this).removeClass("extend-img").addClass("open-img");
            $(this).parent().next().show();
        }
    });
});

/**
 * dialog显示 默认用dcId,如果没有dcId，再用patientId
 * 
 * @param dcId
 * @param patientId
 * @param dialogType
 *            dialog的id名字
 * @param functionName
 *            回调函数
 */
function showCommonDialog(dcId, patientId, dialogType, functionName) {
    if (isFromIframe()) {
        $(".modal[role='dialog']").css("margin-top", "30px");
    }
    if (!isEmpty(functionName)) {
        $("body").data("dialogFunctionName", functionName);
    }
    for (var key in dialogMapper) {
        if (key == dialogType) {
            eval(dialogMapper[key] + "('" + dcId + "','" + patientId + "');");
            return;
        }
    }
}
/** dialog回调 */
function commonDialogCallback(param) {
    // if (isExitsFunction("parent.getProcessCount")) {
    // parent.getProcessCount();
    // }
    // showTips("保存成功");
    var functionName = $("body").data("dialogFunctionName");
    if (!isEmpty(functionName)) {
        eval(functionName + "('" + (isEmpty(param) ? "" : param) + "')");
    } else {
        window.location.reload(true);
    }
}

/** 是否存在指定函数 */
function isExitsFunction(funcName) {
    try {
        if (typeof (eval(funcName)) == "function") {
            return true;
        }
    } catch (e) {}
    return false;
}

/** 获取包含error的组件 */
function getValidateErrorObj(obj, index) {
    if (isEmpty(obj) || obj.length == 0) {
        return obj;
    }
    index = isEmpty(index) ? 0 : (++index);
    if (index == 20) {
        return obj;
    }
    if (obj.find("[data-error]").length > 0) {
        return obj;
    } else {
        return getValidateErrorObj(obj.parent(), index);
    }
}

// 显示选中的图片
function getImage(formId, operator, imagePath, realImagePath) {
    var index = $("#" + formId + " div[data-image]").data("index");
    index = isEmpty(index) ? 0 : ++index;
    var fileId = formId + 'upload_file' + index;
    var divShowId = formId + 'upload_fileDiv' + index;
    var imgShowId = formId + 'upload_fileShow' + index;

    var addHtml = '<div style="display: none;position: relative;" id="' + divShowId + '" data-add="false">';
    addHtml += ' <input type="file" style="display: none;" accept="image/*;capture=camera" id="' + fileId + '">';
    if ("add" == operator) {
        addHtml += '<img id="' + imgShowId + '" class="photo">';
    } else {
        var imgSrc = ctx + '/common/showImage.shtml?fileName=' + imagePath;
        addHtml += '<img id="' + imgShowId + '" class="photo" src="' + imgSrc + '">';
    }

    addHtml += '<img class="delete-icon" src="' + ctx + '/assets/img/delete.png" onclick="delUploadImage(this);">';
    addHtml += '</div>';
    $("#" + formId + " div[data-image]").append(addHtml);

    if ("add" == operator) {
        new uploadPreview({
            UpBtn : fileId,
            DivShow : divShowId,
            ImgShow : imgShowId,
            Width : $("#" + imgShowId).width(),
            Height : $("#" + imgShowId).height(),
        });
        $("#" + fileId).on("change", function() {
            $(this).parent().css("display", "inline-block");
            $(this).parent().attr("data-add", true);
            $(this).attr("name", "files");
        });
        $("#" + fileId).trigger("click");
    } else {
        $("#" + fileId).parent().css("display", "inline-block");
        $("#" + fileId).parent().attr("data-add", true);

        var imageHtml = '<input type="hidden" name="imagePath" value="' + imagePath + '"/>';
        imageHtml += '<input type="hidden" name="realImagePath" value="' + realImagePath + '"/>';
        $("#" + fileId).parent().append(imageHtml);
    }
    $("#" + formId + " div[data-image]").data("index", index);
}

/** 删除添加的图片 */
function delUploadImage(element) {
    $(element).parent().remove();
}

/**
 * 设置dialog的title内容
 * 
 * @param dialogId
 * @param sickbedRecord
 */
function setDialogTitle(dialogId, data) {
    if (isEmptyObject(data)) {
        return false;
    }

    if (data.patient) {
        $("#" + dialogId + " .user-photo").attr("src", ctx + "/images" + data.patient.imagePath);
        $("#" + dialogId + " .user-name").html(data.patient.name);
    }

    // 床号：如果在开始活动之前，没有活动，从排床记录获取，如果开始透析活动，从活动表获取
    var sickbedCode = "";
    if (data.sickbed) {
        sickbedCode = data.sickbed.code;
    } else if (data.dc) {
        sickbedCode = data.dc.bedCode;
    }
    if (!isEmpty(data.isShowSickbedCode) && !data.isShowSickbedCode) {
        return;
    }
    $("#" + dialogId + " .user-info").html(sickbedCode + "号床");
}

/**
 * 设置dialog样式.默认值：{margin:30px auto, width:800px, height:535, tabMarginLeft:-200px}。必输属性。dialog的id
 * 
 * @param jsonObj
 */
function getDialogStyle(jsonObj) {
    jsonObj = jsonObj || {};
    var dialogId = jsonObj.id;
    var margin = jsonObj.margin || "-30px auto";
    var width = jsonObj.width || "800px";
    var height = jsonObj.height || 535;
    var tabMarginLeft = jsonObj.tabMarginLeft;
    // 设置新增药品dialog
    // 设置dialog离顶部高度
    $("#" + dialogId).css("margin", margin + " auto");
    // 设置dialog宽度为800px
    $("#" + dialogId + " .modal-dialog").css("width", width);
    // 设置dialog宽度为800px
    $("#" + dialogId + " .dialog-wrap").css("width", "inherit");
    // 设置tab的距离左边的像素
    if (!isEmpty(tabMarginLeft)) {
        $("#" + dialogId + " .dialog-header > .tab").css("margin-left", tabMarginLeft);
    }
    // 设置dialog 内容高度
    $("#" + dialogId + " .modal-content").css("height", height + "px");
    $("#" + dialogId + " .dialog-wrap .list-group").css("height", (height - 118) + "px");
}