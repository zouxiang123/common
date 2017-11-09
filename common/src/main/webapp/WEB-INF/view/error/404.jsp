<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
</head>
<body>
<div>
    <div class="service-interrupt">
        <div class="content">
            <i class="icon-404"></i>
            <p>抱歉，您访问的页面不存在！</p>
            <button type="button" class="u-btn-blue" onclick="history.go(-1);" fill>返回</button>
        </div>
    </div>
</div>
</body>
</html>