var assayName;
var assayUnit;

/** 获取数据 */
function showAssayHistoryReport(itemCode, name, unit, isshow) {
    var s = $("#searchAssayHistory").find("[name='startDate']").val();
    var e = $("#searchAssayHistory").find("[name='endDate']").val();
    var patientId = $("#patientId").val();
    if (!isEmpty(itemCode)) {
        $("body").data("itemCode", itemCode);
    }
    if (!isEmpty(name))
        assayName = name;
    if (!isEmpty(unit))
        assayUnit = unit;
    /* if (!isEmpty(itemCode)) {
         // $("#assayHistoryDialog #reportDateDiv").data("reportDatePick").setReportDatePickType("y");
         return;
     }*/
    $.ajax({
        url : ctx + "/patient/assay/getAssayReport.shtml?tt=" + (new Date()).getTime(),
        type : "post",
        data : "startDateStr=" + s + "&endDateStr=" + e + "&patientId=" + patientId + "&itemCode=" + encodeURI($("body").data("itemCode")),
        dataType : "json",
        loading : true,
        success : function(data) {
            setLineOption(data, isshow);
        }
    });
}

function setLineOption(chartData, isshow) {
    if (isEmpty(chartData))
        return;
    var patientIndexChart = echarts.init(document.getElementById('assayHistoryChart'));
    var xAxis_data = new Array();
    var legend_data = new Array();
    var series = new Array();
    var formatter = function(params, ticket, callback) {
        var res = params[0].name;
        for (var i = 0, l = params.length; i < l; i++) {
            res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
        }
        return res;
    };
    // "{b}<br/>{a}:{c}";
    legend_data.push(assayName);
    var seriesWeightData = new Array();
    var assayHistoryHtml = '';
    for (var i = chartData.length - 1; i > 0; i--) {
        var item = chartData[i];
        var unitem = chartData[chartData.length - i];
        seriesWeightData.push(unitem.value);
        xAxis_data.push((new Date(unitem.time)).pattern("yyyy/MM/dd"));
        assayHistoryHtml += "<tr><td>" + (new Date(item.time)).pattern("yyyy/MM/dd") + "</td>";
        assayHistoryHtml += "<td>" + item.value + "</td></tr>";
    }
    $("#assayHistoryTable").html(assayHistoryHtml);
    var series_data = {
        name : assayName,
        type : 'line',
        smooth : true,
        itemStyle : {
            normal : {
                label : {
                    show : isshow,
                    formatter : '{c}'
                }
            }
        },
        data : seriesWeightData
    };
    series.push(series_data);
    var option = {
        tooltip : {
            trigger : 'axis',
            formatter : formatter
        },
        legend : {
            data : legend_data,
            top : '-2%'
        },
        grid : {
            left : '10%',
            right : '10%',
            bottom : '0%',
            top : '20%',
            containLabel : true
        },
        toolbox : {
            show : true,
            feature : {
                saveAsImage : {
                    show : true,
                    title : "保存图表为图片"
                }
            }
        },
        xAxis : [ {
            type : 'category',
            splitLine : {
                show : false
            },
            boundaryGap : false,
            data : xAxis_data
        } ],
        yAxis : [ {
            boundaryGap : [ 0, 1 ],
            type : 'value',
            max : Math.ceil(Math.max.apply(null, seriesWeightData)),
            min : Math.floor(Math.min.apply(null, seriesWeightData) == Math.max.apply(null, seriesWeightData) ? 0 : Math.min.apply(null,
                            seriesWeightData)),
            axisLabel : {
                formatter : function(value, index) {
                    return value.toFixed(2);
                }
            }
        } ],
        series : series
    };

    patientIndexChart.setOption(option);
}
