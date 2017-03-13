/**   
 * @Title: PdItemsController.java 
 * @Package com.xtt.pd.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月29日 上午8:59:23 
 *
 */
package com.xtt.common.dynamicForm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.CmFormBaseItemsPO;
import com.xtt.common.form.service.ICmFormBaseItemsService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/dynamicForm/formBaseItems/")
public class CmFormBaseItemsController {
    @Autowired
    private ICmFormBaseItemsService cmFormBaseItemsService;
    @Autowired
    private ICommonCacheService commonCacheService;

    @RequestMapping("view")
    public ModelAndView view(String sys) {
        ModelAndView model = new ModelAndView("/dynamicForm/form_base_items_conf");
        model.addObject(CmDictConsts.FORM_ELEMENT_TYPE, DictUtil.getListByType(CmDictConsts.FORM_ELEMENT_TYPE));
        model.addObject(CmDictConsts.FORM_ITEM_DATA_TYPE, DictUtil.getListByType(CmDictConsts.FORM_ITEM_DATA_TYPE));
        model.addObject(CmDictConsts.FORM_ITEM_UNIT, DictUtil.getListByType(CmDictConsts.FORM_ITEM_UNIT));
        model.addObject(CmDictConsts.SYS_OWNER, DictUtil.getListByType(CmDictConsts.SYS_OWNER, sys));
        model.addObject("sysOwner", sys);
        return model;
    }

    @RequestMapping("getItems")
    @ResponseBody
    public Map<String, Object> getItems(CmFormBaseItemsPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", cmFormBaseItemsService.selectByCondition(record));
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    @RequestMapping("saveItem")
    @ResponseBody
    public Map<String, Object> saveItem(CmFormBaseItemsPO record) {
        Map<String, Object> map = new HashMap<String, Object>();
        String status = cmFormBaseItemsService.saveItem(record);
        if (CommonConstants.SUCCESS.equals(status))
            commonCacheService.cacheDynamicFormNode(UserUtil.getTenantId(), record.getSysOwner());
        map.put(CommonConstants.STATUS, status);
        map.put("item", record);
        return map;
    }

    @RequestMapping("delItem")
    @ResponseBody
    public Map<String, Object> delItem(String itemCode, String sysOwner) {
        Map<String, Object> map = new HashMap<String, Object>();
        String status = cmFormBaseItemsService.deleteByItemCode(itemCode, sysOwner);
        if (CommonConstants.SUCCESS.equals(status))
            commonCacheService.cacheDynamicFormNode(UserUtil.getTenantId(), sysOwner);
        map.put(CommonConstants.STATUS, status);
        return map;
    }

}
