<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<%@ include file="../common/head_theme.jsp"%>
<title>转归统计</title>
</head>
<body style="overflow-x:hidden">
    <div class="xtt" style="padding:10px 0px 0px 0px;">
        <input type="hidden" id="year" value="${year}"> 
        <input type="hidden" id="month" value="${month}">
        <input type="hidden" id="type" value="${type}"> 
        <input type="hidden" id="patientTempValue" value="${patientTempValue}"> 
        <div>
            <div class="ml-12 mr-14 hide" id="tableDiv"></div>
            <div>
                <div style="display: none;" id="monthChartDiv">
                    <div class="echartshalf">
                        <label class="u-checkbox echartsave" style= "right:50px;top:12px"> <input
                            type="checkbox" name="che" id="shownum2" checked> <span class="icon-checkbox"></span> 显示数据
                        </label>
                        <div id="chart2" class="u-clearfix w-100 u-display-block" style="height:260px;"></div>
                    </div>
                    <div class="ml-12 mr-12 mt-8" style="overflow: auto;">
                        <table class="u-table u-table-bordered">
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
                    <div class="patientlist pb-10 ml-2">
                        <span>患者列表</span>
                    </div>
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
    </div>

    <script src="${ctx }/assets/js/report/outcome_detail_report.js?version=${version}"></script>
    <script>
        $(function() {
            $(".sidebar li#outcomeNav").addClass("active").siblings().removeClass("active");
        });
        
        //动态规定表格的高度
        MaxAdaptive('#main', 40);
    </script>
</body>
</html>
