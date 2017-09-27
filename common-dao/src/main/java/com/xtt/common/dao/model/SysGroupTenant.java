package com.xtt.common.dao.model;

/**
 * sys_group_tenant
 */
public class SysGroupTenant {
    /**
     * 集团id sys_group_tenant.fk_group_id
     */
    private Integer fkGroupId;

    /**
     * 租户id sys_group_tenant.fk_tenant_id
     */
    private Integer fkTenantId;

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
}