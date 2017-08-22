package com.xtt.common.dao.model;

import java.util.Date;

/**
 * assay_hosp_dict_group
 */
public class AssayHospDictGroup {
    /**
     * assay_hosp_dict_group.id
     */
    private Long id;

    /**
     * assay_hosp_dict_group.group_id
     */
    private String groupId;

    /**
     * assay_hosp_dict_group.group_name
     */
    private String groupName;

    /**
     * assay_hosp_dict_group.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * assay_hosp_dict_group.create_time
     */
    private Date createTime;

    /**
     * assay_hosp_dict_group.create_user_id
     */
    private Long createUserId;

    /**
     * assay_hosp_dict_group.update_time
     */
    private Date updateTime;

    /**
     * assay_hosp_dict_group.update_user_id
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