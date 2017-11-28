<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="diagnosisHistKtDialog" data-hide="#diagnosisHistKtDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" id="diagnosisHistKt_patientName"></div>
            <div class="fw-bold fs-18">肾移植史</div>
            <div></div>
        </div>
        <div class="u-dialog-content" style="min-height: 230px;">
          <form action="#" id="diagnosisHistKtForm" onsubmit="return false;">
            <input type="hidden" name="id" />
            <input type="hidden" name="fkPatientId" id="diagnosisHistKt_patientId"/>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">
                        <span>开始日期：</span>
                    </div>
                    <div class="right">
                       <input type="text" id="diagnosisHistKt_startDateForm" name="startDateForm" readonly placeholder="开始日期" />
                       <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">
                        <span>结束日期：</span>
                    </div>
                    <div class="right">
                        <input type="text" id="diagnosisHistKt_endDateForm" name="endDateForm" readonly placeholder="结束日期" />
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">
                        <span>结束原因：</span>
                    </div>
                    <div class="right">
                        <c:forEach var="obj" items="${syzEndReason}" varStatus="status">
                            <label class="u-radio mr-20">
                                <input type="radio" name="endReason" value="${obj.value}" />
                                <span class="icon-radio"></span>${obj.name}
                            </label>
                        </c:forEach>
                        <div class="u-textarea-adaption mb-6 hide" data-other>
                            <textarea style="width: 100%;" rows="1" id="diagnosisHistKt_otherEndReason" name="otherEndReason" maxlength="256"></textarea>
                        </div>
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</div>
                    <div class="right">
                        <div class="u-textarea-adaption" style="min-height: 80px">
                            <textarea id="diagnosisHistKt_remark" name="remark" maxlength="256"></textarea>
                        </div>
                    </div>
                </div>
            </div>
          </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#diagnosisHistKtDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveDiagnosisHistKt()" fill>保存</button>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_kt.js?version=${version}"></script>