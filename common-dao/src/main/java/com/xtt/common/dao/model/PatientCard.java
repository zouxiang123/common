package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_card
 */
public class PatientCard {
    /**
     * patient_card.id
     */
    private Long id;

    /**
     * 血透病患ID patient_card.fk_pt_id
     */
    private Long fkPtId;

    /**
     * 卡号类型 patient_card.card_type
     */
    private String cardType;

    /**
     * 卡号 patient_card.card_no
     */
    private String cardNo;

    /**
     * 删除标记 patient_card.del_flag
     */
    private Boolean delFlag;

    /**
     * 本次新旧标示（1=新的 0=旧的） patient_card.new_flag
     */
    private Boolean newFlag;

    /**
     * 租户ID patient_card.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 patient_card.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_card.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 patient_card.update_time
     */
    private Date updateTime;

    /**
     * 更新人 patient_card.update_user_id
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
     * 血透病患ID
     */
    public Long getFkPtId() {
        return fkPtId;
    }

    /**
     * 血透病患ID
     */
    public void setFkPtId(Long fkPtId) {
        this.fkPtId = fkPtId;
    }

    /**
     * 卡号类型
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * 卡号类型
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 删除标记
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 本次新旧标示（1=新的 0=旧的）
     */
    public Boolean getNewFlag() {
        return newFlag;
    }

    /**
     * 本次新旧标示（1=新的 0=旧的）
     */
    public void setNewFlag(Boolean newFlag) {
        this.newFlag = newFlag;
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