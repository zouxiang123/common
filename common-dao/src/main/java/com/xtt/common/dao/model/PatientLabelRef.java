package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_label_ref
 */
public class PatientLabelRef {
    /**
     * 系统ID
     * patient_label_ref.id
     */
    private Long id;

    /**
     * 标签ID
     * patient_label_ref.fk_label_id
     */
    private Long fkLabelId;

    /**
     * 患者ID
     * patient_label_ref.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 排序
     * patient_label_ref.order_by
     */
    private Integer orderBy;

    /**
     * 租户ID
     * patient_label_ref.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 所属系统
     * patient_label_ref.sys_owner
     */
    private String sysOwner;

    /**
     * 创建时间
     * patient_label_ref.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * patient_label_ref.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * patient_label_ref.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * patient_label_ref.update_user_id
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
     * 标签ID
     */
    public Long getFkLabelId() {
        return fkLabelId;
    }

    /**
     * 标签ID
     */
    public void setFkLabelId(Long fkLabelId) {
        this.fkLabelId = fkLabelId;
    }

    /**
     * 患者ID
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 患者ID
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
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