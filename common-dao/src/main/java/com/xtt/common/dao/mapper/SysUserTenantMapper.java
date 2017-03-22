package com.xtt.common.dao.mapper;

import java.util.Collection;
import java.util.List;

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

    /*user define*/
    /**
     * 根据条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<SysUserTenant> listByCondition(SysUserTenant record);

    /**
     * 根据用户id删除用户和租户之间的关联关系
     * 
     * @Title: deleteByUserId
     * @param fkUserId
     *
     */
    void deleteByUserId(Long fkUserId);

    /**
     * 根据id批量删除数据
     * 
     * @Title: removeByIds
     * @param ids
     *
     */
    void removeByIds(Collection<Long> ids);
}