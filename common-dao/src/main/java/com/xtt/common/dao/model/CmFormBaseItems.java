package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * cm_form_base_items
 */
public class CmFormBaseItems {
    /**
     * 物理主键 cm_form_base_items.id
     */
    private Long id;

    /**
     * 父编号 cm_form_base_items.p_item_code
     */
    private String pItemCode;

    /**
     * 编号 cm_form_base_items.item_code
     */
    private String itemCode;

    /**
     * 名称 cm_form_base_items.item_name
     */
    private String itemName;

    /**
     * cm_form_base_items.item_desc
     */
    private String itemDesc;

    /**
     * 组件类型(checkbox、input、textarea)等等 cm_form_base_items.item_type
     */
    private String itemType;

    /**
     * 单位 cm_form_base_items.unit
     */
    private String unit;

    /**
     * 是否需要打分 cm_form_base_items.need_score
     */
    private Boolean needScore;

    /**
     * 分值 cm_form_base_items.score
     */
    private BigDecimal score;

    /**
     * 最小值 cm_form_base_items.min_value
     */
    private BigDecimal minValue;

    /**
     * 最大值 cm_form_base_items.max_value
     */
    private BigDecimal maxValue;

    /**
     * 是否为叶子节点 cm_form_base_items.is_leaf
     */
    private Boolean isLeaf;

    /**
     * 是否固定项 cm_form_base_items.is_fixed
     */
    private Boolean isFixed;

    /**
     * 关联的编号 cm_form_base_items.fk_code
     */
    private String fkCode;

    /**
     * 组标签 cm_form_base_items.group_tag
     */
    private String groupTag;

    /**
     * 数据类型 cm_form_base_items.data_type
     */
    private String dataType;

    /**
     * 所属系统 cm_form_base_items.sys_owner
     */
    private String sysOwner;

    /**
     * 是否有效 cm_form_base_items.is_enable
     */
    private Boolean isEnable;

    /**
     * 子节点是否是value节点 cm_form_base_items.child_value_node
     */
    private Boolean childValueNode;

    /**
     * 租户id cm_form_base_items.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_form_base_items.create_time
     */
    private Date createTime;

    /**
     * 创建人 cm_form_base_items.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_form_base_items.update_time
     */
    private Date updateTime;

    /**
     * 更新人 cm_form_base_items.update_user_id
     */
    private Long updateUserId;

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
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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
     * 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 是否需要打分
     */
    public Boolean getNeedScore() {
        return needScore;
    }

    /**
     * 是否需要打分
     */
    public void setNeedScore(Boolean needScore) {
        this.needScore = needScore;
    }

    /**
     * 分值
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * 分值
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * 最小值
     */
    public BigDecimal getMinValue() {
        return minValue;
    }

    /**
     * 最小值
     */
    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    /**
     * 最大值
     */
    public BigDecimal getMaxValue() {
        return maxValue;
    }

    /**
     * 最大值
     */
    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
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
     * 是否固定项
     */
    public Boolean getIsFixed() {
        return isFixed;
    }

    /**
     * 是否固定项
     */
    public void setIsFixed(Boolean isFixed) {
        this.isFixed = isFixed;
    }

    /**
     * 关联的编号
     */
    public String getFkCode() {
        return fkCode;
    }

    /**
     * 关联的编号
     */
    public void setFkCode(String fkCode) {
        this.fkCode = fkCode;
    }

    /**
     * 组标签
     */
    public String getGroupTag() {
        return groupTag;
    }

    /**
     * 组标签
     */
    public void setGroupTag(String groupTag) {
        this.groupTag = groupTag;
    }

    /**
     * 数据类型
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 数据类型
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
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
     * 子节点是否是value节点
     */
    public Boolean getChildValueNode() {
        return childValueNode;
    }

    /**
     * 子节点是否是value节点
     */
    public void setChildValueNode(Boolean childValueNode) {
        this.childValueNode = childValueNode;
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

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新人
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}