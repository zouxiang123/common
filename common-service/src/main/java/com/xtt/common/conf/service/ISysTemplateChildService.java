/**   
 * @Title: ISysTemplateChildService.java 
 * @Package com.xtt.txgl.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月31日 上午11:31:02 
 *
 */
package com.xtt.common.conf.service;

import java.util.List;

import com.xtt.common.dao.model.SysTemplateChild;
import com.xtt.common.dao.po.SysTemplateChildPO;

public interface ISysTemplateChildService {
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     * 
     */
    List<SysTemplateChildPO> selectByCondition(SysTemplateChildPO record);

    /**
     * 保存模板关联的选项
     * 
     * @Title: saveSysTemplateChild
     * @param fkSysTemplateId
     * @param records
     * @return
     * 
     */
    public int saveSysTemplateChild(Long fkSysTemplateId, List<SysTemplateChild> records);
}
