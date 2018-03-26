<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="diagnosisHistPdDialog" data-hide="#diagnosisHistPdDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div class="pl-12 fw-bold" id="diagnosisHistPd_patientName"></div>
            <div class="fw-bold fs-18">腹透史</div>
            <div></div>
        </div>
        <div class="u-dialog-content">
          <form action="#" id="diagnosisHistPdForm" onsubmit="return false;">
            <input type="hidden" name="id" />
            <input type="hidden" name="fkPatientId" id="diagnosisHistPd_patientId"/>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">开始日期：</div>
                    <div class="right">
                        <input type="text" id="diagnosisHistPd_startDateForm" name="startDateForm" readonly placeholder="开始日期">
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">开始原因：</div>
                    <div class="right">
                        <c:forEach var="obj" items="${ftStartReason}" varStatus="status">
                            <label class="u-radio mr-20">
                                <input type="radio" name="startReason" value="${obj.value}" />
                                <span class="icon-radio"></span>${obj.name}
                            </label>
                        </c:forEach>
                        <div class="u-textarea-adaption mb-6 hide" data-other>
                            <textarea style="width: 100%;" rows="1" id="diagnosisHistPd_otherStartReason" name="otherStartReason" maxlength="256"></textarea>
                        </div>
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">结束日期：</div>
                    <div class="right">
                        <input type="text" id="diagnosisHistPd_endDateForm" name="endDateForm" readonly placeholder="结束日期" />
                        <div data-error></div>
                    </div>
                </div>
            </div>
            <div class="u-xt-12 pr-30">
                <div class="u-list-text">
                    <div class="left">结束原因：</div>
                    <div class="right">
                        <c:forEach var="obj" items="${ftEndReason}" varStatus="status">
                            <label class="u-radio mr-20">
                                <input type="radio" name="endReason" value="${obj.value}" />
                                <span class="icon-radio"></span>${obj.name}
                            </label>
                        </c:forEach>
                        <div class="u-textarea-adaption mb-6 hide" data-other>
                            <textarea style="width: 100%;" rows="1" id="diagnosisHistPd_otherEndReason" name="otherEndReason" maxlength="256"></textarea>
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
                            <textarea id="diagnosisHistPd_remark" name="remark" maxlength="256"></textarea>
                        </div>
                    </div>
                </div>
            </div>
          </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#diagnosisHistPdDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveDiagnosisHistPd();" fill>保存</button>
        </div>
    </div>
</div>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/diagnosis/dialog/diagnosis_hist_pd.js?version=${version}"></script>