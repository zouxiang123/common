package com.xtt.common.dao.model;

import java.util.Date;

/**
 * assay_report_filter_rule
 */
public class AssayReportFilterRule {
    /**
     * assay_report_filter_rule.id
     */
    private Long id;

    /**
     * 规则编号 assay_report_filter_rule.rule_code
     */
    private String ruleCode;

    /**
     * 规则名称 assay_report_filter_rule.rule_name
     */
    private String ruleName;

    /**
     * 是否生效 assay_report_filter_rule.is_enable
     */
    private Boolean isEnable;

    /**
     * 最小值 assay_report_filter_rule.min_value
     */
    private String minValue;

    /**
     * 最大值 assay_report_filter_rule.max_value
     */
    private String maxValue;

    /**
     * 函数名称 assay_report_filter_rule.function_name
     */
    private String functionName;

    /**
     * 租户id assay_report_filter_rule.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 assay_report_filter_rule.create_time
     */
    private Date createTime;

    /**
     * 创建人 assay_report_filter_rule.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间 assay_report_filter_rule.update_time
     */
    private Date updateTime;

    /**
     * 修改人 assay_report_filter_rule.update_user_id
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
     * 规则编号
     */
    public String getRuleCode() {
        return ruleCode;
    }

    /**
     * 规则编号
     */
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    /**
     * 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 是否生效
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否生效
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 最小值
     */
    public String getMinValue() {
        return minValue;
    }

    /**
     * 最小值
     */
    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    /**
     * 最大值
     */
    public String getMaxValue() {
        return maxValue;
    }

    /**
     * 最大值
     */
    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 函数名称
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * 函数名称
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
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
}