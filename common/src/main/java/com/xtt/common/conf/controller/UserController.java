package com.xtt.common.conf.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.ContextAuthUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.security.MD5Util;

@Controller
@RequestMapping("/system/")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("searchUser")
    public ModelAndView searchUser() {
        String sysOwner = UserUtil.getSysOwner();
        ModelAndView model = new ModelAndView("cm/conf/user_list");
        model.addObject("list", userService.selectByTenantId(UserUtil.getTenantId(), null));
        model.addObject("roleList", roleService.getRoleListByTenantId(UserUtil.getTenantId(), sysOwner));
        model.addObject(CmDictConsts.SEX, DictUtil.listByPItemCode(CmDictConsts.SEX));
        model.addObject(CmDictConsts.SYS_OWNER, DictUtil.listByPItemCode(CmDictConsts.SYS_OWNER, sysOwner));
        model.addObject(CmDictConsts.DOCTOR_PROFESSIONAL_TITLE, DictUtil.listByPItemCode(CmDictConsts.DOCTOR_PROFESSIONAL_TITLE));
        model.addObject(CmDictConsts.NURSE_PROFESSIONAL_TITLE, DictUtil.listByPItemCode(CmDictConsts.NURSE_PROFESSIONAL_TITLE));
        return model;
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestParam(value = "id", required = true) Long id) {
        userService.deleteUserById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("resetUser")
    @ResponseBody
    public Map<String, Object> resetUser(@RequestParam(value = "id", required = true) Long id) {
        userService.resetUserPassword(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("getFullUser")
    @ResponseBody
    public SysUserPO getFullUser(@RequestParam(value = "id", required = true) Long id) {
        return userService.selectById(id);
    }

    @RequestMapping("selectUserWithFilter")
    @ResponseBody
    public List<SysUserPO> selectUserWithFilter(SysUserPO user) {
        user.setFkTenantId(UserUtil.getTenantId());
        user.setSysOwner(UserUtil.getSysOwner());
        return userService.selectUserWithFilter(user);
    }

    @RequestMapping("searchUserInfo")
    public ModelAndView searchUserInfo(HttpServletRequest request, String id) throws Exception {
        ModelAndView model = new ModelAndView("cm/conf/user_info");
        return model;
    }

    @RequestMapping("accountSetting")
    public ModelAndView accountSetting() throws Exception {
        ModelAndView model = new ModelAndView("cm/conf/account_settings");
        SysUserPO user = userService.selectById(UserUtil.getLoginUserId());
        model.addObject("user", initUser(user));
        model.addObject(CmDictConsts.SEX, DictUtil.listByPItemCode(CmDictConsts.SEX, user == null ? null : user.getSex()));
        if (UserUtil.getLoginUser().getRoleType().equals(CommonConstants.ROLE_DOCTOR)) {
            model.addObject("professional_title", DictUtil.listByPItemCode(CmDictConsts.DOCTOR_PROFESSIONAL_TITLE, user.getPosition()));
        } else if (UserUtil.getLoginUser().getRoleType().equals(CommonConstants.ROLE_NURSE)) {
            model.addObject("professional_title", DictUtil.listByPItemCode(CmDictConsts.NURSE_PROFESSIONAL_TITLE, user.getPosition()));
        } else {
            model.addObject("professional_title", null);
        }
        return model;
    }

    @RequestMapping("saveUser")
    @ResponseBody
    public Map<String, Object> saveUser(SysUserPO user) throws Exception {
        long start = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isEmpty(user.getRoleId())) {
            map.put("id", user.getId());
            map.put("status", CommonConstants.WARNING);
            return map;
        } else if (user.getId() == null && userService.getUserByAccount(user.getAccount(), UserUtil.getTenantId(), null) != null) {
            map.put("id", user.getId());
            map.put("status", CommonConstants.FAILURE);
            return map;
        } else {
            map.put("id", user.getId());
            map.put("status", userService.saveUser(user));
            LOGGER.info("save user total cost {}ms", System.currentTimeMillis() - start);
            return map;
        }

    }

    @RequestMapping("updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(SysUserPO user) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        userService.updateUser(user);
        map.put("id", user.getId());
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("checkAccountExists")
    @ResponseBody
    public Map<String, Object> checkAccountExists(@RequestParam(value = "account", required = true) String account) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.getUserByAccount(account, UserUtil.getTenantId(), null) == null) {
            map.put("status", CommonConstants.SUCCESS);
            return map;
        } else {
            map.put("status", CommonConstants.WARNING);
            return map;
        }
    }

    @RequestMapping("changePassword")
    @ResponseBody
    public Map<String, Object> changePassword(String password, String newPassword) throws Exception {
        SysUserPO user = userService.selectById(UserUtil.getLoginUserId());
        Map<String, Object> map = new HashMap<String, Object>();
        if (!user.getPassword().equals(MD5Util.md5(password))) {
            map.put("status", CommonConstants.WARNING);
            return map;
        }
        SysUserPO saveUser = new SysUserPO();
        saveUser.setId(user.getId());
        saveUser.setPassword(newPassword);
        userService.updatePassword(saveUser);
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(MultipartFile image) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (image != null && image.getSize() > 0) {
            map.put("filepath", userService.uploadImage(image));
            map.put("status", CommonConstants.SUCCESS);
            return map;
        }
        map.put("status", CommonConstants.WARNING);
        return map;
    }

    @RequestMapping("userInfo")
    public ModelAndView accountView(Long userId) throws Exception {
        ModelAndView model = new ModelAndView("cm/conf/user_info");
        model.addObject("user", initUser(userService.selectById(userId)));
        return model;
    }

    @RequestMapping("searchLog")
    public ModelAndView searchLog() throws Exception {
        ModelAndView model = new ModelAndView("cm/conf/log_list");
        return model;
    }

    private SysUserPO initUser(SysUserPO user) {
        if (user != null) {
            user.setSexShow(DictUtil.getItemName(CmDictConsts.SEX, user.getSex()));
            if (user.getPosition() != null) {
                if (user.getParentRoleId().indexOf(CommonConstants.ROLE_DOCTOR) > -1) {
                    user.setPositionShow(DictUtil.getItemName(CmDictConsts.DOCTOR_PROFESSIONAL_TITLE, user.getPosition()));
                } else if (user.getParentRoleId().indexOf(CommonConstants.ROLE_NURSE) > -1) {
                    user.setPositionShow(DictUtil.getItemName(CmDictConsts.NURSE_PROFESSIONAL_TITLE, user.getPosition()));
                } else if (user.getParentRoleId().indexOf(CommonConstants.ROLE_ADMIN) > -1) {
                    user.setPositionShow(user.getPosition());
                } else if (user.getParentRoleId().indexOf(CommonConstants.ROLE_OTHER) > -1) {
                    user.setPositionShow(user.getPosition());
                }
            }
        }
        return user;
    }

    /**
     * 获取用户权限数据
     */
    @RequestMapping("getUserPermissionData")
    @ResponseBody
    public Map<String, Object> getUserPermissionData() {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> authMap = ContextAuthUtil.getAuth();
        if (MapUtils.isEmpty(authMap)) {
            map.put(CommonConstants.STATUS, CommonConstants.WARNING);
            return map;
        }
        map.put(CommonConstants.USER_NON_PERMISSION, authMap.get(CommonConstants.USER_NON_PERMISSION));
        map.put(CommonConstants.USER_PERMISSION, authMap.get(CommonConstants.USER_PERMISSION));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("saveSkin")
    @ResponseBody
    public Map<String, Object> saveSkin(String skin) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        userService.saveSkin(skin);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 获取医生列表
     * 
     * @Title: getDoctors
     * @param sysOwner
     * @return
     *
     */
    @RequestMapping("getDoctors")
    @ResponseBody
    public HttpResult getDoctors(String sysOwner) {
        HttpResult result = HttpResult.getSuccessInstance();
        result.setRs(userService.getDoctors(UserUtil.getTenantId(), sysOwner));
        return result;
    }

    /**
     * 获取护士列表
     * 
     * @Title: getNurses
     * @param sysOwner
     * @return
     *
     */
    @RequestMapping("getNurses")
    @ResponseBody
    public HttpResult getNurses() {
        HttpResult result = HttpResult.getSuccessInstance();
        result.setRs(userService.getNurses(UserUtil.getTenantId(), UserUtil.getSysOwner()));
        return result;
    }

    /**
     * 获取护士和医生列表
     * 
     * @Title: getDoctorAndNurse
     * @param sysOwner
     * @return
     *
     */
    @RequestMapping("getDoctorAndNurse")
    @ResponseBody
    public HttpResult getDoctorAndNurse() {
        HttpResult result = HttpResult.getSuccessInstance();
        Map<String, List<SysUserPO>> rs = new HashMap<>(2);
        rs.put("doctorList", userService.getDoctors(UserUtil.getTenantId(), UserUtil.getSysOwner()));
        rs.put("nurseList", userService.getNurses(UserUtil.getTenantId(), UserUtil.getSysOwner()));
        result.setRs(rs);
        return result;
    }
}
