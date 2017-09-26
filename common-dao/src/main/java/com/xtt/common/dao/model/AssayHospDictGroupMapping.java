package com.xtt.common.dao.model;

import java.util.Date;

/**
 * assay_hosp_dict_group_mapping
 */
public class AssayHospDictGroupMapping {
    /**
     * assay_hosp_dict_group_mapping.fk_group_id
     */
    private String fkGroupId;

    /**
     * assay_hosp_dict_group_mapping.fk_item_code
     */
    private String fkItemCode;

    /**
     * assay_hosp_dict_group_mapping.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * assay_hosp_dict_group_mapping.create_time
     */
    private Date createTime;

    /**
     * assay_hosp_dict_group_mapping.create_user_id
     */
    private Long createUserId;

    /**
     * assay_hosp_dict_group_mapping.update_time
     */
    private Date updateTime;

    /**
     * assay_hosp_dict_group_mapping.update_user_id
     */
    private Long updateUserId;

    /**
     */
    public String getFkGroupId() {
        return fkGroupId;
    }

    /**
     */
    public void setFkGroupId(String fkGroupId) {
        this.fkGroupId = fkGroupId;
    }

    /**
     */
    public String getFkItemCode() {
        return fkItemCode;
    }

    /**
     */
    public void setFkItemCode(String fkItemCode) {
        this.fkItemCode = fkItemCode;
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