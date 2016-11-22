<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<title>化验检查信息</title>
</head>
<body>
	<jsp:include page="../common/report_datepick.jsp" flush="true"></jsp:include>
	<div class="container-fluid">
		<div class="row">
			<div class="fill-parent">
				<form action="#" onsubmit="return false;" id="assayResultForm">
					<input type="hidden" value="${assayResult.id }" name="id" /> 
					<input type="hidden" value="${patient.id }" name="fkPatientId" /> 
					<input type="hidden" value="12" name="downType" /> 
					<span class="text-bold form-span margin-left-5">传染病标识：</span>
					<div class="inline-block margin-right-10" id="diseaseDiv">
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="normal" type="checkbox" name="normal" <c:if test="${assayResult.normal }">checked</c:if> />正常</label> 
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="hav" type="checkbox" name="hav" <c:if test="${assayResult.hav }">checked</c:if> />甲肝</label> 
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="hbv" type="checkbox" name="hbv" <c:if test="${assayResult.hbv }">checked</c:if> />乙肝</label> 
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="hcv" type="checkbox" name="hcv" <c:if test="${assayResult.hcv }">checked</c:if> />丙肝</label> 
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="hev" type="checkbox" name="hev" <c:if test="${assayResult.hev }">checked</c:if> />戊肝</label> 
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="hiv" type="checkbox" name="hiv" <c:if test="${assayResult.hiv }">checked</c:if> />HIV</label> 
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="hsv" type="checkbox" name="hsv" <c:if test="${assayResult.hsv }">checked</c:if> />梅毒</label> 
						<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><input id="unknown" type="checkbox" name="unknown" <c:if test="${assayResult.unknown }">checked</c:if> />未知</label>
					</div>
					<c:if test="${!patient.delFlag}">
						<div class="form-item-box" style="min-width: 110px !important;">
							<button class="btn btn-def" onclick="saveAssayResult(this);">保存</button>
							<label class="form-span form-checkbox-label" style="min-width: 70px !important;"><span id="showError"></span></label>
						</div>
					</c:if>
					<button class="btn btn-def" onclick="refrshAssayResult(this);">同步化验数据</button>
				</form>
			</div>
			<input type="hidden" value="${patient.id }" id="patientId" />

			<div class="fill-parent margin-top-6" id="assayRecordBody">
				<div class="col-sm-3 col-md-2" style="padding-left: 0px; padding-right: 0px;">
					<div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: scroll; min-height: 76px;" id="assayTimesTable">
						<table class="table table-border table-align-left-15">
							<thead>
								<th>检查时间</th>
							</thead>
							<tbody id="assayTimesRecord">
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-9 col-md-10" style="padding-left: 0px; padding-right: 0px;">
					<div class="fill-parent">
						<span class="tab-tip text-bold margin-top-10" style="display: inline-block;">化验单：&nbsp;&nbsp;</span>
						<div class="simple-line simple-line2 margin-bottom-10" id="assayCategory"></div>
					</div>
					<div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: scroll;" id="assayRecordTable">
						<table class="table table-border table-align-left-10">
							<thead>
								<th>项目代号</th>
								<th>项目名称</th>
								<th>检查结果</th>
								<th>提示</th>
								<th>参考值</th>
								<th>单位</th>
							</thead>
							<tbody id="assayRecord">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- 历史数据 dialog -->
			<div class="modal" id="assayHistoryDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="assayHistoryTitle">历史数据</h4>
							<div class="dialog-close pull-right m-t--33" data-dismiss="modal">
								<img src="${ctx }/assets/img/dialog-close.png">
							</div>
						</div>
						<div class="modal-body">
							<div class="dialog-body" style="padding: 0px;">
								<div class="tab-header no-border margin-top-5">
									<div class="pull-right margin-bottom-10" id="reportDateDiv"></div>
								</div>
								<div id="assayHistoryChart" style="width: 600px; height: 300px;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="${ctx }/framework/echarts/2.2.7/echarts-simple.js"></script>
	<script src="${ctx }/assets/js/assay/patient_assay_chart.js?version=${version}"></script>
	<script src="${ctx }/assets/js/assay/patient_assay_record.js?version=${version}"></script>
</body>
</html>