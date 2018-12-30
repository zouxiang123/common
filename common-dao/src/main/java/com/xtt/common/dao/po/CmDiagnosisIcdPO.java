/**   
 * @Title: CmDiagnosisIcdPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年9月29日 下午2:22:15 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.CmDiagnosisIcd;

public class CmDiagnosisIcdPO extends CmDiagnosisIcd {
    private String name;
    private String itemName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
