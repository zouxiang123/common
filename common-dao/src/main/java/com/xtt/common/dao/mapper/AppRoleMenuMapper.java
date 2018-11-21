package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppRoleMenu;

@Repository
public interface AppRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppRoleMenu record);

    int insertSelective(AppRoleMenu record);

    AppRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppRoleMenu record);

    int updateByPrimaryKey(AppRoleMenu record);

    /**
     * 通过角色id和类型删除对象
     * 
     * @Title: deleteByRoleIdAndType
     * @param menuRoleId
     * @param types
     *
     */
    void deleteByRoleIdAndType(@Param("roleId") Long roleId, @Param("types") String[] types);

    /**
     * 批量插入数据
     * 
     * @Title: batchInsert
     * @param menus
     *
     */
    void insertBatch(@Param("menus") List<AppRoleMenu> menus);

    /**
     * 根据角色id批量删除对象
     * 
     * @Title: batchDeleteByRoleIds
     * @param roleIds
     *
     */
    void batchDeleteByRoleIds(Long[] roleIds);

    /**
     * 根据roleId删除所有的关联菜单数据
     * 
     * @Title: deleteByRoleId
     * @param roleId
     *
     */
    void deleteByRoleId(Long roleId);

}