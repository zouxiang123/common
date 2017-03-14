/**   
 * @Title: DictGradePO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年2月21日 下午1:24:33 
 *
 */
package com.xtt.common.dao.po;

import com.xtt.common.dao.model.DictGrade;

public class DictGradePO extends DictGrade {

    private String[] typeCodes;

    public String[] getTypeCodes() {
        return typeCodes;
    }

    public void setTypeCodes(String[] typeCodes) {
        this.typeCodes = typeCodes;
    }

}
