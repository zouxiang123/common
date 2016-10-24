package com.xtt.common.dao.model;

/**
 * cm_form_value
 */
public class CmFormValue {
    /**
     * 物理主键
     * cm_form_value.id
     */
    private Long id;

    /**
     * 记录id
     * cm_form_value.fk_record_id
     */
    private Long fkRecordId;

    /**
     * 记录id
     * cm_form_value.fk_form_id
     */
    private Long fkFormId;

    /**
     * 编号
     * cm_form_value.item_code
     */
    private String itemCode;

    /**
     * 检测项的值
     * cm_form_value.item_value
     */
    private String itemValue;

    /**
     * 租户id
     * cm_form_value.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 所属系统
     * cm_form_value.sys_owner
     */
    private String sysOwner;

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
     * 记录id
     */
    public Long getFkRecordId() {
        return fkRecordId;
    }

    /**
     * 记录id
     */
    public void setFkRecordId(Long fkRecordId) {
        this.fkRecordId = fkRecordId;
    }

    /**
     * 记录id
     */
    public Long getFkFormId() {
        return fkFormId;
    }

    /**
     * 记录id
     */
    public void setFkFormId(Long fkFormId) {
        this.fkFormId = fkFormId;
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
     * 检测项的值
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 检测项的值
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
}