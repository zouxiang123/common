/**   
 * @Title: UserUtilFactory.java 
 * @Package com.xtt.txgl.common.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月16日 上午9:19:14 
 *
 */
package com.xtt.common.permission;

import com.google.gson.JsonArray;
import com.xtt.common.dto.LoginUser;

public interface UserUtilFactory {

    /**
     * 
     * @Title: setLoginUser 登录时存放当前登陆者对象
     * @param loginUser
     * @param token
     *            令牌
     * 
     */
    public void setLoginUser(String token, LoginUser loginUser);

    /**
     * 
     * @Title: setLoginUser 登录时存放当前登陆者对象
     * @param loginUser
     * 
     */
    public void setLoginUser(LoginUser loginUser);

    /**
     * 获取当前登录者对象
     * 
     * @Title: getLoginUser
     * @return
     * 
     */
    public LoginUser getLoginUser();

    /**
     * 获取当前登陆者id
     * 
     * @Title: getLoginUserId
     * 
     */
    public Long getLoginUserId();

    /**
     * 获取当前登录者名称
     * 
     * @Title: getLoginUserName
     * 
     */
    public String getLoginUserName();

    /**
     * 登录时设置权限集合
     * 
     * @Title: setPermission
     * @param permission
     *
     */
    public void setPermission(JsonArray permission);

    /**
     * 登录时设置当前角色没有权限的菜单
     * 
     * @Title: setPermission
     * @param permission
     *
     */
    public void setNonPermissionList(JsonArray array);

    /**
     * 登录时设置api接口权限
     * 
     * @Title: setPermission
     * @param permission
     *
     */
    public void setApiPermissionList(JsonArray array);

    /**
     * 获取没有权限的菜单数据
     * 
     * @Title: getNonPermissionList
     * @return
     *
     */
    public JsonArray getNonPermissionList();

    /**
     * 获取有API权限的菜单
     * 
     * @Title: getNonPermissionList
     * @return
     *
     */
    public JsonArray getApiPermissionList();

    public void setThreadLoginUser(LoginUser user);
}
