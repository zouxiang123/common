<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<c:if test="${!patient.delFlag}">
    <div class="ml-20 cursor-pointer add_medical_history" id="addMe">
        <i class="icon-add icon-round-add fs-12"></i>
        <i class="icon-round fs-24 position-absolute fc-blue"></i>
    </div>
    <span class="fw-bold position-absolute" style="top: 2px;left: 54px;">新增</span>
    <!--新增弹框-->
    <div class="u-prompt-box width-130 p-0" style="top: -4px;left: 48px;display: none;" id="addMedicalHistoryDialog">
        <ul class="diagnosis-hist-action"></ul>
    </div>
</c:if>
<div id="list_medical_history"></div>
