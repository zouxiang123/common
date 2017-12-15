/**
 * @Title: LoginUser.java 登录者信息
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: 陈光浩
 * @date: 2015年9月16日 上午9:20:22
 *
 */
package com.xtt.common.dto;

import java.util.Date;

public class LoginUser {
    private Long id;
    private String account;
    private String name;
    private String imagePath;
    private String roleId;
    private Integer tenantId;
    private String position;
    private String roleType;
    private String positionShow;
    private String sysOwner;
    private String userType; // 用户类别
    private String multiTenant;// 该用户拥有权限的租户id
    private String groupTenant;// 当前集团下租户列表,以“,”分隔
    private String multiSysOwner;// 该用户拥有权限的系统
    private String skin;
    // APP用
    private Long patientId;
    private Integer countSubAccount;
    private String patientPath;
    private String barcodePath;
    private Boolean isSib;
    private String sex;
    private Integer age;
    private String mobile;
    private Date birthday;

    private String statementSkin;// 用户报表皮肤

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Integer getCountSubAccount() {
        return countSubAccount;
    }

    public void setCountSubAccount(Integer countSubAccount) {
        this.countSubAccount = countSubAccount;
    }

    public String getPatientPath() {
        return patientPath;
    }

    public void setPatientPath(String patientPath) {
        this.patientPath = patientPath;
    }

    public String getBarcodePath() {
        return barcodePath;
    }

    public void setBarcodePath(String barcodePath) {
        this.barcodePath = barcodePath;
    }

    public Boolean getIsSib() {
        return isSib;
    }

    public void setIsSib(Boolean isSib) {
        this.isSib = isSib;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMultiTenant() {
        return multiTenant;
    }

    public void setMultiTenant(String multiTenant) {
        this.multiTenant = multiTenant;
    }

    public String getMultiSysOwner() {
        return multiSysOwner;
    }

    public void setMultiSysOwner(String multiSysOwner) {
        this.multiSysOwner = multiSysOwner;
    }

    public String getGroupTenant() {
        return groupTenant;
    }

    public void setGroupTenant(String groupTenant) {
        this.groupTenant = groupTenant;
    }

    public String getStatementSkin() {
        return statementSkin;
    }

    public void setStatementSkin(String statementSkin) {
        this.statementSkin = statementSkin;
    }

}
