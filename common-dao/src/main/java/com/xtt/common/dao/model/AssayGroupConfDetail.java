package com.xtt.common.dao.model;

import java.util.Date;

/**
 * assay_group_conf_detail
 */
public class AssayGroupConfDetail {
    /**
     * assay_group_conf_detail.id
     */
    private Long id;

    /**
     * assay_group_conf_detail.fk_assay_group_conf_id
     */
    private Long fkAssayGroupConfId;

    /**
     * 项目编号 assay_group_conf_detail.item_code
     */
    private String itemCode;

    /**
     * assay_group_conf_detail.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * assay_group_conf_detail.create_time
     */
    private Date createTime;

    /**
     * assay_group_conf_detail.create_user_id
     */
    private Long createUserId;

    /**
     * assay_group_conf_detail.update_time
     */
    private Date updateTime;

    /**
     * assay_group_conf_detail.update_user_id
     */
    private Long updateUserId;

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
    public Long getFkAssayGroupConfId() {
        return fkAssayGroupConfId;
    }

    /**
     */
    public void setFkAssayGroupConfId(Long fkAssayGroupConfId) {
        this.fkAssayGroupConfId = fkAssayGroupConfId;
    }

    /**
     * 项目编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 项目编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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