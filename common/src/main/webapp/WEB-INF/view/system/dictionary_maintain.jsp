<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>字典参数维护</title>
<style type="text/css">
.select {
	-moz-border-radius: 4px;
	-webkit-border-radius: 4px;
	-border-radius: 4px;
	border: 1px solid #d9e0e6;
	appearance: none;
	-moz-appearance: none;
	-webkit-appearance: none;
	margin-left: 14px;
	height: 32px;
	min-width: 180px;
	text-align: center;
	background: url("${COMMON_SERVER_ADDR}/assets/img/arrow.png") no-repeat scroll right center transparent;
}

tbody tr.active {
	color: #fff !important;
}

tbody tr.active td {
	background: #31AAFF !important;
}

.select::-ms-expand {
	display: none;
}

.input-style {
	margin-bottom: 0px;
}

.dialog-wrap .input-style {
	width: 180px !important;
}

.input-style.width-70 {
	width: 71px !important;
	min-width: 70px !important;
}

.input-style.width-300 {
	width: 300px !important;
	min-width: 0px !important;
}
</style>
</head>
<body onresize="resizeDictionary();">
	<div class="container-fluid">
		<div class="row">
			 <input type="hidden" name="sysOwner" value="${sysOwner }" id="sysOwner">
			<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
				<div class="fill-parent rel bg-white border-bottom-line" style="height: 48px;">
					<div class="tab-index" style="width: auto;" id="dictionaryTopTab">
						<span class="tab-index-item active" data-type="dictionary">字典表</span> 
						<!-- <span class="tab-index-item-center" data-type="assay">化验单</span> -->
					</div>
					<div class="tab-action" onclick="refreshMemory();" style="margin: 20px 34px;">
						<div class="dividing-line"></div>
						<span>刷新缓存</span><img src="${COMMON_SERVER_ADDR}/assets/img/refresh.png" class="refresh">
					</div>
				</div>
				<!-- 字典表 -->
				<div id="dictionary" style="display: none;">
					<!-- 新建数据 dialog -->
					<div class="modal" id="dictionaryAddDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
						<div class="modal-dialog">
							<div class="modal-content" style="min-width: 600px;">
								<form id="dictionaryAddForm" method="post" onsubmit="return false;" action="${ctx }/system/dictionary/updateDictionary.shtml">
									<div class="modal-header">
										<h4 class="modal-title">新增检查项</h4>
									</div>
									<div class="modal-body">
										<div class="dialog-wrap" style="width: inherit;">
											<div class="list-group bg-white">
												<div class="list-group-item margin-top-10">
													<span class="list-group-item-title">类别&nbsp;key：</span> <input class="input-style width-300" type="text" name="pItemCode" maxlength="64" />
												</div>
												<div class="list-group-item">
													<span class="list-group-item-title">类别名称：</span> <input class="input-style width-300" type="text" name="itemDesc" maxlength="127" />
												</div>
												<div class="list-group-item">
													<span class="list-group-item-title">项目编码：</span> <input class="input-style width-300" type="text" name="itemCode" maxlength="32" />
												</div>
												<div class="list-group-item">
													<span class="list-group-item-title">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span> <input class="input-style width-300" type="text"
														name="itemName" maxlength="64" />
												</div>
												<div class="list-group-item">
													<span class="list-group-item-title">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序：</span> <input class="input-style width-300" type="text"
														name="orderBy" maxlength="3" />
												</div>
											</div>
										</div>
									</div>
									<input type="hidden" name="pid" value="0"> <input type="hidden" name="isEnable" value="true">
									<div class="modal-footer">
										<span class="dialog-btn-close dialog-btn-size" data-dismiss="modal">取消</span> <span class="dialog-btn-ok dialog-btn-size"
											onclick="saveAddDictionary('dictionaryAddForm','dictionaryAddDialog',true);">确定</span>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!-- end of 新建数据 dialog -->
					<form method="post" class="hide" id="dictionaryQueryForm" action="#" onsubmit="return false;">
						<input type="hidden" name="pItemCode">
					</form>
					<div class="list-card-item">
						<div class="tab-header">
							<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">数据列表</span>
							<div class="tab-action hand margin-right-25" onclick="showAddDialog('dictionaryAddDialog');">
								<div class="dividing-line"></div>
								<div class="new-btn"></div>
								<span class="pull-right action-name">添加数据</span>
							</div>
						</div>
						<div class="tab-body">
							<div class="col-sm-3 col-md-2 bg-white" style="padding-right: 1px" id="dictionaryCategoryList">
								<div style="text-align: left; font-size: 14px;" id="dictionaryCategorySearchCount"></div>
								<img src="${COMMON_SERVER_ADDR}/assets/img/search-icon.png" class="pad-search-icon" style="position: absolute; top: 37px; left: 36px;"> <input
									class="nav-search-input fill-parent" style="width: 88%;" type="search" placeholder="搜 索" id="dictionaryCategorySearch">
								<div class="fill-parent" id="dictionaryCategorySearchContent" style="height: 397px; overflow-y: auto; overflow-x: hidden;"></div>
							</div>
							<div class="col-sm-9 col-md-10" style="height: 397px; overflow: auto; padding-right: 0px; padding-left: 10px;" id="dictionaryList">
								<div class="table-responsive bg-white">
									<table class="table">
										<thead>
											<th>类别key</th>
											<th>类别名称</th>
											<th>项目编码</th>
											<th>名称</th>
											<th>排序</th>
											<th>是否有效</th>
											<th></th>
										</thead>
										<tbody id="dictionaryTableBody">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 字典表 -->
				<!-- 化验单 -->
				<div id="assay" style="display: none;">
					<div class="col-sm-12 col-md-12" id="assayMappingDiv">
						<div class="list-card-item">
							<div class="tab-header">
								<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">未匹配列表</span>
							</div>
							<div class="tab-body">
								<div class="col-sm-6 col-md-6 p-r-0 p-l-0">
									<div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: auto; max-height: 400px;" id="hospitalAssayTable">
										<table class="table">
											<thead>
												<th><span>分组名称</span></th>
												<th><span>项目代号</span></th>
												<th><span>项目名称</span></th>
											</thead>
											<tbody id="hospitalAssayTableBody">
											</tbody>
										</table>
									</div>
								</div>
								<div class="col-sm-2 col-md-1 p-r-0" style="padding-left: 5px;">
									<button class="btn btn-def" style="width: 80px;" onclick="mappingAssayDict();">关联</button>
								</div>
								<div class="col-sm-4 col-md-5 p-r-0 p-l-0">
									<div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: auto; max-height: 400px;" id="sysAssayTable">
										<table class="table">
											<thead>
												<th><span>项目代号</span></th>
												<th><span>项目名称</span></th>
											</thead>
											<tbody id="sysAssayTableBody">
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12 col-md-12">
						<div class="list-card-item">
							<div class="tab-header">
								<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">已匹配列表</span>
							</div>
							<div class="tab-body">
								<div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: auto; max-height: 300px;" id="assayTable">
									<table class="table">
										<thead>
											<!-- <th><span>分组编号</span></th> -->
											<th><span>分组名称</span></th>
											<th><span>项目代号</span></th>
											<th><span>项目名称</span></th>
											<!-- <th><span>结果类型</span></th> -->
											<th><span>参考值</span></th>
											<th><span>单位</span></th>
											<th><span>关联编码</span></th>
											<th><span>关联名称</span></th>
											<th></th>
										</thead>
										<tbody id="assayTableBody">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 化验单 end-->
			</div>
		</div>
	</div>
	<script src="${COMMON_SERVER_ADDR}/framework/jquery/jquery.fastLiveFilter.js"></script>
	<script type="text/javascript">
	 	var assayItems = [];
	 	$(function(){
			 //设置关联dialog宽度为800px
			$("#assayMappingDialog").css("margin", "0px auto");
            $("#assayMappingDialog .modal-dialog").css("width", "800px");
            //设置关联dialog 内容高度
            var dialog_height = 600;
            $("#assayMappingDialog .modal-content").css("height", dialog_height + "px");
            $("#assayMappingDialog .dialog-wrap .list-group").css("height", (dialog_height - 118)+"px");
            
			$("#dictionaryTopTab").css("margin-left", "-"+($("#dictionaryTopTab").width()/2) + "px");
			resizeDictionary();
			
			initData();
			addEvents();
			addValidate();
	 	});
	 	/**初始化数据 */
	 	function initData(){
		    clearTableBody();
		    getTableBody();
		    showActiveContent();
	 	}
	 	
	 	function resizeDictionary() {
			$("#dictionaryCategorySearchContent").css("height",$(window).height()-170);
			$("#dictionaryList").css("height",$("#dictionaryCategoryList").css("height"));
			$("#assayTable").css("max-height",$(window).height()-$("#assayMappingDiv").height()-110);
		}
	 	/* -----------------事件区 start------------------ */
	 	/**事件注册 */
	 	function addEvents(){
	 		 $("#assayCategoryRadio").on("click","input[type='radio']",function(){
		    	getAssayTableBody();
			 });
	 		 
	 		 $("#dictionaryTopTab span[data-type]").click(function(){
	 			$(this).addClass("active").siblings().removeClass("active");
	 			clearTableBody();
	 			getTableBody();
	 			showActiveContent();
	 		 });
	 		 
	 		 $("#sysAssayTableBody,#hospitalAssayTableBody").on("click","tr",function(){
	 			$(this).addClass("active").siblings().removeClass("active");
	 		 });
	 	}
	 	/** 字典类别模拟点击 */
	 	function dictionarySimulationClick(){
	 		$("#dictionaryCategorySearchContent div.nav-search-result:visible").eq(0).trigger("click");
	 	}
	 	/**字典类别点击事件 */
	 	function showDictionaryBody(element){
	 		clearTableBody();
	 		$(element).addClass("active").siblings().removeClass("active");
	 		$("#dictionaryQueryForm input[name='pItemCode']").val($(element).attr("data-type"));
	 		getDictionaryTableBody();
	 	}
	 	
		/** 显示active tab的内容 */
		function showActiveContent(){
			$("#dictionaryTopTab span[data-type]").each(function(){
				if($(this).hasClass("active")){
					$("#"+$(this).attr("data-type")).show();
				}else{
					$("#"+$(this).attr("data-type")).hide();
				}
			});
		}
	 	/* -----------------事件区 end------------------ */
	 	
	 	/* -----------------功能区 start------------------ */
	 	/** 获取tableBody数据 */ 
		function getTableBody(){
			var type=$("#dictionaryTopTab .active").attr("data-type");
			if(type=="dictionary"){
				var selectedObj = $("#dictionaryCategorySearchContent div.nav-search-result.active");
	 			if(!isEmpty(selectedObj)&&selectedObj.length>0){//刷新项
 					getDictionaryTableBody();
	 			}else{//刷新类别
					getDictionaryCategory();
	 			}
			}else if(type=="assay"){
				getAssayTableBody();
			}else if(type=="assessment"){
				getAssessmentTableBody();
			}
		}
	 	
		/* --------------dictionary start---------------- */
	 	/**获取字典表类别列表 */
	 	function getDictionaryCategory(){
			$.ajax({
	   			url : ctx + "/system/dictionary/getDictCategoryList.shtml",
	   			type : "post",
	   			data : "sysOwner="+$("#sysOwner").val(),
	   			dataType : "json",
	   			success : function(data) {
	   				if(data.status==1){
	   					var bodyHtml = "";
	   					for(var i=0;i<data.items.length;i++){
	   						var item = data.items[i];
	   						bodyHtml+='<div class="nav-search-result" style="min-height:40px;height:auto;" onclick="showDictionaryBody(this);" data-type="'+item.pItemCode+'"><span style="max-width: inherit;">'+item.itemDesc+'</span></div>';
	   					}
	   					$("#dictionaryCategorySearchContent").html(bodyHtml);
	   					
	   					$("#dictionaryList").css("height",$("#dictionaryCategoryList").css("height"));
	   					$('#dictionaryCategorySearch').fastLiveFilter('#dictionaryCategorySearchContent',{
	   			 		    timeout: 300,
	   			 		    callback: function(total) { $('#dictionaryCategorySearchCount').html("共"+total+"种类别");}
	   			 		});//初始化过滤器
	   				}
	   			}
	   		});
	 	}
	 	
	 	/** 获取table数据 */
		function getDictionaryTableBody(){
			$.ajax({
	   			url : ctx + "/system/dictionary/getDictionaryList.shtml",
	   			type : "post",
	   			data : $("#dictionaryQueryForm").serialize()+"&sysOwner="+$("#sysOwner").val(),
	   			dataType : "json",
	   			success : function(data) {
	   				if(data.status==1){
	   					var bodyHtml = "";
	   					for(var i=0;i<data.items.length;i++){
	   						var item = data.items[i];
	   						bodyHtml += '<tr data-type="'+item.pItemCode+'">';
   							bodyHtml += '<td class="text-center">'+convertEmpty(item.pItemCode)+'</td>';  
   							bodyHtml += '<td class="text-center">'+convertEmpty(item.itemDesc)+'</td>';  
   							bodyHtml += '<td class="text-center">'+convertEmpty(item.itemCode)+'</td>';  
   							bodyHtml += '<td class="text-center"><input type="text" name="itemName" value="'+(item.itemName)+'" maxlength="64" /></td>';  
   							bodyHtml += '<td class="text-center"><input type="number" name="orderBy" value="'+convertEmpty(item.orderBy)+'" maxlength="3" /></td>';  
   							bodyHtml += '<td class="text-center">'+(item.isEnable?"是":"否")+'</td>';  
   							bodyHtml += '<td>';  
   							bodyHtml += '<button type="button" class="btn btn-def pull-right" onclick="updateDictionary(this,\''+item.id+'\')">更  新</button>';
   							if(item.isEnable)
   								bodyHtml += '<button type="button" class="btn btn-def pull-right" onclick="updateDictionary(this,'+item.id+',false)">停  用</button>';   
							else
								bodyHtml += '<button type="button" class="btn btn-def pull-right" onclick="updateDictionary(this,'+item.id+',true)">启  用</button>';  	
   							bodyHtml += '</td>';   
   							bodyHtml += '</tr>';   
	   					}
	   					$("#dictionaryTableBody").html(bodyHtml);
	   					resizeDictionary();
	   				}
	   			}
	   		});
		}
		/* --------------dictionary end---------------- */
		
	 	/* -----------------assay start------------------ */
	 	/** 显示添加类别dialog */
	 	function showAssayCategoryDialog(dialogId){
	 		$("#"+dialogId).find("form")[0].reset();
	 		$("#"+dialogId).modal("show");
	 	}
	 	
	 	/** 显示添加项dialog */
	 	function showAddDialog(dialogId){
	 		$("#"+dialogId).find("form")[0].reset();
	 		$("#"+dialogId).find("form input[name='id']").val("");
	 		if("addAssayCheckDialog" == dialogId){
	 			$("#"+dialogId +" select[name='groupId']").val($("#assayCategoryRadio input[type='radio']:checked").val());
	 			changeAssayValueType("1");
	 		}
	 		if("dictionaryAddDialog" == dialogId){
	 			var selectedObj = $("#dictionaryCategorySearchContent div.nav-search-result.active");
	 			if(!isEmpty(selectedObj)&&selectedObj.length>0){
		 			$("#"+dialogId).find("form input[name='pItemCode']").attr("readonly",true).val(selectedObj.attr("data-type"));
		 			$("#"+dialogId).find("form input[name='itemDesc']").attr("readonly",true).val(selectedObj.find("span").text());
	 			}
	 		}
	 		$("#"+dialogId).modal("show");
	 	}

	 	/** 获取table数据 */
		function getAssayTableBody(){
			var groupId = convertEmpty($("#assayCategoryRadio input[type='radio']:checked").val());
			$.ajax({
	   			url : ctx + "/system/dictionary/getAssayList.shtml",
	   			type : "post",
	   			data : "groupId="+encodeURI(groupId),
	   			dataType : "json",
	   			success : function(data) {
   					var bodyHtml = "";
   					var mappingHtml = "";
  					var mappedCodes = [];
	   				if(data.status==1){
	   					assayItems = data.items;
	   					for(var i=0;i<data.items.length;i++){
	   						var item = data.items[i];
	   						if(!isEmpty(item.fkDictCode)){
	   							mappedCodes.push(item.fkDictCode);
		   						bodyHtml += '<tr>';
	   							//bodyHtml += '<td class="text-center">'+convertEmpty(item.groupId)+'</td>';  
	   							bodyHtml += '<td class="text-center">'+convertEmpty(item.groupName)+'</td>';  
	   							bodyHtml += '<td class="text-center">'+convertEmpty(item.itemCode)+'</td>';  
	   							bodyHtml += '<td class="text-center">'+convertEmpty(item.itemName)+'</td>';  
	   							bodyHtml += '<td class="text-center">'+convertEmpty(item.reference)+'</td>';  
	   							bodyHtml += '<td class="text-center">'+convertEmpty(item.unit)+'</td>';  
	   							bodyHtml += '<td class="text-center">'+convertEmpty(item.fkDictCode)+'</td>';  
	   							bodyHtml += '<td class="text-center">'+convertEmpty(item.fkItemName)+'</td>';  
	   							bodyHtml += '<td>';  
	   							bodyHtml += '<button type="button" class="btn btn-def pull-right " onclick="deleteAssayMapping('+item.id+')">解除关联</button>';   
	   							bodyHtml += '</td>';   
	   							bodyHtml += '</tr>';   
	   						}else{
	   							mappingHtml += '<tr data-id="'+item.id+'">';
	   							mappingHtml += '<td class="text-center">'+convertEmpty(item.groupName)+'</td>';
	   							mappingHtml += '<td class="text-center">'+convertEmpty(item.itemCode)+'</td>';  
	   							mappingHtml += '<td class="text-center">'+convertEmpty(item.itemName)+'</td>';  
	   							mappingHtml += '</tr>';   
	   						}
	   					}
	   				}
	   				getAssayMappingData(mappedCodes);
	   				$("#hospitalAssayTableBody").html(mappingHtml);
  					$("#assayTableBody").html(bodyHtml);
  					resizeDictionary();
	   			}
	   		});
		}
	 	/**获取化验单对应关系数据 */
	 	function getAssayMappingData(mappedCodes){
	 		$.ajax({
	   			url : ctx + "/system/dictionary/getPatientAssayDict.shtml",
	   			type : "post",
	   			dataType : "json",
	   			success : function(data) {
   					var bodyHtml = '';
   					if(data.status==1){
   						for(var i=0;i<data.items.length;i++){
   							var item = data.items[i];
   							if(checkCodeExists(mappedCodes,item.itemCode))
   								continue;
   							bodyHtml += '<tr data-code="'+item.itemCode+'">';
   							bodyHtml += '<td class="text-center">'+convertEmpty(item.itemCode)+'</td>';  
   							bodyHtml += '<td class="text-center">'+convertEmpty(item.itemName)+'</td>';  
   							bodyHtml += '</tr>';   
   						}
   					}
   					$("#sysAssayTableBody").html(bodyHtml);
   					resizeDictionary();
	   			}
	   		});	
	 	}
	 	
	 	function checkCodeExists(codes,code){
	 		for(var i=0;i<codes.length;i++){
	 			if(codes[i] == code)
	 				return true;
	 		}
	 		return false;
	 	}
	 	/* --------------assay end---------------- */
	 	
	 	/**获取风险评估分数数据 */
	 	function getAssessmentTableBody(){
			$.ajax({
	   			url : ctx + "/system/dictionary/getAssessmentScore.shtml",
	   			type : "post",
	   			dataType : "json",
	   			success : function(items) {
   					var bodyHtml = "";
   					for(var i=0;i<items.length;i++){
   						var item = items[i];
						bodyHtml += '<tr data-type="'+item.pItemCode+'" data-value="'+item.value+'" data-id="'+convertEmpty(item.id)+'">';
						bodyHtml += '<td class="text-center">'+convertEmpty(item.itemDesc)+'</td>';  
						bodyHtml += '<td class="text-center">'+convertEmpty(item.itemName)+'</td>';  
						bodyHtml += '<td class="text-center"><input type="text" name="score" value="'+convertEmpty(item.score)+'" maxlength="10" /></td>';  
						bodyHtml += '<td>';  
						bodyHtml += '<button type="button" class="btn btn-def pull-right" onclick="updateAssessment(this)">更  新</button>';
						bodyHtml += '</td>';   
						bodyHtml += '</tr>';   
   					}
   					$("#assessmentTableBody").html(bodyHtml);
	   			}
	   		});
	 	}
		
		/** 清空tableBody的值 */
	 	function clearTableBody(){
	 		var type =$("#dictionaryTopTab .active").attr("data-type");
	 		$("#"+type+"TableBody").html("");
	 		if(type=="assay"){
				$("#assayCategoryRadio").html("");
				$("#assayMappingBody").html("");
			}
	 	}
		
		/**--------------------数据提交 start---------------- */
		/** 更新字典表 */
		function updateDictionary(element,itemId,isEnable){
			var data = "";
			if(!isEmpty(isEnable)){//更新状态
				data = "id="+itemId+"&isEnable="+isEnable;
			}else{//更新数据操作
				var parentObj = $(element).parent().parent();
				var orderBy = parentObj.find("input[name='orderBy']").val();
				var name = parentObj.find("input[name='itemName']").val();
				var errorMap = {};
				if(isEmpty(name)){
					errorMap["itemName"] = "显示的名称不能为空";
				}
				if(isEmpty(orderBy)){
					errorMap["orderBy"] = "排序的值不能为空";
				}else if(!isPInt(orderBy)){
					errorMap["orderBy"] = "排序的值只能为正整数";
				}
				if(isEmptyObject(errorMap)){
					data = "id="+itemId+"&itemName="+encodeURIComponent(name)+"&orderBy="+encodeURIComponent(orderBy)+"&pItemCode="+encodeURIComponent(parentObj.attr("data-type"));
				}else{
					showSystemDialog(errorMap, "info");
				}
			}
			if(!isEmpty(data))
				saveDictionary(data);
		}
		
		/**保存添加的字典表数据 */
		function saveAddDictionary(formId,dialogId,needHide){
			if($("#"+formId).valid()){
				saveDictionary($("#"+formId).serialize(),dialogId,needHide);
			}
		}
		
	 	/** 保存化验单数据 */
	 	function saveAssay(formId,dialogId,needHide){
	 		if($("#"+formId).valid()){
	 			$.ajax({
					url : $("#"+formId).attr("action"),
					type : "post",
					data : $("#"+formId).serialize(),
					dataType : "json",
		   			loading : true,
					success : function(data) {
						if(data.status==1){
							if(needHide){
								getTableBody();
								$("#"+dialogId).modal("hide");
							}else{
								window.location.reload(true);
							}
						}else if(data.status==2){
							showWarn("排序字段不能重复");
						}else if(data.status==0){
							showError("请填写所有必填项");
						}
					}
				});
	 		}
	 	}
		
		/** 保存风险评估分数数据 */
		function updateAssessment(element){
			var parentObj = $(element).parent().parent();
			var score = parentObj.find("input[name='score']").val();
			if(isEmpty(score)){
				showWarn("分数不能为空");
			}else if(!isInt(score)){
				showWarn("分数只能是整数");
			}else{
				$.ajax({
		   			url : ctx + "/system/dictionary/saveAssessmentScore.shtml",
		   			type : "post",
		   			data : "id="+parentObj.attr("data-id")+"&dicType="+parentObj.attr("data-type")+"&dicValue="+parentObj.attr("data-value")+"&score="+score,
		   			dataType : "json",
		   			loading : true,
		   			success : function(data) {
		   				if(data.status == 1){
		   					getTableBody();//刷新数据
		   				}
		   			}
		   		});
			}
		}
		
		/** 保存字典表数据 */
		function saveDictionary(data,dialogId,needHide){
			$.ajax({
	   			url : ctx + "/system/dictionary/updateDictionary.shtml",
	   			type : "post",
	   			data : data+ "&sysOwner="+$("#sysOwner").val(),
	   			dataType : "json",
	   			loading : true,
	   			success : function(data) {
	   				if(data.status == 1){
	   					showTips();
	   					if(needHide){
	   						clearTableBody();
   		 					getTableBody();
							$("#"+dialogId).modal("hide");
						}
	   				}else if(data.status == 2){
	   					showWarn("当前类别下的(名称/值)已存在");
	   				}else if(data.status == 0){
	   					showWarn("同类别下的排序值不能相同");
	   				}
	   			}
	   		});
		}
		/** 刷新内存数据 */
		function refreshMemory(){
			$.ajax({
	   			url : ctx + "/system/dictionary/refresh.shtml",
	   			type : "post",
	   			dataType : "json",
	   			success : function(data) {
	   				if(data.status == 1){
	   					showAlert("刷新成功");
	   				}
	   			}
	   		});
		}
		
		/** 删除化驗單類型 */
	 	function deleteAssay(isCategory,id){
	 		if(isCategory){
	 			var assayObj = $("#assayCategoryRadio input[type='radio']:checked");
	 			if(assayObj.length==0){
	 				showWarn("请选择您要删除的类别");
		 			return false;
	 			}
		 		id = $("#assayCategoryRadio input[type='radio']:checked").val();
		 		if(isEmpty(id)){
		 			showWarn("全部不能删除");
		 			return false;
		 		}
	 		}
	 		showWarn("您确定要删除吗？", function(){
	 			$.ajax({
		   			url : ctx + "/system/dictionary/deleteAssay.shtml",
		   			type : "post",
		   			data : "id=" + id,
		   			dataType : "json",
					loading : true,
		   			success : function(data) {
		   				if(data.status==1){
		   					getTableBody();
		   					showAlert("删除成功");
		   				}else if(data.status==2){
		   					showWarn("固定项不能删除");
		   				}
		   			}
		   		});
	 		});
	 	}
		/**解除关联 */
		function deleteAssayMapping(id){
			$.ajax({
	   			url : ctx + "/system/dictionary/deleteAssayMapping.shtml",
	   			type : "post",
	   			data : "id=" + id,
	   			dataType : "json",
				loading : true,
	   			success : function(data) {
	   				if(data.status==1){
	   					getAssayTableBody();
	   					showAlert("解除关联成功");
	   				}else if(data.status==2){
	   					showWarn("固定项不能删除");
	   				}
	   			}
	   		});
		}
		/**建立关联 */
		function mappingAssayDict(){
			var id = $("#hospitalAssayTableBody tr.active").attr("data-id");
			var fkDictCode = $("#sysAssayTableBody tr.active").attr("data-code");
			if(isEmpty(id)){
				showWarn("请选择需要关联的项");
				return false;
			}
			if(isEmpty(fkDictCode)){
				showWarn("请选择关联的对象");
				return false;
			}
			$.ajax({
	   			url : ctx + "/system/dictionary/updateDict.shtml",
	   			type : "post",
	   			data : "id=" + id+"&fkDictCode="+fkDictCode,
	   			dataType : "json",
				loading : true,
	   			success : function(data) {
	   				if(data.status==1){
	   					getAssayTableBody();
	   					showAlert("关联成功");
	   				}else if(data.status==2){
	   					showWarn("该关联项已存在");
	   				}
	   			}
	   		});
			
		}
		/**--------------------数据提交 end---------------- */
	 	
	 	/** 添加校验 */
	 	function addValidate() {
	 		$('#dictionaryAddForm').validate({
	 			onfocusout : false,
	 			// 校验字段
	 			rules : {
	 				type : {
	 					required : [ "类别key" ],
	 					isNumberOr_Letter : ["类别key"]
	 				},
	 				itemDesc : {
	 					required : [ "类别名称" ]
	 				},
	 				value : {
	 					required : [ "itemCode" ],
	 					isNumberOr_Letter : ["itemCode"]
	 				},
	 				name : {
	 					required : [ "名称" ]
	 				},
	 				orderBy : {
	 					required : [ "排序" ],
	 					isPInt : ["排序"]
	 				}
	 			}, 
	 			// 全部校验结果
	 			showErrors : function(errorMap, errorList) {
	 				showSystemDialog(errorMap);
	 			}
	 		});
	 		
	 		$.validator.addMethod("minRequired", function(value, element, params) {
	 			var type = $("#assayCheckForm select[name='valueType']").val();
	 			if(type==1&&(isEmpty($("#assayCheckForm input[name='minValue']").val()))){
		 			return false;
	 			}
	 			return true;
	 		}, jQuery.validator.format("请填写检查项的正常最低值"));
	 		$.validator.addMethod("maxRequired", function(value, element, params) {
	 			var type = $("#assayCheckForm select[name='valueType']").val();
	 			if(type==1&&(isEmpty($("#assayCheckForm input[name='maxValue']").val()))){
		 			return false;
	 			}
	 			return true;
	 		}, jQuery.validator.format("请填写检查项的正常最高值"));

	 		$('#assayCheckForm').validate({
	 			onfocusout : false,
	 			// 校验字段
	 			rules : {
	 				uniqueId : {
	 					required : [ "唯一标识码" ],
	 					isPInt : ["唯一标识码"]
	 				},
	 				code : {
	 					required : [ "项目代号" ]
	 				},
	 				name : {
	 					required : [ "项目名称" ]
	 				},
	 				minValue : {
	 					number : ["参考值-->最低值"],
	 					customRange : [0,9999,"参考值-->最低值"],
	 					minRequired : true
	 				},
	 				maxValue : {
	 					number : ["参考值-->最高值"],
	 					customRange : [0,9999,"参考值-->最高值"],
	 					maxRequired : true
	 				},
	 				orderBy : {
	 					required : [ "排序" ],
	 					isPInt : ["排序"]
	 				}
	 			},
	 			// 全部校验结果
	 			showErrors : function(errorMap, errorList) {
	 				showSystemDialog(errorMap);
	 			}
	 		});
	 		
	 		$('#assayCategoryForm').validate({
	 			onfocusout : false,
	 			// 校验字段
	 			rules : {
	 				name : {
	 					required : [ "类别名称" ]
	 				},
	 				orderBy : {
	 					required : [ "排序" ],
	 					isPInt : ["排序"]
	 				}
	 			},
	 			// 全部校验结果
	 			showErrors : function(errorMap, errorList) {
	 				showSystemDialog(errorMap);
	 			}
	 		});
	 		$('#assayMappingForm').validate();
	 	}
	 </script>
</body>
</html>
