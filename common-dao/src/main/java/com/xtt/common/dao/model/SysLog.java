package com.xtt.common.dao.model;

import java.util.Date;

import com.xtt.platform.framework.core.model.MyBatisSuperModel;

/**
 * sys_log
 */
public class SysLog extends MyBatisSuperModel {
    /**
     * sys_log.id
     */
    private Long id;

    /**
     * 操作人 sys_log.operator_id
     */
    private Long operatorId;

    /**
     * 记录时间 sys_log.log_time
     */
    private Date logTime;

    /**
     * 日志类型 sys_log.log_type
     */
    private String logType;

    /**
     * 日志内容 sys_log.log_info
     */
    private String logInfo;

    /**
     * 租户ID sys_log.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 所属系统 sys_log.sys_owner
     */
    private String sysOwner;

    /**
     * 创建时间 sys_log.create_time
     */
    private Date createTime;

    /**
     * 创建人 sys_log.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 sys_log.update_time
     */
    private Date updateTime;

    /**
     * 更新人 sys_log.update_user_id
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
     * 操作人
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 操作人
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 记录时间
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * 记录时间
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * 日志类型
     */
    public String getLogType() {
        return logType;
    }

    /**
     * 日志类型
     */
    public void setLogType(String logType) {
        this.logType = logType;
    }

    /**
     * 日志内容
     */
    public String getLogInfo() {
        return logInfo;
    }

    /**
     * 日志内容
     */
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
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