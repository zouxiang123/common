var unit = "";
var nutritionList = "";
$(function() {
    queryNutrition();
    defaultNutrition();
})
/**
 * 查询所有营养素
 */
function queryNutrition() {
    $.ajax({
        url : ctx + "/food/nutrition/queryNutrition.shtml",
        type : "post",
        dataType : "json",
        async : false,
        success : function(data) {
            if (data.status) {
                nutritionList = data.lNutritions;
                var boday = $("#nutritionCatogery");
                var temHtml = "";
                for (var i = 0; i < nutritionList.length; i++) {
                    var item = nutritionList[i];
                    if (item != null) {
                        var source = item.nutritionName;
                        var rt = [];
                        rt[1] = source.substring(0, source.indexOf("("));
                        rt[2] = source.substring(source.indexOf("(") + 1, source.indexOf(")"));
                        temHtml += '<button type="button" class="u-button1 f-button-2" data-unit="' + rt[2]
                                        + '" data-select="" onclick="selectNutritionDesc(this),selectNutritionAsc(this)">' + rt[1] + '</button>';
                    }
                }
                $("#nutritionCatogery").html(temHtml);
            }
        }
    });
}
/**
 * @param address
 * @param dom
 * @param table
 * @param record
 *            查询选中食物的营养素
 */
function queryNutritionUrl(address, dom, table, record) {
    isEmpty(record) ? record = 0 : record = record;
    // 点击时给当前项data赋值
    $(dom).attr("data-select", "nutriment").siblings().attr("data-select", "");
    unit = $(doms).attr("data-unit");
    var nutritionName = "";
    // 获取选中的营养素的名称
    var data = $("button[data-select=nutriment]").text();
    isEmpty(data) ? nutritionName = $(dom).text() : nutritionName = data;
    $(dom).addClass("active").siblings().removeClass("active");
    $.ajax({
        url : address,
        type : "post",
        data : "nutritionName=" + nutritionName + "&record=" + record,
        dataType : "json",
        success : function(data) {
            if (data.status) {
                var lNutritions = data.foodAndNutritionPOs;
                var temHtml = "";
                for (var i = 0; i < lNutritions.length; i++) {
                    var item = lNutritions[i];
                    if (item != null) {
                        temHtml += '<p style="width: 90%;" class="u-span-2 f-span-2">' + item.foodName + '</td>';
                        temHtml += '<span class="u-span-3 pull-right" id="unit">' + unit + '</span>'
                        temHtml += '<span class="u-span-2 f-span-2 pull-right m-r-8">' + item.nutritionContent + '</span></p><br/>'
                    }
                }
                table.html(temHtml);
            }
        }
    });
}
/**
 * @param dom
 *            获取当前查询营养素的节点
 */
function nutritionDescDom(dom) {
    var url = ctx + "/nutrition/selectNutritionDesc.shtml";
    var table = $("#moreTable");
    var record = $("#record").val();
    queryNutritionUrl(url, dom, table, record);
}
function nutritionAscDom(dom) {
    var url = ctx + "/nutrition/selectNutritionAsc.shtml";
    var table = $("#littleTable");
    var record = $("#records").val();
    queryNutritionUrl(url, dom, table, record);
}

/**
 * 默认选中第一条
 */
function defaultNutrition() {
    var dom = $("#nutritionCatogery").find("button:eq(0)");
    if (!isEmpty(dom)) {
        selectNutritionDesc(dom);
        selectNutritionAsc(dom);
    }
}
/**
 * 某营养成分含量最多的N条
 */
function selectNutritionDesc(dom) {
    doms = dom;
    nutritionDescDom(dom);
}
function selectNutritionDescNone() {
    nutritionDescDom(doms);
}
/**
 * @param dom
 *            某营养成分含量最少的N条
 */
function selectNutritionAsc(dom) {
    doms = dom;
    nutritionAscDom(dom);
}
function selectNutritionAscNone() {
    nutritionAscDom(doms);
}
