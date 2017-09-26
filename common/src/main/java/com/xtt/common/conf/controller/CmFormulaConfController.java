/**   
 * @Title: CmFormulaConfController.java 
 * @Package com.xtt.common.conf.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年12月10日 上午9:58:05 
 *
 */
package com.xtt.common.conf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.conf.service.ICmFormulaConfService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.CmFormulaConfPO;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/cmFormulaConf/")
public class CmFormulaConfController {
    @Autowired
    private ICmFormulaConfService cmFormulaConfService;
    @Autowired
    private ICommonCacheService commonCacheService;

    @RequestMapping("view")
    public String view(Model model, String sys) {
        CmFormulaConfPO record = new CmFormulaConfPO();
        record.setSysOwners(new String[] { sys, CommonConstants.SYS_CM });
        List<CmFormulaConfPO> list = cmFormulaConfService.selectByCondition(record);
        // 转换成类别map
        Map<String, CmFormulaConfPO> items = new LinkedHashMap<String, CmFormulaConfPO>();
        CmFormulaConfPO category;
        for (CmFormulaConfPO formula : list) {
            category = items.get(formula.getCategory());
            if (category == null) {
                category = new CmFormulaConfPO();
                category.setCategory(formula.getCategory());
                category.setCategoryName(formula.getCategoryName());
                category.setChildren(new ArrayList<>());
                items.put(formula.getCategory(), category);
            }
            category.getChildren().add(formula);
        }
        model.addAttribute("items", items);
        return "system/formula_conf";
    }

    /**
     * 保存配置数据
     */
    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> save(@RequestBody List<CmFormulaConfPO> list) {
        Map<String, Object> map = new HashMap<>();
        cmFormulaConfService.updateByPrimaryKeySelective(list);
        commonCacheService.cacheFormula(UserUtil.getTenantId());
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }
}
