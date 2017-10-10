package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysGroup;

@Repository
public interface SysGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    SysGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysGroup record);

    int updateByPrimaryKey(SysGroup record);
}