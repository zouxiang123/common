<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<div class="p-relative fs-15 fc-deepgrey w-10">
    <form action="#" id="diagnosisEntityForm" onsubmit="return saveDiagnosisEntity(this);">
        <div id="list_diagnosis_tab">
            <div class="clearfix f-both col-xs-12 mp-0 m-con-1 p-b-5" style="">
                <input type="hidden" name="id" />
                <input type="hidden" name="fkPatientId" id="diagnosisEntityForm_patientId"/>
                <input type="hidden" name="itemCode" id="diagnosisEntityForm_itemCode"/>
                <input type="hidden" name="itemName" id="diagnosisEntityForm_itemName"/>
                <div class="col-xs-2 m-con-2 p-l-20 p-r-0" style="overflow:auto;" id="tab_left">

                </div>
                <div class="col-xs-10 mp-0 clearfix" id="tab_right">

                </div>
            </div>
        </div>
        <div class="f-both">
            <div class="center">
                <button id="finish" type="button" class="btn btn-def dialog-button" onclick="buttonSubmit(this);">保 存</button>
            </div>
        </div>
    </form>
</div>
