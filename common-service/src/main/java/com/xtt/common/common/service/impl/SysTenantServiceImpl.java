/**   
 * @Title: SysTenantServiceImpl.java 
 * @Package com.xtt.txgl.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年3月3日 下午1:40:07 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.List;

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
    public List<SysTenant> selectAll() {
        return sysTenantMapper.selectAll();
    }

    @Override
    public SysTenant selectDefault(SysTenant record) {
        return sysTenantMapper.selectDefault(record);
    }

    @Override
    public List<SysTenant> selectAllSwitch() {
        return sysTenantMapper.selectAllSwitch();
    }

    @Override
    public int updateByPrimaryKeySelective(SysTenant record) {
        return sysTenantMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<SysTenant> selectTenantByName(SysTenant record) {
        return sysTenantMapper.selectTenantByName(record);
    }

    @Override
    public int updateEnable(SysTenant sysTenant) {
        return sysTenantMapper.updateEnable(sysTenant);
    }

    @Override
    public SysTenant selectByName(String name) {
        return sysTenantMapper.selectByName(name);
    }

}
