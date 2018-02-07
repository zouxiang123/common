package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * patient_assay_dictionary
 */
public class PatientAssayDictionary {
    /**
     * patient_assay_dictionary.id
     */
    private Long id;

    /**
     * 组ID
     * patient_assay_dictionary.group_id
     */
    private String groupId;

    /**
     * 组名称
     * patient_assay_dictionary.group_name
     */
    private String groupName;

    /**
     * 项目代号
     * patient_assay_dictionary.item_code
     */
    private String itemCode;

    /**
     * 名字
     * patient_assay_dictionary.item_name
     */
    private String itemName;

    /**
     * 值得类型：{1:数字, 2:字符}
     * patient_assay_dictionary.value_type
     */
    private Integer valueType;

    /**
     * 最小值
     * patient_assay_dictionary.min_value
     */
    private BigDecimal minValue;

    /**
     * 最大值
     * patient_assay_dictionary.max_value
     */
    private BigDecimal maxValue;

    /**
     * 其它值
     * patient_assay_dictionary.other_value
     */
    private String otherValue;

    /**
     * 单位
     * patient_assay_dictionary.unit
     */
    private String unit;

    /**
     * 排序
     * patient_assay_dictionary.order_by
     */
    private Integer orderBy;

    /**
     * 是否固定项
     * patient_assay_dictionary.is_fix
     */
    private Boolean isFix;

    /**
     * 创建时间
     * patient_assay_dictionary.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * patient_assay_dictionary.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间
     * patient_assay_dictionary.update_time
     */
    private Date updateTime;

    /**
     * 修改人
     * patient_assay_dictionary.update_user_id
     */
    private Long updateUserId;

    /**
     * 测试方法
     * patient_assay_dictionary.method
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
     * 项目代号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 项目代号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 名字
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 名字
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 值得类型：{1:数字, 2:字符}
     */
    public Integer getValueType() {
        return valueType;
    }

    /**
     * 值得类型：{1:数字, 2:字符}
     */
    public void setValueType(Integer valueType) {
        this.valueType = valueType;
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
     * 其它值
     */
    public String getOtherValue() {
        return otherValue;
    }

    /**
     * 其它值
     */
    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
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
     * 是否固定项
     */
    public Boolean getIsFix() {
        return isFix;
    }

    /**
     * 是否固定项
     */
    public void setIsFix(Boolean isFix) {
        this.isFix = isFix;
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