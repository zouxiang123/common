$(function() {
    getDialogStyle({
        id : "diagnosisHistTumourDialog"
    });// 设置增加透析dialog样式
    addDiagnosisHistTumourValidate();// 添加校验规则
    // 事件初始化
    // 初始化日历控件
    $("#diagnosisHistTumourForm").find("input[name='recordDateShow']").daterangepicker({
        "singleDatePicker" : true,
        "showDropdowns" : true,
        autoUpdateInitInput : false,
        "locale" : {
            format : "YYYY-MM-DD"
        }
    });
});

/**
 * 显示肿瘤史dialog
 * 
 * @param id
 *            记录id
 * @param patientId
 *            患者id
 * @param diagnosisType
 *            诊断类别
 * @returns {Boolean}
 */
function showDiagnosisHistTumourDialog(id, patientId, diagnosisType) {
    $.ajax({
        url : ctx + "/patient/diagnosis/search.shtml",
        data : "patientId=" + patientId + "&diagnosisType=" + diagnosisType + "&id=" + id,
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data.status == 1) {
                $('#diagnosisHistTumourForm').validate().resetForm();
                resetFormAndClearHidden("diagnosisHistTumourForm");// 表单重置
                mappingFormData(data.item, "diagnosisHistTumourForm");
                $("#diagnosisHistTumour_patientId").val(data.patient.id);
                $("#diagnosisHistTumour_patientImage").attr("src", ctx + "/images/" + data.patient.imagePath);

                setDialogTitle("diagnosisHistTumourDialog", data);// 设置dialog标题。包括患者头像、名字、病区床号
                $("#diagnosisHistTumourDialog").modal("show");
            }
            return false;
        }
    });
    return false;
}
/**
 * 保存肿瘤史
 * 
 * @param form
 *            表单组件
 * @returns {Boolean}
 */
function saveDiagnosisHistTumour(form) {
    if ($(form).valid()) {
        $.ajax({
            url : ctx + "/patient/diagnosis/saveHistTumour.shtml",
            data : $(form).serialize(),
            type : "post",
            dataType : "json",
            loading : true,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data.status == 1) {
                    $("#diagnosisHistTumourDialog").modal("hide");
                    commonDialogCallback();
                } else if (data.status == '2') {
                    showWarn("保存的记录已被删除，请刷新后重试");
                }
                return false;
            }
        });
        return false;
    }
}
/**
 * 添加肿瘤史dialog校验
 */
function addDiagnosisHistTumourValidate() {
    $('#diagnosisHistTumourForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            recordDateShow : {
                required : [ "诊断日期" ]
            },
            recordType : {
                required : [ "肿瘤类别" ]
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