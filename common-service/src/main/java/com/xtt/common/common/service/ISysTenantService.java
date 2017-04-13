/**
 * @Title: ITenantService.java
 * @Package com.xtt.common.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2016年3月3日 下午1:37:42
 *
 */
package com.xtt.common.common.service;

import java.util.Collection;
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
     * 查询所有有效的租户
     *
     * @Title: selectAll
     * @return
     *
     */
    public List<SysTenant> listAllEnable();

    /**
     * 根据账户名称查询
     *
     * @param account
     * @param sysOwner
     * @return
     */
    public List<SysTenant> listByAccount(String account, String sysOwner);

    /**
     * 根据集团id查询对应的租户
     * 
     * @Title: listByGroupId
     * @param groupId
     * @return
     *
     */
    public List<SysTenant> listByGroupId(Integer groupId);

    /**
     * 查询所有有效的普通租户，不包含集团虚拟租户
     * 
     * @Title: listAllEnableNormal
     * @return
     *
     */
    public List<SysTenant> listAllEnableNormal();

    /**
     * 获取默认租户
     * 
     * @Title: getDefault
     * @return
     *
     */
    public SysTenant getDefault();

    /**
     * 根据多个租户id查询数据
     * 
     * @Title: listByIds
     * @param ids
     * @return
     *
     */
    public List<SysTenant> listByIds(Collection<Integer> ids);
}
