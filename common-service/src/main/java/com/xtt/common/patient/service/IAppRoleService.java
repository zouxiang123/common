/**   
 * @Title: AppRoleService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年7月21日 下午5:13:57 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.model.AppMenu;
import com.xtt.common.dao.model.AppRole;

public interface IAppRoleService {
    void deleteByPrimaryKey(Long id);

    void insert(AppRole record);

    void insertSelective(AppRole record);

    AppRole selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(AppRole record);

    void updateByPrimaryKey(AppRole record);

    /**
     * 获取版本下所有的菜单
     * 
     * @Title: getAllMenuList
     * @param types
     *            菜单类型
     * @param appSysOwner
     *            app系统版本
     * @return
     *
     */
    List<AppMenu> listAllMenuList(String[] types, String appSysOwner);

    /**
     * 获取版本下所有的菜单
     * 
     * @Title: getAllMenuList
     * @param types
     *            菜单类型
     * @param appSysOwner
     *            app系统版本
     * @return
     *
     */
    List<AppMenu> listMenuListBySysOwner(String[] types, String appSysOwner, String sysOwner);

    /**
     * 获取版本下所有的菜单
     * 
     * @Title: getAllMenuList
     * @param types
     *            菜单类型
     * @param appSysOwner
     *            app系统版本
     * @return
     *
     */
    List<AppMenu> listMenuListByHospital(String[] types, Integer fkHospitalId);

    /**
     * 根据角色Id获取该角色下所有的菜单
     * 
     * @Title: getMenuListByRoleId
     * @param roleId
     * @param types
     * @return
     *
     */
    List<AppMenu> listMenuListByRoleId(Long[] roleId, String[] types);

    /**
     * 根据appowner获取所有角色
     * 
     * @Title: listByAppOwner
     * @param appOwner
     * @return
     *
     */
    List<AppRole> listByAppOwner(String appOwner);

    /**
     * 保存选中的菜单
     * 
     * @Title: saveMenuList
     * @param checkedMenuIds
     * @param menuRoleId
     * @param types
     *
     */
    void saveMenuList(Long[] checkedMenuIds, Long menuRoleId, String[] types);

    /**
     * 保存选中的菜单
     * 
     * @Title: saveMenuList
     * @param checkedMenuIds
     * @param menuRoleId
     * @param types
     *
     */
    void saveHospitalMenuList(Long[] checkedMenuIds, Integer fkHospitalId, String[] types);

    /**
     * 保存角色信息
     *
     * @Title: saveRoleList
     * @param roleList
     *
     */
    String saveRoleList(Long[] delRoleIds, AppRole[] roles, String sysOwner, String appSysOwner);

    /**
     * 删除角色
     *
     * @Title: delRoleById
     * @param roleId
     * @return
     *
     */
    String delRoleById(Long roleId);

    /**
     * 添加菜单
     *
     * @Title: addMenu
     * @param obj
     *
     */
    String addMenu(AppMenu obj);

    /**
     * 保存患者与角色关系
     * 
     * @Title: savePatientRole
     * @param fkPatientId
     * @param fkRoleIds
     *
     */
    void savePatientRole(Long fkPatientId, Long[] fkRoleIds);
}
