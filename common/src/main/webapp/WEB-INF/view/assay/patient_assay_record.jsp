<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head_standard.jsp"></jsp:include>
<title>化验检查信息</title>
</head>
<body class="fc-48">
<input type="hidden" value="${tenantId}" id="tenantId">
<input type="hidden" value="${patient.id }" id="patientId" />
<div class="fc-48">
    <div class="m-12 pb-10 position-relative">
        <span class="fw-bold">化验信息</span>
        <c:if test="${!patient.delFlag}">
            <button class="u-float-r ml-10" onclick="showAddDialog();">添加数据</button>
            <button class="u-float-r ml-10" onclick= "refreshAssayResult(this);">同步化验</button>
        </c:if>
        <button class="u-float-r ml-10" data-show="#searchDialog">查询设置</button>
        <c:if test="${!patient.delFlag}">
            <button class="u-float-r ml-10" onclick="showAssayResultDialog(${patient.id });">传染病标识</button>
            <button class="u-float-r ml-10" style="display:none;" id="editAssayRecord" >编辑</button>
        </c:if>
        <!--查询设置弹框-->
        <div class="u-prompt-box u-display-none p-0" top-right style="right: 206px;top: 41px;" id="searchDialog">
            <form id="searchForm" onsubmit="return false">
                <div class="u-list-text mr-10 mt-10">
                    <span class="ml-30">时间：</span>
                    <c:if test="${labTimeFlag eq '1' or labTimeFlag eq '3'}">
                        <label class="u-radio">
                            <input type="radio" name="dateType" id="sampleTime" checked="checked" value="sampleTime">
                            <span class="icon-radio"></span>采集时间
                        </label>
                    </c:if>
                    <c:if test="${labTimeFlag eq  '2' or labTimeFlag eq '3'}">
                        <label class="u-radio">
                            <input type="radio" name="dateType" id="reportTime" checked="checked" value="reportTime">
                            <span class="icon-radio"></span>报告时间
                        </label>
                    </c:if>
                </div>
                <div class="u-list-text pl-72 mr-12 bb-line pb-10 mr-10">
                    <input type="text" style="width: 110px" name="startDateStr" id="startDate" readonly="readonly">
                    <span class="ml-6 mr-6">至</span>
                    <input type="text" style="width: 110px" name="endDateStr" id="endtDate" readonly="readonly">
                </div>
            </form>
            <div class="u-float-r mt-10 mb-10 mr-10">
                <button type="button" data-hide="#searchDialog">取消</button>
                <button type="button" class="u-btn-blue" onclick="searchSubmit();" fill>确定</button>
            </div>
        </div>
        <!--查询设置弹框-->
    </div>
	<div class="huayan-displayCard m-l-10" id="assaycardScroll" >
	   <div id="assaycard">
	   </div>
	</div>
	<div class="huayan-righttable" style="float: right;">
	    <div class="u-table-fixed">
	        <div class="u-table-fixed-head u-clear-scroll">
	            <table class="u-table u-table-bordered">
	                <thead>
    	                <tr>
                            <th style="width: 15%">项目代号</th>
                            <th style="width: 35%">项目名称</th>
                            <th style="width: 13%">检查结果</th>
                            <th style="width: 8%">提示</th>
                            <th style="width: 20%">参考值</th>
                            <th style="width: 9%">样本</th>
                            <th class="xtt-10">是否透后血</th>
    	                </tr>
	                </thead>
	            </table>
	        </div>
	        <div class="u-table-fixed-body u-clear-scroll" id="huayanTable">
	            <table class="u-table u-table-bordered">
	                <tbody id="assayRecord">
	               
	                </tbody>
	            </table>
	        </div>
	    </div>
	</div>
</div>
<!-- 检验结果弹全屏幕 -->
<jsp:include page="assay_hist_report_dialog.jsp"></jsp:include>
<!-- 添加化验信息 -->
<jsp:include page="patient_assay_record_add_dialog.jsp"></jsp:include>
<!-- 传染病标识弹窗 -->
<jsp:include page="patient_assay_result_dialog.jsp"></jsp:include>
<!-- 手动编辑透前透后标识 -->
<jsp:include page="patient_assay_record_dia_ab_flag_dailog.jsp"></jsp:include>

<script type="text/javascript" src="${ctx }/assets/js/common/pagination.js?version=${version}"></script>
<script type="text/javascript" src="${ctx }/assets/js/assay/patient_assay_record.js?version=${version}"></script>
<script>
    var patientHasOutCome = ${patient.delFlag };
    $("#assaycardScroll").css({"max-height": $(window).height() - $("#assaycard").offset().top - 10});
    $("#huayanTable").css({"max-height": $(window).height() - $("#assaycard").offset().top - 50});
</script>
</body>
</html>