package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * sys_param_range
 */
public class SysParamRange {
    /**
     * sys_param_range.id
     */
    private Long id;

    /**
     * 项目编码
     * sys_param_range.item_code
     */
    private String itemCode;

    /**
     * 项目名称
     * sys_param_range.item_name
     */
    private String itemName;

    /**
     * 最小值
     * sys_param_range.min
     */
    private BigDecimal min;

    /**
     * 最大值
     * sys_param_range.max
     */
    private BigDecimal max;

    /**
     * 是否显示（1：显示 0：不显示）
     * sys_param_range.is_show
     */
    private Boolean isShow;

    /**
     * 是否有效（1：有效 0：无效）
     * sys_param_range.is_enable
     */
    private Boolean isEnable;

    /**
     * 所属系统
     * sys_param_range.sys_owner
     */
    private String sysOwner;

    /**
     * 租户
     * sys_param_range.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * sys_param_range.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_param_range.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间
     * sys_param_range.update_time
     */
    private Date updateTime;

    /**
     * 修改人
     * sys_param_range.update_user_id
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
     * 项目编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 项目编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 最小值
     */
    public BigDecimal getMin() {
        return min;
    }

    /**
     * 最小值
     */
    public void setMin(BigDecimal min) {
        this.min = min;
    }

    /**
     * 最大值
     */
    public BigDecimal getMax() {
        return max;
    }

    /**
     * 最大值
     */
    public void setMax(BigDecimal max) {
        this.max = max;
    }

    /**
     * 是否显示（1：显示 0：不显示）
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * 是否显示（1：显示 0：不显示）
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * 是否有效（1：有效 0：无效）
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否有效（1：有效 0：无效）
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
     * 租户
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户
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