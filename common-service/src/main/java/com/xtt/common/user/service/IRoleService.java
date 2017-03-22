/**
 * @Title: IRoleService.java
 * @Package com.xtt.common.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2015年10月23日 下午2:16:04
 *
 */
package com.xtt.common.user.service;

import java.util.List;

import com.xtt.common.dao.model.SysObj;
import com.xtt.common.dao.model.SysRole;

public interface IRoleService {
    /**
     * 通过租户id获取该租户所有角色
     *
     * @Title: selectSysRoleByTenantId
     * @param sysOwner
     * @return
     *
     */
    List<SysRole> getRoleListByTenantId(Integer tenantId, String sysOwner);

    /**
     * 根据角色Id获取该角色下所有的菜单
     *
     * @Title: getMenuListByRoleId
     * @param roleId
     * @param types
     * @return
     *
     */
    List<SysObj> getMenuListByRoleId(Long[] roleId, String[] types);

    /**
     * 查询该版本下所有菜单
     *
     * @Title: getAllMenuList
     * @return
     *
     */
    List<SysObj> getAllMenuList(String[] types, String sysOwner);

    /**
     * 保存选中的menu
     *
     * @Title: saveMenuList
     * @param checkedMenuIds
     * @param menuRoleId
     * @param types
     *
     */
    void saveMenuList(Long[] checkedMenuIds, Long menuRoleId, String[] types);

    /**
     * 保存角色信息
     *
     * @Title: saveRoleList
     * @param roleList
     *
     */
    String saveRoleList(Long[] delRoleIds, SysRole[] roles, String sysOwner);

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
     * 获取当前角色没有权限的菜单
     *
     * @param roleIds
     *
     * @Title: getNotChecked
     * @param roleIds
     * @return
     *
     */
    List<SysObj> getNotChecked(Long[] roleIds, String[] types, String sysOwner);

    /**
     * 添加菜单
     *
     * @Title: addMenu
     * @param obj
     *
     */
    String addMenu(SysObj obj);

    /**
     * 删除菜单
     *
     * @Title: delMenu
     * @param menuIds
     *
     */
    void delMenu(Long[] menuIds);

    /**
     * 根据固定角色类型查找当前租户下的对象
     *
     * @Title: selectByConstant
     * @param constantType
     * @param tenantId
     * @return
     *
     */
    SysRole getByConstant(int constantType, Integer tenantId, String sysOwner);

    /**
     * 根据固定角色类型查找当前租户下的对象
     *
     * @Title: getByConstants
     * @param constantTypes
     * @param tenantId
     * @return
     *
     */
    List<SysRole> getByConstants(int[] constantTypes, Integer tenantId, String sysOwner);

}
