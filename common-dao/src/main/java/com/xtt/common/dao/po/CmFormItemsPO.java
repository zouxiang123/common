/**   
 * @Title: CmFormConfPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午1:00:58 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.CmFormItems;

public class CmFormItemsPO extends CmFormItems {
    private Boolean isNew;
    private String category;

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
