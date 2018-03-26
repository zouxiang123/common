<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<title>人体测量</title>
</head>
<body>
<div class="frame-content">
<input id="patientId" value="${patient.id }">
<input id="patientName" value="${patient.name }">
    <div class="u-list mt-10">
        <div class="u-xt-6" id="nuTypeDiv">
            <button type="button" data-type="body_measure">人体测量</button>
            <button type="button" data-type="assessment">营养评估</button>
            <button type="button" data-type="food_record">饮食记录</button>
        </div>
        <div class="u-xt-6 u-text-r" id="nuQueryDiv">
            <label class="nutrition-action hide" date-target="body_measure">
                <button type="button" class="u-btn-blue" data-popup="#promptDialog4">新增</button>
                <button type="button" data-show="#querySetId1" class="u-btn">查询设置</button>
                <div class="u-prompt-box" id="querySetId1" top-right style="top: 44px;right:0px;">
                    <div class="query-set">
                        <div class="u-list">时间：
                            <input type="text" name="startDateStr" readonly="readonly" datepicker>
                            <span class="fc-black_5"> 至 </span>
                            <input type="text" name="endDateStr" readonly="readonly" datepicker>
                        </div>
                        <div class="u-border-t u-text-r pt-8 mt-8">
                            <button type="button" class="u-btn" data-hide="#querySetId1">取消</button>
                            <button type="button" class="u-btn-blue" fill>查询</button>
                        </div>
                    </div>
                </div>
            </label>
            <label class="nutrition-action hide" date-target="assessment">
                <button type="button" class="u-btn-blue" data-popup="#promptDialog">新增</button>
                <button type="button" data-show="#querySetId2" class="u-btn">查询设置</button>
                <div class="u-prompt-box" id="querySetId2" top-right style="top: 44px;right:0px;">
                    <div class="query-set">
                        <div class="u-list">时间：
                            <input type="text" name="startDateStr" readonly="readonly" datepicker>
                            <span class="fc-black_5"> 至 </span>
                            <input type="text" name="endDateStr" readonly="readonly" datepicker>
                        </div>
                        <div class="u-border-t u-text-r pt-8 mt-8">
                            <button type="button" class="u-btn" data-hide="#querySetId2">取消</button>
                            <button type="button" class="u-btn-blue" fill>查询</button>
                        </div>
                    </div>
                </div>
            </label>
            <label class="nutrition-action hide" date-target="food_record">
                <button type="button" class="u-btn-blue" data-popup="#promptDialog2">新增</button>
                <button type="button" data-show="#querySetId3" class="u-btn">查询设置</button>
                <div class="u-prompt-box" id="querySetId3" top-right style="top: 44px;right:0px;">
                    <div class="query-set">
                        <div class="u-list">时间：
                            <input type="text" name="startDateStr" readonly="readonly" datepicker>
                            <span class="fc-black_5"> 至 </span>
                            <input type="text" name="endDateStr" readonly="readonly" datepicker>
                        </div>
                        <div class="u-border-t u-text-r pt-8 mt-8">
                            <button type="button" class="u-btn" data-hide="#querySetId3">取消</button>
                            <button type="button" class="u-btn-blue" fill>查询</button>
                        </div>
                    </div>
                </div>
            </label>
        </div>
    </div>
    <div class="nutrition-assessment nutrition hide" id="body_measure_div">
        <div class="u-table-fixed mt-10">
            <div class="u-table-fixed-head">
                <table class="u-table u-table-bordered">
                    <thead>
                    <tr>
                        <th class="xtt-12">时间</th>
                        <th class="xtt-8">身高(cm)</th>
                        <th class="xtt-8">体重(kg)</th>
                        <th class="xtt-8">BMI</th>
                        <th class="xtt-8">BSA</th>
                        <th class="xtt-10">上臀围(cm)</th>
                        <th class="xtt-20">三头肌皮褶厚度(mm)</th>
                        <th class="xtt-13">上臀肌围(cm)</th>
                        <th class="xtt-10">腰臀比</th>
                        <th class="">来源</th>
                        <th class="xtt-10">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="u-table-fixed-body" id="body_measure_bodyDiv">
                <table class="u-table u-table-bordered">
                    <tbody id="body_measure_tbody"></tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="somatometry nutrition hide" id="assessment_div">
        <div id="chart2" style="height: 250px"></div>
        <table class="u-table u-table-bordered mt-20">
            <thead>
            <tr>
                <th class="xtt-12">时间</th>
                <th class="xtt-10">SGA</th>
                <th class="">说明</th>
                <th class="xtt-10">操作</th>
            </tr>
            </thead>
            <tbody id="assessment_tbody"></tbody>
        </table>
    </div>
    <div class="foodrecord nutrition" id="food_record_div">
        <div id="food_record_chart" style="height: 250px"></div>
        <table class="u-table u-table-bordered mt-20">
            <thead>
                <tr>
                    <th style="width: 10%">时间</th>
                    <th style="width: 20%">实际DPI(g)</th>
                    <th style="width: 20%">实际DEI(kal)</th>
                    <th style="width: 20%">推荐DPI(g)</th>
                    <th style="width: 20%">推荐DEI(kal)</th>
                    <th style="width: 10%">操作</th>
                </tr>
            </thead>
            <tbody id="food_record_tbody"></tbody>
        </table>
    </div>
</div>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/nu_list.js"></script>
<script type="text/javascript">
 nu_list.init();
</script>
</body>
</html>