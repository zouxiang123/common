/**   
 * @Title: ISysUserTenantService.java 
 * @Package com.xtt.common.user.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年3月16日 上午10:46:18 
 *
 */
package com.xtt.common.user.service;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.model.SysUserTenant;

public interface ISysUserTenantService {
    /**
     * 批量保存数据
     * 
     * @Title: saveBatch
     * @param list
     *
     */
    void saveBatch(List<SysUserTenant> list);

    /**
     * 根据用户id查询数据
     * 
     * @Title: listByUserId
     * @param userId
     * @return
     *
     */
    List<SysUserTenant> listByUserId(Long userId);

    /**
     * 保存数据
     * 
     * @Title: save
     * @param record
     *
     */
    void save(SysUserTenant record);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<SysUserTenant> listByCondition(SysUserTenant record);

    /**
     * 根据多个id删除数据
     * 
     * @Title: removeByIds
     * @param ids
     *
     */
    void removeByIds(Collection<Long> ids);

    /**
     * 根据用户id查询本系统，本租户的关联数据
     * 
     * @Title: getByUserId
     * @param userId
     * @return
     *
     */
    SysUserTenant getByUserId(Long userId);

    /**
     * 根据id更新不为null的字段
     * 
     * @Title: updateByPrimaryKeySelective
     * @param record
     *
     */
    void updateByPrimaryKeySelective(SysUserTenant record);

}
