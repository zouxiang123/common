package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppSysOwner;

@Repository
public interface AppSysOwnerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppSysOwner record);

    int insertSelective(AppSysOwner record);

    AppSysOwner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppSysOwner record);

    int updateByPrimaryKey(AppSysOwner record);
}