$(function() {
    showNutritionName();
    // 添加类型校验
    $('#foodRecommendForm').validate({
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
        },
        errorPlacement : function(error, element) {
            var obj = getValidateErrorObj($(element));
            $(error["0"]).css("margin-right", "6px");
            obj.find("[data-error]").append(error);
        }
    });
});
var recommendNums = 1;
function addMoreFoodRecommend() {
    recommendNums++;
    var recommendNum = ($("input[name='recommendTime']").length) + 1;// 动态下标
    if (recommendNums <= 3) {
        var tempHtml = '<div class="m-content">';
        tempHtml += '<div class="m-head bottomline"><p class="u-subtitle text-center"></p></div>';
        tempHtml += '<span class="u-span f-span">推荐时间: </span>';
        tempHtml += '<input type="text" data-recommendTime="recommend" id="recommendTime' + recommendNums
                        + '" name="recommendTime" onfocus="addDate(this)" class="u-input m-l-14" placeholder="2016-09-09"/>';
        tempHtml += '</div>';
        tempHtml += '<div class="m-content">';
        tempHtml += '<span class="u-span f-span"  data-value="foodRecommendBreakfast' + recommendNums
                        + '">早&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>';
        tempHtml += '<button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button>';
        tempHtml += '</div>';
        tempHtml += '<div style="display: none;" data-id="foodRecommendBreakfast" id="foodRecommendBreakfast' + recommendNums + '"></div>';
        tempHtml += '<div class="m-content">';
        tempHtml += '<span class="u-span f-span"  data-value="foodRecommendLunch' + recommendNums
                        + '">午&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>';
        tempHtml += '<button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button></div>';
        tempHtml += '<div style="display: none;" data-id="foodRecommendLunch" id="foodRecommendLunch' + recommendNums + '"></div>';
        tempHtml += '<div class="m-content">';
        tempHtml += ' <span class="u-span f-span"  data-value="foodRecommendExtraMeal'
                        + recommendNums
                        + '">加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span><button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button></div>';
        tempHtml += '<div style="display: none;" data-id="foodRecommendExtraMeal" id="foodRecommendExtraMeal' + recommendNums + '"></div>';
        tempHtml += '<div class="m-content"><span class="u-span f-span"  data-value="foodRecommendSupper'
                        + recommendNums
                        + '">晚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span><button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button></div>';
        tempHtml += '<div style="display: none;" data-id="foodRecommendSupper" id="foodRecommendSupper' + recommendNums + '"></div>';
        tempHtml += '<div class="m-content"> <span class="u-span f-span">备注信息:</span><input type="text" id="recommendMark' + recommendNums
                        + '" name="remark" class="u-input f-input m-l-14" placeholder="2016-09-09"/></div>';
        $("#addMoreFoodRecommend").before(tempHtml);
        addCheckRecommendTime(recommendNum);
    } else {
        showError("最多只能推荐三日饮食");
    }
}
/**
 * @param dom
 *            点击添加饮食动态加入表格
 */
function addFoodRecommend(dom) {
    var recommendTime = $(dom).parent().find("span").attr("data-value");// 用餐时间
    var recommendTimeIndex = $("#" + recommendTime).find("div.m-content").length + 1;// 动态下标

    // 表单元素
    var tHtml = '<div class="m-content">';
    tHtml += '<span class="u-delete f-span pull-left" onclick="removeRecommendFood(this)" data-value=' + (recommendTime + recommendTimeIndex)
                    + '></span>';
    tHtml += '<div class="pull-left m-l-10">';
    tHtml += '<div class="form-group dropdown dropdown-1 m-l-5" style="display: inline-block">';
    tHtml += '<input name="' + (recommendTime + recommendTimeIndex) + '" data-class="quickQuery" id="' + (recommendTime + recommendTimeIndex)
                    + 'quickQuery" class="' + (recommendTime + recommendTimeIndex) + ' quickQuery$focus"';
    tHtml += ' data-option="" placeholder="食物名称或简拼" data-time="recordTime' + recordNums + '" data-mark="recommendMark' + recordNums
                    + '"  data-index="' + (recommendTime + recommendTimeIndex) + '">'
    tHtml += "</div></div>";
    tHtml += '<div class="pull-left m-l-18">';
    tHtml += '<input type="text" onKeyUp="keyUp(this)" data-value="' + (recommendTime + recommendTimeIndex) + '"name="foodContent'
                    + (recommendTime + recommendTimeIndex) + '" id="foodContent' + (recommendTime + recommendTimeIndex) + '"></div>';
    tHtml += '<span class="u-span-1 f-span-2 pull-left">g</span><div class="margin-left-35" data-error></div></div>';
    $("#" + recommendTime).append(tHtml);
    $("#" + recommendTime).css("display", "block");
    $quickQuery(arriveCityArray, {
        "viewContentNum" : 25
    });

    addCheckRecommend(recommendTime, recommendTimeIndex);
}
// 校验用量
function addCheckRecommend(recommendTime, recommendTimeIndex) {
    var food = recommendTime + recommendTimeIndex + "quickQuery";
    var foodVal = $("#" + food).val();
    var name = "foodContent" + recommendTime + recommendTimeIndex;
    var content = $("#" + name).val();
    if (isEmpty(foodVal)) {
        $("#recommend1 #foodRecommendForm #" + food).rules("add", {
            required : [ "食物名称" ]
        });
    } else {
        $("#recommend1 #foodRecommendForm #" + food).rules("remove");
    }
    if (isEmpty(content)) {
        $("#recommend1 #foodRecommendForm #" + name).rules("add", {
            required : [ "食物用量" ]
        });
    } else {
        $("#recommend1 #foodRecommendForm #" + name).rules("remove");
    }
}
// 校验时间
function addCheckRecommendTime(recommendNum) {
    var recommendTime = $("#recommendTime" + recommendNum).val();
    if (isEmpty(recommendTime)) {
        $("#recommend1 #foodRecommendForm #recommendTime" + recommendNum).rules("add", {
            required : [ "回顾时间" ]
        });
    } else {
        $("#recommend1 #foodRecommendForm #recommendTime" + recommendNum).rules("remove");
    }
}

/**
 * @param dom
 *            删除当前饮食
 */
function removeRecommendFood(dom) {

    var commomNum = $(dom).attr("data-value");
    var id = $("[name='" + commomNum + "']>option:selected").val();// 食物编号
    var precent = $("#foodContent" + commomNum).val();
    isEmpty(precent) ? precent = 0 : precent = precent;

    var trId = $(dom).attr("data-value");
    var trObj = $("#tableContent>tr[data-id='" + trId + "']").remove();// 删除当前行
    $(dom).parent().remove();

    if (!isEmpty(id) && !isEmpty(precent)) {
        queryNutritionRecommend(id, precent, commomNum);
    }
}
/**
 * @returns {String} 获取食物
 */
function getFoodRecommendName() {
    var foodNameHtml = '';
    var foodNameArr = [ "foodRecommendBreakfast", "foodRecommendLunch", "foodRecommendSupper", "foodRecommendExtraMeal" ];
    for (var i = 0; i < foodNameArr.length; i++) {
        $("div[data-id='" + foodNameArr[i] + "'] >div.m-content").each(
                        function(index, obj) {
                            var selectValue = $(obj).find("div>input[data-class='quickQuery']").attr("data-option");
                            var recommendTimeId = $(obj).find("div>input[data-class='quickQuery']").attr("data-time");
                            var recommendMarkId = $(obj).find("div>input[data-class='quickQuery']").attr("data-mark");
                            foodNameHtml += (foodNameHtml == '' ? foodNameArr[i] + 'Food[' + index + ']=' + selectValue : '&' + foodNameArr[i]
                                            + 'Food[' + index + ']=' + selectValue);
                            foodNameHtml += (foodNameHtml == '' ? 'foodRecommendMarks=' + $("#" + recommendMarkId).val() : '&foodRecommendMarks='
                                            + $("#" + recommendMarkId).val());
                        });
        $("div[data-id='" + foodNameArr[i] + "'] >div.m-content").each(
                        function(index, obj) {
                            var foodContentValue = $(obj).find("div.m-l-18 input").val();
                            foodNameHtml += (foodNameHtml == '' ? foodNameArr[i] + 'FoodContent[' + index + ']=' + foodContentValue : '&'
                                            + foodNameArr[i] + 'FoodContent[' + index + ']=' + foodContentValue);
                        });
    }
    return foodNameHtml;
}
/**
 * 插入饮食回顾
 * 
 * 
 */
function insertFoodRecommend(recommendDateShow, fkPatientId, sysOwner, batchNo) {
    var foodNameHtml = getFoodRecommendName();
    if (!isEmpty(foodNameHtml)) {
        foodNameHtml += '&fkPatientId=' + fkPatientId + '&recommendDateShow=' + recommendDateShow + '&sysOwner=' + sysOwner + '&batchNo=' + batchNo;
        $.ajax({
            url : ctx + "/foodRecommend/insertFoodRecommend.shtml",
            type : "post",
            data : foodNameHtml,
            dataType : "json",
            async : false,
            success : function(data) {
                if (data.status) {
                    showTips("保存成功");
                }
            }
        });
    }
}
