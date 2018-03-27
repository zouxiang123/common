<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<title>人体测量</title>
</head>
<body>
<form action="#" id="bodyMeasureForm" onsubmit="return false;">
    <input type="hidden" name="id" value="${record.id }"/>
    <input type="hidden" id="patientId" name="fkPatientId" value="${record.fkPatientId }"/>
    <input type="hidden" id="patientSex" value="${patient.sex }"/>
    <div class="nutrition-somatometry">
    <div class="u-list-text-three mt-4">
        <div class="left">
            <div>测量日期：</div>
            <div>
                <input type="text" name="recordDateShow" value="${record.recordDateShow }" readonly="readonly">
            </div>
        </div>
        <div class="center">
            <div>体重：</div>
            <div>
                <input type="text" value="<fmt:formatNumber value="${record.weight }" pattern='#.###' />" name="weight" maxlength="6" data-calcstature="bmi,bsa"/>
                <span class="fc-black_5 ml-4">kg</span>
            </div>
        </div>
        <div class="right">
            <div>身高：</div>
            <div>
                <input type="text" value="<fmt:formatNumber value="${record.stature }" pattern='#.###' />" name="stature" maxlength="6" data-calcstature="bmi,bsa">
                <span class="fc-black_5 ml-4">cm</span>
            </div>
        </div>
        <div class="w-100" data-error></div>
    </div>
    <div class="u-list-text mt-4">
        <div class="left">
            BMI：
        </div>
        <div class="right">
            <input type="hidden" name="bmi" value="${record.bmi }" readonly="readonly"/>
            <span data-bmispan><fmt:formatNumber value="${record.bmi }" pattern='#.###' /></span>
            <span class="fc-black_5 ml-14">kg/m²</span>
            <span class="fc-red ml-20" id="bmiTipsSpan"></span>
        </div>
    </div>
    <div class="u-list-text">
        <div class="left">
            BSA：
        </div>
        <div class="right">
            <input type="hidden" name="bsa" value="${record.bsa }" readonly="readonly"/>
            <span data-bsaspan="bsa"><fmt:formatNumber value="${record.bsa }" pattern='#.###' /></span>
            <span class="fc-black_5 ml-14">m²</span>
        </div>
    </div>
    <div class="u-list-text-three mt-8 u-border-t dashed pt-16">
        <div class="left">
            <div>上臀围：</div>
            <div>
                <input type="text" name="mac" maxlength="6" value="<fmt:formatNumber value="${record.mac }" pattern='#.###' />" data-calcmamc="mamc">
                <span class="fc-black_5 ml-4">cm</span>
            </div>
        </div>
        <div class="center">
            <div>三头肌皮褶厚度：</div>
            <div>
                <input type="text" name="tsf" maxlength="6" value="<fmt:formatNumber value="${record.tsf }" pattern='#.###' />" data-calcmamc="mamc">
                <span class="fc-black_5 ml-4">cm</span>
            </div>
        </div>
        <div class="right">
            <div>上臀肌围：</div>
            <div>
                <input type="hidden" name="mamc" value="${record.mamc }" readonly="readonly"/>
                <span data-mamcspan><fmt:formatNumber value="${record.mamc }" pattern='#.###' /></span>
                <span class="fc-black_5 ml-4">cm</span>
            </div>
        </div>
        <div class="w-100" data-error></div>
    </div>
    <div class="u-list-text-three mt-4">
        <div class="left">
            <div>腰围：</div>
            <div>
                <input type="text" name="waist" value="<fmt:formatNumber value="${record.waist }" pattern='#.###' />" maxlength="6" data-calcwhr="whr">
                <span class="fc-black_5 ml-4">cm</span>
            </div>
        </div>
        <div class="center">
            <div>臀围：</div>
            <div>
                <input type="text" name="hip" value="<fmt:formatNumber value="${record.hip }" pattern='#.###' />" maxlength="6" data-calcwhr="whr">
                <span class="fc-black_5 ml-4">mm</span>
            </div>
        </div>
        <div class="right">
            <div>腰臀比：</div>
            <div>
              <span data-whrspan><fmt:formatNumber value="${record.whr }" pattern='#.###' /></span>
              <input type="hidden" name="whr" value="${record.whr }" readonly="readonly"/>
              <span class="fc-black_5 ml-4">%</span>
              <span class="fc-red ml-20" id="whrTipsSpan"></span>
            </div>
        </div>
        <div class="w-100" data-error></div>
    </div>
    <div class="u-list-text-three mt-4">
        <div class="left">
            <div>肩胛下皮褶厚度：</div>
            <div>
                <input type="text" name="ssf" value="<fmt:formatNumber value="${record.ssf }" pattern='#.###' />" maxlength="6">
                <span class="fc-black_5 ml-4">cm</span>
            </div>
        </div>
        <div class="center">
            <div>腹部皮褶厚度：</div>
            <div>
                <input type="text" name="asf" value="<fmt:formatNumber value="${record.asf }" pattern='#.###' />" maxlength="6">
                <span class="fc-black_5 ml-4">cm</span>
            </div>
        </div>
        <div class="w-100" data-error></div>
    </div>
</div>
</form>
</body>
<script type="text/javascript" src="${ctx }/assets/js/nutrition/body_measure_edit.js?version=${version}"></script>
<script type="text/javascript">
$(function(){
  body_measure_edit.init();
});
</script>
</html>
