package com.xtt.common.dao.po;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.CmPatient;
import com.xtt.platform.util.lang.NumberFormatUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmPatientPO extends CmPatient {
    /** 显示 */
    private String birthdayShow;// 出生日期显示用
    private String provinceName;
    private String countyName;
    private Integer age;
    private String sexShow;
    private String tempImagePath;// 创建患者前先上传头像的临时文件
    private String dryWeightShow;
    private String medicareCardTypeShow;
    private String mobileShow;
    private String cardType;// 卡号类型

    private List<PatientCardPO> patientCardList; // 病患卡号维护（住院号，门诊号，卡号，其他...）

    /**
     * 所属多个系统，以,分割
     */
    private String multiSysOwner;
    /**
     * 所属多个租户，以,分割
     */
    private String multiTenantId;

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

    public String getSexShow() {
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}
