package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_form
 */
public class CmForm {
    /**
     * 物理主键 cm_form.id
     */
    private Long id;

    /**
     * cm_form.form_name
     */
    private String formName;

    /**
     * 模块下的子类别 cm_form.category
     */
    private String category;

    /**
     * 所属系统 cm_form.sys_owner
     */
    private String sysOwner;

    /**
     * 是否是最新的 cm_form.is_new
     */
    private Boolean isNew;

    /**
     * 版本号 cm_form.version
     */
    private Integer version;

    /**
     * 是否有效标识 cm_form.is_enable
     */
    private Boolean isEnable;

    /**
     * 租户id cm_form.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_form.create_time
     */
    private Date createTime;

    /**
     * 创建人 cm_form.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_form.update_time
     */
    private Date updateTime;

    /**
     * 更新人 cm_form.update_user_id
     */
    private Long updateUserId;

    /**
     * 物理主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 物理主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     */
    public String getFormName() {
        return formName;
    }

    /**
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * 模块下的子类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 模块下的子类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 所属系统
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    /**
     * 是否是最新的
     */
    public Boolean getIsNew() {
        return isNew;
    }

    /**
     * 是否是最新的
     */
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    /**
     * 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 是否有效标识
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否有效标识
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
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
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
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
}