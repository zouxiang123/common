package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysParamRange;

@Repository
public interface SysParamRangeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysParamRange record);

    int insertSelective(SysParamRange record);

    SysParamRange selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysParamRange record);

    int updateByPrimaryKey(SysParamRange record);

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<SysParamRange> listByCondition(SysParamRange record);
}