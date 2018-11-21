package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppRole;

@Repository
public interface AppRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppRole record);

    int insertSelective(AppRole record);

    AppRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppRole record);

    int updateByPrimaryKey(AppRole record);

    /**
     * 根据appowner获取所以角色
     * 
     * @Title: listByAppOwner
     * @param appOwner
     * @return
     *
     */
    List<AppRole> listByAppOwner(@Param("appOwner") String appOwner);

    /**
     * 根据roleIds 批量删除角色数据
     * 
     * @Title: batchDeleteByRoleIds
     * @param roleIds
     *
     */
    void batchDeleteByRoleIds(Long[] roleIds);
}