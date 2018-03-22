<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表统计-宣教类别-内容详情</title>
<%@ include file="../../common/head_standard.jsp"%>
</head>
<body>
<div id="allContent">
    <div class="">
        <jsp:include page="serach_common.jsp" flush="false"></jsp:include>
        <div class="frame-content u-clear-scroll">
            <div class="u-table-fixed mt-10">
                <div class="u-table-fixed-head">
                  <table class="u-table u-table-bordered">
                      <thead>
                        <tr>
                          <th class="xtt-14">宣教日期</th>
                          <th class="xtt-12">宣教患者</th>
                          <th class="xtt-14">宣教对象</th>
                          <th class="">宣教评价</th>
                          <th class="xtt-12">宣教人</th>
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