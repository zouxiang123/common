/**   
 * @Title: PatientKpiHistPO.java 
 * @Package com.xtt.common.dao.model
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年4月5日 上午10:24:25 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientKpiHist;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientKpiHistPO extends PatientKpiHist {
    private Integer limit;

    private String recordDateShow;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getRecordDateShow() {
        if (super.getRecordDate() != null) {
            recordDateShow = DateFormatUtil.convertDateToStr(super.getRecordDate());
        }
        return recordDateShow;
    }

    public void setRecordDateShow(String recordDateShow) {
        this.recordDateShow = recordDateShow;
    }

}
