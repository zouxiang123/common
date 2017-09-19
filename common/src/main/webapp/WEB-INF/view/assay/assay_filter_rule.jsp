<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>化验清洗规则配置</title>
</head>
<body>
 	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
                <!-- 头部标签的  -->
                <jsp:include page="assay_config_head.jsp"/>
				<div class="tab-header">
						<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">清洗规则</span>
				</div>
				<div class="tab-body">
					<div class="table-responsive bg-white">
						<table class="table" style="padding-left: 250px;" >
							<thead>
								<tr>
									<th class="text-left">分类规则</th>
									<th class="text-left">分组名</th>
									<th class="text-left">化验项</th>
									<th class="text-left">透前关键字</th>
									<th class="text-left">透后关键字</th>
									<th class="text-left">透前项目数量</th>
									<th class="text-left">透后项目数量</th>
                                    <th class="text-left">间隔天数</th>
                                    <th class="text-left">操作</th>
								</tr>
							</thead>
							<tbody id="loadInit">
									<tr class="table-default">
										<td>
                                        <form id="updateAssayFilterRule" method="post" onsubmit="return updateAssay();">
                                            <input type="hidden" name="fkTenantId" value="${assayFilterRule.fkTenantId }">
                                            <input type="hidden" name="id" value="${assayFilterRule.id }"> 
                                            <select id="category" name="category" >
                                                <option value="0" <c:if test="${assayFilterRule.category ==0 }">selected="selected"</c:if>>不区分透前透后</option>
                                                <option value="1" <c:if test="${assayFilterRule.category ==1 }">selected="selected"</c:if>>分组名关键字</option>
                                                <option value="2" <c:if test="${assayFilterRule.category ==2 }">selected="selected"</c:if>>样式名称关键字</option>
                                                <option value="3" <c:if test="${assayFilterRule.category ==3 }">selected="selected"</c:if>>分组名+化验项</option>
                                                <option value="4" <c:if test="${assayFilterRule.category ==4 }">selected="selected"</c:if>>分组名+化验项数量+化验项数值判断</option>
                                            </select>
                                        </td>
										<td>
											<input type="text" id="groupName" name="groupName" class="tl-input pull-left" style="width: 200px"
												value="${assayFilterRule.groupName }" />
										</td>
										<td>
											<input type="text" id="itemCode" name="itemCode" class="tl-input pull-left"  style="width: 200px"
												value="${assayFilterRule.itemCode }" />
										</td>
										<td>
											<input type="text" id="keywordBefore" name="keywordBefore" class="tl-input pull-left"  style="width: 100px"
												value="${assayFilterRule.keywordBefore}" />
										</td>
										<td>
											<input type="text" id="keywordAfter" name="keywordAfter" class="tl-input pull-left" style="width: 100px"
												value="${assayFilterRule.keywordAfter }" />
										</td>
										<td>
											<input type="number" id="itemCountBefore" name="itemCountBefore" class="tl-input pull-left" style="width: 100px"
												value="${assayFilterRule.itemCountBefore }" />
										</td>
										<td>
											<input type="number" id="itemCountAfter" name="itemCountAfter" class="tl-input pull-left" style="width: 100px"
												value="${assayFilterRule.itemCountAfter }" />
										</td>
                                        <td>
                                            <input type="text" id="intervalDay" name="intervalDay" class="tl-input pull-left" style="width: 100px"
                                                value="${assayFilterRule.intervalDay }" />
                                        </td>
										<td class="text-right">
											<input  type="button" value="更新"    class="btn btn-def" onclick="buttonSubmit(this)" />
                                   </from>
										</td>
									</tr>
							</tbody>
						</table>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        setAssayTopActive("filter_rule");
    })

    function updateAssay() {
        var category = $("#category").val();
        var groupName = $("#groupName").val();
        var itemCode = $("#itemCode").val();
        var keywordBefore = $("#keywordBefore").val();
        var keywordAfter = $("#keywordAfter").val();
        var itemCountBefore = $("#itemCountBefore").val();
        var itemCountAfter = $("#itemCountAfter").val();
        var intervalDay = $("#intervalDay").val();
        if (category == null || category == '') {
            showError("分类规则不能为空");
            return false;
        }
        if (category == '1') {
            if (keywordAfter == null || keywordAfter == '') {
                showError("透后关键字不能为空")
                return false;
            }
        }
        if (category == '2') {
            if (keywordAfter == null || keywordAfter == '') {
                showError("透后关键字不能为空");
                return false;
            }
        }
        if (category == '3') {
            var itemCodes = new Array();
            itemCodes = itemCode.split(',');
            if (itemCodes.length > 2) {
                showError('化验项用","分割不能超过两项');
                return false;
            }
            if (itemCodes.length == 0) {
                showError('化验项不能为空');
                return false;
            }
            if (groupName == null && groupName == '') {
                showError('分组名不能为空');
                return false;
            }
        }
        if (category == '4') {
            var itemCodes = new Array();
            itemCodes = itemCode.split(',');
            if (itemCodes.length > 2) {
                showError('化验项用","分割不能超过两项');
                return false;
            }
            if (itemCodes.length == 0) {
                showError('化验项不能为空');
                return false;
            }
            if (groupName == null && groupName == '') {
                showError('分组名不能为空');
                return false;
            }
            if (itemCountBefore == null && itemCountBefore == '') {
                showError('透前项目不能为空');
                return false;
            }
            if (itemCountAfter == null && itemCountAfter == '') {
                showError('透后项目不能为空');
                return false;
            }
        }

        showWarn("是否更新清洗规则", function() {
            $.ajax({
                url : ctx + "/assay/assayFilterRule/updateAssayFilterRule.shtml",
                type : "post",
                data : $("#updateAssayFilterRule").serialize(),
                dataType : "json",
                success : function(data) {
                    if (data.status) {
                        showTips("保存成功");
                    }
                }
            });
            SystemDialog.modal("hide");
        });
    }
</script>
</html>