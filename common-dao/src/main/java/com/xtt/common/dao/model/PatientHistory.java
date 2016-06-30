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
	 * 帐号 patient_history.account
	 */
	private String account;

	/**
	 * 姓名 patient_history.name
	 */
	private String name;

	/**
	 * 姓名首字母 patient_history.initial
	 */
	private String initial;

	/**
	 * 姓名全称首字母（多个） patient_history.spell_initials
	 */
	private String spellInitials;

	/**
	 * 密码 patient_history.password
	 */
	private String password;

	/**
	 * 证件类型 1：身份证 2：护照 patient_history.id_type
	 */
	private String idType;

	/**
	 * 身份证号 patient_history.id_number
	 */
	private String idNumber;

	/**
	 * 性别 patient_history.sex
	 */
	private String sex;

	/**
	 * 出生日期 patient_history.birthday
	 */
	private Date birthday;

	/**
	 * 用户头像 patient_history.image_path
	 */
	private String imagePath;

	/**
	 * 工作单位 patient_history.work_unit
	 */
	private String workUnit;

	/**
	 * 职业 patient_history.profession
	 */
	private String profession;

	/**
	 * 手机号 patient_history.mobile
	 */
	private String mobile;

	/**
	 * 紧急联系人 patient_history.emergency_contacts
	 */
	private String emergencyContacts;

	/**
	 * 紧急联系方式 patient_history.emergency_mobile
	 */
	private String emergencyMobile;

	/**
	 * 紧急联系人2 patient_history.emergency_contacts2
	 */
	private String emergencyContacts2;

	/**
	 * 紧急联系方式3 patient_history.emergency_mobile2
	 */
	private String emergencyMobile2;

	/**
	 * 紧急联系人3 patient_history.emergency_contacts3
	 */
	private String emergencyContacts3;

	/**
	 * 紧急联系方式3 patient_history.emergency_mobile3
	 */
	private String emergencyMobile3;

	/**
	 * 省、市 patient_history.province
	 */
	private Integer province;

	/**
	 * 县、区 patient_history.county
	 */
	private Integer county;

	/**
	 * 家庭地址 patient_history.address
	 */
	private String address;

	/**
	 * 患者干体重 patient_history.dry_weight
	 */
	private BigDecimal dryWeight;

	/**
	 * 患者体重偏移量 patient_history.offset
	 */
	private BigDecimal offset;

	/**
	 * 患者余额 patient_history.balance
	 */
	private BigDecimal balance;

	/**
	 * 医保卡号 patient_history.medicare_card
	 */
	private String medicareCard;

	/**
	 * 医保卡类别 patient_history.medicare_card_type
	 */
	private String medicareCardType;

	/**
	 * 住院号 patient_history.admission_number
	 */
	private String admissionNumber;

	/**
	 * 门诊号 patient_history.outpatient_number
	 */
	private String outpatientNumber;

	/**
	 * 是否临时患者 patient_history.is_temp
	 */
	private Boolean isTemp;

	/**
	 * 透析次数 patient_history.dialysis_times
	 */
	private Integer dialysisTimes;

	/**
	 * 婚姻状况（□未婚；□已婚；□离异；□丧偶；其他） patient_history.civil_state
	 */
	private String civilState;

	/**
	 * 教育程度 patient_history.education_level
	 */
	private String educationLevel;

	/**
	 * 邮编 patient_history.zip_code
	 */
	private String zipCode;

	/**
	 * E-mail patient_history.email
	 */
	private String email;

	/**
	 * 电话 patient_history.tel
	 */
	private String tel;

	/**
	 * 删除标记 patient_history.del_flag
	 */
	private Boolean delFlag;

	/**
	 * 租户ID patient_history.fk_tenant_id
	 */
	private Integer fkTenantId;

	/**
	 * 创建时间 patient_history.create_time
	 */
	private Date createTime;

	/**
	 * 创建人 patient_history.create_user_id
	 */
	private Long createUserId;

	/**
	 * 更新时间 patient_history.update_time
	 */
	private Date updateTime;

	/**
	 * 更新人 patient_history.update_user_id
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
	 * 紧急联系人2
	 */
	public String getEmergencyContacts2() {
		return emergencyContacts2;
	}

	/**
	 * 紧急联系人2
	 */
	public void setEmergencyContacts2(String emergencyContacts2) {
		this.emergencyContacts2 = emergencyContacts2;
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
	 * 紧急联系人3
	 */
	public String getEmergencyContacts3() {
		return emergencyContacts3;
	}

	/**
	 * 紧急联系人3
	 */
	public void setEmergencyContacts3(String emergencyContacts3) {
		this.emergencyContacts3 = emergencyContacts3;
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
	 * 患者体重偏移量
	 */
	public BigDecimal getOffset() {
		return offset;
	}

	/**
	 * 患者体重偏移量
	 */
	public void setOffset(BigDecimal offset) {
		this.offset = offset;
	}

	/**
	 * 患者余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 患者余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
	 * 婚姻状况（□未婚；□已婚；□离异；□丧偶；其他）
	 */
	public String getCivilState() {
		return civilState;
	}

	/**
	 * 婚姻状况（□未婚；□已婚；□离异；□丧偶；其他）
	 */
	public void setCivilState(String civilState) {
		this.civilState = civilState;
	}

	/**
	 * 教育程度
	 */
	public String getEducationLevel() {
		return educationLevel;
	}

	/**
	 * 教育程度
	 */
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	/**
	 * 邮编
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 邮编
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * E-mail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * E-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 电话
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
}