<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>食谱查询</title>
<%@ include file="../common/head_new.jsp"%>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/food_query.js"></script>
</head>
<body>
	<div class="col-xt-12">
                <div class="col-xt-3">
                    <div class="g-main" style="margin-top: 12px;padding-right: 12px;">
                        <div class="g-mainc-1 center-block bg-white main-border clearfix">
                            <div class="m-head">
                                <p class="u-title-1 text-center">类别与名称</p>
                            </div>
                            <input type="text" class="u-input f-input-2 center-block m-t-4 m-b-8" placeholder="搜索" id="searchFood"/>
                            <div class="fill-parent" style="max-height: 600px;overflow-y: scroll;overflow-x: hidden">
                                <div class="fill-parent" id="foodUl">
								    <c:forEach items="${foods }" var ="obj">
								    	<div class="f-list-item" data-value="${obj.id }" id="${obj.id }" onclick="queryFoodAndNutrition(this)">
								    		${obj.foodName }
								    		<span class="hide">${obj.spellInitials }</span>
								    	</div>
									</c:forEach>
						    	</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xt-9">
                    <div class="g-main" style="margin-top: 12px;">
                        <div class="g-mainc-1 center-block bg-white main-border clearfix rel" style="padding-bottom: 40px;">
                            <div class="m-head">
                                <p class="u-title-1 text-center">食物营养成分<span class="u-span-3">（每100g含量）</span></p>
                            </div>
                            <div class="dividingline"></div>

                            <div style="width: 50%;float:left;padding-right: 20px">
                                <div class="m-t-10" id="loadInit">
                                </div>
                            </div>

                            <div style="width: 50%;float:left;padding-right: 20px">
                                <div class="m-t-10" id="loadInit2">
                                </div>
                            </div>

                            <span class="f-span-6" style="position: absolute;bottom:10px;left:24px;">符号说明：kal 卡、g 克、mg 毫克、ug 微克</span>
                        </div>
                    </div>
                </div>
            </div>
</body>
</html>