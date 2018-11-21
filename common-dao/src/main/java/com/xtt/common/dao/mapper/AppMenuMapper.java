package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppMenu;

@Repository
public interface AppMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppMenu record);

    int insertSelective(AppMenu record);

    AppMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppMenu record);

    int updateByPrimaryKey(AppMenu record);

    /**
     * 获取版本下所有的菜单
     * 
     * @Title: listAllMenuList
     * @param types
     *            菜单类型
     * @param appSysOwner
     *            app系统版本
     * @return
     *
     */
    List<AppMenu> listAllMenuList(@Param("types") String[] types, @Param("appSysOwner") String appSysOwner);

    /**
     * 获取版本下所有的菜单
     * 
     * @Title: listMenuListBySysOwner
     * @param types
     *            菜单类型
     * @param appSysOwner
     *            app系统版本
     * @return
     *
     */
    List<AppMenu> listMenuListBySysOwner(@Param("types") String[] types, @Param("appSysOwner") String appSysOwner,
                    @Param("sysOwner") String sysOwner);

    /**
     * 获取版本下所有的菜单
     * 
     * @Title: listMenuListBySysOwner
     * @param types
     *            菜单类型
     * @param appSysOwner
     *            app系统版本
     * @return
     *
     */
    List<AppMenu> listMenuListByHospital(@Param("types") String[] types, @Param("fkHospitalId") Integer fkHospitalId);

    /**
     * 根据角色Id获取该角色下所有的菜单
     * 
     * @Title: listMenuListByRoleId
     * @param roleId
     * @param types
     * @return
     *
     */
    List<AppMenu> listMenuListByRoleId(@Param("roleIds") Long[] roleIds, @Param("types") String[] types);

    /**
     * 根据id列表查询数据
     * 
     * @Title: selectByIds
     * @param menuIds
     * @return
     *
     */
    List<AppMenu> selectByIds(Long[] menuIds);

    /**
     * 通过type和key 查询对象
     *
     * @Title: selectByKey
     * @param key
     * @param types
     * @return
     *
     */
    AppMenu selectByKey(@Param("key") String key, @Param("types") String[] types);
}