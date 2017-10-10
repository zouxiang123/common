package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_formula_conf
 */
public class CmFormulaConf {
    /**
     * 物理主键 cm_formula_conf.id
     */
    private Long id;

    /**
     * 公式类别 cm_formula_conf.category
     */
    private String category;

    /**
     * 公式类别名称 cm_formula_conf.category_name
     */
    private String categoryName;

    /**
     * 公式编号 cm_formula_conf.item_code
     */
    private String itemCode;

    /**
     * 公式名称 cm_formula_conf.item_name
     */
    private String itemName;

    /**
     * 公式描述 cm_formula_conf.item_desc
     */
    private String itemDesc;

    /**
     * 是否选中 cm_formula_conf.is_checked
     */
    private Boolean isChecked;

    /**
     * 是否可用 cm_formula_conf.is_enable
     */
    private Boolean isEnable;

    /**
     * 所属系统 cm_formula_conf.sys_owner
     */
    private String sysOwner;

    /**
     * 租户id cm_formula_conf.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_formula_conf.create_time
     */
    private Date createTime;

    /**
     * 创建人 cm_formula_conf.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间 cm_formula_conf.update_time
     */
    private Date updateTime;

    /**
     * 修改人 cm_formula_conf.update_user_id
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
     * 公式类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 公式类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 公式类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 公式类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 公式编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 公式编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 公式名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 公式名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 公式描述
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * 公式描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * 是否选中
     */
    public Boolean getIsChecked() {
        return isChecked;
    }

    /**
     * 是否选中
     */
    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * 是否可用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否可用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
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
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 修改人
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 修改人
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}