<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宣教类别</title>
<%@ include file="../../common/head_standard.jsp"%>
</head>
<body>
<div id="allContent">
    <div class="">
        <jsp:include page="serach_common.jsp" flush="false"></jsp:include>
        <div class="frame-content u-clear-scroll">
            <div class="u-table-fixed mt-10">
                <div class="u-table-fixed-head">
                  <table class="u-table u-table-striped u-table-bordered">
                      <thead>
                        <tr>
                          <th class="xt-4">宣教内容</th>
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
    </div>
</div>
<script src="${ctx }/assets/js/report/propaganda_report.js?version=${version}"></script>
<script type="text/javascript">
$('#assessmentDiv').hide();
$('#propagandaObjectDiv').hide();
$(function(){
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
})
</script>
</body>
</html>