package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_assay_prop_conf
 */
public class PatientAssayPropConf {
    /**
     * 主键id
     * patient_assay_prop_conf.id
     */
    private Long id;

    /**
     * 化验项code
     * patient_assay_prop_conf.item_code
     */
    private String itemCode;

    /**
     * 化验项名称
     * patient_assay_prop_conf.item_name
     */
    private String itemName;

    /**
     * 分组id
     * patient_assay_prop_conf.group_id
     */
    private String groupId;

    /**
     * 分组名称
     * patient_assay_prop_conf.group_name
     */
    private String groupName;

    /**
     * 租户id
     * patient_assay_prop_conf.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * patient_assay_prop_conf.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * patient_assay_prop_conf.create_user_id
     */
    private Long createUserId;

    /**
     * 更新人
     * patient_assay_prop_conf.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * patient_assay_prop_conf.update_user_id
     */
    private Long updateUserId;

    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 化验项code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 化验项code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 化验项名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 化验项名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 分组id
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 分组id
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
     * 更新人
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新人
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