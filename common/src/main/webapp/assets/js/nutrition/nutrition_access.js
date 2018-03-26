/**
 * 饮食回顾模板
 */
function queryRec() {
    var recordDateShow = $("input[name='recordDateShow']").val();
    var fkPatientId = $("input[name='fkPatientId']").val();
    var sysOwner = $("input[name='sysOwner']").val();
    $.ajax({
        url : ctx + "/nutrition/queryRecordFood.shtml",
        type : "post",
        dataType : "json",
        data : "recordDateShow=" + recordDateShow + "&fkPatientId=" + fkPatientId,
        success : function(data) {
            if (data.status) {
                var tHtml = data.tempHtml;
                $("#modeHtml").html(tHtml);
            }
        }
    });
}