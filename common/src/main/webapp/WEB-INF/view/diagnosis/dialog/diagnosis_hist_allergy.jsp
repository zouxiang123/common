<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="diagnosisHistAllergyDialog" data-hide="#diagnosisHistAllergyDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" id="diagnosisHistAllergy_patientName"></div>
            <div class="fw-bold fs-18">过敏史</div>
            <div></div>
        </div>
        <div class="u-dialog-content" style="min-height: 230px;">
        <form action="#" id="diagnosisHistAllergyForm" onsubmit="return saveDiagnosisHistAllergy(this);">
            <input type="hidden" name="id" />
            <input type="hidden" name="fkPatientId" id="diagnosisHistAllergy_patientId"/>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">录入日期：</div>
                    <div class="right">
                        <input type="text" id="diagnosisHistAllergy_inputDateForm" name="inputDateForm" readonly placeholder="录入日期" />
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">过&nbsp;&nbsp;敏&nbsp;&nbsp;源：</div>
                    <div class="right">
                        <c:forEach var="obj" items="${gmResouce}" varStatus="status">
                            <label class="u-radio mr-20">
                                <input type="radio" name="allergens" value="${obj.value}" />
                                <span class="icon-radio"></span>${obj.name}
                            </label>
                        </c:forEach>
                        <div class="u-textarea-adaption mb-6 hide" data-other>
                            <textarea style="width: 100%;" rows="1" id="diagnosisHistAllergy_otherAllergens" name="otherAllergens" maxlength="256"></textarea>
                        </div>
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</div>
                    <div class="right">
                        <input type="text" id="diagnosisHistAllergy_name" name="name" maxlength="30" placeholder="名称" />
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</div>
                    <div class="right">
                        <div class="u-textarea-adaption mt-10" style="min-height: 80px">
                            <textarea id="diagnosisHistAllergy_remark" name="remark" maxlength="256"></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#diagnosisHistAllergyDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveDiagnosisHistAllergy();" fill>保存</button>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_allergy.js?version=${version}"></script>