package com.xtt.common.dao.model;

import java.util.Date;

/**
 * sys_template_child
 */
public class SysTemplateChild {
    /**
     * sys_template_child.id
     */
    private Long id;

    /**
     * 模板表id
     * sys_template_child.fk_sys_template_id
     */
    private Long fkSysTemplateId;

    /**
     * 类别
     * sys_template_child.item_type
     */
    private String itemType;

    /**
     * 内容
     * sys_template_child.content
     */
    private String content;

    /**
     * 所属系统（HD：血透 PD：腹透）
     * sys_template_child.sys_owner
     */
    private String sysOwner;

    /**
     * sys_template_child.fk_tenant_id
     */
    private Integer fkTenantId;

    /**
     * 创建时间
     * sys_template_child.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * sys_template_child.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * sys_template_child.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * sys_template_child.update_user_id
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
     * 模板表id
     */
    public Long getFkSysTemplateId() {
        return fkSysTemplateId;
    }

    /**
     * 模板表id
     */
    public void setFkSysTemplateId(Long fkSysTemplateId) {
        this.fkSysTemplateId = fkSysTemplateId;
    }

    /**
     * 类别
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 类别
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
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

    /**
     * 所属系统（HD：血透 PD：腹透）
     */
    public String getSysOwner() {
        return sysOwner;
    }

    /**
     * 所属系统（HD：血透 PD：腹透）
     */
    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    /**
     */
    public Integer getFkTenantId() {
        return fkTenantId;
    }

    /**
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