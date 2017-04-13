/**
 * @Title: SysTenantServiceImpl.java
 * @Package com.xtt.common.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2016年3月3日 下午1:40:07
 *
 */
package com.xtt.common.common.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.dao.mapper.SysTenantMapper;
import com.xtt.common.dao.model.SysTenant;

@Service
public class SysTenantServiceImpl implements ISysTenantService {
    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public SysTenant getById(Integer id) {
        return sysTenantMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysTenant> listAllEnable() {
        SysTenant record = new SysTenant();
        record.setIsEnable(true);
        return listByCondition(record);
    }

    public List<SysTenant> listByCondition(SysTenant record) {
        return sysTenantMapper.listByCondition(record);
    }

    @Override
    public List<SysTenant> listByAccount(String account, String sysOwner) {
        return sysTenantMapper.listByAccount(account, sysOwner);
    }

    @Override
    public List<SysTenant> listByGroupId(Integer groupId) {
        return sysTenantMapper.listByGroupId(groupId);
    }

    @Override
    public List<SysTenant> listAllEnableNormal() {
        SysTenant record = new SysTenant();
        record.setIsEnable(true);
        record.setGroupFlag(false);
        return listByCondition(record);
    }

    @Override
    public SysTenant getDefault() {
        SysTenant record = new SysTenant();
        record.setIsDefault(true);
        record.setIsEnable(true);
        List<SysTenant> list = listByCondition(record);
        // 默认租户只可能存在一个
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<SysTenant> listByIds(Collection<Integer> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            return sysTenantMapper.listByIds(ids);
        } else {
            return new ArrayList<>();
        }
    }

}
