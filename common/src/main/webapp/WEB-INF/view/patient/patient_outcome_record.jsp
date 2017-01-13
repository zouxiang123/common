<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>患者转归列表</title>
</head>
<body class="bg-white">
<div class="fill-parent clearfix m-t-16">
    <div style="padding-left:10px;" class="fill-parent center-block rel">
        <div class="fill-parent clearfix">
            <div class="group-time-line fill-parent hand" style="margin-bottom:0;" onclick="outcome.show(this);" data-permission-key="CM_patient_outcome_edit">
                <div class="time-img-line"></div>
                <div class="sf-add"></div>
                <span class="xt-title fs-12">患者转归</span>
            </div>
        </div>
        <c:forEach var="item" items="${items }">
        	<div class="fill-parent clearfix">
             <div class="group-time-line fill-parent" style="margin-bottom:0;">
                 <div class="time-img-line"></div>
                 <div class="tl-point active"></div>
                 <span class="xt-title m-t-14 fs-12">转归时间：${item.recordDateShow }</span>
                 <span class="xt-title m-t-14 m-l-10 fs-12">转归人：${item.createUserName }</span>
                 <div class="group-time-content group-time-content2 fill-parent tl-border clearfix bg-white">
                     <div class="clearfix">
                         <span>类别：${item.typeShow }</span>
                     </div>
                     <div class="clearfix">
                         <span>原因：${item.reason }</span>
                     </div>
                </div>
             </div>
         </div>
        </c:forEach>
    </div>
</div>
<div class="modal" id="outcomeDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header dialog-header">
				<h4 class="modal-title modal-title2 ">患者转归</h4>
				<div class="dialog-close pull-right" data-dismiss="modal">
					<img src="${COMMON_SERVER_ADDR}/assets/img/dialog-new-close.png">
				</div>
			</div>
			<div class="modal-body">
				<form action="#" onsubmit="return false;" id="outcomeForm">
					<input type="hidden" name="fkPatientId" value="${patientId }" />
					<input type="hidden" name="sysOwner" value="${sysOwner }" />
					<div style="padding-left: 125px; padding-bottom: 14px;">
						<div class="m-t-14">
							<span class="u-span-6">转归时间：</span> 
							<input type="text" class="u-input f-input-5" name="recordDateShow" readonly="readonly" daterangepicker required data-msg-required="请选择转归时间"/>
						</div>
						<div class="m-t-14">
							<span class="u-span-6">类别：</span> 
							<div>
							  <c:forEach var="item" items="${patient_outcome_type }" varStatus="status">
			                   	 <label class="tl" <c:if test="${status.first }">style="margin-left:8px;"</c:if> >
			                        <input type="radio" name="type" value="${item.itemCode }" <c:if test="${item.isChecked }">checked</c:if> />
			                        ${item.itemName }
			                      </label>
			                   </c:forEach>
							</div>
						</div>
						<div class="m-t-14" style="padding-right: 20px;">
							<span class="u-span-6">原因：</span>
							<br>
							<textarea class="u-textarea m-t-10" style="width: 100%;" name="reason" placeholder="请输入转归原因" maxlength="512"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer" style="height: 50px !important;">
				<div class="text-right p-t-10 p-r-3">
					<button type="button" class="btn-border" data-dismiss="modal">取消</button>
					<button type="button" class="btn-blue" onclick="outcome.save();">确定</button>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../common/daterangepicker_include.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		outcome.init();
	});
	var outcome = {
		saveUrl:null,
		init:function(){
			outcome.addEvents();
			outcome.addValidate();
		},
		addEvents:function(){
			$("#outcomeForm [daterangepicker]").daterangepicker({
				"singleDatePicker" : true,
				"showDropdowns" : true,
				// "autoUpdateInitInput" : false,
				"locale" : {
					format : "YYYY-MM-DD"
				}
			});
		},
		show:function(el){
			if(isEmpty(outcome.saveUrl))
				outcome.saveUrl = getPermissionUrlByKey($(el).attr("data-permission-key"));
			var recordDateEl = $("#outcomeForm input[name='recordDateShow']");
			recordDateEl.data("daterangepicker").startDate=moment();
			recordDateEl.data("daterangepicker").endDate=moment();
			recordDateEl.val(new Date().pattern("yyyy-MM-dd"));
			$("#outcomeForm input[name='type']").prop("checked",false);
			$("#outcomeForm [name='reason']").val("");
			$("#outcomeDialog").modal("show");
		},
		save:function(){
			if ($("#outcomeForm").valid()) {
				$.ajax({
					url : ctx + "/"+outcome.saveUrl+".shtml",
					type : "post",
					data : $("#outcomeForm").serialize(),
					dataType : "json",
					loading : true,
					success : function(data) {
						if (data.status == "1") {
							showTips("保存成功");
							window.location.reload(true);
						}
					}
				});
			}
		},
		addValidate:function(){
			$('#outcomeForm').validate({
				onfocusout : false,
				// 校验字段
				rules : {
					type : {
						required : ["转归类型"]
					},
					reason : {
						required : [ "转归原因" ]
					}
				},
				// 全部校验结果
				showErrors : function(errorMap, errorList) {
					showSystemDialog(errorMap);
				}
			});
		}
	};
</script>
</body>
</html>
