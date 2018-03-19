<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<%@ include file="../common/head_theme.jsp"%>
<title>转归统计</title>
<style>
.tab-leftsetting span:first-child {
    border-left: 0px
}

.myactive {
    color: #31aaff;
}

.titletab {
    line-height: 32px;
    cursor: pointer;
    padding: 7px 12px;
    border: 1px solid #D9E0E6;
    border-bottom: 0px;
}

.hide {
    display: none;
}
</style>
</head>
<body style="overflow-x:hidden">
    <jsp:include page="../common/report_datepick_new2.jsp" flush="true"></jsp:include>
    <div class="xtt">
        <div class="bed-head pb-4" style="border-bottom: 0px !important;">
            <div class="bb-line" id="reportDateDiv">
                <span class="myactive titletab" id="home" onclick="showOutComeData(true)">数据统计</span>
                <div class="tab-head-text tab-leftsetting" id="tabsDiv" style="margin-left: -4px;"></div>
                <div class="tablesetting u-float-r ml-10" id="tablesetting" style="bottom: 0px;">
                    <button type="button" class="open-show" data-show="#promptDialog">报表设置</button>
                </div>
                <div class="tablesetting u-float-r" style="bottom: 0px;">
                    <button type="button" class="" id="download">报表下载</button>
                </div>
            </div>
        </div>

        <div id="outComeDiv">
            <div data-tab="year">
                <div class="echartshalf">
                    <label class="u-checkbox echartsave" style= "right:130px;top:12px"> <input
                        type="checkbox" name="che" id="shownum" checked> <span class="icon-checkbox"></span>显示数据
                    </label>
                    <div id="chart1" class="u-clearfix w-100 u-display-block" style="height:260px;"></div>
                </div>
                <div class="ml-12 mr-12 mt-8" id="tableDiv"></div>
            </div>

            <div class="hide" data-tab="month">
                <div class="echartshalf">
                    <label class="u-checkbox echartsave" style= "right:118px;top:12px"> <input
                        type="checkbox" name="che" id="shownum2" checked> <span class="icon-checkbox"></span> 显示数据
                    </label>
                    <div id="chart2" class="u-clearfix w-100 u-display-block" style="height:260px;"></div>
                </div>
                <div class="ml-12 mr-14 mt-8">
                    <table class="u-table u-table-bordered" style="overflow: auto;">
                        <thead>
                            <tr>
                                <th style="width: 30%">名称</th>
                                <th style="width: 36%">数量</th>
                                <th style="width: 34%">占比</th>
                            </tr>
                        </thead>
                        <tbody id="monthTableBody"></tbody>
                    </table>
                </div>

                <div class="patientlist">
                    <span>患者列表</span>
                </div>
                <div class="ml-12 mr-14">
                    <div class="u-table-fixed" id="patientTable">
                        <div class="u-table-fixed-head">
                            <table class="u-table u-table-bordered">
                                <thead></thead>
                            </table>
                        </div>
                        <div class="u-table-fixed-body" id="main">
                            <table class="u-table u-table-bordered">
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="tabsBodyDiv"></div>
        <div class="hide" data-tabdiv style="width: 100%; height: 100px" id="basicIframeDiv">
            <iframe src="" frameborder="0" width="100%" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
        </div>
    </div>

    <script src="${ctx }/assets/js/report/outcome_report.js?version=${version}"></script>
    <script src="${ctx }/assets/js/common/tab_nav2.js?version=${version }"></script>
    <script>
        $(function() {
            $(".sidebar li#outcomeNav").addClass("active").siblings().removeClass("active");
        });
    </script>
    <script type="text/javascript">
        tab_nav.init();
        //var tabsMaxWidth = $(".tab-header").width() - 200;
        var tabsMaxWidth = $(".bed-head").width() - 200;
        $("#tabsDiv").on("click", "[data-url]", function() {
            showOutComeData(false);
        });
        tab_nav.removeActiveTab = function(el) {
            if (isEmpty(el)) {
                el = $("#tabsDiv [data-url].active");
            }
            // 删除iframe body
            $("#" + el.data("target")).remove();
            // 删除tab
            $(el).remove();
            if ($("#tabsDiv [data-url]").length > 0 && $("#tabsDiv [data-url].active").length == 0) {
                showOutComeData(true);
            }
            //重新计算Tabs宽度
            reLayoutTabsWidth();
        }
    
        //动态规定表格的高度
        MaxAdaptive('#main', 40);
    </script>
</body>
</html>
