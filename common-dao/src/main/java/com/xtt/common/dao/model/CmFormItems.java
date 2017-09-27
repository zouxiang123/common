package com.xtt.common.dao.model;

/**
 * cm_form_items
 */
public class CmFormItems {
    /**
     * 物理主键 cm_form_items.id
     */
    private Long id;

    /**
     * 表单主表id cm_form_items.fk_form_id
     */
    private Long fkFormId;

    /**
     * 名称 cm_form_items.item_name
     */
    private String itemName;

    /**
     * 父编号 cm_form_items.p_item_code
     */
    private String pItemCode;

    /**
     * 编号 cm_form_items.item_code
     */
    private String itemCode;

    /**
     * 排序 cm_form_items.order_by
     */
    private Integer orderBy;

    /**
     * 所属系统 cm_form_items.sys_owner
     */
    private String sysOwner;

    /**
     * 节点路径，以,分隔 cm_form_items.item_path
     */
    private String itemPath;

    /**
     * 节点层级 cm_form_items.item_level
     */
    private Integer itemLevel;

    /**
     * 是否为叶子节点 cm_form_items.is_leaf
     */
    private Boolean isLeaf;

    /**
     * 是否有效 cm_form_items.is_enable
     */
    private Boolean isEnable;

    /**
     * 显示样式 cm_form_items.display_style
     */
    private String displayStyle;

    /**
     * 显示宽度(px or %） cm_form_items.display_width
     */
    private String displayWidth;

    /**
     * 显示的列数（1~12） cm_form_items.display_col
     */
    private Integer displayCol;

    /**
     * 组件类型(checkbox、input、textarea)等等 cm_form_items.item_type
     */
    private String itemType;

    /**
     * 租户id cm_form_items.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 物理主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 物理主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 表单主表id
     */
    public Long getFkFormId() {
        return fkFormId;
    }

    /**
     * 表单主表id
     */
    public void setFkFormId(Long fkFormId) {
        this.fkFormId = fkFormId;
    }

    /**
     * 名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 父编号
     */
    public String getpItemCode() {
        return pItemCode;
    }

    /**
     * 父编号
     */
    public void setpItemCode(String pItemCode) {
        this.pItemCode = pItemCode;
    }

    /**
     * 编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 排序
     */
    public Integer getOrderBy() {
        return orderBy;
    }

    /**
     * 排序
     */
    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 所属系统
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    /**
     * 节点路径，以,分隔
     */
    public String getItemPath() {
        return itemPath;
    }

    /**
     * 节点路径，以,分隔
     */
    public void setItemPath(String itemPath) {
        this.itemPath = itemPath;
    }

    /**
     * 节点层级
     */
    public Integer getItemLevel() {
        return itemLevel;
    }

    /**
     * 节点层级
     */
    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    /**
     * 是否为叶子节点
     */
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    /**
     * 是否为叶子节点
     */
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 是否有效
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否有效
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 显示样式
     */
    public String getDisplayStyle() {
        return displayStyle;
    }

    /**
     * 显示样式
     */
    public void setDisplayStyle(String displayStyle) {
        this.displayStyle = displayStyle;
    }

    /**
     * 显示宽度(px or %）
     */
    public String getDisplayWidth() {
        return displayWidth;
    }

    /**
     * 显示宽度(px or %）
     */
    public void setDisplayWidth(String displayWidth) {
        this.displayWidth = displayWidth;
    }

    /**
     * 显示的列数（1~12）
     */
    public Integer getDisplayCol() {
        return displayCol;
    }

    /**
     * 显示的列数（1~12）
     */
    public void setDisplayCol(Integer displayCol) {
        this.displayCol = displayCol;
    }

    /**
     * 组件类型(checkbox、input、textarea)等等
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 组件类型(checkbox、input、textarea)等等
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * 租户id
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户id
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }
}