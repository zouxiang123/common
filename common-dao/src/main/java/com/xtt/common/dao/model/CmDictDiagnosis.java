package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_dict_diagnosis
 */
public class CmDictDiagnosis {
    /**
     * 物理主键
     * cm_dict_diagnosis.id
     */
    private Long id;

    /**
     * 父类型
     * cm_dict_diagnosis.p_item_code
     */
    private String pItemCode;

    /**
     * 类型。如性别(sex)、状态(status)
     * cm_dict_diagnosis.item_code
     */
    private String itemCode;

    /**
     * 页面显示名称
     * cm_dict_diagnosis.item_name
     */
    private String itemName;

    /**
     * 项目类型（HD：血透 PD：腹透）
     * cm_dict_diagnosis.item_type
     */
    private String itemType;

    /**
     * 单位
     * cm_dict_diagnosis.unit
     */
    private String unit;

    /**
     * 数据类型
     * cm_dict_diagnosis.data_type
     */
    private String dataType;

    /**
     * 是否叶子节点
     * cm_dict_diagnosis.is_leaf
     */
    private Boolean isLeaf;

    /**
     * 树状层级
     * cm_dict_diagnosis.level
     */
    private Integer level;

    /**
     * 排序
     * cm_dict_diagnosis.order_by
     */
    private Integer orderBy;

    /**
     * 是否固定项
     * cm_dict_diagnosis.is_fixed
     */
    private Boolean isFixed;

    /**
     * 是否可用
     * cm_dict_diagnosis.is_enable
     */
    private Boolean isEnable;

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     * cm_dict_diagnosis.sys_owner
     */
    private String sysOwner;

    /**
     * 租户id
     * cm_dict_diagnosis.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * cm_dict_diagnosis.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * cm_dict_diagnosis.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间
     * cm_dict_diagnosis.update_time
     */
    private Date updateTime;

    /**
     * 修改人
     * cm_dict_diagnosis.update_user_id
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
     * 父类型
     */
    public String getpItemCode() {
        return pItemCode;
    }

    /**
     * 父类型
     */
    public void setpItemCode(String pItemCode) {
        this.pItemCode = pItemCode;
    }

    /**
     * 类型。如性别(sex)、状态(status)
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 类型。如性别(sex)、状态(status)
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 页面显示名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 页面显示名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 项目类型（HD：血透 PD：腹透）
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 项目类型（HD：血透 PD：腹透）
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
     * 是否叶子节点
     */
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    /**
     * 是否叶子节点
     */
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 树状层级
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 树状层级
     */
    public void setLevel(Integer level) {
        this.level = level;
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
     * 是否可用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否可用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
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
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 修改人
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 修改人
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}