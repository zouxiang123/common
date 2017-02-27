$(function() {
    // 头像上传
    new uploadPreview({
        UpBtn : "up_img",
        DivShow : "imgdiv",
        ImgShow : "imgShow"
    });

    // 生日选择
    var date = $("#patientForm_birthday").val();
    $.ymd_DatePicker({
        YearSelector : "#YYYY",
        MonthSelector : "#MM",
        DaySelector : "#DD",
        InitDate : isEmpty(date) ? "1960-01-01" : date,
        ResultSelector : "#patientForm_birthday"
    });

    if (!$("#patientForm_male").attr("checked") && !$("#patientForm_female").attr("checked")) {
        $("#patientForm_male").attr("checked", true);
    }

    // 添加校验
    addValidate();

    // 省县级联绑定事件
    $("#province").bind("change", function() {
        $("#county").empty();
        $("#county").append('<option value="0">--</option>');
        $.ajax({
            url : ctx + "/common/getCountyList.shtml",
            data : "provinceId=" + $(this).val(),
            type : "post",
            dataType : "json",
            success : function(data) {// ajax返回的数据
                if (data) {
                    $.each(data, function(n, row) {
                        $("#county").append('<option value="' + row.id + '" >' + row.name + '</option>');
                    });
                }
            }
        });
    });

    $("#patientForm_idType").bind("change", function() {
        changeIdNumber();
    });

    $("#patientForm_id_number").bind("change", function() {
        changeIdNumber();
    });

    $("#patientForm_id_number").blur(function() {
        changeIdNumber();
    });

    $("#patientForm_birthday").blur(function() {
        changeIdNumber();
    });
});

function changeIdNumber() {
    if ($("#patientForm_idType").val() != "1") {
        return;
    }
    var value = $("#patientForm_id_number").val();
    if (isEmpty(value)) {
        return;
    }
    var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
    if (reg.test(value) === false) {
        return;
    }
    var ymd = value.substring(6, 14);
    $("#YYYY").val(ymd.substring(0, 4));
    $("#YYYY").change();
    $("#MM").val(parseInt(ymd.substring(4, 6)));
    $("#MM").change();
    $("#DD").val(parseInt(ymd.substring(6, 8)));
    $("#DD").change();

    $("#province").val(value.substring(0, 2) + "0000");
    // $("#county").val(value.substring(0, 4) + "00");

    $("#county").empty();
    $("#county").append('<option value="0">--</option>');
    $.ajax({

        url : ctx + "/common/getCountyList.shtml",
        data : "provinceId=" + $("#province").val(),
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                $.each(data, function(n, row) {
                    $("#county").append('<option value="' + row.id + '" >' + row.name + '</option>');
                });
                $("#county").val(value.substring(0, 4) + "00");
                if ($("#county").val() == null) {
                    $("#county").val(value.substring(0, 6));
                }
            }
        }
    });
}

$("#up_img").change(function() {
    uploadImg($("#imageUploadForm"));
});

function uploadImg(form) {
    if (isEmpty($("#up_img").val())) {
        showWarn("请选择上传的文件");
        return false;
    }
    var options = {
        dataType : "json",
        url : ctx + "/patient/savePatientImage.shtml?id=" + $("#patientId").val(),
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $("#tempImagePath").val(data.filepath);
                    $("#nav-patient-photo").attr("src", ctx + "/common/showImage.shtml?fileName=" + data.filepath + "&rnd=" + new Date());
                    return false;
                } else if (data.status == 2) {
                    showWarn("请选择上传的文件");
                    return false;
                }
            }
        }
    };
    $(form).ajaxSubmit(options);
    return false;
}

function addValidate() {
    // 身份证号码校验、身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    $.validator.addMethod("isIdNumber", function(value, element, params) {
        if ($("#patientForm_idType").val() != "1") {
            return true;
        }
        if (isEmpty(value)) {
            return false;
        }
        var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
        if (reg.test(value) === false) {
            return false;
        }
        return true;
    }, jQuery.validator.format("请输入正确的证件号码"));
    $('#patientForm').validate({
        onfocusout : false,
        rules : {
            name : {
                required : [ "姓名" ]
            },
            sex : {
                required : [ "性别" ]
            },
            isTemp : {
                required : [ "长期/临时患者" ]
            },
            idNumber : {
                isIdNumber : true,
                required : [ "证件号码" ],
                remote : {
                    url : ctx + "/patient/checkPatientExistByIdNumber.shtml",
                    type : "post",
                    dataType : "json",
                    cache : false,
                    async : false,
                    data : {
                        id : function() {
                            return $("#id").val();
                        },
                        idNumber : function() {
                            return $("#patientForm_id_number").val();
                        }
                    },
                    dataFilter : function(data, type) {
                        if (isEmpty(data)) {
                            return false;
                        }
                        if (type == "json") {
                            data = eval("(" + data + ")");
                        }
                        if (data.exist) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            },
            workUnit : {
                customMaxlength : [ 128, "工作单位" ]
            },
            birthdayShow : {
                required : [ "出生日期" ],
                date : [ "出生日期" ]
            },
            mobile : {
                required : [ "联系方式" ],
                digits : [ "联系方式" ],
                customMaxlength : [ 12, "联系方式" ]
            },
            emergencyMobile : {
                digits : [ "家属联系方式 " ],
                customMaxlength : [ 12, "家属联系方式 " ]
            },
            address : {
                // required : [ "详细地址" ],
                customMaxlength : [ 128, "详细地址" ]
            },
            admissionNumber : {
                isNumberOrLetter : [ "住院号" ],
                customMaxlength : [ 64, "住院号" ]
            },
            medicareCard : {
                isNumberOrLetter : [ "医保卡号" ],
                customMaxlength : [ 64, "医保卡号" ]
            }
        },
        messages : {
            idNumber : {
                remote : "此证件号码已存在，请确认输入是否正确",
                customMinlength : "输入的证件证号码长度应该为18位，请重新输入"
            },
            mobile : {
                customMaxlength : "请输入正确的联系方式",
                digits : "请输入正确的联系方式"
            },
            emergencyMobile : {
                digits : "请输入正确的家属联系方式 ",
                customMaxlength : "请输入正确的家属联系方式 "
            },
        },

        // 全部校验结果
        showErrors : function(errorMap, errorList) {
            showSystemDialog(errorMap);
        },
        submitHandler : function(form) {
            form.onsubmit();
        }
    });
};
