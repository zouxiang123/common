/**   
 * @Title: UserUtil.java 用户工具类，方便在系统各个地方调用
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月16日 上午10:30:37 
 *
 */
package com.xtt.common.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.dto.SysObjDto;
import com.xtt.common.permission.UserUtilContext;

public class UserUtil {
    private UserUtil() {
    }

    /**
     * 
     * @Title: setLoginUser 登录时存放当前登陆者对象
     * @param loginUser
     * 
     */
    public static void setLoginUser(String token, LoginUser loginUser) {

        UserUtilContext.setLoginUser(token, loginUser);
    }

    /**
     * 
     * @Title: setLoginUser 登录时存放当前登陆者对象
     * @param loginUser
     * 
     */
    public static void setLoginUser(LoginUser loginUser) {

        UserUtilContext.setLoginUser(loginUser);
    }

    /**
     * 获取当前登录者对象
     * 
     * @Title: getLoginUser
     * @return
     * 
     */
    public static LoginUser getLoginUser() {

        return UserUtilContext.getLoginUser();
    }

    /**
     * 获取当前登陆者id
     * 
     * @Title: getLoginUserId
     * 
     */
    public static Long getLoginUserId() {

        return UserUtilContext.getLoginUserId();
    }

    /**
     * 获取当前登录者名称
     * 
     * @Title: getLoginUserName
     * 
     */
    public static String getLoginUserName() {

        return UserUtilContext.getLoginUserName();
    }

    /**
     * 获取当前登录者角色
     * 
     * @Title: getLoginUserName
     * 
     */
    public static String getLoginRoleId() {

        return getLoginUser().getRoleId();
    }

    /**
     * 获取当前登录者的角色类别
     * 
     * @Title: getRoleType
     * @return
     * 
     */
    public static String getRoleType() {
        return getLoginUser().getRoleType();
    }

    /**
     * 获取当前登录者的角色是否为医生
     * 
     * @Title: isDoctor
     * @return
     * 
     */
    public static boolean isDoctor() {
        String roleType = getLoginUser().getRoleType();
        if (roleType.equals(CommonConstants.ROLE_DOCTOR)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前登录者的角色是否为护士
     * 
     * @Title: isNurse
     * @return
     * 
     */
    public static boolean isNurse() {
        String roleType = getLoginUser().getRoleType();
        if (roleType.equals(CommonConstants.ROLE_NURSE)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前登录者的角色是否为管理员
     * 
     * @Title: isNurse
     * @return
     * 
     */
    public static boolean isAdmin() {
        String roleType = getLoginUser().getRoleType();
        if (roleType.equals(CommonConstants.ROLE_ADMIN)) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前登录者的角色是否为[其他或者工程师]
     * 
     * @Title: isEngineer
     * @return
     * 
     */
    public static Boolean isEngineer() {
        String roleType = getLoginUser().getRoleType();
        if (roleType.equals(CommonConstants.ROLE_ENGINEER)) {
            return true;
        }
        return false;
    }

    /**
     * 获取租户id
     * 
     * @Title: getTenantId
     * @return
     * 
     */
    public static Integer getTenantId() {
        return UserUtilContext.getLoginUser().getTenantId();
    }

    /**
     * 设置权限到session
     * 
     * @Title: setPermission
     * @param roleId
     * 
     */
    public static void setPermission(String roleId) {
        if (StringUtils.isNotEmpty(roleId)) {
            UserUtilContext.setPermission(convertRoleToArray(roleId));
        } else {
            UserUtilContext.setPermission(null);
        }
    }

    /**
     * 设置当前没有权限的菜单到session
     * 
     * @Title: getNonPermissionList
     * @return
     * 
     */
    public static void setNonPermissionList(String roleId) {
        if (StringUtils.isNotEmpty(roleId)) {
            UserUtilContext.setNonPermissionList(convertRoleToArray(roleId));
        } else {
            UserUtilContext.setNonPermissionList(null);
        }
    }

    /**
     * 获取有权限的菜单数据
     * 
     * @Title: getPermissionList
     * @return
     *
     */
    public static List<SysObjDto> getPermissionList() {
        return UserUtilContext.getPermissionList();
    }

    /**
     * 获取没有权限的菜单数据
     * 
     * @return
     * 
     * @Title: getNonPermissionList
     * @return
     * 
     */
    public static List<SysObjDto> getNonPermissionList() {
        return UserUtilContext.getNonPermissionList();
    }

    /**
     * 设置有API权限的菜单到session
     * 
     * @Title: getNonPermissionList
     * @return
     * 
     */
    public static void setApiPermissionList(String roleId) {
        if (StringUtils.isNotEmpty(roleId)) {
            UserUtilContext.setApiPermissionList(convertRoleToArray(roleId));
        } else {
            UserUtilContext.setApiPermissionList(null);
        }
    }

    /**
     * 获取有API权限的菜单
     * 
     * @return
     * 
     * @Title: getNonPermissionList
     * @return
     * 
     */
    public static String getApiPermissionList() {
        return UserUtilContext.getApiPermissionList();
    }

    private static Long[] convertRoleToArray(String roleId) {
        Long[] roleIds = null;
        if (StringUtils.isNotEmpty(roleId)) {
            String[] tempRoleIds = roleId.split(",");
            roleIds = new Long[tempRoleIds.length];
            for (int i = 0; i < tempRoleIds.length; i++) {
                roleIds[i] = Long.valueOf(tempRoleIds[i]);
            }
        }
        return roleIds;
    }

    public static void setThreadTenant(Integer id) {
        UserUtilContext.setThreadTenant(id);
    }

    public static void setThreadLoginUser(LoginUser user) {
        UserUtilContext.setThreadLoginUser(user);
    }
}
