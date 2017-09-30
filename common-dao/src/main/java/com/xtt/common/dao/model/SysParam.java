package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_param
 */
public class SysParam {
    /**
     * sys_param.id
     */
    private Long id;

    /**
     * 参数名称 sys_param.param_name
     */
    private String paramName;

    /**
     * 参数描述 sys_param.param_desc
     */
    private String paramDesc;

    /**
     * 参数值 sys_param.param_value
     */
    private String paramValue;

    /**
     * 字典表中的类型 sys_param.dic_type
     */
    private String dicType;

    /**
     * 参数单位的值，和字典表相对应 sys_param.param_unit
     */
    private String paramUnit;

    /**
     * 租户ID sys_param.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 所属系统 sys_param.sys_owner
     */
    private String sysOwner;

    /**
     * 创建时间 sys_param.create_time
     */
    private Date createTime;

    /**
     * 创建人 sys_param.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 sys_param.update_time
     */
    private Date updateTime;

    /**
     * 更新人 sys_param.update_user_id
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
     * 参数名称
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 参数名称
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * 参数描述
     */
    public String getParamDesc() {
        return paramDesc;
    }

    /**
     * 参数描述
     */
    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    /**
     * 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * 字典表中的类型
     */
    public String getDicType() {
        return dicType;
    }

    /**
     * 字典表中的类型
     */
    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    /**
     * 参数单位的值，和字典表相对应
     */
    public String getParamUnit() {
        return paramUnit;
    }

    /**
     * 参数单位的值，和字典表相对应
     */
    public void setParamUnit(String paramUnit) {
        this.paramUnit = paramUnit;
    }

    /**
     * 租户ID
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户ID
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
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