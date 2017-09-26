<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="fill-parent rel bg-white border-bottom-line" style="height: 48px;">
    <div class="tab-index" style="width: auto;" id="dictionaryTopTab">
        <span data-type="mapping" class="tab-index-item" onclick="window.location.href='${ctx}/assay/assayMapping/view.shtml'">化验项映射</span> 
        <span data-type="hosp_dict" class="tab-index-item-center" onclick="window.location.href='${ctx}/assay/hospDict/manualView.shtml'">化验单模板</span> 
        <span data-type="group_rule" class="tab-index-item-center" onclick="window.location.href='${ctx}/assay/patientAssayGroupRule/assayCheckGrouping.shtml'">化验检查分组</span>
        <span data-type="remind_dict" class="tab-index-item-center" onclick="window.location.href='${ctx}/assay/assayRemindDict/view.shtml'">化验项目提醒字典</span>
        <span data-type="filter_rule" class="tab-index-item-center" onclick="window.location.href='${ctx}/assay/assayFilterRule/view.shtml'">化验清洗规则配置</span> 
    </div>
</div>
<script type="text/javascript">
	function setAssayTopActive(type){
	    $("#dictionaryTopTab").find("[data-type='"+type+"']").addClass("active");
	}
</script>
