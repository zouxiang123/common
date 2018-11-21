/**   
 * @Title: IPermissionFactory.java 
 * @Package com.xtt.common.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月18日 上午10:51:01 
 *
 */
package com.xtt.common.permission;

import java.util.List;

import com.xtt.common.dto.AppMenuDto;

public interface IPermissionFactory {
    /**
     * 根据用户角色ID获取所有选中的菜单数据
     * 
     * @Title: getPermissionList
     * @param roleIds
     * @return
     *
     */
    List<AppMenuDto> getPermissionList(Long[] roleIds);

    /**
     * 当前角色没有权限的菜单
     * 
     * @Title: getPermissionList
     * @param roleIds
     * @return
     *
     */
    List<AppMenuDto> getNonPermissionList(Long[] roleIds);

    /**
     * 根据用户角色ID获取所有有权限的接口菜单
     * 
     * @Title: getApiPermissionList
     * @param roleIds
     * @return
     *
     */
    List<AppMenuDto> getApiPermissionList(Long[] roleIds);

    /**
     * 当前角色没有权限的接口菜单
     * 
     * @Title: getApiNonPermissionList
     * @param roleIds
     * @return
     *
     */
    List<AppMenuDto> getApiNonPermissionList(Long[] roleIds);
}
