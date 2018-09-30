package com.xtt.common.dao.model;

import java.util.Date;

/**
 * cm_dict_icd
 */
public class CmDictIcd {
    /**
     * cm_dict_icd.id
     */
    private Long id;

    /**
     * ICD编号 cm_dict_icd.item_code
     */
    private String itemCode;

    /**
     * 名称 cm_dict_icd.item_name
     */
    private String itemName;

    /**
     * 备注 cm_dict_icd.content
     */
    private String content;

    /**
     * 租户Id cm_dict_icd.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间 cm_dict_icd.create_time
     */
    private Date createTime;

    /**
     * 创建人 cm_dict_icd.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间 cm_dict_icd.update_time
     */
    private Date updateTime;

    /**
     * 更新人 cm_dict_icd.update_user_id
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
     * ICD编号
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * ICD编号
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 备注
     */
    public String getContent() {
        return content;
    }

    /**
     * 备注
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 租户Id
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户Id
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