<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>患者标签</title>
<style type="text/css">
.positive {
    width: 24px;
    height: 16px;
    margin-right: 6px;
    margin-bottom: 1px;
}
.bed-user-name {
    font-size: 18px;
    margin-top: -6px;
}
.head-fixed{
  position:fixed;
  z-index:99;
  width:100%;
  background:#ffffff;
}
.navbar-item-img {
    width: 32px;
    height: 32px;
    margin:0;
    padding:0;
    margin-top: 10px;
    margin-right: 20px;
    -webkit-filter: invert(0.7);
    -moz-filter: invert(0.7);
    -o-filter: invert(0.7);
    -ms-filter: invert(0.7);
    filter: invert(0.7);
}

.u-category span {
    margin-bottom: 10px;
}
.u-pop-search{
    position: absolute;
    top:37px;
    right: 0px;
    width: 590px;
    background: #ffffff;
    border:1px solid #d9e0e6;
    z-index: 99;
    box-shadow: 0px 5px 8px rgba(0,0,0,0.15);
    border-radius: 4px;
    font-size: 22px;
}
.u-pop-search>img:first-child{
    position: absolute;
    top: -20px;
    right: 30px;
}
.u-search-text{
    padding:0px 20px;
    height: 80px;
    line-height: 80px;
}
.u-seek-select>span{
    height: 50px;
    line-height: 50px;
    padding: 0px 18px;
    border-radius: 50%;
    display: block;
    float: left;
    cursor: pointer;
    margin: 3px;
}
.u-seek-select>span:active{
    background: #31aaff;
    color: #ffffff;
    opacity: 1;
}

.titlefixed{
    position: fixed;
    top: 74px;
    left: 0px;
    z-index: 97;
    margin-top: -10px;
    padding-top:10px;
}
.navbar-item-img {
    width: 32px;
    height: 32px;
    margin:0;
    padding:0;
    margin-top: 10px;
    margin-right: 20px;
    -webkit-filter: invert(0.7);
    -moz-filter: invert(0.7);
    -o-filter: invert(0.7);
    -ms-filter: invert(0.7);
    filter: invert(0.7);
    margin-top: -5px;
    margin-right: 15px;
}
#big_search_div .search-item {
    width: 60px;
    float: left;
}
</style>
</head>
<body style="padding-top: 0px;">
    <div class="container-fluid" style="padding-left:0;padding-right:0;">
        <div class="row">
            <div class="col-sm-12 col-md-12 bg-white" style="padding-bottom: 0px;">
                <div class="head-fixed" id="headDiv">
                    <!-- 头部search搜索 -->
                    <div id="topTableCustom">
                        <div class="navbar-item hide" id="big_search_navbar">
                            <img src="${ctx }/assets/img/a-z.png" class="navbar-item-img" onclick="toggleSearchDiv(event);"><span class="navbar-item-span">&nbsp;</span>
                        </div>
                        <!-- 点击搜索弹出框内容 start -->
                        <div class="w-10 p-12 hide" id="big_search_div">
                            <div class="u-pop-search u-pop-up-time fs-20 my-toggle" style="right: 20px; top: 60px;">
                                <img src="${ctx }/assets/img/s1110.png" alt="" style="margin-right: 0px;">
                                <div class="text-center f-br-bottom w-10">
                                    <div class="m-auto h-60 search-items" style="width: 60px;">
                                        <div class="search-item p-relative">
                                            <span>姓名</span>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="f-br-bottom">
                                        <div class="u-seek-select clearfix" id="init_zi_mu_div"></div>
                                    </div>
                                    <div class="h-60 w-10 p-l-20 p-r-20">
                                        <div class="u-cox-6">
                                            <span class="fc-deepgrey f-opa-5">姓名：</span><span id="search_result_span"></span>
                                        </div>
                                        <div class="u-cox-6 text-right">
                                            <img src="${ctx }/assets/img/clear-round-blue.png" width="30" height="30" onclick="delLastInit(event);" class="hand"> 
                                            <img src="${ctx }/assets/img/delete-round-red.png" width="30" height="30" class="m-l-10 hand" onclick="toggleSearchDiv(event);">
                                        </div>
                                    </div>
                                </div>
                                <!-- 姓名End -->
                            </div>
                        </div>
                        <!-- 点击搜索弹出框内容 end -->
                    </div>
                    <div style="margin-right:15px">
                        <label class="xt-check-label2 pull-right" style="margin-top:15px"; id="label_batch_btn">批量</label>
                        <label class="xt-check-label2 pull-right" style="margin-top:15px"; onclick="patient_label.showAddDialog()" id="label_manage_btn">标签管理</label>
                    </div>
                    <div class="fill-parent bg-white" style="padding: 15px 16px 0px 20px; width: 88%;min-height: 40px;" id="label_check">
                    </div>
                </div>

                <div class="col-sm-12 col-md-12" id = "patient_label_div" style="padding-top: 97px; padding-bottom: 80px;padding-left:15px;padding-right:0;">
                 <div class="fill-parent" id="label_patient_all">
                     <div class="list-card-item clearfix" id="load_patient_label">
                         
                     </div>
                 </div>
            
                 <div class="bottom-bar" id="label_batch_option" style="display: none;">
                     <div class="group-checkbox bottom-checkbox">
                         <input id="checkbox" type="checkbox" value="1">
                         <label for="checkbox" id="label_batch_option_count">全选(0人)</label>
                     </div>
            
                     <div class="fill-parent center margin-top-12" style="margin-left: -158px;">
                         <button type="button" class="btn btn-bottom-cancel" onclick="patient_label.cancelBatch()">取消</button>
                         <button type="button" class="btn btn-bottom-default" style="margin-left: 30px;" onclick="patient_label.showBatchSet()">批量添加</button>
                     </div>
                 </div>
                </div>
            </div>
        </div>
    </div>
</body>
<!-- 批量弹出框 -->
<div class="modal fade" id="dialogPatient" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <h4 class="modal-title modal-title2 " id="dialogPatient_name"></h4>
            </div>

            <div class="modal-body">
                <div class="dialog-wrap" style="width: inherit;">
                    <div class="list-group bg-white">
                        <div class="list-group-item margin-top-20">
                            <span class="list-group-item-title">已选择：</span> <span class="list-group-item-title" id="dialogPatient_label_name"></span>
                            <input name="patientIds" type="hidden">
                        </div>
                        <div class="dialog-item-line" style="height: 1px;"></div>
                        <div id="dialogPatient_label_check"></div>
                    </div>
                </div>
            </div>

            <div class="modal-footer dialog-footer">
                <div class="center">
                    <button type="button" class="btn btn-can dialog-button" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-def dialog-button" onclick="patient_label.saveRef('dialogPatient','dialogPatient_label_check')">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="dialog" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <h4 class="modal-title modal-title2 ">批量添加</h4>
            </div>
            <div class="modal-body">
                <div class="dialog-wrap" style="width: inherit;">
                    <input name="patientIds" type="hidden">
                    <div class="list-group bg-white">
                        <div class="list-group-item margin-top-20" style="height: 300px; overflow: auto;">
                            <span class="list-group-item-title">已选择：</span>
                            <div class="list-group-item-title" id="label_patient_batch_check_name"
                                style="word-wrap: break-word; white-space: normal; word-break: break-all">
                            </div>
                            <input id="label_patient_batch_check_id" type="hidden">
                        </div>
                        <div class="dialog-item-line" style="height: 1px;"></div>
                        <div id="label_patient_batch_check_label"></div>
                    </div>
                </div>
            </div>

            <div class="modal-footer dialog-footer">
                <div class="center">
                    <button type="button" class="btn btn-can dialog-button" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-def dialog-button" onclick="patient_label.saveRef('dialog','label_patient_batch_check_label')">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="dialogLabel" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <h4 class="modal-title modal-title2 ">标签管理</h4>
            </div>
            <div class="modal-body">
                <div class="dialog-wrap" style="width: inherit;">
                    <div class="list-group bg-white">
                        <div class="list-group-item margin-top-20 center">
                            <input type="search" class="dialog-search-input margin-bottom-10" placeholder="输入新增标签" id="template_dialogLabel_label_name" maxlength="10">
                            <button type="button" class="btn dialog-search-button margin-left-8" onclick="patient_label.saveLabel()">新增</button>
                            <br /> 
                            <span id="errorMsg_spanId" style="color: #F00"></span>
                        </div>
                        <div id="dialogLabel_context"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer dialog-footer">
                <div class="center">
                    <button type="button" class="btn btn-def dialog-button" data-dismiss="modal">返回</button>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="sysOwner" value="${sysOwner }" />
<input type="hidden" id="pd_addr" value="${pd_addr }"/>
<input type="hidden" id="pops_addr" value="${pops_addr }" />
<script type="text/javascript" src="${ctx }/framework/jquery/jquery.fastLiveFilter.js"></script>
<script type="text/javascript" src="${ctx }/assets/js/common/pagination.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx }/assets/js/patient/patient_label.js?version=${version}"></script>
<script type="text/javascript">
$(function(){
    $('#patient_label_div').css('padding-top',$('#headDiv').height()+1);
    $("#big_search_navbar").removeClass('hide');
    $("#big_search_navbar").css({"position": "absolute", "right": "160px", "top": "15px"});
    $("#big_search_div .u-pop-search").css({"right": "160px"});
    createInital();
    patient_label.init();
});
// 点击搜索图片按钮
function toggleSearchDiv(event) {
    $("#big_search_div").toggleClass("hide");
    event.stopPropagation();
}
// 初始化首患者字母
function createInital() {
 var html = '';
 for (var int = 65; int < 91; int++) {
     html += '<span onclick="initialClick(this,event);" data-value="' + String.fromCharCode(int) + '">' + String.fromCharCode(int) + '</span>';
 }
 $("#init_zi_mu_div").html(html);
}
 
// 点击患者首字母
function initialClick(element, event) {
    event.stopPropagation();
    // 在下面结果集中 展现 所点击首字母
    var searchResultInit = $("#search_result_span").html();
    $("#search_result_span").html(searchResultInit + $(element).attr("data-value"));
    patient_label.getPatient(true);
}
// 删除最后一个首字母
function delLastInit(event) {
    event.stopPropagation();
    var searchResultInit = $("#search_result_span").text();
    if (searchResultInit.length > 1) {
            $("#search_result_span").html(searchResultInit.substring(0, searchResultInit.length - 1));
        }
        patient_label.getPatient(true);
    }
</script>
</html>