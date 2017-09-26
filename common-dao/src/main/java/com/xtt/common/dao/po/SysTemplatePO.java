/**   
 * @Title: SysTemplatePO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月31日 下午12:25:54 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.SysTemplate;

public class SysTemplatePO extends SysTemplate {
    private List<SysTemplateChildPO> childList;

    public List<SysTemplateChildPO> getChildList() {
        return childList;
    }

    public void setChildList(List<SysTemplateChildPO> childList) {
        this.childList = childList;
    }

}
