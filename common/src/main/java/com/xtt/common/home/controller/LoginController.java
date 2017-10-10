package com.xtt.common.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.common.service.ISysTenantService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.model.SysUser;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.ContextAuthUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    ICommonService commonService;
    @Autowired
    private ISysLogService sysLogService;
    @Autowired
    private ISysTenantService sysTenantService;

    /**
     * 登陆动作
     * 
     * @Title: login
     * @param response
     * @param account
     * @param password
     * @param tenantId
     *            租户id
     * @param redirectUrl
     * @param isloginSubmit
     *            点击登录标识
     * @param groupAdmin
     *            集团管理员登陆
     * @param sysOwner
     *            所属系统
     * @param isSwitch
     *            是否是系统切换
     * @return
     * @throws Exception
     *
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletResponse response, String account, String password, Integer tenantId, String redirectUrl,
                    Boolean isloginSubmit, Boolean groupAdmin, String sysOwner, Boolean isSwitch) throws Exception {
        ModelAndView model = new ModelAndView("login");
        if ("true".equals(HttpServletUtil.getCookieValueByName("savePwd")) && StringUtils.isEmpty(account) && StringUtils.isEmpty(password)) {
            account = HttpServletUtil.getCookieValueByName("account");
            password = HttpServletUtil.getCookieValueByName("password");
            String tenantIdStr = HttpServletUtil.getCookieValueByName("tenantId");
            if (StringUtils.isNotBlank(tenantIdStr)) {
                tenantId = Integer.parseInt(tenantIdStr);
            }
        }
        // 如果不是点击登录按钮,而且账号或者密码不存在，跳转到登录页面
        if (isloginSubmit == null && (StringUtils.isEmpty(account) || StringUtils.isEmpty(password))) {
            model.addObject("redirectUrl", redirectUrl);
            return model;
        }
        LOGGER.info("login message :account={},password={},tenantId={},redirectUrl={}", account, password, tenantId, redirectUrl);
        Map<String, Object> map = loginSubmit(account, password, tenantId, groupAdmin, sysOwner, isSwitch);
        if (CommonConstants.SUCCESS.equals(map.get("status"))) {
            LOGGER.info("account={} login success,normal submit", account);
            HttpServletUtil.addCookie(response, CommonConstants.COOKIE_TOKEN, map.get(CommonConstants.COOKIE_TOKEN).toString(), -1);
            HttpServletUtil.addCookie(response, "cacheFlag", "0", -1);// 设置权限缓存状态为未缓存
            if (StringUtils.isNotEmpty(redirectUrl)) {
                model.setViewName("redirect:" + redirectUrl);
                return model;
            }
            model.setViewName("redirect:/index.shtml");
        } else {
            model.addAllObjects(map);
        }
        model.addObject("redirectUrl", redirectUrl);
        return model;
    }

    /**
     * 登陆动作接口
     *
     * @Title: login
     * @param account
     * @param password
     * @param tenantId
     * @param groupAdmin
     *            是否是集团管理员登陆
     * @param sysOwner
     *            所属系统
     * @param isSwitch
     *            是否是系统切换
     * @return
     * @throws Exception
     *
     */
    @RequestMapping("loginSubmit")
    @ResponseBody
    public Map<String, Object> loginSubmit(final String account, final String password, final Integer tenantId, final Boolean groupAdmin,
                    final String sysOwner, Boolean isSwitch) throws Exception {
        Map<String, Object> loginResult = userService.loginSubmit(account, password, tenantId, groupAdmin, sysOwner, isSwitch);
        return loginResult;
    }

    /**
     * 登出
     */
    @RequestMapping("logout")
    public ModelAndView logout(String redirectUrl) {
        if (UserUtil.getLoginUser() != null) {// 用户没有登录时，logout不用插入日志
            sysLogService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2, "登出成功", CommonConstants.SYS_HD);
        }
        ModelAndView model = new ModelAndView("login");
        // clear auth
        ContextAuthUtil.delAuth();

        if ("true".equals(HttpServletUtil.getCookieValueByName("savePwd"))) {
            String account = HttpServletUtil.getCookieValueByName("account");
            String password = HttpServletUtil.getCookieValueByName("password");
            String tenantId = HttpServletUtil.getCookieValueByName("tenantId");
            model.addObject("account", account);
            model.addObject("password", password);
            model.addObject("tenantId", tenantId);
        }
        model.addObject("redirectUrl", redirectUrl);
        return model;
    }

    /**
     * 登录时输入账号就显示姓名和图片<br>
     *
     * @param tenantId
     * @param account
     * @param groupAdmin
     *            是否是集团管理员标识
     * @param onlyUser
     *            是否只需要用户信息
     * @return 该账户关联的所有租户(如果租户id不为空或者该账户只存在一个租户时，返回对应的用户)<br>
     *         集团用户登录不需要查询租户
     */
    @RequestMapping("showNameAndPictureByInput")
    @ResponseBody
    public Map<String, Object> showNameAndPictureByInput(Integer tenantId, String account, Boolean groupAdmin, boolean onlyUser) {
        Map<String, Object> map = new HashMap<>();
        account = StringUtil.trim(account);
        groupAdmin = groupAdmin == null ? false : groupAdmin;
        if (!onlyUser && !groupAdmin) {
            // 查询该账号对应的所有租户
            List<SysTenant> list = sysTenantService.listByAccount(account, CommonConstants.SYS_HD);
            if (CollectionUtils.isNotEmpty(list)) {
                map.put("tenants", list);
                // 账户对应多个租户，而且没有选中租户，返回租户列表
                if (list.size() > 1 && tenantId == null) {
                    return map;
                }
                if (list.size() == 1) {
                    tenantId = list.get(0).getId();
                }
            }
        }
        // 返回对应的用户
        SysUser sysUser;
        if (!groupAdmin) {
            sysUser = userService.getUserByAccount(account, tenantId, CommonConstants.SYS_HD, groupAdmin);
        } else {
            sysUser = userService.getGroupAdminByAccount(account);
        }
        if (sysUser != null) {
            // 如果是组管理员登陆，租户id取建立该用户的租户
            if (groupAdmin && tenantId == null) {
                tenantId = sysUser.getFkTenantId();
            }
            map.put("imagePath", sysUser.getImagePath());
            map.put("name", sysUser.getName());
        }
        map.put("tenantId", tenantId);
        return map;
    }

}
