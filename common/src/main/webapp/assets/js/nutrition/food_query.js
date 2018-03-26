var food = "";
$(function() {
    defaultFood();
})
// 查询营养含量
function queryFoodNutrition(id) {
    $.ajax({
        url : ctx + "/nutrition/queryNutritionFood.shtml",
        type : "post",
        data : "id=" + id,
        dataType : "json",
        success : function(data) {
            if (data.status) {
                var nutritionFood = data.listFoods;
                var temHtml = "";
                var temHtml2 = "";
                var nutritionFoodSize = nutritionFood.length;
                for (var i = 0; i < nutritionFood.length; i++) {
                    var item = nutritionFood[i];
                    var source = item.nutritionName;
                    var rt = [];
                    rt[1] = source.substring(0, source.indexOf("("));
                    rt[2] = source.substring(source.indexOf("(") + 1, source.indexOf(")"));
                    if (item != null) {
                        if (i <= (nutritionFoodSize / 2)) {
                            temHtml += '<p style="width: 90%;" class="u-span-2 f-span-2">' + rt[1];
                            temHtml += '<span class="u-span-3 pull-right">' + rt[2] + '</span>';
                            temHtml += '<span class="u-span-3 pull-right">' + item.nutritionContent + '</span>';
                        } else {
                            temHtml2 += '<p style="width: 90%;" class="u-span-2 f-span-2">' + rt[1];
                            temHtml2 += '<span class="u-span-3 pull-right"> ' + rt[2] + '</span>';
                            temHtml2 += '<span class="u-span-3 pull-right">' + item.nutritionContent + '</span>';
                        }
                    }
                }
                $("#loadInit").html(temHtml);
                $("#loadInit2").html(temHtml2);
            }
        }
    });
}
// 查询某食物的营养含量
function queryFoodAndNutrition(dom) {
    var id = $(dom).attr("data-value");
    $(dom).addClass("f-list-item active").siblings().removeClass("active");
    queryFoodNutrition(id);
}
function defaultFood() {
    queryFood()
    var dom = $("#foodUl").find("div:eq(0)");
    if (!isEmpty(dom)) {
        queryFoodAndNutrition(dom);
    }
}
var availableTags = new Array();
// 查询所有食物
function queryFood() {
    $.ajax({
        url : ctx + "/nutrition/queryFood.shtml",
        type : "post",
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.status) {
                food = data.foods;
                var showHtml = "";
                for (var i = 0; i < food.length; i++) {
                    var item = food[i];
                    if (item != null) {
                        showHtml += '<div class="f-list-item" data-value="' + item.id + '" data-value="' + item.id
                                        + '" onclick="queryFoodAndNutrition(this)">' + item.foodName;
                        showHtml += '<span class="hide">' + item.spellInitials + '</span></div>';
                        availableTags.push({
                            lable : item.foodName
                        });
                    }
                }
                $("#foodUl").html(showHtml);
                $('#searchFood').fastLiveFilter('#foodUl');
            }
        }
    });
    var id = $("#foodUl").find("div:eq(0)").attr("data-value");
    queryFoodNutrition(id);
}