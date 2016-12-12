<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>表单预览</title>
<%@ include file="../common/head.jsp"%>
</head>
<body class="bg-white">
	<div class="g-main">
		<div class="g-mainc center-block bg-white clearfix" id="bodyDiv"></div>
	</div>
	<script type="text/javascript">
		$.ajax({
			url :ctx+"/dynamicForm/formItems/getDetailItems.shtml" ,
			data : "fkFormId=${id}",
			type : "post",
			dataType : "json",
			loading : true,
			success : function(data) {
				$("#bodyDiv").html(dfr_obj.generateNodes(data.items));
			}
		});
	</script>
	<script src="${ctx }/assets/js/dynamicForm/rendering.js?version=${version}"></script>
</body>
</html>