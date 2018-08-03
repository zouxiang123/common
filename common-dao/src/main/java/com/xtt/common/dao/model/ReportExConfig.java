package com.xtt.common.dao.model;

import java.util.Date;

/**
 * report_ex_config
 */
public class ReportExConfig {
    /**
     * 编号 report_ex_config.id
     */
    private Long id;

    /**
     * 异常提醒配置类型:1体重[weight] report_ex_config.ex_type
     */
    private Integer exType;

    /**
     * 状态:0初始化,1正在处理,2已处理完成 report_ex_config.status
     */
    private Integer status;

    /**
     * 上次配置数据 report_ex_config.before_config_json
     */
    private String beforeConfigJson;

    /**
     * 本次修改后配置数据 report_ex_config.after_config_json
     */
    private String afterConfigJson;

    /**
     * 租户ID report_ex_config.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 report_ex_config.create_time
     */
    private Date createTime;

    /**
     * 创建人 report_ex_config.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 report_ex_config.update_time
     */
    private Date updateTime;

    /**
     * 更新人 report_ex_config.update_user_id
     */
    private Long updateUserId;

    /**
     * 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 异常提醒配置类型:1体重[weight]
     */
    public Integer getExType() {
        return exType;
    }

    /**
     * 异常提醒配置类型:1体重[weight]
     */
    public void setExType(Integer exType) {
        this.exType = exType;
    }

    /**
     * 状态:0初始化,1正在处理,2已处理完成
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态:0初始化,1正在处理,2已处理完成
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 上次配置数据
     */
    public String getBeforeConfigJson() {
        return beforeConfigJson;
    }

    /**
     * 上次配置数据
     */
    public void setBeforeConfigJson(String beforeConfigJson) {
        this.beforeConfigJson = beforeConfigJson;
    }

    /**
     * 本次修改后配置数据
     */
    public String getAfterConfigJson() {
        return afterConfigJson;
    }

    /**
     * 本次修改后配置数据
     */
    public void setAfterConfigJson(String afterConfigJson) {
        this.afterConfigJson = afterConfigJson;
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