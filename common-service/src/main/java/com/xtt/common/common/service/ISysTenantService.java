/**   
 * @Title: ITenantService.java 
 * @Package com.xtt.common.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年3月3日 下午1:37:42 
 *
 */
package com.xtt.common.common.service;

import java.util.List;

import com.xtt.common.dao.model.SysTenant;

public interface ISysTenantService {
    /**
     * 根据租户id查询租户对象
     * 
     * @Title: getById
     * @param id
     * @return
     * 
     */
    public SysTenant getById(Integer id);

    /**
     * 查询所有的租户
     * 
     * @Title: selectAll
     * @return
     * 
     */
    public List<SysTenant> selectAll();

    /**
     * @Title: selectAllSwitch
     * @return 查询可用状态的住户
     */
    public List<SysTenant> selectAllSwitch();

    /**
     * @Title: selectByName
     * @param name
     * @return 查询某医院的信息
     */
    public SysTenant selectByName(String name);

    /**
     * 根据条件查询默认租户
     * 
     * @Title: selectByCondition
     * @return
     * 
     */
    SysTenant selectDefault(SysTenant record);

    /**
     * @Title: updateByPrimaryKeySelective
     * @param record
     * @return 修改租户信息
     */
    public int updateByPrimaryKeySelective(SysTenant record);

    /**
     * @Title: updateEnable
     * @param sysTenant
     * @return 自动失效
     */
    public int updateEnable(SysTenant sysTenant);

    /**
     * @Title: selectTenantByName
     * @param record
     * @return 校验是否重复添加
     */
    public List<SysTenant> selectTenantByName(SysTenant record);
}
