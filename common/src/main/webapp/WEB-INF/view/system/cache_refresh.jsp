<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>系统缓存刷新</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
				<div class="tab-header">
					<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">缓存列表</span>
				</div>
				<div class="tab-body">
					<div class="table-responsive bg-white">
						<table class="table">
							<tbody>
								<tr>
									<td>用户缓存</td>
									<td><button class="btn btn-def" data-type="sysUser">刷新</button></td>
								</tr>
								<tr>
									<td>患者缓存</td>
									<td><button class="btn btn-def" data-type="patient">刷新</button></td>
								</tr>
								<tr>
									<td>字典缓存</td>
									<td><button class="btn btn-def" data-type="dictionary">刷新</button></td>
								</tr>
								<tr>
									<td>系统参数缓存</td>
									<td><button class="btn btn-def" data-type="sysParam">刷新</button></td>
								</tr>
								<tr>
									<td>权限缓存</td>
									<td><button class="btn btn-def" data-type="permission">刷新</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$("table").on("click", "button", function() {
				$.ajax({
					url : ctx + "/cacheRefresh/" + $(this).data("type") + ".shtml",
					//data : "",
					type : "post",
					loading : true,
					dataType : "json",
					success : function(data) {
						if(data.status==1){
							showTips("刷新成功");
						}else{
							showTips("刷新失败，请联系管理员！！！");
						}
					}
				});
			});
		});
	</script>
</body>
</html>