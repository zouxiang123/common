<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" type="text/css" href="${COMMON_SERVER_ADDR}/assets/css/global.css?version=${version }"/>
<link rel="stylesheet" type="text/css" href="${COMMON_SERVER_ADDR}/assets/css/index.css?version=${version }"/>
<link rel="stylesheet" href="${COMMON_SERVER_ADDR}/assets/css/single.css?version=${version }"/>
<title>化验项统计</title>
</head>
<body style="padding-top:0px;" class="bg-white">
	<jsp:include page="../common/report_datepick_new.jsp" flush="true"></jsp:include>
	<!-- 代表的是PC登陆 -->
	<div class="container-fluid">
      <div class="row">
      <div class="col-sm-12 col-md-12 bg-white main"  style="padding: <c:if test="${page_device_suf}">1</c:if>0px 0px 0px 0px;">
        	<div class="fill-parent" style="margin-top: -4px;">
				<div class="list-item border-top-line margin-top-8">
					<div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;" id="reportDateDiv">
						<span class="tip-line" style="background: #31AAFF;"></span>
						<span class="tab-title">常用项：</span>
						<button type="button" class="pull-right m-l-10 open-show">报表设置</button>
					</div>
					<div class="tab-body">
						<div style="padding-left: 20px;padding-right: 20px;padding-top: 5px;padding-bottom: 5px;" id="assayTopDiv">
						</div>
					</div>
				</div>
				<div class="u-pop-up-from"></div>
        	  	<form id="selectForm" action="#" style="display: none;">
					<div class="list-item">
						<div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
							<span class="tip-line" style="background: #31AAFF;"></span>
							<span class="tab-title">查询条件：</span>
						</div>
						<div class="tab-body">
						   <span class="margin-left-20">选中年份：</span><input type="text" class="text-center margin-left-20 width-150 form-control" style="background-color: #fff;display: inline-block;height: 26px;" name="assayYear" id="reportDate" readonly="readonly" datetimepicker/>
							<div style="display: none;"><select class="selectpicker-small margin-left-20" style="margin-right: 0px;" name="dateType" id="dateType">
								<option value="m">按月分组</option>
								<option value="s">按季度分组</option>
							</select></div>
							<span class="margin-left-20">检查类别：</span><select class="selectpicker-big margin-left-20" style="margin-right: 0px;" name="groupId" id="groupId" onchange="changeGroupId()"></select>
							<span class="margin-left-20">检查项列表：</span> <select class="selectpicker-big margin-left-20" style="margin-right: 0px;" name="itemCode" id="itemCode" onchange="changeItemCode()"></select>
						</div>
						<!-- <div class="tab-body" style="margin-top: 10px;">
							<span class="margin-left-20" style="min-width: 80px;">标签管理：</span>
							<select class="selectpicker-big margin-left-20" id="patientLabelId" name="patientLabelId"></select>
						</div> -->
					</div>
					<div class="list-item border-top-line margin-top-8">
						<div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
							<span class="tip-line" style="background: #31AAFF;"></span>
							<span class="tab-title">检查项规则：</span>
						</div>
						<div class="tab-body">
							<span class="margin-left-20" style="min-width: 80px;">达标范围：</span><input type="text" class="text-center margin-left-20 margin-right-20 width-150" name="minValue" id="minValue"/>~<input type="text" class="text-center margin-left-20 width-150" name="maxValue" id="maxValue"/>
							&nbsp;&nbsp;<span style="display:none ; ;color: red"  id="errorShowInfor">请输入达标范围</span>
						</div>
						<div class="tab-body" style="margin-top: 10px;">
							<span class="margin-left-20" style="min-width: 80px;">分组规则：</span>
							<div style="margin-left: 20px; display: inline-block;" id="ruleListDiv"></div>

							<button type="button" class="btn btn-default width-76" id="addRuleBtn" onclick="addRuleBtnEvent();">添加</button>
						</div>
					</div>
              	</form>
            </div>
            
            <div class="simple-line simple-line-text border-top-line margin-top-8 margin-bottom-20" id="dateTitle">
                <div class="simple-line-container margin-top-18 no-border-all active" id="yearTitle">
                    <span class="tabbar">年度报表<span></span></span>
                </div>
                <div class="simple-line-container margin-top-18 no-border-all" style="display: none;">
                    <span class="tabbar"></span>
                </div>
            </div>
            
            <div id="contentDiv">
	            <div>
		            <div class="fill-parent margin-top-8">
		                <div class="col-sm-6 col-md-6 clear-padding-margin">
		                    <div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
		                        <span class="tip-line" style="background: #31AAFF;"></span>
		                        <span class="tab-title">达标统计：</span>
		                    </div>
		                    <div class="tab-content bg-white">
		                        <div class="padding-15" id="dbbarChart" style="height: 250px;"></div>
		                    </div>
		                </div>
		
		                <div class="col-sm-6 col-md-6 clear-padding-margin">
		                	<div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
		                        <span class="tip-line" style="background: #31AAFF;"></span>
		                        <span class="tab-title">中位数统计：</span>
		                    </div>
		                    <div class="tab-content bg-white">
		                        <div class="padding-15" id="zwsLineChart" style="height: 250px;"></div>
		                    </div>
		                </div>
		                
		                <div class="col-sm-6 col-md-6 clear-padding-margin border-top-line">
		                     <div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
		                        <span class="tip-line" style="background: #31AAFF;"></span>
		                        <span class="tab-title">达标率统计：</span>
		                    </div>
		                    <div class="tab-content bg-white">
		                        <div class="padding-15" id="chart2" style="height: 250px;"></div>
		                    </div>
		                </div>
		            </div>
	            
		            <div class="col-sm-12 col-md-12 border-top-line margin-top-8" style="padding-left: 0 !important;padding-right: 0 !important;">
		                <div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
		                    <span class="tip-line" style="background: #31AAFF;"></span>
		                    <span class="tab-title">化验项列表：</span>
		
		                    <button type="button" class="btn quick-btn pull-right" id="downloadYear">下载</button>
		                </div>
		
		                <div class="tab-body">
		                    <div class="table-responsive bg-white margin-top-10 margin-bottom-10">
		                        <table class="table table-border table-align-left-15">
		                            <thead id="mainThead">
			                            
		                            </thead>
		                            <tbody id="mainTbody">
		                            
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		            </div>
	            </div>
            
	             <div style="display: none;">
	             	<div class="fill-parent border-top-line margin-top-8">
	             		<div class="col-sm-12 col-md-12 clear-padding-margin bg-white">
		                    <div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
		                        <span class="tip-line" style="background: #31AAFF;"></span>
		                        <span class="tab-title">达标统计：</span>
		                    </div>
		                    <div class="tab-body" id="brandStatistics">
								<div class="col-sm-6 col-md-6 clear-padding-margin">
									<div class="table-responsive bg-white margin-top-10 margin-bottom-10">
				                        <table class="table table-border table-align-left-15">
				                            <thead id="mainThead">
					                            
				                            </thead>
				                            <tbody id="mainTbody">
				                            
				                            </tbody>
				                        </table>
				                    </div>
									<div class="table-responsive table-default">
										<table class="table table-border" style="margin-bottom: 0px;">
											<thead>
												<tr>
													<th>类型</th>
													<th>数量</th>
													<th>占比</th>
												</tr>
											</thead>
											<tbody id="okTbody"></tbody>
										</table>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 clear-padding-margin">
									 <div class="padding-15" id="monthSeasonOkPie" style="height: 225px;"></div>
								</div>
							</div>
						</div>
						<div class="col-sm-12 col-md-12 clear-padding-margin bg-white">
							<div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
		                        <span class="tip-line" style="background: #31AAFF;"></span>
		                        <span class="tab-title">分组统计：</span>
		                    </div>
		                    <div class="tab-body" id="brandStatistics">
								<div class="col-sm-6 col-md-6 clear-padding-margin">
									<div class="table-responsive table-default">
										<table class="table table-border" style="margin-bottom: 0px;">
											<thead>
												<tr>
													<th>组</th>
													<th>数量</th>
													<th>占比</th>
												</tr>
											</thead>
											<tbody id="groupTbody"></tbody>
										</table>
									</div>
								</div>
								<div class="col-sm-6 col-md-6 clear-padding-margin">
									 <div class="padding-15" id="groupPie" style="height: 225px;"></div>
								</div>
							</div>
						</div>
		            </div>
		            
	             	<div class="col-sm-12 col-md-12 border-top-line margin-top-8" style="padding-left: 0 !important;padding-right: 0 !important;">
		                <div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
		                    <span class="tip-line" style="background: #31AAFF;"></span>
		                    <span class="tab-title">名单列表：</span>
		
		                    <button type="button" class="btn quick-btn pull-right" id="downloadDetail">下载</button>
		                </div>
		
		                <div class="tab-body" id="patientDiv">
		                    <div class="table-responsive bg-white margin-top-10 margin-bottom-10">
		                        <table class="table table-border table-align-left-15">
		                            <thead>
			                            <tr>
											<th>患者名字</th>
											<th>化验值</th>
											<!-- <th>是否达标</th> -->
			                            </tr>
		                            </thead>
		                            <tbody id="itemTbody">
		                            
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		            </div>
	             </div>
            </div>
      </div><!-- main -->
    </div>
  </div>
    <script type="text/javascript" src="${ctx}/framework/echarts/2.2.7/echarts-simple.js"></script>
	<script src="${ctx }/assets/js/report/assay_report.js?version=${version}"></script>
	<script type="text/javascript">
	 	$(function(){
	 		$("#dictionaryMaintainNavId").addClass("active");
			
			$("#dictionaryTopTab").css("margin-left", "-"+($("#dictionaryTopTab").width()/2) + "px");
			$(".u-pop-up-from").append($("#selectForm").andSelf());

			setReportDatePick($("#reportDateDiv"), {
				dateType:  'nothing',
				defaultValue : new Date().getFullYear(),
				dateFormat: 'yyyy',
				formId: 'selectForm',
				customForm : true,
				callback : function(d) {
					getReportByCondition();
				}
			});
	 	});
	</script>
</body>
</html>
