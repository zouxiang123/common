package com.xtt.common.dao.model;

import java.util.Date;

/**
 * medical_history
 */
public class MedicalHistory {
    /**
     * medical_history.id
     */
    private Long id;

    /**
     * 患者诊断主表ID
     * medical_history.fk_patient_diagnosis_id
     */
    private Long fkPatientDiagnosisId;

    /**
     * medical_history.fk_dialysis_campaign_id
     */
    private Long fkDialysisCampaignId;

    /**
     * 患者ID
     * medical_history.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 有无脑血管意外
     * medical_history.has_cva
     */
    private Boolean hasCva;

    /**
     * 严重出血或出血倾向
     * medical_history.hemorrhage
     */
    private String hemorrhage;

    /**
     * 严重心肺功能不全
     * medical_history.heart_defects
     */
    private String heartDefects;

    /**
     * 外周血管疾病
     * medical_history.has_vascular_disease
     */
    private Boolean hasVascularDisease;

    /**
     * 严重感染或血源性传染病
     * medical_history.has_serious_disease
     */
    private Boolean hasSeriousDisease;

    /**
     * 精神病
     * medical_history.has_psychosis
     */
    private Boolean hasPsychosis;

    /**
     * 其他
     * medical_history.other
     */
    private String other;

    /**
     * 首次透析时间
     * medical_history.first_dialysis_date
     */
    private Date firstDialysisDate;

    /**
     * 是否草稿
     * medical_history.is_draft
     */
    private Boolean isDraft;

    /**
     * 使用操作人
     * medical_history.operator_id
     */
    private Long operatorId;

    /**
     * medical_history.version
     */
    private Integer version;

    /**
     * medical_history.is_enable
     */
    private Boolean isEnable;

    /**
     * medical_history.create_time
     */
    private Date createTime;

    /**
     * medical_history.create_user_id
     */
    private Long createUserId;

    /**
     * medical_history.update_time
     */
    private Date updateTime;

    /**
     * medical_history.update_user_id
     */
    private Long updateUserId;

    /**
     * 手术时间
     * medical_history.opt_time
     */
    private Date optTime;

    /**
     * 手术名称
     * medical_history.opt_name
     */
    private String optName;

    /**
     * 手术_备注
     * medical_history.opt_remark
     */
    private String optRemark;

    /**
     * 血透开始时间
     * medical_history.xt_start_time
     */
    private Date xtStartTime;

    /**
     * 血透开始原因
     * medical_history.xt_start_reason
     */
    private String xtStartReason;

    /**
     * 血透开始其他原因
     * medical_history.xt_start_reason_other
     */
    private String xtStartReasonOther;

    /**
     * 血透停止时间
     * medical_history.xt_end_time
     */
    private Date xtEndTime;

    /**
     * 血透停止原因
     * medical_history.xt_end_reason
     */
    private String xtEndReason;

    /**
     * 血透停止其他原因
     * medical_history.xt_end_reason_other
     */
    private String xtEndReasonOther;

    /**
     * 血透备注
     * medical_history.xt_remark
     */
    private String xtRemark;

    /**
     * 肾移植开始时间
     * medical_history.syz_start_time
     */
    private Date syzStartTime;

    /**
     * 肾移植结束时间
     * medical_history.syz_end_time
     */
    private Date syzEndTime;

    /**
     * 肾移植结束原因
     * medical_history.syz_end_reason
     */
    private String syzEndReason;

    /**
     * 肾移植结束其他原因
     * medical_history.syz_end_reason_other
     */
    private String syzEndReasonOther;

    /**
     * 肾移_备注
     * medical_history.syz_remark
     */
    private String syzRemark;

    /**
     * 腹透开始时间
     * medical_history.ft_start_time
     */
    private Date ftStartTime;

    /**
     * 腹透开始原因
     * medical_history.ft_start_reason
     */
    private String ftStartReason;

    /**
     * 腹透_其他开始原因
     * medical_history.ft_start_reason_other
     */
    private String ftStartReasonOther;

    /**
     * 腹透结束时间
     * medical_history.ft_end_time
     */
    private Date ftEndTime;

    /**
     * 腹透结束原因
     * medical_history.ft_end_reason
     */
    private String ftEndReason;

    /**
     * 腹透_其他结束原因
     * medical_history.ft_end_reason_other
     */
    private String ftEndReasonOther;

    /**
     * 服透备注
     * medical_history.ft_remark
     */
    private String ftRemark;

    /**
     * 过敏_录入时间
     * medical_history.gm_enter_time
     */
    private Date gmEnterTime;

    /**
     * 过敏源
     * medical_history.gm_resouce
     */
    private String gmResouce;

    /**
     * 过敏源_其他
     * medical_history.gm_resouce_other
     */
    private String gmResouceOther;

    /**
     * 过敏名
     * medical_history.gm_name
     */
    private String gmName;

    /**
     * 过敏_备注
     * medical_history.gm_remark
     */
    private String gmRemark;

    /**
     * 传染病_诊断时间
     * medical_history.crb_dia_time
     */
    private Date crbDiaTime;

    /**
     * 传染病_诊断名称
     * medical_history.crb_dia_name
     */
    private String crbDiaName;

    /**
     * 传染病_其他诊断名称
     * medical_history.crb_dia_name_other
     */
    private String crbDiaNameOther;

    /**
     * 传染病_诊断状态
     * medical_history.crb_dia_status
     */
    private String crbDiaStatus;

    /**
     * 传染病_情况
     * medical_history.crb_case
     */
    private String crbCase;

    /**
     * 传染病_其他情况
     * medical_history.crb_case_other
     */
    private String crbCaseOther;

    /**
     * 传染病_备注
     * medical_history.crb_remark
     */
    private String crbRemark;

    /**
     * medical_history.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 首次透析方式
     * medical_history.first_dialysis_method
     */
    private String firstDialysisMethod;

    /**
     * medical_history.hasCva_remark
     */
    private String hascvaRemark;

    /**
     * medical_history.hemorrhage_remark
     */
    private String hemorrhageRemark;

    /**
     * medical_history.heart_defects_remark
     */
    private String heartDefectsRemark;

    /**
     * medical_history.hasVascularDisease_remark
     */
    private String hasvasculardiseaseRemark;

    /**
     * medical_history.hasPsychosis_remark
     */
    private String haspsychosisRemark;

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
     * 患者诊断主表ID
     */
    public Long getFkPatientDiagnosisId() {
        return fkPatientDiagnosisId;
    }

    /**
     * 患者诊断主表ID
     */
    public void setFkPatientDiagnosisId(Long fkPatientDiagnosisId) {
        this.fkPatientDiagnosisId = fkPatientDiagnosisId;
    }

    /**
     */
    public Long getFkDialysisCampaignId() {
        return fkDialysisCampaignId;
    }

    /**
     */
    public void setFkDialysisCampaignId(Long fkDialysisCampaignId) {
        this.fkDialysisCampaignId = fkDialysisCampaignId;
    }

    /**
     * 患者ID
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 患者ID
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 有无脑血管意外
     */
    public Boolean getHasCva() {
        return hasCva;
    }

    /**
     * 有无脑血管意外
     */
    public void setHasCva(Boolean hasCva) {
        this.hasCva = hasCva;
    }

    /**
     * 严重出血或出血倾向
     */
    public String getHemorrhage() {
        return hemorrhage;
    }

    /**
     * 严重出血或出血倾向
     */
    public void setHemorrhage(String hemorrhage) {
        this.hemorrhage = hemorrhage;
    }

    /**
     * 严重心肺功能不全
     */
    public String getHeartDefects() {
        return heartDefects;
    }

    /**
     * 严重心肺功能不全
     */
    public void setHeartDefects(String heartDefects) {
        this.heartDefects = heartDefects;
    }

    /**
     * 外周血管疾病
     */
    public Boolean getHasVascularDisease() {
        return hasVascularDisease;
    }

    /**
     * 外周血管疾病
     */
    public void setHasVascularDisease(Boolean hasVascularDisease) {
        this.hasVascularDisease = hasVascularDisease;
    }

    /**
     * 严重感染或血源性传染病
     */
    public Boolean getHasSeriousDisease() {
        return hasSeriousDisease;
    }

    /**
     * 严重感染或血源性传染病
     */
    public void setHasSeriousDisease(Boolean hasSeriousDisease) {
        this.hasSeriousDisease = hasSeriousDisease;
    }

    /**
     * 精神病
     */
    public Boolean getHasPsychosis() {
        return hasPsychosis;
    }

    /**
     * 精神病
     */
    public void setHasPsychosis(Boolean hasPsychosis) {
        this.hasPsychosis = hasPsychosis;
    }

    /**
     * 其他
     */
    public String getOther() {
        return other;
    }

    /**
     * 其他
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * 首次透析时间
     */
    public Date getFirstDialysisDate() {
        return firstDialysisDate;
    }

    /**
     * 首次透析时间
     */
    public void setFirstDialysisDate(Date firstDialysisDate) {
        this.firstDialysisDate = firstDialysisDate;
    }

    /**
     * 是否草稿
     */
    public Boolean getIsDraft() {
        return isDraft;
    }

    /**
     * 是否草稿
     */
    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    /**
     * 使用操作人
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 使用操作人
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     */
    public Integer getVersion() {
        return version;
    }

    /**
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 手术时间
     */
    public Date getOptTime() {
        return optTime;
    }

    /**
     * 手术时间
     */
    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    /**
     * 手术名称
     */
    public String getOptName() {
        return optName;
    }

    /**
     * 手术名称
     */
    public void setOptName(String optName) {
        this.optName = optName;
    }

    /**
     * 手术_备注
     */
    public String getOptRemark() {
        return optRemark;
    }

    /**
     * 手术_备注
     */
    public void setOptRemark(String optRemark) {
        this.optRemark = optRemark;
    }

    /**
     * 血透开始时间
     */
    public Date getXtStartTime() {
        return xtStartTime;
    }

    /**
     * 血透开始时间
     */
    public void setXtStartTime(Date xtStartTime) {
        this.xtStartTime = xtStartTime;
    }

    /**
     * 血透开始原因
     */
    public String getXtStartReason() {
        return xtStartReason;
    }

    /**
     * 血透开始原因
     */
    public void setXtStartReason(String xtStartReason) {
        this.xtStartReason = xtStartReason;
    }

    /**
     * 血透开始其他原因
     */
    public String getXtStartReasonOther() {
        return xtStartReasonOther;
    }

    /**
     * 血透开始其他原因
     */
    public void setXtStartReasonOther(String xtStartReasonOther) {
        this.xtStartReasonOther = xtStartReasonOther;
    }

    /**
     * 血透停止时间
     */
    public Date getXtEndTime() {
        return xtEndTime;
    }

    /**
     * 血透停止时间
     */
    public void setXtEndTime(Date xtEndTime) {
        this.xtEndTime = xtEndTime;
    }

    /**
     * 血透停止原因
     */
    public String getXtEndReason() {
        return xtEndReason;
    }

    /**
     * 血透停止原因
     */
    public void setXtEndReason(String xtEndReason) {
        this.xtEndReason = xtEndReason;
    }

    /**
     * 血透停止其他原因
     */
    public String getXtEndReasonOther() {
        return xtEndReasonOther;
    }

    /**
     * 血透停止其他原因
     */
    public void setXtEndReasonOther(String xtEndReasonOther) {
        this.xtEndReasonOther = xtEndReasonOther;
    }

    /**
     * 血透备注
     */
    public String getXtRemark() {
        return xtRemark;
    }

    /**
     * 血透备注
     */
    public void setXtRemark(String xtRemark) {
        this.xtRemark = xtRemark;
    }

    /**
     * 肾移植开始时间
     */
    public Date getSyzStartTime() {
        return syzStartTime;
    }

    /**
     * 肾移植开始时间
     */
    public void setSyzStartTime(Date syzStartTime) {
        this.syzStartTime = syzStartTime;
    }

    /**
     * 肾移植结束时间
     */
    public Date getSyzEndTime() {
        return syzEndTime;
    }

    /**
     * 肾移植结束时间
     */
    public void setSyzEndTime(Date syzEndTime) {
        this.syzEndTime = syzEndTime;
    }

    /**
     * 肾移植结束原因
     */
    public String getSyzEndReason() {
        return syzEndReason;
    }

    /**
     * 肾移植结束原因
     */
    public void setSyzEndReason(String syzEndReason) {
        this.syzEndReason = syzEndReason;
    }

    /**
     * 肾移植结束其他原因
     */
    public String getSyzEndReasonOther() {
        return syzEndReasonOther;
    }

    /**
     * 肾移植结束其他原因
     */
    public void setSyzEndReasonOther(String syzEndReasonOther) {
        this.syzEndReasonOther = syzEndReasonOther;
    }

    /**
     * 肾移_备注
     */
    public String getSyzRemark() {
        return syzRemark;
    }

    /**
     * 肾移_备注
     */
    public void setSyzRemark(String syzRemark) {
        this.syzRemark = syzRemark;
    }

    /**
     * 腹透开始时间
     */
    public Date getFtStartTime() {
        return ftStartTime;
    }

    /**
     * 腹透开始时间
     */
    public void setFtStartTime(Date ftStartTime) {
        this.ftStartTime = ftStartTime;
    }

    /**
     * 腹透开始原因
     */
    public String getFtStartReason() {
        return ftStartReason;
    }

    /**
     * 腹透开始原因
     */
    public void setFtStartReason(String ftStartReason) {
        this.ftStartReason = ftStartReason;
    }

    /**
     * 腹透_其他开始原因
     */
    public String getFtStartReasonOther() {
        return ftStartReasonOther;
    }

    /**
     * 腹透_其他开始原因
     */
    public void setFtStartReasonOther(String ftStartReasonOther) {
        this.ftStartReasonOther = ftStartReasonOther;
    }

    /**
     * 腹透结束时间
     */
    public Date getFtEndTime() {
        return ftEndTime;
    }

    /**
     * 腹透结束时间
     */
    public void setFtEndTime(Date ftEndTime) {
        this.ftEndTime = ftEndTime;
    }

    /**
     * 腹透结束原因
     */
    public String getFtEndReason() {
        return ftEndReason;
    }

    /**
     * 腹透结束原因
     */
    public void setFtEndReason(String ftEndReason) {
        this.ftEndReason = ftEndReason;
    }

    /**
     * 腹透_其他结束原因
     */
    public String getFtEndReasonOther() {
        return ftEndReasonOther;
    }

    /**
     * 腹透_其他结束原因
     */
    public void setFtEndReasonOther(String ftEndReasonOther) {
        this.ftEndReasonOther = ftEndReasonOther;
    }

    /**
     * 服透备注
     */
    public String getFtRemark() {
        return ftRemark;
    }

    /**
     * 服透备注
     */
    public void setFtRemark(String ftRemark) {
        this.ftRemark = ftRemark;
    }

    /**
     * 过敏_录入时间
     */
    public Date getGmEnterTime() {
        return gmEnterTime;
    }

    /**
     * 过敏_录入时间
     */
    public void setGmEnterTime(Date gmEnterTime) {
        this.gmEnterTime = gmEnterTime;
    }

    /**
     * 过敏源
     */
    public String getGmResouce() {
        return gmResouce;
    }

    /**
     * 过敏源
     */
    public void setGmResouce(String gmResouce) {
        this.gmResouce = gmResouce;
    }

    /**
     * 过敏源_其他
     */
    public String getGmResouceOther() {
        return gmResouceOther;
    }

    /**
     * 过敏源_其他
     */
    public void setGmResouceOther(String gmResouceOther) {
        this.gmResouceOther = gmResouceOther;
    }

    /**
     * 过敏名
     */
    public String getGmName() {
        return gmName;
    }

    /**
     * 过敏名
     */
    public void setGmName(String gmName) {
        this.gmName = gmName;
    }

    /**
     * 过敏_备注
     */
    public String getGmRemark() {
        return gmRemark;
    }

    /**
     * 过敏_备注
     */
    public void setGmRemark(String gmRemark) {
        this.gmRemark = gmRemark;
    }

    /**
     * 传染病_诊断时间
     */
    public Date getCrbDiaTime() {
        return crbDiaTime;
    }

    /**
     * 传染病_诊断时间
     */
    public void setCrbDiaTime(Date crbDiaTime) {
        this.crbDiaTime = crbDiaTime;
    }

    /**
     * 传染病_诊断名称
     */
    public String getCrbDiaName() {
        return crbDiaName;
    }

    /**
     * 传染病_诊断名称
     */
    public void setCrbDiaName(String crbDiaName) {
        this.crbDiaName = crbDiaName;
    }

    /**
     * 传染病_其他诊断名称
     */
    public String getCrbDiaNameOther() {
        return crbDiaNameOther;
    }

    /**
     * 传染病_其他诊断名称
     */
    public void setCrbDiaNameOther(String crbDiaNameOther) {
        this.crbDiaNameOther = crbDiaNameOther;
    }

    /**
     * 传染病_诊断状态
     */
    public String getCrbDiaStatus() {
        return crbDiaStatus;
    }

    /**
     * 传染病_诊断状态
     */
    public void setCrbDiaStatus(String crbDiaStatus) {
        this.crbDiaStatus = crbDiaStatus;
    }

    /**
     * 传染病_情况
     */
    public String getCrbCase() {
        return crbCase;
    }

    /**
     * 传染病_情况
     */
    public void setCrbCase(String crbCase) {
        this.crbCase = crbCase;
    }

    /**
     * 传染病_其他情况
     */
    public String getCrbCaseOther() {
        return crbCaseOther;
    }

    /**
     * 传染病_其他情况
     */
    public void setCrbCaseOther(String crbCaseOther) {
        this.crbCaseOther = crbCaseOther;
    }

    /**
     * 传染病_备注
     */
    public String getCrbRemark() {
        return crbRemark;
    }

    /**
     * 传染病_备注
     */
    public void setCrbRemark(String crbRemark) {
        this.crbRemark = crbRemark;
    }

    /**
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    /**
     * 首次透析方式
     */
    public String getFirstDialysisMethod() {
        return firstDialysisMethod;
    }

    /**
     * 首次透析方式
     */
    public void setFirstDialysisMethod(String firstDialysisMethod) {
        this.firstDialysisMethod = firstDialysisMethod;
    }

    /**
     */
    public String getHascvaRemark() {
        return hascvaRemark;
    }

    /**
     */
    public void setHascvaRemark(String hascvaRemark) {
        this.hascvaRemark = hascvaRemark;
    }

    /**
     */
    public String getHemorrhageRemark() {
        return hemorrhageRemark;
    }

    /**
     */
    public void setHemorrhageRemark(String hemorrhageRemark) {
        this.hemorrhageRemark = hemorrhageRemark;
    }

    /**
     */
    public String getHeartDefectsRemark() {
        return heartDefectsRemark;
    }

    /**
     */
    public void setHeartDefectsRemark(String heartDefectsRemark) {
        this.heartDefectsRemark = heartDefectsRemark;
    }

    /**
     */
    public String getHasvasculardiseaseRemark() {
        return hasvasculardiseaseRemark;
    }

    /**
     */
    public void setHasvasculardiseaseRemark(String hasvasculardiseaseRemark) {
        this.hasvasculardiseaseRemark = hasvasculardiseaseRemark;
    }

    /**
     */
    public String getHaspsychosisRemark() {
        return haspsychosisRemark;
    }

    /**
     */
    public void setHaspsychosisRemark(String haspsychosisRemark) {
        this.haspsychosisRemark = haspsychosisRemark;
    }
}