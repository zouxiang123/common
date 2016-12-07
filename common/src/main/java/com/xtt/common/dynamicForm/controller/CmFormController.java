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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.CmFormPO;
import com.xtt.common.form.service.ICmFormService;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/dynamicForm/form/")
public class CmFormController {
	@Autowired
	private ICmFormService cmFormService;
	@Autowired
	private ICommonCacheService commonCacheService;

	@RequestMapping("preview")
	public String getList(Model model, Long id) {
		model.addAttribute("id", id);
		return "dynamicForm/form_preview";
	}

	@RequestMapping("getList")
	@ResponseBody
	public Map<String, Object> getList(CmFormPO record) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CmFormPO> list = cmFormService.selectByCategory(record.getCategory(), record.getSysOwner(), true);
		map.put("items", list);
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	@RequestMapping("delById")
	@ResponseBody
	public Map<String, Object> delById(Long id, String sysOwner) {
		Map<String, Object> map = new HashMap<String, Object>();
		String status = cmFormService.delById(id);
		if (CommonConstants.SUCCESS.equals(status)) {
			commonCacheService.cacheDynamicFormNode(UserUtil.getTenantId(), sysOwner);
		}
		map.put(CommonConstants.STATUS, status);
		return map;
	}
}
