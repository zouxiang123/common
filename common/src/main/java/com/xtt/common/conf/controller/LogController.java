package com.xtt.common.conf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.SysLogPO;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/system")
public class LogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("logList")
    public String searchLog(ModelAndView model, String sys) {
        model.addObject("sysOwner", sys);
        return "system/log_list";
    }

    /**
     * 获取系统日志
     * 
     * @Title: getCountyList
     * @param provinceId
     * @return
     * 
     */
    @RequestMapping("selectSysLog")
    @ResponseBody
    public Map<String, Object> selectSysLog(SysLogPO entity) {
        if (entity.getPageNo() == 0) {
            entity.setPageNo(1);
            entity.setPageSize(20);
        }
        entity.setIspaging(true);
        entity.setFkTenantId(UserUtil.getTenantId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("entity", sysLogService.selectSysLog(entity));
        map.put("status", CommonConstants.SUCCESS);
        return map;
    }

}
