package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sync_group
 */
public class SyncGroup {
    /**
     * 主键
     * sync_group.id
     */
    private Long id;

    /**
     * 同步组名
     * sync_group.sg_name
     */
    private String sgName;

    /**
     * 集团id
     * sync_group.fk_group_id
     */
    private Integer fkGroupId;

    /**
     * 创建时间
     * sync_group.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sync_group.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sync_group.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sync_group.update_user_id
     */
    private Long updateUserId;

    /**
     * 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 同步组名
     */
    public String getSgName() {
        return sgName;
    }

    /**
     * 同步组名
     */
    public void setSgName(String sgName) {
        this.sgName = sgName;
    }

    /**
     * 集团id
     */
    public Integer getFkGroupId() {
        return fkGroupId;
    }

    /**
     * 集团id
     */
    public void setFkGroupId(Integer fkGroupId) {
        this.fkGroupId = fkGroupId;
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