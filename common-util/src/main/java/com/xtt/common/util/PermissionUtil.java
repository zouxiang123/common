/**   
 * @Title: PermissionUtil.java 
 * @Package com.xtt.txgl.common.util.permission
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月13日 上午10:35:19 
 *
 */
package com.xtt.common.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dto.SysObjDto;
import com.xtt.common.permission.IPermissionFactory;
import com.xtt.common.permission.PermissionCache;
import com.xtt.platform.util.io.JsonUtil;
import com.xtt.platform.util.lang.StringUtil;

public class PermissionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionUtil.class);

    private static IPermissionFactory permissionFactory = new PermissionCache();

    /**
     * 获取角色有权限的集合
     * 
     * @Title: getPermissionList
     * @param roleIds角色id
     * @return
     *
     */
    public static List<SysObjDto> getPermissionList(Long[] roleIds) {
        return permissionFactory.getPermissionList(roleIds);
    }

    /**
     * 获取角色没有权限的集合
     * 
     * @Title: getNonPermissionList
     * @param roleIds角色id
     * @return
     *
     */
    public static List<SysObjDto> getNonPermissionList(Long[] roleIds) {
        return permissionFactory.getNonPermissionList(roleIds);
    }

    /**
     * 获取api接口有权限的list
     * 
     * @Title: getApiPermissionList
     * @param roleIds角色ids
     * @return
     *
     */
    public static List<SysObjDto> getApiPermissionList(Long[] roleIds) {
        return permissionFactory.getApiPermissionList(roleIds);
    }

    /**
     * 获取api接口没有权限的list
     * 
     * @Title: getApiNonPermissionList
     * @param roleIds角色ids
     * @return
     *
     */
    public static List<SysObjDto> getApiNonPermissionList(Long[] roleIds) {
        return permissionFactory.getApiNonPermissionList(roleIds);
    }

    public static boolean hasPermission(String url, Map<String, Object> authMap) {
        String permission = authMap != null ? (String) authMap.get(CommonConstants.USER_NON_PERMISSION) : UserUtil.getNonPermissionList();
        if (StringUtil.isBlank(permission)) // role has all permission
            return true;
        try {
            List<SysObjDto> list = JsonUtil.AllJsonUtil().fromJson(permission, new TypeReference<List<SysObjDto>>() {
            });
            if (CollectionUtils.isNotEmpty(list)) {
                if (StringUtil.isEmpty(url) || url.indexOf(".shtml") == -1) {// 地址无效时不做校验
                    return true;
                }
                String path = url.substring(1, url.indexOf(".shtml"));
                // 防止多个斜杠造成的权限判断失败
                String realPath = "";
                for (String str : path.split("/")) {
                    if (StringUtil.isNotBlank(str)) {
                        realPath += str + "/";
                    }
                }
                if (realPath.length() > 0) {
                    realPath = realPath.substring(0, realPath.length() - 1);
                }
                String[] urlArr;
                for (SysObjDto s : list) {
                    if (StringUtil.isNotBlank(s.getUrl())) {
                        urlArr = s.getUrl().split(",");// 一个动作可能存在多个url
                        for (int i = 0; i < urlArr.length; i++) {
                            if (realPath.equals(urlArr[i])) {
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warn("convert json str failed ", e);
        }
        return true;
    }
}
