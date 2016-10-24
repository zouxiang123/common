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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.common.constants.CommonConstants;
import com.xtt.common.common.util.DynamicFormUtil;
import com.xtt.common.common.util.HttpServletUtil;
import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.po.CmFormPO;
import com.xtt.common.form.service.ICmFormService;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/dynamicForm/form/")
public class CmFormController {
	@Autowired
	private ICmFormService cmFormService;

	@RequestMapping("getList")
	@ResponseBody
	public Map<String, Object> getList(CmFormPO record) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<CmFormPO> list = cmFormService.selectByCategory(record.getCategory(), record.getSysOwner());
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
			sysOwner = StringUtil.isBlank(sysOwner) ? HttpServletUtil.getProjectName() : sysOwner;
			DynamicFormUtil.refresh(UserUtil.getTenantId(), sysOwner);
		}
		map.put(CommonConstants.STATUS, status);
		return map;
	}
}
