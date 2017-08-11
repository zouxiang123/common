// 达标率统计
function setDbBar(barData) {
    var dbbarChart = echarts.init(document.getElementById('dbbarChart'));

    var option = {
        /*
         * title : { text : '达标统计' },
         */
        tooltip : {
            trigger : 'axis'
        },
        toolbox : {
            show : true,
            feature : {
                saveAsImage : {
                    show : true
                }
            }
        },
        legend : {
            data : barData.title
        },
        xAxis : [ {
            type : 'category',
            data : barData.xAxis
        } ],
        yAxis : [ {
            type : 'value',
            name : '数量（例）',
            axisLabel : {
                formatter : '{value}'
            }
        }, {
            type : 'value',
            name : '达标率',
            axisLabel : {
                formatter : '{value} %'
            },
            splitLine : {
                show : false
            }
        } ]
    };

    var series = [];
    for (var i = 0; i < barData.title.length; i++) {
        var item = {};
        item.name = barData.title[i];
        item.data = barData.data[i];
        if (i == barData.title.length - 1) {
            item.type = 'line';
            item.yAxisIndex = 1;
        } else {
            item.type = 'bar';
        }
        item.itemStyle = {
            normal : {
                label : {
                    show : true,
                    position : 'top'
                }
            }
        };
        series.push(item);

    }
    option.series = series;

    dbbarChart.setOption(option);
}

// 组装均值和标准差
function setAvgAndPop(lineData) {
    var zwsLineChart = echarts.init(document.getElementById('zwsLineChart'));

    var option = {
        /*
         * title : { text : '中位数统计' },
         */
        tooltip : {
            trigger : 'axis'
        },
        toolbox : {
            show : true,
            feature : {
                saveAsImage : {
                    show : true
                }
            }
        },
        legend : {
            data : lineData.title
        },
        xAxis : [ {
            type : 'category',
            data : lineData.xAxis
        } ],
        yAxis : [ {
            type : 'value',
            name : '平均值',
            axisLabel : {
                formatter : '{value} ' + lineData.yUnit
            }
        }, {
            type : 'value',
            name : '标准差',
            axisLabel : {
                formatter : '{value}'
            },
            splitLine : {
                show : false
            }
        } ],
        series : [ {
            name : lineData.title[0],
            type : 'line',
            itemStyle : {
                normal : {
                    label : {
                        show : true,
                        position : 'top'
                    }
                }
            },
            data : lineData.avgData
        }, {
            name : lineData.title[1],
            type : 'line',
            yAxisIndex : 1,
            itemStyle : {
                normal : {
                    label : {
                        show : true,
                        position : 'top'
                    }
                }
            },
            data : lineData.popData
        } ]
    };

    zwsLineChart.setOption(option);
}

// 组装达标率饼图数据
function setdblPie(pieData) {
    if (isEmpty(pieData)) {
        return;
    }
    var myChart2 = echarts.init(document.getElementById('chart2'));

    var option = {
        /*
         * title : { text : '达标率统计' },
         */
        tooltip : {
            trigger : 'item',
            formatter : "{a} <br/>{b} : {c}例 ({d}%)"
        },
        toolbox : {
            show : true,
            feature : {
                saveAsImage : {
                    show : true
                }
            }
        },
        legend : {
            orient : 'vertical',
            x : 'left',
            y : 'center',
            data : pieData.title
        },
        calculable : false,
        series : [ {
            name : '达标率统计',
            type : 'pie',
            radius : '55%',
            center : [ '50%', '50%' ],
            data : pieData.data
        } ]
    };

    // 为echarts对象加载数据
    myChart2.setOption(option);

}// setdblPie

// 组装分组饼图
function setGroupPie(pieData) {
    if (isEmpty(pieData)) {
        return;
    }
    var groupPie = echarts.init(document.getElementById('groupPie'));

    var option = {
        /*
         * title : { text : '分组统计' },
         */
        tooltip : {
            trigger : 'item',
            formatter : "{a} <br/>{b} : {c}例({d}%)"
        },
        toolbox : {
            show : true,
            feature : {
                saveAsImage : {
                    show : true
                }
            }
        },
        legend : {
            orient : 'vertical',
            x : 'left',
            y : 'center',
            data : pieData.title
        },
        calculable : false,
        series : [ {
            name : '分组统计',
            type : 'pie',
            radius : '55%',
            center : [ '50%', '50%' ],
            data : pieData.data
        } ]
    };

    // 为echarts对象加载数据
    groupPie.setOption(option);

}// setGroupPie

// 组装分组达标率报表饼图
function setMonthSeasonOkPie(pieData) {
    if (isEmpty(pieData)) {
        return;
    }
    var groupPie = echarts.init(document.getElementById('monthSeasonOkPie'));

    var option = {
        /*
         * title : { text : '分组统计' },
         */
        tooltip : {
            trigger : 'item',
            formatter : "{a} <br/>{b} : {c}例({d}%)"
        },
        toolbox : {
            show : true,
            feature : {
                saveAsImage : {
                    show : true
                }
            }
        },
        legend : {
            orient : 'vertical',
            x : 'left',
            y : 'center',
            data : pieData.title
        },
        calculable : false,
        series : [ {
            name : '分组统计',
            type : 'pie',
            radius : '55%',
            center : [ '50%', '50%' ],
            data : pieData.data
        } ]
    };

    // 为echarts对象加载数据
    groupPie.setOption(option);

}// setGroupPie

/** 获取报表数据 */
function getReport() {
    getReportByCondition();
}// getReport

function getRuleList() {
    $.ajax({
        url : ctx + "/report/assay/getRuleList.shtml",
        type : "post",
        loading : true,
        data : $("#selectForm").serialize(),
        dataType : "json",
        success : function(result) {
            var list = result.list || [];
            var html = '';
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                if (i > 0) {
                    html += '<span> / </span>';
                }
                html += '<div class="add-result add-result-number">';
                html += ' <input class="personal-input add-number" name="ruleList[' + i + ']" type="text" value="' + item.minValue + '" readonly>';
                html += ' <img class="delete-icon" src="' + ctx + '/assets/img/delete.png" data-minValue="' + item.minValue + '">';
                html += '</div>';

            }
            html += '<input class="tl-input margin-right-10 width-88 margin-left-30" type="text" style="height: 30px;" name="ruleList[' + list.length
                            + ']" id="addRule">';
            $("#ruleListDiv").html(html);

            // 注册删除事件
            registerRuleBtnDelEvent();
            // itemcode 变更需要加载报表数据
            if ($(document).data("itemCodeChangeNeedData")) {
                getReportByCondition();
                $(document).removeData("itemCodeChangeNeedData");
            }
        }
    });
}

// 重置分组
function resetRules() {
    $.ajax({
        url : ctx + "/report/assay/resetRules.shtml",
        type : "post",
        loading : true,
        data : $("#selectForm").serialize(),
        async : false,
        dataType : "json",
        success : function(result) {
            var list = result.list || [];
            var html = '';
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                if (i > 0) {
                    html += ' / ';
                }
                html += '<div class="add-result add-result-number">';
                html += ' <input class="personal-input add-number" name="ruleList[' + i + ']" type="text" value="' + item + '" readonly>';
                html += ' <img class="delete-icon" src="' + ctx + '/assets/img/delete.png" data-minValue="' + item + '">';
                html += '</div>';
            }
            html += '<input class="tl-input margin-right-10 width-88 margin-left-30" type="text" style="height: 30px;" name="ruleList[' + list.length
                            + ']" id="addRule">';

            $("#ruleListDiv").html(html);
            // 注册删除事件
            registerRuleBtnDelEvent();
        }
    });
}

// 查询报表数据
function getReportByCondition() {

    changeTab(0, "");
    // 没有选择化验项，不进行操作
    if (isEmpty($("#itemCode").val())) {
        return false;
    }

    $.ajax({
        url : ctx + "/report/assay/selectYearDatas.shtml",
        type : "post",
        loading : true,
        data : $("#selectForm").serialize(),
        dataType : "json",
        success : function(chartData) {
            setDbBar(chartData.bar);
            setdblPie(chartData.pie);
            setAvgAndPop(chartData.line);
            showMainTable(chartData);
        }
    });
}

/** 获取化验单类别数据 */
function getAssayCategory() {
    $.ajax({
        url : ctx + "/system/dictionary/getAssayCategoryList.shtml",
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {

            var htmlSelect = '';
            for (var i = 0; i < data.items.length; i++) {
                var item = data.items[i];
                htmlSelect += '<option value="' + item.groupId + '">' + item.groupName + '</option>';
            }
            $("#groupId").html(htmlSelect);
            if (data.status == 1) {
                getAssayitem();
            }
        }
    });
}

function searchPatientLabelAll() {
    $.ajax({
        url : ctx + "/patient/label/searchPatientLabelAll.shtml",
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {

            var htmlSelect = '';
            htmlSelect += '<option value="" selected="selected">全部</option>';
            for (var i = 0; i < data.context.length; i++) {
                var item = data.context[i];
                htmlSelect += '<option value="' + item.id + '">' + item.name + '</option>';
            }
            $("#patientLabelId").html(htmlSelect);

        }
    });
}

/** 获取table数据 */
function getAssayitem() {
    var groupId = convertEmpty($("#groupId").val());
    $.ajax({
        url : ctx + "/system/dictionary/getAssayList.shtml",
        type : "post",
        data : "groupId=" + encodeURI(groupId),
        dataType : "json",
        success : function(data) {

            var htmlSelect = '';
            if (data.status == 1) {
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    htmlSelect += '<option value="' + item.itemCode + '" data-minValue="' + convertEmpty(item.personalMinValue) + '" data-maxValue="'
                                    + convertEmpty(item.personalMaxValue) + '" data-dateType="' + item.dateType + '">' + item.itemName + '</option>';
                }
            }
            $("#itemCode").html(htmlSelect);

            var itemCode = $(document).data("itemCode");
            if (!isEmpty(itemCode)) {
                $("#itemCode").val(itemCode);
            }
            $("#itemCode").change();

        }
    });
}

/** 根据检查类别获取检查项列表 */
function changeGroupId() {
    $(document).data("itemCode", "");
    getAssayitem();
}

/** 获取置顶的化验项 */
function getAssayListByTop() {
    $.ajax({
        url : ctx + "/system/dictionary/getAssayListByTop.shtml",
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {

            var html = '';
            for (var i = 0; i < data.items.length; i++) {
                var item = data.items[i];
                html += '<div class="select-item" data-itemcode="' + item.itemCode + '" data-groupid="' + item.groupId + '">' + item.itemName
                                + '</div>';
            }
            $("#assayTopDiv").html(html);
        }
    });
}

/** table显示 */
function showMainTable(chartData) {
    var xAxis = chartData.bar.xAxis;
    var monthSeason = chartData.bar.monthSeason;
    var titles = chartData.bar.title;

    var html_thead = '<tr><th>时间</th><th>总数</th>';

    for (var i = 0; i < titles.length; i++) {
        if (i == titles.length - 1) {
            html_thead += '<th>达标率（%）</th>';
        } else {
            html_thead += '<th>' + titles[i] + '数（例）</th>';
        }
    }

    html_thead += '<th>平均值</th><th>标准差</th></tr>';

    $("#mainThead").html(html_thead);

    var html_tbody = '';

    var datas = chartData.bar.data;
    var avgData = chartData.line.avgData;
    var popData = chartData.line.popData;
    var countData = chartData.line.countData;

    for (var i = 0; i < xAxis.length; i++) {
        html_tbody += '<tr style="height: 48px !important" data-value="' + monthSeason[i] + '" data-total="' + countData[i] + '">';
        html_tbody += '<td>' + xAxis[i] + '</td>';
        html_tbody += '<td>' + countData[i] + '</td>';
        for (var j = 0; j < datas.length; j++) {
            html_tbody += '<td>' + datas[j][i] + '</td>';
        }
        html_tbody += '<td>' + avgData[i] + '</td>';
        html_tbody += '<td>' + popData[i] + '</td>';
        html_tbody += '</tr>';
    }

    $("#mainTbody").html(html_tbody);
}

// 获取分组信息
function getGroupPie() {
    $.ajax({
        url : ctx + "/report/assay/countItem.shtml",
        type : "post",
        data : "dateValue=" + dateValue + "&" + $("#selectForm").serialize(),
        dataType : "json",
        loading : true,
        success : function(data) {
            recordList = data.recordList;
            var html_tbody = '';
            for (var i = 0; i < recordList.length; i++) {
                var item = recordList[i];
                html_tbody += '<tr class="table-default" data-okindex="' + item.okIndex + '" data-groupindex="' + item.groupIndex + '">';
                html_tbody += '<td>' + item.patientName + '</td>';
                html_tbody += '<td>' + item.result + '</td>';
                /* html_tbody += '<td>' + item.resultTipsShow + '</td>'; */
                html_tbody += '</tr>';
            }
            $("#itemTbody").html(html_tbody);

            setGroupPie(data.groupMap);// 分组报表
            setGroupTable(data.groupMap);// 分组表格
            setMonthSeasonOkPie(data.okRangePie);// 达标率报表
            setMonthSeasonOkTable(data.okRangePie);// 达标率表格
        }
    });
}

// 获取月度分组表格
function setGroupTable(groupMap) {
    var titles = groupMap.title;
    var datas = groupMap.data;
    var dataValues = [];
    var total = 0;

    for (var i = 0; i < datas.length; i++) {
        dataValues[i] = datas[i].value;
        total += datas[i].value;
    }

    var html = '';
    for (var i = 0; i < dataValues.length; i++) {
        html += '<tr data-index="' + i + '"><td>' + titles[i] + '</td>';
        html += '<td>' + dataValues[i] + '</td>';
        html += '<td>' + (dataValues[i] / total).toPercent() + '</td></tr>';
    }

    html += '<tr data-index=""><td>总计</td><td>' + total + '</td><td>100%</td></tr>';
    $("#groupTbody").html(html);
}

// 获取月度达标率表格
function setMonthSeasonOkTable(okRangePie) {
    var titles = okRangePie.title;
    var datas = okRangePie.data;
    var dataValues = [];
    var total = 0;

    for (var i = 0; i < datas.length; i++) {
        dataValues[i] = datas[i].value;
        total += datas[i].value;
    }

    var html = '';
    for (var i = 0; i < dataValues.length; i++) {
        html += '<tr data-index="' + i + '"><td>' + titles[i] + '</td>';
        html += '<td>' + dataValues[i] + '</td>';
        html += '<td>' + (dataValues[i] / total).toPercent() + '</td></tr>';
    }

    html += '<tr data-index=""><td>总计</td><td>' + total + '</td><td>100%</td></tr>';
    $("#okTbody").html(html);
}

// 显示隐藏tab
function changeTab(index, dateShow) {
    $("#dateTitle div:eq(" + index + ")").addClass("active").siblings().removeClass("active");
    $("#contentDiv > div:eq(" + index + ")").show().siblings().hide();
    if (!isEmpty(dateShow)) {
        $("#dateTitle div:eq(" + index + ") > span > span").text("(" + convertEmpty(dateShow) + ")");
    }
    if (index == 0) {
        $("#dateTitle div:eq(1)").hide();
    } else {
        var dateType = $("#dateType").val();

        var title = '';
        if (dateType == 'm') {
            title += '月度报表';
        } else {
            title += '季度报表';
        }
        title += "(" + convertEmpty(dateShow) + ")";
        $("#dateTitle div:eq(1) > span").text(title);
        $("#dateTitle div:eq(1)").show();
    }
}

// 根据选中值隐藏显示名单详情
function showDetialTable(okIndex, groupIndex) {
    if (!isEmpty(okIndex)) {
        $("#itemTbody > tr").hide();
        $('#itemTbody > tr[data-okindex="' + okIndex + '"]').show();
    } else if (!isEmpty(groupIndex)) {
        $("#itemTbody > tr").hide();
        $('#itemTbody > tr[data-groupindex="' + groupIndex + '"]').show();
    } else {
        $("#itemTbody > tr").show();
    }

    $("body").animate({
        scrollTop : $("#patientDiv").offset().top
    }, 1000);
    // $("#patientDiv").animate({
    // scrollTop : '0px'
    // }, 400);
}

$(function() {
    // 首次加载页面默认显示第一条化验项数据
    $(document).data("itemCodeChangeNeedData", true);
    getAssayListByTop();
    getAssayCategory();
    // 没有患者标签表
    // searchPatientLabelAll();
    // 返回年度统计
    $("#yearTitle").click(function() {
        changeTab(0, "");
    });

    // 选择模板
    $("#assayTopDiv").on("click", "div[data-itemcode]", function() {
        $(this).addClass("active").siblings().removeClass("active");

        var groupId = $(this).attr("data-groupid");
        $(document).data("itemCode", $(this).attr("data-itemcode"));
        // itemcode 变更需要加载报表数据
        $(document).data("itemCodeChangeNeedData", true);

        $("#groupId").val(groupId);
        getAssayitem();// 模拟化验类型修改

    });

    // 达标范围最大值失焦事件
    $("#maxValue").blur(function() {
        getReportByCondition();
    });

    // 点击主表单行,显示当前时间段的明细
    $("#mainTbody").on("click", "tr", function() {
        $(this).addClass("active").siblings().removeClass("active");

        var totalCount = $(this).attr("data-total");
        if (isEmpty(totalCount) || totalCount == 0) {
            return false;
        }

        dateValue = $(this).attr("data-value");
        changeTab(1, dateValue);
        getGroupPie();
    });

    // 确定修改分组规则【无效方法，弃用】
    /*$("#addRuleBtn").click(function() {
        resetRules();
        if ($("#contentDiv > div:eq(1)").is(":visible")) {
            getGroupPie();
        }
    });*/

    // 点击达标列表的行
    $("#groupTbody").on("click", "tr", function() {
        $(this).addClass("active").siblings().removeClass("active");
        $("#okTbody > tr").removeClass("active");

        var groupIndex = $(this).attr("data-index");
        showDetialTable("", groupIndex);
    });

    // 点击分组列表的行
    $("#okTbody").on("click", "tr", function() {
        $(this).addClass("active").siblings().removeClass("active");
        $("#groupTbody > tr").removeClass("active");

        var okIndex = $(this).attr("data-index");
        showDetialTable(okIndex, "");
    });

    // 点击下载年度统计
    $("#downloadYear").click(function() {
        $("#errorShowInfor").css("display", "none");
        window.location.href = ctx + "/report/assay/downloadYear.shtml?" + $("#selectForm").serialize();
        /*var minValue=$("#minValue").val();
        var maxValue=$("#maxValue").val();
        if(isEmpty(minValue)||isEmpty(maxValue)){
            $("#errorShowInfor").css("display","inline");
            showWarn("请输入达标范围");
        }
        else{
            $("#errorShowInfor").css("display","none");
            window.location.href = ctx + "/report/assay/downloadYear.shtml?" + $("#selectForm").serialize();
        }*/
        /*alert(minValue+"  "+maxValue);*/
        /*window.location.href = ctx + "/report/assay/downloadYear.shtml?" + $("#selectForm").serialize();*/
    });

    // 点击下载年度统计
    $("#downloadDetail").click(function() {
        window.location.href = ctx + "/report/assay/downloadDetail.shtml?" + $("#selectForm").serialize() + "&dateValue=" + dateValue;
    });

});
/**
 * 化验项变更事件
 */
function changeItemCode() {
    // 非置顶项点击事件或者初始化显示，才需要移除置顶项active 效果
    if (!$(document).data("itemCodeChangeNeedData")) {
        $("#assayTopDiv div[data-itemcode]").removeClass("active");// 删除置顶项选中事件
    }
    var minValue = $("#itemCode option:selected").attr("data-minValue");
    var maxValue = $("#itemCode option:selected").attr("data-maxValue");
    $("#minValue").val(minValue);// 设置最大值
    $("#maxValue").val(maxValue);// 设置最小值

    getRuleList();// 查询化验项的分组规则
}

/**
 * 点击添加功能
 */
function addRuleBtnEvent() {
    resetRules();
    if ($("#contentDiv > div:eq(1)").is(":visible")) {
        getGroupPie();
    }
}

/**
 * 动态注册事件
 */
function registerRuleBtnDelEvent() {
    // 删除数值分组
    $("#ruleListDiv").on("click", "img.delete-icon", function() {
        $(this).parent().remove();
        resetRules();
        if ($("#contentDiv > div:eq(1)").is(":visible")) {
            getGroupPie();
        }
    });
}
