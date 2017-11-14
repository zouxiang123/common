/**   
 * @Title: SysUserTenantServiceImpl.java 
 * @Package com.xtt.common.user.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年3月16日 上午10:46:37 
 *
 */
package com.xtt.common.user.service.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.SysUserTenantMapper;
import com.xtt.common.dao.model.SysUserTenant;
import com.xtt.common.user.service.ISysUserTenantService;
import com.xtt.common.util.UserUtil;

@Service
public class SysUserTenantServiceImpl implements ISysUserTenantService {
    @Autowired
    private SysUserTenantMapper sysUserTenantMapper;

    @Override
    public void saveBatch(List<SysUserTenant> list) {
        for (int i = 0; i < list.size(); i++) {
            sysUserTenantMapper.insert(list.get(i));
        }
    }

    @Override
    public List<SysUserTenant> listByUserId(Long userId) {
        SysUserTenant record = new SysUserTenant();
        record.setFkUserId(userId);
        return listByCondition(record);
    }

    @Override
    public void save(SysUserTenant record) {
        sysUserTenantMapper.insert(record);
    }

    @Override
    public List<SysUserTenant> listByCondition(SysUserTenant record) {
        return sysUserTenantMapper.listByCondition(record);
    }

    @Override
    public void removeByIds(Collection<Long> ids) {
        sysUserTenantMapper.removeByIds(ids);
    }

    @Override
    public SysUserTenant getByUserId(Long userId) {
        SysUserTenant record = new SysUserTenant();
        record.setFkUserId(userId);
        record.setSysOwner(UserUtil.getSysOwner());
        record.setFkTenantId(UserUtil.getTenantId());
        List<SysUserTenant> list = listByCondition(record);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void updateByPrimaryKeySelective(SysUserTenant record) {
        sysUserTenantMapper.updateByPrimaryKeySelective(record);
    }
}
