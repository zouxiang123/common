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

public class PatientKpiHistPO extends PatientKpiHist {
    private Integer limit;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
