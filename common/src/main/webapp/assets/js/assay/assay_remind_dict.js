/* 参数 */
var initRoleNodes = [];
var delRoleIds = [];
var removedIds = [];
var selectedRoleNode;
// DictHospitalLab所有数据
var allDictHospitalLabPOData = '';
$(document).ready(function() {
    $("input[daterangepicker]").daterangepicker({
        "singleDatePicker" : true,
        "autoUpdateInput" : true,
        "autoUpdateInitInput" : false,
        "showDropdowns" : true,
        "clearBtn" : true,
        "alwaysShowCalendars" : true,
        "locale" : {
            format : "YYYY-MM-DD",
            applyLabel : "确认",
            cancelLabel : "取消",
            daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ]
        }
    });
    // resizeTree();
    $("#smallassayClass").attr("checked", "true");

    // 加载配置时间
    loadTimeConfigure();

    // 点击取消
    $("#cancle").click(function() {
        compositeFun($("[name='assayClass']:checked").val());
    });

});
// 复合方法
function compositeFun(assayClass) {

}

function resizeTree() {
    $(".ztree").css("height", ($(window).height() - 100) * 0.618);
    $(".tree-block").css("height", ($(window).height() - 100));
    $(".tree-block").css("max-width", ($(window).width() - 200) * 0.5);
};

// 查询所有DictHospitalLab
function loadDictHospitalLab() {
    var result = '';
    $.ajax({
        url : ctx + '/assay/assayRemindDict/selectAllDictHospitalLab.shtml',
        dataType : 'json',
        Type : 'POST',
        async : false,
        success : function(data) {
            result = data;
        }
    });
    return result;
}

// 保存病患化验类
function savePatientAssayClass() {

    $("#loading_center").show();

    var detailList = new Array();

    $("#resultList").find("input[name='itemCode']").each(function(i, obj) {
        detailList.push({
            /*assayClass : $("[name='assayClass']:checked").val(),*/
            itemCode : $(this).val(),
            itemName : $("#resultList").find("input[name='itemName']")[i].value,
            fkAssayGroupConfId : $("#resultList").find("input[name='fkAssayGroupConfId']")[i].value,
            fkAssayGroupConfName : $("#resultList").find("input[name='fkAssayGroupConfName']")[i].value,
            groupId : $("#resultList").find("input[name='groupId']")[i].value,
            groupName : $("#resultList").find("input[name='groupName']")[i].value,
            assayDay : $("#resultList").find("input[name='assayDay']")[i].value,
        });

    });
    var json = {
        /*assayClass : $("[name='assayClass']:checked").val(),*/
        detailList : detailList
    };
    $.ajax({
        url : ctx + '/assay/assayRemindDict/savePatientAssayClass.shtml',
        data : JSON.stringify(json),
        dataType : 'json',
        type : 'POST',
        loading : true,
        contentType : 'application/json;charset=utf-8',
        success : function(data) {
            $("#assayGroupDialog").modal("hide");
            loadAssayGroupList();
            showTips("保存成功");

            $("#loading_center").hide();
        }
    });
}

// 查询所有病患化验类
function loadPatientAssayClass(assayClass) {
    var patientAssayClassAllData = '';
    $.ajax({
        url : ctx + '/assay/assayRemindDict/selectAllPatientAssayClass.shtml',
        dataType : 'json',
        data : {
            "assayClass" : assayClass
        },
        type : 'POST',
        async : false,
        success : function(data) {
            patientAssayClassAllData = data;
        }
    });
    return patientAssayClassAllData;
}

// 加载patient_assay_month_r_scope
function loadPatientAssayMonth() {
    $.ajax({
        url : ctx + '/assay/assayRemindDict/selectAllMonth.shtml',
        dataType : 'json',
        type : 'POST',
        async : false,
        success : function(data) {
            var PatientAssayMonthRScopePOData = data.PatientAssayMonthRScopePOList;
            for (var i = 0; i < PatientAssayMonthRScopePOData.length; i++) {
                if (PatientAssayMonthRScopePOData[i].assayClass == $("#assayPatientMonthBody tr:eq(0) td:eq('" + (i + 1) + "') label input").val()) {
                    $("#assayPatientMonthBody tr:eq(0) td:eq('" + (i + 1) + "') label input").prop("checked", "true");
                } else if (PatientAssayMonthRScopePOData[i].assayClass == $("#assayPatientMonthBody tr:eq(1) td:eq('" + (i + 1) + "') label input")
                                .val()) {
                    $("#assayPatientMonthBody tr:eq(1) td:eq('" + (i + 1) + "') label input").prop("checked", "true");
                } else if (PatientAssayMonthRScopePOData[i].assayClass == $("#assayPatientMonthBody tr:eq(2) td:eq('" + (i + 1) + "') label input")
                                .val()) {
                    $("#assayPatientMonthBody tr:eq(2) td:eq('" + (i + 1) + "') label input").prop("checked", "true");
                }
            }
        }
    });
}

// 保存勾选的Month，assayClass
function saveMonthFun() {
    // 修改配置时间(patient_assay_time_scope)
    updateTimeConfigure();
    /*// 1yue
    var january = $("[name='january']:checked").val();
    // 2yue
    var february = $("[name='february']:checked").val();
    // 3yue
    var march = $("[name='march']:checked").val();
    // 4yue
    var april = $("[name='april']:checked").val();
    // 5yue
    var may = $("[name='may']:checked").val();
    // 6yue
    var june = $("[name='june']:checked").val();
    // 7yue
    var july = $("[name='july']:checked").val();
    // 8yue
    var august = $("[name='august']:checked").val();
    // 9yue
    var september = $("[name='september']:checked").val();
    // 10yue
    var october = $("[name='october']:checked").val();
    // 11yue
    var november = $("[name='november']:checked").val();
    // 12yue
    var december = $("[name='december']:checked").val();
    var assayClas = [ january, february, march, april, may, june, july, august, september, october, november, december ];
    $.ajax({
    	url : ctx + '/assay/assayRemindDict/saveAssayMonth.shtml',
    	dataType : 'json',
    	data : JSON.stringify(assayClas),
    	contentType : 'application/json;charset=utf-8',
    	type : 'POST',
    	async : false,
    	success : function(data) {
    		if (data.result == "success") {
    			showTips("保存成功！");
    			// 调用查询方法
    			loadPatientAssayMonth();
    		}
    	}
    });*/
}

/**
 * 加载时间配置
 */
function loadTimeConfigure() {
    $.ajax({
        url : ctx + '/patientAssayConf/view.shtml',
        dataType : 'json',
        type : 'POST',
        success : function(result) {
            var patientAssayConf = result.patientAssayConf;
            $("#confId").val(patientAssayConf.id);
            if (!isEmpty(patientAssayConf.endDate)) {
                $("#endDate").val(patientAssayConf.endDate);
            }
        }
    });
}

/**
 * 修改时间配置(patient_assay_time_scope)
 */
function updateTimeConfigure() {
    var endDate = $("#endDate").val();
    var confId = $("#confId").val();
    $.ajax({
        url : ctx + '/patientAssayConf/update.shtml',
        dataType : 'json',
        type : 'POST',
        async : false,
        data : {
            "id" : confId,
            "endDate" : endDate
        },
        success : function(result) {
        }
    });
}

/** ******************************重构 start************************************* */
$(function() {
    selectAssayByClass();
    // 点击右边的大血，小血，中血，
    /*$("[name='assayClass']").click(function() {*/
    selectAllByAssayClass();
    /*});*/
    // 加载配置月份(patientAssayMonth)
    /*loadPatientAssayMonth();*/

    loadAssayGroupList();
});

// 根据大中小血查询化验项及选中情况
function selectAllByAssayClass() {
    $.ajax({
        url : ctx + '/assay/assayRemindDict/selectAllByAssayClass.shtml',
        dataType : 'json',
        data : "assayClass=" + $("[name='assayClass']:checked").val(),
        type : 'POST',
        success : function(data) {

            var bodyHtml = "";
            /*trimJosnArray(data.list);*/
            if (data.list.length > 0) {
                for (var i = 0; i < data.list.length; i++) {
                    var item = data.list[i];
                    var hidden = '<input type="hidden" name="itemCode" value="' + item.itemCode + '">';
                    hidden += '<input type="hidden" name="itemName" value="' + item.itemName + '">';
                    hidden += '<input type="hidden" name="fkAssayGroupConfId" value="' + item.fkAssayGroupConfId + '">';
                    hidden += '<input type="hidden" name="fkAssayGroupConfName" value="' + item.fkAssayGroupConfName + '">';
                    hidden += '<input type="hidden" name="groupId" value="' + item.groupId + '">';
                    hidden += '<input type="hidden" name="groupName" value="' + item.groupName + '">';
                    bodyHtml += '<tr>';
                    bodyHtml += '<td class="text-center">' + hidden + item.itemName + item.fkAssayGroupConfName + '</td>';
                    bodyHtml += '<td class="text-center">' + item.groupName + '</td>';
                    bodyHtml += '<td class="text-center"><input type="text" name="assayDay" value="' + item.assayDay + '"></td>'
                    bodyHtml += '<td class="text-center">';
                    bodyHtml += '	<input type="button" class=" btn-def" onclick="$(this).parent().parent().remove()" value="删除">';
                    bodyHtml += '</td>';
                    bodyHtml += '</tr>';
                }
                $("#resultList").html(bodyHtml);
            }
        }
    });
}

// 根据大中小血查询化验项及选中情况
function selectAssayByClass() {
    $.ajax({
        url : ctx + '/assay/assayRemindDict/selectAssayByClass.shtml',
        dataType : 'json',
        data : {
            "assayClass" : $("[name='assayClass']:checked").val()
        },
        type : 'POST',
        success : function(data) {
            loadOperateTree(data.itemList);
        }
    });
}

// 加载左边操作树
function loadOperateTree(itemList) {
    var rootId = "root_" + new Date().getTime();
    var treeNodes = [ {
        id : rootId,
        pId : null,
        name : "检查项目字典",
        open : true,
        title : "检查项目字典",
        itemCode : ""
    } ];

    for (var i = 0; i < itemList.length; i++) {
        var item = itemList[i];
        var treeNode = null;
        // 如果是化验单
        if (item.categoryFlag) {
            treeNode = {
                id : item.groupId,
                pId : rootId,
                name : item.groupName,
                open : item.selectFlag,
                categoryFlag : item.categoryFlag,
                itemCode : "",
                itemName : "",
                groupId : item.groupId,
                groupName : item.groupName
            };
        } else {
            treeNode = {
                id : item.itemCode,
                pId : item.groupId,
                name : item.itemName + "（" + item.itemCode + "）",
                checked : item.selectFlag,
                categoryFlag : item.categoryFlag,
                itemCode : item.itemCode,
                itemName : item.itemName,
                groupId : item.groupId,
                groupName : item.groupName
            };
        }
        treeNodes.push(treeNode);
    }
    var treeSettings = {
        check : {
            enable : true
        },
        view : {
            selectedMulti : false
        },
        data : {
            key : {
                title : "title" // 设置title提示信息对应的属性名称
            },
            simpleData : {
                enable : true
            }
        },
        callback : {
            onClick : selectNode
        }
    };
    $.fn.zTree.init($("#dictHospitalLabTree"), treeSettings, treeNodes);
}

/**
 * 加载同类分组
 */
function loadAssayGroupList() {
    $.ajax({
        url : ctx + '/assay/assayRemindDict/selectAssayGroupConfAll.shtml',
        dataType : 'json',
        type : 'POST',
        success : function(data) {
            var bodyHtml = "";
            trimJosnArray(data.list);
            for (var i = 0; i < data.list.length; i++) {
                var item = data.list[i];
                bodyHtml += '<tr>';
                bodyHtml += '<td class="text-center">' + item.name + '</td>';
                bodyHtml += '<td class="text-center">';
                bodyHtml += '	<input type="button" class="btn-def" onclick="selectAssayGroup(' + item.id + ',\'' + item.name + '\')" value="选择">';
                bodyHtml += '	<input type="button" class="btn-def" onclick="showAssayGroupDialog(' + item.id + ')" value="编辑">';
                bodyHtml += '	<input type="button" class="btn-def" onclick="deleteAssayGroupConf(' + item.id + ')" value="删除">';
                bodyHtml += '</td>';
                bodyHtml += '</tr>';
            }
            $("#assayGroupList").html(bodyHtml);
        }
    });
}
/**
 * 选择同类化验分组
 * 
 * @param id
 * @param name
 */
function selectAssayGroup(id, name) {
    // $("[name='assayClass']:checked").length == 0 ||
    if (checkExistInSelected(null, id)) {
        return;
    }
    var hidden = '<input type="hidden" name="itemCode">';
    hidden += '<input type="hidden" name="itemName">';
    hidden += '<input type="hidden" name="fkAssayGroupConfId" value="' + id + '">';
    hidden += '<input type="hidden" name="fkAssayGroupConfName" value="' + name + '">';
    hidden += '<input type="hidden" name="groupId">';
    hidden += '<input type="hidden" name="groupName" value="同类化验分组">';
    var bodyHtml = '<tr>';
    bodyHtml += '<td class="text-center">' + hidden + name + '</td>';
    bodyHtml += '<td class="text-center">同类化验分组</td>';
    bodyHtml += '<td class="text-center"><input type="text" name="assayDay" value="30"></td>';
    bodyHtml += '<td class="text-center">';
    bodyHtml += '	<input type="button" class=" btn-def" onclick="$(this).parent().parent().remove()" value="删除">';
    bodyHtml += '</td>';
    bodyHtml += '</tr>';
    $("#resultList").append(bodyHtml);
}

/**
 * 选择化验项
 * 
 * @param event
 * @param treeId
 * @param treeNode
 */
function selectNode(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("dictHospitalLabTree");
    treeNode.checked = true;
    zTree.updateNode(treeNode)
    if (/*$("[name='assayClass']:checked").length == 0 || */checkExistInSelected(treeNode.itemCode)) {
        return;
    }
    var hidden = '<input type="hidden" name="itemCode" value="' + treeNode.itemCode + '">';
    hidden += '<input type="hidden" name="itemName" value="' + treeNode.itemName + '">';
    hidden += '<input type="hidden" name="fkAssayGroupConfId">';
    hidden += '<input type="hidden" name="fkAssayGroupConfName">';
    hidden += '<input type="hidden" name="groupId" value="' + treeNode.groupId + '">';
    hidden += '<input type="hidden" name="groupName" value="' + treeNode.groupName + '">';
    var bodyHtml = '<tr>';
    bodyHtml += '<td class="text-center">' + hidden + treeNode.itemName + '</td>';
    bodyHtml += '<td class="text-center">' + treeNode.groupName + '</td>';
    bodyHtml += '<td class="text-center"><input type="text" name="assayDay" value="30"></td>';
    bodyHtml += '<td class="text-center">';
    bodyHtml += '	<input id="del" type="button" class=" btn-def" onclick="$(this).parent().parent().remove() ,delAndCancel(\'' + treeNode.tId
                    + '\')" value="删除">';
    bodyHtml += '</td>';
    bodyHtml += '</tr>';
    $("#resultList").append(bodyHtml);
}
/**
 * 删除化验项同时去掉选中复选框
 * 
 * @param tid
 */
function delAndCancel(tid) {
    var zTree = $.fn.zTree.getZTreeObj("dictHospitalLabTree");
    var node = zTree.getNodeByTId(tid);
    node.checked = false;
    zTree.updateNode(node);
}
/**
 * 验证化验项或同类分组是否已存在
 * 
 * @param itemCode
 * @param fkAssayGroupConfId
 * @returns {Boolean}
 */
function checkExistInSelected(itemCode, fkAssayGroupConfId) {
    var exist = false;
    if (isEmpty(itemCode) && isEmpty(fkAssayGroupConfId)) {
        return true;
    }
    if (!isEmpty(itemCode)) {
        $("#resultList").find("input[name='itemCode']").each(function() {
            if (itemCode == $(this).val()) {
                exist = true;
                return;
            }
        });
    } else if (!isEmpty(fkAssayGroupConfId)) {
        $("#resultList").find("input[name='fkAssayGroupConfId']").each(function() {
            if (fkAssayGroupConfId == $(this).val()) {
                exist = true;
                return;
            }
        });
    }
    return exist;
}

/**
 * 显示同类项目分组配置
 */
function showAssayGroupDialog(id) {

    resetFormAndClearHidden("assayGroupForm");
    $("#assayList").html("");
    $("#assayGroupDialog").modal("show");

    $.ajax({
        url : ctx + '/assay/assayRemindDict/selectAllItemByGroupDetail.shtml',
        data : "id=" + convertEmpty(id),
        dataType : 'json',
        type : 'POST',
        success : function(data) {
            if (!isEmpty(id)) {
                $("#assayGroupForm_id").val(data.assayGroupConf.id)
                $("#assayGroupForm_name").val(data.assayGroupConf.name);
            }
            var bodyHtml = "";
            trimJosnArray(data.itemList);
            var checked = "";
            for (var i = 0; i < data.itemList.length; i++) {
                var item = data.itemList[i];
                if (isEmpty(item.itemCode))
                    continue;
                if (item.selectFlag) {
                    checked = "checked";
                } else {
                    checked = "";
                }
                bodyHtml += '<tr>';
                bodyHtml += '<td class="text-center">';
                bodyHtml += '<label><input type="checkbox" name="itemCode" value="' + item.itemCode + '"' + checked + '></label>';
                bodyHtml += '</td>';
                bodyHtml += '<td>' + item.itemCode + '</td>';
                bodyHtml += '<td>' + item.itemName + '</td>';
                bodyHtml += '<td>' + item.groupName + '</td>';
                bodyHtml += '<td>' + convertEmpty(item.minValue) + '</td>';
                bodyHtml += '<td>' + convertEmpty(item.maxValue) + '</td>';
                bodyHtml += '<td>' + convertEmpty(item.reference) + '</td>';
                bodyHtml += '</tr>';
            }
            $("#assayList").html(bodyHtml);
            $('#assaySearch').fastLiveFilter('#assayList');
        }
    });
}

/**
 * 保存同类分组
 */
function saveAssayGroupConf() {

    var details = new Array();
    $("#assayList input[type='checkbox']:checked").each(function() {
        details.push({
            itemCode : $(this).val()
        });
    });

    var json = {
        id : $("#assayGroupForm_id").val(),
        name : $("#assayGroupForm_name").val(),
        details : details
    };
    $.ajax({
        url : ctx + '/assay/assayRemindDict/saveAssayGroupConf.shtml',
        data : JSON.stringify(json),
        dataType : 'json',
        type : 'POST',
        contentType : 'application/json;charset=utf-8',
        success : function(data) {
            $("#assayGroupDialog").modal("hide");
            loadAssayGroupList();
            showTips("保存成功");
        }
    });
}

/**
 * 删除同类分组
 */
function deleteAssayGroupConf(id) {
    $.ajax({
        url : ctx + '/assay/assayRemindDict/deleteAssayGroupConf.shtml',
        data : "id=" + id,
        dataType : 'json',
        type : 'POST',
        success : function(data) {
            showTips("删除成功");
            loadAssayGroupList();
        }
    });
}

/** ******************************重构 end************************************* */
