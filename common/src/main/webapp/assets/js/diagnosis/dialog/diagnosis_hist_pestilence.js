$(function() {
    getDialogStyle({
        id : "diagnosisHistPestilenceDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistPestilenceValidate();// 添加校验规则
    addDiagnosisHistPestilenceEvent();
});
function addDiagnosisHistPestilenceEvent() {
    $("#diagnosisHistPestilenceForm input[type='radio'], #diagnosisHistPestilenceForm input[type='checkbox']").bind("click", function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").val('');
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
    $("#diagnosisHistPestilenceForm input[type='radio']:checked, #diagnosisHistPestilenceForm input[type='checkbox']:checked").each(function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
}
/** 显示传染病史dialog */
function showDiagnosisHistPestilenceDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $('#diagnosisHistPestilenceForm').validate().resetForm();
                    resetFormAndClearHidden("diagnosisHistPestilenceForm");// 表单重置
                    mappingFormData(data.item, "diagnosisHistPestilenceForm");
                    // 绑定事件
                    addDiagnosisHistPestilenceEvent();
                    $("#diagnosisHistPestilence_patientId").val(data.patient.id);

                    setDialogTitle("diagnosisHistPestilenceDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                    $("#diagnosisHistPestilenceDialog").modal("show");
                }
            }
            return false;
        }
    });
    return false;
}
// 保存
function saveDiagnosisHistPestilence(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveHistPestilence.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#diagnosisHistPestilenceDialog").modal("hide");
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
function addDiagnosisHistPestilenceValidate() {
    $('#diagnosisHistPestilenceForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            diagnosticDateForm : {
                required : [ "诊断日期" ]
            },
            diagnosticName : {
                required : [ "诊断名称" ]
            },
            activityState : {
                required : [ "活动状态" ]
            },
            treatment : {
                required : [ "治疗情况" ]
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