/**   
 * @Title: AppDictPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月8日 上午11:44:23 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.AppDict;

public class AppDictPO extends AppDict {
    private Boolean isOrderBy;// 是否只根据order_by排序

    public Boolean getIsOrderBy() {
        return isOrderBy;
    }

    public void setIsOrderBy(Boolean isOrderBy) {
        this.isOrderBy = isOrderBy;
    }
}
