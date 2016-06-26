/**   
 * @Title: LoginUser.java 登录者信息
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: 陈光浩   
 * @date: 2015年9月16日 上午9:20:22 
 *
 */
package com.xtt.common.util.user;

public class LoginUser {
	private Long id;
	private String name;
	private String imagePath;
	private String roleId;
	private Integer tenantId;
	private String position;
	private String roleType;
	private String positionShow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getPositionShow() {
		return positionShow;
	}

	public void setPositionShow(String positionShow) {
		this.positionShow = positionShow;
	}

}
