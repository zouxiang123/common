package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayFilterRule;

@Repository
public interface AssayFilterRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssayFilterRule record);

    int insertSelective(AssayFilterRule record);

    AssayFilterRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssayFilterRule record);

    int updateByPrimaryKey(AssayFilterRule record);

    /**
     * 根据租户号查询清洗数据逻辑
     * 
     * @Title: getByTenantId
     * @param fkTenantId
     * @return
     *
     */
    AssayFilterRule getByTenantId(Integer fkTenantId);

}