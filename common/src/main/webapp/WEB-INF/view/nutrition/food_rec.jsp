<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<%@ include file="../common/head_standard.jsp"%>
<link href="${ctx }/framework/jquery/selectpage/selectpage.css" rel="stylesheet" />
<style type="text/css">
    div.sp_clear_btn {
        display: none
    }
</style>
</head>
<body>
<input type="hidden" id="proteinKey" value="${protein }"/>
<input type="hidden" id="energyKey" value="${energy }"/>
<form action="#" onsubmit="return false;" id="foodRecForm">
    <input type="hidden" name="id" value="${id }"/>
    <input type="hidden" name="fkPatientId" value="${patientId }"/>
<div class="new-assess">
    <div class="new-assess-card">
        <div class="head">记录日期</div>
        <div class="content">
            <div class="list mt-8 mb-8">
                <div class="u-xt-6">
                                    记录日期：<input type="text" name="recordDateShow" readonly="readonly" datepicker/>
                </div>
                <div data-error></div>
            </div>
        </div>
    </div>
    <div class="new-assess-card">
        <div class="head">饮食回顾</div>
        <div class="content pt-6">
            <div id="foodNormalDiv"></div>
            <div data-error></div>
            <div class="u-list-text">
                <div class="left"></div>
                <div class="right">
                    <button type="button" class="u-btn-blue custom" fill style="top: -10px;" onclick="food_rec.addRec('foodNormalDiv','normal')">新增饮食回顾</button>
                </div>
            </div>
            <div class="new-diet-reviews pb-12">
                <div class="head">食物营养参考摄入总量：</div>
                <div class="content" id="foodNormalComponentTotalDiv"></div>
            </div>
        </div>
    </div>
    <div class="new-assess-card">
        <div class="head">饮食推荐</div>
        <div class="content pt-6" id="foodRecomDiv">
        </div>
         <div data-error></div>
    </div>
</div>
</form>
</body>
<script src="${ctx }/framework/jquery/selectpage/selectpage.min.js"></script>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/food_rec.js?version=${version}"></script>
</html>