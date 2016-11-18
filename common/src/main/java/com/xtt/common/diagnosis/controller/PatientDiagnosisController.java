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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CmDictConstants;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.Arf;
import com.xtt.common.dao.model.CkdStage;
import com.xtt.common.dao.model.ClinicalDiagnosisResult;
import com.xtt.common.dao.model.County;
import com.xtt.common.dao.model.Crf;
import com.xtt.common.dao.model.CureSymptomAndCondition;
import com.xtt.common.dao.model.MedicalHistory;
import com.xtt.common.dao.model.OtherDiagnosisResult;
import com.xtt.common.dao.model.PathologicDiagnosisResult;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientDiagnosis;
import com.xtt.common.dao.model.Province;
import com.xtt.common.dao.model.SeriousCrf;
import com.xtt.common.dao.po.ArfPO;
import com.xtt.common.dao.po.CrfPO;
import com.xtt.common.dao.po.MedicalHistoryPO;
import com.xtt.common.dao.po.MedicalHistoryRemarkPO;
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
	public ModelAndView newPatient(Long fkPatientDiagnosisId, Long patientId, String patientName, String tabId) throws Exception {

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
		MedicalHistory mh = new MedicalHistory();
		ClinicalDiagnosisResult cd = new ClinicalDiagnosisResult();
		Crf crf = new Crf();
		SeriousCrf seriousCrf = new SeriousCrf();
		Arf arf = new Arf();
		PathologicDiagnosisResult pdr = new PathologicDiagnosisResult();
		CkdStage ckdStage = new CkdStage();
		CureSymptomAndCondition cureSymptomAndCondition = new CureSymptomAndCondition();

		Map<String, Object> hisMap = null;
		// 已录入过部分信息，恢复最后一次操作数据
		if (fkPatientDiagnosisId != null) {
			pd = pd == null ? patientDiagnosisService.selectById(fkPatientDiagnosisId) : pd;
			hisMap = patientDiagnosisService.selectAllDiagnosisInfo(fkPatientDiagnosisId);
			model.addAllObjects(hisMap);
			mh = (MedicalHistory) hisMap.get("medicalHistory");
			cd = (ClinicalDiagnosisResult) hisMap.get("clinicalDiagnosisResult");
			crf = (Crf) hisMap.get("crf");
			seriousCrf = (SeriousCrf) hisMap.get("seriousCrf");
			arf = (Arf) hisMap.get("arf");
			ckdStage = (CkdStage) hisMap.get("ckdStage");
			pdr = (PathologicDiagnosisResult) hisMap.get("pathologicDiagnosisResult");
			cureSymptomAndCondition = (CureSymptomAndCondition) hisMap.get("cureSymptomAndCondition");
			// 如果患者id不存在，从诊断中获取
			patientId = patientId == null ? pd.getFkPatientId() : patientId;
		}
		if (patientId != null) {
			model.addObject("patient", patientService.selectById(patientId));
			// 已经添加的患者卡片数据
			model.addObject("patientCardList", patientCardService.selectByPatientId(patientId));
		}
		model.addObject("patientId", patientId);
		model.addObject("patientDiagnosis", pd == null ? new PatientDiagnosis() : pd);
		model.addObject("fkPatientDiagnosisId", fkPatientDiagnosisId);

		List<Province> provinceList = commonService.getProvinceList();
		model.addObject("provinceList", provinceList);
		Patient patient = hisMap == null ? null : (Patient) hisMap.get("patient");
		List<County> countyList = null;
		if (patient == null) {
			countyList = commonService.getCountyList(provinceList.get(0).getId());
		} else {
			countyList = commonService.getCountyList(patient.getProvince());
		}
		model.addObject("countyList", countyList);
		model.addObject(CmDictConstants.MEDICARE_CARD_TYPE,
						CmDictUtil.getListByType(CmDictConstants.MEDICARE_CARD_TYPE, patient == null ? null : patient.getMedicareCardType()));

		// ===========从缓存中获取病史相关字典数据
		model.addObject(CmDictConstants.FIRST_DIALYSIS_METHOD,
						CmDictUtil.getListByType(CmDictConstants.FIRST_DIALYSIS_METHOD, mh == null ? null : mh.getFirstDialysisMethod()));
		model.addObject("hasCva", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasCva()));
		model.addObject("hasVascularDisease", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasVascularDisease()));
		model.addObject("hasSeriousDisease", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasSeriousDisease()));
		model.addObject("hasPsychosis", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, mh == null ? null : mh.getHasPsychosis()));
		model.addObject(CmDictConstants.HEMORRHAGE, CmDictUtil.getListByType(CmDictConstants.HEMORRHAGE, mh == null ? null : mh.getHemorrhage()));
		model.addObject(CmDictConstants.HEART_DEFECTS,
						CmDictUtil.getListByType(CmDictConstants.HEART_DEFECTS, mh == null ? null : mh.getHeartDefects()));
		// 血透开始原因
		model.addObject("xtStartReason", CmDictUtil.getListByType(CmDictConstants.BS_KSYY, mh == null ? null : mh.getXtStartReason()));
		// 血透结束原因
		model.addObject("xtEndReason", CmDictUtil.getListByType(CmDictConstants.BS_JSYY, mh == null ? null : mh.getXtEndReason()));
		// 腹透开始原因
		model.addObject("ftStartReason", CmDictUtil.getListByType(CmDictConstants.BS_KSYY, mh == null ? null : mh.getFtStartReason()));
		// 腹透结束原因
		model.addObject("ftEndReason", CmDictUtil.getListByType(CmDictConstants.BS_JSYY, mh == null ? null : mh.getFtEndReason()));
		// 肾移植结束原因
		model.addObject("syzEndReason", CmDictUtil.getListByType(CmDictConstants.BS_JSYY, mh == null ? null : mh.getSyzEndReason()));
		// 过敏原因
		model.addObject("gmResouce", CmDictUtil.getListByType(CmDictConstants.BS_GMY, mh == null ? null : mh.getGmResouce()));
		// 传染病诊断名称
		model.addObject("bs_crbzdmc", CmDictUtil.getListByType(CmDictConstants.BS_CRBZDMC, mh == null ? null : mh.getCrbDiaName()));
		// 传染病活动状态
		model.addObject("bs_crbhdzt", CmDictUtil.getListByType(CmDictConstants.BS_CRBHDZT, mh == null ? null : mh.getCrbDiaStatus()));
		// 传染病治疗情况
		model.addObject("bs_crbzlqk", CmDictUtil.getListByType(CmDictConstants.BS_CRBZLQK, mh == null ? null : mh.getCrbCase()));

		model.addObject(CmDictConstants.NEPHROSIS_TYPE, CmDictUtil.getListByType(CmDictConstants.NEPHROSIS_TYPE, cd == null ? null : cd.getType()));
		model.addObject(CmDictConstants.CLINICAL_PGN, CmDictUtil.getListByType(CmDictConstants.CLINICAL_PGN, crf == null ? null : crf.getPgn()));
		model.addObject(CmDictConstants.CLINICAL_SGN, CmDictUtil.getListByType(CmDictConstants.CLINICAL_SGN, crf == null ? null : crf.getSgn()));
		model.addObject(CmDictConstants.CLINICAL_HEREDITARY_NEPHROPATHY, CmDictUtil.getListByType(CmDictConstants.CLINICAL_HEREDITARY_NEPHROPATHY,
						crf == null ? null : crf.getHereditaryNephropathy()));
		model.addObject(CmDictConstants.CLINICAL_TIN, CmDictUtil.getListByType(CmDictConstants.CLINICAL_TIN, crf == null ? null : crf.getTin()));
		model.addObject(CmDictConstants.UN_AND_STONE,
						CmDictUtil.getListByType(CmDictConstants.UN_AND_STONE, crf == null ? null : crf.getUnAndStone()));
		model.addObject(CmDictConstants.RENAL_RESECTION,
						CmDictUtil.getListByType(CmDictConstants.RENAL_RESECTION, crf == null ? null : crf.getRenalResection()));
		model.addObject("urologicNeoplasms", CmDictUtil.getListByType(CmDictConstants.HAVE_OR_NOT, crf == null ? null : crf.getUrologicNeoplasms()));
		model.addObject("crfUnknownReason", CmDictUtil.getListByType(CmDictConstants.IS_OR_NOT, crf == null ? null : crf.getUnknownReason()));
		model.addObject(CmDictConstants.SERIOUS_CRF_REASON,
						CmDictUtil.getListByType(CmDictConstants.SERIOUS_CRF_REASON, seriousCrf == null ? null : seriousCrf.getReason()));
		model.addObject(CmDictConstants.ARF_REASON, CmDictUtil.getListByType(CmDictConstants.ARF_REASON, arf == null ? null : arf.getReason()));
		model.addObject("arfUnknownReason", CmDictUtil.getListByType(CmDictConstants.IS_OR_NOT, arf == null ? null : arf.getUnknownReason()));
		model.addObject(CmDictConstants.PATHOLOGY_PGN, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_PGN, pdr == null ? null : pdr.getPgn()));
		model.addObject(CmDictConstants.PATHOLOGY_SGN, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_SGN, pdr == null ? null : pdr.getSgn()));
		model.addObject(CmDictConstants.PATHOLOGY_HEREDITARY_NEPHROPATHY, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_HEREDITARY_NEPHROPATHY,
						pdr == null ? null : pdr.getHereditaryNephropathy()));
		model.addObject(CmDictConstants.PATHOLOGY_TIN, CmDictUtil.getListByType(CmDictConstants.PATHOLOGY_TIN, pdr == null ? null : pdr.getTin()));
		model.addObject(CmDictConstants.CKD_TYPE,
						CmDictUtil.getListByType(CmDictConstants.CKD_TYPE, ckdStage == null ? null : ckdStage.getCkdType()));
		model.addObject(CmDictConstants.CKD_STAGE,
						CmDictUtil.getListByType(CmDictConstants.CKD_STAGE, ckdStage == null ? null : ckdStage.getCkdStage()));
		model.addObject(CmDictConstants.AKI_TYPE,
						CmDictUtil.getListByType(CmDictConstants.AKI_TYPE, ckdStage == null ? null : ckdStage.getAkiType()));
		model.addObject(CmDictConstants.AKI_STAGE_RIFLE,
						CmDictUtil.getListByType(CmDictConstants.AKI_STAGE_RIFLE, ckdStage == null ? null : ckdStage.getAkiStage()));
		model.addObject(CmDictConstants.AKI_STAGE,
						CmDictUtil.getListByType(CmDictConstants.AKI_STAGE, ckdStage == null ? null : ckdStage.getAkiStage()));
		model.addObject(CmDictConstants.MAIN_SYMPTOM, CmDictUtil.getListByType(CmDictConstants.MAIN_SYMPTOM,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getMainSymptom()));

		// =================================================治疗前合并症
		model.addObject(CmDictConstants.GKWZDXWL_INFO, CmDictUtil.getListByType(CmDictConstants.GKWZDXWL_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getGkwzdxwl()));// 1:骨矿物质代谢紊乱

		model.addObject(CmDictConstants.DFYBX_INFO, CmDictUtil.getListByType(CmDictConstants.DFYBX_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getDfypx()));// 2:淀粉样变性

		model.addObject(CmDictConstants.HXXT_INFO, CmDictUtil.getListByType(CmDictConstants.HXXT_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getHxxt()));// 3:呼吸系统

		model.addObject(CmDictConstants.XHXT_INFO, CmDictUtil.getListByType(CmDictConstants.XHXT_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getXhxt()));// 4:消化系统

		model.addObject(CmDictConstants.XXGXT_INFO, CmDictUtil.getListByType(CmDictConstants.XXGXT_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getXxgxt()));// 5:心血管系统

		model.addObject(CmDictConstants.SJXT_INFO, CmDictUtil.getListByType(CmDictConstants.SJXT_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getSjxt()));// 6:神经系统

		model.addObject(CmDictConstants.PFSY_INFO, CmDictUtil.getListByType(CmDictConstants.PFSY_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getPfsy()));// 7:皮肤瘙痒

		model.addObject(CmDictConstants.XYXT_INFO, CmDictUtil.getListByType(CmDictConstants.XYXT_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getXyxt()));// 8:血液系统

		model.addObject(CmDictConstants.HBZL_INFO, CmDictUtil.getListByType(CmDictConstants.HBZL_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getHbzl()));// 9:合并肿瘤

		model.addObject(CmDictConstants.HBGR_INFO, CmDictUtil.getListByType(CmDictConstants.HBGR_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getHbgr()));// 10:合并感染

		model.addObject(CmDictConstants.ZSMYXJB_INFO, CmDictUtil.getListByType(CmDictConstants.ZSMYXJB_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getZsmyxjb()));// 11:自身免疫性疾病

		model.addObject(CmDictConstants.NFMJDXXT_INFO, CmDictUtil.getListByType(CmDictConstants.NFMJDXXT_INFO,
						cureSymptomAndCondition == null ? null : cureSymptomAndCondition.getNfmjdxxt()));// 12:内分泌及代谢系统
		// 13:其他
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
		ModelAndView model = newPatient(fkPatientDiagnosisId, patientId, null, null);
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
}
