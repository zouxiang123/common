package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_hospital_menu
 */
public class AppHospitalMenu {
    /**
     * app_hospital_menu.id
     */
    private Long id;

    /**
     * app_hospital_menu.fk_hospital_id
     */
    private Integer fkHospitalId;

    /**
     * app_hospital_menu.fk_menu_key
     */
    private String fkMenuKey;

    /**
     * app_hospital_menu.create_time
     */
    private Date createTime;

    /**
     * app_hospital_menu.create_user_id
     */
    private Long createUserId;

    /**
     * app_hospital_menu.update_time
     */
    private Date updateTime;

    /**
     * app_hospital_menu.update_user_id
     */
    private Long updateUserId;

    /**
     * app_hospital_menu.fk_tenant_id
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
    public Integer getFkHospitalId() {
        return fkHospitalId;
    }

    /**
     */
    public void setFkHospitalId(Integer fkHospitalId) {
        this.fkHospitalId = fkHospitalId;
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
}