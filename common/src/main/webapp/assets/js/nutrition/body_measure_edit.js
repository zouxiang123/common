var body_measure_edit = {
    init : function() {
        this.addEvents();
        this.addValidate();
    },
    /**
     * 事件注册
     */
    addEvents : function() {
        // 日历查询
        layui.use('laydate', function() {
            var laydate = layui.laydate;
            $("#bodyMeasureForm").find('input[name="recordDateShow"]').each(function() {
                laydate.render({
                    elem : this,
                    theme : '#31AAFF',
                    btns : [ "now", "confirm" ]
                });
            });
        });
        // 计算与身高相关指标
        $("#bodyMeasureForm").on("change", "[data-calcstature]", function() {
            var form = $("#bodyMeasureForm");
            var stature = $("[name='stature']", form).val();
            var weight = $("[name='weight']", form).val();
            if (isEmpty(stature) || isEmpty(weight) || isNaN(stature) || isNaN(weight))
                return;
            var param = {
                patientId : $("#patientId").val(),
                stature : stature,
                weight : weight
            };
            body_measure_edit.setCalcData(param, ctx + "/nuCalc/getStature.shtml", form, $(this).attr("data-calcstature"), function(rs) {
                var bmi = rs.bmi;
                var tips = "";
                if (bmi < 18.5) {
                    tips = "过轻";
                } else if (bmi >= 25 && bmi <= 30) {
                    tips = "超重";
                } else if (bmi > 30) {
                    tips = "肥胖";
                }
                $("#bmiTipsSpan").html(tips);
            });
        });
        // 计算上臂肌围数据
        $("#bodyMeasureForm").on("change", "[data-calcmamc]", function() {
            var form = $("#bodyMeasureForm");
            var tsf = $("[name='tsf']", form).val();
            var mac = $("[name='mac']", form).val();
            // 是皮褶部位肱二头肌时
            if (isEmpty(tsf) || isEmpty(mac) || isNaN(tsf) || isNaN(mac))
                return;
            var data = {
                tsf : tsf,
                mac : mac
            };
            body_measure_edit.setCalcData(data, ctx + "/nuCalc/getMamc.shtml", form, $(this).attr("data-calcmamc"));
        });
        // 计算腰臀比数据
        $("#bodyMeasureForm").on("change", "[data-calcwhr]", function() {
            var form = $("#bodyMeasureForm");
            var waist = $("[name='waist']", form).val();
            var hip = $("[name='hip']", form).val();
            if (isEmpty(waist) || isEmpty(hip) || isNaN(waist) || isNaN(hip))
                return;
            var data = {
                waist : waist,
                hip : hip
            };
            body_measure_edit.setCalcData(data, ctx + "/nuCalc/getWHR.shtml", form, $(this).attr("data-calcwhr"), function(rs) {
                var whr = rs.whr / 100.00;
                var isMan = $("#patientSex").val() == "M";
                var tips = "";
                // 正常男性>=0.85，女性>=0.80位腹型肥胖
                if ((isMan && (whr >= 0.85)) || (!isMan && (whr >= 0.8))) {
                    tips = "肥胖";
                }
                $("#whrTipsSpan").html(tips);
            });
        });
    },
    /** 获取并设置自动计算的结果字段的值 */
    setCalcData : function(data, url, form, calcNodesStr, callback) {
        $.ajax({
            url : url,
            type : "post",
            data : data,
            dataType : "json",
            loading : false,
            success : function(result) {
                if (result.status == "1") {
                    var calcNodes = calcNodesStr.split(",");
                    for (var i = 0; i < calcNodes.length; i++) {
                        $("[name='" + calcNodes[i] + "']", form).val(convertEmpty(result[calcNodes[i]]));
                        $("[data-" + calcNodes[i] + "span]", form).text(convertEmpty(result[calcNodes[i]]));
                    }
                    if (!isEmpty(callback)) {
                        callback(result);
                    }
                }
            }
        });
    },
    /**
     * 保存数据
     * 
     * @param callback
     */
    save : function(callback) {
        if ($("#bodyMeasureForm").valid()) {
            $.ajax({
                url : ctx + "/nuBodyMeasure/save.shtml",
                data : $("#bodyMeasureForm").serialize(),
                type : "post",
                dateType : "json",
                success : function(result) {
                    if (result.status == "1") {
                        showTips("保存成功");
                        if (!isEmpty(callback)) {
                            callback();
                        }
                    } else {
                        showWarn(result.errmsg);
                    }
                }
            });
        }
    },
    addValidate : function() {
        $('#bodyMeasureForm').validate({
            // 校验字段
            rules : {
                recordDateShow : {
                    required : [ "测量日期" ]
                },
                weight : {
                    number : [ "体重" ]
                },
                statureMeasureValue : {
                    number : [ "测量结果" ]
                },
                stature : {
                    number : [ "身高" ]
                },
                mac : {
                    number : [ "上臀围" ]
                },
                tsf : {
                    number : [ "三头肌皮褶厚度" ]
                },
                waist : {
                    number : [ "腰围" ]
                },
                hip : {
                    number : [ "臀围" ]
                },
                ssf : {
                    number : [ "肩胛下皮褶厚度" ]
                },
                asf : {
                    number : [ "腹部皮褶厚度" ]
                }
            },
            errorPlacement : function(error, element) {
                var obj = getValidateErrorDisplayEl($(element));
                $(error).css("display", "block");
                obj.find("[data-error]").append(error);
            }
        });
    }
};