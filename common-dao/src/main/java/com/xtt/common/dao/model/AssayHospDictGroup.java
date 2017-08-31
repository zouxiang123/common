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
     * 组ID
     * assay_hosp_dict_group.group_id
     */
    private String groupId;

    /**
     * 组名称
     * assay_hosp_dict_group.group_name
     */
    private String groupName;

    /**
     * 是否自动生成标识
     * assay_hosp_dict_group.is_auto
     */
    private Boolean isAuto;

    /**
     * 租户ID
     * assay_hosp_dict_group.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * assay_hosp_dict_group.create_time
     */
    private Date createTime;

    /**
     * 创建人ID
     * assay_hosp_dict_group.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * assay_hosp_dict_group.update_time
     */
    private Date updateTime;

    /**
     * 更新人id
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
     * 组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 是否自动生成标识
     */
    public Boolean getIsAuto() {
        return isAuto;
    }

    /**
     * 是否自动生成标识
     */
    public void setIsAuto(Boolean isAuto) {
        this.isAuto = isAuto;
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
     * 创建人ID
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人ID
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
     * 更新人id
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 更新人id
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}