package com.xtt.common.dao.model;

import java.util.Date;

import com.xtt.platform.framework.core.model.MyBatisSuperModel;

/**
 * app_patient_account
 */
public class AppPatientAccount extends MyBatisSuperModel {
    /**
     * 主键id app_patient_account.id
     */
    private Long id;

    /**
     * 账号 app_patient_account.account
     */
    private String account;

    /**
     * 密码 app_patient_account.password
     */
    private String password;

    /**
     * 患者id app_patient_account.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 家属姓名 app_patient_account.sib_name
     */
    private String sibName;

    /**
     * 电话 app_patient_account.tel
     */
    private String tel;

    /**
     * 是否家属 app_patient_account.is_sib
     */
    private String isSib;

    /**
     * 与患者关系 app_patient_account.relationship
     */
    private String relationship;

    /**
     * 是否可用 app_patient_account.is_enable
     */
    private Boolean isEnable;

    /**
     * 租户id app_patient_account.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 app_patient_account.create_time
     */
    private Date createTime;

    /**
     * 创建人 app_patient_account.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 app_patient_account.update_time
     */
    private Date updateTime;

    /**
     * 更新人 app_patient_account.update_user_id
     */
    private Long updateUserId;

    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 患者id
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 患者id
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 家属姓名
     */
    public String getSibName() {
        return sibName;
    }

    /**
     * 家属姓名
     */
    public void setSibName(String sibName) {
        this.sibName = sibName;
    }

    /**
     * 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 是否家属
     */
    public String getIsSib() {
        return isSib;
    }

    /**
     * 是否家属
     */
    public void setIsSib(String isSib) {
        this.isSib = isSib;
    }

    /**
     * 与患者关系
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 与患者关系
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
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