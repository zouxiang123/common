package com.xtt.common.dao.mapper;

import com.xtt.common.dao.model.AssayFilterRule;

public interface AssayFilterRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssayFilterRule record);

    int insertSelective(AssayFilterRule record);

    AssayFilterRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssayFilterRule record);

    int updateByPrimaryKey(AssayFilterRule record);
}