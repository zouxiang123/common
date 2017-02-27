/**   
 * @Title: DictHospitalLabPO.java 
 * @Package com.lt.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月20日 下午7:35:04 
 *
 */
package com.xtt.common.dao.po;

import java.util.Collection;

import com.xtt.common.dao.model.DictHospitalLab;

public class DictHospitalLabPO extends DictHospitalLab {
    public static final String DATE_TYPE_MONTH = "m";
    public static final String DATE_TYPE_SEASON = "s";

    public static final Integer VALUE_TYPE_NUMBER = 1;

    private Collection<String> dictCodes;

    private String fkItemName;

    public String getFkItemName() {
        return fkItemName;
    }

    public void setFkItemName(String fkItemName) {
        this.fkItemName = fkItemName;
    }

    public Collection<String> getDictCodes() {
        return dictCodes;
    }

    public void setDictCodes(Collection<String> dictCodes) {
        this.dictCodes = dictCodes;
    }

}
