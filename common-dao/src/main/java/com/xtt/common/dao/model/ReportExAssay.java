package com.xtt.common.dao.model;

import java.util.Date;

/**
 * report_ex_assay
 */
public class ReportExAssay {
    /**
     * 主键 report_ex_assay.id
     */
    private Long id;

    /**
     * 患者ID report_ex_assay.fk_patient_id
     */
    private Long fkPatientId;

    /**
     * 化验检查结果ID report_ex_assay.fk_patient_assay_record_busi_id
     */
    private Long fkPatientAssayRecordBusiId;

    /**
     * 组ID report_ex_assay.group_id
     */
    private String groupId;

    /**
     * 组名称 report_ex_assay.group_name
     */
    private String groupName;

    /**
     * 项目编码 report_ex_assay.item_code
     */
    private String itemCode;

    /**
     * 项目名称 report_ex_assay.item_name
     */
    private String itemName;

    /**
     * 申请单ID report_ex_assay.req_id
     */
    private String reqId;

    /**
     * 本次化验结果值 report_ex_assay.pre_result_actual
     */
    private Double preResultActual;

    /**
     * 上次化验结果值 report_ex_assay.before_result_actual
     */
    private Double beforeResultActual;

    /**
     * 结果值小于下限：本次化验结果值<下限 report_ex_assay.min_value
     */
    private Double minValue;

    /**
     * 结果值大于上限：本次化验结果值>上限 report_ex_assay.max_value
     */
    private Double maxValue;

    /**
     * 相差值+-|本次检验值—上次检验值| report_ex_assay.difference_value
     */
    private Double differenceValue;

    /**
     * 相差百分比+-|(本次检验值—上次检验值)/100| report_ex_assay.percentage_value
     */
    private Double percentageValue;

    /**
     * 是否异常记录[0正常，1异常信息] report_ex_assay.is_ex
     */
    private Integer isEx;

    /**
     * 统计日期=化验日期[格式：[格式：yyyy-mm-dd] report_ex_assay.report_date
     */
    private Date reportDate;

    /**
     * 统计日期=化验日期[格式：[格式：yyyy-mm] report_ex_assay.report_date_month
     */
    private String reportDateMonth;

    /**
     * 租户ID report_ex_assay.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 report_ex_assay.create_time
     */
    private Date createTime;

    /**
     * 创建人 report_ex_assay.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 report_ex_assay.update_time
     */
    private Date updateTime;

    /**
     * 更新人 report_ex_assay.update_user_id
     */
    private Long updateUserId;

    /**
     * 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
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
     * 化验检查结果ID
     */
    public Long getFkPatientAssayRecordBusiId() {
        return fkPatientAssayRecordBusiId;
    }

    /**
     * 化验检查结果ID
     */
    public void setFkPatientAssayRecordBusiId(Long fkPatientAssayRecordBusiId) {
        this.fkPatientAssayRecordBusiId = fkPatientAssayRecordBusiId;
    }

    /**
     * 组ID
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 组ID
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 项目编码
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 项目编码
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 申请单ID
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * 申请单ID
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * 本次化验结果值
     */
    public Double getPreResultActual() {
        return preResultActual;
    }

    /**
     * 本次化验结果值
     */
    public void setPreResultActual(Double preResultActual) {
        this.preResultActual = preResultActual;
    }

    /**
     * 上次化验结果值
     */
    public Double getBeforeResultActual() {
        return beforeResultActual;
    }

    /**
     * 上次化验结果值
     */
    public void setBeforeResultActual(Double beforeResultActual) {
        this.beforeResultActual = beforeResultActual;
    }

    /**
     * 结果值小于下限：本次化验结果值<下限
     */
    public Double getMinValue() {
        return minValue;
    }

    /**
     * 结果值小于下限：本次化验结果值<下限
     */
    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    /**
     * 结果值大于上限：本次化验结果值>上限
     */
    public Double getMaxValue() {
        return maxValue;
    }

    /**
     * 结果值大于上限：本次化验结果值>上限
     */
    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 相差值+-|本次检验值—上次检验值|
     */
    public Double getDifferenceValue() {
        return differenceValue;
    }

    /**
     * 相差值+-|本次检验值—上次检验值|
     */
    public void setDifferenceValue(Double differenceValue) {
        this.differenceValue = differenceValue;
    }

    /**
     * 相差百分比+-|(本次检验值—上次检验值)/100|
     */
    public Double getPercentageValue() {
        return percentageValue;
    }

    /**
     * 相差百分比+-|(本次检验值—上次检验值)/100|
     */
    public void setPercentageValue(Double percentageValue) {
        this.percentageValue = percentageValue;
    }

    /**
     * 是否异常记录[0正常，1异常信息]
     */
    public Integer getIsEx() {
        return isEx;
    }

    /**
     * 是否异常记录[0正常，1异常信息]
     */
    public void setIsEx(Integer isEx) {
        this.isEx = isEx;
    }

    /**
     * 统计日期=化验日期[格式：[格式：yyyy-mm-dd]
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * 统计日期=化验日期[格式：[格式：yyyy-mm-dd]
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * 统计日期=化验日期[格式：[格式：yyyy-mm]
     */
    public String getReportDateMonth() {
        return reportDateMonth;
    }

    /**
     * 统计日期=化验日期[格式：[格式：yyyy-mm]
     */
    public void setReportDateMonth(String reportDateMonth) {
        this.reportDateMonth = reportDateMonth;
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