package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * assay_hosp_dict
 */
public class AssayHospDict {
    /**
     * assay_hosp_dict.id
     */
    private Long id;

    /**
     * 项目编码
     * assay_hosp_dict.item_code
     */
    private String itemCode;

    /**
     * 项目名称
     * assay_hosp_dict.item_name
     */
    private String itemName;

    /**
     * 最小值
     * assay_hosp_dict.min_value
     */
    private BigDecimal minValue;

    /**
     * 最大值
     * assay_hosp_dict.max_value
     */
    private BigDecimal maxValue;

    /**
     * 单位
     * assay_hosp_dict.unit
     */
    private String unit;

    /**
     * 参照
     * assay_hosp_dict.reference
     */
    private String reference;

    /**
     * 排序
     * assay_hosp_dict.order_by
     */
    private Integer orderBy;

    /**
     * 患者化验单字典表外键
     * assay_hosp_dict.fk_dict_code
     */
    private String fkDictCode;

    /**
     * 1、数值，2、文本
     * assay_hosp_dict.value_type
     */
    private Integer valueType;

    /**
     * assay_hosp_dict.is_top
     */
    private Boolean isTop;

    /**
     * assay_hosp_dict.date_type
     */
    private String dateType;

    /**
     * assay_hosp_dict.personal_min_value
     */
    private BigDecimal personalMinValue;

    /**
     * assay_hosp_dict.personal_max_value
     */
    private BigDecimal personalMaxValue;

    /**
     * 是否自动生成标识(0:否;1:是)
     * assay_hosp_dict.is_auto
     */
    private Boolean isAuto;

    /**
     * 租户id
     * assay_hosp_dict.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * assay_hosp_dict.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * assay_hosp_dict.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间
     * assay_hosp_dict.update_time
     */
    private Date updateTime;

    /**
     * 修改人
     * assay_hosp_dict.update_user_id
     */
    private Long updateUserId;

    /**
     * 测试方法
     * assay_hosp_dict.method
     */
    private String method;

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
     * 最小值
     */
    public BigDecimal getMinValue() {
        return minValue;
    }

    /**
     * 最小值
     */
    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    /**
     * 最大值
     */
    public BigDecimal getMaxValue() {
        return maxValue;
    }

    /**
     * 最大值
     */
    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 参照
     */
    public String getReference() {
        return reference;
    }

    /**
     * 参照
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * 排序
     */
    public Integer getOrderBy() {
        return orderBy;
    }

    /**
     * 排序
     */
    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 患者化验单字典表外键
     */
    public String getFkDictCode() {
        return fkDictCode;
    }

    /**
     * 患者化验单字典表外键
     */
    public void setFkDictCode(String fkDictCode) {
        this.fkDictCode = fkDictCode;
    }

    /**
     * 1、数值，2、文本
     */
    public Integer getValueType() {
        return valueType;
    }

    /**
     * 1、数值，2、文本
     */
    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    /**
     */
    public Boolean getIsTop() {
        return isTop;
    }

    /**
     */
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**
     */
    public String getDateType() {
        return dateType;
    }

    /**
     */
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    /**
     */
    public BigDecimal getPersonalMinValue() {
        return personalMinValue;
    }

    /**
     */
    public void setPersonalMinValue(BigDecimal personalMinValue) {
        this.personalMinValue = personalMinValue;
    }

    /**
     */
    public BigDecimal getPersonalMaxValue() {
        return personalMaxValue;
    }

    /**
     */
    public void setPersonalMaxValue(BigDecimal personalMaxValue) {
        this.personalMaxValue = personalMaxValue;
    }

    /**
     * 是否自动生成标识(0:否;1:是)
     */
    public Boolean getIsAuto() {
        return isAuto;
    }

    /**
     * 是否自动生成标识(0:否;1:是)
     */
    public void setIsAuto(Boolean isAuto) {
        this.isAuto = isAuto;
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
     * 测试方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 测试方法
     */
    public void setMethod(String method) {
        this.method = method;
    }
}