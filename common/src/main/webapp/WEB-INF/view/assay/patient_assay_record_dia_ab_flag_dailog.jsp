<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal fade" id="assayRecordDiaAbFlag" role="dialog" aria-hidden="true">
    <input type="hidden" id="bakReqId" name="bakReqId" value=""> 
    <input type="hidden" id="bakDiaAbFlag" name="bakDiaAbFlag" value="">
    <input type="hidden" id="bakInspectionId" name="bakInspectionId" value="">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <span class="modal-title modal-title2 fs-16-imp"> 是否透后血 </span>
            </div>
            <div class="modal-body" style="width: 100%">
                 <div class="m-list u-borer-not line-35 m-t-10  p-l-32 bb-dashed p-b-44" id="diaAbFlagRadioDiv">
                    <label> <input type="radio" name="diaAbFlag" value="2"> 是
                    </label> <label> <input type="radio" name="diaAbFlag" value="1"> 否
                    </label>
                </div>
            </div>
            <div class="modal-body" style="width: 100%">
                <div class="m-list u-borer-not line-35 m-t-10 p-l-32">
                    <label> <input type="checkbox" name="isAllToHDAfter" id="isAllToHDAfter"> 是否更新整个化验单
                    </label>
                </div>
            </div>
            <div class="modal-footer dialog-footer" style="height: 60px !important;">
                <div class="right m-t-14">
                    <button type="button" class="btn btn-can dialog-buttontype" data-dismiss="modal">取消</button>
                    <button id="finish" type="button" class="btn btn-def dialog-buttontype m-r-24" onclick="updateDiaAbFlag();">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        // 设置dialog离顶部高度
        $("#assayRecordDiaAbFlag").css("margin", "50px auto");
        //设置dialog宽度为80%
        $("#assayRecordDiaAbFlag .modal-dialog").css("width", "300px");
        //设置dialog 内容高度
        var dialog_height = 220;
        $("#assayRecordDiaAbFlag .modal-content").css("height", dialog_height+16+ "px");
    });
</script>