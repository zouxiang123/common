<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="patientAssayRecordAddDialog" data-hide="#patientAssayRecordAddDialog">
    <div class="u-dialog">
        <div class="u-dialog-header">
            <div></div>
            <div class="fs-18" id="patientAssayRecordAddTitle">添加化验项信息</div>
            <div></div>
        </div>
        <div class="u-dialog-content">
            <form id="patientAssayRecordAddForm" onsubmit="return false;">
                <div>
                    <span>化验单：</span>
                    <label class="u-select" style="width:120px;">
                        <select style="width:120px;" id="patientAssayRecordAddGroupList"></select>
                    </label>
                    <span class="ml-30">日期：</span>
                    <input type="text" style="width: 120px;" id="patientAssayRecordAddAssayDate" name="assayDate"  readonly="readonly">
                </div>
                <table class="u-table u-table-bordered mt-10">
                    <thead>
                        <tr>
                            <th style="width: 25%">项目名称</th>
                            <th style="width: 20%">项目代号</th>
                            <th class="xtt-9">检查结果</th>
                            <th style="width: 25%">参考值</th>
                            <th style="width: 15%">操作</th>
                        </tr>
                    </thead>
                    <tbody id="patientAssayRecordAddBody"></tbody>
                </table>
                <div data-error></div>
            </form>
        </div>
        <div class="u-dialog-footer">
            <button type="button" data-hide="#patientAssayRecordAddDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="assay_record_add.save();" fill>确定</button>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx }/assets/js/assay/patient_assay_record_add.js?version=${version }"></script>