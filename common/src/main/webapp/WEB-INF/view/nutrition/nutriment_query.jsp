<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/head_new.jsp"%>
<title>营养含量</title>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/nutriment_query.js"></script>
</head>
<body>
	      <div class="g-main" style="margin-top: 39px;">
                <div class="g-mainc-1 center-block bg-white main-border">
                    <div class="m-head">
                        <p class="u-title-1 text-left">选择类型</p>
                    </div>
                    <div class="dividingline"></div>

                    <div class="m-t-10">
                        <div class="m-content" id="nutritionCatogery">
                        </div>
                    </div>
                </div>
           </div>

           <div class="g-main" style="margin-top: 12px;">
                <div class="g-mainc-1 center-block bg-white main-border clearfix">

                    <div style="width: 50%;float:left;padding-right: 20px">
                        <div class="m-head clearfix">
                            <p class="u-title-1 text-left pull-left">含量较多食物</p>
                            <span class="u-span-2 f-span-2 pull-right m-r-8 m-t-4">条记录</span>
                            <div class="dropdown-2 pull-right m-t-6" style="display: inline-block">
                               <input id="record" type="text" value="20" onchange="selectNutritionDescNone()">
                            </div>
                            <span class="u-span-2 f-span-2 pull-right m-r-8 m-t-4">选取前</span>
                        </div>
                        <div class="dividingline"></div>

                        <div class="m-t-10" id="moreTable">

                        </div>
                    </div>

                    <div style="width: 50%;float:left;padding-right: 20px">
                        <div class="m-head clearfix">
                            <p class="u-title-1 text-left pull-left">含量较少食物</p>
                            <span class="u-span-2 f-span-2 pull-right m-r-8 m-t-4">条记录</span>
                            <div class="dropdown-2 pull-right m-t-6" style="display: inline-block">
                                <input id="records" type="text" value="20" onchange="selectNutritionAscNone()">
                            </div>
                            <span class="u-span-2 f-span-2 pull-right m-r-8 m-t-4">选取前</span>
                        </div>
                        <div class="dividingline"></div>

                        <div class="m-t-10" id="littleTable">

                        </div>
                    </div>
                </div>
            </div>
</body>
</html>