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
	 * 密码 patient.password
	 */
	private String password;

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
	 * 手机号 patient.mobile
	 */
	private String mobile;

	/**
	 * 紧急联系方式 patient.emergency_mobile
	 */
	private String emergencyMobile;

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
	 * 患者干体重 patient.dry_weight
	 */
	private BigDecimal dryWeight;

	/**
	 * patient.balance
	 */
	private BigDecimal balance;

	/**
	 * 删除标记 patient.del_flag
	 */
	private Boolean delFlag;

	/**
	 * 租户ID patient.fk_tenant_id
	 */
	private Integer fkTenantId;

	/**
	 * 创建时间 patient.create_time
	 */
	private Date createTime;

	/**
	 * 创建人 patient.create_user_id
	 */
	private Long createUserId;

	/**
	 * 更新时间 patient.update_time
	 */
	private Date updateTime;

	/**
	 * 更新人 patient.update_user_id
	 */
	private Long updateUserId;

	/**
	 * 医保卡号 patient.medicare_card
	 */
	private String medicareCard;

	/**
	 * 医保卡类别 patient.medicare_card_type
	 */
	private String medicareCardType;

	/**
	 * 住院号 patient.admission_number
	 */
	private String admissionNumber;

	/**
	 * 姓名全拼首字母 patient.spell_initials
	 */
	private String spellInitials;

	/**
	 * 门诊号 patient.outpatient_number
	 */
	private String outpatientNumber;

	/**
	 * 证件类型 1：身份证 2：护照 patient.id_type
	 */
	private String idType;

	/**
	 * 紧急联系人 patient.emergency_contacts
	 */
	private String emergencyContacts;

	/**
	 * 紧急联系方式2 patient.emergency_mobile2
	 */
	private String emergencyMobile2;

	/**
	 * 紧急联系方式3 patient.emergency_mobile3
	 */
	private String emergencyMobile3;

	/**
	 * 体重偏移量 patient.offset
	 */
	private BigDecimal offset;

	/**
	 * 是否临时患者 patient.is_temp
	 */
	private Boolean isTemp;

	/**
	 * 透析次数 patient.dialysis_times
	 */
	private Integer dialysisTimes;

	/**
	 * 患者类型 1：门诊 2：住院 patient.patient_type
	 */
	private String patientType;

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
	private String createDateShow; // 创建日期
	private String provinceName;
	private String countyName;
	private Integer age;
	private String sexShow;
	private String dryWeightShow;
	private String mobileShow;
	private String medicareCardTypeShow;

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
	 * 患者干体重
	 */
	public BigDecimal getDryWeight() {
		return dryWeight;
	}

	/**
	 * 患者干体重
	 */
	public void setDryWeight(BigDecimal dryWeight) {
		this.dryWeight = dryWeight;
	}

	/**
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 删除标记
	 */
	public Boolean getDelFlag() {
		return delFlag;
	}

	/**
	 * 删除标记
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 租户ID
	 */
	public Integer getFkTenantId() {
		return fkTenantId;
	}

	/**
	 * 租户ID
	 */
	public void setFkTenantId(Integer fkTenantId) {
		this.fkTenantId = fkTenantId;
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

	/**
	 * 医保卡号
	 */
	public String getMedicareCard() {
		return medicareCard;
	}

	/**
	 * 医保卡号
	 */
	public void setMedicareCard(String medicareCard) {
		this.medicareCard = medicareCard;
	}

	/**
	 * 医保卡类别
	 */
	public String getMedicareCardType() {
		return medicareCardType;
	}

	/**
	 * 医保卡类别
	 */
	public void setMedicareCardType(String medicareCardType) {
		this.medicareCardType = medicareCardType;
	}

	/**
	 * 住院号
	 */
	public String getAdmissionNumber() {
		return admissionNumber;
	}

	/**
	 * 住院号
	 */
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
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
	 * 门诊号
	 */
	public String getOutpatientNumber() {
		return outpatientNumber;
	}

	/**
	 * 门诊号
	 */
	public void setOutpatientNumber(String outpatientNumber) {
		this.outpatientNumber = outpatientNumber;
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
	 * 体重偏移量
	 */
	public BigDecimal getOffset() {
		return offset;
	}

	/**
	 * 体重偏移量
	 */
	public void setOffset(BigDecimal offset) {
		this.offset = offset;
	}

	/**
	 * 是否临时患者
	 */
	public Boolean getIsTemp() {
		return isTemp;
	}

	/**
	 * 是否临时患者
	 */
	public void setIsTemp(Boolean isTemp) {
		this.isTemp = isTemp;
	}

	/**
	 * 透析次数
	 */
	public Integer getDialysisTimes() {
		return dialysisTimes;
	}

	/**
	 * 透析次数
	 */
	public void setDialysisTimes(Integer dialysisTimes) {
		this.dialysisTimes = dialysisTimes;
	}

	/**
	 * 患者类型 1：门诊 2：住院
	 */
	public String getPatientType() {
		return patientType;
	}

	/**
	 * 患者类型 1：门诊 2：住院
	 */
	public void setPatientType(String patientType) {
		this.patientType = patientType;
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

	public String getCreateDateShow() {
		return createDateShow;
	}

	public void setCreateDateShow(String createDateShow) {
		this.createDateShow = createDateShow;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSexShow() {
		return sexShow;
	}

	public void setSexShow(String sexShow) {
		this.sexShow = sexShow;
	}

	public String getDryWeightShow() {
		return dryWeightShow;
	}

	public void setDryWeightShow(String dryWeightShow) {
		this.dryWeightShow = dryWeightShow;
	}

	public String getMobileShow() {
		return mobileShow;
	}

	public void setMobileShow(String mobileShow) {
		this.mobileShow = mobileShow;
	}

	public String getMedicareCardTypeShow() {
		return medicareCardTypeShow;
	}

	public void setMedicareCardTypeShow(String medicareCardTypeShow) {
		this.medicareCardTypeShow = medicareCardTypeShow;
	}

}
