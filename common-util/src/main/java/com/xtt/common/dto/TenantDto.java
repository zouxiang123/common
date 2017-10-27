/**   
 * @Title: SysTenantDto.java 
 * @Package com.xtt.common.dto
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年10月27日 上午10:25:49 
 *
 */
package com.xtt.common.dto;

import java.util.Date;

public class TenantDto {
    /**
     * sys_tenant.id
     */
    private Integer id;

    /**
     * 透析中心名称 sys_tenant.name
     */
    private String name;

    /**
     * 是否可用 sys_tenant.is_enable
     */
    private Boolean isEnable;

    /**
     * 有效开始日期 sys_tenant.start_date
     */
    private Date startDate;

    /**
     * 有效终止日期 sys_tenant.end_date
     */
    private Date endDate;

    /**
     * sys_tenant.is_default
     */
    private Boolean isDefault;

    /**
     * 集团虚拟租户标识（1：集团虚拟租户，0：普通租户） sys_tenant.group_flag
     */
    private Boolean groupFlag;

    /**
     * sys_tenant.license
     */
    private String license;

    /**
     */
    public Integer getId() {
        return id;
    }

    /**
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 透析中心名称
     */
    public String getName() {
        return name;
    }

    /**
     * 透析中心名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 有效开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 有效开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 有效终止日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 有效终止日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 集团虚拟租户标识（1：集团虚拟租户，0：普通租户）
     */
    public Boolean getGroupFlag() {
        return groupFlag;
    }

    /**
     * 集团虚拟租户标识（1：集团虚拟租户，0：普通租户）
     */
    public void setGroupFlag(Boolean groupFlag) {
        this.groupFlag = groupFlag;
    }

    /**
     */
    public String getLicense() {
        return license;
    }

    /**
     */
    public void setLicense(String license) {
        this.license = license;
    }
}
