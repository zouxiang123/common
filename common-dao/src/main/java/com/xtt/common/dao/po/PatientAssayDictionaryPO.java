/**   
 * @Title: PatientAssayDictionaryPO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:46:13 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.PatientAssayDictionary;
import com.xtt.platform.util.lang.NumberFormatUtil;

public class PatientAssayDictionaryPO extends PatientAssayDictionary {
    /** 数字 */
    public static Integer TYPE_NUMBER = 1;
    /** 字符 */
    public static Integer TYPE_STRING = 2;

    private Long[] pIds;

    private String categoryName;
    private String minValueShow;
    private String maxValueShow;
    private String otherValueShow;
    private int index;

    public Long[] getpIds() {
        return pIds;
    }

    public void setpIds(Long[] pIds) {
        this.pIds = pIds;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMinValueShow() {
        if (super.getMinValue() != null)
            minValueShow = NumberFormatUtil.formatNumber(super.getMinValue());
        return minValueShow;
    }

    public void setMinValueShow(String minValueShow) {
        this.minValueShow = minValueShow;
    }

    public String getMaxValueShow() {
        if (super.getMaxValue() != null)
            maxValueShow = NumberFormatUtil.formatNumber(super.getMaxValue());
        return maxValueShow;
    }

    public void setMaxValueShow(String maxValueShow) {
        this.maxValueShow = maxValueShow;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOtherValueShow() {
        return otherValueShow;
    }

    public void setOtherValueShow(String otherValueShow) {
        this.otherValueShow = otherValueShow;
    }

}
