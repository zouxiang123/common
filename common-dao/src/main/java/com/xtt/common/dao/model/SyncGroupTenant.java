package com.xtt.common.dao.model;

/**
 * sync_group_tenant
 */
public class SyncGroupTenant {
    /**
     * 租户id
     * sync_group_tenant.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 同步组
     * sync_group_tenant.fk_sync_group_id
     */
    private Long fkSyncGroupId;

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
     * 同步组
     */
    public Long getFkSyncGroupId() {
        return fkSyncGroupId;
    }

    /**
     * 同步组
     */
    public void setFkSyncGroupId(Long fkSyncGroupId) {
        this.fkSyncGroupId = fkSyncGroupId;
    }
}