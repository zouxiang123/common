package com.xtt.common.dao.model;

import java.util.Date;

import com.xtt.platform.framework.core.model.MyBatisSuperModel;

/**
 * patient_label
 */
public class PatientLabel extends MyBatisSuperModel {
    /**
     * 系统ID patient_label.id
     */
    private Long id;

    /**
     * 名称 patient_label.name
     */
    private String name;

    /**
     * 排序 patient_label.order_by
     */
    private Integer orderBy;

    /**
     * 租户ID patient_label.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 所属系统 patient_label.sys_owner
     */
    private String sysOwner;

    /**
     * 创建时间 patient_label.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_label.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_label.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_label.update_user_id
     */
    private Long updateUserId;

    /**
     * 系统ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 系统ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 租户ID
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户ID
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
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