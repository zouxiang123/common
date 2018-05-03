/**   
 * @Title: AssayFilterRuleController.java 
 * @Package com.xtt.txgl.system.controller
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年9月6日 下午2:58:24 
 *
 */
package com.xtt.common.assay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.assay.service.IAssayFilterRuleService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/assay/assayFilterRule")
public class AssayFilterRuleController {

    @Autowired
    private IAssayFilterRuleService assayFilterRuleService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("cm/assay/assay_filter_rule");
        AssayFilterRule assayFilterRule = assayFilterRuleService.getByTenantId(UserUtil.getTenantId());
        model.addObject("assayFilterRule", assayFilterRule);
        model.addObject("status", CommonConstants.SUCCESS);
        return model;
    }

    @RequestMapping("updateAssayFilterRule")
    @ResponseBody
    public Map<String, Object> updateAssayFilterRule(AssayFilterRule assayFilterRule) {
        Map<String, Object> map = new HashMap<>();
        assayFilterRuleService.updateByfkTenantId(assayFilterRule);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }
}
