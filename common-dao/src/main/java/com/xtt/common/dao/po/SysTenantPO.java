
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.SysTenant;

public class SysTenantPO extends SysTenant {

    /*
     * 所属集团id
     */
    private Integer fkGroupId;

    /**
     * 医院租户号
     */
    private String fkTenantId;

    /**
     * 模板租户号
     */
    private String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(String fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    public Integer getFkGroupId() {
        return fkGroupId;
    }

    public void setFkGroupId(Integer fkGroupId) {
        this.fkGroupId = fkGroupId;
    }

}
