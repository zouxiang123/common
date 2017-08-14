var basicFormData;
$(document).ready(function() {
    basicFormData = $("#basicForm").serialize();
    // 头像上传
    new uploadPreview({
        UpBtn : "up_img",
        DivShow : "imgdiv",
        ImgShow : "imgShow"
    });
    // 校验
    addValidate();
});

$("#up_img").change(function() {
    uploadImg($("#imageUploadForm"));
});

function uploadImg(form) {
    if (isEmpty($("#up_img").val())) {
        showWarn("请选择上传的文件");
        return false;
    }
    var options = {
        dataType : "json",
        url : ctx + "/system/uploadImage.shtml",
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    $("#nav-user-photo").attr("src", convertURL(ctx + "/images" + data.filepath));
                    $(form)[0].reset();
                    return false;
                } else if (data.status == 2) {
                    showWarn("请选择上传的文件");
                    return false;
                }
            }
        }
    };
    $(form).ajaxSubmit(options);
    return false;
}

function passwordForm() {
    var form = $("#passwordForm")[0];
    var old = $("input[name='password']").val();
    var new1 = $("input[name='newPassword']").val();
    var new2 = $("input[name='newPasswordConfirm']").val();
    if (new1 == old) {
        showSystemDialog({
            "password" : "新密码不能和原密码一样"
        });
        return false;
    }
    $.ajax({
        url : ctx + "/system/changePassword.shtml",
        data : $(form).serialize(),
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    showAlert("密码修改成功");
                    return false;
                } else if (data.status == 2) {
                    showWarn("原密码不正确");
                    form.reset();
                    return false;
                }
            }
        }
    });
    return false;
}
/**
 * 订阅通知
 * 
 * @param form
 * @returns {Boolean}
 */
function subscribeForm() {
    var selectedCheck = "";
    $("input:checkbox[name='subscribeCheckBox']:checked").each(function(i, obj) {
        if (i == 0) {
            selectedCheck += $(obj).val();
        } else {
            selectedCheck += ("," + $(obj).val());
        }
    });

    if (selectedCheck == "")
        return;

    // 保存数据库
    var param = "subscribe=" + selectedCheck + "&id=" + $("#subscribeId").val();
    $.ajax({
        url : ctx + "/system/updateUserSubscribe.shtml",
        data : param,
        type : "post",
        dataType : "json",
        loading : true,
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    showAlert("保存成功");
                    history.go(-1);
                    return false;
                }
            }
        }
    });

}

function basicForm(form) {
    var form = $("#basicForm")[0];
    var formData = $(form).serialize();
    if (basicFormData != formData) {
        $.ajax({
            url : ctx + "/system/updateUser.shtml",
            data : $(form).serialize(),
            type : "post",
            dataType : "json",
            loading : true,
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        // window.location.href = window.location.href;
                        showAlert("保存成功");
                        history.go(-1);
                        return false;
                    }
                }
            }
        });
    }
    return false;
}
/**
 * 保存签名图片
 * 
 * @param form
 * @returns {boolean}
 */
function autographForm(form) {
    var autographUrl = $("input[name='x']").val();
    if (autographUrl == '') {
        showSystemDialog({
            "x" : "请上传电子签名"
        });
        return false;
    }
    var options = {
        dataType : "json",
        url : ctx + "/system/changeAutograph.shtml",
        loading : true,
        success : function(data) {// ajax返回的数据
            if (data) {
                if (data.status == 1) {
                    showAlert("签名上传成功");
                    if (data.filepath) {
                        $("#autographView img").attr("src", convertURL(ctx + "/images" + data.filepath));
                        $("#autographView img").attr("style", "");
                        $("#autographView img").css({
                            width : "263px",
                            height : "148px"
                        });
                        $(".clip-up .clip-title").text("修改签名");
                        resetFormAndClearHidden(form.id);
                        $("[name='imgFile']").val('');
                    }
                    return false;
                } else {
                    showWarn("签名上传失败");
                    return false;
                }
            }
        }
    };
    $(form).ajaxSubmit(options);
    return false;
}

function addValidate() {
    $('#basicForm').validate({
        onfocusout : false,
        rules : {
            name : {
                required : [ "姓名" ]
            },
            sex : {
                required : [ "性别" ]
            },
            birthdayShow : {
                required : [ "出生日期" ],
                date : [ "出生日期" ]
            },
            mobile : {
                digits : [ "联系方式" ],
                customMaxlength : [ 11, "手机号" ]
            },
            subPhone : {
                customMaxlength : [ 64, "其他联系方式" ]
            }
        },
        messages : {
            mobile : {
                customMaxlength : "请输入正确的联系方式",
                digits : "请输入正确的联系方式"
            }
        },

        // 全部校验结果
        showErrors : function(errorMap, errorList) {
            showSystemDialog(errorMap);
        },
        submitHandler : function(form) {
            form.onsubmit();
        }
    });

    $('#passwordForm').validate({
        onfocusout : false,
        // 校验字段
        rules : {
            password : {
                required : [ "原密码" ]
            },
            newPassword : {
                required : [ "新密码" ]
            },
            newPasswordConfirm : {
                equalTo : "#newPassword"
            }
        },
        messages : {
            newPasswordConfirm : {
                equalTo : "两次新密码的值不一致"
            }
        },
        // 全部校验结果
        showErrors : function(errorMap, errorList) {
            showSystemDialog(errorMap);
        },
        submitHandler : function(form) {
            form.onsubmit();
        }
    });
}
