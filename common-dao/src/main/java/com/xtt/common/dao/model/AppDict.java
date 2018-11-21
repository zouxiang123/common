package com.xtt.common.dao.model;

import java.util.Date;

/**
 * app_dict
 */
public class AppDict {
    /**
     * app_dict.id
     */
    private Long id;

    /**
     * app_dict.p_item_code
     */
    private String pItemCode;

    /**
     * app_dict.item_code
     */
    private String itemCode;

    /**
     * app_dict.item_name
     */
    private String itemName;

    /**
     * 描述
     * app_dict.item_desc
     */
    private String itemDesc;

    /**
     * 排序
     * app_dict.order_by
     */
    private Integer orderBy;

    /**
     * 是否可用
     * app_dict.is_enable
     */
    private Boolean isEnable;

    /**
     * 创建时间
     * app_dict.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * app_dict.create_user_id
     */
    private Long createUserId;

    /**
     * 更新人
     * app_dict.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * app_dict.update_user_id
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
     */
    public String getpItemCode() {
        return pItemCode;
    }

    /**
     */
    public void setpItemCode(String pItemCode) {
        this.pItemCode = pItemCode;
    }

    /**
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     */
    public String getItemName() {
        return itemName;
    }

    /**
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 描述
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * 描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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
     * 更新人
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新人
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