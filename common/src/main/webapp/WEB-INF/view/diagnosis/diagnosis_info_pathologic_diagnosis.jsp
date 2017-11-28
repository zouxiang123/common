<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<c:if test="${!patient.delFlag}">
<div style="height:23px;">
    <div class="ml-20 cursor-pointer add_pathologic_diagnosis">
        <i class="icon-add icon-round-add fs-12"></i>
        <i class="icon-round fs-24 position-absolute fc-blue"></i>
    </div>
    <span class="fw-bold position-absolute" style="top: 2px;left: 54px;">新增</span>
</div>
</c:if>
<div id="list_pathologic_diagnosis"></div>
