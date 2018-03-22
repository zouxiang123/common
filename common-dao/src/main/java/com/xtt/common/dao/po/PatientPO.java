package com.xtt.common.dao.po;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dao.model.Patient;
import com.xtt.platform.util.lang.NumberFormatUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientPO extends Patient {
    /** 患者身份类型，1代表身份证 */
    public static String ID_TYPE = "1";
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

    private List<PatientCardPO> patientCardList; // 病患卡号维护（住院号，门诊号，卡号，其他...）

    private Integer orderBy;// 患者排序方式

    /**
     * 所属多个系统，以,分割
     */
    private String multiSysOwner;
    /**
     * 所属多个租户，以,分割
     */
    private String multiTenantId;

    private String cardNo;// his患者序号

    private String cardType;// 患者序号显示类别
    private String heightShow;
    private String weightShow;
    private Boolean hav;// 甲肝
    private Boolean hbv;// 已肝
    private Boolean hcv;// 丙肝
    private Boolean hev;// 戌肝
    private Boolean hiv;// 艾滋病
    private Boolean hsv;// 梅毒
    private Boolean unknown;// 未知

    private List<String> assaylist;// 化验项名字显示

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

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getHeightShow() {
        heightShow = NumberFormatUtil.formatNumber(super.getHeight(), false);
        return heightShow;
    }

    public String getWeightShow() {
        weightShow = NumberFormatUtil.formatNumber(super.getWeight(), false);
        return weightShow;
    }

    public Boolean getHav() {
        return hav;
    }

    public void setHav(Boolean hav) {
        this.hav = hav;
    }

    public Boolean getHbv() {
        return hbv;
    }

    public void setHbv(Boolean hbv) {
        this.hbv = hbv;
    }

    public Boolean getHcv() {
        return hcv;
    }

    public void setHcv(Boolean hcv) {
        this.hcv = hcv;
    }

    public Boolean getHev() {
        return hev;
    }

    public void setHev(Boolean hev) {
        this.hev = hev;
    }

    public Boolean getHiv() {
        return hiv;
    }

    public void setHiv(Boolean hiv) {
        this.hiv = hiv;
    }

    public Boolean getHsv() {
        return hsv;
    }

    public void setHsv(Boolean hsv) {
        this.hsv = hsv;
    }

    public Boolean getUnknown() {
        return unknown;
    }

    public void setUnknown(Boolean unknown) {
        this.unknown = unknown;
    }

    public void setHeightShow(String heightShow) {
        this.heightShow = heightShow;
    }

    public List<String> getAssaylist() {
        return assaylist;
    }

    public void setAssaylist(List<String> assaylist) {
        this.assaylist = assaylist;
    }

}
