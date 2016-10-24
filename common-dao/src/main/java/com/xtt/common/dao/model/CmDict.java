package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_dict
 */
public class CmDict {
    /**
     * 物理主键
     * cm_dict.id
     */
    private Long id;

    /**
     * 父节点
     * cm_dict.p_item_code
     */
    private String pItemCode;

    /**
     * 类型。如性别(sex)、状态(status)
     * cm_dict.item_code
     */
    private String itemCode;

    /**
     * 页面显示
     * cm_dict.item_name
     */
    private String itemName;

    /**
     * 类型描述
     * cm_dict.item_desc
     */
    private String itemDesc;

    /**
     * 排序
     * cm_dict.order_by
     */
    private Integer orderBy;

    /**
     * 是否可用
     * cm_dict.is_enable
     */
    private Boolean isEnable;

    /**
     * 操作人
     * cm_dict.operator_id
     */
    private Long operatorId;

    /**
     * 租户id
     * cm_dict.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * cm_dict.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * cm_dict.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间
     * cm_dict.update_time
     */
    private Date updateTime;

    /**
     * 修改人
     * cm_dict.update_user_id
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
     * 父节点
     */
    public String getpItemCode() {
        return pItemCode;
    }

    /**
     * 父节点
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
     * 页面显示
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 页面显示
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 类型描述
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * 类型描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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
     * 操作人
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 操作人
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
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