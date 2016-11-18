/**   
 * @Title: FollowUpController.java 
 * @Package com.xtt.pd.system.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 上午9:24:20 
 *
 */
package com.xtt.common.conf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.conf.service.IDiagnosisConfService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.CmDiagnosisConfPO;
import com.xtt.common.dao.po.CmDictDiagnosisPO;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;

@Controller
@RequestMapping("/system/diagnosisConf/")
public class DiagnosisConfController {
	@Autowired
	private IDiagnosisConfService diagnosisConfSerivce;
	@Autowired
	private IDictDiagnosisService dictDiagnosisService;

	/**
	 * 跳转到诊断配置页面
	 * 
	 * @Title: view
	 * @return
	 *
	 */
	@RequestMapping("view")
	public ModelAndView view() {
		ModelAndView model = new ModelAndView("system/diagnosis_conf");
		return model;
	}

	@RequestMapping("getBasicItems")
	@ResponseBody
	public Map<String, Object> getBasicItems() {
		Map<String, Object> map = new HashMap<String, Object>();
		CmDictDiagnosisPO record = new CmDictDiagnosisPO();
		record.setIsEnable(true);
		map.put("items", dictDiagnosisService.selectByCondition(record));
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	/**
	 * 加载基础树数据
	 * 
	 * @Title: getItems
	 * @return
	 *
	 */
	@RequestMapping("getConfItems")
	@ResponseBody
	public Map<String, Object> getConfItems() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", diagnosisConfSerivce.selectAll());
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	@RequestMapping("delConf")
	@ResponseBody
	public Map<String, Object> delConf(@RequestBody CmDiagnosisConfPO[] records) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = diagnosisConfSerivce.delConf(records);
		if (resultMap.isEmpty()) {
			map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		} else {
			map.putAll(resultMap);
			map.put(CommonConstants.STATUS, CommonConstants.WARNING);
		}
		return map;
	}

	/**
	 * 保存配置
	 * 
	 * @Title: saveConf
	 * @param records
	 * @return
	 *
	 */
	@RequestMapping("saveConf")
	@ResponseBody
	public Map<String, Object> saveConf(@RequestBody CmDiagnosisConfPO[] records) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = diagnosisConfSerivce.saveConf(records);
		if (resultMap.isEmpty()) {
			map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		} else {
			map.putAll(resultMap);
			map.put(CommonConstants.STATUS, CommonConstants.WARNING);
		}
		return map;
	}

}
