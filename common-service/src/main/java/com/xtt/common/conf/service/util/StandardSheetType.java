/**   
 * @Title: StandardSheetType.java 
 * @Package com.test.poi
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月16日 下午4:51:01 
 *
 */
package com.xtt.common.conf.service.util;

public enum StandardSheetType {

    patient("患者"), doctor("医生"), nurse("护士");

    private final String value;

    private StandardSheetType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
