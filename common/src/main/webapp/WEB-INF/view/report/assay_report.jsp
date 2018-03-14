<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<title>化验项统计</title>
<style>
#assayTopDiv span.active {
    color: #31aaff
}

table>tbody tr.active {
    background: rgba(217, 224, 230, 0.5);
}
</style>
</head>
<body>
    <jsp:include page="../common/report_datepick_new2.jsp" flush="true"></jsp:include>
    <div class="xtt">
        <div class="bed-head pb-4 mt-20" style="border-bottom: 0px !important;">
            <div class="bb-line" id="reportDateDiv">
                <div class="tab-head-text tab-leftsetting">
                    <span style="border: none; margin-left: -12px">数据统计</span>
                </div>
                <div class="tablesetting u-float-r ml-10">
                    <button type="button" class="open-show" data-show="#promptDialog">报表设置</button>
                </div>
                <div class="tablesetting u-float-r">
                    <button type="button" id="downloadYear">报表下载</button>
                </div>
                
                <div class="tablesetting u-float-r" style="display:none" >
                    <button type="button" id="downloadDetail">报表下载</button>
                </div>

                <div class="u-pop-up-from"></div>
                <form id="selectForm" action="#" style="display: none;">
                    <div class="mb-10">
                        <div class="tab-list-text2 line-height-35">选择年份：</div>
                        <div class="bb-dashed pb-10 u-display-inlineBlock">
                            <input type="text" style="width: 280px" name="assayYear" id="reportDate" readonly="readonly" datetimepicker>
                        </div>
                    </div>

                    <div class="mb-10">
                        <div class="bb-dashed pb-10 u-display-inlineBlock" style="display: none">
                            <label class="u-select w-100"> <select class="w-100" name="dateType" id="dateType">
                                    <option value="m">按月分组</option>
                                    <option value="s">按季度分组</option>
                            </select>
                            </label>
                        </div>
                    </div>

                    <div class="mb-10">
                        <div class="tab-list-text2 line-height-35">检查类别：</div>
                        <div class="bb-dashed pb-10 u-display-inlineBlock width-280">
                            <label class="u-select w-100"> <select class="w-100" name="groupId" id="groupId" onchange="changeGroupId()"></select>
                            </label>
                        </div>
                    </div>

                    <div class="mb-10">
                        <div class="tab-list-text2 line-height-35">检查项列表：</div>
                        <div class="bb-dashed pb-10 u-display-inlineBlock width-280">
                            <label class="u-select w-100"> <select class="w-100" name="itemCode" id="itemCode" onchange="changeItemCode()"></select>
                            </label>
                        </div>
                    </div>
                    <div class="mb-10">
                        <div class="tab-list-text2 line-height-35">标签管理：</div>
                        <div class="bb-dashed pb-10 u-display-inlineBlock width-280">
                            <label class="u-select w-100"> 
                                <select style="width:282px" name="patientLabelId">
                                    <option value="">全部</option>
                                    <c:forEach var="item" items="${labels }">
                                        <option value="${item.id }">${item.name }</option>
                                    </c:forEach>
                                </select>
                            </label>
                        </div>
                    </div>
                    <div>
                        <div class="tab-list-text2 line-height-35">达标范围：</div>
                        <div class="bb-dashed pb-10 u-display-inlineBlock">
                            <input type="text" style="width: 120px" name="minValue" id="minValue"> <span class="ml-8 mr-8">~</span> <input
                                type="text" style="width: 120px" name="maxValue" id="maxValue">
                        </div>
                    </div>

                    <div>
                        <div class="tab-list-text2 line-height-35">分组规则：</div>
                        <div class="bb-line pb-10 u-display-inlineBlock width-280">
                            <div class="u-display-inlineBlock position-relative mt-10" id="ruleListDiv"></div>
                            <button type="button" class="u-btn-blue mr-8 mt-10" onclick="addRuleBtnEvent();">添加</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="ml-12 mt-6 mb-10">
            <span>常用项：</span>
            <div class="mt-6 mb-6 cursor" style="line-height: 30px;" id="assayTopDiv"></div>
        </div>

        <div class="bb-line ml-12 mr-12">
            <div class="tab-head-text tab-leftsetting" id="dateTitle">
                <span class="active u-border-r" id="yearTitle">年度报表</span> 
                <span style="display: none; line-height: 35px; border-left: none"></span>
            </div>
        </div>

        <div id="contentDiv">
            <div>
                <div class="echartshalf">
                    <div class="echartname">达标统计</div>
                    <label class="u-checkbox echartsave"> <input type="checkbox" name="echarts1" id="shownum" checked> <span
                        class="icon-checkbox"></span> 显示数据
                    </label>
                    <div id="dbbarChart" class="mt-24" style="width: 100%; height: 98%"></div>
                </div>

                <div class="echartshalf">
                    <div class="echartname">中位数统计</div>
                    <label class="u-checkbox echartsave"> <input type="checkbox" name="echarts2" id="shownum2" checked> <span
                        class="icon-checkbox"></span> 显示数据
                    </label>
                    <div id="zwsLineChart" class="mt-24" style="width: 100%; height: 98%"></div>
                </div>

                <div class="echartshalf mt-10">
                    <div class="echartname">达标率统计</div>
                    <label class="u-checkbox echartsave"> <input type="checkbox" name="echarts3" id="shownum3" checked> <span
                        class="icon-checkbox"></span> 显示数据
                    </label>
                    <div id="chart2" class="mt-24" style="width: 100%; height: 250px"></div>
                </div>

                <div class="ml-22 mt-6 mb-10">化验项列表</div>
                <div class="ml-12 mr-12 mb-10">
                    <table class="u-table u-table-bordered">
                        <thead id="mainThead"></thead>
                        <tbody id="mainTbody"></tbody>
                    </table>
                </div>
            </div>

            <div style="display: none">
                <div class="qualityecharts1">
                    <span class="position-relative" style="top: 24px; left: 2px">达标统计 </span> <label class="u-checkbox u-float-r mt-20 pr-24"
                        style="z-index: 10;"> <input type="checkbox" name="echarts4" id="shownum4" checked> <span class="icon-checkbox"></span>
                        显示统计数据
                    </label>
                    <div id="monthSeasonOkPie" style="width: 100%; height: 100%; top: -28px;"></div>
                </div>
                <div class="ml-12 mr-12 mb-10" style="margin-top: -20px;">
                    <table class="u-table u-table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 30%">类型</th>
                                <th style="width: 30%">数量</th>
                                <th style="width: 30%">占比</th>
                                <th style="width: 10%">操作</th>
                            </tr>
                        </thead>
                        <tbody id="okTbody"></tbody>
                    </table>
                </div>

                <div id="grouped" style="display: none">
                    <div class="qualityecharts1">
                        <span class="position-relative" style="top: 24px; left: 2px">分组统计 </span> <label class="u-checkbox u-float-r mt-20 pr-24"
                            style="z-index: 10;"> <input type="checkbox" name="echarts5" id="shownum5" checked> <span class="icon-checkbox"></span>
                            显示统计数据
                        </label>
                        <div id="groupPie" style="width: 100%; height: 100%; top: -28px;"></div>
                    </div>
                    <div class="ml-12 mr-12 mb-10">
                        <table class="u-table u-table-bordered">
                            <thead>
                                <tr>
                                    <th style="width: 30%">分组</th>
                                    <th style="width: 30%">数量</th>
                                    <th style="width: 30%">占比</th>
                                    <th style="width: 10%">操作</th>
                                </tr>
                            </thead>
                            <tbody id="groupTbody"></tbody>
                        </table>
                    </div>
                </div>

                <div class="qualityecharts1" id="noGroup" style="display: none">
                    <span class="position-relative" style="top: 24px; left: 2px"> 分组统计 </span>
                    <div style="top: -18px; height: 260px;" class="empty-echarts"></div>
                </div>

                <div class="ml-12 mt-20" id="patientDiv">患者名单详情</div>
                <div class="ml-12 mr-14 mb-8 mt-10">
                    <table class="u-table u-table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 30%">姓名</th>
                                <th style="width: 70%">化验值</th>
                            </tr>
                        </thead>
                        <tbody id="itemTbody"></tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>

    <script type="text/javascript" src="${COMMON_SERVER_ADDR}/framework/echarts/3.2.3/echarts_3.2.3_min.js"></script>
    <script src="${ctx }/assets/js/report/assay_report.js?version=${version}"></script>
    <script type="text/javascript">
          $(function() {
              $("#dictionaryTopTab").css("margin-left", "-" + ($("#dictionaryTopTab").width() / 2) + "px");
              $(".u-pop-up-from").append($("#selectForm").andSelf());

              setReportDatePick($("#reportDateDiv"), {
                  dateType : 'nothing',
                  defaultValue : new Date().getFullYear(),
                  dateFormat : 'yyyy',
                  formId : 'selectForm',
                  customForm : true,
                  callback : function(d) {
                      getReportByCondition();
                  }
              });
          });
      </script>
</body>
</html>
