package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysGroupTenant;

@Repository
public interface SysGroupTenantMapper {
    int insert(SysGroupTenant record);

    int insertSelective(SysGroupTenant record);

    int save(SysGroupTenant record);

    SysGroupTenant getSysGroupByFkTenantId(Integer fkTenantId);
}