package com.xtt.common.dao.po;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.Patient;
import com.xtt.platform.util.lang.NumberFormatUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientPO extends Patient {
	/** 显示 */
	private String birthdayShow;// 出生日期显示用
	private String provinceName;
	private String countyName;
	private Integer age;
	private String sexShow;
	private String tempImagePath;// 创建患者前先上传头像的临时文件
	private String bedCode;// 床位编号
	private String areaCode;// 病区编号
	private Integer shiftNumber;// 班次
	private String dryWeightShow;
	private String medicareCardTypeShow;
	@SuppressWarnings("unused")
	private String mobileShow;

	private Long fkDialysisCampaignId;
	private Integer lastStep;// 步骤
	private Boolean isEmergency;
	private Boolean stockWarnFlag;// 是否药品库存警告

	/** 查询条件 */
	private String CDRType;// 临床诊断类型

	private Long[] categoryIds;// 药品耗材类型id
	private String categoryIdStr;// 药品耗材类型id

	private boolean assayFlag;
	private List<String> assaylist;// 化验项名字显示

	private String dialysisDateStr;// 透析时间
	private Long sickbedRecordId;// 排床记录id
	private boolean selectFlag;// 是否选中（在排床页面使用）
	private String dialysisModel;// 透析模式

	private Integer dayOfWeek;// 星期几
	private Integer weekNumber;// 第几周

	private Integer areaType;

	private List<PatientCardPO> patientCardList; // 病患卡号维护（住院号，门诊号，卡号，其他...）

	public List<PatientCardPO> getPatientCardList() {
		return patientCardList;
	}

	public void setPatientCardList(List<PatientCardPO> patientCardList) {
		this.patientCardList = patientCardList;
	}

	public String getBirthdayShow() {
		birthdayShow = DateFormatUtil.convertDateToStr(super.getBirthday(), "yyyy-MM-dd");
		return birthdayShow;
	}

	public void setBirthdayShow(String birthdayShow) {
		this.birthdayShow = birthdayShow;
		if (birthdayShow != null) {
			super.setBirthday(DateFormatUtil.convertStrToDate(birthdayShow, "yyyy-MM-dd"));
		}
	}

	public String getTempImagePath() {
		return tempImagePath;
	}

	public void setTempImagePath(String tempImagePath) {
		this.tempImagePath = tempImagePath;
	}

	/**
	 * 获取用户详细地址（省+市+具体地址）
	 * 
	 * @Title: getDetailAddress
	 * @return
	 * 
	 */
	public String getDetailAddress() {
		return this.provinceName + this.countyName + super.getAddress();
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

	public Long getFkDialysisCampaignId() {
		return fkDialysisCampaignId;
	}

	public void setFkDialysisCampaignId(Long fkDialysisCampaignId) {
		this.fkDialysisCampaignId = fkDialysisCampaignId;
	}

	public Integer getLastStep() {
		return lastStep;
	}

	public void setLastStep(Integer lastStep) {
		this.lastStep = lastStep;
	}

	public String getSexShow() {
		return sexShow;
	}

	public void setSexShow(String sexShow) {
		this.sexShow = sexShow;
	}

	public String getBedCode() {
		return bedCode;
	}

	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Boolean getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(Boolean isEmergency) {
		this.isEmergency = isEmergency;
	}

	public String getCDRType() {
		return CDRType;
	}

	public void setCDRType(String cDRType) {
		CDRType = cDRType;
	}

	public Boolean getStockWarnFlag() {
		return stockWarnFlag;
	}

	public void setStockWarnFlag(Boolean stockWarnFlag) {
		this.stockWarnFlag = stockWarnFlag;
	}

	public Integer getShiftNumber() {
		return shiftNumber;
	}

	public void setShiftNumber(Integer shiftNumber) {
		this.shiftNumber = shiftNumber;
	}

	public String getDryWeightShow() {
		if (super.getDryWeight() != null) {
			dryWeightShow = NumberFormatUtil.formatNumber(super.getDryWeight());
		}
		return dryWeightShow;
	}

	public void setDryWeightShow(String dryWeightShow) {
		if (StringUtils.isNotBlank(dryWeightShow))
			super.setDryWeight(new BigDecimal(dryWeightShow).setScale(2, BigDecimal.ROUND_UP));
		this.dryWeightShow = dryWeightShow;
	}

	public String getMedicareCardTypeShow() {
		return medicareCardTypeShow;
	}

	public void setMedicareCardTypeShow(String medicareCardTypeShow) {
		this.medicareCardTypeShow = medicareCardTypeShow;
	}

	public Long[] getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(Long[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getCategoryIdStr() {
		return categoryIdStr;
	}

	public void setCategoryIdStr(String categoryIdStr) {
		if (StringUtil.isNotBlank(categoryIdStr)) {
			String[] arr = categoryIdStr.split(",");
			categoryIds = new Long[arr.length];
			for (int i = 0; i < arr.length; i++) {
				String str = arr[i];
				categoryIds[i] = Long.parseLong(str);
			}
		}
		this.categoryIdStr = categoryIdStr;
	}

	public String getMobileShow() {
		return StringUtil.formatMobile(super.getMobile());
	}

	public void setMobileShow(String mobileShow) {
		this.mobileShow = mobileShow;
	}

	public String getEmergencyMobileShow() {
		return StringUtil.formatMobile(super.getEmergencyMobile());
	}

	public String getEmergencyMobileShow2() {
		return StringUtil.formatMobile(super.getEmergencyMobile2());
	}

	public String getEmergencyMobileShow3() {
		return StringUtil.formatMobile(super.getEmergencyMobile3());
	}

	public List<String> getAssaylist() {
		return assaylist;
	}

	public void setAssaylist(List<String> assaylist) {
		this.assaylist = assaylist;
	}

	public String getDialysisDateStr() {
		return dialysisDateStr;
	}

	public void setDialysisDateStr(String dialysisDateStr) {
		this.dialysisDateStr = dialysisDateStr;
	}

	public Long getSickbedRecordId() {
		return sickbedRecordId;
	}

	public void setSickbedRecordId(Long sickbedRecordId) {
		this.sickbedRecordId = sickbedRecordId;
	}

	public boolean isSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(boolean selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getDialysisModel() {
		return dialysisModel;
	}

	public void setDialysisModel(String dialysisModel) {
		this.dialysisModel = dialysisModel;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}

	public boolean isAssayFlag() {
		return assayFlag;
	}

	public void setAssayFlag(boolean assayFlag) {
		this.assayFlag = assayFlag;
	}

	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

}
