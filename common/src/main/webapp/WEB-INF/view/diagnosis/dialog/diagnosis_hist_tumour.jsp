<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="modal" id="diagnosisHistTumourDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header dialog-header">
                <img id="diagnosisHistTumour_patientImage" src="${ctx }/assets/img/default-user.png" class="user-photo">
                <span class="user-name" id="diagnosisHistTumour_patientName" ></span>
                <h4 class="modal-title modal-title2 ">肿瘤史</h4>
                <div class="dialog-close pull-right" data-dismiss="modal"><img src="${ctx }/assets/img/dialog-new-close.png"></div>
            </div>
            <!-- cache -->
            <form action="#" id="diagnosisHistTumourForm" onsubmit="return saveDiagnosisHistTumour(this);">
                <input type="hidden" name="id" />
                <input type="hidden" name="fkPatientId" id="diagnosisHistTumour_patientId"/>
                <div class="modal-body">
                    <div class="dialog-wrap">
                        <div class="list-group bg-white layerNode">
                            <div class="list-group-item">
                                <span class="list-group-item-title">诊断日期：</span>
                                <input type="text" class="input-style" name="recordDateShow" placeholder="请选择诊断日期" readonly="readonly"/>
                            </div>
                            <div class="list-group-item">
                                <span class="list-group-item-title">诊断类别：</span>
                                <textarea style="width:100%;resize:none;height:100px;" name="recordType" maxlength="256"></textarea>
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
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_tumour.js?version=${version}"></script>