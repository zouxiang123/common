package com.xtt.common.home.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.ContextAuthUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.security.MD5Util;

@Controller
@RequestMapping
public class CmLoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmLoginController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    ICommonService commonService;
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 登陆动作接口
     * 
     * @Title: login
     * @param account
     * @param password
     * @param tenantId
     * @param sysOwner
     * @throws Exception
     * 
     */
    @RequestMapping("cmLoginSubmit")
    @ResponseBody
    public HttpResult cmLoginSubmit(@RequestParam(value = "account", required = false) String account,
                    @RequestParam(value = "password", required = false) String password,
                    @RequestParam(value = "tenantId", required = false) Integer tenantId, String sysOwner) throws Exception {
        LOGGER.info("request to login [account:{},tenantId:{},sysOwner:{}]", account, tenantId, sysOwner);
        HttpResult result = HttpResult.getSuccessInstance();
        if (StringUtil.isNotBlank(account) && StringUtil.isNotBlank(password)) {
            /** 验证是否输入正确 */
            SysUserPO sysUser = userService.login(account.trim(), MD5Util.md5(password), tenantId, sysOwner);
            if (sysUser != null) {
                String token = UUID.randomUUID().toString();
                HttpServletUtil.getRequest().setAttribute(CommonConstants.COOKIE_TOKEN, token);
                LoginUser loginUser = new LoginUser();
                loginUser.setId(sysUser.getId());
                loginUser.setAccount(account);
                loginUser.setName(sysUser.getName());
                loginUser.setTenantId(sysUser.getFkTenantId());
                loginUser.setImagePath(sysUser.getImagePath());
                loginUser.setRoleId(sysUser.getRoleId());
                loginUser.setPosition(sysUser.getPosition());
                loginUser.setSysOwner(sysOwner);
                UserUtil.setLoginUser(token, loginUser);
                UserUtil.setNonPermissionList(sysUser.getRoleId());// 设置没有权限的菜单列表
                UserUtil.setPermission(sysUser.getRoleId());// 设置有权限的菜单列表
                // 设置用户职称
                if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_ADMIN) > -1) {
                    loginUser.setRoleType(CommonConstants.ROLE_ADMIN);
                    loginUser.setPositionShow(sysUser.getPosition());
                } else if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_DOCTOR) > -1) {
                    loginUser.setRoleType(CommonConstants.ROLE_DOCTOR);
                    loginUser.setPositionShow(DictUtil.getItemName(CmDictConsts.DOCTOR_PROFESSIONAL_TITLE, loginUser.getPosition()));
                } else if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_NURSE) > -1) {
                    loginUser.setRoleType(CommonConstants.ROLE_NURSE);
                    loginUser.setPositionShow(DictUtil.getItemName(CmDictConsts.NURSE_PROFESSIONAL_TITLE, loginUser.getPosition()));
                } else if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_OTHER) > -1) {
                    loginUser.setRoleType(CommonConstants.ROLE_OTHER);
                    loginUser.setPositionShow(sysUser.getPosition());
                }
                // refresh redis cache
                UserUtil.setLoginUser(loginUser);
                sysLogService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2, "登陆成功", sysOwner);
                result.setRs(token);
            } else {
                Map<String, Object> map = new HashMap<String, Object>(4);
                map.put("account", account);
                map.put("password", password);
                map.put("tenantId", tenantId);
                map.put("sysOwner", sysOwner);
                result.setRs(map);
                result.setStatus(HttpResult.FAILURE);
                result.setErrmsg("用户名或密码错误！");
            }
        } else {
            result.setStatus(HttpResult.FAILURE);
            result.setErrmsg("用户名或密码不能为空！");
        }
        return result;
    }

    /**
     * 登出接口
     */
    @RequestMapping("cmLogout")
    @ResponseBody
    public HttpResult cmLogout(String sysOwner) {
        HttpResult result = new HttpResult();
        if (UserUtil.getLoginUser() != null) {// 用户没有登录时，logout不用插入日志
            sysLogService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2, "登出成功", sysOwner);
        }
        // clear auth
        ContextAuthUtil.delAuth();
        return result;
    }

    /**
     * 登录时输入账号就显示姓名和图片
     */
    @RequestMapping("showNameAndPictureByInput")
    @ResponseBody
    public Map<String, Object> showNameAndPictureByInput(String tenId, String account) {
        if (account != null) {
            account = account.trim();
        }
        SysUser sysUser = userService.getUserByAccount(account, null, null);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sysUser", sysUser);
        return map;
    }

}
