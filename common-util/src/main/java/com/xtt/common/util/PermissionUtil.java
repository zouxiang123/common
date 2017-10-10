/**   
 * @Title: PermissionUtil.java 
 * @Package com.xtt.common.common.util.permission
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
import com.xtt.common.permission.PermissionCache;
import com.xtt.platform.util.io.JsonUtil;
import com.xtt.platform.util.lang.StringUtil;

public class PermissionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionUtil.class);
    private static PermissionCache permissionFactory = new PermissionCache();

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

    /**
     * 判断url是否有权限
     * 
     * @Title: hasPermission
     * @param url
     * @param authMap
     * @return
     *
     */
    public static boolean hasPermission(String url, Map<String, Object> authMap) {
        List<SysObjDto> list = authMap != null ? covertToList((String) authMap.get(CommonConstants.USER_NON_PERMISSION))
                        : UserUtil.getNonPermissionList();
        if (CollectionUtils.isEmpty(list)) // role has all permission
            return true;
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
                urlArr = s.getUrl().split(",");// 一个key可能存在多个url
                for (int i = 0; i < urlArr.length; i++) {
                    if (realPath.equals(urlArr[i])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 通过权限key获取权限url,如果当前权限不存在url，获取其子节点的第一个url
     * 
     * @param key
     * @returns 权限中的url地址
     */
    public static String getUrlByKey(String key) {
        SysObjDto obj = getObjByKey(key);
        if (obj != null) {
            if (StringUtil.isNotBlank(obj.getUrl())) {
                if (obj.getUrl().indexOf(",") > -1) {// 如果一个权限对应多个url，默认获取第一个
                    return obj.getUrl().split(",")[0];
                } else {
                    return obj.getUrl();
                }
            } else {
                return getUrlByPcode(obj.getCode());
            }
        }
        return null;
    }

    /**
     * 根据key获取权限对象
     * 
     * @Title: getObjByKey
     * @param key
     * @return
     *
     */
    public static SysObjDto getObjByKey(String key) {
        if (StringUtil.isNotBlank(key)) {
            List<SysObjDto> list = UserUtil.getPermissionList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (SysObjDto obj : list) {
                    if (key.equals(obj.getKey())) {
                        return obj;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 通过pCode获取该对象下第一个有权限的url
     * 
     * @Title: getUrlByPcode
     * @param code
     * @return 第一个有权限的url,如果对应多个url，则返回第一个
     *
     */
    public static String getUrlByPcode(String code) {
        SysObjDto obj = getObjByPcode(code);
        if (obj != null) {
            if (obj.getUrl().indexOf(",") > -1) {// 如果一个权限对应多个url，默认获取第一个
                return obj.getUrl().split(",")[0];
            } else {
                return obj.getUrl();
            }
        }
        return null;
    }

    /**
     * 通过pCode获取该对象下第一个有权限而且有url的对象
     * 
     * @param val
     * @returns permission Object
     */
    public static SysObjDto getObjByPcode(String code) {
        if (StringUtil.isNotBlank(code)) {
            List<SysObjDto> list = UserUtil.getPermissionList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (SysObjDto obj : list) {
                    if (code.equals(obj.getpCode())) {
                        if (StringUtil.isBlank(obj.getUrl())) {// 如果不存在url，获取其子节点的url
                            return getObjByPcode(obj.getCode());
                        } else {
                            return obj;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 将SysObjDtoList转为JSONStr
     * 
     * @Title: covertToStr
     * @param list
     * @return
     *
     */
    public static String covertToStr(List<SysObjDto> list) {
        if (list != null && !list.isEmpty()) {
            return JsonUtil.AllJsonUtil().toJson(list);
        }
        return null;
    }

    /**
     * 将JSONStr转为List
     * 
     * @Title: covertToList
     * @param list
     * @return
     *
     */
    public static List<SysObjDto> covertToList(String jsonStr) {
        List<SysObjDto> list = null;
        if (StringUtil.isNotBlank(jsonStr)) {
            try {
                list = JsonUtil.AllJsonUtil().fromJson(jsonStr, new TypeReference<List<SysObjDto>>() {
                });
            } catch (Exception e) {
                LOGGER.warn("convert json str failed ", e);
            }
        }
        return list;
    }
}
