<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<title>患者转归列表</title>
</head>
<body>
<div class="mt-12 position-relative">
    <div style="height:23px;">
        <div class="cursor-pointer" data-permission-key="CM_patient_outcome_edit" onclick="patient_outcome_edit.show(${patientId },'${sysOwner }');">
            <div class="ml-20">
                <i class="icon-add icon-round-add fs-12"></i>
                <i class="icon-round fs-24 position-absolute fc-blue"></i>
            </div>
            <span class="fw-bold newpagebutton">新增并发症</span>
        </div>
        <!-- <button type="button" class="pdsettingbutton" data-show="#searchDialog">筛选设置</button>
        <div class="u-prompt-box p-0" top-right style="right: 20px;top: 47px;display: none;" id="searchDialog">
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
                <button type="button" data-hide="#searchDialog">取消</button>
                <button type="button" class="u-btn-blue" onclick="lc_list.search();" fill>确定</button>
            </div>
        </div> -->
    </div>
    <input type="hidden" value="${patient.name }"  id="patientName">
    <input type="hidden" value="${patient.id }"  id="patientId">
    <div id="recordList">
        <c:forEach var="item" items="${items }">
            <div class="border-gray p-10 mt-12">
                <div class="bb-dashed pb-8">
                    <span class="fw-bold">转归时间：${item.recordDateShow }</span>
                    <span class="fw-bold ml-20">转归人：${item.createUserName }</span>
                </div>
                <div class="pt-12 u-xt-12">类别：${item.typeShow }</div>
                <div class="pt-12 u-xt-12">原因：${item.reason }</div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="patient_outcome_edit.jsp"></jsp:include>
</body>
</html>
