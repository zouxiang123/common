$(function() {
    getDialogStyle({
        id : "diagnosisHistHdDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistHdValidate();// 添加校验规则
    addDiagnosisHistHdEvent();
});
function addDiagnosisHistHdEvent() {
    $("#diagnosisHistHdForm input[type='radio'], #diagnosisHistHdForm input[type='checkbox']").bind("click", function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").val('');
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
    $("#diagnosisHistHdForm input[type='radio']:checked, #diagnosisHistHdForm input[type='checkbox']:checked").each(function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
}
/** 显示血透史dialog */
function showDiagnosisHistHdDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $('#diagnosisHistHdForm').validate().resetForm();
                    resetFormAndClearHidden("diagnosisHistHdForm");// 表单重置
                    mappingFormData(data.item, "diagnosisHistHdForm");
                    // 绑定事件
                    addDiagnosisHistHdEvent();
                    $("#diagnosisHistHd_patientId").val(data.patient.id);

                    setDialogTitle("diagnosisHistHdDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                    $("#diagnosisHistHdDialog").modal("show");
                }
            }
            return false;
        }
    });
    return false;
}
// 保存
function saveDiagnosisHistHd(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveHistHd.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#diagnosisHistHdDialog").modal("hide");
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
function addDiagnosisHistHdValidate() {
    $('#diagnosisHistHdForm').validate({
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