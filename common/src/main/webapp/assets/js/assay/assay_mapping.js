var assay_mapping = {
    /**
     * 化验项
     */
    assayItems : [],
    /**
     * 初始化化验关联页面
     * 
     */
    init : function() {
        // 设置关联dialog宽度为800px
        $("#assayMappingDialog").css("margin", "0px auto");
        $("#assayMappingDialog .modal-dialog").css("width", "800px");
        // 设置关联dialog 内容高度
        var dialog_height = 600;
        $("#assayMappingDialog .modal-content").css("height", dialog_height + "px");
        $("#assayMappingDialog .dialog-wrap .list-group").css("height", (dialog_height - 118) + "px");

        // 清空tablebody数据
        this.clearTableBody();
        this.addEvents();
        $(window).resize();
        this.getTableBody();
    },
    addEvents : function() {
        // 动态设置高度
        $(window).on("resize", function() {
            var displayHeight = $(window).height() - $("#assayMappingDiv").offset().top - 20;
            $("#assayTable").css("max-height", displayHeight - 15);
        });
        // 化验项table点击事件
        $("#sysAssayTableBody,#hospitalAssayTableBody").on("click", "tr", function() {
            $(this).addClass("active").siblings().removeClass("active");
        });
    },
    /** 清空tableBody的值 */
    clearTableBody : function() {
        $("#assayTableBody").html("");
        $("#assayMappingBody").html("");
    },
    /** 获取tableBody数据 */
    getTableBody : function() {
        assay_mapping.getAssayTableBody();
    },
    /** 显示添加项dialog */
    showAddDialog : function(dialogId) {
        $("#" + dialogId).find("form")[0].reset();
        $("#" + dialogId).find("form input[name='id']").val("");
        $("#" + dialogId).modal("show");
    },
    /** 获取化验table数据 */
    getAssayTableBody : function() {
        $.ajax({
            url : ctx + "/assay/hospDict/listAllBasic.shtml",
            type : "post",
            dataType : "json",
            success : function(data) {
                var bodyHtml = "";
                var mappingHtml = "";
                var mappedCodes = {};
                if (data.status == 1) {
                    assay_mapping.assayItems = data.items;
                    for (var i = 0; i < data.items.length; i++) {
                        var item = data.items[i];
                        // 已经关联的项目
                        if (!isEmpty(item.fkDictCode)) {
                            mappedCodes[item.fkDictCode] = true;
                            bodyHtml += '<tr>';
                            // bodyHtml += '<td class="text-center">'+convertEmpty(item.groupId)+'</td>';
                            bodyHtml += '<td class="text-center">' + convertEmpty(item.itemCode) + '</td>';
                            bodyHtml += '<td class="text-center">' + convertEmpty(item.itemName) + '</td>';
                            bodyHtml += '<td class="text-center">' + convertEmpty(item.reference) + '</td>';
                            bodyHtml += '<td class="text-center">' + convertEmpty(item.unit) + '</td>';
                            bodyHtml += '<td class="text-center">' + convertEmpty(item.fkDictCode) + '</td>';
                            bodyHtml += '<td class="text-center">' + convertEmpty(item.fkItemName) + '</td>';
                            bodyHtml += '<td>';
                            bodyHtml += '<button type="button" class="btn btn-def pull-right " onclick="assay_mapping.deleteAssayMapping(' + item.id
                                            + ')">解除关联</button>';
                            bodyHtml += '</td>';
                            bodyHtml += '</tr>';
                        } else {
                            mappingHtml += '<tr data-id="' + item.id + '">';
                            mappingHtml += '<td class="text-center">' + convertEmpty(item.itemCode) + '</td>';
                            mappingHtml += '<td class="text-center">' + convertEmpty(item.itemName) + '</td>';
                            mappingHtml += '</tr>';
                        }
                    }
                }
                // 生成可关联的项目
                assay_mapping.getAssayMappingData(mappedCodes);
                $("#hospitalAssayTableBody").html(mappingHtml);
                $("#assayTableBody").html(bodyHtml);
                $('#hospAssaySerch').fastLiveFilter('#hospitalAssayTableBody', {
                    timeout : 300
                });
            }
        });
    },
    /** 获取化验单对应关系数据 */
    getAssayMappingData : function(mappedCodes) {
        $.ajax({
            url : ctx + "/assay/assayMapping/getPatientAssayDict.shtml",
            type : "post",
            dataType : "json",
            success : function(data) {
                var bodyHtml = '';
                if (data.status == 1) {
                    for (var i = 0; i < data.items.length; i++) {
                        var item = data.items[i];
                        // 如果当前项目已经关联，则不允许再关联
                        /*if (mappedCodes[item.itemCode])
                            continue;*/
                        bodyHtml += '<tr data-code="' + item.itemCode + '">';
                        bodyHtml += '<td class="text-center">' + convertEmpty(item.itemCode) + '</td>';
                        bodyHtml += '<td class="text-center">' + convertEmpty(item.itemName) + '</td>';
                        bodyHtml += '</tr>';
                    }
                }
                $("#sysAssayTableBody").html(bodyHtml);
                $('#sysAssaySerch').fastLiveFilter('#sysAssayTableBody', {
                    timeout : 300
                });
            }
        });
    },
    /** 保存化验单数据 */
    saveAssay : function(formId, dialogId, needHide) {
        if ($("#" + formId).valid()) {
            $.ajax({
                url : $("#" + formId).attr("action"),
                type : "post",
                data : $("#" + formId).serialize(),
                dataType : "json",
                loading : true,
                success : function(data) {
                    if (data.status == 1) {
                        if (needHide) {
                            assay_mapping.getTableBody();
                            $("#" + dialogId).modal("hide");
                        } else {
                            window.location.reload(true);
                        }
                    } else if (data.status == 2) {
                        showWarn("排序字段不能重复");
                    } else if (data.status == 0) {
                        showError("请填写所有必填项");
                    }
                }
            });
        }
    },
    /** 解除关联 */
    deleteAssayMapping : function(id) {
        $.ajax({
            url : ctx + "/assay/assayMapping/deleteAssayMapping.shtml",
            type : "post",
            data : "id=" + id,
            dataType : "json",
            loading : true,
            success : function(data) {
                if (data.status == 1) {
                    assay_mapping.getAssayTableBody();
                    showAlert("解除关联成功");
                } else if (data.status == 2) {
                    showWarn("固定项不能删除");
                }
            }
        });
    },
    /** 建立关联 */
    mappingAssayDict : function() {
        var id = $("#hospitalAssayTableBody tr.active").attr("data-id");
        var fkDictCode = $("#sysAssayTableBody tr.active").attr("data-code");
        if (isEmpty(id)) {
            showWarn("请选择需要关联的项");
            return false;
        }
        if (isEmpty(fkDictCode)) {
            showWarn("请选择关联的对象");
            return false;
        }
        $.ajax({
            url : ctx + "/assay/assayMapping/updateDict.shtml",
            type : "post",
            data : "id=" + id + "&fkDictCode=" + fkDictCode,
            dataType : "json",
            loading : true,
            success : function(data) {
                if (data.status == 1) {
                    assay_mapping.getAssayTableBody();
                    showAlert("关联成功");
                } else if (data.status == 2) {
                    showWarn("该关联项已存在");
                }
            }
        });
    }
};
