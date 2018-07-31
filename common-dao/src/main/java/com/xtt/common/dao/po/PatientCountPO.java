/**   
 * @Title: PatientCountPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年7月31日 下午2:36:39 
 *
 */
package com.xtt.common.dao.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.util.DictUtil;
import com.xtt.platform.util.time.DateUtil;

public class PatientCountPO extends Patient {
    /**
     * patient_outcome.type 转归类型
     */
    private String type;
    /**
     * patient_outcome.reason 死亡原因
     */
    private String reason;
    /**
     * patient_outcome.record_date 死亡时间
     */
    private Date recordDate;

    /**
     * 死亡总人数
     */
    /** 
    * 
     */
    private int patientCount;

    /**
     * 死亡男人数
     */
    private int manCnt;
    /**
     * 死亡男人数占比
     */
    private BigDecimal manPer;
    /**
     * 死亡女人数
     */
    private int womanCnt;
    /**
     * 死亡女人数占比
     */
    private BigDecimal womanPer;
    /**
     * 性别：男女
     */
    private String sexShow;

    /**
     * 年龄
     */
    private Integer age;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getSexShow() {
        Map<String, String> dictSex = DictUtil.getMapByPItemCode(CmDictConsts.SEX);
        sexShow = dictSex.get(this.getSex());
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

    public Integer getAge() {
        age = DateUtil.getAge(this.getBirthday());
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    public int getManCnt() {
        return manCnt;
    }

    public void setManCnt(int manCnt) {
        this.manCnt = manCnt;
    }

    public BigDecimal getManPer() {
        return manPer;
    }

    public void setManPer(BigDecimal manPer) {
        this.manPer = manPer;
    }

    public int getWomanCnt() {
        return womanCnt;
    }

    public void setWomanCnt(int womanCnt) {
        this.womanCnt = womanCnt;
    }

    public BigDecimal getWomanPer() {
        return womanPer;
    }

    public void setWomanPer(BigDecimal womanPer) {
        this.womanPer = womanPer;
    }
}
