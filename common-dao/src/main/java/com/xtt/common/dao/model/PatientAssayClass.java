package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_assay_class
 */
public class PatientAssayClass {
    /**
     * patient_assay_class.id
     */
    private Long id;

    /**
     * patient_assay_class.assay_class
     */
    private String assayClass;
    /**
     * 化验项提醒天数
     */
    private Integer assayDay;

    /**
     * patient_assay_class.item_code
     */
    private String itemCode;

    /**
     * patient_assay_class.item_name
     */
    private String itemName;

    /**
     * patient_assay_class.group_id
     */
    private String groupId;

    /**
     * patient_assay_class.group_name
     */
    private String groupName;

    /**
     * 同类分组编号
     * patient_assay_class.fk_assay_group_conf_id
     */
    private Long fkAssayGroupConfId;

    /**
     * 同类分组名称
     * patient_assay_class.fk_assay_group_conf_name
     */
    private String fkAssayGroupConfName;

    /**
     * patient_assay_class.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * patient_assay_class.create_time
     */
    private Date createTime;

    /**
     * patient_assay_class.create_user_id
     */
    private Long createUserId;

    /**
     * patient_assay_class.update_time
     */
    private Date updateTime;

    /**
     * patient_assay_class.update_user_id
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
     */
    public String getAssayClass() {
        return assayClass;
    }

    /**
     */
    public void setAssayClass(String assayClass) {
        this.assayClass = assayClass;
    }

    /**
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     */
    public String getItemName() {
        return itemName;
    }

    /**
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 同类分组编号
     */
    public Long getFkAssayGroupConfId() {
        return fkAssayGroupConfId;
    }

    /**
     * 同类分组编号
     */
    public void setFkAssayGroupConfId(Long fkAssayGroupConfId) {
        this.fkAssayGroupConfId = fkAssayGroupConfId;
    }

    /**
     * 同类分组名称
     */
    public String getFkAssayGroupConfName() {
        return fkAssayGroupConfName;
    }

    /**
     * 同类分组名称
     */
    public void setFkAssayGroupConfName(String fkAssayGroupConfName) {
        this.fkAssayGroupConfName = fkAssayGroupConfName;
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

	public Integer getAssayDay() {
		return assayDay;
	}

	public void setAssayDay(Integer assayDay) {
		this.assayDay = assayDay;
	}
    
}