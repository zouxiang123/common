$(function() {
    getDialogStyle({
        id : "diagnosisHistAllergyDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistAllergyValidate();// 添加校验规则
    addDiagnosisHistAllergyEvent();
});
function addDiagnosisHistAllergyEvent() {
    $("#diagnosisHistAllergyForm input[type='radio'], #diagnosisHistAllergyForm input[type='checkbox']").bind("click", function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").val('');
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
    $("#diagnosisHistAllergyForm input[type='radio']:checked, #diagnosisHistAllergyForm input[type='checkbox']:checked").each(function() {
        if ($(this).val() == '00') {
            $(this).parent().parent().find("textarea").addClass('show').removeClass('hide');
        } else {
            $(this).parent().parent().find("textarea").addClass('hide').removeClass('show');
        }
    });
}
/** 显示过敏史dialog */
function showDiagnosisHistAllergyDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $('#diagnosisHistAllergyForm').validate().resetForm();
                    resetFormAndClearHidden("diagnosisHistAllergyForm");// 表单重置
                    mappingFormData(data.item, "diagnosisHistAllergyForm");
                    // 绑定事件
                    addDiagnosisHistAllergyEvent();
                    $("#diagnosisHistAllergy_patientId").val(data.patient.id);

                    setDialogTitle("diagnosisHistAllergyDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                    $("#diagnosisHistAllergyDialog").modal("show");
                }
            }
            return false;
        }
    });
    return false;
}
// 保存
function saveDiagnosisHistAllergy(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveHistAllergy.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#diagnosisHistAllergyDialog").modal("hide");
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
function addDiagnosisHistAllergyValidate() {
    $('#diagnosisHistAllergyForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            inputDateForm : {
                required : [ "录入日期" ]
            },
            allergens : {
                required : [ "过敏源" ]
            },
            name : {
                required : [ "名称" ]
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