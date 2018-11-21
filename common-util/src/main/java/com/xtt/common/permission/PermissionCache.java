/**
 * @Title: PermissionByMemory.java
 * @Package com.xtt.common.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2016年1月13日 上午10:38:30
 *
 */
package com.xtt.common.permission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.xtt.common.dto.AppMenuDto;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;

public class PermissionCache implements IPermissionFactory {
    public static final String ALL_SYS_OBJ_KEY = "all_app_role_menu_key";

    public static String getKey(String appSysOwner, Long id) {
        return appSysOwner + "permission" + (id == null ? "*" : id);
    }

    public static String getOwnerKey(String appSysOwner, String sysOwner) {
        return appSysOwner + "owner_permission" + (sysOwner == null ? "*" : sysOwner);
    }

    public static String getHospitalKey(String appSysOwner, Integer fkTenantId) {
        return appSysOwner + "hospital_permission" + (fkTenantId == null ? "*" : fkTenantId);
    }

    /** 加载数据到内存 */
    public static void cacheAll(Map<String, List<AppMenuDto>> map) {
        RedisCacheUtil.batchSetObject(map);
    }

    @Override
    public List<AppMenuDto> getPermissionList(Long[] roleIds) {
        return permissionList(roleIds, new String[] { "1", "2" }, true);
    }

    @Override
    public List<AppMenuDto> getNonPermissionList(Long[] roleIds) {
        return permissionList(roleIds, new String[] { "1", "2" }, false);
    }

    @Override
    public List<AppMenuDto> getApiPermissionList(Long[] roleIds) {
        // return permissionList(roleIds, new String[] { "api" }, true);
        return permissionList(roleIds, new String[] { "1", "2" }, true);
    }

    @Override
    public List<AppMenuDto> getApiNonPermissionList(Long[] roleIds) {
        // return permissionList(roleIds, new String[] { "api" }, false);
        return permissionList(roleIds, new String[] { "1", "2" }, false);
    }

    /**
     * 通过roleId和类别获取权限数据
     *
     * @Title: getList
     * @param roleIds
     *            角色id
     * @param types
     *            菜单类别
     * @param hasPermission
     *            是否为有权限的集合
     * @return
     *
     */
    @SuppressWarnings("unchecked")
    private static List<AppMenuDto> permissionList(Long[] roleIds, String[] types, boolean hasPermission) {
        HashMap<String, AppMenuDto> permissionMap = new HashMap<>();
        HashMap<String, AppMenuDto> ownerPermissionMap = new HashMap<>();
        HashMap<String, AppMenuDto> hospitalPermissionMap = new HashMap<>();
        List<AppMenuDto> permissionList;
        String appSysOwner = UserUtil.getAppSysOwner();
        // 系统权限
        String multiSysOwner = UserUtil.getMultiSysOwner();
        String multiTenant = UserUtil.getMultiTenant();
        if (StringUtils.isNotBlank(multiSysOwner)) {
            String[] arrayOwner = multiSysOwner.split(",");
            for (String sysOwner : arrayOwner) {
                List<AppMenuDto> ownerList = (List<AppMenuDto>) RedisCacheUtil.getObject(getOwnerKey(appSysOwner, sysOwner));
                if (CollectionUtils.isNotEmpty(ownerList)) {
                    for (int t = 0; t < ownerList.size(); t++) {// 使用map去除重复权限
                        ownerPermissionMap.put(ownerList.get(t).getKey(), ownerList.get(t));
                    }
                }
            }
        }
        if (MapUtils.isEmpty(ownerPermissionMap)) {
            return new ArrayList<>();
        }
        // 医院权限
        if (StringUtils.isNotBlank(multiTenant)) {
            String[] arrayTenant = multiTenant.split(",");
            for (String tenantId : arrayTenant) {
                List<AppMenuDto> hospitalList = (List<AppMenuDto>) RedisCacheUtil.getObject(getHospitalKey(appSysOwner, Integer.valueOf(tenantId)));
                if (CollectionUtils.isNotEmpty(hospitalList)) {
                    for (int t = 0; t < hospitalList.size(); t++) {// 使用map去除重复权限
                        // 系统有的权限才放入
                        if (ownerPermissionMap.get(hospitalList.get(t).getKey()) != null) {
                            hospitalPermissionMap.put(hospitalList.get(t).getKey(), hospitalList.get(t));
                        }
                    }
                }
            }
        }
        if (MapUtils.isEmpty(hospitalPermissionMap)) {
            hospitalPermissionMap = ownerPermissionMap;
        }

        // 角色权限
        for (int i = 0; i < roleIds.length; i++) {
            List<AppMenuDto> list = (List<AppMenuDto>) RedisCacheUtil.getObject(getKey(appSysOwner, roleIds[i]));
            if (CollectionUtils.isNotEmpty(list)) {
                for (int t = 0; t < list.size(); t++) {// 使用map去除重复权限
                    // 系统有的权限才放入
                    if (ownerPermissionMap.get(list.get(t).getKey()) != null) {
                        permissionMap.put(list.get(t).getKey(), list.get(t));
                    }
                }
                list.clear();
            }
        }
        Collection<AppMenuDto> permissions;
        if (MapUtils.isNotEmpty(permissionMap)) {
            permissions = permissionMap.values();
        } else {
            permissions = hospitalPermissionMap.values();
        }
        permissionList = new ArrayList<>(permissions.size());
        permissionList.addAll(permissions);
        permissionList.sort((o1, o2) -> o1.getCode().compareTo(o2.getCode()));

        if (hasPermission) {
            return permissionList;
        }
        // get non permission list
        List<AppMenuDto> nonList = new ArrayList<>();
        List<AppMenuDto> allObjs = (List<AppMenuDto>) RedisCacheUtil.getObject(appSysOwner + ALL_SYS_OBJ_KEY);
        if (CollectionUtils.isNotEmpty(allObjs)) {
            if (CollectionUtils.isNotEmpty(permissionList)) {
                nonList = allObjs.stream().filter(obj -> !permissionMap.containsKey(obj.getKey())).collect(Collectors.toList());
            } else {// 当角色没有任何权限时
                return allObjs;
            }
        }
        return nonList;
    }
}
