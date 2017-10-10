var formsData = {};

$(document).ready(function() {
    // get all forms data when init
    $("form").each(function() {
        formsData[$(this).attr('id')] = $(this).serialize();
    });
});

/**
 * 
 * @param form
 *            提交的form组件
 * @param nextPage
 *            跳转的页面地址，如果是tab，写tabId，如果是页面跳转，写跳转地址。如：（/doctor/newPatient.shtml）
 * @param isTab
 *            区分是否是tab
 * @param processId
 *            流程id
 * @returns {Boolean}
 */
function formSubmit(form, nextPage, isTab) {
    var formId = $(form).attr("id");
    var fkDialysisCampaignId = $("#fkDialysisCampaignId").val();
    $("#" + formId + " input[type='hidden']").each(function() {
        var inputValue = "";
        var isCheckbox = false;
        var hiddenId = $(this).attr("id");
        $("#" + formId + " input[type='checkbox']").each(function() {
            if ($(this).attr("id").indexOf(hiddenId + formId) != -1) {
                if ($(this).is(":checked")) {
                    inputValue += $(this).val() + ",";
                }
                isCheckbox = true;
            }
        });
        if (inputValue.length > 0) {
            $(this).val(inputValue.substring(0, inputValue.length - 1));
        } else {
            if (isCheckbox) {
                $(this).val(inputValue);
            }
        }
    });
    var formData = $(form).serialize();
    if (!isEmpty(formData) && formsData[formId] != formData) {// 没有数据变化时，不更新
        postForm(formData, formId, fkDialysisCampaignId, nextPage, isTab, form);
    } else {
        var recordId = $("#" + formId + "Id").val();
        if (isEmpty(recordId)) {
            postForm(formData, formId, fkDialysisCampaignId, nextPage, isTab, form);
        } else {
            gotoPage(nextPage, isTab);
        }
    }
    return false;
}
/** 提交表单数据 */
function postForm(formData, formId, fkDialysisCampaignId, nextPage, isTab, form) {
    var actionValue = "save" + formId.substr(0, 1).toUpperCase() + formId.substring(1, formId.indexOf("Form"));
    $.ajax({
        url : ctx + "/patient/diagnosis/" + actionValue + ".shtml",
        data : formData + (isEmpty(fkDialysisCampaignId) ? "" : "&fkDialysisCampaignId=" + fkDialysisCampaignId),
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $("#" + formId + "Id").val(data.id);
                    if (!isEmpty(data.fkPathwayId)) {
                        $("input[name='fkPathwayId']").val(data.fkPathwayId);
                    }
                    if (!isEmpty(data.fkPathwayActivityId)) {
                        $("input[name='fkPathwayActivityId']").val(data.fkPathwayActivityId);
                    }
                    $("#" + formId + "ParentId").val(data.parentId);
                    $("#" + formId + "PAId").val(data.paId);
                    formsData[formId] = $("#" + formId).serialize();
                    gotoPage(nextPage, isTab);
                } else if (data.status == 2) {
                    showWarn("信息已被他人修改！请刷新后重试^_^");
                }
            }
        }
    });
}

function gotoPage(nextPage, isTab) {
    if (!isEmpty(nextPage)) {
        if (isTab == true) {
            tabSwitch('contentBarId', nextPage);
        } else {
            goBack();
        }
    }
}