package com.xtt.common.dto;

import java.util.Date;

/**
 * app_patient_param
 */
public class AppPatientParamDto {
    /**
     * app_patient_param.id
     */
    private Long id;

    /**
     * 用户id app_patient_param.fk_user_id
     */
    private Long fkUserId;

    /**
     * itemCode app_patient_param.item_code
     */
    private String itemCode;

    /**
     * item_name app_patient_param.item_name
     */
    private String itemName;

    /**
     * 描述 app_patient_param.item_desc
     */
    private String itemDesc;

    /**
     * 值 app_patient_param.item_value
     */
    private String itemValue;

    /**
     * 租户id app_patient_param.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 app_patient_param.create_time
     */
    private Date createTime;

    /**
     * app_patient_param.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 app_patient_param.update_time
     */
    private Date updateTime;

    /**
     * 更新人 app_patient_param.update_user_id
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
     * 用户id
     */
    public Long getFkUserId() {
        return fkUserId;
    }

    /**
     * 用户id
     */
    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    /**
     * itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * itemCode
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * item_name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * item_name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 描述
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * 描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * 值
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 值
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
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