package com.xtt.common.conf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.conf.vo.RoleListVO;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysObj;
import com.xtt.common.dao.model.SysRole;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/system")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICommonCacheService commonCacheService;

    @RequestMapping("searchRole")
    public ModelAndView searchRole() throws Exception {
        String sysOwner = UserUtil.getSysOwner();
        ModelAndView model = new ModelAndView("cm/conf/role_list");
        model.addObject("sysOwner", sysOwner);
        return model;
    }

    @RequestMapping("apiRoleList")
    public ModelAndView apiRoleList() throws Exception {
        String sysOwner = UserUtil.getSysOwner();
        ModelAndView model = new ModelAndView("cm/conf/api_role_list");
        String[] types = { "api" };
        model.addObject("sysOwner", sysOwner);
        model.addObject("menu_list", roleService.getAllMenuList(types, sysOwner));
        return model;
    }

    @RequestMapping("roleList")
    @ResponseBody
    public List<SysRole> RoleList(String sysOwner) {
        return roleService.getRoleListByTenantId(UserUtil.getTenantId(), sysOwner);
    }

    @RequestMapping("menuList")
    @ResponseBody
    public List<SysObj> menuList(Long roleId, String type, String sysOwner) {
        String[] types;
        if (StringUtils.isEmpty(type)) {
            types = new String[2];
            types[0] = "1";
            types[1] = "2";
        } else {
            types = new String[1];
            types[0] = type;
        }
        if (roleId == null) {
            return roleService.getAllMenuList(types, sysOwner);
        } else {
            Long[] role = new Long[1];
            if (roleId != null) {
                role[0] = roleId;
            }
            return roleService.getMenuListByRoleId(role, types, sysOwner);
        }
    }

    @RequestMapping("saveMenu")
    @ResponseBody
    public Map<String, Object> saveMenu(@RequestBody RoleListVO roleList) {
        String[] types = { "1", "2" };
        roleService.saveMenuList(roleList.getCheckedMenuIds(), roleList.getMenuRoleId(), types);
        commonCacheService.cachePermission(UserUtil.getTenantId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("saveRole")
    @ResponseBody
    public Map<String, Object> saveRole(@RequestBody RoleListVO roleList) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", roleService.saveRoleList(roleList.getDelRoleIds(), roleList.getRoles(), roleList.getSysOwner()));
        commonCacheService.cachePermission(UserUtil.getTenantId());// 刷新缓存
        return map;
    }

    @RequestMapping("delRole")
    @ResponseBody
    public Map<String, Object> delRole(@RequestParam(value = "roleId", required = true) Long roleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", roleService.delRoleById(roleId));
        commonCacheService.cachePermission(UserUtil.getTenantId());// 刷新缓存
        return map;
    }

    @RequestMapping("addMenu")
    @ResponseBody
    public Map<String, Object> addMenu(SysObj obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", roleService.addMenu(obj));
        commonCacheService.cachePermission(UserUtil.getTenantId());// 刷新缓存
        return map;
    }

    @RequestMapping("delMenu")
    @ResponseBody
    public Map<String, Object> delMenu(Long menuId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long[] menuIds = { menuId };
        roleService.delMenu(menuIds);
        commonCacheService.cachePermission(UserUtil.getTenantId());// 刷新缓存
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("saveApiMenu")
    @ResponseBody
    public Map<String, Object> saveApiMenu(@RequestBody RoleListVO roleList) {
        String[] types = { "api" };
        roleService.saveMenuList(roleList.getCheckedMenuIds(), roleList.getMenuRoleId(), types);
        commonCacheService.cachePermission(UserUtil.getTenantId());// 刷新缓存
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 刷新menu缓存
     * 
     * @Title: refreshMenuCache
     * @return
     *
     */
    @RequestMapping("refreshMenuCache")
    @ResponseBody
    public Map<String, Object> refreshMenuCache() {
        commonCacheService.cachePermission(UserUtil.getTenantId());// 刷新缓存
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }
}
