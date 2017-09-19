<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head${page_device_suf }.jsp"></jsp:include>
<title>化验检查信息</title>
</head>
<style>
   .table-bordered>thead>tr>td, .table-bordered>thead>tr>th{
      border-bottom-width:0px !important;
   }
</style>
<body>
<input type="hidden" value="${tenantId}" id="tenantId">
<input type="hidden" value="${patient.id }" id="patientId" />
<input type="hidden" value="${patient.delFlag }" id="delFlag" />
<input type="hidden" value=""  id="itemCode" />
<input type="hidden" value=""  id="name" />
<input type="hidden" value=""  id="unit" />
<div class="col-sm-12 col-md-12 main" style="padding: 10px 20px 0px 20px;">
    <jsp:include page="../common/report_datepick.jsp" flush="true"></jsp:include>
	<div class="p-10 clearfix">
	    <span class="float-left m-t-10" style="font-weight: bold">化验信息</span>
	    <c:if test="${!patient.delFlag}">
	        <button class="button-gray float-right m-l-10" onclick="showAddDialog('addPatientAssay');">添加数据</button>
	        <button class="button-gray float-right m-l-10" onclick= "refrshAssayResult(this);">同步化验</button>
	    </c:if>
	    <button class="button-gray float-right m-l-10" onclick="searchDialogShow()">查询设置</button>
	    <c:if test="${!patient.delFlag}">
	        <button class="button-gray float-right m-l-10" onclick="showAssayResultDialog();">传染病标识</button>
	        <button class="button-gray float-right" style="display:none;" id="editAssayRecord" >编辑</button>
	    </c:if>
	      <!--查询框-->
	    <div class="u-tip" style="top: 50px;right: 200px; min-width: 330px;     display: none" id="searchDialog"><!--这个style里面的top和right是可以调的，使用时可能会有些不准-->
	        <img src="${ctx }/assets/img/s1110.png" alt="" style="position: absolute;top: -20px;right: 30px;"><!--s1110.png在eclipse上是有的-->
	        <form action="#" onsubmit="return searchSubmit(this);" id="searchForm">
	        <div class="m-l-30 m-t-30">
	            <span>时间：</span>
	            <c:if test="${labTimeFlag eq '1'}">
	            <label class="u-radio">
	                <input type="radio" name="timeType" id="sampleTime" checked="checked" value="sampleTime">
	                采集时间
	            </label>
	            </c:if>
	             <c:if test="${labTimeFlag eq  '2'}">
	            <label class="u-radio">
	                <input type="radio" name="timeType" id="reportTime" checked="checked" value="reportTime">
	                报告时间
	            </label>
	            </c:if>
	             <c:if test="${labTimeFlag eq '3'}">
	            <label class="u-radio">
	                <input type="radio" name="timeType" id="sampleTime" checked="checked" value="sampleTime">
	                采集时间
	            </label>
	            <label class="u-radio">
	                <input type="radio" name="timeType" id="reportTime" value="reportTime">
	                报告时间
	            </label>
	            </c:if>
	        </div>
	
	        <div class="m-t-16" style="margin-left: 81px">
	            <input type="text" style="width: 100px" class="border-gray p-t-5 p-b-5 m-r-8 m-l-8 text-center"  id="startDate">
	            <span>至</span>
	            <input type="text" style="width: 100px" class="border-gray p-t-5 p-b-5 m-l-8 text-center"  id="endtDate">
	        </div>
	
	        <div class="right m-t-20 m-b-14 p-t-14 border-top-line">
	            <button type="button" class="btn btn-can dialog-buttontype" data-dismiss="modal" onclick="searchDialogHidden()">取消</button>
	            <button id="finish" type="button" onclick="buttonSubmit(this);" class="btn btn-def dialog-buttontype m-r-24">确定</button>
	        </div>
	        </form>
	    </div>
	    <!--这里是提示框-->
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
	                    <th style="width: 25%">项目名称</th>
	                    <th style="width: 15%">检查结果</th>
	                    <th style="width: 5%">提示</th>
	                    <th style="width: 20%">参考值</th>
	                    <th style="width: 10%">样本</th>
	                    <th style="width: 10%">是否透后血</th>
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
<jsp:include page="../common/daterangepicker_include.jsp"></jsp:include>
<!-- 检验结果弹全屏幕 -->
<jsp:include page="patient_assay_record_history_dialog.jsp"></jsp:include>
<!-- 添加化验信息 -->
<jsp:include page="patient_assay_record_add_dialog.jsp"></jsp:include>
<!-- 传染病标识弹窗 -->
<jsp:include page="patient_assay_record_assay_result_dialog.jsp"></jsp:include>
<!-- 手动编辑透前透后标识 -->
<jsp:include page="patient_assay_record_dia_ab_flag_dailog.jsp"></jsp:include>

    <script src="${COMMON_SERVER_ADDR}/framework/echarts/3.2.3/echarts_3.2.3_min.js"></script>
    <script type="text/javascript" src="${ctx }/assets/js/common/pagination.js?version=${version}"></script>
    <script src="${ctx }/assets/js/assay/patient_assay_report.js?version=${version}"></script>
    <script type="text/javascript" src="${ctx }/assets/js/assay/patient_assay_record.js"></script>
<script>
    //页面自适应最大高度事件
    //MaxAdaptive（id,nub）,id：是需要高度的元素，nub是需要减去的高度和目标元素的id
    function MaxAdaptive(name, nub) {
        if (isNaN(nub)) {
            $(name).css({"max-height": $(window).height() - $(nub).height()});
        } else {
            $(name).css({"max-height": $(window).height() - parseInt(nub)});
        }
    }
</script>
<script>
    MaxAdaptive("#huayanTable", 140);//这个可以调试 数字可改
    MaxAdaptive("#assaycardScroll", 110);
</script>
</body>
</html>