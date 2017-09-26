<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal dialog-full-screen" id="assayHistoryDialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog fill-parent">
        <div class="modal-content">
            <div class="modal-header dialog-header" id="searchAssayHistory">
                <div class="dialog-close pull-left" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png">
                </div>
                <h4 class="modal-title modal-title2 " id="assayHistoryTitle">历史数据</h4>
                <button class="button-fff pull-right" onclick="showDateSelect()" style="margin-right: 90px;">报表设置</button>

                <!--这里是提示框-->
                <div class="u-tip" style="top: 53px;right: 16px; min-width: 330px; display:none" id='dateSelect'>
                    <!--这个style里面的top和right是可以调的，使用时可能会有些不准-->
                    <img src="${ctx }/assets/img/s1110.png" alt="" style="position: absolute;top: -20px;right: 30px;">
                    <!--s1110.png在eclipse上是有的-->
                    <div class="m-l-16 m-t-16">
                        <label class="u-radio">
                            <input type="radio" name="time" value="w">
                            最近一周
                        </label>
                        <label class="u-radio m-l-20">
                            <input type="radio" name="time" value="m">
                            最近一月
                        </label>
                        <label class="u-radio m-l-20">
                            <input type="radio" name="time" value="y">
                            最近一年
                        </label>
                    </div>

                    <div class="m-t-10 m-l-10">
                        <input type="text" style="width: 140px;"
                               class="border-gray p-t-5 p-b-5 m-r-8 m-l-8 text-center line-hei26" name="startDate">
                        <span>至</span>
                        <input type="text" style="width: 140px"
                               class="border-gray p-t-5 p-b-5 m-l-8 text-center line-hei26" name="endDate" >
                    </div>
                    <div class="right m-t-14 m-b-14 p-t-14 border-top-line">
                        <button type="button" class="btn btn-can dialog-buttontype line-hei26" onclick="hiddenDateSelect()">取消</button>
                        <button id="finish" type="button" class="btn btn-def dialog-buttontype m-r-24 line-hei26"  onclick="hiddenDateSelect();">确定
                        </button>
                    </div>
                </div>
                <!--这里是提示框-->
            </div>

            <div class="modal-body">
                <div class="dialog-wrap">
                    <div class="list-group bg-white m-l-16 m-r-16">
                        <label class="u-checkbox float-right " style="position: relative; top: 31px;margin-right: 100px; z-index: 10;">
                            <input type="checkbox" id="shownum" name="num" checked="checked">显示统计数据
                        </label>
                        <div id="assayHistoryChart" class="m-t-10" style="height: 250px;margin-right:60px;position:relative"></div>
                        <div class="m-r-30 m-t-30">
                            <table class="u-table u-table-bordered m-t-16">
                                <thead>
                                <tr>
                                    <th style="width: 50%">检查时间</th>
                                    <th style="width: 50%">检查结果</th>
                                </tr>
                                </thead>
                                <tbody id="assayHistoryTable">
                                
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#assayHistoryDialog .modal-content").css("height", $(window).height() + "px");
        $("#assayHistoryDialog .dialog-wrap .list-group").css("height", ($(window).height() - 118) + "px");
    });
    function searchAssayHistory(){
        $("#searchAssayHistory").hide();
    }
    //控制数据设置的
    function showDateSelect(){
        $("#dateSelect").show();
    }
    function hiddenDateSelect(){
        showAssayHistoryReport();
        $("#dateSelect").hide();
    }
</script>