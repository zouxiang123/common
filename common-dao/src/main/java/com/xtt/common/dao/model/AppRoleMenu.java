package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_role_menu
 */
public class AppRoleMenu {
    /**
     * app_role_menu.id
     */
    private Long id;

    /**
     * app_role_menu.fk_role_id
     */
    private Long fkRoleId;

    /**
     * app_role_menu.fk_menu_key
     */
    private String fkMenuKey;

    /**
     * 创建时间
     * app_role_menu.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * app_role_menu.create_user_id
     */
    private Long createUserId;

    /**
     * 更新人
     * app_role_menu.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * app_role_menu.update_user_id
     */
    private Long updateUserId;

    /**
     * 租户号
     * app_role_menu.fk_tenant_id
     */
    private Integer fkTenantId;

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
    public Long getFkRoleId() {
        return fkRoleId;
    }

    /**
     */
    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    /**
     */
    public String getFkMenuKey() {
        return fkMenuKey;
    }

    /**
     */
    public void setFkMenuKey(String fkMenuKey) {
        this.fkMenuKey = fkMenuKey;
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

    /**
     * 租户号
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户号
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }
}