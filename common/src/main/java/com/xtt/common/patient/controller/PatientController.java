/**   
 * @Title: PatientController.java 
 * @Package com.xtt.common.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月16日 下午2:28:18 
 *
 */
package com.xtt.common.patient.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.CmDictUtil;
import com.xtt.common.util.FileUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.common.webservice.IWsHisService;
import com.xtt.common.webservice.WsHisService;
import com.xtt.platform.util.http.HttpClientResultUtil;
import com.xtt.platform.util.http.HttpClientUtil;
import com.xtt.platform.util.io.JsonUtil;
import com.xtt.platform.util.lang.StringUtil;

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
			model.addObject("patientCardList", cardList);// 卡号信息
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

	/**
	 * 验证身份证号码是否存在
	 * 
	 * @Title: checkPatientExistByIdNumber
	 * @param patient
	 * @return
	 * 
	 */
	@RequestMapping("checkPatientExistByIdNumber")
	@ResponseBody
	public HashMap<String, Object> checkPatientExistByIdNumber(Long id, String idNumber) {
		boolean exist = patientService.checkPatientExistByIdNumber(id, idNumber);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("exist", exist);
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
		return map;
	}

	/**
	 * 保存患者信息
	 * 
	 * @Title: savePatientImage
	 * @param patient
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 * 
	 */
	@RequestMapping("savePatientImage")
	@ResponseBody
	public HashMap<String, Object> savePatientImage(MultipartFile image, String id) throws IllegalStateException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String path = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/";
		if (id == null) {
			String newFilename = "/patient/" + System.currentTimeMillis() + ".png";
			FileUtil.uploadFile(image, path + newFilename);
			BusinessCommonUtil.compressPic(path, newFilename);
			map.put("status", CommonConstants.SUCCESS);
			map.put("filepath", "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + newFilename);
		} else {
			String newFilename = "/patient/" + id + ".png";
			FileUtil.uploadFile(image, path + newFilename);
			BusinessCommonUtil.compressPic(path, newFilename);
			map.put("status", CommonConstants.SUCCESS);
			map.put("filepath", "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + newFilename);
		}
		return map;
	}

	/**
	 * 调用第三方接口获取病患基本资料
	 * 
	 * @param cardNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wsQueryPatientInfo")
	@ResponseBody
	public HashMap<String, Object> wsQueryPatientInfo(String cardNo) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		String tenantId = HttpServletUtil.getCookieValueByName("tenantId");
		String url = CmDictUtil.getName(CmDictConstants.URL, CmDictConstants.DOWN_DB_WS_URL_PT);
		String json = "";
		if ("10101".equals(tenantId)) {
			if (StringUtil.isNotEmpty(cardNo)) {
				// 1=卡号 2=身份证号
				int cardType = cardNo.length() != 18 ? 1 : 2;
				// 传入参数
				String parm = cardType + "," + cardNo;
				// 调用webService接口
				WsHisService gt = new WsHisService();
				IWsHisService service = gt.getWsHisServicePort();
				json = service.patientInfo(parm);

			}
		} else {
			Map<String, String> qmap = new HashMap<String, String>();
			qmap.put("cardNo", cardNo);
			qmap.put("fkTenantId", tenantId);

			HttpClientResultUtil httpClientResultUtil = HttpClientUtil.post(url, qmap);
			json = httpClientResultUtil.getContext();
		}

		PatientPO patient = JsonUtil.AllJsonUtil().fromJson(json, PatientPO.class);
		if (patient != null) {
			patient.setIdType("1");
		}
		map.put("patient", patient);
		map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);

		return map;
	}
}
