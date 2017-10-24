<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="assayRecordDiaAbFlag" data-hide="#assayRecordDiaAbFlag">
    <input type="hidden" id="bakReqId" name="bakReqId" value=""> 
    <input type="hidden" id="bakDiaAbFlag" name="bakDiaAbFlag" value="">
    <input type="hidden" id="bakInspectionId" name="bakInspectionId" value="">
    <div class="u-dialog" min>
        <div class="u-dialog-header">
            <div></div>
            <div class="fs-18">是否透后血</div>
            <div></div>
        </div>
        <div class="u-dialog-content plr-0">
            <div class="pb-10 bb-dashed pl-40" id="diaAbFlagRadioDiv">
                <label class="u-radio">
                    <input type="radio" name="diaAbFlag" value="2">
                    <span class="icon-radio"></span>是
                </label>
                <label class="u-radio ml-20">
                    <input type="radio" name="diaAbFlag" value="1">
                    <span class="icon-radio"></span>否
                </label>
            </div>
            <div class="pl-40 mt-10">
                <label class="u-checkbox">
                    <input type="checkbox" name="isAllToHDAfter" id="isAllToHDAfter">
                    <span class="icon-checkbox"></span>是否将整个化验单更新为透后
                </label>
            </div>
        </div>
        <div class="u-dialog-footer" style="border-top: 1px solid #d9e0e6;">
            <button type="button" data-hide="#assayRecordDiaAbFlag">取消</button>
            <button type="button" class="u-btn-blue" onclick="updateDiaAbFlag();" fill>确定</button>
        </div>
    </div>
</div>