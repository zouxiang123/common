package com.xtt.common.home.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CmDictConstants;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.dto.LoginUser;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.CmDictUtil;
import com.xtt.common.util.ContextAuthUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.security.MD5Util;

@Controller
@RequestMapping
public class LoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private IUserService userService;
	@Autowired
	ICommonService commonService;

	/**
	 * 登陆动作
	 * 
	 * @Title: login
	 * @param account
	 * @param password
	 * @param tenantId
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("login")
	public ModelAndView login(HttpServletResponse response, String account, String password, Integer tenantId, String redirectUrl,
					Boolean isloginSubmit) throws Exception {
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
		Map<String, Object> map = loginSubmit(account, password, tenantId);
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
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("loginSubmit")
	@ResponseBody
	public Map<String, Object> loginSubmit(@RequestParam(value = "account", required = false) String account,
					@RequestParam(value = "password", required = false) String password,
					@RequestParam(value = "tenantId", required = false) Integer tenantId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotBlank(account) && StringUtil.isNotBlank(password)) {
			/** 验证是否输入正确 */
			SysUserPO sysUser = userService.login(account.trim(), MD5Util.md5(password), tenantId, null);
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
				loginUser.setSysOwner(sysUser.getSysOwner());
				UserUtil.setLoginUser(token, loginUser);
				UserUtil.setNonPermissionList(sysUser.getRoleId());// 设置没有权限的菜单列表
				UserUtil.setPermission(sysUser.getRoleId());// 设置有权限的菜单列表
				// 设置用户职称
				if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_ADMIN) > -1) {
					loginUser.setRoleType(CommonConstants.ROLE_ADMIN);
					loginUser.setPositionShow(sysUser.getPosition());
				} else if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_DOCTOR) > -1) {
					loginUser.setRoleType(CommonConstants.ROLE_DOCTOR);
					loginUser.setPositionShow(CmDictUtil.getName(CmDictConstants.DOCTOR_PROFESSIONAL_TITLE, loginUser.getPosition()));
				} else if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_NURSE) > -1) {
					loginUser.setRoleType(CommonConstants.ROLE_NURSE);
					loginUser.setPositionShow(CmDictUtil.getName(CmDictConstants.NURSE_PROFESSIONAL_TITLE, loginUser.getPosition()));
				} else if (sysUser.getParentRoleId().indexOf(CommonConstants.ROLE_OTHER) > -1) {
					loginUser.setRoleType(CommonConstants.ROLE_OTHER);
					loginUser.setPositionShow(sysUser.getPosition());
				}
				// refresh redis cache
				UserUtil.setLoginUser(loginUser);
				// commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2, "登陆成功");
				map.put("status", CommonConstants.SUCCESS);
				map.put(CommonConstants.COOKIE_TOKEN, token);
			} else {
				map.put("account", account);
				map.put("password", password);
				map.put(CommonConstants.API_ERROR_MESSAGE, "用户名或密码错误！");
				map.put(CommonConstants.STATUS, CommonConstants.FAILURE);
			}
		} else {
			map.put(CommonConstants.API_ERROR_MESSAGE, "用户名或密码不能为空！");
			map.put(CommonConstants.API_STATUS, CommonConstants.FAILURE);
		}

		return map;
	}

	/**
	 * 登出
	 */
	@RequestMapping("logout")
	public ModelAndView logout(String redirectUrl) {
		if (UserUtil.getLoginUser() != null) {// 用户没有登录时，logout不用插入日志
			commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2, "登出成功");
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
