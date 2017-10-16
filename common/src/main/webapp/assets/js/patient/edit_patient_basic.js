window.cardFlag = 1;// 卡号验证是否正确，默认是1
var patient_edit = {
    card_index : 0,
    init : function() {
        // 如果不是新增患者，删除查询按钮
        if (!isEmpty($('#id').val())) {
            // $("#queryPatientInfo").remove();// 隐藏查询按钮
        }
        this.card_index = $("#patientCardTable").find("tr[data-trid]").length;
        this.addEvents();
        // 触发默认的患者卡号类型切换事件，修复如果卡号类别默认为ID的情况没有显示raido的问题
        $("#patientCardDefaultTr").find("[data-cardType]").trigger("change");
    },
    addEvents : function() {
        // 删除按钮点击事件
        $("#patientCardTable").on("click", "[data-remove]", function() {
            // 如果是删除已存在的数据，标识delFlag为1,否则移除当前记录
            var trEl = $(this).parent().parent();
            if (!isEmpty($(this).data("remove"))) {
                trEl.addClass("hide");
                trEl.find("input[data-delFlag]").val(1);
            } else {
                trEl.remove();
            }
            cardFlag = 3;// 验证通过；
        });
        // 是否为最新项的点击事件
        $("#patientCardTable").on("click", "input[data-newFlag]", function() {
            // 当前项是否选中标识
            var isChecked = $(this).is(":checked");
            var trEl = $(this).parent().parent().parent();
            if (isChecked && isEmpty(trEl.find("input[data-cardNo]").val().trim())) {
                showWarn("卡号为空时不能作为默认卡号");
                return false;
            }
            // 取消所有项目最新标识的选中状态
            $("#patientCardTable").find("input[data-newFlag]").prop("checked", false);
            // 设置值到当前元素
            $(this).prop("checked", isChecked);
        });
        // 卡号类别变更
        $("#patientCardTable").on("change", "[data-cardType]", function() {
            var val = $(this).val();
            var trEl = $(this).parent().parent();
            if (val == '03') {
                trEl.find("[data-newFlagLabel]").removeClass("hide");
            } else {
                trEl.find("[data-newFlagLabel]").addClass("hide");
                trEl.find("input[data-newFlag]").prop("checked", false);
            }
        });

        // 卡号focus事件,缓存当前的值
        $("#patientCardTable").on("focus", "[data-cardNo]", function() {
            $(this).data("cacheval", $(this).val());
        });

        // 卡号失去焦点事件,如果当前卡号被默认为最新的卡号，则卡号不能为空
        $("#patientCardTable").on("blur", "[data-cardNo]", function() {
            var trEl = $(this).parent().parent();
            var val = trEl.find("[data-cardType]").val();
            if (val == '03') {
                var newFlag = trEl.find("input[data-newFlag]").is(":checked");
                if (newFlag && isEmpty($(this).val())) {
                    showWarn("当前卡号作为默认卡号时，不能为空");
                    // 还原值为输入之前的
                    $(this).val($(this).data("cacheval"));
                    return false;
                }
            }
        });
        // 添加患者卡号信息
        $("#addPatientCard").on("click", function() {
            /* // 新增的时候将是否调用接口设为空
             $("#checkedStatus").val("");*/
            patient_edit.card_index++;
            var $defaultTr = $("#patientCardDefaultTr");
            var cardType = $defaultTr.find("[data-cardType]").val();
            var tr = $("#patientCardDefaultTr").clone();
            // jquey clone lost select val，set it
            tr.find("[data-cardType]").val(cardType);
            // 新增的时候显示没有验证的卡号标志
            if (!isEmpty(paramVal)) {
                var cardTypeText = $("#cardTypeId").find("option:selected").text();
                // 首先要判断卡号类型是不是属于要验证的范围,比如后端admin登录之后，在系统参数表中，有没有配置门诊号，住院号之类的要验证
                if (!isEmpty(paramVal) && paramVal.indexOf(cardTypeText) != -1) {
                    // 在点击加号之前要先判断是否拉取一下接口，拉取过了并且验证通过了，才可以继续新增，否则不允许新增
                    if (cardFlag != 3 && !isEmpty(tr.find("input[data-cardNo]").val().trim())) {
                        showWarn("请先验证卡号");
                        return false;
                    }

                }
                cardFlag = 1;// 未验证
                $("#validCardNoDiv").html("<font style='color:#F8C730;'>卡号未验证</font>");

            } else {
                $("#validCardNoDiv").hide();
                cardFlag = 3;// 直接通过
                $("#queryPatientInfo").css("margin-top", "0");
                $("#addPatientCard").css("margin-top", "0");
            }
            if (isEmpty(tr.find("input[data-cardNo]").val().trim())) {
                showWarn("请填写卡号");
                return false;
            }
            tr.find("[data-clonereplace]").each(function() {
                var name = $(this).attr("name").replace("[0]", "[" + patient_edit.card_index + "]");
                $(this).attr("name", name);
            });
            // 删除查询和新增按钮
            tr.find("[data-query],[data-add]").remove();
            tr.find("[data-remove]").removeClass("hide");
            $("#patientCardTable").append(tr);
            $defaultTr.find("input[data-cardNo]").val("");
            // 清除的选中状态
            $defaultTr.find("input[data-newFlag]").prop("checked", false);
        });

        // 调用接口查询患者数据
        $("#queryPatientInfo").on("click", function() {

            // 判断是否已经验证
            $("#checkedStatus").val(1);
            var trEl = $(this).parent().parent();
            var cardTypeValue = trEl.find("select[data-cardType]").val();
            // $("#ddlregtype").find("option:selected").text();
            // 获取选中的下拉框的文本
            var cardTypeText = trEl.find("select[data-cardType]").find("option:selected").text();
            var cardNo = trEl.find("input[data-cardNo]").val();
            if (isEmpty(cardNo)) {
                showError("“卡号”不能为空");
                return false;
            }
            var name = $("#patientForm_name").val();
            $.ajax({
                url : ctx + "/patient/getWsPatientInfo.shtml",
                data : "cardNo=" + cardNo + "&cardType=" + cardTypeValue,
                type : "post",
                dataType : "json",
                loading : true,
                success : function(data) {// ajax返回的数据
                    if (data.wsdlStatus == 2) {
                        $("#validCardNoDiv").html("");
                        cardFlag = 3;// 接口不通的时候，去掉了验证，可以直接手动录入
                        return true;
                    } else {

                        if (data) {
                            var pt = data.patient;
                            var province = data.provinceList;
                            var county = data.countyList;
                            if (pt != null) {
                                if (isEmpty(pt.name)) {
                                    // 验证通不通过的标志
                                    // showError("<font color='red'>" + cardTypeText + "</font>&nbsp;<font color='red'>" + cardNo +
                                    // "</font>&nbsp;不存在!");
                                    $("#validCardNoDiv").html("<font style='color:#F7587A;'>" + cardTypeText + cardNo + "不存在</font>");
                                    cardFlag = 2;// 2代表卡号不存在
                                    return false;
                                }
                                if (!isEmpty(pt.cardType)) {
                                    // 跟页面上的cardType进行判断，如果不一致，就要改变，一致的话就不动
                                    if (pt.cardType != cardTypeValue) {
                                        // 在设置为选中状态
                                        $("#cardTypeId option[value=" + pt.cardType + "]").prop("selected", true);
                                    }
                                }
                                // 判断文本框中输入的名字，是否跟后端返回的名字一致，一致的话就直接覆盖，不一致的话就要提示
                                if (!isEmpty(name)) {
                                    if (name != pt.name) {
                                        showCueDialog(name, pt.name, cardTypeText, cardNo);
                                        // 对比不一致的字段
                                        replacePatient(pt, province, county);

                                    } else {

                                        // 对比不一致的字段
                                        replacePatient(pt, province, county, 'edit');

                                    }

                                } else {
                                    cardFlag = 3;// 验证通过
                                    // 向页面填充数据
                                    mappingFormData(pt, "patientForm", [ "id" ]);
                                    // 当没有输入姓名的时候，直接验证通过
                                    $("#validCardNoDiv").html("<font style='color:#41C95B'>验证通过</font>");
                                }

                            } else {
                                cardFlag = 2;// 卡号不存在
                                // showError("<font color='red'>" + cardTypeText + "</font>&nbsp;<font color='red'>" + cardNo + "</font>&nbsp;不存在!");
                                $("#validCardNoDiv").html("<font style='color:#F7587A;'>" + cardTypeText + cardNo + "不存在</font>");
                                return false;
                            }
                        }
                        return false;
                    }
                }
            });
        });
    }
};
$(function() {
    // 如果开启了接口服务，就要一开始显示卡号未验证,反之就不验证
    // 获取当前选中的值，如果包含在配置中，就要验证
    var cardTypeText = $("#cardTypeId").find("option:selected").text();
    if (!isEmpty(paramVal) && paramVal.indexOf(cardTypeText) == -1) {
        cardFlag = 3;// 直接通过
        $("#queryPatientInfo").css("margin-top", "0");
        $("#addPatientCard").css("margin-top", "0");
        $("#validCardNoDiv").hide();
    } else {// 开启了接口服务，又分为新增，与修改，新增要显示未验证，而修改不需要
        cardFlag = 1;// 未验证
        $("#validCardNoDiv").show();
    }
    // 初始化
    patient_edit.init();
    // 头像上传
    new uploadPreview({
        UpBtn : "up_img",
        DivShow : "imgdiv",
        ImgShow : "imgShow"
    });
    // 卡号类型变换绑定事件
    $("#cardTypeId").bind("change", function() {
        var cardTypeText = $("#cardTypeId").find("option:selected").text();
        if (!isEmpty(paramVal) && paramVal.indexOf(cardTypeText) != -1) {
            $("#queryPatientInfo").css("margin-top", "-26px");
            $("#addPatientCard").css("margin-top", "-26px");
            $("#validCardNoDiv").show();
            $("#validCardNoDiv").html("<font style='color:#F8C730;'>卡号未验证</font>");
        } else {
            $("#queryPatientInfo").css("margin-top", "0");
            $("#addPatientCard").css("margin-top", "0");
            cardFlag = 3;// 验证通过
            $("#validCardNoDiv").hide();
        }
    });
    // 生日选择
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
        getBirthdatByIdNo($("#patientForm_id_number").val());
    });

    $("#patientForm_id_number").blur(function() {
        changeIdNumber();
        getBirthdatByIdNo($("#patientForm_id_number").val());
    });

    $("#patientForm_birthday").blur(function() {
        changeIdNumber();
    });

});

/**
 * 输入身份证号自动带出省、市、区
 */
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
    if ($("#province").val() == null) {
        $("#county").empty();
        return;
    }
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
/**
 * 根据身份证获取出生日期
 * 
 * @param iIdNo
 */
function getBirthdatByIdNo(iIdNo) {
    var tmpStr = "";
    var birthday = $("#patientForm_birthday");
    iIdNo = $.trim(iIdNo);
    if (iIdNo.length == 15) {
        tmpStr = iIdNo.substring(6, 12);
        tmpStr = "19" + tmpStr;
        tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
        // sexStr = parseInt(iIdNo.substring(14, 15), 10) % 2 ? "M" : "F";
        birthday.val(tmpStr);
        // $("input[name='sex'][value='" + sexStr + "']").attr("checked", "checked");
    } else {
        tmpStr = iIdNo.substring(6, 14);
        tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
        // sexStr = parseInt(iIdNo.substring(17, 18), 10) % 2 ? "M" : "F";
        birthday.val(tmpStr);
        // $("input[name='sex'][value='" + sexStr + "']").attr("checked", "checked");
    }
}

/**
 * 上传头像
 */
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

/**
 * 保存患者
 * 
 * @returns {Boolean}
 */
function savePatient() {
    var cardTypeText = $("#cardTypeId").find("option:selected").text();
    var chargeType = $("input[name='chargeType']:checked");
    var name = $("#patientForm_name").val();
    var checkNum = $("#patientCardTable").find("input[data-newFlag]:checked").length;
    if (checkNum > 1) {
        showAlert("“ID”号只能有一个最新");
        return false;
    }

    if (chargeType.val() == "3") {
        if (isEmpty($("#patientForm_name").val())) {
            showAlert("“姓名”不能为空");
            return false;
        }
        if (isEmpty($("input[name='sex']:checked").length == 0)) {
            showAlert("“性别”不能为空");
            return false;
        }
        if (isEmpty($("#patientForm_birthday").val())) {
            showAlert("“出生年月”不能为空");
            return false;
        }
    } else {
        if (!$("#patientForm").valid()) {
            return false;
        }
    }
    // 獲取卡号
    var cardNo = $("input[name='patientCardList[0].cardNo']").val();
    // 卡号验证没有通过的话，就要提示
    if ((cardFlag == 1 || cardFlag == 2) && !isEmpty(cardNo)) {
        // 跳转到页面上的卡号位置
        $('html,body').animate({
            scrollTop : $('input[name="patientCardList[0].cardNo"]').offset().top - $('#validCardNoDiv').height() - 48
        }, 500);
        return false;
    } else {

        $.ajax({
            url : ctx + "/patient/savePatient.shtml",
            data : $("#patientForm").serialize(),
            type : "post",
            dataType : "json",
            loading : true,
            success : function(data) {// ajax返回的数据

                if (data.status == '1') {
                    refrshPatientData();
                    goToPatientHomePage(data.fkPatientId);
                } else if (data.status == "2") {
                    showWarn("患者卡号不能重复");
                } else if (data.status == "4") {
                    showWarn(data.errmsg);
                }
                return false;
            }
        });
    }
    return false;
}
/**
 * 跳转到患者首页
 * 
 * @param patientId
 */
function goToPatientHomePage(patientId) {
    window.location.href = ctx + "/patient/patientDetail.shtml?patientId=" + patientId + "&timeTemp=" + (new Date()).getTime();
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
    }, $.validator.format("请输入正确的证件号码"));
    $('#patientForm').validate({
        onfocusout : false,
        rules : {
            sex : {
                required : [ "性别" ]
            },
            isTemp : {
                required : [ "长期/临时患者" ]
            },
            idNumber : {
                required : [ "证件号码" ],
                isIdNumber : true
            },
            workUnit : {
                customMaxlength : [ 128, "工作单位" ]
            },
            birthdayShow : {
                required : [ "出生日期" ],
                date : [ "出生日期" ]
            },
            name : {
                required : [ "姓名" ]
            },
            mobile : {
                required : [ "联系方式" ],
                digits : [ "联系方式" ],
                customMaxlength : [ 12, "联系方式" ]
            },
            email : {
                email : [ "电子邮件" ]
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
            },
            height : {
                number : [ "身高" ],
                customRange : [ 0.01, 300, "身高" ]
            },
            weight : {
                number : [ "体重" ],
                customRange : [ 1, 500, "体重" ]
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

function refrshPatientData() {
    if (existsFunction("parent.getPatientData")) {
        parent.getPatientData();
    }
}
/**
 * 弹出提示的dialog
 */
function showCueDialog(name, defName, cardType, cardNo) {
    // show dialog
    $("#dialog").modal("show");
    // 设置dialog离顶部高度
    $("#dialog").css("margin", "-30px auto");
    // 设置dialog宽度为80%
    $(".modal-dialog").css("width", "460px");
    // 设置dialog 内容高度
    var dialog_height = 230;
    // 显示名字不一致的提示框
    $("#cueDiv").html("血透姓名" + name + "与" + cardType + cardNo + "查询到的" + defName + "不一致，是否确定为同一个人;");
    $("#dialog .modal-content").css("height", dialog_height + "px");
    $("#dialog .dialog-wrap .list-group").css("height", (dialog_height - 118) + "px");
}
/**
 * 显示替换页面
 */
function showReplace() {
    $("#dialog").modal("hide");
    $("#replacePatientId").modal("show");
    $("#replacePatientId").css("margin", "-30px auto");
    $("#replacePatientId .modal-dialog").css("width", "700px");
    // var dialog_height = auto;
    $("#replacePatientId .modal-content").css("height", "auto");
    $("#replacePatientId .dialog-wrap .list-group").css("height", "auto");
}
/**
 * 关闭提示框，并且显示未验证
 */
function hideDialog() {
    cardFlag = 1;// 未验证
    $("#dialog").modal("hide");
    $("#validCardNoDiv").html("<font style='color:#F8C730;'>卡号未验证</font>");
}
/**
 * 关闭提示框，并且显示验证已通过
 */
function closeDialog() {
    cardFlag = 3;// 验证通过
    $("#validCardNoDiv").html("<font style='color:#41C95B'>验证通过</font>");
    $("#replacePatientId").modal("hide");
    $("#dialog").modal("hide");
}
/**
 * 卡号输入的时候，就要显示了。
 */
function cardNoChange(cardNo) {
    var cardTypeText = $("#cardTypeId").find("option:selected").text();
    var cardNo = $("input[name='patientCardList[0].cardNo']").val();
    // 首先要判断卡号类型是不是属于要验证的范围,比如后端admin登录之后，在系统参数表中，有没有配置门诊号，住院号之类的要验证
    if (!isEmpty(paramVal) && paramVal.indexOf(cardTypeText) != -1) {
        // 如果卡号没有输入就不需要验证
        if (isEmpty(cardNo)) {
            $("#validCardNoDiv").html("<font style='color:#F8C730;'>卡号未验证</font>");
        } else {
            cardFlag = 1;// 输入卡号的时候，就开始显示未验证
            $("#queryPatientInfo").css("margin-top", "-26px");
            $("#addPatientCard").css("margin-top", "-26px");
            $("#validCardNoDiv").show();
            $("#validCardNoDiv").html("<font style='color:#F7587A;'>卡号必须验证</font>");
        }
    } else {
        cardFlag = 3;// 直接验证通过。
    }

}