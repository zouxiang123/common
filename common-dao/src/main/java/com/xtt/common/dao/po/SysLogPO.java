/**   
 * @Title: SysLogPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月14日 下午8:35:49 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.SysLog;
import com.xtt.platform.util.time.DateFormatUtil;

public class SysLogPO extends SysLog {
    private String name;
    private String startDate;
    private String endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getLogTimeShow() {
        if (super.getLogTime() != null) {
            return DateFormatUtil.format(super.getLogTime(), "yyyy-MM-dd HH:mm:ss");
        }
        return "";
    }

}
