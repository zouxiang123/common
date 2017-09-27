$(function() {
    getDialogStyle({
        id : "diagnosisHistKtDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistKtValidate();// 添加校验规则
    addDiagnosisHistKtEvent();
});
function addDiagnosisHistKtEvent() {
    $("#diagnosisHistKtForm input[type='radio'], #diagnosisHistKtForm input[type='checkbox']").bind("click", function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").val('');
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
    $("#diagnosisHistKtForm input[type='radio']:checked, #diagnosisHistKtForm input[type='checkbox']:checked").each(function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
}
/** 显示肾移植史dialog */
function showDiagnosisHistKtDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $('#diagnosisHistKtForm').validate().resetForm();
                    resetFormAndClearHidden("diagnosisHistKtForm");// 表单重置
                    mappingFormData(data.item, "diagnosisHistKtForm");
                    // 绑定事件
                    addDiagnosisHistKtEvent();
                    $("#diagnosisHistKt_patientId").val(data.patient.id);

                    setDialogTitle("diagnosisHistKtDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                    $("#diagnosisHistKtDialog").modal("show");
                }
            }
            return false;
        }
    });
    return false;
}
// 保存
function saveDiagnosisHistKt(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveHistKt.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#diagnosisHistKtDialog").modal("hide");
                        commonDialogCallback();
                    }
                }
                return false;
            },
            error : function() {
            }
        };
        $(form).ajaxSubmit(options);
        return false;
    }
}
function addDiagnosisHistKtValidate() {
    $('#diagnosisHistKtForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            startDateForm : {
                required : [ "开始日期" ]
            },
            endDateForm : {
                required : [ "结束日期" ]
            },
            endReason : {
                required : [ "结束原因" ]
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
            $(error["0"]).css("margin-right", "6px");
            obj.find("[data-error]").append(error);
        }
    });
}