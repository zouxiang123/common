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

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.CmDictDiagnosis;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;
import com.xtt.common.util.DictUtil;

@Controller
@RequestMapping("/system/dictDiagnosis/")
public class DictDiagnosisController {
    public DictDiagnosisController() {
        // TODO Auto-generated constructor stub
    }

    @Autowired
    private IDictDiagnosisService dictDiagnosisService;

    @RequestMapping("view")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("system/dict_diagnosis_items");
        model.addObject(CmDictConsts.FORM_ELEMENT_TYPE, DictUtil.listByPItemCode(CmDictConsts.FORM_ELEMENT_TYPE));
        model.addObject(CmDictConsts.FORM_ITEM_DATA_TYPE, DictUtil.listByPItemCode(CmDictConsts.FORM_ITEM_DATA_TYPE));
        model.addObject(CmDictConsts.FORM_ITEM_UNIT, DictUtil.listByPItemCode(CmDictConsts.FORM_ITEM_UNIT));
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
