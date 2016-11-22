/**   
 * @Title: DiagnosisController.java 
 * @Package com.xtt.common.diagnosis.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月18日 上午10:11:46 
 *
 */
package com.xtt.common.diagnosis.controller;

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
import com.xtt.common.dao.model.CkdStage;
import com.xtt.common.dao.model.County;
import com.xtt.common.dao.model.CureSymptomAndCondition;
import com.xtt.common.dao.model.OtherDiagnosisResult;
import com.xtt.common.dao.model.PathologicDiagnosisResult;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientDiagnosis;
import com.xtt.common.dao.model.Province;
import com.xtt.common.dao.po.ArfPO;
import com.xtt.common.dao.po.CkdStagePO;
import com.xtt.common.dao.po.ClinicalDiagnosisResultPO;
import com.xtt.common.dao.po.CrfPO;
import com.xtt.common.dao.po.CureSymptomAndConditionPO;
import com.xtt.common.dao.po.MedicalHistoryPO;
import com.xtt.common.dao.po.MedicalHistoryRemarkPO;
import com.xtt.common.dao.po.OtherDiagnosisResultPO;
import com.xtt.common.dao.po.PathologicDiagnosisResultPO;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.SeriousCrfPO;
import com.xtt.common.diagnosis.service.IPatientDiagnosisService;
import com.xtt.common.patient.service.IPatientCardService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.CmDictUtil;
import com.xtt.common.util.FileUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/patient/diagnosis/")
public class PatientDiagnosisController {
	@Autowired
	ICommonService commonService;
	@Autowired
	private IPatientDiagnosisService patientDiagnosisService;
	@Autowired
	private IPatientService patientService;
	@Autowired
	private IPatientCardService patientCardService;

	/**
	 * 
	 * @Title: newPatient
	 * @param fkPatientDiagnosisId
	 *            诊断ID
	 * @param patientId
	 *            患者ID
	 * @param patientName
	 *            患者姓名
	 * @param tabId
	 *            定位诊断流程界面Tab
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("newPatient")
	public ModelAndView newPatient(Long fkPatientDiagnosisId, Long patientId, String patientName, String tabId, String sys) throws Exception {

		ModelAndView model = new ModelAndView("diagnosis/new_patient");
		model.addObject("patientName", patientName);
		model.addObject("tabId", tabId);
		// 判断是否还有未结束的诊断
		Integer cnt = patientDiagnosisService.countByPatientId(patientId, UserUtil.getTenantId());
		if (cnt > 0) {
			model.addObject("operation", "secondAdd");
		}
		PatientDiagnosis pd = null;
		if (fkPatientDiagnosisId == null && patientId != null) {
			// 没有诊断，指定患者新增诊断记录
			pd = patientDiagnosisService.getPatientDiagnosisByPatientId(patientId);
			fkPatientDiagnosisId = pd == null ? null : pd.getId();
		}
		MedicalHistoryPO mh = new MedicalHistoryPO();
		ClinicalDiagnosisResultPO cd = new ClinicalDiagnosisResultPO();
		CrfPO crf = new CrfPO();
		SeriousCrfPO seriousCrf = new SeriousCrfPO();
		ArfPO arf = new ArfPO();
		PathologicDiagnosisResultPO pdr = new PathologicDiagnosisResultPO();
		CkdStagePO ckdStage = new CkdStagePO();
		CureSymptomAndConditionPO cureSymptomAndCondition = new CureSymptomAndConditionPO();

		Map<String, Object> hisMap = null;
		// 已录入过部分信息，恢复最后一次操作数据
		if (fkPatientDiagnosisId != null) {
			pd = pd == null ? patientDiagnosisService.selectById(fkPatientDiagnosisId) : pd;
			hisMap = patientDiagnosisService.selectAllDiagnosisInfo(fkPatientDiagnosisId);
			mh = (MedicalHistoryPO) hisMap.get("medicalHistory");
			cd = (ClinicalDiagnosisResultPO) hisMap.get("clinicalDiagnosisResult");
			crf = (CrfPO) hisMap.get("crf");
			seriousCrf = (SeriousCrfPO) hisMap.get("seriousCrf");
			arf = (ArfPO) hisMap.get("arf");
			ckdStage = (CkdStagePO) hisMap.get("ckdStage");
			pdr = (PathologicDiagnosisResultPO) hisMap.get("pathologicDiagnosisResult");
			cureSymptomAndCondition = (CureSymptomAndConditionPO) hisMap.get("cureSymptomAndCondition");
			// 如果患者id不存在，从诊断中获取
			patientId = patientId == null ? pd.getFkPatientId() : patientId;
			model.addAllObjects(hisMap);
		}
		// 病史信息
		initMedicalHistory(model.getModelMap(), mh);
		// 慢性肾病类型
		model.addObject(CmDictConstants.NEPHROSIS_TYPE, CmDictUtil.getListByType(CmDictConstants.NEPHROSIS_TYPE, cd == null ? null : cd.getType()));
		// 初始化慢性肾功能衰竭
		initCrf(model.getModelMap(), crf);
		// 初始化慢性肾功能不全急性加重
		initSeriousCrf(model.getModelMap(), seriousCrf);
		// 初始化急性肾功能衰竭
		initArf(model.getModelMap(), arf);
		// 病理诊断信息
		initPathologicDiagnosisResult(model.getModelMap(), pdr);
		// 初始化CKD/AKI分期数据
		initCkdStage(model.getModelMap(), ckdStage);
		// 治疗前合并症信息
		initCureSymptomAndCondition(model.getModelMap(), cureSymptomAndCondition);
		PatientPO patient = null;
		if (patientId != null) {
			patient = patientService.selectById(patientId);
			// 已经添加的患者卡片数据
			model.addObject("patientCardList", patientCardService.selectByPatientId(patientId));
		}
		model.addObject("patientId", patientId);
		model.addObject("patientDiagnosis", pd == null ? new PatientDiagnosis() : pd);
		model.addObject("fkPatientDiagnosisId", fkPatientDiagnosisId);

		List<Province> provinceList = commonService.getProvinceList();
		model.addObject("provinceList", provinceList);
		List<County> countyList = null;
		if (patient == null) {
			countyList = commonService.getCountyList(provinceList.get(0).getId());
		} else {
			countyList = commonService.getCountyList(patient.getProvince());
		}
		model.addObject("countyList", countyList);
		if (patient == null) {
			patient = new PatientPO();
			patient.setSysOwner(sys);
		}
		model.addObject("patient", patient);
		model.addObject(CmDictConstants.MEDICARE_CARD_TYPE,
						CmDictUtil.getListByType(CmDictConstants.MEDICARE_CARD_TYPE, patient == null ? null : patient.getMedicareCardType()));
		return model;
	}

	/**
	 * 首次评估预览
	 * 
	 * @Title: firstPreview
	 * @param fkPatientDiagnosisId
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("firstPreview")
	public ModelAndView firstPreview(Long fkPatientDiagnosisId, Long patientId) throws Exception {
		ModelAndView model = newPatient(fkPatientDiagnosisId, patientId, null, null, null);
		model.setViewName("diagnosis/done");
		return model;
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
		map.put("status", CommonConstants.SUCCESS);
		return map;
	}

	/**
	 * 保存患者信息
	 * 
	 * @Title: savePatient
	 * @param patient
	 * @return
	 * 
	 */
	@RequestMapping("savePatient")
	@ResponseBody
	public HashMap<String, Object> savePatient(PatientPO patient) {
		if (StringUtil.isNotBlank(patient.getTempImagePath())) {
			patient.setImagePath(patient.getTempImagePath());
		}
		Long fkPatientDiagnosisId = patientDiagnosisService.savePatient(patient, false);
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2, String.format("对患者（编号：%s 姓名：%s）基本信息进行了编辑动作", patient.getId(), patient.getName()));

		// 批量保存病患卡号信息
		List<PatientCardPO> patientCardList = patient.getPatientCardList();
		List<PatientCardPO> newPatientCardList = new ArrayList<PatientCardPO>();
		for (PatientCardPO patientCardPO : patientCardList) {
			patientCardPO.setFkPtId(patient.getId());
			newPatientCardList.add(patientCardPO);
		}
		patientCardService.savePatientCard(newPatientCardList);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", fkPatientDiagnosisId);
		map.put("fkPatientId", patient.getId());
		map.put("status", CommonConstants.SUCCESS);
		return map;
	}

	/**
	 * 保存病史询问
	 * 
	 * @Title: saveMedicalHistoryRemark
	 * @param mh
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("saveMedicalHistory")
	@ResponseBody
	public HashMap<String, Object> saveMedicalHistory(MedicalHistoryPO mh, MedicalHistoryRemarkPO mePo) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.saveMedicalHistory(mh, mePo));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", mh.getId());

		Patient patient = patientService.selectById(mh.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "病史"));
		return map;
	}

	/**
	 * 保存慢性肾功能衰竭CRF
	 * 
	 * @Title: saveCrf
	 * @param mh
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("saveCrf")
	@ResponseBody
	public HashMap<String, Object> saveCrf(CrfPO model) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.saveCrf(model));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", model.getId());
		map.put("version", model.getVersion());
		map.put("parentId", model.getFkClinicalDiagnosisResultId());
		map.put("parentVersion", model.getVersion());

		Patient patient = patientService.selectById(model.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "慢性肾功能衰竭"));
		return map;
	}

	/**
	 * 保存Serious CRF(慢性肾功能不全急性加重)
	 * 
	 * @Title: saveSeriousCrf
	 * @param mh
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("saveSeriousCrf")
	@ResponseBody
	public HashMap<String, Object> saveSeriousCrf(SeriousCrfPO model) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.saveSeriousCrf(model));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", model.getId());
		map.put("version", model.getVersion());
		map.put("parentId", model.getFkClinicalDiagnosisResultId());
		map.put("parentVersion", model.getVersion());

		Patient patient = patientService.selectById(model.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "慢性肾功能不全急性加重"));
		return map;
	}

	/**
	 * 保存ARF(急性肾功能衰竭)
	 * 
	 * @Title: saveArf
	 * @param mh
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("saveArf")
	@ResponseBody
	public HashMap<String, Object> saveArf(ArfPO model) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.saveArf(model));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", model.getId());
		map.put("version", model.getVersion());
		map.put("parentId", model.getFkClinicalDiagnosisResultId());
		map.put("parentVersion", model.getVersion());

		Patient patient = patientService.selectById(model.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "急性肾功能衰竭"));
		return map;
	}

	/**
	 * 保存病理诊断
	 * 
	 * @Title: savePathologicDiagnosis
	 * @param PathologicDiagnosisResult
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("savePathologicDiagnosis")
	@ResponseBody
	public HashMap<String, Object> savePathologicDiagnosis(PathologicDiagnosisResult model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.savePathologicDiagnosis(model));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", model.getId());
		map.put("version", model.getVersion());

		Patient patient = patientService.selectById(model.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "病理诊断"));
		return map;
	}

	/**
	 * 保存CKD/AKI
	 * 
	 * @Title: saveCkdStage
	 * @param CkdStage
	 * @return
	 * 
	 */
	@RequestMapping("saveCkdStage")
	@ResponseBody
	public HashMap<String, Object> saveCkdStage(CkdStage model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.saveCkdStage(model));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", model.getId());
		map.put("version", model.getVersion());

		Patient patient = patientService.selectById(model.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "CKD/AKI"));
		return map;
	}

	/**
	 * 保存治疗前并发症
	 * 
	 * @Title: saveCureComplication
	 * @param CureSymptomAndCondition
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("saveCureComplication")
	@ResponseBody
	public HashMap<String, Object> saveCureComplication(CureSymptomAndCondition model) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.saveCureComplication(model));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", model.getId());
		map.put("version", model.getVersion());

		Patient patient = patientService.selectById(model.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "治疗前并发症"));
		return map;
	}

	/**
	 * 保存其它诊断
	 * 
	 * @Title: saveOtherDiagnosis
	 * @param OtherDiagnosis
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("saveOtherDiagnosis")
	@ResponseBody
	public HashMap<String, Object> saveOtherDiagnosis(OtherDiagnosisResult model) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fkPatientDiagnosisId", patientDiagnosisService.saveOtherDiagnosis(model));
		map.put("status", CommonConstants.SUCCESS);
		map.put("id", model.getId());
		map.put("version", model.getVersion());

		Patient patient = patientService.selectById(model.getFkPatientId());
		commonService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
						String.format("对患者（编号：%s 姓名：%s）%s进行了编辑动作", patient.getId(), patient.getName(), "其它诊断"));
		return map;
	}

	/**
	 * 首次诊断基本信息完成
	 * 
	 * @Title: saveOtherDiagnosis
	 * @param fkDialysisCampaignId
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("saveFirstDone")
	@ResponseBody
	public String saveFirstDone(Long fkPatientDiagnosisId) throws Exception {
		patientDiagnosisService.saveFirstDone(fkPatientDiagnosisId);
		return CommonConstants.SUCCESS;
	}

	/**
	 * 患者诊断信息
	 * 
	 * @Title: diagnosisInfo
	 * @param patientId
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping("diagnosisInfo")
	public ModelAndView diagnosisInfo(@RequestParam(required = true) Long patientId) throws Exception {
		ModelAndView model = new ModelAndView("diagnosis/diagnosis_info");
		model.addAllObjects(diagnosisInfoApi(patientId));
		return model;
	}

	/**
	 * 患者诊断信息
	 * 
	 * @Title: diagnosisInfo
	 * @param patientId
	 * @return
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("api/diagnosisInfo")
	@ResponseBody
	public Map<String, Object> diagnosisInfoApi(@RequestParam(value = "patientId", required = false) Long patientId) throws Exception {

		// API返回用结果用
		Map<String, Object> retMap = new HashMap<String, Object>();

		Map<String, Object> mapAll = patientDiagnosisService.selectDignosisByPatient(patientId);
		List<MedicalHistoryPO> mhList = (List<MedicalHistoryPO>) mapAll.get("medicalHistory");
		List<CrfPO> crfList = (List<CrfPO>) mapAll.get("crf");
		List<SeriousCrfPO> seriousCrfList = (List<SeriousCrfPO>) mapAll.get("seriousCrf");
		List<ArfPO> arfList = (List<ArfPO>) mapAll.get("arf");
		List<CkdStagePO> ckdStageList = (List<CkdStagePO>) mapAll.get("ckdStage");
		List<PathologicDiagnosisResultPO> pdList = (List<PathologicDiagnosisResultPO>) mapAll.get("pathologicDiagnosisResult");
		List<CureSymptomAndConditionPO> cureSymptomAndConditionList = (List<CureSymptomAndConditionPO>) mapAll.get("cureSymptomAndCondition");
		List<OtherDiagnosisResultPO> otherDiagnosisResultList = (List<OtherDiagnosisResultPO>) mapAll.get("otherDiagnosisResult");

		// 病史信息
		List<Map<String, Object>> medicalHistorys = new ArrayList<Map<String, Object>>();
		for (MedicalHistoryPO mh : mhList) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 初始化病史信息
			initMedicalHistory(map, mh);
			map.put("medicalHistory", mh);
			medicalHistorys.add(map);
		}
		// 临床诊断信息
		List<Map<String, Object>> crfs = new ArrayList<Map<String, Object>>();
		for (CrfPO crf : crfList) {
			Map<String, Object> map = new HashMap<String, Object>();
			initCrf(map, crf);
			map.put("crf", crf);
			crfs.add(map);
		}

		List<Map<String, Object>> seriousCrfs = new ArrayList<Map<String, Object>>();
		for (SeriousCrfPO seriousCrf : seriousCrfList) {
			Map<String, Object> map = new HashMap<String, Object>();
			initSeriousCrf(map, seriousCrf);
			map.put("seriousCrf", seriousCrf);
			seriousCrfs.add(map);
		}

		List<Map<String, Object>> arfs = new ArrayList<Map<String, Object>>();
		for (ArfPO arf : arfList) {
			Map<String, Object> map = new HashMap<String, Object>();
			initArf(map, arf);
			map.put("arf", arf);
			arfs.add(map);
		}

		// 病理诊断信息
		List<Map<String, Object>> pathologicDiagnosisResults = new ArrayList<Map<String, Object>>();
		for (PathologicDiagnosisResultPO pd : pdList) {
			Map<String, Object> map = new HashMap<String, Object>();
			initPathologicDiagnosisResult(map, pd);
			map.put("pathologicDiagnosisResult", pd);
			pathologicDiagnosisResults.add(map);
		}

		// CKD/AKI信息
		List<Map<String, Object>> ckdStages = new ArrayList<Map<String, Object>>();
		for (CkdStagePO ckdStage : ckdStageList) {
			Map<String, Object> map = new HashMap<String, Object>();
			initCkdStage(map, ckdStage);
			map.put("ckdStage", ckdStage);
			ckdStages.add(map);
		}

		// 治疗前合并症信息
		List<Map<String, Object>> cureSymptomAndConditions = new ArrayList<Map<String, Object>>();
		for (CureSymptomAndConditionPO cureSymptomAndCondition : cureSymptomAndConditionList) {
			Map<String, Object> map = new HashMap<String, Object>();
			initCureSymptomAndCondition(map, cureSymptomAndCondition);
			map.put("cureSymptomAndCondition", cureSymptomAndCondition);
			cureSymptomAndConditions.add(map);
		}

		// 其它信息
		List<Map<String, Object>> otherDiagnosisResults = new ArrayList<Map<String, Object>>();
		for (OtherDiagnosisResultPO otherDiagnosisResult : otherDiagnosisResultList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("otherDiagnosisResult", otherDiagnosisResult);
			otherDiagnosisResults.add(map);
		}
		retMap.put("patientId", patientId);
		retMap.put("patientDiagnosis", mapAll.get("patientDiagnosis"));
		retMap.put("patient", patientService.selectById(patientId));
		retMap.put("medicalHistorys", medicalHistorys);
		retMap.put("crfs", crfs);
		retMap.put("seriousCrfs", seriousCrfs);
		retMap.put("arfs", arfs);
		retMap.put("pathologicDiagnosisResults", pathologicDiagnosisResults);
		retMap.put("ckdStages", ckdStages);
		retMap.put("cureSymptomAndConditions", cureSymptomAndConditions);
		retMap.put("otherDiagnosisResults", otherDiagnosisResults);

		retMap.put(CommonConstants.API_STATUS, CommonConstants.SUCCESS);
		return retMap;
	}

	/** 初始化病史信息 */
	private void initMedicalHistory(Map<String, Object> map, MedicalHistoryPO mh) {

		map.put(CmDictConstants.FIRST_DIALYSIS_METHOD,
						CmDictUtil.getListByType(CmDictConstants.FIRST_DIALYSIS_METHOD, mh == null ? null : mh.getFirstDialysisMethod()));
		map.put("hasCva", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasCva()));
		map.put("hasVascularDisease", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasVascularDisease()));
		map.put("hasSeriousDisease", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasSeriousDisease()));
		map.put("hasPsychosis", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasPsychosis()));
		map.put(CmDictConstants.HEMORRHAGE, CmDictUtil.getListByType(CmDictConstants.HEMORRHAGE, mh == null ? null : mh.getHemorrhage()));
		map.put(CmDictConstants.HEART_DEFECTS, CmDictUtil.getListByType(CmDictConstants.HEART_DEFECTS, mh == null ? null : mh.getHeartDefects()));

		// 血透开始原因
		map.put("xtStartReason", CmDictUtil.getListByType(CmDictConstants.BS_KSYY, mh == null ? null : mh.getXtStartReason()));
		// 血透结束原因
		map.put("xtEndReason", CmDictUtil.getListByType(CmDictConstants.BS_JSYY, mh == null ? null : mh.getXtEndReason()));
		// 腹透开始原因
		map.put("ftStartReason", CmDictUtil.getListByType(CmDictConstants.BS_KSYY, mh == null ? null : mh.getFtStartReason()));
		// 腹透结束原因
		map.put("ftEndReason", CmDictUtil.getListByType(CmDictConstants.BS_JSYY, mh == null ? null : mh.getFtEndReason()));
		// 肾移植结束原因
		map.put("syzEndReason", CmDictUtil.getListByType(CmDictConstants.BS_JSYY, mh == null ? null : mh.getSyzEndReason()));
		// 过敏原因
		map.put("gmResouce", CmDictUtil.getListByType(CmDictConstants.BS_GMY, mh == null ? null : mh.getGmResouce()));

		// 传染病诊断名称
		map.put("bs_crbzdmc", CmDictUtil.getListByType(CmDictConstants.BS_CRBZDMC, mh == null ? null : mh.getCrbDiaName()));
		// 传染病活动状态
		map.put("bs_crbhdzt", CmDictUtil.getListByType(CmDictConstants.BS_CRBHDZT, mh == null ? null : mh.getCrbDiaStatus()));
		// 传染病治疗情况
		map.put("bs_crbzlqk", CmDictUtil.getListByType(CmDictConstants.BS_CRBZLQK, mh == null ? null : mh.getCrbCase()));
	}

	/** 初始化慢性肾功能衰竭 */
	private void initCrf(Map<String, Object> map, CrfPO crf) {
		map.put(CmDictConstants.CLINICAL_PGN, CmDictUtil.getListByType(CmDictConstants.CLINICAL_PGN, crf == null ? null : crf.getPgn()));
		map.put(CmDictConstants.CLINICAL_SGN, CmDictUtil.getListByType(CmDictConstants.CLINICAL_SGN, crf == null ? null : crf.getSgn()));
		map.put(CmDictConstants.CLINICAL_HEREDITARY_NEPHROPATHY, CmDictUtil.getListByType(CmDictConstants.CLINICAL_HEREDITARY_NEPHROPATHY,
						crf == null ? null : crf.getHereditaryNephropathy()));
		map.put(CmDictConstants.CLINICAL_TIN, CmDictUtil.getListByType(CmDictConstants.CLINICAL_TIN, crf == null ? null : crf.getTin()));
		map.put(CmDictConstants.UN_AND_STONE, CmDictUtil.getListByType(CmDictConstants.UN_AND_STONE, crf == null ? null : crf.getUnAndStone()));
		map.put(CmDictConstants.RENAL_RESECTION,
						CmDictUtil.getListByType(CmDictConstants.RENAL_RESECTION, crf == null ? null : crf.getRenalResection()));
		map.put("urologicNeoplasms", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, crf == null ? null : crf.getUrologicNeoplasms()));
		map.put("crfUnknownReason", CmDictUtil.getListByType(CmDictConstants.IS_OR_NOT, crf == null ? null : crf.getUnknownReason()));
	}

	/** 初始化慢性肾功能不全急性加重 */
	private void initSeriousCrf(Map<String, Object> map, SeriousCrfPO seriousCrf) {
		map.put(CmDictConstants.SERIOUS_CRF_REASON,
						CmDictUtil.getListByType(CmDictConstants.SERIOUS_CRF_REASON, seriousCrf == null ? null : seriousCrf.getReason()));
	}

	/** 初始化急性肾功能衰竭 */
	private void initArf(Map<String, Object> map, ArfPO arf) {
		map.put(CmDictConstants.ARF_REASON, CmDictUtil.getListByType(CmDictConstants.ARF_REASON, arf == null ? null : arf.getReason()));
		map.put("arfUnknownReason", CmDictUtil.getListByType(CmDictConstants.IS_OR_NOT, arf == null ? null : arf.getUnknownReason()));
	}

	/** 初始化病理诊断 */
	private void initPathologicDiagnosisResult(Map<String, Object> map, PathologicDiagnosisResultPO pd) {
		map.put(CmDictConstants.PATHOLOGY_PGN, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_PGN, pd == null ? null : pd.getPgn()));
		map.put(CmDictConstants.PATHOLOGY_SGN, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_SGN, pd == null ? null : pd.getSgn()));
		map.put(CmDictConstants.PATHOLOGY_HEREDITARY_NEPHROPATHY, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_HEREDITARY_NEPHROPATHY,
						pd == null ? null : pd.getHereditaryNephropathy()));
		map.put(CmDictConstants.PATHOLOGY_TIN, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_TIN, pd == null ? null : pd.getTin()));
	}

	/** 初始化CKD/AKI分期数据 */
	private void initCkdStage(Map<String, Object> map, CkdStagePO ckd) {
		map.put(CmDictConstants.CKD_TYPE, CmDictUtil.getListByType(CmDictConstants.CKD_TYPE, ckd == null ? null : ckd.getCkdType()));
		map.put(CmDictConstants.CKD_STAGE, CmDictUtil.getListByType(CmDictConstants.CKD_STAGE, ckd == null ? null : ckd.getCkdStage()));
		map.put(CmDictConstants.AKI_TYPE, CmDictUtil.getListByType(CmDictConstants.AKI_TYPE, ckd == null ? null : ckd.getAkiType()));
		map.put(CmDictConstants.AKI_STAGE_RIFLE, CmDictUtil.getListByType(CmDictConstants.AKI_STAGE_RIFLE, ckd == null ? null : ckd.getAkiStage()));
		map.put(CmDictConstants.AKI_STAGE, CmDictUtil.getListByType(CmDictConstants.AKI_STAGE, ckd == null ? null : ckd.getAkiStage()));
	}

	/** 初始化治疗前合并症 */
	private void initCureSymptomAndCondition(Map<String, Object> map, CureSymptomAndConditionPO csac) {
		map.put(CmDictConstants.MAIN_SYMPTOM, CmDictUtil.getListByType(CmDictConstants.MAIN_SYMPTOM, csac == null ? null : csac.getMainSymptom()));
		// =================================================治疗前合并症
		map.put(CmDictConstants.GKWZDXWL_INFO, CmDictUtil.getListByType(CmDictConstants.GKWZDXWL_INFO, csac == null ? null : csac.getGkwzdxwl()));// 1:骨矿物质代谢紊乱

		map.put(CmDictConstants.DFYBX_INFO, CmDictUtil.getListByType(CmDictConstants.DFYBX_INFO, csac == null ? null : csac.getDfypx()));// 2:淀粉样变性

		map.put(CmDictConstants.HXXT_INFO, CmDictUtil.getListByType(CmDictConstants.HXXT_INFO, csac == null ? null : csac.getHxxt()));// 3:呼吸系统

		map.put(CmDictConstants.XHXT_INFO, CmDictUtil.getListByType(CmDictConstants.XHXT_INFO, csac == null ? null : csac.getXhxt()));// 4:消化系统

		map.put(CmDictConstants.XXGXT_INFO, CmDictUtil.getListByType(CmDictConstants.XXGXT_INFO, csac == null ? null : csac.getXxgxt()));// 5:心血管系统

		map.put(CmDictConstants.SJXT_INFO, CmDictUtil.getListByType(CmDictConstants.SJXT_INFO, csac == null ? null : csac.getSjxt()));// 6:神经系统

		map.put(CmDictConstants.PFSY_INFO, CmDictUtil.getListByType(CmDictConstants.PFSY_INFO, csac == null ? null : csac.getPfsy()));// 7:皮肤瘙痒

		map.put(CmDictConstants.XYXT_INFO, CmDictUtil.getListByType(CmDictConstants.XYXT_INFO, csac == null ? null : csac.getXyxt()));// 8:血液系统

		map.put(CmDictConstants.HBZL_INFO, CmDictUtil.getListByType(CmDictConstants.HBZL_INFO, csac == null ? null : csac.getHbzl()));// 9:合并肿瘤

		map.put(CmDictConstants.HBGR_INFO, CmDictUtil.getListByType(CmDictConstants.HBGR_INFO, csac == null ? null : csac.getHbgr()));// 10:合并感染

		map.put(CmDictConstants.ZSMYXJB_INFO, CmDictUtil.getListByType(CmDictConstants.ZSMYXJB_INFO, csac == null ? null : csac.getZsmyxjb()));// 11:自身免疫性疾病

		map.put(CmDictConstants.NFMJDXXT_INFO, CmDictUtil.getListByType(CmDictConstants.NFMJDXXT_INFO, csac == null ? null : csac.getNfmjdxxt()));// 12:内分泌及代谢系统
	}

}
