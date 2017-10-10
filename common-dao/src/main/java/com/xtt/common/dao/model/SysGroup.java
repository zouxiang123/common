package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_group
 */
public class SysGroup {
    /**
     * sys_group.id
     */
    private Integer id;

    /**
     * 集团名称
     * sys_group.group_name
     */
    private String groupName;

    /**
     * 创建时间
     * sys_group.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_group.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sys_group.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sys_group.update_user_id
     */
    private Long updateUserId;

    /**
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 集团名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 集团名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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