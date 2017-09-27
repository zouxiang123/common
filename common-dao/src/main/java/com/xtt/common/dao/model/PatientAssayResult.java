package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_assay_result
 */
public class PatientAssayResult {
    /**
     * patient_assay_result.id
     */
    private Long id;

    /**
     * 患者id patient_assay_result.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 甲肝 patient_assay_result.hav
     */
    private Boolean hav;

    /**
     * 已肝 patient_assay_result.hbv
     */
    private Boolean hbv;

    /**
     * 丙肝 patient_assay_result.hcv
     */
    private Boolean hcv;

    /**
     * 戌肝 patient_assay_result.hev
     */
    private Boolean hev;

    /**
     * 艾滋病 patient_assay_result.hiv
     */
    private Boolean hiv;

    /**
     * 梅毒 patient_assay_result.hsv
     */
    private Boolean hsv;

    /**
     * 操作人 patient_assay_result.operator_id
     */
    private Long operatorId;

    /**
     * 是否可用 patient_assay_result.is_enable
     */
    private Boolean isEnable;

    /**
     * 租户id patient_assay_result.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 patient_assay_result.create_time
     */
    private Date createTime;

    /**
     * 创建人 patient_assay_result.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间 patient_assay_result.update_time
     */
    private Date updateTime;

    /**
     * 修改人 patient_assay_result.update_user_id
     */
    private Long updateUserId;

    /**
     * 正常的 patient_assay_result.normal
     */
    private Boolean normal;

    /**
     * 尚未做检查 patient_assay_result.unknown
     */
    private Boolean unknown;

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
     * 甲肝
     */
    public Boolean getHav() {
        return hav;
    }

    /**
     * 甲肝
     */
    public void setHav(Boolean hav) {
        this.hav = hav;
    }

    /**
     * 已肝
     */
    public Boolean getHbv() {
        return hbv;
    }

    /**
     * 已肝
     */
    public void setHbv(Boolean hbv) {
        this.hbv = hbv;
    }

    /**
     * 丙肝
     */
    public Boolean getHcv() {
        return hcv;
    }

    /**
     * 丙肝
     */
    public void setHcv(Boolean hcv) {
        this.hcv = hcv;
    }

    /**
     * 戌肝
     */
    public Boolean getHev() {
        return hev;
    }

    /**
     * 戌肝
     */
    public void setHev(Boolean hev) {
        this.hev = hev;
    }

    /**
     * 艾滋病
     */
    public Boolean getHiv() {
        return hiv;
    }

    /**
     * 艾滋病
     */
    public void setHiv(Boolean hiv) {
        this.hiv = hiv;
    }

    /**
     * 梅毒
     */
    public Boolean getHsv() {
        return hsv;
    }

    /**
     * 梅毒
     */
    public void setHsv(Boolean hsv) {
        this.hsv = hsv;
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

    /**
     * 正常的
     */
    public Boolean getNormal() {
        return normal;
    }

    /**
     * 正常的
     */
    public void setNormal(Boolean normal) {
        this.normal = normal;
    }

    /**
     * 尚未做检查
     */
    public Boolean getUnknown() {
        return unknown;
    }

    /**
     * 尚未做检查
     */
    public void setUnknown(Boolean unknown) {
        this.unknown = unknown;
    }
}