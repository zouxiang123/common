/**   
 * @Title: ISysTemplateService.java 
 * @Package com.xtt.common.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月7日 上午10:46:00 
 *
 */
package com.xtt.common.conf.service;

import java.util.List;

import com.xtt.common.dao.model.SysTemplate;
import com.xtt.common.dao.po.SysTemplatePO;

/**
 * @ClassName: ISysTemplateService
 * @date: 2016年4月7日 上午10:46:00
 * @version: V1.0
 * 
 */
public interface ISysTemplateService {

    /**
     * 根据类型查询模板
     * 
     * @Title: selectByType
     * @param type
     * @param fkTenantId
     * @return
     * 
     */
    List<SysTemplatePO> selectByType(String type, boolean needChild, String sysOwner);

    /**
     * 查询模板类型
     * 
     * @Title: selectTemplateType
     * @return
     * 
     */
    List<SysTemplate> selectTemplateType(String sysOwner);

    /**
     * 根据类型查询模板
     * 
     * @Title: selectByType
     * @param type
     * @param fkTenantId
     * @return
     * 
     */
    int saveTemplate(SysTemplatePO record);

    /**
     * 删除模板
     * 
     * @Title: selectByType
     * @param type
     * @param fkTenantId
     * @return
     * 
     */
    int deleteTemplate(SysTemplate record);

    /**
     * 根据id更新模板默认值
     * 
     * @param record
     * @return
     */
    int updateTemplateStatus(SysTemplate record);
}
