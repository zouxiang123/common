<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ include file="../common/head_new.jsp"%>
    <link href="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.css" rel="stylesheet" >
    <link href="${ctx }/framework/dist/css/quickQuery.css" rel="stylesheet">
    <style>
       #quickQuery_container{
           top:inherit !important;
       }
    </style>
</head>

<body class="bg-white">
    <div class="g-mainc center-block bg-white clearfix" style="margin-top: 39px;">
        <input type="hidden" value="${fkPatientId }" id="patientId"/>
        <input type="hidden" value="${sysOwner }" id="sysOwner"/>
        <input type="hidden" value="${recordDateShow }" id="recordDateShow"/>
        <input type="hidden" value="${batchNo }" id="batchNo"/>
    	<div class="g-main" style="margin-top: 12px;">
	        <div class="m-head bottomline">
	            <p class="u-subtitle text-center">饮食回顾</p>
	        </div>
	
	        <div class="m-t-12 col-sm-12 m-b-36">
	        	<div id="record1">
	        	<form id="foodRecordForm">
		            <div class="m-content">
		                <span class="u-span f-span">回顾时间: </span>
		                <input type="text" data-recordTime="record" id="recordTime1" onfocus="addDate(this)" class="u-input m-l-14" placeholder="请选择回顾日期" readonly="readonly"/>
		            </div>
		
		            <div class="m-content">
		                <span class="u-span f-span"  data-value="breakfast">早&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
		                <button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button>
		            </div>
					<div style="display: none;" data-id="breakfast" data-div="breakfast" id="breakfast">
					</div>
		            <div class="m-content">
		                <span class="u-span f-span"  data-value="lunch">午&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
		                <button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button>
		            </div>
					<div style="display: none;" data-id="lunch" data-div="lunch" id="lunch">
					</div>
		            <div class="m-content">
		                <span class="u-span f-span"  data-value="extraMeal">加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
		                <button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button>
		            </div>
					<div style="display: none;" data-id="extraMeal" data-div="extraMeal" id="extraMeal">
					</div>
		            <div class="m-content">
		                <span class="u-span f-span"  data-value="supper">晚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
		                <button type="button" class="u-button m-l-14" onclick="addFoodRecord(this)">添加饮食</button>
		            </div>
					<div style="display: none;" data-id="supper" data-div="supper" id="supper">
					</div>
		            <div class="m-content">
		                <span class="u-span f-span">备注信息:</span>
		                <input type="text" id="recordMark1" name="remark" class="u-input f-input m-l-14"/>
		            </div>
		            <div class="m-content" id="addMoreFood">
		                <span class="u-span f-span"></span>
		                <button type="button" class="u-button1 m-l-14" onclick="addMoreFoodRecord()">添加饮食回顾</button>
		        	</div>
		        	</form>
	        	</div>
		        
	          <div class="m-head bottomline">
	            <p class="u-subtitle text-center"></p>
	          </div>
	          <div class="col-xt-9">
	              <div class="g-main" style="margin-top: 12px;">
	                  <div class="g-mainc-1 center-block bg-white main-border clearfix rel" style="padding-bottom: 40px;">
	                      <div class="m-head">
	                          <p class="u-title-1 text-left">食物营养参考摄入总量<!-- <span class="u-span-3">（每100g含量）</span> --></p>
	                      </div>
	                      <div class="dividingline"></div>
	
	                      <div style="width: 50%;float:left;padding-right: 20px">
	                          <div class="m-t-10" id="totalLoadInit">
	                          </div>
	                      </div>
	
	                      <div style="width: 50%;float:left;padding-right: 20px">
	                          <div class="m-t-10" id="totalLoadInit2">
	                          </div>
	                      </div>
	                  </div>
	              </div>
	           </div>
	        </div>
           	<div class="bottomline">
	            <p class="u-subtitle text-center">推荐饮食</p>
	        </div>
		     
		     <div class="m-t-12 col-sm-12 m-b-36">
	            <div id="recommend1">
					<form id="foodRecommendForm">
			           <!--  <div class="m-content">
		                	<span class="u-span f-span">推荐时间: </span>
		                	<input type="text" data-recommendTime="recommend" id="recommendTime1" onfocus="addDate(this)" class="u-input m-l-14" placeholder="2016-09-09"/>
		            	</div> -->
			
			            <div class="m-content">
			                <span class="u-span f-span"  data-value="foodRecommendBreakfast">早&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
			                <button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button>
			            </div>
						 <div style="display: none;" data-id="foodRecommendBreakfast" id="foodRecommendBreakfast" data-div="foodRecommendBreakfast">
						</div>
			            <div class="m-content">
			                <span class="u-span f-span"  data-value="foodRecommendLunch">午&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
			                <button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button>
			            </div>
						<div style="display: none;" data-id="foodRecommendLunch" id="foodRecommendLunch" data-div="foodRecommendLunch">
						</div>
			            <div class="m-content">
			                <span class="u-span f-span"  data-value="foodRecommendExtraMeal">加&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
			                <button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button>
			            </div>
						<div style="display: none;" data-id="foodRecommendExtraMeal" id="foodRecommendExtraMeal" data-div="foodRecommendExtraMeal">
						</div>
			            <div class="m-content">
			                <span class="u-span f-span"  data-value="foodRecommendSupper">晚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;餐:</span>
			                <button type="button" class="u-button m-l-14" onclick="addFoodRecommend(this)">添加饮食</button>
			            </div>
						<div style="display: none;" data-id="foodRecommendSupper" id="foodRecommendSupper" data-div="foodRecommendSupper">
						</div>
			            <div class="m-content">
			                <span class="u-span f-span">其&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;他:</span>
			                <input type="text" id="recommendMark1" name="remark" class="u-input f-input m-l-14"/>
			            </div>
			           <!--  <div class="m-content" id="addMoreFoodRecommend">
			                <span class="u-span f-span">推荐时间:</span>
			                <button type="button" class="u-button1 m-l-14" onclick="addMoreFoodRecommend()">添加饮食推荐</button>
			        	</div>  -->
		         	</form>
	         	</div>
	        </div>
			 <div class="col-sm-12 center-block p-t-12 p-l-12">
		           <button type="button" class="u-button f-button pull-right m-l-8" onclick="resetFrom()">取消</button>
		           <button type="button" class="u-button f-button-1 pull-right" onclick="insertFoodRecord()">保存</button>
		     </div>
    	</div>
   </div>
<div>     	
</div>
<table class="table" style="padding-left: 250px;display: none;" >
		<thead>
			<tr id="nutritionNameTable">
			</tr>
		</thead>
		<tbody id="tableContent" style="border: 3px solid red;">
		</tbody>
</table>
</body>
<script src="${ctx }/assets/js/nutrition/food_record.js?version=${version}"></script>
<script src="${ctx }/assets/js/nutrition/food_recommend.js?version=${version}"></script>

<script src="${ctx }/framework/dist/js/quickQuery-packer.js"></script>
<script src="${ctx }/framework/jquery/jquery.fastLiveFilter.js"></script>
<script src="${ctx }/framework/moment/moment.min.js"></script>
<script src="${ctx }/framework/moment/locale/zh-cn.js"></script>
<script src="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.js"></script>
</html>