package com.xtt.common.dto;

/**
 * cm_form
 */
public class FormDto {
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

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

}