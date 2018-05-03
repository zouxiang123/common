/**   
 * @Title: PdItemsController.java 
 * @Package com.xtt.pd.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月29日 上午8:59:23 
 *
 */
package com.xtt.common.conf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.CmDictDiagnosis;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;

@Controller
@RequestMapping("/system/dictDiagnosis/")
public class DictDiagnosisController {

    @Autowired
    private IDictDiagnosisService dictDiagnosisService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("cm/conf/dict_diagnosis_items");
        return model;
    }

    @RequestMapping("getItems")
    @ResponseBody
    public Map<String, Object> getItems() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", dictDiagnosisService.selectAll());
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("getTreeItems")
    @ResponseBody
    public Map<String, Object> getTreeItems(String pItemCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", dictDiagnosisService.selectTreeList(pItemCode));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("saveItem")
    @ResponseBody
    public Map<String, Object> saveItem(CmDictDiagnosis record) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CommonConstants.STATUS, dictDiagnosisService.saveItem(record));
        map.put("item", record);
        return map;
    }

    @RequestMapping("delItem")
    @ResponseBody
    public Map<String, Object> delItem(String itemCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CommonConstants.STATUS, dictDiagnosisService.deleteByItemCode(itemCode));
        return map;
    }
}