<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<div class="p-relative fs-15 fc-deepgrey w-10">
    <div class="u-lead"></div>
    <div class="m-l-21 p-relative">
        <c:if test="${patient.delFlag}">
            <img class="mt-2 add_cure_complication" style="display: none;">
        </c:if>
        <c:if test="${!patient.delFlag}">
            <img src="${ctx}/assets/img/add2.png" width="20" height="20" class="mt-2 add_cure_complication">
            <span class="m-l-5">新增</span>
        </c:if>
    </div>
    <div id="list_cure_complication">

    </div>
</div>
