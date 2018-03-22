<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>患者</title>
<%@ include file="../../common/head_standard.jsp"%>
<script src="${COMMON_SERVER_ADDR }/assets/js/common/selectSearch.js?version=${version}"></script>
<script src="${COMMON_SERVER_ADDR}/framework/jquery/jquery.fastLiveFilter.js"></script>
</head>
<body>
<div id="allContent">
    <div class="">
        <div style="position:absolute;left:12px;z-index:99;top:4px">
        <form id="patientQueryFrom" onsubmit="return false;">
            <input type="hidden" id="pageNo" name="pageNo" value="1">
            <input type="hidden" id="pageSize" name="pageSize" value="25">
            <input type="hidden" id="totalPage" name="totalPage" value="0">
            <input type="hidden" id="totalRecord" name="totalRecord" value="0">
            <label class="u-prompt-text icon-input" id="patientDiv">
                <input type="text" id="patientSearch" name="patientName" placeholder="请输入搜索内容" value="${entity.patientName}">
                <ul hidden id="patientList"><c:forEach items="${patientList }" var="entity">
                    <li data-patient-id="${entity.id }" data-patient-name="${entity.name }">
                    <span class="text-ellipsis" style="width:60px">${entity.name }</span>    
                    <span style="display: none">${entity.spellInitials }</span>
                    <span class="u-float-r mt-8 ml-2 text-ellipsis" style="width:40px"><c:if test="${entity.assaylist != null and entity.assaylist.size() > 0 }">
                        <c:forEach items="${entity.assaylist}" var="assay" varStatus="status"><c:out value="${fn:substring(assay, 0, 1)}" /><c:if test="${status.index > 0}">/</c:if></c:forEach>
                    </c:if></span>
                    </li></c:forEach>
                </ul>
            </label>
        </form>
        </div>
        <jsp:include page="serach_common.jsp" flush="false"></jsp:include>
        <div class="frame-content u-clear-scroll">
            <div class="u-table-fixed mt-10">
                <div class="u-table-fixed-head">
                  <table class="u-table u-table-bordered">
                      <thead>
                        <tr>
                          <th class="xtt-6">排序</th>
                          <th class="xtt-20">患者姓名</th>
                          <th>宣教总数</th>
                          <th class="xtt-10">操作</th>
                        </tr>
                      </thead>
                  </table>
                </div>
                <div class="u-table-fixed-body" id="tableBody">
                  
                </div>
            </div>
        </div>
        <div style="bottom: -4px;margin-left:30%;position: fixed">
            <div id="dataPaging"></div>
        </div>
    </div>
</div>
<script src="${ctx }/assets/js/report/propaganda_report.js?version=${version}"></script>
<script>
    $(function(){
        $('#patientSearch').fastLiveFilter('#patientList');// 注册患者检索事件
        // 点击患者事件
        $("#patientList").on("click", "li", function(e) {
            $('#patientSearch').val($(this).attr("data-patient-name"));
            propagandaReport.resetPage();
            propagandaReport.selectPropagandaReport();
            e.preventDefault();
        });

        // 点击患者搜索输入框
        $("#patientSearch").click(function(e) {
            e.stopPropagation();
            $(this).siblings("ul").show();
        });
        $("#patientSearch").bind("keydown", function(e) {
            e = e || event;
            if (e.keyCode == 13) {
                propagandaReport.resetPage();
                propagandaReport.selectPropagandaReport();
                $('#patientList').hide();
            }
        });
        $("body").click(function(e) {
            $('#patientList').hide();
        });
        $('#assessmentDiv').hide();
        $('#propagandaObjectDiv').hide();
        
        layui.use('laydate', function() {
            // ie11下日期弹不出来
            $('#startDate').attr('lay-key', '100');
            $('#endDate').attr('lay-key', '101');
            var laydate = layui.laydate;
            laydate.render({
                elem : '#startDate',
                theme : '#31AAFF',
                btns : [ 'clear', 'confirm' ]
            });
            laydate.render({
                elem : '#endDate',
                theme : '#31AAFF',
                btns : [ 'clear', 'confirm' ]
            });
        });
        $("#tableBody").css({
            "max-height" : $(window).height() - $("#tableBody").offset().top - 50
        });
    });
</script>
</body>
</html>