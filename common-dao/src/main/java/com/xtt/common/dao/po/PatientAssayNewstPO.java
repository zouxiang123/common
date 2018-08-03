/**   
 * @Title: PatientAssayNewstPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年12月1日 下午7:12:10 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayNewst;
import com.xtt.platform.util.time.DateFormatUtil;

public class PatientAssayNewstPO extends PatientAssayNewst {
    /**
     * 最新记录化验项状态 -1 未检查[从未检查过] 0 已化验[化验单 指定天数 内检查过该项目] 1 已逾期[化验单 指定天数 未内检查过该项目]
     */
    private int state = -1;

    private String assayTimeStr;

    // 化验项天数
    private String assayDay;
    // 显示用itemCode,如果是血透唯一标识的item_code，去除其后缀
    private String itemCodeShow;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAssayTimeStr() {
        if (this.getAssayTime() != null) {
            assayTimeStr = DateFormatUtil.convertDateToStr(this.getAssayTime(), "yyyy-MM-dd");
            // assayDay = String.valueOf(DateUtil.getDays(new Date(), this.getAssayTime()));
        }
        return assayTimeStr;
    }

    public String getAssayDay() {
        return assayDay;
    }

    public void setAssayDay(String assayDay) {
        this.assayDay = assayDay;
    }

    public String getItemCodeShow() {
        return itemCodeShow;
    }

    public void setItemCodeShow(String itemCodeShow) {
        this.itemCodeShow = itemCodeShow;
    }

}
