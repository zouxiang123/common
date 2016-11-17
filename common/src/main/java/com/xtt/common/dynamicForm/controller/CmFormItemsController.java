/**   
 * @Title: FollowUpController.java 
 * @Package com.xtt.pd.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 上午9:24:20 
 *
 */
package com.xtt.common.dynamicForm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.constants.CmDictConstants;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.CmFormBaseItemsPO;
import com.xtt.common.dao.po.CmFormItemsPO;
import com.xtt.common.dto.FormNodesDto;
import com.xtt.common.dynamicForm.vo.CmFormItemsVO;
import com.xtt.common.form.service.ICmFormBaseItemsService;
import com.xtt.common.form.service.ICmFormItemsSerivce;
import com.xtt.common.util.CmDictUtil;
import com.xtt.common.util.DynamicFormUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/dynamicForm/formItems/")
public class CmFormItemsController {
	private final String BASE_FORM_CATEGORY = "_form_category";
	@Autowired
	private ICmFormItemsSerivce cmFormItemsSerivce;
	@Autowired
	private ICmFormBaseItemsService cmFormBaseItemsService;
	@Autowired
	private ICommonCacheService commonCacheService;

	/** 跳转到随访单配置页面 */
	@RequestMapping("view")
	public ModelAndView view(String sys) {
		ModelAndView model = new ModelAndView("dynamicForm/form_items_conf");
		model.addObject(CmDictConstants.SYS_OWNER, CmDictUtil.getListByType(CmDictConstants.SYS_OWNER, sys));
		model.addObject("sysOwner", sys);
		return model;
	}

	/** 获取表单类别 */
	@RequestMapping("getFormCategory")
	@ResponseBody
	public Map<String, Object> getFormCategory(String sysOwner) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", CmDictUtil.getListByType(sysOwner + BASE_FORM_CATEGORY));
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	@RequestMapping("getBasicItems")
	@ResponseBody
	public Map<String, Object> getBasicItems(CmFormBaseItemsPO record) {
		Map<String, Object> map = new HashMap<String, Object>();
		record.setIsEnable(true);
		map.put("items", cmFormBaseItemsService.selectByCondition(record));
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	@RequestMapping("getItems")
	@ResponseBody
	public Map<String, Object> getItems(CmFormItemsPO record) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (record.getFkFormId() == null)
			record.setIsNew(true);
		map.put("items", cmFormItemsSerivce.selectByCondition(record));
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	@RequestMapping("delConf")
	@ResponseBody
	public Map<String, Object> delConf(@RequestBody CmFormItemsPO[] records) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = cmFormItemsSerivce.deleteConf(records);
		if (resultMap.isEmpty()) {
			if (records != null && records.length > 0)
				commonCacheService.cacheDynamicFormNode(UserUtil.getTenantId(), records[0].getSysOwner());
			map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		} else {
			map.putAll(resultMap);
			map.put(CommonConstants.STATUS, CommonConstants.WARNING);
		}
		return map;
	}

	@RequestMapping("saveConf")
	@ResponseBody
	public Map<String, Object> saveConf(@RequestBody CmFormItemsVO record) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = cmFormItemsSerivce.saveConf(record.getRecords(), record.getFormName(), record.getFormId());
		if (resultMap.isEmpty()) {
			if (record.getRecords() != null && record.getRecords().length > 0)
				commonCacheService.cacheDynamicFormNode(UserUtil.getTenantId(), record.getRecords()[0].getSysOwner());
			map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		} else {
			map.putAll(resultMap);
			map.put(CommonConstants.STATUS, CommonConstants.WARNING);
		}
		return map;
	}

	@RequestMapping("refresh")
	@ResponseBody
	public Map<String, Object> refresh(String sysOwner) {
		Map<String, Object> map = new HashMap<String, Object>();
		sysOwner = StringUtil.isBlank(sysOwner) ? HttpServletUtil.getSysName() : sysOwner;
		commonCacheService.cacheDynamicFormNode(UserUtil.getTenantId(), sysOwner);
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	@RequestMapping("getDetailItems")
	@ResponseBody
	public Map<String, Object> getDetailItems(CmFormItemsPO record) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FormNodesDto> list = DynamicFormUtil.getByFromId(UserUtil.getTenantId(), record.getFkFormId());
		if (CollectionUtils.isNotEmpty(list)) {
			list = DynamicFormUtil.initTreeList(list);
		}
		map.put("items", list);
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}
}
