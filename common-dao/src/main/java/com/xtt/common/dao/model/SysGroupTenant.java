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
     * 父节点租户id
     */
    private Integer pTenantId;

    public Integer getFkGroupId() {
        return fkGroupId;
    }

    public void setFkGroupId(Integer fkGroupId) {
        this.fkGroupId = fkGroupId;
    }

    public Integer getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    public Integer getpTenantId() {
        return pTenantId;
    }

    public void setpTenantId(Integer pTenantId) {
        this.pTenantId = pTenantId;
    }

}