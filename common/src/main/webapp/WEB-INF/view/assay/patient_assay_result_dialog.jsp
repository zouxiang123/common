<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<div class="u-mask" id="assayResultDialog" data-hide="#assayResultDialog">
    <div class="u-dialog" min>
        <div class="u-dialog-header">
            <div></div>
            <div class="fs-18">传染病标识</div>
            <div></div>
        </div>
        <div class="u-dialog-content text-center">
        <form onsubmit="return false;" id="assayResultForm">
            <input type="hidden" name="fkPatientId" />
            <input type="hidden" name="id" />
            <label class="u-checkbox u-xt-3 mr-0">
                <input type="checkbox" name="normal" value="true" data-text="正常">
                <span class="icon-checkbox"></span> 正常
            </label>
            <label class="u-checkbox u-xt-3 mr-0">
                <input  type="checkbox"  name="hav" value="true" data-text="甲肝">
                <span class="icon-checkbox"></span>甲肝
            </label>
            <label class="u-checkbox u-xt-3 mr-0">
                <input  type="checkbox" name="hbv" value="true" data-text="乙肝">
                <span class="icon-checkbox"></span>乙肝
            </label>
            <label class="u-checkbox u-xt-3 mr-0">
                <input  type="checkbox" name="hcv" value="true" data-text="丙肝">
                <span class="icon-checkbox"></span>丙肝
            </label>
            <label class="u-checkbox u-xt-3 mr-0">
                <input  type="checkbox" name="hev" value="true" data-text=" 戊肝">
                <span class="icon-checkbox"></span>戊肝
            </label>
            <label class="u-checkbox u-xt-3 mr-0">
                <input  type="checkbox" name="hiv" value="true" data-text="HIV">
                <span class="icon-checkbox"></span>HIV
            </label>
            <label class="u-checkbox u-xt-3 mr-0">
                <input  type="checkbox" name="hsv" value="true" data-text="梅毒">
                <span class="icon-checkbox"></span>梅毒
            </label>
            <label class="u-checkbox u-xt-3 mr-0">
                <input  type="checkbox" name="unknown" value="true" data-text="未知">
                <span class="icon-checkbox"></span>未知
            </label>
            <div class="w-100 text-center fc-red" id="showError"></div>
            </form>
        </div>
        <div class="u-dialog-footer" style="border-top: 1px solid #d9e0e6;">
            <button type="button" data-hide="#assayResultDialog">取消</button>
            <button type="button" class="u-btn-blue" onclick="saveAssayResult();" fill>确定</button>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
    $("#assayResultForm").on("click", ":checkbox", function() {
        var name = $(this).attr("name");
        if (name == "normal" || name == "unknown") {
            $("#assayResultForm").find(":checkbox:not([name='" + name + "'])").prop("checked", false);
        } else {
            $("#assayResultForm").find(":checkbox[name='normal'],:checkbox[name='unknown']").prop("checked", false);
        }
    });
});
/**
 * 显示dialog
 * @param patientId
 * @param callback
 */
function showAssayResultDialog(patientId,callback) {
    $("#assayResultForm").find("[name='fkPatientId']").val(patientId);
    $("#showError").text("");
    $.ajax({
        url : ctx + "/assay/patientAssayRecord/record.shtml",
        data : {
            patientId:patientId
        },
        type : "post",
        dataType : "json",
        success : function(data) {// ajax返回的数据
            if (data.assayResult) {
                mappingFormData(data.assayResult, "assayResultForm");
                popDialog("#assayResultDialog",function(){
                    if (!isEmpty(callback)) {
                        callback();
                    }
                });
            }
        }
    });
}

/** 保存检查结果汇总数据 */
function saveAssayResult() {
    var checkedEl = $("#assayResultForm").find(':checkbox:checked');
    if (checkedEl.length > 0) {
        $("#showError").text("");
        var chk_value = [];// 定义一个数组
        checkedEl.each(function() {// 遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push($(this).attr("data-text"));// 将选中的值添加到数组chk_value中
        });
        showConfirm("传染病标志:" + chk_value, function() {
            $.ajax({
                url : ctx + "/assay/patientAssayRecord/saveAssayResult.shtml",
                data : $("#assayResultForm").serialize(),
                type : "post",
                loading : true,
                dataType : "json",
                success : function(data) {// ajax返回的数据
                    if (data.status == 1) {
                        showTips("保存成功");
                        $("#assayResultForm input[name='id']").val(data.id);
                        hiddenMe("#assayResultDialog");
                    }
                }
            });
        });
    } else {
        $("#showError").text("请至少选择一项");
    }
}
</script>