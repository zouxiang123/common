$(function() {
    getDialogStyle({
        id : "diagnosisHistPdDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistPdValidate();// 添加校验规则
    addDiagnosisHistPdEvent();
});
function addDiagnosisHistPdEvent() {
    $("#diagnosisHistPdForm input[type='radio'], #diagnosisHistPdForm input[type='checkbox']").bind("click", function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").val('');
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
    $("#diagnosisHistPdForm input[type='radio']:checked, #diagnosisHistPdForm input[type='checkbox']:checked").each(function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
}
/** 显示腹透史dialog */
function showDiagnosisHistPdDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $('#diagnosisHistPdForm').validate().resetForm();
                    resetFormAndClearHidden("diagnosisHistPdForm");// 表单重置
                    mappingFormData(data.item, "diagnosisHistPdForm");
                    // 绑定事件
                    addDiagnosisHistPdEvent();
                    $("#diagnosisHistPd_patientId").val(data.patient.id);

                    setDialogTitle("diagnosisHistPdDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                    $("#diagnosisHistPdDialog").modal("show");
                }
            }
            return false;
        }
    });
    return false;
}
// 保存
function saveDiagnosisHistPd(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveHistPd.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#diagnosisHistPdDialog").modal("hide");
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
function addDiagnosisHistPdValidate() {
    $('#diagnosisHistPdForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            startDateForm : {
                required : [ "开始日期" ]
            },
            startReason : {
                required : [ "开始原因" ]
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