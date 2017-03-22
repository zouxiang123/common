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

public class SysUserDto {
    private Long id;
    private String account;
    private String name;
    private String initial;
    private String password;
    private String imagePath;
    private String sex;
    private Date birthday;
    private String mobile;
    private Boolean delFlag;
    /**
     * 创建该用户的租户id
     */
    private Integer fkTenantId;
    private String birthdayShow;
    private String sexShow;
    private String mobileShow;

    /**
     * 用户类型（管理员、超级管理员、普通用户）等
     */
    private String userType;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getBirthdayShow() {
        return birthdayShow;
    }

    public void setBirthdayShow(String birthdayShow) {
        this.birthdayShow = birthdayShow;
    }

    public String getSexShow() {
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

    public String getMobileShow() {
        return mobileShow;
    }

    public void setMobileShow(String mobileShow) {
        this.mobileShow = mobileShow;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
