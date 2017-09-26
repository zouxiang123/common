package com.xtt.common.dao.model;

import java.util.Date;

/**
 * assay_filter_rule
 */
public class AssayFilterRule {
    /**
     * 主键
     * assay_filter_rule.id
     */
    private Long id;

    /**
     * 分类规则(1:分组名关键字;2:样式名称关键字;3:分组名+化验项+透前透后时间;4:分组名+化验项数量+化验项数值判断+透析日时间;)
     * assay_filter_rule.category
     */
    private String category;

    /**
     * 分组名
     * assay_filter_rule.group_name
     */
    private String groupName;

    /**
     * 化验项
     * assay_filter_rule.item_code
     */
    private String itemCode;

    /**
     * 透前关键字
     * assay_filter_rule.keyword_before
     */
    private String keywordBefore;

    /**
     * 透后关键字
     * assay_filter_rule.keyword_after
     */
    private String keywordAfter;

    /**
     * 透前项目数量
     * assay_filter_rule.item_count_before
     */
    private Integer itemCountBefore;

    /**
     * 透后项目数量
     * assay_filter_rule.item_count_after
     */
    private Integer itemCountAfter;

    /**
     * 间隔天数
     * assay_filter_rule.interval_day
     */
    private Integer intervalDay;

    /**
     * 租户id
     * assay_filter_rule.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * assay_filter_rule.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * assay_filter_rule.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间
     * assay_filter_rule.update_time
     */
    private Date updateTime;

    /**
     * 修改人
     * assay_filter_rule.update_user_id
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
     * 分类规则(1:分组名关键字;2:样式名称关键字;3:分组名+化验项+透前透后时间;4:分组名+化验项数量+化验项数值判断+透析日时间;)
     */
    public String getCategory() {
        return category;
    }

    /**
     * 分类规则(1:分组名关键字;2:样式名称关键字;3:分组名+化验项+透前透后时间;4:分组名+化验项数量+化验项数值判断+透析日时间;)
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 分组名
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 分组名
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 化验项
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 化验项
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 透前关键字
     */
    public String getKeywordBefore() {
        return keywordBefore;
    }

    /**
     * 透前关键字
     */
    public void setKeywordBefore(String keywordBefore) {
        this.keywordBefore = keywordBefore;
    }

    /**
     * 透后关键字
     */
    public String getKeywordAfter() {
        return keywordAfter;
    }

    /**
     * 透后关键字
     */
    public void setKeywordAfter(String keywordAfter) {
        this.keywordAfter = keywordAfter;
    }

    /**
     * 透前项目数量
     */
    public Integer getItemCountBefore() {
        return itemCountBefore;
    }

    /**
     * 透前项目数量
     */
    public void setItemCountBefore(Integer itemCountBefore) {
        this.itemCountBefore = itemCountBefore;
    }

    /**
     * 透后项目数量
     */
    public Integer getItemCountAfter() {
        return itemCountAfter;
    }

    /**
     * 透后项目数量
     */
    public void setItemCountAfter(Integer itemCountAfter) {
        this.itemCountAfter = itemCountAfter;
    }

    /**
     * 间隔天数
     */
    public Integer getIntervalDay() {
        return intervalDay;
    }

    /**
     * 间隔天数
     */
    public void setIntervalDay(Integer intervalDay) {
        this.intervalDay = intervalDay;
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