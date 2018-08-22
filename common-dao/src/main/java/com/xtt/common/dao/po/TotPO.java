/**   
 * @Title: TotPo.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年8月22日 下午5:34:34 
 *
 */
package com.xtt.common.dao.po;

import java.math.BigDecimal;

public class TotPO {
    private String yearMonth;
    private BigDecimal value;
    public String getYearMonth() {
        return yearMonth;
    }
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
