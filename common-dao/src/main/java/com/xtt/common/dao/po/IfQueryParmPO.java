package com.xtt.common.dao.po;

import java.util.Date;

/**
 * if_query_parm
 */
public class IfQueryParmPO {

    /**
     * 下载（卡号）的入参条件
     */
    public static final String PT_CARD = "patient_card";
    /**
     * 下载（住院门诊）的入参条件
     */
    public static final String PT_VISIT_IDNO = "patient_visit_idno";
    /**
     * 下载（住院门诊）的入参条件
     */
    public static final String PT_VISIT_CARDNO = "patient_visit_cardno";
    /**
     * 下载（检验）的入参条件
     */
    public static final String PT_LIS = "patient_assay_record";
    /**
     * 下载（影像）的入参条件
     */
    public static final String PT_PACS = "patient_pacs";
    /**
     * 下载（医嘱）的入参条件
     */
    public static final String PT_ORDERS = "patient_orders";
    /**
     * 下载（手术）的入参条件
     */
    public static final String PT_OPT = "patient_operation";

    /**
     * if_query_parm.id
     */
    private String id;

    /**
     * 序号 if_query_parm.id_no
     */
    private Integer idNo;

    /**
     * 下载的类型 if_query_parm.down_type
     */
    private String downType;

    /**
     * 血透患者ID if_query_parm.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 患者姓名 if_query_parm.patient_name
     */
    private String patientName;

    /**
     * 卡号类型 if_query_parm.card_type
     */
    private String cardType;

    /**
     * 卡号 if_query_parm.card_no
     */
    private String cardNo;

    /**
     * 下一次开始日期 if_query_parm.start_date
     */
    private String startDate;

    private String endDate;

    /**
     * 删除标记 if_query_parm.del_flag
     */
    private Boolean delFlag;

    /**
     * 创建日期 if_query_parm.create_time
     */
    private Date createTime;

    /**
     * 更新日期 if_query_parm.update_time
     */
    private Date updateTime;

    /**
     * 租户 if_query_parm.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     */
    public String getId() {
        return id;
    }

    /**
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 序号
     */
    public Integer getIdNo() {
        return idNo;
    }

    /**
     * 序号
     */
    public void setIdNo(Integer idNo) {
        this.idNo = idNo;
    }

    /**
     * 下载的类型
     */
    public String getDownType() {
        return downType;
    }

    /**
     * 下载的类型
     */
    public void setDownType(String downType) {
        this.downType = downType;
    }

    /**
     * 血透患者ID
     */
    public Long getFkPatientId() {
        return fkPatientId;
    }

    /**
     * 血透患者ID
     */
    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    /**
     * 患者姓名
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * 患者姓名
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
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
     * 下一次开始日期
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 下一次开始日期
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
     * 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

}