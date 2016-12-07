/**   
 * @Title: SysUserDto.java 
 * @Package com.xtt.common.dto
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年12月5日 下午3:10:18 
 *
 */
package com.xtt.common.dto;

import java.util.Date;

import com.xtt.platform.util.Constants;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class SysUserDto {
	private Long id;
	private String account;
	private String name;
	private String initial;
	private String password;
	private String imagePath;
	private String sex;
	private Date birthday;
	private String position;
	private String mobile;
	private String subPhone;
	private String sysOwner;
	private Boolean delFlag;
	private Integer fkTenantId;

	private String roleId;
	private String parentRoleId;
	private String birthdayShow;
	private String sexShow;
	private String mobileShow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSubPhone() {
		return subPhone;
	}

	public void setSubPhone(String subPhone) {
		this.subPhone = subPhone;
	}

	public String getSysOwner() {
		return sysOwner;
	}

	public void setSysOwner(String sysOwner) {
		this.sysOwner = sysOwner;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getFkTenantId() {
		return fkTenantId;
	}

	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getParentRoleId() {
		return parentRoleId;
	}

	public void setParentRoleId(String parentRoleId) {
		this.parentRoleId = parentRoleId;
	}

	public String getBirthdayShow() {
		return birthdayShow;
	}

	public void setBirthdayShow(String birthdayShow) {
		if (getBirthday() != null)
			birthdayShow = DateFormatUtil.convertDateToStr(getBirthday(), Constants.SYS_YYYY_MM_DD);
		this.birthdayShow = birthdayShow;
	}

	public String getSexShow() {
		return sexShow;
	}

	public void setSexShow(String sexShow) {
		this.sexShow = sexShow;
	}

	public String getMobileShow() {
		if (StringUtil.isNotBlank(getMobile()))
			mobileShow = StringUtil.formatMobile(getMobile());
		return mobileShow;
	}

	public void setMobileShow(String mobileShow) {
		this.mobileShow = mobileShow;
	}

}
