/**   
 * @Title: RoleVO.java 
 * @Package com.xtt.txgl.system.vo
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年10月24日 上午11:33:09 
 *
 */
package com.xtt.common.conf.vo;

import com.xtt.common.dao.model.SysRole;

public class RoleListVO {
	private Long[] checkedMenuIds;
	private Long menuRoleId;
	private Long[] delRoleIds;
	private SysRole[] roles;
	private String sysOwner;

	public Long[] getCheckedMenuIds() {
		return checkedMenuIds;
	}

	public void setCheckedMenuIds(Long[] checkedMenuIds) {
		this.checkedMenuIds = checkedMenuIds;
	}

	public Long getMenuRoleId() {
		return menuRoleId;
	}

	public void setMenuRoleId(Long menuRoleId) {
		this.menuRoleId = menuRoleId;
	}

	public Long[] getDelRoleIds() {
		return delRoleIds;
	}

	public void setDelRoleIds(Long[] delRoleIds) {
		this.delRoleIds = delRoleIds;
	}

	public SysRole[] getRoles() {
		return roles;
	}

	public void setRoles(SysRole[] roles) {
		this.roles = roles;
	}

	public String getSysOwner() {
		return sysOwner;
	}

	public void setSysOwner(String sysOwner) {
		this.sysOwner = sysOwner;
	}

}
