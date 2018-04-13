package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.Patient;
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
    private String mobileShow;
    private Integer fkTenantId;// 租户id
    private Boolean isEnable;// 是否已转归，使用此字段时查询需有租户id和所属系统
    private String sysOwner;// 所属系统
    /**
     * 是否临时患者
     */
    private Boolean isTemp;
    private List<PatientCardPO> patientCardList; // 病患卡号维护（住院号，门诊号，卡号，其他...）
    private Boolean hasSpellInitials;// 是否存在全称拼写
    /**
     * 所属多个系统，以,分割
     */
    private String multiSysOwner;
    /**
     * 所属多个租户，以,分割
     */
    private String multiTenantId;

    private String itemName; // 原发病item_name
    private String itemCode;// 原发病item_code
    private String registerTime;// 该患者原发病登记时间
    private String content;// 备注

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

    public String getMobileShow() {
        mobileShow = StringUtil.formatMobile(super.getMobile());
        return mobileShow;
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

    public Integer getFkTenantId() {
        return fkTenantId;
    }

    public void setFkTenantId(Integer fkTenantId) {
        this.fkTenantId = fkTenantId;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getSysOwner() {
        return sysOwner;
    }

    public void setSysOwner(String sysOwner) {
        this.sysOwner = sysOwner;
    }

    public Boolean getHasSpellInitials() {
        return hasSpellInitials;
    }

    public void setHasSpellInitials(Boolean hasSpellInitials) {
        this.hasSpellInitials = hasSpellInitials;
    }

    public Boolean getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Boolean isTemp) {
        this.isTemp = isTemp;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
