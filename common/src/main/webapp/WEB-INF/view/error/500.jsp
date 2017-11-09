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
                <i class="icon-500"></i>
                <p>很抱歉,服务器出错了...</p>
                <input type="hidden" id="message" value="${message }">
                <button type="button" class="u-btn-blue mr-10" onclick="history.go(-1);" fill>返回</button>
                <button type="button" class="u-btn-blue mr-10" onclick="location.reload(true)" fill>重新加载</button>
                <button type="button" class="u-btn-blue mr-10" onclick="feedBack();" fill>意见反馈</button>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    function feedBack() {
        $.ajax({
            url : cm_server_addr + "/saveFeedback.shtml",
            data : {
                content : $("#message").val()
            },
            type : "post",
            dataType : "json",
            success : function(data) {// ajax返回的数据
                showTips("提交成功");
            }
        });
    }
</script>
</html>