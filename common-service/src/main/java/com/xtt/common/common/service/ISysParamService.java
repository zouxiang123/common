/**   
 * @Title: SysParamService.java 
 * @Package com.xtt.txgl.common.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月3日 下午3:19:29 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.SysParam;
import com.xtt.common.dao.po.SysParamPO;

public interface ISysParamService {

    /**
     * 根据参数名称查询参数
     * 
     * @Title: getByName
     * @param name
     * @return
     * 
     */
    SysParam getByName(String name);

    /**
     * 根据参数名称和租户编号查询参数
     * 
     * @Title: getByName
     * @param name
     * @param tenantId
     * @return
     * 
     */
    SysParam getByName(String name, Integer tenantId);

    /**
     * 获取当前租户配置的系统参数
     * 
     * @Title: getByTenantId
     * @param tenantId
     * @param sysOwner
     * @return
     *
     */
    List<SysParamPO> getByTenantId(Integer tenantId, String sysOwner);

    /**
     * 保存系统参数配置
     * 
     * @Title: saveParam
     * @param param
     * @return
     *
     */
    String saveParam(SysParam param);

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<SysParamPO> selectByCondition(SysParamPO record);

}
