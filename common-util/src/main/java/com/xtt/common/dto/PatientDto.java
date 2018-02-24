package com.xtt.common.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PatientDto {
    /**
     * patient.id
     */
    private Long id;

    /**
     * 帐号 patient.account
     */
    private String account;

    /**
     * 姓名 patient.name
     */
    private String name;

    /**
     * 姓名首字母 patient.initial
     */
    private String initial;

    /**
     * 姓名全拼首字母 patient.spell_initials
     */
    private String spellInitials;

    /**
     * 密码 patient.password
     */
    private String password;

    /**
     * 证件类型 1：身份证 2：护照 patient.id_type
     */
    private String idType;

    /**
     * 身份证号 patient.id_number
     */
    private String idNumber;

    /**
     * 性别 patient.sex
     */
    private String sex;

    /**
     * 出生日期 patient.birthday
     */
    private Date birthday;

    /**
     * 用户头像 patient.image_path
     */
    private String imagePath;

    /**
     * 工作单位 patient.work_unit
     */
    private String workUnit;

    /**
     * 职业 patient.profession
     */
    private String profession;

    /**
     * 省、市 patient.province
     */
    private Integer province;

    /**
     * 县、区 patient.county
     */
    private Integer county;

    /**
     * 家庭地址 patient.address
     */
    private String address;

    /**
     * 手机号 patient.mobile
     */
    private String mobile;

    /**
     * 紧急联系人 patient.emergency_contacts
     */
    private String emergencyContacts;

    /**
     * 紧急联系方式 patient.emergency_mobile
     */
    private String emergencyMobile;

    /**
     * 紧急联系方式2 patient.emergency_mobile2
     */
    private String emergencyMobile2;

    /**
     * 紧急联系方式3 patient.emergency_mobile3
     */
    private String emergencyMobile3;

    /**
     * ABO血型 patient.blood_abo
     */
    private String bloodAbo;

    /**
     * RH(D)血型 1=阳性 0= 阴性 patient.blood_rh
     */
    private String bloodRh;

    /**
     * 二维码路径 patient.barcode_path
     */
    private String barcodePath;

    /**
     * 身高（cm） patient.height
     */
    private BigDecimal height;

    /**
     * 体重（kg） patient.weight
     */
    private BigDecimal weight;
    private String birthdayShow;// 出生日期显示用
    private String provinceName;
    private String countyName;
    private Integer age;
    private String sexShow;
    private String mobileShow;
    /**
     * 所属多个系统，以,分割
     */
    private String multiSysOwner;
    /**
     * 所属多个租户，以,分割
     */
    private String multiTenantId;

    /**
     * 患者邮箱
     */
    private String email;
    /**
     * 患者历史透析次数
     */
    private Integer dialysisTimes;

    /**
     * 民族
     */
    private String nation;
    /**
     * 文化程度
     */
    private String culture;

    public Integer getDialysisTimes() {
        return dialysisTimes;
    }

    public void setDialysisTimes(Integer dialysisTimes) {
        this.dialysisTimes = dialysisTimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 帐号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 帐号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 姓名首字母
     */
    public String getInitial() {
        return initial;
    }

    /**
     * 姓名首字母
     */
    public void setInitial(String initial) {
        this.initial = initial;
    }

    /**
     * 姓名全拼首字母
     */
    public String getSpellInitials() {
        return spellInitials;
    }

    /**
     * 姓名全拼首字母
     */
    public void setSpellInitials(String spellInitials) {
        this.spellInitials = spellInitials;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 证件类型 1：身份证 2：护照
     */
    public String getIdType() {
        return idType;
    }

    /**
     * 证件类型 1：身份证 2：护照
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * 身份证号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 身份证号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 用户头像
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 用户头像
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * 工作单位
     */
    public String getWorkUnit() {
        return workUnit;
    }

    /**
     * 工作单位
     */
    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    /**
     * 职业
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 职业
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 省、市
     */
    public Integer getProvince() {
        return province;
    }

    /**
     * 省、市
     */
    public void setProvince(Integer province) {
        this.province = province;
    }

    /**
     * 县、区
     */
    public Integer getCounty() {
        return county;
    }

    /**
     * 县、区
     */
    public void setCounty(Integer county) {
        this.county = county;
    }

    /**
     * 家庭地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 家庭地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 紧急联系人
     */
    public String getEmergencyContacts() {
        return emergencyContacts;
    }

    /**
     * 紧急联系人
     */
    public void setEmergencyContacts(String emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    /**
     * 紧急联系方式
     */
    public String getEmergencyMobile() {
        return emergencyMobile;
    }

    /**
     * 紧急联系方式
     */
    public void setEmergencyMobile(String emergencyMobile) {
        this.emergencyMobile = emergencyMobile;
    }

    /**
     * 紧急联系方式2
     */
    public String getEmergencyMobile2() {
        return emergencyMobile2;
    }

    /**
     * 紧急联系方式2
     */
    public void setEmergencyMobile2(String emergencyMobile2) {
        this.emergencyMobile2 = emergencyMobile2;
    }

    /**
     * 紧急联系方式3
     */
    public String getEmergencyMobile3() {
        return emergencyMobile3;
    }

    /**
     * 紧急联系方式3
     */
    public void setEmergencyMobile3(String emergencyMobile3) {
        this.emergencyMobile3 = emergencyMobile3;
    }

    /**
     * ABO血型
     */
    public String getBloodAbo() {
        return bloodAbo;
    }

    /**
     * ABO血型
     */
    public void setBloodAbo(String bloodAbo) {
        this.bloodAbo = bloodAbo;
    }

    /**
     * RH(D)血型 1=阳性 0= 阴性
     */
    public String getBloodRh() {
        return bloodRh;
    }

    /**
     * RH(D)血型 1=阳性 0= 阴性
     */
    public void setBloodRh(String bloodRh) {
        this.bloodRh = bloodRh;
    }

    /**
     * 二维码路径
     */
    public String getBarcodePath() {
        return barcodePath;
    }

    /**
     * 二维码路径
     */
    public void setBarcodePath(String barcodePath) {
        this.barcodePath = barcodePath;
    }

    /**
     * 身高（cm）
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * 身高（cm）
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    /**
     * 体重（kg）
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 体重（kg）
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getBirthdayShow() {
        return birthdayShow;
    }

    public void setBirthdayShow(String birthdayShow) {
        this.birthdayShow = birthdayShow;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

}
