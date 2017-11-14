
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.SysTenant;

public class SysTenantPO extends SysTenant {

    /*
     * 所属集团id
     */
    private Integer fkGroupId;

    /**
     * 模板租户号
     */
    private Integer template;

    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    public Integer getFkGroupId() {
        return fkGroupId;
    }

    public void setFkGroupId(Integer fkGroupId) {
        this.fkGroupId = fkGroupId;
    }

}
