<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_new.jsp"%>
<title>相似食物查询</title>
</head>
<body>
<input type="hidden" id="selectFoodId">
<input type="hidden" id="selectFoodId2">
<input type="hidden" id="selectFoodName">
<input type="hidden" id="selectFoodId1">
<input type="hidden" id="selectFoodName1">
<div class="g-main" style="margin-top: 39px;">
            
             <div class="bg-white">
             	<div class="g-main" style="margin-top: 12px;">
                  <div class="col-xt-3">
                      <div class="main-border p-t-12 p-b-12 center">
                          <span class="u-span-2 fw-bold">待查食物名称：</span>
                          <input type="text" class="u-input f-input-1 m-l-4" id="searchSimilaryFood" placeholder="黄瓜"/>
                      </div>
                      <div class="main-border m-t-10 p-l-12">
                          <div class="m-head">
                              <p class="u-title-1 text-left" id="foodText">待查食物</p>
                          </div>
                          <div class="dividingline"></div>

                          <div class="m-t-10" id="similaryFoodUi">
                             
                          </div>
                      </div>
                  </div>

                  <div class="col-xt-5 p-l-12 p-r-12 center">
                      <div class="main-border p-t-10 center p-l-12 p-r-12">
                          <button id="nutritionBut1" type="button" class="u-button1 f-button-2 active m-r-6" onclick="chooseCatogery(this,1)">营养素关联</button>
                          <button id="nutritionBut2" type="button" class="u-button1 f-button-2" onclick="chooseCatogery(this,2)">营养素对比</button>

                          <div class="clearfix" id="similarCatogery">                         
                          </div>
					<div id="similarContrast" style="display: none;">
					  <div class="main-border m-t-10 center p-l-12 p-r-12">
                            <div class="m-head">
                                <p class="u-title-1 text-left" id="hFood1"></p>
                            </div>
                            <div class="dividingline"></div>

                            <div class="clearfix">
                                <div class="col-xt-6 text-left p-t-5 p-b-5" id="nutritionTable1">
                                    
                                </div>
                            </div>
                        </div>

	                   <div class="main-border m-t-10 center p-l-12 p-r-12">
	                       <div class="m-head">
	                           <p class="u-title-1 text-left" id="hFood2"></p>
	                       </div>
	                       <div class="dividingline"></div>
                            <div class="clearfix">
                                <div class="col-xt-6 text-left p-t-5 p-b-5"  id="nutritionTable2">
                                   
                                </div>
                            </div>
	                  	</div>
					</div>
                          <div class="dividingline"></div>

                          <div class="m-t-10 m-b-10 text-left">
                              <span class="u-span-2 f-span-2 m-r-8">相似度</span>
                              <div class="dropdown-2" style="display: inline-block">
                                 <input id="percent" type="text" value="80"/>%
                              </div>
                          </div>
                      </div>

                      <button type="button" class="u-button1 f-button-1 m-t-12" style="width:30%;" onclick="querySimilaryFood()">查询</button>
                      <div class="m-t-10 m-b-10 text-center hide" id="similaryError">
                              <span class="u-span-2 f-span-2 m-r-8" style="color: #FF0000;">请输入</span>
                      </div>
                  </div>

                  <div class="col-xt-4">
                      <div class="main-border p-l-12">
                          <div class="m-head">
                              <p class="u-title-1 text-left" id="simalaryResultTitle">相似食物查询结果</p>
                          </div>
                          <div class="dividingline"></div>

                          <div class="m-t-10" id="similaryQueryFood">
                              
                          </div>
                      </div>
                  </div>
                 </div>
             </div>
</div>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/similarity_query.js"></script>
</body>
</html>