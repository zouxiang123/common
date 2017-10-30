<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>诊断信息</title>
<%@ include file="../common/head_standard.jsp"%>
</head>
<body class="fc-48">
    <input type="hidden" id="patientId" value="${patientId }" />
    <div class="mt-12 ml-12 diagnosis-btns"></div>
    <div class="diagnosis-content">
        <div id="medical_history" class="mt-12 position-relative diagnosis-card" >
            <jsp:include page="diagnosis_info_medical_history2.jsp" flush="true"></jsp:include>
        </div>
        <div id="clinical_diagnosis" class="mt-12 position-relative diagnosis-card">
            <jsp:include page="diagnosis_info_clinical_diagnosis.jsp" flush="true"></jsp:include>
        </div>
        <div id="pathologic_diagnosis" class="mt-12 position-relative diagnosis-card">
            <jsp:include page="diagnosis_info_pathologic_diagnosis.jsp" flush="true"></jsp:include>
        </div>
        <div id="ckd_aki" class="mt-12 position-relative diagnosis-card">
            <jsp:include page="diagnosis_info_ckd_aki.jsp" flush="true"></jsp:include>
        </div>
        <div id="cure_complication" class="mt-12 position-relative diagnosis-card">
            <jsp:include page="diagnosis_info_cure_complication.jsp" flush="true"></jsp:include>
        </div>
        <div id="other_diagnosis" class="mt-12 position-relative diagnosis-card">
            <jsp:include page="diagnosis_info_other_diagnosis.jsp" flush="true"></jsp:include>
        </div>
        <div id="diagnosis_tab" class="mt-12 diagnosis-card">
            <jsp:include page="diagnosis_info_diagnosis_tab.jsp" flush="true"></jsp:include>
        </div>
    </div>
    <script type="text/javascript">
    var patientHasOutCome = ${patient.delFlag};
    </script>
    <script src="${ctx }/assets/js/diagnosis/diagnosis_new1.js?version=${version}"></script>
<%--     <jsp:include page="dialog/diagnosis_hist_first_dialysis.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_surgery.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_hd.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_pd.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_kt.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_allergy.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_pestilence.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_tumour.jsp" flush="true"></jsp:include> --%>
</body>
</html>