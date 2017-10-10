/**   
 * @Title: UserUtil.java 用户工具类，方便在系统各个地方调用
 * @Package com.xtt.common.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月16日 上午10:30:37 
 *
 */
package com.xtt.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.dto.SysObjDto;
import com.xtt.common.permission.UserUtilContext;
import com.xtt.platform.util.lang.StringUtil;

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
        if (Objects.equals(CommonConstants.ROLE_DOCTOR, roleType)) {
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
        if (Objects.equals(CommonConstants.ROLE_NURSE, roleType)) {
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
        if (Objects.equals(CommonConstants.ROLE_ADMIN, roleType)) {
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
        if (Objects.equals(CommonConstants.ROLE_ENGINEER, roleType)) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前登录用户是否是集团管理员
     * 
     * @Title: isGroupAdmin
     * @return
     *
     */
    public static boolean isGroupAdmin() {
        return Objects.equals(CommonConstants.USER_TYPE_GROUP_ADMIN, getLoginUser().getUserType());
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
     * 设置当前角色的权限的菜单到用户Cache
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
     * 设置当前角色的没有权限的菜单到用户Cache
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
     * 设置有API权限的菜单到cache
     * 
     * @Title: setApiPermissionList
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
     * @Title: getApiPermissionList
     * @return
     * 
     */
    public static String getApiPermissionList() {
        return UserUtilContext.getApiPermissionList();
    }

    private static Long[] convertRoleToArray(String roleId) {
        if (StringUtils.isNotBlank(roleId)) {
            String[] tempRoleIds = roleId.split(",");
            Long[] roleIds = new Long[tempRoleIds.length];
            for (int i = 0; i < tempRoleIds.length; i++) {
                roleIds[i] = Long.valueOf(tempRoleIds[i]);
            }
            return roleIds;
        }
        return null;
    }

    /**
     * 设置线程局部租户id，没有默认的所属系统,默认用户id为{@link CommonConstants.SYSTEM_USER_ID}
     * 
     * @Title: setThreadTenant
     * @param tenantId
     *            租户id
     *
     */
    public static void setThreadTenant(Integer tenantId) {
        UserUtilContext.setThreadTenant(tenantId, null);
    }

    /**
     * 设置线程局部登录用户信息
     * 
     * @Title: setThreadLoginUser
     * @param user
     *
     */
    public static void setThreadLoginUser(LoginUser user) {
        UserUtilContext.setThreadLoginUser(user);
    }

    /**
     * 设置线程局部用户数据，默认用户id为{@link CommonConstants.SYSTEM_USER_ID}
     * 
     * @Title: setThreadTenant
     * @param tenantId
     *            租户id
     * @param sysOwner
     *            所属系统
     *
     */
    public static void setThreadTenant(Integer tenantId, String sysOwner) {
        UserUtilContext.setThreadTenant(tenantId, sysOwner);
    }

    /**
     * 获取当前登录用户的所属系统
     * 
     * @Title: getSysOwner
     * @return
     *
     */
    public static String getSysOwner() {
        LoginUser user = UserUtilContext.getLoginUser();
        return user == null ? null : user.getSysOwner();
    }

    /**
     * 获取多个租户字符串
     * 
     * @Title: getMultiTenant
     * @return (以,分隔的多个租户id)
     *
     */
    public static String getMultiTenant() {
        LoginUser user = UserUtilContext.getLoginUser();
        if (user == null) {
            return null;
        }
        return StringUtil.stripToNull(user.getMultiTenant());
    }

    /**
     * 获取当前所属集团下所有租户字符串
     * 
     * @Title: getGroupTenant
     * @return (以,分隔的多个租户id)
     *
     */
    public static String getGroupTenant() {
        LoginUser user = UserUtilContext.getLoginUser();
        if (user == null) {
            return null;
        }
        return StringUtil.stripToNull(user.getGroupTenant());
    }

    /**
     * 获取多个租户id集合
     * 
     * @Title: listTenantIds
     * @return
     *
     */
    public static List<Integer> listTenantIds() {
        LoginUser user = UserUtilContext.getLoginUser();
        if (user == null || StringUtil.isBlank(user.getMultiTenant())) {
            return null;
        }
        String[] tenantIds = user.getMultiTenant().split(",");
        List<Integer> tenants = new ArrayList<>(tenantIds.length);
        for (String tenantStr : tenantIds) {
            tenants.add(Integer.valueOf(tenantStr));
        }
        return tenants;
    }

    /**
     * 获取多个所属系统
     * 
     * @Title: listSysOwners
     * @return
     *
     */
    public static List<String> listSysOwners() {
        LoginUser user = UserUtilContext.getLoginUser();
        if (user == null || StringUtil.isBlank(user.getMultiSysOwner())) {
            return null;
        }
        String[] owners = user.getMultiSysOwner().split(",");
        return Arrays.asList(owners);
    }
}
