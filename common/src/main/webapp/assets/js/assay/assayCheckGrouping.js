/** 获取报表数据 */
function getReport() {
    var startDateStr = $("#selectDate").val() + "-01-01";
    var endDateStr = $("#selectDate").val() + "-12-31";
    /*
     * var startDateStr = $("#reportDateDiv input[name='startDateStr']").val();
     * var endDateStr = $("#reportDateDiv input[name='endDateStr']").val();
     */
    var itemCode = $("#assayItemRadio [name='itemCode']:checked").val();
    if (isEmpty(itemCode)) {
        return false;
    }
    $.ajax({
        url : ctx + "/report/assay/countPie.shtml",
        type : "post",
        loading : true,
        data : "startDateStr=" + startDateStr + "&endDateStr=" + endDateStr + "&itemCode=" + encodeURI(itemCode),
        dataType : "json",
        success : function(chartData) {
            setDbBar(chartData.bar);
            setdblPie(chartData.pie);
            setAvgAndPop(chartData.line);
            showMainTable(chartData);
        }
    });
}// getPatientCenterLineReport

$(function() {
    addEvents();
    getAssayCategory();
    // 加载检查项规则
    // selectDictHospitalLabByItemCode($("[name='hideItemCode']").val());
});

/** 事件注册 */
function addEvents() {
    // });

    // 选中radio事件
    $("#assayCategoryRadio").on("click", "input[type='radio']", function() {
        getAssayitem();
    });

    $("#assayItemRadio").on("change", "[name='itemCode']", function() {
        // getReport();
        isTop();
    });

    // 点击刷新
    $("#refresh").click(function() {
        getReport();
    });
}

/** 获取table数据 */
function getAssayitem() {
    var groupId = convertEmpty($("#assayCategoryRadio input[type='radio']:checked").val());
    $.ajax({
        url : ctx + "/assay/hospDict/getAssayList.shtml",
        type : "post",
        data : "groupId=" + encodeURI(groupId),
        dataType : "json",
        loading : true,
        success : function(data) {
            var htmlRadio = '';
            if (data.status == 1 && !isEmptyObject(data.items)) {
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    htmlRadio += '<div class="item-box">';
                    htmlRadio += '<input id="assay_item_' + i + '" type="radio" onclick="toggleFunction(this,null)" name="itemCode" value="'
                                    + item.itemCode + '"/>';
                    htmlRadio += '<label class="item-col-sm-width" for="assay_item_' + i + '">' + item.itemName + '</label>';
                    htmlRadio += '</div>';
                }
                $("#assayItemRadio").html(htmlRadio);
                $("#assayItemRadio input[type='radio']:first").trigger("click");// 模拟点击事件
                $("[name='hideItemCode']").val(data.items[0].itemCode);
            }
        }
    });
}
/**
 * 根据检查项和检查类别判断是否是常规项
 */
function isTop() {
    var itemCode = $("#assayItemRadio input[type='radio']:checked").val();
    $.ajax({
        url : ctx + "/assay/hospDict/getByItemCode.shtml",
        type : "post",
        dataType : "json",
        data : {
            "itemCode" : itemCode
        },
        loading : true,
        success : function(data) {
            if (data.status == "1") {
                var record = data.rs;
                if (!isEmpty(record.isTop)) {
                    if (record.isTop) {
                        $(":radio[name='assayHospDict.isTop'][value='" + 1 + "']").prop("checked", true);
                    } else {
                        $(":radio[name='assayHospDict.isTop'][value='" + 0 + "']").prop("checked", true);
                    }
                }
            }
        }
    })
}
/** 获取化验单类别数据 */
function getAssayCategory() {
    $.ajax({
        url : ctx + "/assay/hospDict/getAssayCategoryList.shtml",
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {
            var htmlRadio = '';
            if (data.status == 1) {
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    htmlRadio += '<div class="item-box">';
                    htmlRadio += '<input id="assay_category_' + i + '" type="radio" name="groupId" value="' + item.groupId + '" '
                                    + (i == 0 ? "checked" : "") + ' />';
                    htmlRadio += '<label class="item-col-sm-width" for="assay_category_' + i + '">' + item.groupName + '</label>';
                    htmlRadio += '</div>';
                }
            }
            $("#assayCategoryRadio").html(htmlRadio);
            if (data.status == 1) {
                getAssayitem();
            }
        }
    });
}
$(function() {
    // Validate校验
    $('#patientCheckGroupForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            "patientAssayGroupRuleList[0].minValue" : {
                number : [ "规则" ],
                customRange : [ 0.01, 10000, "规则" ]
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
});

// 添加化验分组规则数据-Btn
$("#addRuleBtn").click(function() {
    saveGroupRuleValue();
});

// toggle函数-Fun
function toggleFunction(dom, value) {
    // 清除原来查询出来的分组规则的数据
    $("#ruleListDiv div").remove();
    // 调用查询分组规则函数
    getGroupingRuleData(dom, value);
    // 查询达标范围的数据
    getReachRangeData(dom);
    // 设置隐藏值
    $("[name='hideItemCode']").val($(dom).val());
}
// 查询分组规则的数据，循环加载
function getGroupingRuleData(dom, value) {
    // 将输入的值清空
    $("#addRule").val("");
    if (value == null || value == "") {
        $.ajax({
            url : ctx + "/assay/patientAssayGroupRule/selectAllAssayGroupRule.shtml",
            data : {
                "itemsCode" : $(dom).val()
            },
            dataType : 'json',
            type : 'POST',
            success : function(data) {
                $.each(data, function(index, obj) {
                    $("#addRule").before(createAssayGroupRuleHTMLByLoad(index, obj));
                });
            }
        });
    } else {
        $.ajax({
            url : ctx + "/assay/patientAssayGroupRule/selectAllAssayGroupRule.shtml",
            data : {
                "itemsCode" : value
            },
            dataType : 'json',
            type : 'POST',
            success : function(data) {
                $.each(data, function(index, obj) {
                    $("#addRule").before(createAssayGroupRuleHTMLByLoad(index, obj));
                });
            }
        });
    }
}

// 查询达标范围
function getReachRangeData(dom) {
    $.ajax({
        url : ctx + "/assay/hospDict/getByItemCode.shtml",
        type : 'POST',
        data : {
            "itemCode" : $(dom).val()
        },
        dataType : 'json',
        success : function(data) {
            if (data.status == "1") {
                var record = data.rs;
                // 是否空值
                if (record.personalMinValue == null || record.personalMinValue == "") {
                    record.personalMinValue = "";
                }
                if (record.personalMaxValue == null || record.personalMaxValue == "") {
                    record.personalMaxValue = "";
                }
                // 是否置顶 $("#isTopNo").attr("checked", "true");
                if (record.isTop) {
                    $("#isTopFalse").removeAttr("checked");
                    $("#isTopTrue").prop("checked", "true");
                } else {
                    $("#isTopTrue").removeAttr("checked");
                    $("#isTopFalse").prop("checked", "true");
                }
                $("[name='assayHospDict.personalMinValue']").val(record.personalMinValue);
                $("[name='assayHospDict.personalMaxValue']").val(record.personalMaxValue);
                $("#dictHospitalLabId").val(record.id);
            }

        }
    });
}

// 添加化验分组规则到后台再排序出来(没有添加到数据库中)
function saveGroupRuleValue() {
    var maxValue = $("[name='assayHospDict.personalMaxValue']").val();
    var minValue = $("[name='assayHospDict.personalMinValue']").val();
    if ($("#addRule").val() == "") {
        showAlert("不能输入空值");
        return;
    }
    // 正则表达式判断是否输入了合法的数字
    var legalFigure = /^\d+(\.{1}\d+)?$/;

    if (!legalFigure.test($("#addRule").val())) {
        showAlert("输入的数据不合法");
        return;
    }
    // 判断达标范围内的数据是否合法
    if (maxValue != "") {
        if ((!legalFigure.test(maxValue))) {
            showAlert("达标范围输入的数据不合法");
            return;
        }
    }
    // 当最小值不为空，正则表达式来判断最小值
    if (minValue != "") {
        if ((!legalFigure.test(minValue))) {
            showAlert("达标范围输入的数据不合法");
            return;
        }
    }

    var patientAssayGroupRuleList = $("#patientCheckGroupForm").serialize();

    // 将数据传进去再排序出来
    $.ajax({
        url : ctx + "/assay/patientAssayGroupRule/sortAssayGroupRule.shtml",
        data : patientAssayGroupRuleList,
        type : 'POST',
        async : false,// 即必须执行完这个方法后才能往下执行
        dataType : 'json',
        success : function(data) {
            // 清除原来查询出来的分组规则的数据
            $("#ruleListDiv div").remove();
            // 将输入的数据清空
            $("#addRule").val("");
            $.each(data, function(index, obj) {
                if (obj.id == null) {
                    obj.id = 0;
                }
                // 动态生成文本框
                $("#addRule").before(createAssayGroupRuleHTML(index, obj));
            });
        }
    });
}

// 删除分组规则
function deleteGroupRule(dom) {
    $(dom).parent().remove();
}

// 修改分组规则时，判断是否修改成重复的数据
var repeatIndexByUpdate = 1;
function judgeExistByUpdateGroupRule(dom) {
    // 判断如果是点击了修改按钮的状态下才触发事件
    if ($("#updateGroupRuleBtn").text() == "修改") {
        return;
    }
    var getValue = $(dom).val();// 输入值
    $("#ruleListDiv div").each(function() {
        var preInputValue = $(this).find("input:eq(0)").val();
        if (preInputValue == getValue) {
            repeatIndexByUpdate = repeatIndexByUpdate + 1;
            if (repeatIndexByUpdate > 2) {
                showAlert("不能输入重复的数据");
                $(this).find("input:eq(0)").val(0);
                return;
            }
        }
    });
    repeatIndexByUpdate = 1;
}

// 添加分组规则时，判断是否输入了重复数据
var repeatIndexByAdd = 1;
function judgeExistByAddGroupRule(dom) {
    var getValue = $(dom).val();// 输入值
    $("#ruleListDiv div").each(function() {
        var preInputValue = $(this).find("input:eq(0)").val();
        if (preInputValue == getValue) {
            repeatIndexByAdd = repeatIndexByAdd + 1;
            if (repeatIndexByAdd >= 2) {
                showAlert("不能输入重复的数据");
                $("#addRule").val("");
                return;
            }
        }
    });
    repeatIndexByAdd = 1;
}
// 保存检查项规则
function saveCheckRuleData() {

    var maxValue = $("[name='assayHospDict.personalMaxValue']").val();
    var minValue = $("[name='assayHospDict.personalMinValue']").val();
    var reg = /^\d+(\.{1}\d+)?$/;
    // 范围内两个Input都有值
    if (maxValue != "" || minValue != "") {
        // 当最大值不为空，正则表达式来判断最大值
        if (maxValue != "") {
            if ((!reg.test(maxValue))) {
                showAlert("输入的数据不合适，请重新输入");
                return;
            }
        }
        // 当最小值不为空，正则表达式来判断最小值
        if (minValue != "") {
            if ((!reg.test(minValue))) {
                showAlert("输入的数据不合适，请重新输入");
                return;
            }
        }
        // 判断后面值是否大于前面值

        // 最小值不为空，最大值为空
        if (minValue != "" && maxValue == "") {
        } else {
            if ((maxValue * 1) <= (1 * minValue)) {
                showAlert("输入的数据不合适，请重新输入");
                return;
            }
        }

    }

    // 判断提示错误的样式是否还在,存在样式就删除样式
    if ($("div[data-error]").text() != "") {
        $("div[data-error]").text("");
    }

    // 验证通过
    if ($("#patientCheckGroupForm").valid()) {
        var patientCheckRule = $("#patientCheckGroupForm").serialize();
        var getItemCode = $("[name='hideItemCode']").val();// 项编码
        patientCheckRule += "&itemCode=" + encodeURIComponent(getItemCode);
        $.ajax({
            url : ctx + "/assay/patientAssayGroupRule/savePatientAssayGroupRule.shtml",
            data : patientCheckRule,
            type : 'POST',
            dataType : 'json',
            loading : true,
            success : function(data) {
                if (data.status == 1) {
                    showTips("保存成功");
                    $("div[data-error]").text("");
                    $("#addRule").val("");
                } else {
                    showAlert("保存失败");
                }

            }
        });
    }
}

// 取消添加
function cancleSave() {
    var getHideValue = $("[name='hideItemCode']").val();
    // 清除原来查询出来的分组规则的数据
    $("#ruleListDiv div").remove();
    // 调用查询分组规则函数
    getGroupingRuleData(getHideValue, getHideValue);
    // 查询达标范围的数据
    getReachRangeData($("[name='hideItemCode']"));
}
// 分组规则排序后再动态生成文本框
function createAssayGroupRuleHTML(index, obj) {
    var tempHTML = '<div class="add-result add-result-number" > <input class="personal-input add-number" name="patientAssayGroupRuleList['
                    + (index + 1) + '].minValue" type="text" value="' + obj.minValue + '"  readonly><img class="delete-icon" src="' + ctx
                    + '/assets/img/delete.png" onclick="deleteGroupRule(this)"><input type="hidden" name="patientAssayGroupRuleList[' + (index + 1)
                    + '].id" value="' + obj.id + '"/></div>';
    return tempHTML;
}
// 加载页面时，查询分组规则动态生成文本框
function createAssayGroupRuleHTMLByLoad(index, obj) {
    var tempHTML = '<div class="add-result add-result-number"> <input class="personal-input add-number" name="patientAssayGroupRuleList['
                    + (index + 1) + '].minValue" type="text" value="' + obj.minValue
                    + '" onblur="judgeExistByUpdateGroupRule(this)" readonly><img class="delete-icon" src="' + ctx
                    + '/assets/img/delete.png" onclick="deleteGroupRule(this)"><input type="hidden" name="patientAssayGroupRuleList[' + (index + 1)
                    + '].id" value="' + obj.id + '"/></div>';
    return tempHTML;
}