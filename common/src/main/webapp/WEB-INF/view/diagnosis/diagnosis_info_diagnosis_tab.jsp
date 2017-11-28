<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="border-gray u-clearfix w-100">
<form action="#" id="diagnosisEntityForm" onsubmit="return false;">
    <input type="hidden" name="id" />
    <input type="hidden" name="fkPatientId" id="diagnosisEntityForm_patientId"/>
    <input type="hidden" name="itemCode" id="diagnosisEntityForm_itemCode"/>
    <input type="hidden" name="itemName" id="diagnosisEntityForm_itemName"/>
    <div id="list_diagnosis_tab">
        <div class="u-xt-3 u-border-r" style="min-height: 286px;width: 20%;">
            <ul id="diagnosis_tab_tab_left"></ul>
        </div>
        <div class="u-xt-9" style="width: 80%" id="diagnosis_tab_tab_right">
        </div>
    </div>
    <div class="bt-line pt-10 modelbottom">
        <button id="finish" type="button" class="u-btn-blue mb-10 w-92" onclick="saveDiagnosisEntity();" fill>保存</button>
    </div>
</form>
</div>
