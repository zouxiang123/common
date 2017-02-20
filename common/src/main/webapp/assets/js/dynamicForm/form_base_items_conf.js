$(document).ready(function() {
    form_items_conf_obj.init();
});
/** 左补0 */
function lPad(str, bit) {
    if (isEmpty(bit)) {
        bit = 3;
    }
    var countLength = (str + "").length;
    for (var i = bit; i > countLength; i--) {
        str = "0" + str;
    }
    return str;
}
var form_items_conf_obj = {
    init : function() {
        form_items_conf_obj.resizeTree();
        form_items_conf_obj.addEvents();
        form_items_conf_obj.initTreeSettings();
        form_items_conf_obj.loadItemsTreeData();
        form_items_conf_obj.addValidate();
    },
    addEvents : function() {
        $("#sys_owner").on("change", function() {
            form_items_conf_obj.loadItemsTreeData();
        });
        $("#refresh").on("click", function() {
            if (isEmpty($("#sys_owner").val())) {
                return;
            }
            $.ajax({
                url : ctx + "/dynamicForm/formItems/refresh.shtml",
                data : encodeURI("sysOwner=" + $("#sys_owner").val()),
                type : "post",
                dataType : "json",
                loading : true,
                success : function(data) {
                    if (data.status == 1) {
                        showTips("刷新成功");
                    } else {
                        showWarn("刷新失败，请联系管理员");
                    }
                }
            });
        });
    },
    // tree显示区域设置
    resizeTree : function() {
        $(".tree-block").css("height", ($(window).height() - $("#contentDiv").offset().top - 50));
        $(".ztree").css("height", $(".tree-block").height() - 20);
    },
    /** 初始化树配置 */
    initTreeSettings : function() {
        var treeEvent = {
            // 添加按钮显示
            addHoverDom : function(treeId, treeNode) {
                if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0 || treeNode.level >= 6) {
                    return;
                }
                var sObj = $("#" + treeNode.tId + "_span");
                var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='添加' onfocus='this.blur();'></span>";
                sObj.after(addStr);
                var btn = $("#addBtn_" + treeNode.tId);
                if (btn) {
                    btn.on("click", function() {
                        var childs = treeNode.children;
                        var nodeId = 1;
                        if (!isEmptyObject(childs)) {// 获取最后一个节点的id
                            var lastId = childs[childs.length - 1].id;
                            nodeId = parseInt(lastId.substring(lastId.length - 3, lastId.length)) + 1;
                        }
                        nodeId = lPad(nodeId);
                        var node = {
                            id : treeNode.id + "" + nodeId,
                            pId : treeNode.id,
                            name : "子节点" + nodeId,
                            title : treeNode.name,
                            isFixed : false,
                            isNew : true
                        };
                        form_items_conf_obj.setItemFormData(node, treeNode);
                        // zTree.addNodes(treeNode, node);
                        return false;
                    });
                }
            },
            // 鼠标移走事件
            removeHoverDom : function(treeId, treeNode) {
                $("#addBtn_" + treeNode.tId).off().remove();
            },
            // 设置删除按钮显示
            setRemoveBtn : function(treeId, treeNode) {
                return !treeNode.isParent && !treeNode.isFixed;
            },
            // 角色树点击事件
            treeClick : function(event, treeId, treeNode) {
                if (treeNode) {
                    if (treeNode.pId == "0")
                        return;
                    form_items_conf_obj.setItemFormData(treeNode);
                }
            },
            /** 删除树节点数据 */
            beforeRemove : function(treeId, treeNode) {
                if (!treeNode.isNew) {
                    var flag = false;
                    $.ajax({
                        url : ctx + "/dynamicForm/formBaseItems/delItem.shtml",
                        type : "post",
                        data : encodeURI("itemCode=" + treeNode.id + "&sysOwner=" + $("#sys_owner").val()),
                        dataType : "json",
                        async : false,
                        loading : true,
                        success : function(data) {
                            if (data.status == 1) {
                                showTips("删除成功^_^");
                                flag = true;
                            } else if (data.status == 2) {
                                showWarn("删除失败，该项目正在使用");
                            } else if (data.status == 0) {
                                showWarn("项目不存在，请刷新后重试");
                            }
                        }
                    });
                    return flag;
                } else {
                    return true;
                }
            },
            beforeDrop : function(treeId, treeNodes, targetNode, moveType) {
                if (targetNode.level >= 5) {// 树的层级不能超过六层
                    showTips("超过最大允许的层级数");
                    return false;
                }
                // 拖拽的节点不是根节点时
                if (!(isEmptyObject(targetNode) || (moveType != "inner" && !targetNode.parentTId))) {
                    var index = 1;
                    if (!isEmptyObject(targetNode.children)) {// 获取树最后一个节点的id
                        var lastId = targetNode.children[targetNode.children.length - 1].id;
                        index = parseInt(lastId.substring(lastId.length - 3, lastId.length)) + 1;
                    }
                    for (var i = 0; i < treeNodes.length; i++) {
                        form_items_conf_obj.convertToTargetNode(treeNodes[i], targetNode.id + lPad(index + i), index + i);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        };
        var settings = {
            view : {
                addHoverDom : treeEvent.addHoverDom,
                removeHoverDom : treeEvent.removeHoverDom,
                selectedMulti : false,
                dblClickExpand : false
            },
            check : {
                enable : false
            },
            data : {
                key : {
                    title : "title" // 设置title提示信息对应的属性名称
                },
                simpleData : {
                    enable : true,
                    rootPId : "0"
                }
            },
            edit : {
                enable : true,
                removeTitle : "删除节点",
                renameTitle : "重命名",
                showRenameBtn : false,
                showRemoveBtn : treeEvent.setRemoveBtn,
                drag : {// 禁止拖拽
                    isCopy : false,
                    isMove : false
                }
            },
            callback : {
                onClick : treeEvent.treeClick,
                beforeRemove : treeEvent.beforeRemove,
                beforeDrop : treeEvent.beforeDrop
            }
        };
        $("#itemsTree").data("settings", settings);
    },
    convertToTargetNode : function(node, id) {
        node.tempId = null;// 清空数据库对应的id
        node.isNew = true;
        node.id = id;// 无需重新生成id
        if (!isEmptyObject(node.children)) {// 拖拽的节点包含子节点时
            for (var i = 0; i < node.children.length; i++) {
                node.children[i].pId = node.id;// 重新生成子节点pId
                id = node.id + lPad(i + 1);// 重新生成子节点Id
                form_items_conf_obj.convertToTargetNode(node.children[i], id);
            }
        }
    },
    /** 设置itemform内容 */
    setItemFormData : function(node, pNode) {
        $("#saveItem").removeClass("hide");
        if (isEmpty(pNode)) {
            $("#saveItemAndAdd").addClass("hide");
            $("#itemForm").removeData("pNode");
        } else {
            $("#saveItemAndAdd").removeClass("hide");
            $("#itemForm").data("pNode", pNode);
        }
        $("#itemForm").data("node", node);
        resetFormAndClearHidden("itemForm");
        node.isLeaf = !node.isParent;
        mappingFormData(form_items_conf_obj.convertNode(node), "itemForm");
    },
    /** 加载树数据 */
    loadItemsTreeData : function() {
        if (isEmpty($("#sys_owner").val())) {
            return;
        }
        var nodes = [];
        $.ajax({
            url : ctx + "/dynamicForm/formBaseItems/getItems.shtml",
            data : encodeURI("sysOwner=" + $("#sys_owner").val()),
            type : "post",
            dataType : "json",
            loading : true,
            success : function(data) {
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    // 将对象转换为tree对象
                    nodes.push(form_items_conf_obj.convertNode(item, true));
                }
                $.fn.zTree.init($("#itemsTree"), $("#itemsTree").data("settings"), nodes);
                var treeObj = $.fn.zTree.getZTreeObj("itemsTree");
                var expandNodes = treeObj.getNodesByParam("pId", "0");
                for (var i = 0; i < expandNodes.length; i++) {
                    treeObj.expandNode(expandNodes[i]);
                }
            }
        });
    },
    /** 保存节点 */
    saveItem : function(needAdd) {
        var pNode = $("#itemForm").data("pNode");
        if (needAdd && isEmpty(pNode)) {
            showTips("父节点不存在，不能复制");
            return false;
        }
        var owner = $("#sys_owner").val();
        if ($("#itemForm").valid()) {
            $.ajax({
                url : ctx + "/dynamicForm/formBaseItems/saveItem.shtml",
                type : "post",
                // contentType : 'application/json;charset=utf-8',
                data : $("#itemForm").serialize() + encodeURI("&sysOwner=" + owner),
                dataType : "json",
                loading : true,
                success : function(data) {
                    if (data.status == 1) {
                        showTips("保存成功^_^");
                        form_items_conf_obj.addOrUpdateNode(form_items_conf_obj.convertNode(data.item, true), pNode, $("#itemForm").data("node"));
                        if (needAdd) {
                            data.item.id = "";
                            data.item.fkCode = "";
                            var lastId = data.item.itemCode;
                            data.item.itemCode = lastId.substring(0, lastId.length - 3)
                                            + lPad(parseInt(lastId.substring(lastId.length - 3, lastId.length)) + 1);
                            data.item.itemName = "";
                            resetFormAndClearHidden("itemForm");
                            mappingFormData(data.item, "itemForm");
                        }
                    } else if (data.status == 2) {
                        showWarn("保存失败，请刷新后重试");
                    }
                }
            });
        }
    },
    /** 添加或者更新节点 */
    addOrUpdateNode : function(node, pNode, oldNode) {
        var zTree = $.fn.zTree.getZTreeObj("itemsTree");
        if (isEmpty(pNode)) {
            $.extend(oldNode, node);
            zTree.updateNode(oldNode);
            if (!isEmptyObject(oldNode.children)) {
                for (var i = 0; i < oldNode.children.length; i++) {
                    oldNode.children[i].title = oldNode.name;
                    zTree.updateNode(oldNode.children[i]);
                }
            }
        } else {
            zTree.addNodes(pNode, node);
        }
    },
    /** 将后台对象转为tree对象 */
    convertNode : function(item, toTree) {
        var treeFields = [ "id", "pId", "name", "title", "tempId" ];
        var objFields = [ "itemCode", "pItemCode", "itemName", "itemDesc", "id" ];
        var extraFields = [ "createTime", "createUserId", "updateTime", "updateUserId" ];// 不需要mapping的字段

        var fromFields;
        var toFields;
        if (toTree) {
            fromFields = objFields;
            toFields = treeFields;
        } else {
            fromFields = treeFields;
            toFields = objFields;
        }
        var node = {};
        var mappedCnt = 0;
        point: for ( var key in item) {
            if (mappedCnt <= fromFields.length) {// 已经转换的总数小于等于需要转换的总数时
                for (var i = 0; i < fromFields.length; i++) {
                    if (key == fromFields[i]) {
                        node[toFields[i]] = item[key];
                        mappedCnt++;
                        continue point;
                    }
                }
            }
            for (var i = 0; i < extraFields.length; i++) {// 额外字段
                if (key == extraFields[i]) {
                    continue point;
                }
            }
            node[key] = item[key];
        }
        node.isNew = false;
        return node;
    },
    // 添加校验
    addValidate : function() {
        $('#itemForm').validate({
            onfocusout : false,
            // 校验字段
            rules : {
                itemName : {
                    required : [ "名称" ]
                },
                itemType : {
                    required : [ "控件类型" ]
                },
                dataType : {
                    required : [ "数据类型" ]
                },
                groupTag : {
                    isNumberOr_Letter : [ "数据类型" ]
                },
                score : {
                    customRange : [ -99999, 99999, "分值" ]
                },
                maxLength : {
                    required : [ "最大长度" ],
                    digits : [ "最大长度" ],
                    customRange : [ 1, 512, "最大长度" ]
                }
            },
            // 全部校验结果
            showErrors : function(errorMap, errorList) {
                showSystemDialog(errorMap);
            },
            submitHandler : function(form) {
                // form.onsubmit();
            }
        });
    }
};
