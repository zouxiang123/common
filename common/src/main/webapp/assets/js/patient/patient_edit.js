var patient_edit = {
    interfaceOpenCardText : null,
    idAdd : false,
    card_index : 0,
    cardFlag : 1,
    idNumberReg : /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/,
    /**
     * 初始化患者编辑
     * 
     * @param interfaceOpenCardText
     */
    init : function(interfaceOpenCardText) {
        this.interfaceOpenCardText = interfaceOpenCardText;
        this.idAdd == isEmpty($("#patientForm_id").val());
        // 如果开启了接口服务，就要一开始显示卡号未验证,反之就不验证
        // 获取当前选中的值，如果包含在配置中，就要验证
        var cardTypeText = $("#cardTypeId").find("option:selected").text();
        if (!isEmpty(interfaceOpenCardText) && interfaceOpenCardText.indexOf(cardTypeText) == -1) {
            this.cardFlag = 3;// 直接通过
            $("#validCardNoDiv").addClass("hide");
        } else {// 开启了接口服务，又分为新增，与修改，新增要显示未验证，而修改不需要
            this.cardFlag = 1;// 未验证
            $("#validCardNoDiv").removeClass("hide");
        }
        this.card_index = $("#patientCardListDiv").find("[data-cardrecord]").length;
        // 注册事件
        this.addEvents();
        // 触发默认的患者卡号类型切换事件，修复如果卡号类别默认为ID的情况没有显示radio的问题
        $("#patientCardDefaultTr").find("[data-cardType]").trigger("change");
        // 头像上传
        new uploadPreview({
            UpBtn : "up_img",
            DivShow : "imgdiv",
            ImgShow : "imgShow"
        });
        layui.use('laydate', function() {
            var laydate = layui.laydate;
            laydate.render({
                elem : '#patientForm_birthday',
                theme : '#31AAFF',
                btns : [ 'clear', 'now', 'confirm' ]
            });
        });
        this.addValidate();
    },
    /**
     * 事件注册
     */
    addEvents : function() {
        // 删除卡号事件
        $("#patientCardListDiv").on("click", "[data-remove]", function() {
            var id = $(this).data("remove");
            var trEl = $(this).parents("[data-cardrecord]");
            if (isEmpty(id)) {
                trEl.remove();
            } else {
                trEl.addClass("hide");
                trEl.find("input[data-delFlag]").val(1);
            }
            patient_edit.cardFlag = 3;// 验证通过；
        });
        // 是否为最新项的点击事件
        $("#patientCardListDiv").on("click", "input[data-newFlag]", function() {
            // 当前项是否选中标识
            var isChecked = $(this).is(":checked");
            var trEl = $(this).parents("[data-cardrecord]");
            if (isChecked && isEmpty(trEl.find("input[data-cardNo]").val().trim())) {
                showWarn("卡号为空时不能作为默认卡号");
                return false;
            }
            // 取消所有项目最新标识的选中状态
            $("#patientCardListDiv").find("input[data-newFlag]").prop("checked", false);
            // 设置值到当前元素
            $(this).prop("checked", isChecked);
        });
        // 卡号类别变更
        $("#patientCardListDiv").on("change", "[data-cardType]", function() {
            var val = $(this).val();
            var trEl = $(this).parents("[data-cardrecord]");
            if (val == '03') {// 如果不是hisId 不显示是否最新标识
                trEl.find("[data-newFlagLabel]").removeClass("hide");
            } else {
                trEl.find("[data-newFlagLabel]").addClass("hide");
                trEl.find("input[data-newFlag]").prop("checked", false);
            }
        });
        // 卡号focus事件,缓存当前的值
        $("#patientCardListDiv").on("focus", "[data-cardNo]", function() {
            $(this).data("cacheval", $(this).val());
        });
        // 卡号失去焦点事件,如果当前卡号被默认为最新的卡号，则卡号不能为空
        $("#patientCardListDiv").on("blur", "[data-cardNo]", function() {
            var trEl = $(this).parents("[data-cardrecord]");
            var val = trEl.find("[data-cardType]").val();
            if (val == '03') {
                var newFlag = trEl.find("input[data-newFlag]").is(":checked");
                if (newFlag && isEmpty($(this).val())) {
                    showWarn("当前卡号作为默认卡号时，不能为空");
                    // 还原值为输入之前的
                    $(this).val($(this).data("cacheval"));
                    return false;
                }
            }
        });
        // 添加患者卡号信息
        $("#addPatientCard").on("click", function() {
            patient_edit.card_index++;
            var $defaultTr = $("#patientCardDefaultDiv");
            var cardType = $defaultTr.find("[data-cardType]").val();
            var tr = $("#patientCardDefaultDiv").clone();
            // jquey clone lost select val，set it
            tr.find("[data-cardType]").val(cardType);
            // 新增的时候显示没有验证的卡号标志
            var inOpenText = patient_edit.interfaceOpenCardText;
            if (!isEmpty(inOpenText)) {
                var cardTypeText = $("#cardTypeId").find("option:selected").text();
                // 首先要判断卡号类型是不是属于要验证的范围,比如后端admin登录之后，在系统参数表中，有没有配置门诊号，住院号之类的要验证
                if (!isEmpty(inOpenText) && inOpenText.indexOf(cardTypeText) != -1) {
                    // 在点击加号之前要先判断是否拉取一下接口，拉取过了并且验证通过了，才可以继续新增，否则不允许新增
                    if (patient_edit.cardFlag != 3 && !isEmpty(tr.find("input[data-cardNo]").val().trim())) {
                        showWarn("请先验证卡号");
                        return false;
                    }
                }
                patient_edit.cardFlag = 1;// 未验证
                $("#validCardNoDiv").removeClass("hide").html("<font style='color:#F8C730;'>卡号未验证</font>");
            } else {
                $("#validCardNoDiv").addClass("hide");
                patient_edit.cardFlag = 3;// 直接通过
            }
            if (isEmpty(tr.find("input[data-cardNo]").val().trim())) {
                showWarn("请填写卡号");
                return false;
            }
            tr.find("[data-clonereplace]").each(function() {
                var name = $(this).attr("name").replace("[0]", "[" + patient_edit.card_index + "]");
                $(this).attr("name", name);
            });
            // 删除查询和新增按钮
            tr.find("[data-query],[data-add]").remove();
            tr.find("[data-remove]").removeClass("hide");
            $("#patientCardListDiv").append(tr);
            $defaultTr.find("input[data-cardNo]").val("");
            // 清除的选中状态
            $defaultTr.find("input[data-newFlag]").prop("checked", false);
        });

        // 调用接口查询患者数据
        $("#queryPatientInfo").on("click", function() {
            // 判断是否已经验证
            $("#checkedStatus").val(1);
            var trEl = $(this).parents("[data-cardrecord]");
            var cardTypeValue = trEl.find("[data-cardType]").val();
            // $("#ddlregtype").find("option:selected").text();
            // 获取选中的下拉框的文本
            var cardTypeText = trEl.find("select[data-cardType]").find("option:selected").text();
            var cardNo = trEl.find("input[data-cardNo]").val();
            if (isEmpty(cardNo)) {
                showError("“卡号”不能为空");
                return false;
            }
            var name = $("#patientForm_name").val();
            $.ajax({
                url : ctx + "/patient/getWsPatientInfo.shtml",
                data : {
                    cardNo : cardNo,
                    cardType : cardTypeValue
                },
                type : "post",
                dataType : "json",
                success : function(data) {// ajax返回的数据
                    if (data.wsdlStatus == 2) {
                        $("#validCardNoDiv").html("");
                        patient_edit.cardFlag = 3;// 接口不通的时候，去掉了验证，可以直接手动录入
                        return true;
                    } else {
                        if (data) {
                            var pt = data.patient;
                            var province = data.provinceList;
                            var county = data.countyList;
                            if (pt != null) {
                                if (isEmpty(pt.name)) {
                                    // 验证通不通过的标志
                                    $("#validCardNoDiv").html("<font style='color:#F7587A;'>" + cardTypeText + cardNo + "不存在</font>");
                                    patient_edit.cardFlag = 2;// 2代表卡号不存在
                                    return false;
                                }
                                if (!isEmpty(pt.cardType)) {
                                    // 跟页面上的cardType进行判断，如果不一致，就要改变，一致的话就不动
                                    if (pt.cardType != cardTypeValue) {
                                        // 在设置为选中状态
                                        $("#cardTypeId option[value=" + pt.cardType + "]").prop("selected", true);
                                    }
                                }
                                // 判断文本框中输入的名字，是否跟后端返回的名字一致，一致的话就直接覆盖，不一致的话就要提示
                                if (!isEmpty(name)) {
                                    if (name != pt.name) {
                                        patient_edit.showCueDialog(name, pt.name, cardTypeText, cardNo);
                                        // 对比不一致的字段
                                        patient_edit.replacePatient(pt, province, county);
                                    } else {
                                        // 对比不一致的字段
                                        patient_edit.replacePatient(pt, province, county, 'edit');
                                    }
                                } else {
                                    patient_edit.cardFlag = 3;// 验证通过
                                    // 向页面填充数据
                                    mappingFormData(pt, "patientForm", [ "id" ]);
                                    // 当没有输入姓名的时候，直接验证通过
                                    $("#validCardNoDiv").html("<font style='color:#41C95B'>验证通过</font>");
                                }
                            } else {
                                patient_edit.cardFlag = 2;// 卡号不存在
                                $("#validCardNoDiv").html("<font style='color:#F7587A;'>" + cardTypeText + cardNo + "不存在</font>");
                                return false;
                            }
                        }
                        return false;
                    }
                }
            });
        });

        // 卡号类型变换绑定事件
        $("#cardTypeId").on("change", function() {
            // 首先要判断卡号类型是不是属于要验证的范围,比如后端admin登录之后，在系统参数表中，有没有配置门诊号，住院号之类的要验证
            var cardTypeText = $("#cardTypeId").find("option:selected").text();
            if (!isEmpty(patient_edit.interfaceOpenCardText) && patient_edit.interfaceOpenCardText.indexOf(cardTypeText) != -1) {
                var cardNo = $("#patientCardDefaultDiv").find("input[name='patientCardList[0].cardNo']").val();
                $("#validCardNoDiv").removeClass("hide");
                // 如果卡号没有输入就不需要验证
                if (isEmpty(cardNo)) {
                    $("#validCardNoDiv").html("<font style='color:#F8C730;'>卡号未验证</font>");
                } else {
                    patient_edit.cardFlag = 1;// 输入卡号的时候，就开始显示未验证
                    $("#validCardNoDiv").show();
                    $("#validCardNoDiv").html("<font style='color:#F7587A;'>卡号必须验证</font>");
                }
            } else {
                patient_edit.cardFlag = 3;// 验证通过
                $("#validCardNoDiv").addClass("hide");
            }
        });

        // 省县级联绑定事件
        $("#province").bind("change", function() {
            $("#county").empty();
            $("#county").append('<option value="0">--</option>');
            $.ajax({
                url : ctx + "/common/getCountyList.shtml",
                data : "provinceId=" + $(this).val(),
                type : "post",
                dataType : "json",
                success : function(data) {// ajax返回的数据
                    if (data) {
                        $.each(data, function(n, row) {
                            $("#county").append('<option value="' + row.id + '" >' + row.name + '</option>');
                        });
                    }
                }
            });
        });

        $("#patientForm_idType").on("change", function() {
            patient_edit.changeIdNumber();
        });

        $("#patientForm_id_number").on("change blur", function() {
            patient_edit.changeIdNumber();
        });

        $("#patientForm_birthday").on("blur", function() {
            patient_edit.changeIdNumber();
        });
        /**
         * 上传头像
         */
        $("#up_img").change(function() {
            patient_edit.uploadImg($("#imageUploadForm"));
        });
    },
    /**
     * 输入身份证号自动带出省、市、区
     */
    changeIdNumber : function() {
        if ($("#patientForm_idType").val() != "1") {
            return;
        }
        var value = $("#patientForm_id_number").val();
        if (isEmpty(value)) {
            return;
        }
        if (this.idNumberReg.test(value) === false) {
            return;
        }
        this.getBirthdatByIdNo(value);

        $("#province").val(value.substring(0, 2) + "0000");
        if ($("#province").val() == null) {
            $("#county").empty();
            return;
        }
        $("#county").empty();
        $("#county").append('<option value="0">--</option>');
        $.ajax({
            url : ctx + "/common/getCountyList.shtml",
            data : {
                provinceId : $("#province").val()
            },
            type : "post",
            dataType : "json",
            success : function(data) {// ajax返回的数据
                if (data) {
                    $.each(data, function(n, row) {
                        $("#county").append('<option value="' + row.id + '" >' + row.name + '</option>');
                    });
                    $("#county").val(value.substring(0, 4) + "00");
                    if ($("#county").val() == null) {
                        $("#county").val(value.substring(0, 6));
                    }
                }
            }
        });
    },
    /**
     * 根据身份证获取出生日期
     * 
     * @param iIdNo
     */
    getBirthdatByIdNo : function(iIdNo) {
        var tmpStr = "";
        var birthday = $("#patientForm_birthday");
        iIdNo = $.trim(iIdNo);
        if (iIdNo.length == 15) {
            tmpStr = iIdNo.substring(6, 12);
            tmpStr = "19" + tmpStr;
            tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
            birthday.val(tmpStr);
        } else {
            tmpStr = iIdNo.substring(6, 14);
            tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
            birthday.val(tmpStr);
        }
    },
    uploadImg : function(form) {
        if (isEmpty($("#up_img").val())) {
            showWarn("请选择上传的文件");
            return false;
        }
        var options = {
            dataType : "json",
            url : ctx + "/patient/savePatientImage.shtml?id=" + $("#patientForm_id").val(),
            success : function(data) {// ajax返回的数据
                if (data) {
                    if (data.status == 1) {
                        $("#tempImagePath").val(data.filepath);
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
    },
    /**
     * 保存患者
     * 
     * @returns {Boolean}
     */
    save : function() {
        var checkNum = $("#patientCardListDiv").find("input[data-newFlag]:checked").length;
        if (checkNum > 1) {
            showAlert("“ID”号只能有一个最新");
            return false;
        }
        var chargeType = $("input[name='chargeType']:checked").val();
        if (chargeType == "3") {
            if (isEmpty($("#patientForm_name").val())) {
                showAlert("“姓名”不能为空");
                return false;
            }
            if (isEmpty($("input[name='sex']:checked").length == 0)) {
                showAlert("“性别”不能为空");
                return false;
            }
            if (isEmpty($("#patientForm_birthday").val())) {
                showAlert("“出生年月”不能为空");
                return false;
            }
        } else {
            if (!$("#patientForm").valid()) {
                return false;
            }
        }
        // 獲取卡号
        var cardNo = $("#cardTypeId").val();
        // 卡号验证没有通过的话，就要提示
        if ((this.cardFlag == 1 || this.cardFlag == 2) && !isEmpty(cardNo)) {
            // 跳转到页面上的卡号位置
            $('body').animate({
                scrollTop : $('#cardTypeId').offset().top - $('#validCardNoDiv').height() - 48
            }, 500);
            return false;
        } else {
            $.ajax({
                url : ctx + "/patient/savePatient.shtml",
                data : $("#patientForm").serialize(),
                type : "post",
                dataType : "json",
                loading : true,
                success : function(data) {// ajax返回的数据
                    if (data.status == '1') {
                        patient_edit.refreshPatientData();
                        patient_edit.goToPatientHomePage(data.fkPatientId);
                    } else if (data.status == "2") {
                        showWarn("患者卡号不能重复");
                    } else if (data.status == "4") {
                        showWarn(data.errmsg);
                    }
                    return false;
                }
            });
        }
        return false;
    },
    /**
     * 跳转到患者首页
     * 
     * @param patientId
     */
    goToPatientHomePage : function(patientId) {
        window.location.href = ctx + "/patient/patientDetail.shtml?patientId=" + patientId + "&timeTemp=" + (new Date()).getTime();
    },
    /**
     * 刷新患者数据
     */
    refreshPatientData : function() {
        if (existsFunction("parent.getPatientData")) {
            parent.getPatientData();
        }
    },
    /**
     * 弹出提示的dialog
     */
    showCueDialog : function(name, defName, cardType, cardNo) {
        showConfirm("血透姓名" + name + "与" + cardType + cardNo + "查询到的" + defName + "不一致，是否确定为同一个人;", this.showReplace(), this.hideDialog());
    },
    /**
     * 显示替换页面
     */
    showReplace : function() {
        $("#dialog").modal("hide");
        $("#replacePatientId").modal("show");
        $("#replacePatientId").css("margin", "-30px auto");
        $("#replacePatientId .modal-dialog").css("width", "700px");
        // var dialog_height = auto;
        $("#replacePatientId .modal-content").css("height", "auto");
        $("#replacePatientId .dialog-wrap .list-group").css("height", "auto");
    },
    /**
     * 关闭提示框，并且显示未验证
     */
    hideDialog : function() {
        this.cardFlag = 1;// 未验证
        $("#dialog").modal("hide");
        $("#validCardNoDiv").html("<font style='color:#F8C730;'>卡号未验证</font>");
    },
    /**
     * 关闭提示框，并且显示验证已通过
     */
    closeDialog : function() {
        this.cardFlag = 3;// 验证通过
        $("#validCardNoDiv").html("<font style='color:#41C95B'>验证通过</font>");
        $("#replacePatientId").modal("hide");
        $("#dialog").modal("hide");
    },
    /**
     * 添加校验
     */
    addValidate : function() {
        // 身份证号码校验、身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        $.validator.addMethod("isIdNumber", function(value, element, params) {
            if ($("#patientForm_idType").val() != "1") {
                return true;
            }
            if (isEmpty(value)) {
                return false;
            }

            if (patient_edit.idNumberReg.test(value) === false) {
                return false;
            }
            return true;
        }, $.validator.format("请输入正确的证件号码"));

        $('#patientForm').validate({
            onfocusout : false,
            rules : {
                sex : {
                    required : [ "性别" ]
                },
                isTemp : {
                    required : [ "长期/临时患者" ]
                },
                idNumber : {
                    required : [ "证件号码" ],
                    isIdNumber : true
                },
                workUnit : {
                    customMaxlength : [ 128, "工作单位" ]
                },
                birthdayShow : {
                    required : [ "出生日期" ],
                    date : [ "出生日期" ]
                },
                name : {
                    required : [ "姓名" ]
                },
                mobile : {
                    required : [ "联系方式" ],
                    digits : [ "联系方式" ],
                    customMaxlength : [ 12, "联系方式" ]
                },
                email : {
                    email : [ "电子邮件" ]
                },
                emergencyMobile : {
                    digits : [ "家属联系方式 " ],
                    customMaxlength : [ 12, "家属联系方式 " ]
                },
                address : {
                    // required : [ "详细地址" ],
                    customMaxlength : [ 128, "详细地址" ]
                },
                admissionNumber : {
                    isNumberOrLetter : [ "住院号" ],
                    customMaxlength : [ 64, "住院号" ]
                },
                medicareCard : {
                    isNumberOrLetter : [ "医保卡号" ],
                    customMaxlength : [ 64, "医保卡号" ]
                },
                height : {
                    number : [ "身高" ],
                    customRange : [ 0.01, 300, "身高" ]
                },
                weight : {
                    number : [ "体重" ],
                    customRange : [ 1, 500, "体重" ]
                }
            },
            messages : {
                idNumber : {
                    remote : "此证件号码已存在，请确认输入是否正确",
                    customMinlength : "输入的证件证号码长度应该为18位，请重新输入"
                },
                mobile : {
                    customMaxlength : "请输入正确的联系方式",
                    digits : "请输入正确的联系方式"
                },
                emergencyMobile : {
                    digits : "请输入正确的家属联系方式 ",
                    customMaxlength : "请输入正确的家属联系方式 "
                },
            },
            showErrors : function(errorMap, errorList) {
                if (errorList.length > 0) {
                    $(errorList[0].element).focus();
                }
                this.defaultShowErrors();
            },
            errorPlacement : function(error, element) {
                var obj = getValidateErrorDisplayEl($(element));
                $(error).css("display", "block");
                obj.find("[data-error]").append(error);
            }
        });
    }
};