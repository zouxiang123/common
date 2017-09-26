<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal fade" id="addPatientAssay" role="dialog" aria-hidden="true">
    <input id="saveType" type="hidden" value="save">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header dialog-header">
                <h4 class="modal-title modal-title2 " id="dialogTitle">添加化验项信息</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png">
                </div>
            </div>
            
             <form id="addAssay">
            <div class="modal-body">
               <div class="p-l-20 p-r-20" id="modalbody" style="overflow-x: hidden;overflow-y: auto">
                   <div class=" p-t-20">
                       <span>化验单：</span>
                       <select class="border-gray" style="width:120px;height: 28px" id="groupId">
                       </select>
                       <span class="m-l-30">日期：</span>
                       <input id="assayDaye" name="assayDaye"  type="text" style="width: 120px;height: 28px" class="border-gray text-center">
                   </div>

                   <table class="u-table u-table-bordered m-t-16 m-b-10">
                       <thead id="thValue">
                       </thead>
                       <tbody id="trValue">
                      
                       </tbody>
                   </table>
               </div>
            </div>

            <div class="modal-footer dialog-footer" style="height: 60px !important;">
                <div class="right m-t-14">
                    <button type="button" class="btn btn-can dialog-buttontype" data-dismiss="modal">取消</button>
                    <button id="finish" type="button" class="btn btn-def dialog-buttontype m-r-24" onclick="insertPatientAssay();">保存</button>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(function () {
        // 设置dialog离顶部高度
        $("#addPatientAssay").css("margin", "30px auto");
        //设置dialog宽度为80%
        $("#addPatientAssay .modal-dialog").css("width", "700px");
        //设置dialog 内容高度
        var dialog_height = 535;
        $("#addPatientAssay .modal-content").css("height", dialog_height + "px");
        $("#addPatientAssay #modalbody").css("height", (dialog_height - 118) + "px");
    });
</script>