/**   
 * @Title: SysTemplatePO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月31日 下午12:25:54 
 *
 */
package com.xtt.common.dto;

import java.util.List;

public class SysTemplateDto {
    /**
     * 标题 sys_template.title
     */
    private String title;

    /**
     * 内容 sys_template.content
     */
    private String content;

    /**
     * 模板类型 sys_template.type
     */
    private String type;

    /**
     * 模板描述 sys_template.type_desc
     */
    private String typeDesc;

    /**
     * 使用次数 sys_template.count
     */
    private Integer count;

    /**
     * 排序 sys_template.order_by
     */
    private Integer orderBy;

    /**
     * 删除标记 1：是 0：否 sys_template.del_flag
     */
    private Boolean delFlag;

    /**
     * 所属系统（HD：血透 PD：腹透） sys_template.sys_owner
     */
    private String sysOwner;

    /**
     * 租户编号 sys_template.fk_tenant_id
     */
    private Integer fkTenantId;

    private List<SysTemplateChildDto> childList;

    public List<SysTemplateChildDto> getChildList() {
        return childList;
    }

    public void setChildList(List<SysTemplateChildDto> childList) {
        this.childList = childList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    public Integer getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

}
