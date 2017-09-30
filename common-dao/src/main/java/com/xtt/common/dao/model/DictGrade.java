package com.xtt.common.dao.model;

import java.util.Date;

/**
 * dict_grade
 */
public class DictGrade {
    /**
     * 物理主键 dict_grade.id
     */
    private Long id;

    /**
     * 类别标号 dict_grade.type_code
     */
    private String typeCode;

    /**
     * 类别名称 dict_grade.type_name
     */
    private String typeName;

    /**
     * 评估项名称 dict_grade.item_name
     */
    private String itemName;

    /**
     * 评估项code dict_grade.item_code
     */
    private String itemCode;

    /**
     * 分值 dict_grade.score
     */
    private Integer score;

    /**
     * 租户id dict_grade.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 是否有效 dict_grade.is_enable
     */
    private Boolean isEnable;

    /**
     * 创建时间 dict_grade.create_time
     */
    private Date createTime;

    /**
     * 创建人 dict_grade.create_user_id
     */
    private Long createUserId;

    /**
     * 修改时间 dict_grade.update_time
     */
    private Date updateTime;

    /**
     * 修改人 dict_grade.update_user_id
     */
    private Long updateUserId;

    /**
     * 物理主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 物理主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 类别标号
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 类别标号
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 类别名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 类别名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 评估项名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 评估项名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 评估项code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 评估项code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 分值
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 分值
     */
    public void setScore(Integer score) {
        this.score = score;
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
     * 是否有效
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 是否有效
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