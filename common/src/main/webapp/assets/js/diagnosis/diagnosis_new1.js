$(document).ready(function() {
    delFlag = $('#delFlag').val();
    // 初始化全部诊断数据
    loadDiagnosisData('', '');
    bindTreeEvent();
    addDiagnosisEvents();
});
/**
 * 添加诊断事件
 */
function addDiagnosisEvents() {
    $("body").click(function() {
        if ($(".u-addUl").css('display') == "block") {
            $(".u-addUl").hide();
        }
    });
    $("#addMe").click(function(event) {
        $(".u-addUl").toggle();
        stopEventBubble(event); // 组织事件冒泡
    });
    /**
     * tab 切换事件
     */
    $(".diagnosis-btns").on("click", "button", function() {
        $(this).addClass("u-btn-blue").siblings().removeClass("u-btn-blue");
        var permission = $(this).data("permission-key");
        $(".diagnosis-content > div#" + permission).show().siblings().hide();
    });
    /**
     * 删除tab事件
     */
    $(".diagnosis-btns").on("click", ".icon-close", function() {
        var itemCode = $(this).parent().data('item-code');
        $(this).parent().remove();
        $(".diagnosis-btns > button[data-item-code='" + itemCode + "']").click();
    });
}

function bindTreeEvent() {
    var myHistory;
    $(".u-tree li span").click(function(event) {
        if (myHistory) {
            $(myHistory).css({
                "color" : "#484848",
                "font-weight" : "400"
            });
        }
        var myTree = $(this).parent().children();
        if (myTree[1]) {
            $(myTree[1]).toggle();
            if ($(myTree[1]).is(":hidden")) {
                $(this).find("img").attr("src", ctx + "/assets/img/ArtboardCopy1.png");
            } else {
                $(this).find("img").attr("src", ctx + "/assets/img/ArtboardCopy2.png");
            }
            return;
        }
        $(myTree[0]).css({
            "color" : "#31aaff",
            "font-weight" : "bold"
        });
        myHistory = $(myTree[0]);
    });
}
var diagnosis_types = [ {
    type : '',
    value : 0
}, {
    type : 'hist_first_dialysis',
    value : 1
}, {
    type : 'hist_surgery',
    value : 2
}, {
    type : 'hist_hd',
    value : 3
}, {
    type : 'hist_pd',
    value : 4
}, {
    type : 'hist_kt',
    value : 5
}, {
    type : 'hist_allergy',
    value : 6
}, {
    type : 'hist_pestilence',
    value : 7
}, {
    type : 'diagnosis_entity',
    value : 8
}, {
    type : 'hist_tumour',
    value : 9
} ];
var permission_keys = [ 'medical_history', 'clinical_diagnosis', 'pathologic_diagnosis', 'ckd_aki', 'cure_complication', 'other_diagnosis' ];
// 病史新增操作菜单项
var diagnosis_hist_actions = [ {
    itemCode : 'SCTX',
    itemName : '首次透析',
    dialogType : 'diagnosisHistFirstDialysis',
    background : '#e87b92',
    diagnosisType : 'hist_first_dialysis'
}, {
    itemCode : 'SS',
    itemName : '手术史',
    dialogType : 'diagnosisHistSurgery',
    background : '#eb9b18',
    diagnosisType : 'hist_surgery'
}, {
    itemCode : 'XT',
    itemName : '血透史',
    dialogType : 'diagnosisHistHd',
    background : '#82929d',
    diagnosisType : 'hist_hd'
}, {
    itemCode : 'FT',
    itemName : '腹透史',
    dialogType : 'diagnosisHistPd',
    background : '#71bfe8',
    diagnosisType : 'hist_pd'
}, {
    itemCode : 'SYZ',
    itemName : '肾移植史',
    dialogType : 'diagnosisHistKt',
    background : '#31d57d',
    diagnosisType : 'hist_kt'
}, {
    itemCode : 'GM',
    itemName : '过敏史',
    dialogType : 'diagnosisHistAllergy',
    background : '#8f6dac',
    diagnosisType : 'hist_allergy'
}, {
    itemCode : 'CRB',
    itemName : '传染病史',
    dialogType : 'diagnosisHistPestilence',
    background : '#cc52f1',
    diagnosisType : 'hist_pestilence'
}, {
    itemCode : 'TUMOUR',
    itemName : '肿瘤史',
    dialogType : 'diagnosisHistTumour',
    background : '#7FABCA',
    diagnosisType : 'hist_tumour'
} ];
var dictDiagnosisList = [];
/**
 * 初始化数据
 * 
 * @param diagnosisType
 */
function loadDiagnosisData(diagnosisType, itemCode) {
    var patientId = $("#patientId").val();
    $.ajax({
        url : ctx + "/patient/diagnosis/getDiagnosisData.shtml",
        data : {
            patientId : patientId,
            diagnosisType : diagnosisType,
            itemCode : itemCode
        },
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data) {
                buildDiagnosisView(data);
            }
        }
    });
}
// 生成诊断页面试图内容
function buildDiagnosisView(data) {
    data.diagnosisType = data.diagnosisType || '';
    var diagnosisTypeObj = {};
    $.each(diagnosis_types, function(i, v) {
        if (v.type == data.diagnosisType) {
            diagnosisTypeObj = v;
        }
    });
    switch (diagnosisTypeObj.value) {
    case 0: // 渲染所有诊断列表信息
        dictDiagnosisList = data.dictDiagnosisList; // 诊断字典数据结婚，树形结构，作为选项的数据源
        buildDictDiagnosisView(); // 渲染诊断字典结构数据
        buildDiagnosisHistFirstDialysisData(data);
        buildDiagnosisHistSurgeryData(data);
        buildDiagnosisHistHdData(data);
        buildDiagnosisHistPdData(data);
        buildDiagnosisHistKtData(data);
        buildDiagnosisHistAllergyData(data);
        buildDiagnosisHistPestilenceData(data);
        buildDiagnosisHistTumourData(data);
        buildDiagnosisDiagnosisEntityData(data);
        break;
    case 1: // 渲染'首次透析'内容
        buildDiagnosisHistFirstDialysisData(data);
        break;
    case 2: // 渲染'手术史'内容
        buildDiagnosisHistSurgeryData(data);
        break;
    case 3: // 渲染'血透史'内容
        buildDiagnosisHistHdData(data);
        break;
    case 4: // 渲染'腹透史'内容
        buildDiagnosisHistPdData(data);
        break;
    case 5: // 渲染'肾移植史'内容
        buildDiagnosisHistKtData(data);
        break;
    case 6: // 渲染'过敏史'内容
        buildDiagnosisHistAllergyData(data);
        break;
    case 7: // 渲染'传染病史'内容
        buildDiagnosisHistPestilenceData(data);
        break;
    case 8: // 渲染'诊断选项'内容
        buildDiagnosisDiagnosisEntityData(data);
        break;
    case 9: // 渲染'肿瘤史'内容
        buildDiagnosisHistTumourData(data);
        break;
    default:
        break;
    }
}
// 刷新首次透析数据
function refresh_hist_first_dialysis() {
    loadDiagnosisData('hist_first_dialysis', '');
}
// 刷新手术史数据
function refresh_hist_surgery() {
    loadDiagnosisData('hist_surgery', '');
}
// 刷新血透史数据
function refresh_hist_hd() {
    loadDiagnosisData('hist_hd', '');
}
// 刷新腹透史数据
function refresh_hist_pd() {
    loadDiagnosisData('hist_pd', '');
}
// 刷新肾移植史数据
function refresh_hist_kt() {
    loadDiagnosisData('hist_kt', '');
}
// 刷新过敏史数据
function refresh_hist_allergy() {
    loadDiagnosisData('hist_allergy', '');
}
// 刷新传染病史数据
function refresh_hist_pestilence() {
    loadDiagnosisData('hist_pestilence', '');
}

// 刷新传染病史数据
function refresh_hist_tumour() {
    loadDiagnosisData('hist_tumour', '');
}
/**
 * 渲染诊断字典结构数据
 */
function buildDictDiagnosisView() {
    // 诊断第一层btn项内容
    var html = '';
    var patientId = $("#patientId").val();
    $.each(dictDiagnosisList, function(i, dict) {
        var permission_key = permission_keys[i];
        html += '<button type="button" class="mr-10" data-permission-key="' + permission_key + '" data-item-code="' + dict.itemCode + '">'
                        + dict.itemName + '</button>';
        $(".add_" + permission_key).attr("data-item-code", dict.itemCode);
        // 初始化页面布局初始内容结构
        var diagnosis_content_html = '';
        if ($(".add_" + permission_key).parent().find("ul.diagnosis-hist-action").length > 0) {
            // 新增操作的menu内容
            var add_action_html = '';
            $.each(diagnosis_hist_actions,
                            function(j, action) {
                                // 先遍历获取固定的菜单项
                                add_action_html += '<li data-item-code="' + action.itemCode + '" onclick="showDiagnosisDialog(\'\', ' + patientId
                                                + ', \'' + action.diagnosisType + '\', \'' + action.dialogType + '\')">' + action.itemName
                                                + '<div class="f-solid"></div></li>';
                                // 病史页面生成动态的内容布局结构
                                diagnosis_content_html += '<div class="m-t-12 m-l-21 p-relative data-view-' + action.itemCode + '">';
                                diagnosis_content_html += '     <span class="u-sign-1" style="background: ' + action.background + ';">'
                                                + action.itemName + '</span>';
                                diagnosis_content_html += '</div>';
                                diagnosis_content_html += '<div class="m-t-5 u-card-1 data-view-' + action.itemCode + '">';
                                diagnosis_content_html += '    <div class="clearfix">';
                                diagnosis_content_html += '        <div class="pull-left">暂无' + action.itemName + '数据</div>';
                                diagnosis_content_html += '    </div>';
                                diagnosis_content_html += '</div>';
                            });
            // 增加动态的菜单项（病史有点特殊，itemCode动态，名称固定归为其他病史）
            add_action_html += '<li data-item-code="' + dict.itemCode + '" onclick="ShowDiagnosisEntityTab(\'\', ' + patientId + ', \''
                            + dict.itemCode + '\', \'其他病史\')">其他病史<div class="f-solid"></div></li>';
            $(".add_" + permission_key).parent().find("ul.diagnosis-hist-action").html(add_action_html);
            // 添加动态的诊断选项的内容布局结构
            diagnosis_content_html += '<div class="m-t-12 m-l-21 p-relative data-view-' + dict.itemCode + '">';
            diagnosis_content_html += '     <span class="u-sign-1" style="background: #44cfb0;">其他病史</span>';
            diagnosis_content_html += '</div>';
            diagnosis_content_html += '<div class="m-t-5 u-card-1 data-view-' + dict.itemCode + '">';
            diagnosis_content_html += '    <div class="clearfix">';
            diagnosis_content_html += '        <div class="pull-left">暂无其他病史数据</div>';
            diagnosis_content_html += '    </div>';
            diagnosis_content_html += '</div>';
        } else {
            // 添加其他诊断Tab按钮的新增事件绑定
            $(".add_" + permission_key).bind("click", function() {
                ShowDiagnosisEntityTab('', patientId, dict.itemCode, dict.itemName);
            });
            $(".add_" + permission_key).attr("data-item-code", dict.itemCode);
            // 添加动态的诊断选项的内容布局结构
            diagnosis_content_html += '<div class="data-view-' + dict.itemCode + '">';
            diagnosis_content_html += '    <div class="m-t-5 u-card-1">';
            diagnosis_content_html += '        <div class="clearfix">';
            diagnosis_content_html += '            <div class="pull-left">暂无' + dict.itemName + '数据</div>';
            diagnosis_content_html += '        </div>';
            diagnosis_content_html += '    </div>';
            diagnosis_content_html += '</div>';
        }
        $("#list_" + permission_key).html(diagnosis_content_html);
    });
    $(".diagnosis-btns").html(html);
    // 默认第一个btn被激活
    $(".diagnosis-btns button:first").click();
}
/**
 * 展示病史dialog
 * 
 * @param id
 * @param patientId
 * @param diagnosisType
 * @param dialogType
 */
function showDiagnosisDialog(id, patientId, diagnosisType, dialogType) {
    var functionName = 'refresh_' + diagnosisType;
    if (isFromIframe()) {
        $(".modal[role='dialog']").css("margin-top", (parent.document.body.scrollTop - 30) + "px");
    }
    if (!isEmpty(functionName)) {
        $("body").data("dialogFunctionName", functionName);
    }
    for ( var key in dialogMapper) {
        if (key == dialogType) {
            eval(dialogMapper[key] + "('" + id + "','" + patientId + "','" + diagnosisType + "');");
            return;
        }
    }
}
/**
 * 渲染'首次透析'内容
 * 
 * @param data
 */
function buildDiagnosisHistFirstDialysisData(data) {

    var list = data.items.hist_first_dialysis || [];
    if (list.length > 0) {
        // 首次透析数据只能有一条，隐藏 新增 首次透析 按钮
        $(".u-addUl li").eq(0).hide();
        var diagnosis_content_html = '';
        $
                        .each(
                                        list,
                                        function(i, v) {
                                            diagnosis_content_html += '    <div class="clearfix">';
                                            diagnosis_content_html += '        <div class="pull-right">';
                                            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.operatorName + '</span>';
                                            if (!patientHasOutCome) {
                                                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis('
                                                                + v.id
                                                                + ', '
                                                                + v.fkPatientId
                                                                + ', \'hist_first_dialysis\', \''
                                                                + diagnosis_hist_actions[0].itemCode
                                                                + '\', \''
                                                                + diagnosis_hist_actions[0].itemName
                                                                + '\')">删除</span>';
                                                var dialogType = 'diagnosis' + upperFirstLetter('hist_first_dialysis'.split('_'));
                                                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="showDiagnosisDialog('
                                                                + v.id + ', ' + v.fkPatientId + ', \'hist_first_dialysis\', \'' + dialogType
                                                                + '\')">编辑</span>';
                                            }
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '        <div class="pull-left">';
                                            diagnosis_content_html += '            <div>透析时间：' + v.firstTreatmentDateShow + '</div>';
                                            diagnosis_content_html += '            <div>透析方式：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.firstTreatmentTypeShow + '</label></div>';
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '    </div>';
                                            if (list.length > 1 && i != list.length - 1) {
                                                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
                                            }
                                        });
        $(".data-view-" + diagnosis_hist_actions[0].itemCode + ":eq(1)").html(diagnosis_content_html);
    } else {
        // $(".data-view-" + diagnosis_hist_actions[0].itemCode).hide();
        var diagnosis_content_html = '';
        diagnosis_content_html += '    <div class="clearfix">';
        diagnosis_content_html += '        <div class="pull-left">暂无' + diagnosis_hist_actions[0].itemName + '数据</div>';
        diagnosis_content_html += '    </div>';
        $(".data-view-" + diagnosis_hist_actions[0].itemCode + ":eq(1)").html(diagnosis_content_html);
    }
}
/**
 * 渲染'手术史'内容
 * 
 * @param data
 */
function buildDiagnosisHistSurgeryData(data) {
    var list = data.items.hist_surgery || [];
    if (list.length > 0) {
        var diagnosis_content_html = '';
        $.each(list, function(i, v) {
            diagnosis_content_html += '    <div class="clearfix">';
            diagnosis_content_html += '        <div class="pull-right">';
            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
                            + v.operatorName + '</span>';
            if (!patientHasOutCome) {
                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis(' + v.id + ', '
                                + v.fkPatientId + ', \'hist_surgery\', \'' + diagnosis_hist_actions[1].itemCode + '\', \''
                                + diagnosis_hist_actions[1].itemName + '\')">删除</span>';
                var dialogType = 'diagnosis' + upperFirstLetter('hist_surgery'.split('_'));
                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="showDiagnosisDialog(' + v.id + ', ' + v.fkPatientId
                                + ', \'hist_surgery\', \'' + dialogType + '\')">编辑</span>';
            }
            diagnosis_content_html += '        </div>';
            diagnosis_content_html += '        <div class="pull-left">';
            diagnosis_content_html += '            <div>';
            diagnosis_content_html += '                <span>手术日期：' + v.surgeryDateShow + '</span>';
            diagnosis_content_html += '                <span class="m-l-30">名        称：' + v.surgeryName + '</span>';
            diagnosis_content_html += '            </div>';
            diagnosis_content_html += '            <div>备注：' + v.remark + '</div>';
            diagnosis_content_html += '        </div>';
            diagnosis_content_html += '    </div>';
            if (list.length > 1 && i != list.length - 1) {
                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
            }
        });
        $(".data-view-" + diagnosis_hist_actions[1].itemCode + ":eq(1)").html(diagnosis_content_html);
    } else {
        // $(".data-view-" + diagnosis_hist_actions[1].itemCode).hide();
        var diagnosis_content_html = '';
        diagnosis_content_html += '    <div class="clearfix">';
        diagnosis_content_html += '        <div class="pull-left">暂无' + diagnosis_hist_actions[1].itemName + '数据</div>';
        diagnosis_content_html += '    </div>';
        $(".data-view-" + diagnosis_hist_actions[1].itemCode + ":eq(1)").html(diagnosis_content_html);
    }
}
/**
 * // 渲染'血透史'内容
 * 
 * @param data
 */
function buildDiagnosisHistHdData(data) {
    var list = data.items.hist_hd || [];
    if (list.length > 0) {
        var diagnosis_content_html = '';
        $
                        .each(
                                        list,
                                        function(i, v) {
                                            diagnosis_content_html += '    <div class="clearfix">';
                                            diagnosis_content_html += '        <div class="pull-right">';
                                            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.operatorName + '</span>';
                                            if (!patientHasOutCome) {
                                                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis('
                                                                + v.id
                                                                + ', '
                                                                + v.fkPatientId
                                                                + ', \'hist_hd\', \''
                                                                + diagnosis_hist_actions[2].itemCode
                                                                + '\', \''
                                                                + diagnosis_hist_actions[2].itemName
                                                                + '\')">删除</span>';
                                                var dialogType = 'diagnosis' + upperFirstLetter('hist_hd'.split('_'));
                                                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="showDiagnosisDialog('
                                                                + v.id + ', ' + v.fkPatientId + ', \'hist_hd\', \'' + dialogType + '\')">编辑</span>';
                                            }
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '        <div class="pull-left">';
                                            diagnosis_content_html += '            <div>';
                                            diagnosis_content_html += '                <span>开始时间：'
                                                            + v.startDateShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始原因：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.startReasonShow + (v.otherStartReason ? '：' + v.otherStartReason : '')
                                                            + '</label></span>';
                                            diagnosis_content_html += '                <span class="m-l-30">结束时间：'
                                                            + v.endDateShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束原因：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.endReasonShow + (v.otherEndReason ? '：' + v.otherEndReason : '') + '</label></span>';
                                            diagnosis_content_html += '            </div>';
                                            diagnosis_content_html += '            <div>备注：' + v.remark + '</div>';
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '    </div>';
                                            if (list.length > 1 && i != list.length - 1) {
                                                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
                                            }
                                        });
        $(".data-view-" + diagnosis_hist_actions[2].itemCode + ":eq(1)").html(diagnosis_content_html);
    } else {
        // $(".data-view-" + diagnosis_hist_actions[2].itemCode).hide();
        var diagnosis_content_html = '';
        diagnosis_content_html += '    <div class="clearfix">';
        diagnosis_content_html += '        <div class="pull-left">暂无' + diagnosis_hist_actions[2].itemName + '数据</div>';
        diagnosis_content_html += '    </div>';
        $(".data-view-" + diagnosis_hist_actions[2].itemCode + ":eq(1)").html(diagnosis_content_html);
    }
}
/**
 * 渲染'腹透史'内容
 * 
 * @param data
 */
function buildDiagnosisHistPdData(data) {
    var list = data.items.hist_pd || [];
    if (list.length > 0) {
        var diagnosis_content_html = '';
        $
                        .each(
                                        list,
                                        function(i, v) {
                                            diagnosis_content_html += '    <div class="clearfix">';
                                            diagnosis_content_html += '        <div class="pull-right">';
                                            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.operatorName + '</span>';
                                            if (!patientHasOutCome) {
                                                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis('
                                                                + v.id
                                                                + ', '
                                                                + v.fkPatientId
                                                                + ', \'hist_pd\', \''
                                                                + diagnosis_hist_actions[3].itemCode
                                                                + '\', \''
                                                                + diagnosis_hist_actions[3].itemName
                                                                + '\')">删除</span>';
                                                var dialogType = 'diagnosis' + upperFirstLetter('hist_pd'.split('_'));
                                                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="showDiagnosisDialog('
                                                                + v.id + ', ' + v.fkPatientId + ', \'hist_pd\', \'' + dialogType + '\')">编辑</span>';
                                            }
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '        <div class="pull-left">';
                                            diagnosis_content_html += '            <div>';
                                            diagnosis_content_html += '                <span>开始时间：'
                                                            + v.startDateShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始原因：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.startReasonShow + (v.otherStartReason ? '：' + v.otherStartReason : '')
                                                            + '</label></span>';
                                            diagnosis_content_html += '                <span class="m-l-30">结束时间：'
                                                            + v.endDateShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束原因：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.endReasonShow + (v.otherEndReason ? '：' + v.otherEndReason : '') + '</label></span>';
                                            diagnosis_content_html += '            </div>';
                                            diagnosis_content_html += '            <div>备注：' + v.remark + '</div>';
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '    </div>';
                                            if (list.length > 1 && i != list.length - 1) {
                                                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
                                            }
                                        });
        $(".data-view-" + diagnosis_hist_actions[3].itemCode + ":eq(1)").html(diagnosis_content_html);
    } else {
        // $(".data-view-" + diagnosis_hist_actions[3].itemCode).hide();
        var diagnosis_content_html = '';
        diagnosis_content_html += '    <div class="clearfix">';
        diagnosis_content_html += '        <div class="pull-left">暂无' + diagnosis_hist_actions[3].itemName + '数据</div>';
        diagnosis_content_html += '    </div>';
        $(".data-view-" + diagnosis_hist_actions[3].itemCode + ":eq(1)").html(diagnosis_content_html);
    }
}
/**
 * 渲染'肾移植史'内容
 * 
 * @param data
 */
function buildDiagnosisHistKtData(data) {
    var list = data.items.hist_kt || [];
    if (list.length > 0) {
        var diagnosis_content_html = '';
        $
                        .each(
                                        list,
                                        function(i, v) {
                                            diagnosis_content_html += '    <div class="clearfix">';
                                            diagnosis_content_html += '        <div class="pull-right">';
                                            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.operatorName + '</span>';
                                            if (!patientHasOutCome) {
                                                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis('
                                                                + v.id
                                                                + ', '
                                                                + v.fkPatientId
                                                                + ', \'hist_kt\', \''
                                                                + diagnosis_hist_actions[4].itemCode
                                                                + '\', \''
                                                                + diagnosis_hist_actions[4].itemName
                                                                + '\')">删除</span>';
                                                var dialogType = 'diagnosis' + upperFirstLetter('hist_kt'.split('_'));
                                                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="showDiagnosisDialog('
                                                                + v.id + ', ' + v.fkPatientId + ', \'hist_kt\', \'' + dialogType + '\')">编辑</span>';
                                            }
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '        <div class="pull-left">';
                                            diagnosis_content_html += '            <div>';
                                            diagnosis_content_html += '                <span>开始时间：' + v.startDateShow + '</span>';
                                            diagnosis_content_html += '                <span class="m-l-30">结束时间：'
                                                            + v.endDateShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束原因：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.endReasonShow + (v.otherEndReason ? '：' + v.otherEndReason : '') + '</label></span>';
                                            diagnosis_content_html += '            </div>';
                                            diagnosis_content_html += '            <div>备注：' + v.remark + '</div>';
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '    </div>';
                                            if (list.length > 1 && i != list.length - 1) {
                                                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
                                            }
                                        });
        $(".data-view-" + diagnosis_hist_actions[4].itemCode + ":eq(1)").html(diagnosis_content_html);
    } else {
        // $(".data-view-" + diagnosis_hist_actions[4].itemCode).hide();
        var diagnosis_content_html = '';
        diagnosis_content_html += '    <div class="clearfix">';
        diagnosis_content_html += '        <div class="pull-left">暂无' + diagnosis_hist_actions[4].itemName + '数据</div>';
        diagnosis_content_html += '    </div>';
        $(".data-view-" + diagnosis_hist_actions[4].itemCode + ":eq(1)").html(diagnosis_content_html);
    }
}
/**
 * 渲染'过敏史'内容
 * 
 * @param data
 */
function buildDiagnosisHistAllergyData(data) {
    var list = data.items.hist_allergy || [];
    if (list.length > 0) {
        var diagnosis_content_html = '';
        $
                        .each(
                                        list,
                                        function(i, v) {
                                            diagnosis_content_html += '    <div class="clearfix">';
                                            diagnosis_content_html += '        <div class="pull-right">';
                                            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.operatorName + '</span>';
                                            if (!patientHasOutCome) {
                                                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis('
                                                                + v.id
                                                                + ', '
                                                                + v.fkPatientId
                                                                + ', \'hist_allergy\', \''
                                                                + diagnosis_hist_actions[5].itemCode
                                                                + '\', \''
                                                                + diagnosis_hist_actions[5].itemName
                                                                + '\')">删除</span>';
                                                var dialogType = 'diagnosis' + upperFirstLetter('hist_allergy'.split('_'));
                                                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="showDiagnosisDialog('
                                                                + v.id + ', ' + v.fkPatientId + ', \'hist_allergy\', \'' + dialogType
                                                                + '\')">编辑</span>';
                                            }
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '        <div class="pull-left">';
                                            diagnosis_content_html += '            <div>';
                                            diagnosis_content_html += '                <span>录入日期：'
                                                            + v.inputDateShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;过敏源：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.allergensShow + (v.otherAllergens ? '：' + v.otherAllergens : '') + '</label></span>';
                                            diagnosis_content_html += '                <span class="m-l-30">名        称：' + v.name + '</span>';
                                            diagnosis_content_html += '            </div>';
                                            diagnosis_content_html += '            <div>备注：' + v.remark + '</div>';
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '    </div>';
                                            if (list.length > 1 && i != list.length - 1) {
                                                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
                                            }
                                        });
        $(".data-view-" + diagnosis_hist_actions[5].itemCode + ":eq(1)").html(diagnosis_content_html);
    } else {
        // $(".data-view-" + diagnosis_hist_actions[5].itemCode).hide();
        var diagnosis_content_html = '';
        diagnosis_content_html += '    <div class="clearfix">';
        diagnosis_content_html += '        <div class="pull-left">暂无' + diagnosis_hist_actions[5].itemName + '数据</div>';
        diagnosis_content_html += '    </div>';
        $(".data-view-" + diagnosis_hist_actions[5].itemCode + ":eq(1)").html(diagnosis_content_html);
    }
}
/**
 * 渲染'传染病史'内容
 * 
 * @param data
 */
function buildDiagnosisHistPestilenceData(data) {
    var list = data.items.hist_pestilence || [];
    if (list.length > 0) {
        var diagnosis_content_html = '';
        $
                        .each(
                                        list,
                                        function(i, v) {
                                            diagnosis_content_html += '    <div class="clearfix">';
                                            diagnosis_content_html += '        <div class="pull-right">';
                                            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.operatorName + '</span>';
                                            if (!patientHasOutCome) {
                                                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis('
                                                                + v.id
                                                                + ', '
                                                                + v.fkPatientId
                                                                + ', \'hist_pestilence\', \''
                                                                + diagnosis_hist_actions[6].itemCode
                                                                + '\', \''
                                                                + diagnosis_hist_actions[6].itemName
                                                                + '\')">删除</span>';
                                                var dialogType = 'diagnosis' + upperFirstLetter('hist_pestilence'.split('_'));
                                                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="showDiagnosisDialog('
                                                                + v.id + ', ' + v.fkPatientId + ', \'hist_pestilence\', \'' + dialogType
                                                                + '\')">编辑</span>';
                                            }
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '        <div class="pull-left">';
                                            diagnosis_content_html += '            <div>';
                                            diagnosis_content_html += '                <span>诊断日期：' + v.diagnosticDateShow
                                                            + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;诊断名称：';
                                            if (v.diagnosticNameShow && v.diagnosticNameShow.split(',').length > 0) {
                                                $
                                                                .each(
                                                                                v.diagnosticNameShow.split(','),
                                                                                function(j, name) {
                                                                                    diagnosis_content_html += '                    <label class="form-span"><input type="checkbox" class="u-checkbox-1" checked="" disabled="disabled">'
                                                                                                    + name + '</label>';
                                                                                });
                                                diagnosis_content_html += (v.otherDiagnosticName ? '：' + v.otherDiagnosticName : '');
                                            }
                                            diagnosis_content_html += '                </span>';
                                            diagnosis_content_html += '                <span class="m-l-30">活动状态：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.activityStateShow
                                                            + '</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;治疗情况：<label class="form-span"><input type="radio" class="u-radio-1" checked="" disabled="disabled">'
                                                            + v.treatmentShow + (v.otherTreatment ? '：' + v.otherTreatment : '') + '</label></span>';
                                            diagnosis_content_html += '            </div>';
                                            diagnosis_content_html += '            <div>备注：' + v.remark + '</div>';
                                            diagnosis_content_html += '        </div>';
                                            diagnosis_content_html += '    </div>';
                                            if (list.length > 1 && i != list.length - 1) {
                                                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
                                            }
                                        });
        $(".data-view-" + diagnosis_hist_actions[6].itemCode + ":eq(1)").html(diagnosis_content_html);
    } else {
        // $(".data-view-" + diagnosis_hist_actions[6].itemCode).hide();
        var diagnosis_content_html = '';
        diagnosis_content_html += '    <div class="clearfix">';
        diagnosis_content_html += '        <div class="pull-left">暂无' + diagnosis_hist_actions[6].itemName + '数据</div>';
        diagnosis_content_html += '    </div>';
        $(".data-view-" + diagnosis_hist_actions[6].itemCode + ":eq(1)").html(diagnosis_content_html);
    }
}

/**
 * 渲染“肿瘤史”
 * 
 * @param data
 */
function buildDiagnosisHistTumourData(data) {
    var list = data.items.hist_tumour || [];
    var action = diagnosis_hist_actions[7];
    var name = action.itemName;
    var code = action.itemCode;
    var type = action.diagnosisType;
    var html = '';
    if (list.length > 0) {
        $.each(list, function(i, v) {
            html += '<div class="clearfix">';
            html += ' <div class="pull-right">';
            html += '  <span class="m-r-5">记录：' + v.createTimeShow + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + v.createUserName + '</span>';
            if (!patientHasOutCome) {
                html += '  <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis(' + v.id + ', ' + v.fkPatientId + ', \'' + type
                                + '\', \'' + code + '\', \'' + name + '\');">删除</span>';
                html += '  <span class="u-btn-24-grey" onclick="showDiagnosisDialog(' + v.id + ', ' + v.fkPatientId + ', \'' + type + '\', \''
                                + action.dialogType + '\')">编辑</span>';
            }
            html += ' </div>';
            html += ' <div class="pull-left">';
            html += '  <div>';
            html += '    <span>诊断日期：' + convertEmpty(v.recordDateShow) + '</span>';
            html += '  </div>';
            html += '  <div>诊断类型：' + convertEmpty(v.recordType) + '</div>';
            html += ' </div>';
            html += '</div>';
            if (i != list.length - 1) {
                // 分割虚线
                html += '<div class="f-dashed m-t-10 m-b-10"></div>';
            }
        });
    } else {
        html += '<div class="clearfix">';
        html += '  <div class="pull-left">暂无' + name + '数据</div>';
        html += '</div>';
    }
    $(".data-view-" + code + ":eq(1)").html(html);
}
/**
 * 渲染'诊断选项'内容
 * 
 * @param data
 */
function buildDiagnosisDiagnosisEntityData(data) {
    var list = data.items.diagnosis_entity || [];
    if (list.length > 0) {
        if (data.itemCode) {
            var dictDiagnosis = {};
            $.each(dictDiagnosisList, function(i, dict) {
                if (dict.itemCode == data.itemCode) {
                    dictDiagnosis = dict;
                    return false;
                }
            });
            // 从当前诊断树结构中设置当前勾选的内容
            if (list.length > 0)
                buildDiagnosisChildrenView(dictDiagnosis, list);
        } else {
            $.each(dictDiagnosisList, function(i, dict) {
                var entities = [];
                $.each(list, function(j, entity) {
                    if (entity.itemCode == dict.itemCode) {
                        entities.push(entity);
                    }
                });
                if (entities.length > 0)
                    buildDiagnosisChildrenView(dict, entities);
            });
        }
    } else {
        /*if(data.itemCode){
            $(".data-view-" + data.itemCode).hide();
        }else{
            $.each(dictDiagnosisList, function(i, dict){
                $(".data-view-" + dict.itemCode).hide();
            });
        }*/
        if (data.itemCode) {
            $.each(dictDiagnosisList, function(i, dict) {
                if (dict.itemCode == data.itemCode) {
                    if (data.itemCode == 'BS') {
                        var diagnosis_content_html = '';
                        diagnosis_content_html += '    <div class="clearfix">';
                        diagnosis_content_html += '        <div class="pull-left">暂无其他病史数据</div>';
                        diagnosis_content_html += '    </div>';
                        $(".data-view-" + data.itemCode + ":eq(1)").html(diagnosis_content_html);
                    } else {
                        var diagnosis_content_html = '';
                        diagnosis_content_html += '    <div class="m-t-5 u-card-1">';
                        diagnosis_content_html += '        <div class="clearfix">';
                        diagnosis_content_html += '            <div class="pull-left">暂无' + dict.itemName + '数据</div>';
                        diagnosis_content_html += '        </div>';
                        diagnosis_content_html += '    </div>';
                        $(".data-view-" + data.itemCode + ":eq(0)").html(diagnosis_content_html);
                    }
                }
            });
        }
    }
}

function buildDiagnosisChildrenView(dict, list) {
    if (dict.itemCode == 'BS') {
        $(".data-view-" + dict.itemCode + ":eq(1)").html('');
        $.each(list, function(i, v) {
            var diagnosis_content_html = '';
            diagnosis_content_html += '    <div class="clearfix">';
            diagnosis_content_html += '        <div class="pull-right">';
            diagnosis_content_html += '            <span class="m-r-5">记录：' + v.createTimeShow + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
                            + v.operatorName + '</span>';
            if (!patientHasOutCome) {
                diagnosis_content_html += '            <span class="u-btn-24-red m-r-5 fc-red" onclick="removeDiagnosis(' + v.id + ', '
                                + v.fkPatientId + ', \'diagnosis_entity\', \'' + dict.itemCode + '\', \'' + dict.itemName + '\')">删除</span>';
                diagnosis_content_html += '            <span class="u-btn-24-grey" onclick="ShowDiagnosisEntityTab(' + v.id + ', ' + v.fkPatientId
                                + ', \'' + dict.itemCode + '\', \'' + dict.itemName + '\')">编辑</span>';
            }
            diagnosis_content_html += '        </div>';
            diagnosis_content_html += '        <div class="pull-left">';
            diagnosis_content_html += '            <div class="data-view-' + dict.itemCode + '-' + (i + 1) + '">';
            diagnosis_content_html += '            </div>';
            diagnosis_content_html += '        </div>';
            diagnosis_content_html += '    </div>';
            if (list.length > 1 && i != list.length - 1) {
                diagnosis_content_html += '    <div class="f-dashed m-t-10 m-b-10"></div><!-- 分割虚线 -->';
            }
            $(".data-view-" + dict.itemCode + ":eq(1)").append(diagnosis_content_html);
            // 定义子元素
            var children_content_html = $("<ul data-item-level='root'></ul>");
            // 生成children对应的值，多级递归
            showChildrenContent(dict.childrens, children_content_html, v);
            $(".data-view-" + dict.itemCode + "-" + (i + 1)).append(children_content_html);
        });
    } else {
        $(".data-view-" + dict.itemCode + ":eq(0)").html('');
        $.each(list, function(i, v) {
            var diagnosis_content_html = '';
            diagnosis_content_html += '<div class="m-t-12 m-l-26 p-relative">';
            diagnosis_content_html += '    <span class="u-bround-9 bg-grey"></span>';
            diagnosis_content_html += '    <span class="m-l-10">' + v.createTimeShow + '</span>';
            diagnosis_content_html += '    <span>医生：' + v.operatorName + '</span>';
            diagnosis_content_html += '</div>';
            diagnosis_content_html += '<div class="m-t-10 u-card-1">';
            diagnosis_content_html += '    <div>';
            if (!patientHasOutCome) {
                diagnosis_content_html += '        <span class="u-btn-24-grey pull-right m-l-10" onclick="ShowDiagnosisEntityTab(' + v.id + ', '
                                + v.fkPatientId + ', \'' + dict.itemCode + '\', \'' + dict.itemName + '\')">编辑</span>';
                diagnosis_content_html += '        <span class="u-btn-24-red pull-right fc-red" onclick="removeDiagnosis(' + v.id + ', '
                                + v.fkPatientId + ', \'diagnosis_entity\', \'' + dict.itemCode + '\', \'' + dict.itemName + '\')">删除</span>';
            }
            diagnosis_content_html += '    </div>';
            diagnosis_content_html += '    <div class="data-view-' + dict.itemCode + '-' + (i + 1) + '">';
            diagnosis_content_html += '    </div>';
            diagnosis_content_html += '</div>';
            $(".data-view-" + dict.itemCode + ":eq(0)").append(diagnosis_content_html);
            // 定义子元素
            var children_content_html = $("<ul data-item-level='root'></ul>");
            // 生成children对应的值，多级递归
            showChildrenContent(dict.childrens, children_content_html, v);
            $(".data-view-" + dict.itemCode + "-" + (i + 1)).append(children_content_html);
        });
        if ($(".data-view-" + dict.itemCode + ":eq(0) .u-card-1").length > 1) {
            $(".data-view-" + dict.itemCode + ":eq(0) span.u-bround-9:eq(0)").addClass("bg-blue").removeClass("bg-grey");
        }
    }
}

// dict_list为json数据
// parent为要组合成html的容器
function showChildrenContent(dict_list, parent, entity) {
    $.each(dict_list, function(i, dict) {
        // 如果有子节点，则遍历该子节点
        if (dict.childrens && dict.childrens.length > 0) {
            // 创建一个子节点li
            var li = $("<li></li>");
            // 将li的文本设置好，并马上添加一个空白的ul子节点，并且将这个li添加到父亲节点中
            var itemName = dict.itemName.replace('？', '') + '：';
            var ul = "<ul style='display: initial;' data-item-level='" + dict.itemLevel + "' data-item-code='" + dict.itemCode + "'></ul>";
            if (dict.itemType && dict.itemType == 'radio') {
                itemName = dict.itemName;
                ul = "<ul data-item-level='" + dict.itemLevel + "' data-item-code='" + dict.itemCode + "'></ul>";
            }
            if (dict.itemCode.indexOf('CKDAKI') >= 0 && dict.itemLevel > 3) {
                itemName = '<label class="form-span"><input type="' + dict.itemType + '" class="u-' + dict.itemType
                                + '-1" checked="" disabled="disabled">' + dict.itemName + '</label>';
            }
            $(li).append(itemName).append(ul).appendTo(parent);
            // 将空白的ul作为下一个递归遍历的父亲节点传入
            showChildrenContent(dict.childrens, $(li).children().eq(0), entity);
        }
        // 如果该节点没有子节点，则直接将该节点li以及文本创建好直接添加到父亲节点中
        else {
            if (entity.valueList && entity.valueList.length > 0) {
                var entityValue = null;
                $.each(entity.valueList, function(j, value) {
                    // 判断当前诊断中是否保存了当前的节点值
                    if (value.itemCode == dict.itemCode) {
                        entityValue = value;
                        return false;
                    }
                });
                if (entityValue != null) { // 如果匹配到了，则生成对应的li节点
                    var content = '';
                    if (entityValue.itemValue) {
                        content = entityValue.itemValue + (entityValue.content ? ' : ' + entityValue.content : '');
                    } else {
                        content = entityValue.content;
                    }
                    if (dict.itemType == 'radio' || dict.itemType == 'checkbox') {
                        content = '<label class="form-span"><input type="' + dict.itemType + '" class="u-' + dict.itemType
                                        + '-1" checked="" disabled="disabled">' + content + '</label>';
                    }
                    $("<li style='display: initial; margin: 0 5px;'></li>").append(content).appendTo(parent);
                }
            }
        }
        if (i == dict_list.length - 1 && $(parent).find("li").length == 0) {
            parent.parent().remove();
        }
    });
}
function removeDiagnosis(id, patientId, diagnosisType, itemCode, itemName) {
    var diagnosisTypeObj = {};
    $.each(diagnosis_types, function(i, v) {
        if (v.type == diagnosisType) {
            diagnosisTypeObj = v;
        }
    });
    var url = "";
    var data = "";
    switch (diagnosisTypeObj.value) {
    case 1: // 渲染'首次透析'内容
        url = ctx + "/patient/diagnosis/deleteMedicalHistory.shtml";
        data = "id=" + id + "&patientId=" + patientId;
        break;
    case 2: // 渲染'手术史'内容
        url = ctx + "/patient/diagnosis/deleteHistSurgery.shtml";
        data = "id=" + id + "&patientId=" + patientId;
        break;
    case 3: // 渲染'血透史'内容
        url = ctx + "/patient/diagnosis/deleteHistHd.shtml";
        data = "id=" + id + "&patientId=" + patientId;
        break;
    case 4: // 渲染'腹透史'内容
        url = ctx + "/patient/diagnosis/deleteHistPd.shtml";
        data = "id=" + id + "&patientId=" + patientId;
        break;
    case 5: // 渲染'肾移植史'内容
        url = ctx + "/patient/diagnosis/deleteHistKt.shtml";
        data = "id=" + id + "&patientId=" + patientId;
        break;
    case 6: // 渲染'过敏史'内容
        url = ctx + "/patient/diagnosis/deleteHistAllergy.shtml";
        data = "id=" + id + "&patientId=" + patientId;
        break;
    case 7: // 渲染'传染病史'内容
        url = ctx + "/patient/diagnosis/deleteHistPestilence.shtml";
        data = "id=" + id + "&patientId=" + patientId;
        break;
    case 8: // 渲染'诊断选项'内容
        url = ctx + "/patient/diagnosis/deleteDiagnosisEntity.shtml";
        data = "id=" + id + "&patientId=" + patientId + "&itemName=" + itemName;
        break;
    case 9: // 渲染'肿瘤史'内容
        url = ctx + "/patient/diagnosis/deleteHistTumour.shtml";
        data = "id=" + id + "&patientId=" + patientId + "&itemName=" + itemName;
        break;
    default:
        break;
    }

    showConfirm('确定要删除' + itemName + '诊断信息吗？', function() {
        SystemDialog.modal('hide');
        $.ajax({
            url : url,
            data : data,
            type : "post",
            dataType : "json",
            loading : true,
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (diagnosisTypeObj.value == 1) {
                        $(".u-addUl li").eq(0).show();
                    }
                    loadDiagnosisData(diagnosisType, itemCode);
                }
            }
        });
    });
}
/* 设置时间 */
function addDate(dom, minDateElement, maxDateElement) {
    var minDate = minDateElement != '' > 0 ? $("#" + minDateElement).val() : false;
    var maxDate = maxDateElement != '' > 0 ? $("#" + maxDateElement).val() : false;
    $(dom).daterangepicker({
        "singleDatePicker" : true,
        "showDropdowns" : true,
        "autoUpdateInitInput" : false,
        "minDate" : minDate,
        "maxDate" : maxDate,
        "locale" : {
            format : "YYYY-MM-DD"
        }
    });

}

// //////////////////////////////////////////// 其他诊断选项内容的创建实现 /////////////////////////////////////////////
/**
 * 显示诊断对应的tab按钮内容
 * 
 * @param id
 * @param patientId
 * @param itemCode
 * @param itemName
 * @constructor
 */
function ShowDiagnosisEntityTab(id, patientId, itemCode, itemName) {
    $(".diagnosis-btns > button").removeClass("u-btn-blue");
    // 如果当前打开过diagnosis-entity-tab,先移除后追加
    if ($(".diagnosis-btns").find(".diagnosis-entity-tab").length > 0) {
        $(".diagnosis-btns").find(".diagnosis-entity-tab").remove();
    }
    // 追加该诊断模块的diagnosis-entity-tab按钮
    var html = '';
    html += '<button type="button" class="mr-10 diagnosis-entity-tab" data-permission-key="diagnosis_tab" id="tab-' + itemCode + '" data-item-code="'
                    + itemCode + '">';
    html += '   ' + (id == '' ? '新增' : '编辑') + itemName + '<i class="icon-close ml-14 fs-12"></i>';
    html += '</button>';
    $(".diagnosis-btns").append(html);
    // 展示该诊断对应的tab形式的表单内容
    showDiagnosisTabView(id, patientId, itemCode, itemName);
    $(window.document.body).animate({
        scrollTop : 0
    }, 100);
}
/**
 * 加载itemCode对应的诊断信息的tab内容
 * 
 * @param id
 * @param patientId
 * @param itemCode
 * @param itemName
 */
function showDiagnosisTabView(id, patientId, itemCode, itemName) {
    // 显示当前tab布局，隐藏其他诊断的list布局
    $(".diagnosis-content > div#diagnosis_tab").show().siblings().hide();
    $("#diagnosisEntityForm input[name='id']").val(id);
    $("#diagnosisEntityForm input[name='fkPatientId']").val(patientId);
    $("#diagnosisEntityForm input[name='itemCode']").val(itemCode);
    $("#diagnosisEntityForm input[name='itemName']").val(itemName);
    $("#diagnosisEntityForm").data("item-code", itemCode);
    if (id != '') {
        $.ajax({
            url : ctx + "/patient/diagnosis/search.shtml",
            data : "patientId=" + patientId + "&diagnosisType=diagnosis_entity&id=" + id,
            type : "post",
            dataType : "json",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        buildTreeContent(itemCode, data.item);
                    }
                }
                return false;
            }
        });
    } else {
        buildTreeContent(itemCode, null);
    }
}
function buildTreeContent(itemCode, entity) {
    $.each(dictDiagnosisList, function(i, dict) {
        if (dict.itemCode == itemCode) {
            // 定义子元素
            var children_content_html = $('<ul class="u-tree clearfix p-relative"></ul>');
            // 生成children对应的值，多级递归
            // 定义了编辑选中过的节点集合
            var checkedElements = [];
            $("#diagnosis_tab #tab_left").html('');
            $("#diagnosis_tab #tab_right").html('');
            showChildrenFormContent(dict.childrens, children_content_html, entity, checkedElements);
            $("#diagnosis_tab #tab_left").html(children_content_html);
            bindTreeEvent();
            // 生成所有拥有选项节点对应的右侧选项内容，即触发该li的单击事件
            $.each(checkedElements, function(i, element) {
                element.click();
            });
            var element;
            if (entity != null) {
                var element = $("#list_diagnosis_tab #tab_left").find("[data-hasChecked]")[0];
            } else {
                element = checkedElements[0];
            }
            // 如果编辑状态，则默认打开第一个有选项被选中的节点，否则默认打开第一个节点
            $(element).parent().parent().find("span").click();
            $(element).click();
            $(element).find("span").click();
        }
    });
}
// dict_list为json数据
// parent为要组合成html的容器
function showChildrenFormContent(dict_list, parent, entity, checkedElements) {
    var parentLiElement = parent.parent();
    $.each(dict_list, function(i, dict) {
        // 如果有子节点，则遍历该子节点
        if (dict.childrens && dict.childrens.length > 0) {
            // 创建一个子节点li
            var li = $('<li class="clearfix" data-item-code="' + dict.itemCode + '" data-item-name="' + dict.itemName + '"></li>');
            var liHtml = '<span>';
            liHtml += '    <img src="' + ctx + '/assets/img/ArtboardCopy1.png" alt="" width="21" height="12">';
            liHtml += '    ' + dict.itemName;
            liHtml += '</span>';
            // 将li的文本设置好，并马上添加一个空白的ul子节点，并且将这个li添加到父亲节点中
            var ul = "<ul class='u-tree clearfix p-relative' data-item-level='" + dict.itemLevel + "' data-item-code='" + dict.itemCode + "'></ul>";
            $(li).append(liHtml).append(ul).appendTo(parent);
            // 将空白的ul作为下一个递归遍历的父亲节点传入
            showChildrenFormContent(dict.childrens, $(li).children().eq(1), entity, checkedElements);
        }
        // 如果该节点没有子节点，则直接将该节点li以及文本创建好直接添加到父亲节点中
        else {
            parentLiElement.find("span > img").attr("src", ctx + "/assets/img/ArtboardCopy3.png");
            parentLiElement.find("span > img").attr("width", 12);
            parent.remove();
            var entityValue = null;
            if (entity != null && entity.valueList && entity.valueList.length > 0) {
                $.each(entity.valueList, function(j, value) {
                    // 判断当前诊断中是否保存了当前的节点值
                    if (value.itemCode == dict.itemCode) {
                        entityValue = value;
                        parentLiElement.attr("data-hasChecked", true);
                        return false;
                    }
                });
            }
            // 判断当前是否重复
            var locationIndex = $.inArray(parentLiElement, checkedElements);
            if (locationIndex == -1)
                checkedElements.push(parentLiElement);
            var newDict = {};
            newDict = dict;
            var allOptions = parentLiElement.data("allOptions");
            if (allOptions == undefined) {
                allOptions = [];
            }
            if (entityValue != null) { // 如果匹配到了，则生成对应的li节点
                newDict.checked = true;
                newDict.content = entityValue.content || '';
            } else {
                newDict.checked = false;
                newDict.content = '';
            }
            allOptions.push(newDict);
            parentLiElement.data("allOptions", allOptions);
        }
    });
    // 绑定树形的单击事件
    $(parentLiElement).bind(
                    "click",
                    function() {

                        var allOptions = $(this).data("allOptions");
                        if (allOptions != undefined) {
                            var itemName = $(this).data("item-name");
                            var itemCode = $(this).data("item-code");
                            if ($("#diagnosis_tab #tab_right").find("div[data-item-code='" + itemCode + "']").length > 0) {
                                $("#diagnosis_tab #tab_right").find("div[data-item-code='" + itemCode + "']").show().siblings().hide();
                            } else {
                                var tab_right_option_html = '';
                                tab_right_option_html += '<div data-item-code="' + itemCode + '" style="display: none;">';
                                tab_right_option_html += '  <div class="m-header-1">' + itemName;
                                tab_right_option_html += '  </div>';
                                tab_right_option_html += '  <div class="p-20 col-xs-12 clearfix">';
                                tab_right_option_html += '     <div>';
                                var tab_right_textarea_html = '';
                                $.each(allOptions, function(i, option) {
                                    if (option.itemType == 'textarea') {
                                        tab_right_option_html += '        <div class="col-xs-12 mp-0 text-left m-b-15">';
                                        tab_right_option_html += '            <input type="hidden" name="valueMap[\'' + itemCode + '\'][' + i
                                                        + '].itemCode" value="' + option.itemCode + '">';
                                        tab_right_option_html += '            <textarea class="form-control" id="' + itemCode + '_' + i
                                                        + '" name="valueMap[\'' + itemCode + '\'][' + i
                                                        + '].content" maxlength="256" placeholder="备注">' + (option.content || '') + '</textarea>';
                                        tab_right_option_html += '        </div>';
                                    } else {
                                        tab_right_option_html += '        <div class="col-xs-3 mp-0 text-left m-b-15">';
                                        tab_right_option_html += '            <label for="' + itemCode + '_' + i + '" class="p-b-5">';
                                        if (option.itemType == 'radio') {
                                            tab_right_option_html += '            <input id="' + itemCode + '_' + i + '" type="' + option.itemType
                                                            + '" class="u-' + option.itemType + '-1" name="valueMap[\'' + itemCode
                                                            + '\'][0].itemValue" ' + (option.checked ? 'checked' : '') + ' value="' + option.itemCode
                                                            + '_' + option.itemName + '" data-item-code="' + itemCode
                                                            + '" onclick="changeOptionValue(this, \'' + itemCode + '\', \'' + option.itemName
                                                            + '\', ' + allOptions.length + ')">';
                                        } else {
                                            tab_right_option_html += '            <input id="' + itemCode + '_' + i + '" type="' + option.itemType
                                                            + '" class="u-' + option.itemType + '-1" name="valueMap[\'' + itemCode + '\'][' + i
                                                            + '].itemValue" ' + (option.checked ? 'checked' : '') + ' value="' + option.itemCode
                                                            + '_' + option.itemName + '" data-item-code="' + itemCode
                                                            + '" onclick="changeOptionValue(this, \'' + itemCode + '\', \'' + option.itemName
                                                            + '\', ' + allOptions.length + ')">';
                                        }
                                        tab_right_option_html += '               ' + option.itemName;
                                        tab_right_option_html += '            </label>';
                                        tab_right_option_html += '        </div>';
                                        if (option.itemName == '其他' && itemCode.indexOf('HBZ') == -1) {
                                            var hasHide = option.checked ? '' : 'hide';
                                            tab_right_textarea_html = '     <div class="w-10 f-both ">';
                                            tab_right_textarea_html += '         <textarea class="form-control ' + hasHide + '" id="' + itemCode
                                                            + '_' + allOptions.length + '" name="valueMap[\'' + itemCode + '\']['
                                                            + (allOptions.length - 1) + '].content" maxlength="256" placeholder="' + option.itemName
                                                            + '">' + (option.content || '') + '</textarea>';
                                            tab_right_textarea_html += '     </div>';
                                        }
                                    }

                                });
                                tab_right_option_html += '     </div>';
                                tab_right_option_html += tab_right_textarea_html;
                                tab_right_option_html += '  </div>';
                                tab_right_option_html += '</div>';
                                $("#diagnosis_tab #tab_right").append(tab_right_option_html);
                            }
                        }
                    });
}
// 选项input框单击事件
function changeOptionValue(obj, itemCode, itemName, len) {
    if (itemName.indexOf('其他') >= 0 && itemCode.indexOf('HBZ') == -1) {
        $("#diagnosisEntityForm textarea[id='" + itemCode + "_" + len + "']").html('');
        if ($(obj).is(":checked")) {
            $("#diagnosisEntityForm textarea[id='" + itemCode + "_" + len + "']").attr("placeholder", itemName);
            $("#diagnosisEntityForm textarea[id='" + itemCode + "_" + len + "']").removeClass('hide').addClass('show');
        } else {
            $("#diagnosisEntityForm textarea[id='" + itemCode + "_" + len + "']").removeClass('show').addClass('hide');
        }
    } else if (itemCode.indexOf('CKDAKI') >= 0) { // CKD/AKI诊断，则执行联动处理，切换节点时取消其他节点的选中值
        $("#list_diagnosis_tab #tab_right").find("[type='radio'][data-item-code != '" + itemCode + "']").attr("checked", false);
    }
}
// 保存
function saveDiagnosisEntity(form) {
    if ($(form).valid()) {
        var options = {
            url : ctx + "/patient/diagnosis/saveDiagnosisEntity.shtml",
            dataType : "json",
            loading : true,
            // async : false,
            loadingMsg : "正在保存，请稍等...",
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $(".diagnosis-btns").find(".icon-close").click();
                        var itemCode = $("#diagnosisEntityForm").data("item-code");
                        $("#diagnosis_tab #tab_left").html('');
                        $("#diagnosis_tab #tab_right").html('');
                        loadDiagnosisData('diagnosis_entity', itemCode);
                    } else {
                        showWarn(data.message);
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
