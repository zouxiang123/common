package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_serial_number
 */
public class PatientSerialNumber {
    /**
     * patient_serial_number.id
     */
    private Long id;

    /**
     * 序列号码 patient_serial_number.serial_num
     */
    private String serialNum;

    /**
     * 是否已经使用 patient_serial_number.is_use
     */
    private Boolean isUse;

    /**
     * patient_serial_number.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * patient_serial_number.create_time
     */
    private Date createTime;

    /**
     * patient_serial_number.create_user_id
     */
    private Long createUserId;

    /**
     * patient_serial_number.update_time
     */
    private Date updateTime;

    /**
     * patient_serial_number.update_user_id
     */
    private Long updateUserId;

    /**
     */
    public Long getId() {
        return id;
    }

    /**
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 序列号码
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * 序列号码
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * 是否已经使用
     */
    public Boolean getIsUse() {
        return isUse;
    }

    /**
     * 是否已经使用
     */
    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    /**
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    /**
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}