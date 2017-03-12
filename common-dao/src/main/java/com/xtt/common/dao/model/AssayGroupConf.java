package com.xtt.common.dao.model;

import java.util.Date;

/**
 * assay_group_conf
 */
public class AssayGroupConf {
    /**
     * assay_group_conf.id
     */
    private Long id;

    /**
     * 分组名称
     * assay_group_conf.name
     */
    private String name;

    /**
     * assay_group_conf.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * assay_group_conf.create_time
     */
    private Date createTime;

    /**
     * assay_group_conf.create_user_id
     */
    private Long createUserId;

    /**
     * assay_group_conf.update_time
     */
    private Date updateTime;

    /**
     * assay_group_conf.update_user_id
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
     * 分组名称
     */
    public String getName() {
        return name;
    }

    /**
     * 分组名称
     */
    public void setName(String name) {
        this.name = name;
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