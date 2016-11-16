/**   
 * @Title: PatientController.java 
 * @Package com.xtt.common.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月16日 下午2:28:18 
 *
 */
package com.xtt.common.patient.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CmDictConstants;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.County;
import com.xtt.common.dao.model.Province;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.patient.service.IPatientCardService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.CmDictUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/patient/")
public class PatientController {
	@Autowired
	private IPatientService patientService;
	@Autowired
	private IPatientCardService patientCardService;
	@Autowired
	private ICommonService commonService;

	/**
	 * 患者基本信息编辑
	 * 
	 * @Title: findPatient
	 * @param patientId
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("editPatient")
	public ModelAndView editPatient(Long patientId) throws Exception {
		ModelAndView model = new ModelAndView("patient/edit_patient");
		PatientPO patient = patientService.selectById(patientId);
		model.addObject("patientId", patientId);
		model.addObject("patient", patient);
		{
			// 根据id获取该患者的相关卡号
			List<PatientCardPO> cardList = new ArrayList<PatientCardPO>();
			if (patient != null) {
				cardList = patientCardService.selectByPatientId(patient.getId());
			}
			model.addObject("ptCardList", cardList);// 卡号信息
		}
		model.addObject(CmDictConstants.MEDICARE_CARD_TYPE,
						CmDictUtil.getListByType(CmDictConstants.MEDICARE_CARD_TYPE, patient == null ? null : patient.getMedicareCardType()));

		List<Province> provinceList = commonService.getProvinceList();
		model.addObject("provinceList", provinceList);
		List<County> countyList = null;
		if (patient == null) {
			countyList = commonService.getCountyList(provinceList.get(0).getId());
		} else {
			countyList = commonService.getCountyList(patient.getProvince());
		}
		model.addObject("countyList", countyList);

		return model;
	}

	@RequestMapping("patientDetail")
	public ModelAndView patientDetail(Long patientId) throws Exception {
		ModelAndView model = new ModelAndView("patient/patient_detail");
		if (!HttpServletUtil.isFromPC() || UserUtil.isNurse()) {
			model = new ModelAndView("patient/pad/patient_detail");
		}
		model.addAllObjects(findPatientApi(patientId));
		return model;
	}

	/**
	 * 患者基本信息详情API
	 * 
	 * @Title: findPatient
	 * @param patientId
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("api/patientDetail")
	@ResponseBody
	public Map<String, Object> findPatientApi(@RequestParam(value = "patientId", required = false) Long patientId) throws Exception {
		Map<String, Object> retMap = new HashMap<String, Object>();
		PatientPO patient = patientService.selectById(patientId);
		retMap.put("patientId", patientId);
		retMap.put("patient", patient);
		retMap.put("patientCardList", patientCardService.selectByPatientId(patientId));
		// 卡号类型
		retMap.put(CmDictConstants.MEDICARE_CARD_TYPE,
						CmDictUtil.getListByType(CmDictConstants.MEDICARE_CARD_TYPE, patient == null ? null : patient.getMedicareCardType()));
		retMap.put(CommonConstants.API_STATUS, CommonConstants.SUCCESS);
		return retMap;
	}
}
