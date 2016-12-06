<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 bg-white" id="bodyDiv">
			</div>
		</div>
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