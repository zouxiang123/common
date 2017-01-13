/**   
 * @Title: SysTemplateChildDto.java 
 * @Package com.xtt.common.dto
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月6日 下午3:51:51 
 *
 */
package com.xtt.common.dto;

public class SysTemplateChildDto {
	/**
	 * 模板表id sys_template_child.fk_sys_template_id
	 */
	private Long fkSysTemplateId;

	/**
	 * 类别 sys_template_child.item_type
	 */
	private String itemType;

	/**
	 * 内容 sys_template_child.content
	 */
	private String content;

	/**
	 * 所属系统（HD：血透 PD：腹透） sys_template_child.sys_owner
	 */
	private String sysOwner;

	/**
	 * sys_template_child.fk_tenant_id
	 */
	private Integer fkTenantId;

	public Long getFkSysTemplateId() {
		return fkSysTemplateId;
	}

	public void setFkSysTemplateId(Long fkSysTemplateId) {
		this.fkSysTemplateId = fkSysTemplateId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
