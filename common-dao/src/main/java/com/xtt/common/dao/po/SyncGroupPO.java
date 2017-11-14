/**   
 * @Title: SyncGroupPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年6月22日 上午10:07:26 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.SyncGroup;

public class SyncGroupPO extends SyncGroup {
    private String modules;
    private String tenants;
    private String tenantName;
    private String moduleName;

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getTenants() {
        return tenants;
    }

    public void setTenants(String tenants) {
        this.tenants = tenants;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

}
