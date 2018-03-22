<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/head_standard.jsp"%>
<title>宣教统计报表</title>
</head>
<body style="overflow: hidden">
<div class="bed-head pb-4" style="border-bottom: 0px !important;" >
    <div id="reportDateDiv">
        <div class="tab-head-text tab-leftsetting" id="tabsDiv">
            <span class="mt-10" data-url="${ctx}/report/propaganda/doctorAndNurse.shtml?noStack=1" data-target="propaganda_report_doctor_nurse" data-refresh="0" data-fixed="1">医护工作量</span>
            <span class="mt-10" data-url="${ctx}/report/propaganda/patient.shtml?noStack=1" data-target="propaganda_report_patient" data-refresh="0" data-fixed="1">患者</span>
            <span class="mt-10" data-url="${ctx}/report/propaganda/category.shtml?noStack=1" data-target="propaganda_report_category" data-refresh="0" data-fixed="1">宣教类别</span>
        </div>
        <div class="m-b-10" id="tabsBodyDiv" style="margin-right: -15px;margin-left: -12px;"></div>
        <div class="col-sm-12 hide" data-tabdiv style="padding: 0px;" id="basicIframeDiv">
           <iframe src="" frameborder="no" width="100%" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
        </div>
    </div>
</div>
<script src="${COMMON_SERVER_ADDR}/assets/js/common/tab_nav2.js?version=${version }"></script>
<script type="text/javascript">
    $(function(){
        tab_nav.init();
    });
</script>
</body>
</html>