/**   
 * @Title: PatientAccountPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月18日 上午11:48:00 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.AppPatientAccount;

/**
 * @ClassName: PatientAccountPO
 * @date: 2016年8月18日 上午11:48:00
 * @version: V1.0
 *
 */
public class AppPatientAccountPO extends AppPatientAccount {

    private Integer countSubAccount;
    private String imagePath;
    private String barcodePath;
    private String sex;
    private String sexShow;
    private String age;
    private String name;
    private String sysOwner;
    private String appSysOwner;
    private String roleId;// 角色id
    private Long fkRoleId;// 角色id
    private String parentRoleId;// 父角色id
    /**
     * 所属多个系统，以,分割
     */
    private String multiSysOwner;
    /**
     * 所属多个租户，以,分割
     */
    private String multiTenantId;

    private String patientName;// 患者姓名
    private String fkTenantName;// 租户名称 医院名 称
    private String url;// 医院登录ip地址
    private String hospitalId;// 医院id
    private String hdUrl;// 医院血透ip地址
    private String groupFlag;// 集团账户标记
    private String idNumber;// 身份证
    private String roleName;// 角色
    private String roleIds;// 角色id

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getFkTenantName() {
        return fkTenantName;
    }

    public void setFkTenantName(String fkTenantName) {
        this.fkTenantName = fkTenantName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = groupFlag;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    private String bgImage;// 背景图片

    public Integer getCountSubAccount() {
        return countSubAccount;
    }

    public void setCountSubAccount(Integer countSubAccount) {
        this.countSubAccount = countSubAccount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBarcodePath() {
        return barcodePath;
    }

    public void setBarcodePath(String barcodePath) {
        this.barcodePath = barcodePath;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexShow() {
        sexShow = "M".equals(this.sex) ? "男" : "女";
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
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

    public String getAppSysOwner() {
        return appSysOwner;
    }

    public void setAppSysOwner(String appSysOwner) {
        this.appSysOwner = appSysOwner;
    }

    public String getMultiSysOwner() {
        return multiSysOwner;
    }

    public void setMultiSysOwner(String multiSysOwner) {
        this.multiSysOwner = multiSysOwner;
    }

    public String getMultiTenantId() {
        return multiTenantId;
    }

    public void setMultiTenantId(String multiTenantId) {
        this.multiTenantId = multiTenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Long getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Long fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

}
