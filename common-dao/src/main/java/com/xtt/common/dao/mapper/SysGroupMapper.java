package com.xtt.common.dao.mapper;

import java.util.List;

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

    /**
     * 查询所有的集团
     * 
     * @Title: getSysGroupAll
     * @param sysGroup
     * @return
     *
     */
    List<SysGroup> listSysGroup(SysGroup sysGroup);
}