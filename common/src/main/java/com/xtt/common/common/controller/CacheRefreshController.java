package com.xtt.common.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/cacheRefresh/")
public class CacheRefreshController {
    @Autowired
    private ICommonCacheService commonCacheService;

    @RequestMapping("view")
    public String view(Model model, String sys) {
        return "system/cache_refresh";
    }

    @RequestMapping("sysUser")
    @ResponseBody
    public Map<String, Object> sysUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        commonCacheService.cacheUser();
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("patient")
    @ResponseBody
    public Map<String, Object> patient() {
        Map<String, Object> map = new HashMap<String, Object>();
        commonCacheService.cachePatient();
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("dictionary")
    @ResponseBody
    public Map<String, Object> dictionary() {
        Map<String, Object> map = new HashMap<String, Object>();
        commonCacheService.cacheDict(UserUtil.getTenantId());
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("sysParam")
    @ResponseBody
    public Map<String, Object> sysParam() {
        Map<String, Object> map = new HashMap<String, Object>();
        commonCacheService.cacheSysParam(UserUtil.getTenantId());
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("permission")
    @ResponseBody
    public Map<String, Object> permission() {
        Map<String, Object> map = new HashMap<String, Object>();
        commonCacheService.cachePermission(UserUtil.getTenantId(), null);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("formula")
    @ResponseBody
    public Map<String, Object> formula() {
        Map<String, Object> map = new HashMap<String, Object>();
        commonCacheService.cacheFormula(UserUtil.getTenantId());
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }
}
