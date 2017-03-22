var user_list = {
    /** 是否是新增集团用户 */
    isGroup : false,
    /** 表单数据缓存 */
    formData : null,
    /** 选中的用户角色父级id */
    checkedParentId : "",
    /**
     * 初始化
     */
    init : function(isGroup) {
        if (!isEmpty(isGroup)) {
            this.isGroup = isGroup;
        }
        this.addEvents();
        this.getUserList();
        $(window).resize();
    },
    /** 事件注册 */
    addEvents : function() {
        // 窗口渲染事件
        $(window).on("resize", function() {
            $("#tableDiv").css("height", ($(window).height() - $("#tableDiv").offset().top - 65));
            $('#tableDiv').perfectScrollbar("update");
        });
        // 出生日期日历控件初始化
        $("#birthdayShow").daterangepicker({
            "singleDatePicker" : true,
            "showDropdowns" : true,
            autoUpdateInitInput : false,
            "locale" : {
                format : "YYYY-MM-DD"
            }
        });
        // 滚动条事件注册
        $("#tableDiv").perfectScrollbar();
        // 用户筛选keydown 事件
        $("#searchRow").on("keydown", "#searchAccount,#searchMobile,#searchName", function(event) {
            if (event.keyCode == 13) {
                user_list.getUserList($(this).attr("id"));
            }
        });
        // 选择用户角色事件，一个用户只能输入一个大的角色
        $("input[name='roleId']").click(function() {
            if ($(this).is(":checked")) {
                var exists = false;
                var parentId = $(this).attr("parentId");
                $("input[name='roleId']:checked").each(function() {
                    if (parentId != $(this).attr("parentId")) {
                        exists = true;
                        return false;
                    }
                });
                if (exists) {
                    showWarn("用户角色只能存在一种分类");
                    return false;
                }
                // 如果选中的角色不包含当前角色，获取职称的html
                if (user_list.checkedParentId.indexOf(parentId) == -1) {
                    user_list.getPositionHtml(parentId);
                } else {
                    // 如果之前没有选中角色，获取职称的html
                    if (isEmpty(user_list.checkedParentId)) {
                        user_list.getPositionHtml(parentId);
                    }
                    user_list.checkedParentId = parentId;
                }
            } else {
                if ($("input[name='roleId']:checked").length == 0) {
                    $("#position").html("");
                    user_list.checkedParentId = "";
                }
            }
        });
        // 账户名称变更事件，判断账户是否存在
        $("#account").change(function() {
            var param = {
                account : $(this).val()
            }
            if (!isEmpty(account)) {
                $.ajax({
                    url : ctx + "/system/checkAccountExists.shtml",
                    data : $.param(param),
                    type : "post",
                    dataType : "json",
                    success : function(data) {// ajax返回的数据
                        if (data.status == 2) {
                            showWarn("该用户已经存在");
                        }
                    }
                });
            }
        });
    },
    /**
     * 重置
     */
    reset : function(element) {
        $("#searchRow").find("input[type='text']").val("");
        this.getUserList();
    },
    /**
     * 获取职称的html内容
     * 
     * @param parentId
     */
    getPositionHtml : function(parentId) {
        if (isEmpty(parentId)) {
            return;
        }
        if (parentId.indexOf('2') != -1) {
            $("#position").html($("#doctorPosition").html());
        } else if (parentId.indexOf('3') != -1) {
            $("#position").html($("#nursePosition").html());
        } else if (parentId.indexOf('1') != -1) {
            $("#position").html('<input type="text" name="position" maxlength="32" value="管理员" maxlength="32"/>');
        } else if (parentId.indexOf('4') != -1) {
            $("#position").html('<input type="text" name="position" maxlength="32" value="其他" maxlength="32" />');
        }
    },
    /**
     * 获取用户列表
     * 
     * @param componentId
     *            组件id
     */
    getUserList : function(componentId) {
        var param = {
            account : $("#searchAccount").val(),
            name : $("#searchName").val(),
            mobile : $("#searchMobile").val()
        };
        $.ajax({
            url : ctx + "/system/selectUserWithFilter.shtml",
            data : $.param(param),
            type : "post",
            loading : true,
            dataType : "json",
            success : function(items) {
                var tableHtml = "";
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    tableHtml += "<tr class='table-default'>";
                    tableHtml += "<td style='width:15%;'>" + convertEmpty(item.account) + "</td>";
                    tableHtml += "<td style='width:15%;'>" + convertEmpty(item.name) + "</td>";
                    tableHtml += "<td style='width:10%;'>" + convertEmpty(item.roleName) + "</td>";
                    tableHtml += "<td style='width:15%;'>" + convertEmpty(item.mobileShow) + "</td>";
                    tableHtml += "<td style='width:15%;'>" + convertEmpty(item.subPhone) + "</td>";
                    tableHtml += "<td style='width:30%;'><a onclick=\"user_list.getFullItem('" + item.id
                                    + "');\">修改</a> <a onclick=\"user_list.deleteItem('" + item.id
                                    + "');\">删除</a> <a onclick=\"user_list.resetPassword('" + item.id + "');\">密码重置</a></td>";
                    tableHtml += "</tr>";
                }
                $("#tableBody").html(tableHtml);
                $("#tableDiv").perfectScrollbar("update");
            }
        });
    },
    /**
     * 添加用户
     */
    addUser : function() {
        $("#userInfoForm")[0].reset();
        $("#userInfoForm").find("input[name='id']").val("");
        $("input[name='sex']:eq(0)").prop("checked", true);
        $("#modal-title").html("新增用户(默认密码:123456)");
        $("#position").html("");
        this.checkedParentId = "";
        $("#userInfo").modal("show");
    },
    /**
     * 根据id获取用户信息
     */
    getFullItem : function(itemId) {
        $.ajax({
            url : ctx + "/system/getFullUser.shtml",
            data : "id=" + itemId,
            type : "post",
            dataType : "json",
            success : function(data) {// ajax返回的数据
                if (data) {
                    user_list.getPositionHtml(data.parentRoleId);// 获取职称的值
                    user_list.checkedParentId = data.parentRoleId;
                    mappingFormData(data, "userInfoForm");
                    $("#modal-title").html("用户信息修改");
                    $("#userInfo").modal("show");
                    user_list.formData = $("#userInfoForm").serialize();
                }
            }
        });
    },
    /**
     * 删除用户 *
     * 
     * @param itemId
     *            用户ID
     */
    deleteItem : function(itemId) {
        showWarn("您确定要删除用户吗？", function() {
            $.ajax({
                url : ctx + "/system/deleteUser.shtml",
                data : "id=" + itemId,
                type : "post",
                dataType : "json",
                success : function(data) {// ajax返回的数据
                    if (data.status == 1) {
                        window.location.href = window.location.href;
                    }
                    SystemDialog.modal("hide");
                }
            });
        })
    },
    /**
     * 密码重置
     * 
     * @param itemId
     *            用户ID
     */
    resetPassword : function(itemId) {
        showWarn("您确定要重置该用户密码吗？", function() {
            $.ajax({
                url : ctx + "/system/resetUser.shtml",
                data : "id=" + itemId,
                type : "post",
                dataType : "json",
                success : function(data) {// ajax返回的数据
                    if (data.status == 1) {
                        alert("重置成功,新密码为 '123456'");
                    }
                    SystemDialog.modal("hide");
                }
            });
        });
    },
    updateUserInfo : function() {
        var currentFormData = $("#userInfoForm").serialize();
        if (this.formData == currentFormData) {
            return false;
        }
        if (!$('#userInfoForm').valid()) {
            return false;
        }
        $.ajax({
            url : ctx + "/system/saveUser.shtml",
            data : currentFormData,
            type : "post",
            dataType : "json",
            loading : true,
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        refreshTable();
                        $("#userInfo").modal("hide");
                        return false;
                    } else if (data.status == 2) {
                        showWarn("用户角色不能为空");
                        return false;
                    } else if (data.status == 0) {
                        showWarn("账户名已存在");
                        return false;
                    }
                }
            }
        });
        return false;
    },
    /**
     * 添加表单校验
     */
    addValidate : function() {
        $('#userInfoForm').validate({
            onfocusout : false,
            // 校验字段
            rules : {
                account : {
                    required : [ "账号" ]
                },
                name : {
                    required : [ "姓名" ]
                },
                roleId : {
                    required : [ '角色' ]
                },
                birthdayShow : {
                    date : [ "生日" ]
                },
                mobile : {
                    digits : [ "手机号" ],
                    customMaxlength : [ 11, "手机号" ]
                },
                subPhone : {
                    customMaxlength : [ 64, "其他联系方式" ]
                }
            },
            messages : {
                mobile : {
                    customMaxlength : "请输入正确的手机号",
                    digits : "请输入正确的手机号"
                }
            },
            // 全部校验结果
            showErrors : function(errorMap, errorList) {
                showSystemDialog(errorMap);
            }
        });
    }
}
