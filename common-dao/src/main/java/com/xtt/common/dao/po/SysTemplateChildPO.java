/**   
 * @Title: SysTemplateChildPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月31日 上午10:51:45 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.SysTemplateChild;

public class SysTemplateChildPO extends SysTemplateChild {

    private List<Long> fkSysTemplateIds;

    public List<Long> getFkSysTemplateIds() {
        return fkSysTemplateIds;
    }

    public void setFkSysTemplateIds(List<Long> fkSysTemplateIds) {
        this.fkSysTemplateIds = fkSysTemplateIds;
    }

}
