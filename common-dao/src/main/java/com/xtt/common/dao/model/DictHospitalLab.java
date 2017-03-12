package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * dict_hospital_lab
 */
public class DictHospitalLab {
    /**
     * dict_hospital_lab.id
     */
    private Long id;

    /**
     * 组ID
     * dict_hospital_lab.group_id
     */
    private String groupId;

    /**
     * 组名称
     * dict_hospital_lab.group_name
     */
    private String groupName;

    /**
     * 项目编码
     * dict_hospital_lab.item_code
     */
    private String itemCode;

    /**
     * 项目名称
     * dict_hospital_lab.item_name
     */
    private String itemName;

    /**
     * 最小值
     * dict_hospital_lab.min_value
     */
    private BigDecimal minValue;

    /**
     * 最大值
     * dict_hospital_lab.max_value
     */
    private BigDecimal maxValue;

    /**
     * 单位
     * dict_hospital_lab.unit
     */
    private String unit;

    /**
     * 参照
     * dict_hospital_lab.reference
     */
    private String reference;

    /**
     * 排序
     * dict_hospital_lab.order_by
     */
    private Integer orderBy;

    /**
     * 患者化验单字典表外键
     * dict_hospital_lab.fk_dict_code
     */
    private String fkDictCode;

    /**
     * 租户id
     * dict_hospital_lab.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * dict_hospital_lab.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * dict_hospital_lab.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间
     * dict_hospital_lab.update_time
     */
    private Date updateTime;

    /**
     * 修改人
     * dict_hospital_lab.update_user_id
     */
    private Long updateUserId;

    /**
     * 1、数值，2、文本
     * dict_hospital_lab.value_type
     */
    private Integer valueType;

    /**
     * dict_hospital_lab.is_top
     */
    private Boolean isTop;

    /**
     * dict_hospital_lab.date_type
     */
    private String dateType;

    /**
     * dict_hospital_lab.personal_min_value
     */
    private BigDecimal personalMinValue;

    /**
     * dict_hospital_lab.personal_max_value
     */
    private BigDecimal personalMaxValue;

    /**
     * 是否是院外化验项: 0否  1是
     * dict_hospital_lab.flage
     */
    private Boolean flage;

    /**
     * 测试方法
     * dict_hospital_lab.method
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
     * 是否是院外化验项: 0否  1是
     */
    public Boolean getFlage() {
        return flage;
    }

    /**
     * 是否是院外化验项: 0否  1是
     */
    public void setFlage(Boolean flage) {
        this.flage = flage;
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