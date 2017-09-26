package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_template
 */
public class SysTemplate {
    /**
     * sys_template.id
     */
    private Long id;

    /**
     * 标题
     * sys_template.title
     */
    private String title;

    /**
     * 模板类型
     * sys_template.type
     */
    private String type;

    /**
     * 使用次数
     * sys_template.count
     */
    private Integer count;

    /**
     * 删除标记 1：是 0：否
     * sys_template.del_flag
     */
    private Boolean delFlag;

    /**
     * 租户编号
     * sys_template.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 所属系统
     * sys_template.sys_owner
     */
    private String sysOwner;

    /**
     * 创建时间
     * sys_template.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_template.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sys_template.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sys_template.update_user_id
     */
    private Long updateUserId;

    /**
     * 模板描述
     * sys_template.type_desc
     */
    private String typeDesc;

    /**
     * 排序
     * sys_template.order_by
     */
    private Integer orderBy;

    /**
     * 0未默认 1已默认
     * sys_template.is_default
     */
    private Boolean isDefault;

    /**
     * 内容
     * sys_template.content
     */
    private String content;

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
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 模板类型
     */
    public String getType() {
        return type;
    }

    /**
     * 模板类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 使用次数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 使用次数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 删除标记 1：是 0：否
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记 1：是 0：否
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 租户编号
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
     * 租户编号
     */
    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    /**
     * 所属系统
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
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

    /**
     * 模板描述
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * 模板描述
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
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
     * 0未默认 1已默认
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 0未默认 1已默认
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}