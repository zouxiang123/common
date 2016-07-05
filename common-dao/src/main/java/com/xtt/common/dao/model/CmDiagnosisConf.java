package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_diagnosis_conf
 */
public class CmDiagnosisConf {
    /**
     * 物理主键
     * cm_diagnosis_conf.id
     */
    private Long id;

    /**
     * 父编号
     * cm_diagnosis_conf.p_item_code
     */
    private String pItemCode;

    /**
     * 编号
     * cm_diagnosis_conf.item_code
     */
    private String itemCode;

    /**
     * 是否为叶子节点
     * cm_diagnosis_conf.is_leaf
     */
    private Boolean isLeaf;

    /**
     * 排序
     * cm_diagnosis_conf.order_by
     */
    private Integer orderBy;

    /**
     * 所属系统（HD：血透 PD：腹透）多系统","分割
     * cm_diagnosis_conf.sys_owner
     */
    private String sysOwner;

    /**
     * 是否有效
     * cm_diagnosis_conf.is_enable
     */
    private Boolean isEnable;

    /**
     * 租户id
     * cm_diagnosis_conf.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * cm_diagnosis_conf.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * cm_diagnosis_conf.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * cm_diagnosis_conf.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * cm_diagnosis_conf.update_user_id
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