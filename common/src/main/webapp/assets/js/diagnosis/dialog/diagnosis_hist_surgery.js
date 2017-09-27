$(function() {
    getDialogStyle({
        id : "diagnosisHistSurgeryDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistSurgeryValidate();// 添加校验规则
});

/** 显示手术史dialog */
function showDiagnosisHistSurgeryDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $('#diagnosisHistSurgeryForm').validate().resetForm();
                    resetFormAndClearHidden("diagnosisHistSurgeryForm");// 表单重置
                    mappingFormData(data.item, "diagnosisHistSurgeryForm");
                    $("#diagnosisHistSurgery_patientId").val(data.patient.id);

                    setDialogTitle("diagnosisHistSurgeryDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                    $("#diagnosisHistSurgeryDialog").modal("show");
                }
            }
            return false;
        }
    });
    return false;
}
// 保存
function saveDiagnosisHistSurgery(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveHistSurgery.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#diagnosisHistSurgeryDialog").modal("hide");
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
function addDiagnosisHistSurgeryValidate() {
    $('#diagnosisHistSurgeryForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            surgeryDateForm : {
                required : [ "手术日期" ]
            },
            surgeryName : {
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