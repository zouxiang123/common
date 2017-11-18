<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="u-mask" id="patientOutcomeDialog" data-hide="#patientOutcomeDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" title1></div>
            <div class="fs-18">患者转归</div>
            <div></div>
        </div>
        <div class="u-dialog-content" style="min-height: 150px;">
            <form action="#" onsubmit="return false;" id="patientOutcomeForm">
                <input type="hidden" name="fkPatientId" />
                <input type="hidden" name="sysOwner" />
                <div class="contentCenter">
                    <div class="u-list-text">
                        <div class="left">更换时间：</div>
                        <div class="right">
                            <input type="text" name="recordDateShow" readonly="readonly" datepicker required data-msg-required="请选择转归时间"/>
                        </div>
                    </div>
                    <div class="u-list-text">
                        <div class="left">类别：</div>
                        <div class="right">
                            <label class="u-select">
                                <select name="type">
                                    <c:forEach var="item" items="${patient_outcome_type }">
                                        <option value="${item.itemCode }" <c:if test="${item.isChecked }">selected</c:if> >${item.name }</option>
                                    </c:forEach>
                                </select>
                            </label>
                            <div data-error></div>
                        </div>
                    </div>
                    <div class="u-list-text">
                        <div class="left">原因：</div>
                        <div class="right">
                            <div class="u-textarea-adaption mt-4" style="min-height: 80px;width: 210px;">
                                <textarea name="reason" style="width: 100%" placeholder="请输入转归原因" maxlength="512"></textarea>
                            </div>
                            <div data-error></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#patientOutcomeDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="patient_outcome_edit.save();" fill>确定</button>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx }/assets/js/patient/patient_outcome_edit.js?version=${version}"></script>