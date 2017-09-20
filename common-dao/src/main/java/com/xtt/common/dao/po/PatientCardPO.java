package com.xtt.common.dao.po;

import java.util.Collection;

import com.xtt.common.dao.model.PatientCard;
import com.xtt.platform.util.time.DateFormatUtil;
import com.xtt.platform.util.time.IDateConst;

/**
 * 病患多卡号维护
 * 
 * @author zz
 */
public class PatientCardPO extends PatientCard {

    private Collection<Long> patientIds;

    private String cardTypeDesc; // 卡号类型

    private String nameShow;// 病患姓名

    private String sexShow;// 性别

    private String idNumber;// 身份证号

    private String createTimeShow;// 创建时间

    private String newFlagDesc;// 是否最新

    private String startDate;// 结束日期

    private String endDate;// 开始日期

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNewFlagDesc() {
        if ("1".equals(this.getNewFlag())) {
            newFlagDesc = "是";
        } else {
            newFlagDesc = "否";
        }
        return newFlagDesc;
    }

    public void setNewFlagDesc(String newFlagDesc) {
        this.newFlagDesc = newFlagDesc;
    }

    public String getCreateTimeShow() {
        if (super.getCreateTime() == null) {
            return "";
        } else {
            createTimeShow = DateFormatUtil.format(super.getCreateTime(), IDateConst.TIME_FORMAT);
        }
        return createTimeShow;
    }

    public void setCreateTimeShow(String createTimeShow) {
        this.createTimeShow = createTimeShow;
        if (createTimeShow == null) {
            return;
        }
        super.setCreateTime(DateFormatUtil.convertStrToDate(createTimeShow, IDateConst.TIME_FORMAT));
    }

    public String getCardTypeDesc() {
        return cardTypeDesc;
    }

    public void setCardTypeDesc(String cardTypeDesc) {
        this.cardTypeDesc = cardTypeDesc;
    }

    public Collection<Long> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(Collection<Long> patientIds) {
        this.patientIds = patientIds;
    }

    public String getNameShow() {
        return nameShow;
    }

    public void setNameShow(String nameShow) {
        this.nameShow = nameShow;
    }

    public String getSexShow() {
        return sexShow;
    }

    public void setSexShow(String sexShow) {
        this.sexShow = sexShow;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

}
