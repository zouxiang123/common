/**   
 * @Title: CmDiagnosisHistTumourPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年4月1日 上午9:38:47 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.CmDiagnosisHistTumour;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmDiagnosisHistTumourPO extends CmDiagnosisHistTumour {
    private String recordDateShow;
    private String createTimeShow;
    private String createUserName;
    private String multiTenant;
    private String hospitalName; // 就诊医院
    private String groupTenant; // 租户所属集团下全部的租户

    public String getGroupTenant() {
        return groupTenant;
    }

    public void setGroupTenant(String groupTenant) {
        this.groupTenant = groupTenant;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getRecordDateShow() {
        if (super.getRecordDate() != null) {
            this.recordDateShow = DateFormatUtil.convertDateToStr(super.getRecordDate());
        }
        return recordDateShow;
    }

    public void setRecordDateShow(String recordDateShow) {
        if (StringUtil.isNotBlank(recordDateShow)) {
            super.setRecordDate(DateFormatUtil.convertStrToDate(recordDateShow));
        }
        this.recordDateShow = recordDateShow;
    }

    public String getCreateTimeShow() {
        if (super.getCreateTime() != null) {
            this.createTimeShow = DateFormatUtil.convertDateToStr(super.getCreateTime());
        }
        return createTimeShow;
    }

    public void setCreateTimeShow(String createTimeShow) {
        this.createTimeShow = createTimeShow;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getMultiTenant() {
        return multiTenant;
    }

    public void setMultiTenant(String multiTenant) {
        this.multiTenant = multiTenant;
    }

}
