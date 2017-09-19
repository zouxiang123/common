<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>化验分组配置</title>
<style>
	*{list-style:none;}
	#assayGroupTable_Thead tr td{padding-left:50px;}
</style>
</head>

<body>
	<div class="container-fluid">
      <div class="row">
		<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
            <!-- 头部标签的  -->
            <jsp:include page="assay_config_head.jsp"/>
            <div class="fill-parent">
            <div class="list-card-item">
		  		<div class="tab-header">
		           <span class="tip-line" style="background: #31AAFF;"></span>
		           <span class="tab-title">检查类别</span>
		        </div>
		        <div class="tab-body">
		        	<div class="form-item"><div class="item-box" id="assayCategoryRadio"></div></div>
		        </div>
		    </div>
		    
		    <div class="list-card-item">
		     	 <div class="tab-header">
		           <span class="tip-line" style="background: #31AAFF;"></span>
		           <span class="tab-title">检查项列表</span>
		         </div>
		         <div class="tab-body">
	        		<div class="form-item"><div class="item-box" id="assayItemRadio"></div></div>
		        </div>
	      	</div>
          
           <div class="list-item border-top-line margin-top-8">
                <div class="tab-header" style="border-bottom: 0 !important;padding-left: 0 !important;">
                    <span class="tip-line" style="background: #31AAFF;"></span>
                    <span class="tab-title">检查项规则：</span>
               </div>
               <form id="patientCheckGroupForm">
		               <div class="tab-body" style="margin-top: 5px;">
							<input type="hidden" name="assayHospDict.id" value="" id="dictHospitalLabId"/>
		                    <span style="margin-left:20px;">是否常规项</span>：
		                    <div class="item-box"><input id="isTopTrue" type="radio"  name="assayHospDict.isTop" value="1"><label class="item-col-sm-width" for="isTopTrue">是</label></div>
		                     <div class="item-box"><input id="isTopFalse" type="radio"  name="assayHospDict.isTop" value="0"><label class="item-col-sm-width" for="isTopFalse">否</label></div>
		                    <div style="display: inline-block;margin-left: 20px;"></div>
		               </div>
		               <div class="tab-body" style="margin-top:10px;">
			                    <span class="margin-left-20" style="min-width: 80px;">达标范围</span>：<input type="text" class="text-center margin-left-20 width-150" name="assayHospDict.personalMinValue" id="minValue"/> ~ <input type="text" class="text-center margin-left-20 width-150" name="assayHospDict.personalMaxValue" id="maxValue"/>
			                  
			                   <div style="display: inline-block;margin-left: 20px;"></div>
		               </div>
               
               <!-- 分组规则时的表单数据 -->
		             <div class="tab-body" style="margin-top: 5px;">
		               <input type="hidden" name="hideItemCode" value="">
		                 <span class="margin-left-20" style="min-width: 80px;">分组规则：</span>
		                 <div style="margin-left: 6px; display: inline-block;" id="ruleListDiv">
		                 	 <input class="tl-input margin-right-10 width-78 margin-left-0" type="text" style="height: 30px;"  name="patientAssayGroupRuleList[0].minValue" id="addRule" onblur="judgeExistByAddGroupRule(this)" >
		                 </div>
		                  <form id="saveAssayGroupRuleForm">
	                 		 <button type="button" class="btn btn-default width-76" id="addRuleBtn" >添加</button>
	                 		
	                	 </form>
		               </div>
		               <div data-error></div>
	         </form>   
	         
	             <div>
	               <!-- 保存化验检查项 -->
	               	 <button type="button" class="btn btn-default width-76 " style="margin-left:110px;" id="saveReachRangeDataBtn" onclick="saveCheckRuleData()">保存</button>
	               	   <button type="button" class="btn btn-default width-76" id="cancleBtn" onclick="cancleSave()" >取消</button>
	              </div>
           </div>
            
        </div>
      </div>
      
     
    </div>
    
    <!-- assayCheckGroup页面 -->
   
	<script src="${ctx }/assets/js/assay/assayCheckGrouping.js?version=${version}"></script>
	<script type="text/javascript">
	 	$(function(){
	 	   setAssayTopActive("group_rule");
			$("#dictionaryTopTab").css("margin-left", "-"+($("#dictionaryTopTab").width()/2) + "px");
			
			//日历控件初始化
			$("input[datetimepicker]").datetimepicker({
				language : 'zh-CN',
				format : 'yyyy',
				weekStart : 1,
				todayBtn : 1,
				autoclose : 1,
				todayHighlight : 1,
				startView : 4,
				minView : 4,
				bootcssVer : 3,
				forceParse : 0
			});
	 	});
	</script>
</body>
</html>