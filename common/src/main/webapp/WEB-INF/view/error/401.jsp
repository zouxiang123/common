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
                <i class="icon-401"></i>
                <p>用户尚未登录或登录信息已失效，正在跳转到登录页面...</p>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    $(function() {
        setTimeout(function() {
            top.location.reload(true);
        }, 1000);
    });
</script>
</html>