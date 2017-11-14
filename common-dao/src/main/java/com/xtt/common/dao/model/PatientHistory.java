package com.xtt.common.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * patient_history
 */
public class PatientHistory {
    /**
     * patient_history.id
     */
    private Long id;

    /**
     * 帐号
     * patient_history.account
     */
    private String account;

    /**
     * 姓名
     * patient_history.name
     */
    private String name;

    /**
     * 姓名首字母
     * patient_history.initial
     */
    private String initial;

    /**
     * 姓名全称首字母（多个）
     * patient_history.spell_initials
     */
    private String spellInitials;

    /**
     * 密码
     * patient_history.password
     */
    private String password;

    /**
     * 证件类型 1：身份证 2：护照
     * patient_history.id_type
     */
    private String idType;

    /**
     * 身份证号
     * patient_history.id_number
     */
    private String idNumber;

    /**
     * 性别
     * patient_history.sex
     */
    private String sex;

    /**
     * 出生日期
     * patient_history.birthday
     */
    private Date birthday;

    /**
     * 用户头像
     * patient_history.image_path
     */
    private String imagePath;

    /**
     * 工作单位
     * patient_history.work_unit
     */
    private String workUnit;

    /**
     * 职业
     * patient_history.profession
     */
    private String profession;

    /**
     * 省、市
     * patient_history.province
     */
    private Integer province;

    /**
     * 县、区
     * patient_history.county
     */
    private Integer county;

    /**
     * 家庭地址
     * patient_history.address
     */
    private String address;

    /**
     * 手机号
     * patient_history.mobile
     */
    private String mobile;

    /**
     * 邮箱
     * patient_history.email
     */
    private String email;

    /**
     * 紧急联系人
     * patient_history.emergency_contacts
     */
    private String emergencyContacts;

    /**
     * 紧急联系方式
     * patient_history.emergency_mobile
     */
    private String emergencyMobile;

    /**
     * 紧急联系方式3
     * patient_history.emergency_mobile2
     */
    private String emergencyMobile2;

    /**
     * 紧急联系方式3
     * patient_history.emergency_mobile3
     */
    private String emergencyMobile3;

    /**
     * ABO血型
     * patient_history.blood_abo
     */
    private String bloodAbo;

    /**
     * RH(D)血型 1=阳性 0= 阴性
     * patient_history.blood_rh
     */
    private String bloodRh;

    /**
     *  二维码路径
     * patient_history.barcode_path
     */
    private String barcodePath;

    /**
     * 身高（cm）
     * patient_history.height
     */
    private BigDecimal height;

    /**
     * 体重（kg）
     * patient_history.weight
     */
    private BigDecimal weight;

    /**
     * 创建时间
     * patient_history.create_time
     */
    private Date createTime;

    /**
     * 创建人
     * patient_history.create_user_id
     */
    private Long createUserId;

    /**
     * 更新时间
     * patient_history.update_time
     */
    private Date updateTime;

    /**
     * 更新人
     * patient_history.update_user_id
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
     * 姓名全称首字母（多个）
     */
    public String getSpellInitials() {
        return spellInitials;
    }

    /**
     * 姓名全称首字母（多个）
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
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
     * 紧急联系方式3
     */
    public String getEmergencyMobile2() {
        return emergencyMobile2;
    }

    /**
     * 紧急联系方式3
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
     *  二维码路径
     */
    public String getBarcodePath() {
        return barcodePath;
    }

    /**
     *  二维码路径
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