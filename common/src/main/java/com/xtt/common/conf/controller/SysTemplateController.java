package com.xtt.common.conf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.conf.service.ISysTemplateChildService;
import com.xtt.common.conf.service.ISysTemplateService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.SysTemplate;
import com.xtt.common.dao.po.SysTemplateChildPO;
import com.xtt.common.dao.po.SysTemplatePO;

@Controller
@RequestMapping("/system/template/")
public class SysTemplateController {

    @Autowired
    private ISysTemplateService sysTemplateService;
    @Autowired
    private ISysTemplateChildService sysTemplateChildService;

    /** 系统模板值定义页面 */
    @RequestMapping("view")
    public ModelAndView definition(String sys) {
        ModelAndView model = new ModelAndView("system/template_maintain");
        List<SysTemplate> list = sysTemplateService.selectTemplateType(sys, null);
        model.addObject("list", list);
        model.addObject("sysOwner", sys);
        return model;
    }

    /** 系统模板值定义页面 */
    @RequestMapping("selectByType")
    @ResponseBody
    public Map<String, Object> selectByType(String type, String sysOwner) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SysTemplatePO> list = sysTemplateService.selectByType(type, false, sysOwner);
        map.put("list", list);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /** 保存或者更新系统模板 */
    @RequestMapping("saveTemplate")
    @ResponseBody
    public Map<String, Object> saveTemplate(SysTemplatePO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        sysTemplateService.saveTemplate(record);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /** 保存或者更新系统模板 */
    @RequestMapping("deleteTemplate")
    @ResponseBody
    public Map<String, Object> deleteTemplate(SysTemplate record) {
        Map<String, Object> map = new HashMap<String, Object>();
        sysTemplateService.deleteTemplate(record);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /** 系统模板值定义页面 */
    @RequestMapping("selectSysTemplateChildByCondition")
    @ResponseBody
    public Map<String, Object> selectSysTemplateChildByCondition(SysTemplateChildPO child) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", sysTemplateChildService.selectByCondition(child));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

}
