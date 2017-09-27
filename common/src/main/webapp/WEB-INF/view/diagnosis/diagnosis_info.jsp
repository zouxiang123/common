<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>诊断信息</title>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/assets/css/global.css">
<link rel="stylesheet" type="text/css" href="${ctx }/assets/css/index.css">
<link rel="stylesheet" href="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.css">
<script type="text/javascript" src="${ctx }/framework/moment/moment.min.js"></script>
<script type="text/javascript" src="${ctx }/framework/moment/locale/zh-cn.js"></script>
<script type="text/javascript" src="${ctx }/framework/bootstrap/daterangepicker/daterangepicker.js"></script>
<style type="text/css">
/* */
.diagnosis-content>.diagnosis-card {
    display: none;
}

label+label {
    margin: 0px 5px;
    margin-top: 0px;
    margin-bottom: 0px;
}

.u-tree>li>span {
    margin-top: -32px;
}
</style>
</head>
<body class="bg-white">
    <input type="hidden" id="patientId" value="${patientId }" />
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12 col-md-12 main" style="padding: 10px 20px 20px 20px;">
                <div class="m-t-7 clearfix diagnosis-btns"></div>
                <div class="f-both m-t-10 diagnosis-content">
                    <div id="medical_history" class="diagnosis-card">
                        <jsp:include page="diagnosis_info_medical_history.jsp" flush="true"></jsp:include>
                    </div>
                    <div id="clinical_diagnosis" class="diagnosis-card">
                        <jsp:include page="diagnosis_info_clinical_diagnosis.jsp" flush="true"></jsp:include>
                    </div>
                    <div id="pathologic_diagnosis" class="diagnosis-card">
                        <jsp:include page="diagnosis_info_pathologic_diagnosis.jsp" flush="true"></jsp:include>
                    </div>
                    <div id="ckd_aki" class="diagnosis-card">
                        <jsp:include page="diagnosis_info_ckd_aki.jsp" flush="true"></jsp:include>
                    </div>
                    <div id="cure_complication" class="diagnosis-card">
                        <jsp:include page="diagnosis_info_cure_complication.jsp" flush="true"></jsp:include>
                    </div>
                    <div id="other_diagnosis" class="diagnosis-card">
                        <jsp:include page="diagnosis_info_other_diagnosis.jsp" flush="true"></jsp:include>
                    </div>
                    <div id="diagnosis_tab" class="diagnosis-card">
                        <jsp:include page="diagnosis_info_diagnosis_tab.jsp" flush="true"></jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    var patientHasOutCome = ${patient.delFlag};
    </script>
    <script src="${ctx }/assets/js/common/dialog/dialog_common.js?version=${version}"></script>
    <script src="${ctx }/assets/js/diagnosis/diagnosis_new.js?version=${version}"></script>
    <jsp:include page="dialog/diagnosis_hist_first_dialysis.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_surgery.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_hd.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_pd.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_kt.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_allergy.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_pestilence.jsp" flush="true"></jsp:include>
    <jsp:include page="dialog/diagnosis_hist_tumour.jsp" flush="true"></jsp:include>
</body>
</html>