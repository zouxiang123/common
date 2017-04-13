package com.xtt.common.dao.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysTenant;

@Repository
public interface SysTenantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysTenant record);

    int insertSelective(SysTenant record);

    SysTenant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysTenant record);

    int updateByPrimaryKey(SysTenant record);

    /* user define */

    /**
     * 根据自定义条件查询租户数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<SysTenant> listByCondition(SysTenant record);

    /**
     * 根据账户名称查询对应的租户数据
     *
     * @param account
     * @param sysOwner
     * @return
     */
    List<SysTenant> listByAccount(@Param("account") String account, @Param("sysOwner") String sysOwner);

    /**
     * 根据集团id查询对应的租户
     * 
     * @Title: listByGroupId
     * @param groupId
     * @return
     *
     */
    List<SysTenant> listByGroupId(Integer groupId);

    /**
     * 根据多个租户id查询数据
     * 
     * @Title: listByIds
     * @param ids
     * @return
     *
     */
    List<SysTenant> listByIds(@Param("ids") Collection<Integer> ids);
}