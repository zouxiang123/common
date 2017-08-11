package com.xtt.common.dao.model;

import java.util.Date;

/**
 * patient_visit
 */
public class PatientVisit {
    /**
     * patient_visit.id
     */
    private Long id;

    /**
     * 唯一编码
     * patient_visit.uuid_code
     */
    private String uuidCode;

    /**
     * 血透病患ID
     * patient_visit.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 病人ID
     * patient_visit.his_pt_id
     */
    private String hisPtId;

    /**
     * 病患类型(1=门诊 2=住院）
     * patient_visit.pt_type
     */
    private String ptType;

    /**
     * 日期（2016-12-12）
     * patient_visit.visit_date
     */
    private String visitDate;

    /**
     * 门诊（每天从1开始递增，为病人每次挂号分配一个序号，该序号与就诊日期一起，构成一次就诊的唯一标识），住院号
     * patient_visit.card_no
     */
    private String cardNo;

    /**
     * 就诊日期|住院次数
     * patient_visit.times
     */
    private String times;

    /**
     * 号别
     * patient_visit.clinic_label
     */
    private String clinicLabel;

    /**
     * 费别
     * patient_visit.charge_type
     */
    private String chargeType;

    /**
     * 身份
     * patient_visit.pt_identity
     */
    private String ptIdentity;

    /**
     * 症状
     * patient_visit.symptom
     */
    private String symptom;

    /**
     * 入院时间
     * patient_visit.in_time
     */
    private Date inTime;

    /**
     * 出院时间
     * patient_visit.out_time
     */
    private Date outTime;

    /**
     * 出院科室
     * patient_visit.in_dept
     */
    private String inDept;

    /**
     * 出院科室
     * patient_visit.out_dept
     */
    private String outDept;

    /**
     * 诊断
     * patient_visit.disgnosis
     */
    private String disgnosis;

    /**
     * 医生
     * patient_visit.doctor
     */
    private String doctor;

    /**
     * 总费用
     * patient_visit.total_costs
     */
    private String totalCosts;

    /**
     * 实际支付
     * patient_visit.total_payments
     */
    private String totalPayments;

    /**
     * 余额
     * patient_visit.total_balance
     */
    private String totalBalance;

    /**
     * 保险类别
     * patient_visit.insurance_type
     */
    private String insuranceType;

    /**
     * 患者状态(1=住院 2=出院)
     * patient_visit.pt_status
     */
    private String ptStatus;

    /**
     * 1=最新 0=旧的
     * patient_visit.new_flag
     */
    private Boolean newFlag;

    /**
     * 是否手术
     * patient_visit.opt_flag
     */
    private String optFlag;

    /**
     * 护理等级名称
     * patient_visit.hospital_service
     */
    private String hospitalService;

    /**
     * 房间号
     * patient_visit.room_no
     */
    private String roomNo;

    /**
     * 床号
     * patient_visit.bed_no
     */
    private String bedNo;

    /**
     * 床名
     * patient_visit.bed_name
     */
    private String bedName;

    /**
     * 转归状态
     * patient_visit.zg_status_name
     */
    private String zgStatusName;

    /**
     * 转归描述
     * patient_visit.zg_desc
     */
    private String zgDesc;

    /**
     * 过敏类型（DA：药物过敏,FA:食物过敏，MA:其他过敏)
     * patient_visit.allergy_type
     */
    private String allergyType;

    /**
     * 过敏原
     * patient_visit.allergy_desc
     */
    private String allergyDesc;

    /**
     * 过敏严重程度
     * patient_visit.allergy_severity
     */
    private String allergySeverity;

    /**
     * 过敏反应
     * patient_visit.allergy_reaction
     */
    private String allergyReaction;

    /**
     * 鉴定时间
     * patient_visit.identification_date
     */
    private Date identificationDate;

    /**
     * 备注
     * patient_visit.remark
     */
    private String remark;

    /**
     * patient_visit.del_flag
     */
    private Boolean delFlag;

    /**
     * 租户ID
     * patient_visit.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * patient_visit.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * patient_visit.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * patient_visit.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * patient_visit.update_user_id
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
     * 唯一编码
     */
    public String getUuidCode() {
        return uuidCode;
    }

    /**
     * 唯一编码
     */
    public void setUuidCode(String uuidCode) {
        this.uuidCode = uuidCode;
    }

    /**
     * 血透病患ID
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 血透病患ID
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 病人ID
     */
    public String getHisPtId() {
        return hisPtId;
    }

    /**
     * 病人ID
     */
    public void setHisPtId(String hisPtId) {
        this.hisPtId = hisPtId;
    }

    /**
     * 病患类型(1=门诊 2=住院）
     */
    public String getPtType() {
        return ptType;
    }

    /**
     * 病患类型(1=门诊 2=住院）
     */
    public void setPtType(String ptType) {
        this.ptType = ptType;
    }

    /**
     * 日期（2016-12-12）
     */
    public String getVisitDate() {
        return visitDate;
    }

    /**
     * 日期（2016-12-12）
     */
    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * 门诊（每天从1开始递增，为病人每次挂号分配一个序号，该序号与就诊日期一起，构成一次就诊的唯一标识），住院号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 门诊（每天从1开始递增，为病人每次挂号分配一个序号，该序号与就诊日期一起，构成一次就诊的唯一标识），住院号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 就诊日期|住院次数
     */
    public String getTimes() {
        return times;
    }

    /**
     * 就诊日期|住院次数
     */
    public void setTimes(String times) {
        this.times = times;
    }

    /**
     * 号别
     */
    public String getClinicLabel() {
        return clinicLabel;
    }

    /**
     * 号别
     */
    public void setClinicLabel(String clinicLabel) {
        this.clinicLabel = clinicLabel;
    }

    /**
     * 费别
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * 费别
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    /**
     * 身份
     */
    public String getPtIdentity() {
        return ptIdentity;
    }

    /**
     * 身份
     */
    public void setPtIdentity(String ptIdentity) {
        this.ptIdentity = ptIdentity;
    }

    /**
     * 症状
     */
    public String getSymptom() {
        return symptom;
    }

    /**
     * 症状
     */
    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    /**
     * 入院时间
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * 入院时间
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * 出院时间
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * 出院时间
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    /**
     * 出院科室
     */
    public String getInDept() {
        return inDept;
    }

    /**
     * 出院科室
     */
    public void setInDept(String inDept) {
        this.inDept = inDept;
    }

    /**
     * 出院科室
     */
    public String getOutDept() {
        return outDept;
    }

    /**
     * 出院科室
     */
    public void setOutDept(String outDept) {
        this.outDept = outDept;
    }

    /**
     * 诊断
     */
    public String getDisgnosis() {
        return disgnosis;
    }

    /**
     * 诊断
     */
    public void setDisgnosis(String disgnosis) {
        this.disgnosis = disgnosis;
    }

    /**
     * 医生
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * 医生
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * 总费用
     */
    public String getTotalCosts() {
        return totalCosts;
    }

    /**
     * 总费用
     */
    public void setTotalCosts(String totalCosts) {
        this.totalCosts = totalCosts;
    }

    /**
     * 实际支付
     */
    public String getTotalPayments() {
        return totalPayments;
    }

    /**
     * 实际支付
     */
    public void setTotalPayments(String totalPayments) {
        this.totalPayments = totalPayments;
    }

    /**
     * 余额
     */
    public String getTotalBalance() {
        return totalBalance;
    }

    /**
     * 余额
     */
    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    /**
     * 保险类别
     */
    public String getInsuranceType() {
        return insuranceType;
    }

    /**
     * 保险类别
     */
    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    /**
     * 患者状态(1=住院 2=出院)
     */
    public String getPtStatus() {
        return ptStatus;
    }

    /**
     * 患者状态(1=住院 2=出院)
     */
    public void setPtStatus(String ptStatus) {
        this.ptStatus = ptStatus;
    }

    /**
     * 1=最新 0=旧的
     */
    public Boolean getNewFlag() {
        return newFlag;
    }

    /**
     * 1=最新 0=旧的
     */
    public void setNewFlag(Boolean newFlag) {
        this.newFlag = newFlag;
    }

    /**
     * 是否手术
     */
    public String getOptFlag() {
        return optFlag;
    }

    /**
     * 是否手术
     */
    public void setOptFlag(String optFlag) {
        this.optFlag = optFlag;
    }

    /**
     * 护理等级名称
     */
    public String getHospitalService() {
        return hospitalService;
    }

    /**
     * 护理等级名称
     */
    public void setHospitalService(String hospitalService) {
        this.hospitalService = hospitalService;
    }

    /**
     * 房间号
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * 房间号
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    /**
     * 床号
     */
    public String getBedNo() {
        return bedNo;
    }

    /**
     * 床号
     */
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    /**
     * 床名
     */
    public String getBedName() {
        return bedName;
    }

    /**
     * 床名
     */
    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    /**
     * 转归状态
     */
    public String getZgStatusName() {
        return zgStatusName;
    }

    /**
     * 转归状态
     */
    public void setZgStatusName(String zgStatusName) {
        this.zgStatusName = zgStatusName;
    }

    /**
     * 转归描述
     */
    public String getZgDesc() {
        return zgDesc;
    }

    /**
     * 转归描述
     */
    public void setZgDesc(String zgDesc) {
        this.zgDesc = zgDesc;
    }

    /**
     * 过敏类型（DA：药物过敏,FA:食物过敏，MA:其他过敏)
     */
    public String getAllergyType() {
        return allergyType;
    }

    /**
     * 过敏类型（DA：药物过敏,FA:食物过敏，MA:其他过敏)
     */
    public void setAllergyType(String allergyType) {
        this.allergyType = allergyType;
    }

    /**
     * 过敏原
     */
    public String getAllergyDesc() {
        return allergyDesc;
    }

    /**
     * 过敏原
     */
    public void setAllergyDesc(String allergyDesc) {
        this.allergyDesc = allergyDesc;
    }

    /**
     * 过敏严重程度
     */
    public String getAllergySeverity() {
        return allergySeverity;
    }

    /**
     * 过敏严重程度
     */
    public void setAllergySeverity(String allergySeverity) {
        this.allergySeverity = allergySeverity;
    }

    /**
     * 过敏反应
     */
    public String getAllergyReaction() {
        return allergyReaction;
    }

    /**
     * 过敏反应
     */
    public void setAllergyReaction(String allergyReaction) {
        this.allergyReaction = allergyReaction;
    }

    /**
     * 鉴定时间
     */
    public Date getIdentificationDate() {
        return identificationDate;
    }

    /**
     * 鉴定时间
     */
    public void setIdentificationDate(Date identificationDate) {
        this.identificationDate = identificationDate;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
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