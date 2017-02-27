var clinicalDiagnosisType;
$(function() {
    // 包含“其它”内容的自动展开输入框
    $("input[value='00']:checkbox,input[value='00']:radio").each(function(index, obj) {
        var other = $(this).parent().parent().find(".other");
        if (obj.checked) {
            if (other.hasClass("hide")) {
                other.removeClass("hide");
            }
        }
    });

    // 选中radio中的其他，显示其他对应的输入框
    $("input[value='00']:radio").each(function(index, obj) {
        $("input[type='radio'][name='" + $(this).attr('name') + "']").bind("click", function() {
            var other = $(this).parent().parent().find(".other");
            if ($(this).val() == '00') {
                if (other.hasClass("hide")) {
                    other.removeClass("hide");
                }
            } else {
                if (!other.hasClass("hide")) {
                    other.addClass("hide");
                }
            }
        });
    });

    $("#patientInfo").hide();
    $("#medicalHistory").hide();
    $("#clinicalDiagnosis").hide();
    $("#pathologicDiagnosis").hide();
    $("#ckdAki").hide();
    $("#cureComplication").hide();
    $("#otherDiagnosis").hide();
    $("#done").hide();
    var step = 0;
    var tabId = "";
    var barLen = $('#contentBar').children("div").length;
    // show table
    var lastStep = $("#lastStep").val();
    if (lastStep == "") {
        if (barLen == 8) {
            step = 0;
            tabId = "patientInfo";
        } else {
            step = 2;
            tabId = "clinicalDiagnosis";
        }
    } else {
        var step = parseInt(lastStep);
        if (step == 10) {
            step = 1;
            tabId = "medicalHistory";
        } else if (step == 20) {
            step = 2;
            tabId = "clinicalDiagnosis";
        } else if (step == 30) {
            step = 3;
            tabId = "pathologicDiagnosis";
        } else if (step == 40) {
            step = 4;
            tabId = "ckdAki";
        } else if (step == 50) {
            if (barLen == 8) {
                step = 5;
                tabId = "cureComplication";
            } else {
                step = 3;
                if ($("#tabId").val() == "") {
                    reloadPreview();
                }
                tabId = "done";
            }
        } else if (step == 60) {
            step = 6;
            tabId = "otherDiagnosis";
        } else if (step == 70) {
            if ($("#tabId").val() == "") {
                reloadPreview();
            }
            step = 7;
            tabId = "done";
        }
    }
    var barLen = $('#contentBar').children("div").length;
    var bar = [];
    if (barLen == 8) {// 从临床诊断开始
        bar = [ "基本信息", "询问病史", "临床诊断", "病理诊断", "CKD/AKI分期", "治疗前合并症", "其他诊断", "完成" ];
    } else {
        bar = [ "临床诊断", "病理诊断", "CKD/AKI分期", "完成" ];
        if (step == 7) {
            step = 3;
        } else {
            step -= 2;
        }
    }

    // 设置默认选中Tab
    if ($("#tabId").val() != "") {
        tabId = $("#tabId").val();
    }
    if (tabId != "") {
        if (barLen == 8) {
            tabSwitch("contentBar", tabId, bar, step);
        } else {
            tabSwitch("contentBar", tabId, bar, 3);
        }
    }
    // ctrlNav(step, bar);

    /**
     * 当前第几个Tab，Tab名数组
     */
    function ctrlNav(step, bar) {
        var barLen = $('#contentBar').children("div").length;
        var div = "";
        $('#contentBar').children("div").each(function(index) {
            $(this).html('<div class="disabled">' + bar[index] + '</div>');
        });
        $('#contentBar').children("div").each(
                        function(index) {

                            if (step == 0 && index == 0) {
                                div = '<div class="content-editing">' + bar[0] + '</div><div class="content-editing-ae"></div>';
                                $(this).html(div);
                                return;
                            }
                            if (index < step) {// 已完成
                                div = '<img src="' + ctx + '/assets/img/edit-finish.png"><span>' + bar[index] + '</span>';
                                $(this).html(div);
                            } else if (index == step) {
                                if (index == barLen - 1) {
                                    div = 'content-editing-as"></div><div class="content-editing">' + bar[index] + '</div>';
                                    $('#contentDiv').children("div")[0].html(div);
                                } else {
                                    div = '<div class="content-editing-as"></div><div class="content-editing">' + bar[index]
                                                    + '</div><div class="content-editing-ae"></div>';
                                    $(this).html(div);
                                }
                            } else {
                                div = '<div class="disabled">' + bar[index] + '</div>';
                                $(this).html(div);
                            }
                        });
    }

    // 首次透析日期选择
    var date = $("#medicalHistoryForm_firstDialysisDate").val();
    $.ymd_DatePicker({
        YearSelector : "#YYYY_firstDialysisDate",
        MonthSelector : "#MM_firstDialysisDate",
        DaySelector : "#DD_firstDialysisDate",
        InitDate : date,
        FirstValue : "",
        ResultSelector : "#medicalHistoryForm_firstDialysisDate"
    });

    // display values by type
    // clinical diagnosis
    clinicalDiagnosisType = $("input[name='type']:checked").val();
    if (typeof (clinicalDiagnosisType) == "undefined") {
        $("input[name='type']:eq(0)").attr("checked", true);
        showType(1);
    } else {
        showType(clinicalDiagnosisType);
    }

    $("input[name='type']").click(function() {
        var val = $("input[name='type']:checked").val();
        if (typeof (val) != "undefined" && val != clinicalDiagnosisType) {
            showType(val);
        }
        clinicalDiagnosisType = val;
    });

    // CKD/AKI显示控制
    if ($("input:radio[name='ckdAki']:checked").val() == "ckd") {
        $("#divCkd").show();
        $("#divAki").hide();
    } else {
        $("#divAki").show();
        $("#divCkd").hide();
    }
    if (isEmpty($("input:radio[name='ckdAki']:checked").val())) {
        $("#divAki").hide();
        $("#divCkd").hide();
    }

    $("input:radio[name='ckdType']").bind("click", function() {
        if (isEmpty($("input:radio[name='ckdAki']:checked").val())) {
            $(this).attr("checked", false);
        }
    });
    /*	$("input:radio[name='ckdStage']").bind("click", function() {
    		if (isEmpty($("input:radio[name='ckdType']:checked").val())) {
    			$(this).attr("checked", false);
    		}
    	});*/
    $("input:radio[name='akiType']").bind("click", function() {
        if (isEmpty($("input:radio[name='ckdAki']:checked").val())) {
            $(this).attr("checked", false);
        }
    });
    $("input:radio[name='akiStage']").bind("click", function() {
        if (isEmpty($("input:radio[name='akiType']:checked").val())) {
            $(this).attr("checked", false);
        }
    });
    $("input:radio[name='ckdAki']").bind("click", function() {
        if ($(this).val() == "ckd") {
            $("#divCkd").show();
            $("#divAki").hide();
            $("#divAki").find(":radio").attr("checked", false);
        } else {
            $("#divAki").show();
            $("#divCkd").hide();
            $("#divCkd").find(":radio").attr("checked", false);
        }
    });
    if ($("input:radio[name='akiType']:checked").val() == "R") {
        $("#akiStageRifle").show();
        $("#akiStageAK").hide();
    } else {
        $("#akiStageRifle").hide();
        $("#akiStageAK").show();
    }
    $("input:radio[name='akiType']").click(function() {
        if (this.value == "R") {
            $("#akiStageRifle").show();
            $("#akiStageAK").hide();
        } else {
            $("#akiStageRifle").hide();
            $("#akiStageAK").show();
        }
    });

});
/**
 * 使用button 提交 form 避免enter键直接提交
 * 
 * @param btn
 */
function buttonSubmit(btn) {
    /* 表单校验 */
    if ($(btn.form).valid()) {
        if (isEmpty(btn.form.onsubmit)) {
            btn.form.submit();
        } else {
            btn.form.onsubmit();
        }
    }
}

function checkMedicalHistory() {
    var date = $("#medicalHistoryForm_firstDialysisDate").val();
    if (!isEmpty(date) && date.length != 10) {
        showWarn("首次透析日期格式不正确");
        return false;
    }
    var date = $("[name='firstDialysisDateFrom']").val();
    $("#medicalHistoryForm_firstDialysisDate").val(date);
    return true;
}

/**
 * 临床诊断
 * 
 * @param typeVal
 */
function showType(typeVal) {
    if (typeVal == 1) {
        $("#crfForm").show();
        $("#seriousCrfForm").hide();
        $("#arfForm").hide();
    } else if (typeVal == 2) {
        $("#crfForm").hide();
        $("#seriousCrfForm").show();
        $("#arfForm").hide();
    } else if (typeVal == 3) {
        $("#crfForm").hide();
        $("#seriousCrfForm").hide();
        $("#arfForm").show();
    }
}

function checkCkdAki() {

    var barLen = $('#contentBar').children("div").length;
    if (barLen == 8 && isEmpty($("input:radio[name='ckdAki']:checked").val())) {
        showWarn("请选择CKD/AKI分期");
        return false;
    }
    if ($("input:radio[name='ckdAki']:checked").val() == "aki") {
        if (isEmpty($("#ckdStageForm input:radio[name='akiType']:checked").val())) {
            // && isEmpty($("#ckdStageForm input:radio[name='ckdType']:checked").val())
            showWarn("请选择AKI分期分类");
            return false;
        }
        var akiType = $("#ckdStageForm input:radio[name='akiType']:checked").val();
        if (akiType != undefined) {
            if (akiType == "R") {
                if ("RIFLE".indexOf($("#ckdStageForm input:radio[name='akiStage']:checked").val()) == -1) {
                    showWarn("请选择AKI分期");
                    return false;
                }
            } else {
                if ("123".indexOf($("#ckdStageForm input:radio[name='akiStage']:checked").val()) == -1) {
                    showWarn("请选择AKI分期");
                    return false;
                }
            }
        }

    } else if ($("input:radio[name='ckdAki']:checked").val() == "aki") {

        if (isEmpty($("#ckdStageForm input:radio[name='ckdStage']:checked").val())) {
            // $("#ckdStageForm input:radio[name='ckdType']:checked").val() != undefined
            showWarn("请选择CKD分期");
            return false;
        }
    }
    return true;
}

$("input[name='crfUnknownReason']").click(function() {
    $("#crfUnknownReason").val($(this).val());
});

function reloadPreview() {
    $("#ifrDone")
                    .attr(
                                    "src",
                                    "firstPreview.shtml?fkPatientDiagnosisId=" + $("#fkPatientDiagnosisId").val() + "&patientId="
                                                    + $("#fkPatientId").val());

    $("#ifrDone").load(function() {
        var mainheight = $(this).contents().find("body").height();
        $(this).height(mainheight);
    });
}

/**
 * 
 * @param form
 *            提交的form组件
 * @param nextPage
 *            跳转的页面地址，如果是tab，写tabId，如果是页面跳转，写跳转地址。如：（/patient/diagnosis/newPatient.shtml）
 * @param isTab
 *            区分是否是tab
 * @param processId
 *            流程id
 * @returns {Boolean}
 */
function formSubmit(form, nextPage, isTab, callback) {
    var formId = $(form).attr("id");
    var fkPatientDiagnosisId = $("#fkPatientDiagnosisId").val();
    $("#" + formId + " input[type='hidden']").each(function() {
        var inputValue = "";
        var isCheckbox = false;
        var hiddenId = $(this).attr("id");
        $("#" + formId + " input[type='checkbox']").each(function() {
            if ($(this).attr("id").indexOf(hiddenId + formId) != -1) {
                if ($(this).is(":checked")) {
                    inputValue += $(this).val() + ",";
                }
                isCheckbox = true;
            }
        });
        if (inputValue.length > 0) {
            $(this).val(inputValue.substring(0, inputValue.length - 1));
        } else {
            if (isCheckbox) {
                $(this).val(inputValue);
            }
        }
    });
    clearOther(formId);
    var formData = $(form).serialize();
    if (!(typeof (formData) == "undefined" || formData == "")) {
        var actionValue = "save" + formId.substr(0, 1).toUpperCase() + formId.substring(1, formId.indexOf("Form"));
        var commonPara = "&fkPatientDiagnosisId=" + fkPatientDiagnosisId + "&fkPatientId=" + $("#fkPatientId").val() + "&isDraft="
                        + $("#isDraft").val();
        $.ajax({
            url : ctx + "/patient/diagnosis/" + actionValue + ".shtml",
            data : formData + commonPara,
            type : "post",
            dataType : "json",
            loading : true,
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        var tip = "<div id='tip' align='right'><font size='5px;' color='red'>恭喜你，保存成功</font></div>";
                        $(form).append(tip);
                        $("#tip").show(300).delay(1200).hide(300);
                        $("#" + formId + "Id").val(data.id);
                        $("#" + formId + "ParentId").val(data.parentId);
                        if (data.fkPatientDiagnosisId != undefined) {
                            $("#fkPatientDiagnosisId").val(data.fkPatientDiagnosisId);
                        }
                        setTimeout(function() {
                            $("#tip").empty().remove();
                        }, 1200);

                        gotoPage(nextPage, isTab, fkPatientDiagnosisId);
                    } else if (data.status == 2) {
                        showWarn("信息已被他人修改！请刷新后重试^_^");
                    }
                }
            }
        });
        return false;
    } else {
        gotoPage(nextPage, isTab, fkPatientDiagnosisId);
        return false;
    }
}

function gotoPage(nextPage, isTab, dcId) {
    if (typeof (nextPage) != "undefined" && nextPage != "") {
        if (isTab == true) {
            // $('a[data-toggle="tab"]').removeClass("active");
            // $('a[href="#' + nextPage + '"]').tab('show');
            if ($('#contentBar').children("div").length == 4) {
                tabSwitch("contentBar", nextPage, null, 3);
            } else {

                var lastStep = $("#lastStep").val();
                var step = 0;
                if (lastStep == "") {
                    step = 0;
                } else {
                    step = parseInt(lastStep);
                    if (step == 10) {
                        step = 1;
                    } else if (step == 20) {
                        step = 2;
                    } else if (step == 30) {
                        step = 3;
                    } else if (step == 40) {
                        step = 4;
                    } else if (step == 50) {
                        step = 5;
                    } else if (step == 60) {
                        step = 6;
                    } else if (step == 70) {
                        step = 7;
                    }
                }
                tabSwitch("contentBar", nextPage, null, step);
            }
            if (nextPage == "done") {
                reloadPreview();
            }
        } else {
            window.location.href = ctx + nextPage + (typeof (dcId) == "undefined" ? "" : "?fkPatientDiagnosisId=" + dcId);
        }
    }

}

/**
 * 清除指定待提交form中未选择其它的输入框内容
 */
function clearOther(formId) {
    // 包含“其它”内容的自动展开输入框
    $("input[value='00']:checkbox").each(function(index, obj) {
        var other = $(this).parent().parent().find(".other");
        if (!obj.checked) {
            other.children("textarea").val("");
        }
    });
}

$(function() {
    var show_index = 0;
    var show_editing = $(this).find(".content-editing-wrap").eq(show_index);
    var arrow = show_editing.parent().find(".tab-action2");
    arrow.parent().find(".not-selected").removeClass("not-selected").addClass("selected");
    arrow.find("span").html("收起");
    arrow.find("img").attr("src", ctx + "/assets/img/arrow-down.png").removeClass("arrow-up").addClass("arrow-down");
    show_editing.toggle(400);

    $(".content-editing-bar").each(function() {
        $(this).css("cursor", "hand");
    });
    $(".content-editing-bar").click(function() {
        $(this).css("cursor", "hand");
        var tabAction = $(this).children(".tab-action2");
        tabAction.parent().parent().find(".content-editing-wrap").eq(0).toggle(400);
        if (tabAction.find("img").hasClass("arrow-up")) {
            tabAction.find("span").html("收起");
            tabAction.parent().find(".not-selected").removeClass("not-selected").addClass("selected");
            tabAction.find("img").attr("src", ctx + "/assets/img/arrow-down.png").removeClass("arrow-up").addClass("arrow-down");
        } else {
            tabAction.find("span").html("编辑");
            tabAction.parent().find(".selected").removeClass("selected").addClass("not-selected");
            tabAction.find("img").attr("src", ctx + "/assets/img/arrow-right.png").removeClass("arrow-down").addClass("arrow-up");
        }
    });

    $("input[value='00']:checkbox").click(function() {
        var other = $(this).parent().parent().find(".other");
        if (other.hasClass("hide")) {
            other.removeClass("hide");
        } else {
            other.addClass("hide");
        }
    });
});