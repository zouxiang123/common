<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="assayHistReportDialog" data-hide="#assayHistReportDialog">
    <div class="u-dialog" fullScreen>
        <div class="u-dialog-header position-relative">
            <div><i class="icon-close ml-12" data-hide="#assayHistReportDialog"></i></div>
            <div class="fs-18" id="assayHistDialogTitle">历史数据</div>
            <div>
                <button type="button" data-show="#assayHistReportSearch">数据设置</button>
                <!--数据设置弹框-->
                <div class="u-prompt-box u-display-none p-0" top-right style="right: 20px;top: 47px;" id="assayHistReportSearch">
                    <div class="u-list-text mr-10 mt-10 pl-20">
                        <label class="u-radio">
                            <input type="radio" name="time" value="w">
                            <span class="icon-radio"></span>最近一周
                        </label>
                        <label class="u-radio">
                            <input type="radio" name="time" value="m">
                            <span class="icon-radio"></span>最近一月
                        </label>
                        <label class="u-radio">
                            <input type="radio" name="time" value="y">
                            <span class="icon-radio"></span>最近一年
                        </label>
                    </div>
                    <div class="u-list-text pl-20 mr-12 bb-line pb-10 mt-10">
                        <input type="text" style="width: 110px" name="startDate" readonly="readonly">
                        <span class="ml-6 mr-6">至</span>
                        <input type="text" style="width: 110px" name="endDate" readonly="readonly">
                    </div>
                    <div class="u-float-r mt-10 mb-10 mr-10">
                        <button type="button" data-hide="#assayHistReportSearch">取消</button>
                        <button type="button" class="u-btn-blue" onclick="assay_hist_report.search();" fill>确定</button>
                    </div>
                </div>
                <!--数据设置弹框-->
            </div>
        </div>
        <div class="u-dialog-content">
            <label class="u-checkbox  u-float-r position-relative" style="top: 30px; z-index: 10;margin-right: 90px;">
                <input type="checkbox" id="assayHistChartShownum" checked>
                <span class="icon-checkbox"></span>显示数据
            </label>
            <div id="assayHistReportChart" style="height: 250px;right: 60px;margin-top: -20px"></div>
            <table class="u-table u-table-bordered mt-20">
                <thead>
                    <tr>
                        <th style="width: 50%">检查时间</th>
                        <th style="width: 50%">检查结果</th>
                    </tr>
                </thead>
                <tbody id ="assayHistTable">
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="${COMMON_SERVER_ADDR}/framework/echarts/3.2.3/echarts_3.2.3_min.js"></script>
<script src="${ctx }/assets/js/assay/assay_hist_report_dialog.js?version=${version}"></script>
