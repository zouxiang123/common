$(function() {
    similaryQueryFood();
    similaryCatogery();
})
/**
 * 查询营养标签
 */
function similaryCatogery() {
    var temHtml = "";
    for (var i = 0; i < nutritionList.length; i++) {
        var item = nutritionList[i];
        if (item != null) {
            var source = item.nutritionName;
            var rt = [];
            rt[1] = source.substring(0, source.indexOf("("));
            rt[2] = source.substring(source.indexOf("(") + 1, source.indexOf(")"));
            temHtml += '<label for="c' + i + '" class="m-b-12"><input type="checkbox" value=' + item.id + ' class="u-checkbox" name="similarCheck">'
                            + rt[1] + '</label>';
        }
    }
    $("#similarCatogery").html(temHtml);
}
/**
 * @param dom
 * @param type
 *            切换面板
 */
function chooseCatogery(dom, type) {
    $(dom).addClass("active").siblings().removeClass("active");
    if (type == 1) {
        $("#similarCatogery").css("display", "block");
        $("#similarContrast").css("display", "none");
    }
    if (type == 2) {
        $("#similarCatogery").css("display", "none");
        $("#similarContrast").css("display", "block");
    }
}
/**
 * @param dom
 *            改变相似食物源样式
 */
function selectSimilaryFood(dom) {
    $("#similaryFoodUi [data-text].u-span-2.f-span-2").removeClass("active");
    $(dom).addClass("active");

    $("#selectFoodId").val($(dom).attr("data-value"));
    $("#hFood1").text($(dom).attr("data-text"));
    // 自动变换营养素查询部分
    var chk_value = [];
    $('input[name="similarCheck"]:checked').each(function() {
        chk_value.push($(this).val());
    });
    var nutritionId = chk_value;
    if (chk_value.length > 0 && chk_value != null) {
        var table = $("#nutritionTable1");
        nutionContent($(dom).attr("data-value"), table);
    }
}
/**
 * @param foodID
 * @param table
 *            查询某食物某些营养素含量
 */
function nutionContent(foodID, table) {
    var chk_value = [];
    $('input[name="similarCheck"]:checked').each(function() {
        chk_value.push($(this).val());
    });
    var nutritionId = chk_value;
    $.ajax({
        url : ctx + "/nutrition/selectNutritionContent.shtml",
        type : "post",
        dataType : "json",
        data : "foodID=" + foodID + "&nutritionId=" + nutritionId,
        success : function(data) {
            if (data.status) {
                var fList = data.fList;
                var showHtml = "";
                for (var i = 0; i < fList.length; i++) {
                    var item = fList[i];
                    var source = item.nutritionName;
                    var rt = [];
                    rt[1] = source.substring(0, source.indexOf("("));
                    rt[2] = source.substring(source.indexOf("(") + 1, source.indexOf(")"));
                    showHtml += '<p style="width: 90%;" class="u-span-2 f-span-2">' + rt[1];
                    showHtml += '<span class="u-span-3 pull-right">' + rt[2] + '</span>';
                    showHtml += '<span class="u-span-2 f-span-2 pull-right m-r-8">' + item.nutritionContent + '</span></p><br/>';
                }
                table.html(showHtml);
            }
        }
    });
}
/**
 * @param foodID
 *            营养素对比
 */
function selectNutritionContent(foodID) {
    var table = $("#nutritionTable1");
    nutionContent(foodID, table);
}
function selectNutritionContent1(dom) {
    $("#similarCatogery").css("display", "none");
    $("#similarContrast").css("display", "block");
    $("#nutritionBut2").addClass("active");
    $("#nutritionBut1").removeClass("active");
    $("#hFood2").text($(dom).attr("data-text"));
    $(dom).addClass("u-span-2 f-span-2 active").siblings().removeClass("active");

    var foodID = $(dom).attr("data-value");
    var table = $("#nutritionTable2");
    nutionContent(foodID, table);
}
/**
 * 查询相似食物
 */
function querySimilaryFood() {
    var chk_value = [];
    $('input[name="similarCheck"]:checked').each(function() {
        chk_value.push($(this).val());
    });
    var percent = $("#percent").val();
    var nutritionId = chk_value;
    if (isEmpty(nutritionId) || nutritionId == "") {
        $("#similaryError").removeClass("hide");
        $("#similaryError>span").text("请选择关联的营养素");
        return;
    }
    var foodID = $("#selectFoodId").val();
    if (isEmpty(foodID)) {
        $("#similaryError>span").text("请选择要查询的食物");
        $("#similaryError").removeClass("hide");
        return;
    }
    $("#similaryError").addClass("hide")
    $.ajax({
        url : ctx + "/nutrition/querySimilaryFood.shtml",
        type : "post",
        dataType : "json",
        async : false,
        data : "percent=" + percent + "&fkFoodId=" + foodID + "&fkNutritionId=" + nutritionId,
        success : function(data) {
            if (data.status) {
                var food = data.liFoods;
                var showHtml = "";
                if (isEmpty(food) || food.length < 1) {
                    showTips("没有找到相似食物");
                    $("#similaryQueryFood").html(showHtml);
                    return;
                }
                $("#simalaryResultTitle").text("相似食物查询结果");
                for (var i = 0; i < food.length; i++) {
                    var item = food[i];
                    // 不显示本身
                    if (foodID != item.id) {
                        showHtml += '<p data-text="' + item.foodName + '" class="u-span-2 f-span-2" data-value="' + item.id
                                        + '" onclick="selectNutritionContent1(this)">' + item.foodName + '<span class="hide">' + item.spellInitials
                                        + '</span></p><br/>';
                    }
                }
                selectNutritionContent(foodID);
                $("#similaryQueryFood").html(showHtml);
            }
        }
    });
}
// 查询所有食物
function similaryQueryFood() {
    var showHtml = "";
    for (var i = 0; i < food.length; i++) {
        var item = food[i];
        showHtml += '<div><p data-text="' + item.foodName + '" class="u-span-2 f-span-2" data-value="' + item.id
                        + '" onclick="selectSimilaryFood(this)">' + item.foodName + '<span class="hide">' + item.spellInitials + '</span></p></div>';
    }
    $("#similaryFoodUi").html(showHtml);
    $('#searchSimilaryFood').fastLiveFilter('#similaryFoodUi');
}