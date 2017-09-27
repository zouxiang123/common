<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal" id="diagnosisHistSurgeryDialog" role="dialog" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <img id="diagnosisHistSurgery_patientImage" src="${ctx }/assets/img/default-user.png"
                     class="user-photo">
                <span class="user-name" id="diagnosisHistSurgery_patientName"></span>
                <h4 class="modal-title modal-title2 ">手术史</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img
                        src="${ctx }/assets/img/dialog-new-close.png"></div>
            </div>
            <!-- cache -->
            <form action="#" id="diagnosisHistSurgeryForm" onsubmit="return saveDiagnosisHistSurgery(this);">
                <input type="hidden" name="id"/>
                <input type="hidden" name="fkPatientId" id="diagnosisHistSurgery_patientId"/>
                <div class="modal-body">
                    <div class="dialog-wrap">
                        <div class="list-group bg-white layerNode">
                            <div class="list-group-item">
                                <span class="list-group-item-title">手术日期：</span>
                                <input type="text" class="input-style" id="diagnosisHistSurgery_surgeryDateForm"
                                       name="surgeryDateForm" onfocus="addDate(this)" readonly placeholder="手术日期"/>
                            </div>
                            <div class="list-group-item">
                                <span class="list-group-item-title">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
                                <input type="text" class="input-style" id="diagnosisHistSurgery_surgeryName"
                                       name="surgeryName" maxlength="30" placeholder="名称"/>
                            </div>
                            <div class="list-group-item">
                                <span class="list-group-item-title">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
                                <textarea class="form-control" id="diagnosisHistSurgery_remark" name="remark"
                                          maxlength="256"></textarea>
                            </div>
                            <div data-error></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer dialog-footer">
                    <div class="center">
                        <button type="button" class="btn btn-can dialog-button" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-def dialog-button" onclick="buttonSubmit(this)">确定</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_surgery.js?version=${version}"></script>