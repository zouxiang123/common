var nu_list = {
    patientId : null,
    patientName : null,
    /**
     * 初始化页面
     */
    init : function() {
        this.patientId = $("#patientId").val();
        this.patientName = $("#patientName").val();
        body_measure.init();
        assessment.init();
        food_record.init();
        this.addEvents();
        // 默认显示第一页
        $("#nuTypeDiv").find("[data-type]:not('.hide'):first").click();
    },
    /**
     * 时间注册
     */
    addEvents : function() {
        $("#nuTypeDiv").on("click", "[data-type]", function() {
            $(".nutrition,nutrition-action").addClass("hide");
            $(this).addClass("u-btn-blue").siblings().removeClass("u-btn-blue");
            var type = $(this).data("type");
            if (type == "body_measure") {// 人体测量
                body_measure.show();
            } else if (type == "assessment") {
                assessment.show();
            } else if (type == "food_record") {
                food_record.show();
            }
        });
    }
};
/**
 * 人体测量
 */
var body_measure = {
    init : function() {
        addEvents();
    },
    /**
     * 事件注册
     */
    addEvents : function() {
        layui.use('laydate', function() {
            var laydate = layui.laydate;
            $("#nuQueryDiv").find('[date-target="body_measure"] [datepicker]').each(function() {
                laydate.render({
                    elem : this,
                    theme : '#31AAFF',
                    btns : [ "now", "confirm" ]
                });
            });
        });
    },
    /**
     * 显示人体测量列表
     */
    show : function() {
        $("#body_measure_div").removeClass("hide");
    },
    getList : function() {

    }
};
/**
 * 评估
 */
var assessment = {
    init : function() {

    },
    getList : function() {

    }
};
/**
 * 饮食记录
 */
var food_record = {
    init : function() {

    },
    getList : function() {

    }
};