var foodHtml = "";
var food = "";
// 食物含量个数
var nutritionSize = "";
var arriveCityArray = new Array("", "", "");
$(function() {
    $.ajax({
        url : ctx + "/nutrition/queryFood.shtml",
        type : "post",
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.status) {
                food = data.foods;
            }
        }
    });

    showNutritionName();
    // 添加类型校验
    $('#foodRecordForm').validate({
        onfocusout : false,
        highlight : function(element, errorClass, validClass) { // element出错时触发
            if (!$(element).hasClass(errorClass)) {
                $(element).addClass(errorClass);
            }
        },
        unhighlight : function(element, errorClass) { // element通过验证时触发
            if ($(element).hasClass(errorClass)) {
                $(element).removeClass(errorClass);
            }
        }
    });
    for (var i = 0; i < food.length; i++) {
        var item = food[i];
        arriveCityArray[i] = new Array(item.id, item.foodName, item.spellInitials);
    }
    // 添加第一个回顾日期的校验
    addCheckTime(1);
});
/**
 * 清空回顾节点
 */
function resetFrom() {
    // 关闭窗口
    closeWindow();
    /*resetFood();
    showTips("取消成功");*/
}
/**
 * @param dom
 *            日期选择插件
 */
function addDate(dom) {
    $(dom).daterangepicker({
        "singleDatePicker" : true,
        "showDropdowns" : true,
        "autoUpdateInitInput" : false,
        "locale" : {
            format : "YYYY-MM-DD"
        }
    });
}
/**
 * 查询所有食物
 */
function dayQueryFood() {
    var showHtml = "";
    for (var i = 0; i < food.length; i++) {
        var item = food[i];
        showHtml += '<option data-tokens="' + item.spellInitials + item.foodName + '" value="' + item.id + '">' + item.foodName + '</option>';
    }
    return showHtml;
}
var recordNums = 1;
function addMoreFoodRecord() {
    recordNums++;
    var recordNum = ($("input[name='recordTime']").length) + 1;// 动态下标
    if (recordNums <= 3) {
        var tempHtml = '<div class="m-content"  data-div="resectDiv">';
        tempHtml += '<div  data-div="resectDiv" class="m-head bottomline"><p class="u-subtitle text-center"></p></div>';
        tempHtml += '<span class="u-span f-span">回顾时间: </span>';
        tempHtml += '<input type="text" data-recordTime="record" id="recordTime' + recordNums
                        + '" name="recordTime" onfocus="addDate(this)" class="u-input m-l-14" placeholder="请选择回顾日期" readonly/>';
        tempHtml += '</div>';
        tempHtml += '<div  data-div="resectDiv" class="m-content">';
        tempHtml += '<span class="u-span f-span"  data-value="breakfast' + recordNums + '">早&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>';
        tempHtml += '<button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button>';
        tempHtml += '</div>';
        tempHtml += '<div style="display: none;"  data-div="resectDiv" data-id="breakfast" id="breakfast' + recordNums + '"></div>';
        tempHtml += '<div  data-div="resectDiv" class="m-content">';
        tempHtml += '<span class="u-span f-span"  data-value="lunch' + recordNums + '">午&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>';
        tempHtml += '<button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button></div>';
        tempHtml += '<div style="display: none;" data-id="lunch"  data-div="resectDiv" id="lunch' + recordNums + '"></div>';
        tempHtml += '<div  data-div="resectDiv" class="m-content">';
        tempHtml += ' <span class="u-span f-span"  data-value="extraMeal'
                        + recordNums
                        + '">加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span><button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button></div>';
        tempHtml += '<div style="display: none;" data-id="extraMeal"  data-div="resectDiv" id="extraMeal' + recordNums + '"></div>';
        tempHtml += '<div  data-div="resectDiv" class="m-content"><span class="u-span f-span"  data-value="supper'
                        + recordNums
                        + '">晚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span><button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button></div>';
        tempHtml += '<div style="display: none;" data-id="supper"  data-div="resectDiv" id="supper' + recordNums + '"></div>';
        tempHtml += '<div  data-div="resectDiv" class="m-content"> <span class="u-span f-span">备注信息:</span><input type="text" id="recordMark'
                        + recordNums + '" name="remark" class="u-input f-input m-l-14" placeholder="回顾备注"/></div>';
        $("#addMoreFood").before(tempHtml);
        addCheckTime(recordNum);
    } else {
        showError("最多只能回顾三日饮食");
    }
}
function keyUp(ob) {
    // 检查是否是非数字值
    if (isNaN(ob.value)) {
        ob.value = "";
    }
    if (ob != null) {
        var numDon = ob.value.toString().split(".");
        // 检查小数点后是否大于三位
        if (numDon.length > 1 && numDon[1].length > 3) {
            ob.value = numDon[0] + "." + numDon[1].substring(0, 3);
            ob.next().text("最多两位小数");
        }
        // 保留小数点前最多8位数
        if (numDon[0].length > 8) {
            if (numDon.length > 1) {
                ob.value = numDon[0].substring(0, 8) + "." + numDon[1].substring(0, 3);
            } else {
                ob.value = numDon[0].substring(0, 8);
            }
        }
    }
}
/**
 * @param dom
 *            点击添加饮食动态加入表格
 */
var diningTime = "";
var diningTimeIndex = "";
function addFoodRecord(dom) {
    diningTime = $(dom).parent().find("span").attr("data-value");// 用餐时间
    diningTimeIndex = $("#" + diningTime).find("div.m-content").length + 1;// 动态下标

    // 表单元素
    var tHtml = '<div class="m-content">';
    tHtml += '<span class="u-delete f-span pull-left" onclick="removeFood(this)" data-value=' + (diningTime + diningTimeIndex) + '></span>';
    tHtml += '<div class="pull-left m-l-10">';
    tHtml += '<div class="form-group dropdown dropdown-1 m-l-5" style="display: inline-block;">';
    tHtml += '<input name="' + (diningTime + diningTimeIndex) + '" data-class="quickQuery" id="' + (diningTime + diningTimeIndex)
                    + 'quickQuery" class="' + (diningTime + diningTimeIndex) + ' quickQuery$focus"';
    tHtml += ' data-option="" placeholder="食物名称或简拼" data-time="recordTime' + recordNums + '" data-mark="recordMark' + recordNums + '"  data-index="'
                    + (diningTime + diningTimeIndex) + '">';
    tHtml += "</div></div>";
    tHtml += '<div class="pull-left m-l-18">';
    tHtml += '<input class="u-input-27" type="text" data-value="' + (diningTime + diningTimeIndex) + '"name="foodContent'
                    + (diningTime + diningTimeIndex) + '" id="foodContent' + (diningTime + diningTimeIndex) + '"'
                    + ' onblur="selectAllNutrition(this)" onKeyUp="keyUp(this)" placeholder onchange="selectAllNutrition(this)"></div>';
    tHtml += '<span class="u-span-1 f-span-2 pull-left">g</span><div class="margin-left-35" data-error></div></div>';
    $("#" + diningTime).append(tHtml);
    $("#" + diningTime).css("display", "block");
    $quickQuery(arriveCityArray, {
        "viewContentNum" : 25
    });
    // 表格元素
    var foodName = $("input[name='" + (diningTime + diningTimeIndex) + "quickQuery']>option:selected").text();
    var foodContent = $("input[id='" + (diningTime + diningTimeIndex) + "']").val();// 获取食物用量
    var tableHtml = "<tr data-id='" + (diningTime + diningTimeIndex) + "' data-row='everyFood'>";
    tableHtml += "<td data-value='foodName" + diningTimeIndex + "' id='tdName" + (diningTime + diningTimeIndex) + "'>" + foodName + "</td>";
    isEmpty(foodContent) ? tableHtml += "<td data-value='precent' id='td" + (diningTime + diningTimeIndex) + "'>0</td>"
                    : tableHtml += "<td data-value='precent' id='td" + (diningTime + diningTimeIndex) + "'>" + foodContent + "</td>";
    for (var int = 0; int < nutritionSize; int++) {
        tableHtml += "<td data-value='precent' id='" + int + "precent" + (diningTime + diningTimeIndex) + "'>0</td>";
    }
    tableHtml += "</tr>";
    $("#totalTr").before(tableHtml);
    // 注册校验
    addCheck(diningTime, diningTimeIndex);
    /*$("#tableContent").insertBefore("#totalTr");*/
}
// 校验用量
function addCheck(diningTime, diningTimeIndex) {
    var food = diningTime + diningTimeIndex + "quickQuery";
    var foodVal = $("#" + food).val();
    var name = "foodContent" + diningTime + diningTimeIndex;
    var content = $("#" + name).val();
    if (isEmpty(foodVal)) {
        $("#record1 #foodRecordForm #" + food).rules("add", {
            required : [ "食物名称" ]
        });
    } else {
        $("#record1 #foodRecordForm #" + food).rules("remove");
    }
    if (isEmpty(content)) {
        $("#record1 #foodRecordForm #" + name).rules("add", {
            required : [ "食物用量" ]
        });
    } else {
        $("#record1 #foodRecordForm #" + name).rules("remove");
    }
}
// 校验时间
function addCheckTime(recordNum) {
    var recordTime = $("#recordTime" + recordNum).val();
    if (isEmpty(recordTime)) {
        $("#record1 #foodRecordForm #recordTime" + recordNum).rules("add", {
            required : [ "回顾时间" ]
        });
    } else {
        $("#record1 #foodRecordForm #recordTime" + recordNum).rules("remove");
    }
}

/**
 * @param dom
 *            当前点击事件节点 食物变更事件（改变表格数据）
 */
function changeFood(dom) {
    var commomNum = $(dom).attr("data-index");// 关联编号
    var foodName = $(dom).attr("data-name");// 食物名称

    $("#tdName" + commomNum).text(foodName);
    // 计算各营养成分含量
    var id = $(dom).attr("data-value");// 食物编号
    var precent = $("#td" + commomNum).text();// 食物用量
    if (!isEmpty(id) && !isEmpty(precent)) {
        queryNutritionRecord(id, precent, commomNum);
    }
}
/**
 * @param dom
 *            删除当前饮食
 */
function removeFood(dom) {

    var commomNum = $(dom).attr("data-value");
    var id = $("input[name='" + commomNum + "']").attr("data-option");// 食物编号
    var precent = $("#foodContent" + commomNum).val();
    isEmpty(precent) ? precent = 0 : precent = precent;

    var trId = $(dom).attr("data-value");
    var trObj = $("#tableContent>tr[data-id='" + trId + "']").remove();// 删除当前行
    $(dom).parent().remove();

    if (!isEmpty(id) && !isEmpty(precent)) {
        queryNutritionRecord(id, precent, commomNum);
    }
}
/**
 * 查询所添加的饮食的营养成分
 */
function selectAllNutrition(dom) {
    var commomNum = $(dom).attr("data-value");// 关联编号
    var precent = $(dom).val();
    isEmpty(precent) ? precent = 0 : precent = precent;
    $("#td" + commomNum).text(precent);
    var id = $("input[name='" + commomNum + "']").attr("data-option");// 食物编号
    if (!isEmpty(id) && !isEmpty(precent)) {
        queryNutritionRecord(id, precent, commomNum);
    }
}
// 查询所有的营养成分
function totalFoodNutrition(arrFoodTotal) {
    $.ajax({
        url : ctx + "/food/nutrition/queryNutrition.shtml",
        type : "post",
        dataType : "json",
        success : function(data) {
            if (data.status) {
                var lNutritions = data.lNutritions;
                var temHtml = "";
                var temHtml2 = "";
                var nutritionFoodSize = lNutritions.length;
                for (var i = 0; i < lNutritions.length; i++) {
                    var item = lNutritions[i];
                    var source = item.nutritionName;
                    var rt = [];
                    rt[1] = source.substring(0, source.indexOf("("));
                    rt[2] = source.substring(source.indexOf("(") + 1, source.indexOf(")"));
                    var total = arrFoodTotal[i + 1].toFixed(2);
                    if (item != null) {
                        if (i <= (nutritionFoodSize / 2)) {
                            temHtml += '<p style="width: 90%;" class="u-span-2 f-span-2">' + rt[1];
                            temHtml += '<span class="u-span-3 pull-right">' + rt[2] + '</span>';
                            temHtml += '<span class="u-span-3 pull-right">' + total + '</span>';
                        } else {
                            temHtml2 += '<p style="width: 90%;" class="u-span-2 f-span-2">' + rt[1];
                            temHtml2 += '<span class="u-span-3 pull-right"> ' + rt[2] + '</span>';
                            temHtml2 += '<span class="u-span-3 pull-right">' + total + '</span>';
                        }
                    }
                }
                $("#totalLoadInit").html(temHtml);
                $("#totalLoadInit2").html(temHtml2);
            }
        }
    });
}
/**
 * @param id
 * @param precent
 *            查询各营养含量
 */
function queryNutritionRecord(id, precent, commomNum) {
    var nutritionTotal = 0;
    $.ajax({
        url : ctx + "/nutrition/selectAllNutrition.shtml",
        type : "post",
        data : "id=" + id + "&precent=" + precent,
        async : false,
        dataType : "json",
        success : function(data) {
            var arrFoodTotal = new Array();
            if (data.status) {

                for (var i = 0; i < nutritionSize; i++) {
                    var nutritionContent = data.nutritionContent;
                    for (var j = 0; j < nutritionContent.length; j++) {
                        if (i == j) {
                            var nutritionFoodContent = parseFloat(nutritionContent[j]);
                            nutritionTotal += nutritionFoodContent;
                            $("#" + i + "precent" + commomNum).text(nutritionFoodContent);
                        }
                        arrFoodTotal[i] = 0;// 初始化
                        arrFoodTotal[nutritionSize] = 0;
                    }
                }
            }
            // 计算总和
            $('#tableContent>tr[data-row="everyFood"]').each(function(i, parentObj) {
                $(parentObj).find("td[data-value='precent']").each(function(index, obj) {
                    var tempParent = parseFloat($(obj).text());
                    arrFoodTotal[index] += tempParent;
                });
            });
            // alert(arrFoodTotal);
            // 总和列表填充数据
            $('#tableContent>tr[data-row="totalAll"]').each(function(i, parentObj) {
                $(parentObj).find("td[data-value='precent']").each(function(index, obj) {
                    $(obj).text(arrFoodTotal[index]);
                });
            });
            totalFoodNutrition(arrFoodTotal);
        }
    });
}
/**
 * 获取营养素名称
 */
function showNutritionName() {
    $.ajax({
        url : ctx + "/food/nutrition/queryNutrition.shtml",
        type : "post",
        dataType : "json",
        success : function(data) {
            if (data.status) {
                var lNutritions = data.lNutritions;
                nutritionSize = lNutritions.length;
                var temHtml = '<th style="border: 1px solid;white-space: nowrap;">菜肴名称</th>';
                temHtml += '<th style="border: 1px solid;white-space: nowrap;">用量(g)</th>'
                var totalHtml = '<tr id="totalTr" data-row="totalAll"><td>合计</td><td data-value="precent">0</td>';// 合计表格
                for (var i = 0; i < lNutritions.length; i++) {
                    var item = lNutritions[i];
                    if (item != null) {
                        var source = item.nutritionName;
                        temHtml += '<th style="border: 1px solid;white-space: nowrap;">' + source + '</th>';
                        totalHtml += '<td data-value="precent">0</td>';
                    }
                }
                totalHtml += "</tr>";
                $("#nutritionNameTable").html(temHtml);
                $("#tableContent").append(totalHtml);
            }
        }
    });
}

/**
 * @returns {String} 获取食物
 */
function getFoodName() {
    var foodNameHtml = '';
    var foodNameArr = [ "breakfast", "lunch", "supper", "extraMeal" ];
    for (var i = 0; i < foodNameArr.length; i++) {
        $("div[data-id='" + foodNameArr[i] + "'] >div.m-content").each(
                        function(index, obj) {
                            var foodContentValue = $(obj).find("div.m-l-18 input").val();
                            foodNameHtml += (foodNameHtml == '' ? foodNameArr[i] + 'FoodContent[' + index + ']=' + foodContentValue : '&'
                                            + foodNameArr[i] + 'FoodContent[' + index + ']=' + foodContentValue);
                            if (Number(foodContentValue) > 100000) {
                                foodNameHtml = "1";
                            }
                        });
        if (foodNameHtml != "1") {
            $("div[data-id='" + foodNameArr[i] + "'] >div.m-content").each(
                            function(index, obj) {
                                var selectValue = $(obj).find("div>input[data-class='quickQuery']").attr("data-option");
                                var recordTimeId = $(obj).find("div>input[data-class='quickQuery']").attr("data-time");
                                var recordMarkId = $(obj).find("div>input[data-class='quickQuery']").attr("data-mark");
                                foodNameHtml += (foodNameHtml == '' ? foodNameArr[i] + 'Food[' + index + ']=' + selectValue : '&' + foodNameArr[i]
                                                + 'Food[' + index + ']=' + selectValue);
                                foodNameHtml += (foodNameHtml == '' ? '&recordTimes=' + $("#" + recordTimeId).val() : '&recordTimes='
                                                + $("#" + recordTimeId).val());
                                foodNameHtml += (foodNameHtml == '' ? '&recordMarks=' + $("#" + recordMarkId).val() : '&recordMarks='
                                                + $("#" + recordMarkId).val());
                                if (isEmpty(selectValue)) {
                                    foodNameHtml = "2";
                                }
                            });
        }
    }
    return foodNameHtml;
}

/**
 * 重置饮食
 */
function resetFood() {
    var foodNameHtml = '';
    var foodNameArr = [ "breakfast", "lunch", "supper", "extraMeal" ];
    var recommdenNameArrs = [ "foodRecommendBreakfast", "foodRecommendLunch", "foodRecommendSupper", "foodRecommendExtraMeal" ];
    for (var i = 0; i < foodNameArr.length; i++) {
        $("#" + foodNameArr[i]).empty();
        $("#" + recommdenNameArrs[i]).empty();
    }
    $("div[data-div='resectDiv']").remove();
    // 时间备注清空
    $("#recordTime1").val("");
    $("#recordMark1").val("");
    $("#recommendMark1").val("");
    // 总量清空
    $("#totalLoadInit").empty();
    $("#totalLoadInit2").empty();
    $("#totalTr").siblings().remove();
    // 计算归零
    $("#totalTr>td").each(function(i, dom) {
        $(dom).text(0);
    });
    // 清空
    recordNums = 1;
    $('body,html').animate({
        scrollTop : 0
    }, 0);
}

/**
 * 插入饮食回顾
 */
function insertFoodRecord() {
    var fkPatientId = $("#patientId").val();
    var recordDateShow = $("#recordDateShow").val();
    var sysOwner = $("#sysOwner").val();
    var batchNo = $("#batchNo").val();
    // 饮食校验
    var foodNameArr = [ "breakfast", "lunch", "supper", "extraMeal" ];
    var checkFood = true;
    for (var i = 0; i < foodNameArr.length; i++) {
        $("div[data-id='" + foodNameArr[i] + "'] >div.m-content").each(function(index, obj) {
            var selectValue = $(obj).find("div>input[data-class='quickQuery']").attr("data-option");
            if (selectValue == "") {
                checkFood = false;
                return false;
            }
        });
        if (!checkFood) {
            showWarn("请选择正确的饮食");
            return false;
        }
    }
    // 进行校验
    if ($("#foodRecordForm").valid() && $("#foodRecommendForm").valid()) {
        var foodNameHtml = getFoodName();
        if (!isEmpty(foodNameHtml) && foodNameHtml != "1" && foodNameHtml != "2") {
            foodNameHtml += '&fkPatientId=' + fkPatientId + '&recordDateShow=' + recordDateShow + '&sysOwner=' + sysOwner + '&batchNo=' + batchNo;
            $.ajax({
                url : ctx + "/foodRecord/insertFoodRecord.shtml",
                type : "post",
                data : foodNameHtml,
                dataType : "json",
                async : false,
                success : function(data) {
                    if (data.status) {
                        insertFoodRecommend(recordDateShow, fkPatientId, sysOwner, batchNo);
                        showTips("保存成功");
                        resetFood();
                        if (!isEmpty(window.opener)) {
                            if (existsFunction("window.opener.callbackForOpenWindow")) {
                                window.opener.callbackForOpenWindow();
                            }
                            window.opener.focus();
                        }
                        // 关闭窗口
                        closeWindow();
                    }
                }
            });
        } else if (foodNameHtml == "1") {
            showWarn("饮食单位不正确");
        } else if (foodNameHtml == "2") {
            showWarn("添加饮食不存在");
        } else {
            showWarn("请输入回顾内容");
        }
    }
}