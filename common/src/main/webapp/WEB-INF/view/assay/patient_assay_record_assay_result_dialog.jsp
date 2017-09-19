<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal fade" id="assayResultDialog" role="dialog" aria-hidden="true" >
<form action="" onsubmit="return false;" id="assayResultForm">
    <input type="hidden" value="${patientId}" name="fkPatientId" id="fkPatientId" />
    <input type="hidden" value="" name="id" id="id" />
    <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header dialog-header">
                    <span class="modal-title modal-title2 fs-16-imp">
                        传染病标识
                    </span>
                </div>
    
                <div class="modal-body" style="width: 100%">
                    <div class="m-list u-borer-not line-35 m-t-30 text-center">
                        <label class="itemcheckbox">
                        <input  type="checkbox" name="normal" value="true" data-text="正常">
                            正常
                        </label>
                        <label class="itemcheckbox">
                            <input  type="checkbox"  name="hav" value="true" data-text="甲肝">
                            甲肝
                        </label>
                        <label class="itemcheckbox">
                            <input  type="checkbox" name="hbv" value="true" data-text="乙肝">
                            乙肝
                        </label>
                        <label class="itemcheckbox">
                            <input  type="checkbox" name="hcv" value="true" data-text="丙肝">
                            丙肝
                        </label>
                    </div>
                    <div class="m-list u-borer-not line-35 m-t-10 text-center" >
                        <label class="itemcheckbox">
                            <input  type="checkbox" name="hev" value="true" data-text=" 戊肝">
                            戊肝
                        </label>
                        <label class="itemcheckbox">
                            <input  type="checkbox" name="hiv" value="true" data-text="HIV">
                            HIV
                        </label>
                        <label class="itemcheckbox">
                            <input  type="checkbox" name="hsv" value="true" data-text="梅毒">
                            梅毒
                        </label>
                        <label class="itemcheckbox">
                            <input  type="checkbox" name="unknown" value="true" data-text="未知">
                            未知
                        </label>
                    </div>
                    <div class="m-list u-borer-not line-35 text-center" id="showError">
                    
                    </div>
                </div>
                <div class="modal-footer dialog-footer" style="height: 60px !important;">
                    <div class="right m-t-14">
                        <button type="button" class="btn btn-can dialog-buttontype" data-dismiss="modal">取消</button>
                        <button id="finish" type="button" class="btn btn-def dialog-buttontype m-r-24" onclick="saveAssayResult(this);">确定</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    $(function () {
        // show dialog

        // 设置dialog离顶部高度
       /*  $("#dialog").css("margin", "30px auto");
        //设置dialog宽度为80%
        $("#dialog .modal-dialog").css("width", "450px");
        //设置dialog 内容高度
        var dialog_height = 240;
        $("#dialog .modal-content").css("height", dialog_height+16+ "px"); */
    });
</script>