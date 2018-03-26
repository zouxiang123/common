<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<link rel="stylesheet" href="${ctx}/assets/css/styles.css?version=${version }">
<%@ include file="../common/head_new.jsp"%>
<link href="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.css" rel="stylesheet" >
<link href="${ctx }/framework/autocomplete/jquery.autocomplete.css" rel="stylesheet" >
<script src="${ctx }/framework/jquery/jquery.fastLiveFilter.js"></script>
</head>
<body class="bg-white">
<div class="container-fluid">
    <div class="row">
        <div id="main-home-page" class="col-sm-12 col-md-12 main" style="padding:0px 15px 0px 15px !important" data-iframe-css="main">
            <div class="u-tabs">
                <span class="u-tab">全部工具</span>
                <span class="u-tab active" onclick="findRecycleBinList(this,1)">食谱查询<span class="u-tab-delete"></span></span>
                <span class="u-tab" onclick="findRecycleBinList(this,2)">营养素查询<span class="u-tab-delete"></span></span>
                <span class="u-tab" onclick="findRecycleBinList(this,3)">相似食物查询<span class="u-tab-delete"></span></span>
                <span class="u-tab" onclick="findRecycleBinList(this,4)">饮食回顾<span class="u-tab-delete"></span></span>
                <span class="u-tab" onclick="findRecycleBinList(this,5)">膳食指南<span class="u-tab-delete"></span></span>
                <span class="u-tab" onclick="findRecycleBinList(this,6)">ckd营养评估模板<span class="u-tab-delete"></span></span>
            </div>
        </div>
        <div>
       		<div id="foodQuery">
       			<jsp:include page="food_query.jsp" flush="true"></jsp:include>
				<%-- <%@ include file="food_query.jsp"%> --%>
			</div>
			<div id="nutrimentQuery" style="display: none;">
				<jsp:include page="nutriment_query.jsp" flush="true"></jsp:include>
				<%-- <%@ include file="nutriment_query.jsp" %> --%>
			</div>
			<div id="similarityQuery" style="display: none;">
				<jsp:include page="similarity_query.jsp" flush="true"></jsp:include>
				<%-- <%@ include file="similarity_query.jsp" %> --%>
			</div>
			<div id="dietaryGuidelines" style="display: none;">
				<jsp:include page="dietary_guidelines.jsp" flush="true"></jsp:include>
				<%-- <%@ include file="dietary_guidelines.jsp" %> --%>
			</div>
			<div id="nutritionAssess" style="display: none;">
				<jsp:include page="nutrition_assess.jsp" flush="true"></jsp:include>
				<%-- <%@ include file="nutrition_assess.jsp" %> --%>
			</div>
			<div id="foodRecord" style="display: none;">
				  <jsp:include page="food_record.jsp" flush="true"></jsp:include>
				  <%-- <%@ include file="food_record.jsp" %>  --%>
			</div>
        </div>
    </div>
</div>
<script src="${ctx }/framework/moment/moment.min.js"></script>
<script src="${ctx }/framework/moment/locale/zh-cn.js"></script>
<script src="${ctx }/framework/autocomplete/jquery.autocomplete.js"></script>
<script src="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.js"></script>
</body>
<script>
function findRecycleBinList(dom, type) {
	$(dom).addClass("active").siblings().removeClass("active");
	if (type == 1) {
		$("#foodQuery").css("display","block").siblings().css("display","none");
	} 
	if (type == 2) {
		$("#nutrimentQuery").css("display","block").siblings().css("display","none");
	}
	if (type == 3) {
		$("#similarityQuery").css("display","block").siblings().css("display","none");
	}
	if (type == 4) {
		resetFood();
		$("#foodRecord").css("display","block").siblings().css("display","none");
	}
	if (type == 5) {
		$("#dietaryGuidelines").css("display","block").siblings().css("display","none");
	}
	if (type == 6) {
		$("#nutritionAssess").css("display","block").siblings().css("display","none");
	}
}
</script>
</html>
