package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysUserTenant;

@Repository
public interface SysUserTenantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserTenant record);

    int insertSelective(SysUserTenant record);

    SysUserTenant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserTenant record);

    int updateByPrimaryKey(SysUserTenant record);
}