$(function() {
    setAssayTopActive("hosp_dict");
    $("#addMaxValue").change(function() {
        changeValue();
    });
    $("#addMinValue").change(function() {
        changeValue();
    });
    $("#addUnit").change(function() {
        changeValue();
    });

    $.validator.addMethod("numChexckShow", function(value, element, params) {
        var returnVal = false;
        var minValue = $("input[name='minValueShow']").val();
        var maxValue = $("input[name='maxValueShow']").val();
        if (parseInt(maxValue) < parseInt(minValue)) {
            returnVal = false;
        } else {
            returnVal = true;
        }
        return returnVal;
    }, jQuery.validator.format("最”大“值要大于最”小“值"));
    getGroupNameSelect();
});
function changeValue() {
    var maxValue = $("#addMaxValue").val();
    var minValue = $("#addMinValue").val();
    var unit = $("#addUnit").val();
    if (isEmpty(maxValue) && !isEmpty(minValue)) {
        $("#addReference").val(">" + minValue);
    }
    if (isEmpty(minValue) && !isEmpty(maxValue)) {
        $("#addReference").val("<" + maxValue);
    }
    if (!isEmpty(maxValue) && !isEmpty(minValue)) {
        $("#addReference").val(minValue + "~" + maxValue);
    }
    if (isEmpty(maxValue) && isEmpty(minValue)) {
        $("#addReference").val("");
    }
}
/** 显示添加项dialog */
function showAddDialog(dialogId) {
    $("#addDictionaryForm")[0].reset();
    $("#" + dialogId).modal("show");
    if ("addDictHospital" == dialogId) {
        $("#addDictionaryForm")[0].reset();
        addValidate("addDictionaryForm");
    }
    if ("addDictionaryForm" == dialogId) {
        $("#addGroupDictionaryForm")[0].reset();
        addValidate("addGroupDictionaryForm");
    }
    getGroupNameSelect();
}
/*
 * 保存化验单名称
 */
function addGroupAssayMaintain() {
    if (!$('#addGroupDictionaryForm').valid()) {
        return false;
    }
    $.ajax({
        url : ctx + "/assay/hospDict/insertGroupName.shtml",
        type : "post",
        data : $("#addGroupDictionaryForm").serialize(),
        dataType : "json",
        success : function(data) {
            if (data.status == "1") {
                showTips("保存成功");
                $("#addGroupDictHospital").modal("hide");
                referenceShow();
                $("#dictHospital").show();
            } else {
                showError(data.errmsg);
            }
        }
    });
}
/**
 * 添加化验单
 */
function addAssayMaintain() {
    if (!$('#addDictionaryForm').valid()) {
        return false;
    }
    $.ajax({
        url : ctx + "/assay/hospDict/insertSelective.shtml",
        type : "post",
        data : $("#addDictionaryForm").serialize(),
        dataType : "json",
        success : function(data) {
            if (data.status == "1") {
                showTips("保存成功");
                $("#addDictHospital").modal("hide");
                referenceShow();
            } else {
                showError(data.errmsg);
            }
        }
    });
}
/**
 * 删除化验单
 * 
 * @param dom
 */
function removeAssayMaintain(dom) {
    var index = $(dom).attr("data-index");
    var itemCode = $("#itemCode" + index).val();
    var groupId = $("#groupId" + index).val();
    $.ajax({
        url : ctx + "/assay/hospDict/deleteById.shtml",
        type : "post",
        data : "itemCode=" + itemCode + "&groupId=" + groupId,
        dataType : "json",
        success : function(data) {
            showTips("删除成功");
            referenceShow();
        }
    });
}
/**
 * 更新化验单
 * 
 * @param dom
 */
function updateAssay(dom) {
    var id = $(dom).attr("data-id");
    var index = $(dom).attr("data-index");
    var groupName = $("#groupName" + index).val();
    var itemCode = $("#itemCode" + index).val();
    var itemName = $("#itemName" + index).val();
    var minValue = $("#minValue" + index).val();
    var maxValue = $("#maxValue" + index).val();
    var unit = $("#unit" + index).val();
    var reference = $("#reference" + index).val();
    var valueType = $("#valueType" + index).val();
    var groupId = $("#groupId" + index).val();
    var oldItemCode = $("#oldItemCode" + index).val();
    var datas = "id=" + id + "&groupName=" + groupName + "&itemCode=" + itemCode + "&itemName=" + itemName + "&minValue=" + minValue + "&maxValue="
                    + maxValue + "&unit=" + unit + "&reference=" + reference + "&valueType=" + valueType + "&groupId=" + groupId + "&oldItemCode="
                    + oldItemCode;
    $.ajax({
        url : ctx + "/assay/hospDict/updateAssay.shtml",
        type : "post",
        data : datas,
        dataType : "json",
        success : function(data) {
            if (data.status == "1") {
                showTips("更新成功");
                referenceShow();
            } else {
                showError(data.errmsg);
            }
        }
    });
}

/**
 * 刷新数据
 */
function referenceShow() {
    $.ajax({
        url : ctx + "/assay/hospDict/listAllManualAdd.shtml",
        type : "post",
        dataType : "json",
        success : function(data) {
            if (data.status) {
                var list = data.allDictHospitalLabPO;
                var tHtml = "";
                for (var i = 0; i < list.length; i++) {
                    var item = list[i];
                    tHtml += '<tr class="table-default">';
                    tHtml += '<td><input type="hidden" name ="oldItemCode" id = "oldItemCode' + i + '" value ="' + item.itemCode
                                    + '" ><input type="hidden" name ="groupId" id = "groupId' + i + '" value ="' + item.groupId
                                    + '" ><input type="text" style="width: 150px;" class="tl-input pull-left"  readonly="readonly" id="groupName' + i
                                    + '" value="' + item.groupName + '" /></td>';
                    tHtml += '<td><input type="text" class="tl-input pull-left" id="itemName' + i + '" value="' + item.itemName + '" /></td>';
                    tHtml += '<td><input type="text" class="tl-input pull-left" id="itemCode' + i + '" value="' + item.itemCode + '" /></td>';
                    tHtml += '<td><input type="text" class="tl-input pull-left" id="minValue' + i + '" value="'
                                    + (isEmpty(item.minValue) ? '' : (item.minValue).toFixed(3)) + '"/>';
                    tHtml += '<input type="text" class="tl-input pull-left" id="maxValue' + i + '" value="'
                                    + (isEmpty(item.maxValue) ? '' : (item.maxValue).toFixed(3)) + '"/></td>';
                    tHtml += '<td><input type="text" class="tl-input pull-left" id="unit' + i + '" value="' + item.unit + '" /></td>';
                    tHtml += '<td><input type="text" class="tl-input pull-left" id="reference' + i + '" value="' + item.reference + '" /></td>';
                    if (item.valueType == '1') {
                        tHtml += '<td>' + '<select id="valueType' + i
                                        + '" style="width:120px;height:24px;border-radius:0px;-webkit-appearance:block;">'
                                        + '<option value="1" selected="selected">数字</option>' + '<option value="2">文本</option>' + '</select>'
                                        + '</td>';
                    } else {
                        tHtml += '<td>' + '<select id="valueType' + i
                                        + '" style="width:120px;height:24px;border-radius:0px;-webkit-appearance:block;">'
                                        + '<option value="1">数字</option>' + '<option value="2" selected="selected">文本</option>' + '</select>'
                                        + '</td>';
                    }
                    tHtml += '<td class="text-right"><input type="button" value="更新" data-id="' + item.id + '" data-index="' + i
                                    + '" onclick="updateAssay(this);" class="btn btn-def" />' + '<input type="button" value="删除" data-id="' + item.id
                                    + '" data-index="' + i + '"  onclick="removeAssayMaintain(this);" class="btn btn-def" /></td>';
                    tHtml += '</tr>';
                }
            }
            $("#loadInit").html(tHtml);
        }
    });
}
function addValidate(formId) {
    $.validator.addMethod("numChexck", function(value, element, params) {
        var returnVal = false;
        var minValue = $("#addMinValue").val();
        var maxValue = $("#addMaxValue").val();
        if (parseInt(maxValue) < parseInt(minValue)) {
            returnVal = false;
        } else {
            returnVal = true;
        }
        return returnVal;
    }, jQuery.validator.format("最”大“值要大于最”小“值"));
    $('#' + formId).validate({
        onfocusout : false,
        rules : {
            groupName : {
                required : [ "化验单名称" ]
            },
            itemName : {
                required : [ "项目名称" ]
            },
            itemCode : {
                required : [ "项目代号" ]
            },
            minValue : {
                number : [ "输入有误" ]
            },
            maxValue : {
                number : [ "输入有误" ],
                numChexck : true
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
            $(error[0]).css("padding-right", "30px");
            obj.find("[data-error]").append(error);
        }
    });
};
// 新增dailg化验单名称下拉单生成
function getGroupNameSelect() {
    $.ajax({
        url : ctx + "/assay/hospDict/listManualAddGroup.shtml",
        type : "post",
        dataType : "json",
        success : function(data) {
            if (data.status) {
                var list = data.items;
                var tHtml = "";
                for (var i = 0; i < list.length; i++) {
                    tHtml += '<option value="' + list[i].groupId + '">' + list[i].groupName + '</option>'
                }
                if (list.length == 0) {
                    $("#dictHospital").hide();
                } else {
                    $("#dictHospital").show();
                }
                $("#addDictionaryForm #groupId").html(tHtml);
            }
        }

    })
}
