$(function() {
    getDialogStyle({
        id : "diagnosisHistFirstDialysisDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistFirstDialysisValidate();// 添加校验规则
});

/** 显示首次透析dialog */
function showDiagnosisHistFirstDialysisDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $('#diagnosisHistFirstDialysisForm').validate().resetForm();
                    resetFormAndClearHidden("diagnosisHistFirstDialysisForm");// 表单重置
                    mappingFormData(data.item, "diagnosisHistFirstDialysisForm");
                    $("#diagnosisHistFirstDialysis_patientId").val(data.patient.id);

                    setDialogTitle("diagnosisHistFirstDialysisDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                    $("#diagnosisHistFirstDialysisDialog").modal("show");
                }
            }
            return false;
        }
    });
    return false;
}
// 保存
function saveDiagnosisHistFirstDialysis(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveHistFirstDialysis.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#diagnosisHistFirstDialysisDialog").modal("hide");
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
function addDiagnosisHistFirstDialysisValidate() {
    $('#diagnosisHistFirstDialysisForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            firstTreatmentDateForm : {
                required : [ "首次透析时间" ]
            },
            firstTreatmentType : {
                required : [ "首次透析类型" ]
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