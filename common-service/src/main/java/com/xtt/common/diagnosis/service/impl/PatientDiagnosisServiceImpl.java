/**   
 * @Title: PatientDiagnosisServiceImpl.java 
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月30日 下午12:31:26 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.ArfMapper;
import com.xtt.common.dao.mapper.CkdStageMapper;
import com.xtt.common.dao.mapper.ClinicalDiagnosisResultMapper;
import com.xtt.common.dao.mapper.CrfMapper;
import com.xtt.common.dao.mapper.CureSymptomAndConditionMapper;
import com.xtt.common.dao.mapper.MedicalHistoryMapper;
import com.xtt.common.dao.mapper.MedicalHistoryRemarkMapper;
import com.xtt.common.dao.mapper.OtherDiagnosisResultMapper;
import com.xtt.common.dao.mapper.PathologicDiagnosisResultMapper;
import com.xtt.common.dao.mapper.PatientDiagnosisMapper;
import com.xtt.common.dao.mapper.SeriousCrfMapper;
import com.xtt.common.dao.model.Arf;
import com.xtt.common.dao.model.CkdStage;
import com.xtt.common.dao.model.ClinicalDiagnosisResult;
import com.xtt.common.dao.model.Crf;
import com.xtt.common.dao.model.CureSymptomAndCondition;
import com.xtt.common.dao.model.MarkType;
import com.xtt.common.dao.model.MedicalHistory;
import com.xtt.common.dao.model.OtherDiagnosisResult;
import com.xtt.common.dao.model.PathologicDiagnosisResult;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientDiagnosis;
import com.xtt.common.dao.model.SeriousCrf;
import com.xtt.common.dao.po.ArfPO;
import com.xtt.common.dao.po.CkdStagePO;
import com.xtt.common.dao.po.ClinicalDiagnosisResultPO;
import com.xtt.common.dao.po.CrfPO;
import com.xtt.common.dao.po.CureSymptomAndConditionPO;
import com.xtt.common.dao.po.MedicalHistoryPO;
import com.xtt.common.dao.po.MedicalHistoryRemarkPO;
import com.xtt.common.dao.po.OtherDiagnosisResultPO;
import com.xtt.common.dao.po.PathologicDiagnosisResultPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.SeriousCrfPO;
import com.xtt.common.diagnosis.DiagnosisStepEnum;
import com.xtt.common.diagnosis.service.IPatientDiagnosisService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class PatientDiagnosisServiceImpl implements IPatientDiagnosisService {

	@Autowired
	private PatientDiagnosisMapper patientDiagnosisMapper;
	@Autowired
	private ClinicalDiagnosisResultMapper clinicalDiagnosisResultMapper;
	@Autowired
	private CkdStageMapper ckdStageMapper;
	@Autowired
	private CrfMapper crfMapper;
	@Autowired
	private ArfMapper arfMapper;
	@Autowired
	MedicalHistoryMapper medicalHistoryMapper;
	@Autowired
	SeriousCrfMapper seriousCrfMapper;
	@Autowired
	PathologicDiagnosisResultMapper pathologicDiagnosisResultMapper;
	@Autowired
	CureSymptomAndConditionMapper cureSymptomAndConditionMapper;
	@Autowired
	OtherDiagnosisResultMapper otherDiagnosisResultMapper;
	@Autowired
	private IPatientService patientService;
	@Autowired
	private MedicalHistoryRemarkMapper medicalHistoryRemarkMapper;

	/**
	 * 根据患者ID查询处于草稿状态的诊断信息
	 * 
	 * @Title: getPatientDiagnosisByPatientId
	 * @param patientId
	 * @return
	 * 
	 */
	@Override
	public PatientDiagnosis getPatientDiagnosisByPatientId(Long patientId) {
		return patientDiagnosisMapper.selectByPatientId(patientId, UserUtil.getTenantId());
	}

	/**
	 * 根据诊断信息ID查询诊断信息
	 * 
	 * @Title: getPatientDiagnosisByPDId
	 * @param pdId
	 * @return
	 * 
	 */
	@Override
	public PatientDiagnosis selectById(Long pdId) {
		return patientDiagnosisMapper.selectByPrimaryKey(pdId);
	}

	@Override
	public Integer countByPatientId(Long patientId, Integer tenantId) {
		return patientDiagnosisMapper.countByPatientId(patientId, tenantId);
	}

	@Override
	public List<ClinicalDiagnosisResultPO> getClinicalDiagnosisResultByPatientId(Long patientId) {
		return clinicalDiagnosisResultMapper.selectByPatientId(patientId);
	}

	@Override
	public List<CkdStagePO> getCkdStageByPatientId(Long patientId) {
		return ckdStageMapper.selectByPatientId(patientId);
	}

	@Override
	public List<CrfPO> getCrfByPatientId(Long patientId) {
		return crfMapper.selectByPatientId(patientId);
	}

	@Override
	public List<ArfPO> getArfByPatientId(Long patientId) {
		return arfMapper.selectByPatientId(patientId);
	}

	@Override
	public Map<String, Object> getLastestByPatientId(Long patientId) {
		// 询问病史
		List<MedicalHistoryPO> medicalHistory = medicalHistoryMapper.selectByPatientId(patientId);
		MedicalHistoryPO mh = new MedicalHistoryPO();
		if (medicalHistory != null && !medicalHistory.isEmpty()) {
			mh = medicalHistory.get(0);
		}
		// 临床诊断
		List<ClinicalDiagnosisResultPO> clinicalDiagnosisResult = clinicalDiagnosisResultMapper.selectByPatientId(patientId);
		ClinicalDiagnosisResult cdr = new ClinicalDiagnosisResult();
		if (clinicalDiagnosisResult != null && !clinicalDiagnosisResult.isEmpty()) {
			cdr = clinicalDiagnosisResult.get(0);
		}
		// 慢性肾功能衰竭
		List<CrfPO> crfList = crfMapper.selectByPatientId(patientId);
		CrfPO crf = new CrfPO();
		if (crfList != null && !crfList.isEmpty()) {
			crf = crfList.get(0);
		}
		// 慢性肾功能不全急性加重
		List<SeriousCrfPO> seriousCrf = seriousCrfMapper.selectByPatientId(patientId);
		SeriousCrfPO sc = new SeriousCrfPO();
		if (seriousCrf != null && !seriousCrf.isEmpty()) {
			sc = seriousCrf.get(0);
		}
		// 急性肾功能衰竭
		List<ArfPO> arfList = arfMapper.selectByPatientId(patientId);
		ArfPO arf = new ArfPO();
		if (arfList != null && !arfList.isEmpty()) {
			arf = arfList.get(0);
		}
		// 病理诊断
		List<PathologicDiagnosisResultPO> pathologicDiagnosisResult = pathologicDiagnosisResultMapper.selectByPatientId(patientId);
		PathologicDiagnosisResultPO pdr = new PathologicDiagnosisResultPO();
		if (pathologicDiagnosisResult != null && !pathologicDiagnosisResult.isEmpty()) {
			pdr = pathologicDiagnosisResult.get(0);
		}
		// CKD/AKI
		List<CkdStagePO> ckdStage = ckdStageMapper.selectByPatientId(patientId);
		CkdStagePO ckd = new CkdStagePO();
		if (ckdStage != null && !ckdStage.isEmpty()) {
			ckd = ckdStage.get(0);
		}
		// 治疗前合并症
		List<CureSymptomAndConditionPO> cureSymptomAndCondition = cureSymptomAndConditionMapper.selectByPatientId(patientId);
		CureSymptomAndConditionPO csac = new CureSymptomAndConditionPO();
		if (cureSymptomAndCondition != null && !cureSymptomAndCondition.isEmpty()) {
			csac = cureSymptomAndCondition.get(0);
		}
		// 其它诊断
		List<OtherDiagnosisResultPO> otherDiagnosisResult = otherDiagnosisResultMapper.selectByPatientId(patientId);
		OtherDiagnosisResultPO odr = new OtherDiagnosisResultPO();
		if (otherDiagnosisResult != null && !otherDiagnosisResult.isEmpty()) {
			odr = otherDiagnosisResult.get(0);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("medicalHistory", mh);
		map.put("clinicalDiagnosisResult", cdr);
		map.put("crf", crf);
		map.put("seriousCrf", sc);
		map.put("arf", arf);
		map.put("pathologicDiagnosisResult", pdr);
		map.put("ckdStage", ckd);
		map.put("cureSymptomAndCondition", csac);
		map.put("otherDiagnosisResult", odr);
		return map;
	}

	@Override
	public Map<String, Object> selectAllDiagnosisInfo(Long pdId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pdId == null)
			return map;
		// 恢复患者入院首次录入信息
		PatientDiagnosis pd = selectById(pdId);
		PatientPO patient = patientService.selectById(pd.getFkPatientId());
		// 询问病史
		MedicalHistoryPO medicalHistory = medicalHistoryMapper.selectByPDId(pdId);
		if (medicalHistory != null) {
			// 添加（手术史、 血透史）等多条数据
			addMedicalHistory(map, medicalHistory);
			// 临床诊断
			ClinicalDiagnosisResultPO clinicalDiagnosisResult = clinicalDiagnosisResultMapper.selectByPDId(pdId);
			// 慢性肾功能衰竭
			CrfPO crf = crfMapper.selectByPDId(pdId);
			// 慢性肾功能不全急性加重
			SeriousCrfPO seriousCrf = seriousCrfMapper.selectByPDId(pdId);
			// 急性肾功能衰竭
			ArfPO arf = arfMapper.selectByPDId(pdId);
			// 病理诊断
			PathologicDiagnosisResultPO pathologicDiagnosisResult = pathologicDiagnosisResultMapper.selectByPDId(pdId);
			// CKD/AKI
			CkdStagePO ckdStage = ckdStageMapper.selectByPDId(pdId);
			// 治疗前合并症
			CureSymptomAndConditionPO cureSymptomAndCondition = cureSymptomAndConditionMapper.selectByPDId(pdId);
			// 其它诊断
			OtherDiagnosisResultPO otherDiagnosisResult = otherDiagnosisResultMapper.selectByPDId(pdId);

			map.put("patient", patient);
			map.put("medicalHistory", medicalHistory);

			map.put("clinicalDiagnosisResult", clinicalDiagnosisResult);
			map.put("crf", crf);
			map.put("seriousCrf", seriousCrf);
			map.put("arf", arf);
			map.put("ckdStage", ckdStage);
			map.put("pathologicDiagnosisResult", pathologicDiagnosisResult);
			map.put("cureSymptomAndCondition", cureSymptomAndCondition);
			map.put("otherDiagnosisResult", otherDiagnosisResult);
		}
		return map;
	}

	/** 添加（手术史、 血透史）等多条数据 */
	public Map<String, Object> addMedicalHistory(Map<String, Object> map, MedicalHistoryPO medicalHistory) {
		if (medicalHistory == null)
			return map;
		// 询问病史
		List<MedicalHistoryRemarkPO> optList = null;
		List<MedicalHistoryRemarkPO> xtList = null;
		List<MedicalHistoryRemarkPO> ftList = null;
		List<MedicalHistoryRemarkPO> syzList = null;
		List<MedicalHistoryRemarkPO> gmList = null;
		List<MedicalHistoryRemarkPO> crbList = null;
		medicalHistory.setMhrMark("opt");// 手术史
		optList = medicalHistoryRemarkMapper.selectRemarkKey(medicalHistory);
		medicalHistory.setMhrMark("xt");// 血透史
		xtList = medicalHistoryRemarkMapper.selectRemarkKey(medicalHistory);
		medicalHistory.setMhrMark("ft");// 腹透史
		ftList = medicalHistoryRemarkMapper.selectRemarkKey(medicalHistory);
		medicalHistory.setMhrMark("syz");// 肾移植
		syzList = medicalHistoryRemarkMapper.selectRemarkKey(medicalHistory);
		medicalHistory.setMhrMark("gm");// 过敏
		gmList = medicalHistoryRemarkMapper.selectRemarkKey(medicalHistory);
		medicalHistory.setMhrMark("crb");// 传染病
		crbList = medicalHistoryRemarkMapper.selectRemarkKey(medicalHistory);
		map.put("meList", optList);
		map.put("xtList", xtList);
		map.put("ftList", ftList);
		map.put("syzList", syzList);
		map.put("gmList", gmList);
		map.put("crbList", crbList);
		return map;
	}

	@Override
	public Long savePatient(Patient patient, boolean isImport) {
		if (patient.getId() == null) {
			patientService.savePatient(patient, isImport);
			// 首次创建患者，自动创建一次诊断流程活动
			PatientDiagnosis pd = createPatientDiagnosis(patient.getId());

			return pd.getId();
		} else {
			patientService.savePatient(patient, isImport);
			return patient.getId();
		}
	}

	@Override
	public Long saveMedicalHistory(MedicalHistory model) throws Exception {
		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());
		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		MedicalHistory entity = medicalHistoryMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			medicalHistoryMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			medicalHistoryMapper.insert(model);
		}
		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.MEDICAL_HISTORY.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.MEDICAL_HISTORY.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long saveMedicalHistory(MedicalHistory model, MedicalHistoryRemarkPO mePo) throws Exception {
		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());
		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		MedicalHistory entity = medicalHistoryMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			medicalHistoryMapper.updateByPrimaryKey(model);
			mePo.setMedicalHistoryid(model.getId());
			// 获取多次病史的数据
			List<MarkType> liMarkTypes = mePo.getMhrMarkType();
			if (liMarkTypes != null && liMarkTypes.size() > 0) {
				for (MarkType mType : liMarkTypes) {
					if (!StringUtil.isEmpty(mType.getMhrStartTimeShow())) {
						mePo.setId(null);
						DataUtil.setSystemFieldValue(mePo);
						mePo.setFkTenantId(UserUtil.getTenantId());
						mePo.setMhrStartTimeShow(mType.getMhrStartTimeShow());// 开始时间
						mePo.setMhrEndTimeShow(mType.getMhrEndTimeShow());// 结束时间
						mePo.setMhrStartreasonOrname(mType.getMhrStartreasonOrname());// 开始原因或名称
						mePo.setMhrStartortherreason(mType.getMhrStartortherreason());// 开始的其他原因
						mePo.setMhrEndotherreason(mType.getMhrEndotherreason());// 其他结束原因
						mePo.setMhrEndreason(mType.getMhrEndreason());// 结束原因
						mePo.setMhrMark(mType.getMhrMark());// 标志
						mePo.setMhrRemark(mType.getMhrRemark());// 备注
						mePo.setActivitystatus(mType.getActivityStatus());// 活动状态
						medicalHistoryRemarkMapper.insertSelectivePO(mePo);
					}
				}
			}
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			medicalHistoryMapper.insert(model);
		}
		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.MEDICAL_HISTORY.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.MEDICAL_HISTORY.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long saveCrf(CrfPO model) throws Exception {

		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());

		model.setFkPatientDiagnosisId(pd.getId());

		// 更新临床诊断主表内容
		ClinicalDiagnosisResult cdr = clinicalDiagnosisResultMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (cdr == null) {
			cdr = new ClinicalDiagnosisResult();
			cdr.setFkPatientDiagnosisId(pd.getId());
			cdr.setFkPatientId(model.getFkPatientId());
			cdr.setType(CommonConstants.NEPHROSIS_TYPE_CRF);
			cdr.setOperatorId(UserUtil.getLoginUserId());
			cdr.setIsDraft(true);
			DataUtil.setVersion(cdr, 1);
			cdr.setFkTenantId(UserUtil.getTenantId());
			clinicalDiagnosisResultMapper.insert(cdr);
		} else {
			cdr.setOperatorId(UserUtil.getLoginUserId());
			cdr.setVersion(cdr.getVersion() + 1);
			cdr.setType(CommonConstants.NEPHROSIS_TYPE_CRF);
			clinicalDiagnosisResultMapper.updateByPrimaryKey(cdr);
		}

		model.setFkClinicalDiagnosisResultId(cdr.getId());
		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		// 更新临床诊断子表内容
		Crf entity = crfMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			crfMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			crfMapper.insert(model);
		}

		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.CLINICAL_DIAGNOSIS.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.CLINICAL_DIAGNOSIS.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long saveArf(ArfPO model) throws Exception {

		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());

		model.setFkPatientDiagnosisId(pd.getId());

		// 更新临床诊断主表内容
		ClinicalDiagnosisResult cdr = clinicalDiagnosisResultMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (cdr == null) {
			cdr = new ClinicalDiagnosisResult();
			cdr.setFkPatientDiagnosisId(pd.getId());
			cdr.setFkPatientId(model.getFkPatientId());
			cdr.setType(CommonConstants.NEPHROSIS_TYPE_ARF);
			cdr.setOperatorId(UserUtil.getLoginUserId());
			cdr.setIsDraft(true);
			DataUtil.setVersion(cdr, 1);
			cdr.setFkTenantId(UserUtil.getTenantId());
			clinicalDiagnosisResultMapper.insert(cdr);
		} else {
			cdr.setOperatorId(UserUtil.getLoginUserId());
			cdr.setVersion(cdr.getVersion() + 1);
			cdr.setType(CommonConstants.NEPHROSIS_TYPE_ARF);
			clinicalDiagnosisResultMapper.updateByPrimaryKey(cdr);
		}

		model.setFkClinicalDiagnosisResultId(cdr.getId());
		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		// 更新临床诊断子表内容
		Arf entity = arfMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			arfMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			arfMapper.insert(model);
		}

		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.CLINICAL_DIAGNOSIS.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.CLINICAL_DIAGNOSIS.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long saveSeriousCrf(SeriousCrfPO model) throws Exception {

		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());

		model.setFkPatientDiagnosisId(pd.getId());

		// 更新临床诊断主表内容
		ClinicalDiagnosisResult cdr = clinicalDiagnosisResultMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (cdr == null) {
			cdr = new ClinicalDiagnosisResult();
			cdr.setFkPatientDiagnosisId(pd.getId());
			cdr.setFkPatientId(model.getFkPatientId());
			cdr.setType(CommonConstants.NEPHROSIS_TYPE_SERIOUS_CRF);
			cdr.setOperatorId(UserUtil.getLoginUserId());
			cdr.setIsDraft(true);
			DataUtil.setVersion(cdr, 1);
			cdr.setFkTenantId(UserUtil.getTenantId());
			clinicalDiagnosisResultMapper.insert(cdr);
		} else {
			cdr.setOperatorId(UserUtil.getLoginUserId());
			cdr.setVersion(cdr.getVersion() + 1);
			cdr.setType(CommonConstants.NEPHROSIS_TYPE_SERIOUS_CRF);
			clinicalDiagnosisResultMapper.updateByPrimaryKey(cdr);
		}

		model.setFkClinicalDiagnosisResultId(cdr.getId());
		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		// 更新临床诊断子表内容
		SeriousCrf entity = seriousCrfMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			seriousCrfMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			seriousCrfMapper.insert(model);
		}

		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.CLINICAL_DIAGNOSIS.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.CLINICAL_DIAGNOSIS.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long savePathologicDiagnosis(PathologicDiagnosisResult model) {

		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());

		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		PathologicDiagnosisResult entity = pathologicDiagnosisResultMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			pathologicDiagnosisResultMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			pathologicDiagnosisResultMapper.insert(model);
		}
		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.PATHOLOGIC_DIAGNOSIS.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.PATHOLOGIC_DIAGNOSIS.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long saveCkdStage(CkdStage model) {

		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());

		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		model.setFkTenantId(UserUtil.getTenantId());
		CkdStage entity = ckdStageMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			ckdStageMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			ckdStageMapper.insert(model);
		}
		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.CKD_AKI.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.CKD_AKI.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long saveCureComplication(CureSymptomAndCondition model) {

		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());

		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		CureSymptomAndConditionPO entity = cureSymptomAndConditionMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			cureSymptomAndConditionMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			cureSymptomAndConditionMapper.insert(model);
		}
		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.CURE_COMPLICATION.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.CURE_COMPLICATION.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public Long saveOtherDiagnosis(OtherDiagnosisResult model) {

		PatientDiagnosis pd = getPatientDiagnosisByPDId(model.getFkPatientDiagnosisId(), model.getFkPatientId());

		DataUtil.setSystemFieldValue(model);
		model.setOperatorId(UserUtil.getLoginUserId());
		model.setIsEnable(true);
		model.setIsDraft(true);
		OtherDiagnosisResult entity = otherDiagnosisResultMapper.selectByPDId(model.getFkPatientDiagnosisId());
		if (pd.getIsDraft() && entity != null) {
			model.setVersion(entity.getVersion() + 1);
			otherDiagnosisResultMapper.updateByPrimaryKey(model);
		} else {
			model.setFkPatientDiagnosisId(pd.getId());
			model.setVersion(1);
			model.setFkTenantId(UserUtil.getTenantId());
			otherDiagnosisResultMapper.insert(model);
		}
		if (pd.getLastStep() != null && pd.getLastStep() < DiagnosisStepEnum.OTHER_DIAGNOSIS.getValue()) {
			pd.setLastStep(DiagnosisStepEnum.OTHER_DIAGNOSIS.getValue());
		}
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);
		return pd.getId();
	}

	@Override
	public void saveFirstDone(Long fkPatientDiagnosisId) {
		PatientDiagnosis pd = patientDiagnosisMapper.selectByPrimaryKey(fkPatientDiagnosisId);
		pd.setLastStep(DiagnosisStepEnum.FIRST_TDONE.getValue());
		pd.setIsDraft(false);
		DataUtil.setSystemFieldValue(pd);
		pd.setOperatorId(UserUtil.getLoginUserId());
		patientDiagnosisMapper.updateByPrimaryKey(pd);

		MedicalHistory medicalHistory = medicalHistoryMapper.selectByPDId(fkPatientDiagnosisId);
		if (medicalHistory != null) {
			medicalHistory.setIsDraft(false);
			medicalHistoryMapper.updateByPrimaryKey(medicalHistory);
		}

		ClinicalDiagnosisResult cdr = clinicalDiagnosisResultMapper.selectByPDId(fkPatientDiagnosisId);
		if (cdr != null) {
			cdr.setIsDraft(false);
			clinicalDiagnosisResultMapper.updateByPrimaryKey(cdr);
		}

		Crf crf = crfMapper.selectByPDId(fkPatientDiagnosisId);
		if (crf != null) {
			crf.setIsDraft(false);
			crfMapper.updateByPrimaryKey(crf);
		}

		SeriousCrf seriousCrf = seriousCrfMapper.selectByPDId(fkPatientDiagnosisId);
		if (seriousCrf != null) {
			seriousCrf.setIsDraft(false);
			seriousCrfMapper.updateByPrimaryKey(seriousCrf);
		}

		Arf arf = arfMapper.selectByPDId(fkPatientDiagnosisId);
		if (arf != null) {
			arf.setIsDraft(false);
			arfMapper.updateByPrimaryKey(arf);
		}

		PathologicDiagnosisResult pathologicDiagnosisResult = pathologicDiagnosisResultMapper.selectByPDId(fkPatientDiagnosisId);
		if (pathologicDiagnosisResult != null) {
			pathologicDiagnosisResult.setIsDraft(false);
			pathologicDiagnosisResultMapper.updateByPrimaryKey(pathologicDiagnosisResult);
		}

		CkdStage ckdStage = ckdStageMapper.selectByPDId(fkPatientDiagnosisId);
		if (ckdStage != null) {
			ckdStage.setIsDraft(false);
			ckdStageMapper.updateByPrimaryKey(ckdStage);
		}

		CureSymptomAndConditionPO cureSymptomAndCondition = cureSymptomAndConditionMapper.selectByPDId(fkPatientDiagnosisId);
		if (cureSymptomAndCondition != null) {
			cureSymptomAndCondition.setIsDraft(false);
			cureSymptomAndConditionMapper.updateByPrimaryKey(cureSymptomAndCondition);
		}

		OtherDiagnosisResultPO otherDiagnosisResult = otherDiagnosisResultMapper.selectByPDId(fkPatientDiagnosisId);
		if (otherDiagnosisResult != null) {
			otherDiagnosisResult.setIsDraft(false);
			otherDiagnosisResultMapper.updateByPrimaryKey(otherDiagnosisResult);
		}
	}

	/**
	 * 创建诊断流程活动
	 * 
	 * @Title: createPatientDiagnosis
	 * @param patientId
	 * @return
	 * 
	 */
	private PatientDiagnosis createPatientDiagnosis(Long patientId) {
		PatientDiagnosis pd = new PatientDiagnosis();
		pd.setFkPatientId(patientId);
		pd.setIsDraft(true);
		pd.setLastStep(DiagnosisStepEnum.PATIENT_INFO.getValue());
		pd.setOperatorId(UserUtil.getLoginUserId());
		pd.setFkTenantId(UserUtil.getLoginUser().getTenantId());
		DataUtil.setSystemFieldValue(pd);
		patientDiagnosisMapper.insert(pd);
		return pd;
	}

	/**
	 * 根据fkPatientDiagnosisId取得一条诊断记录，没有则根据patientId创建一条记录，并反回记录
	 * 
	 * @Title: getPatientDiagnosisByPDId
	 * @param fkPatientDiagnosisId
	 * @param patientId
	 * @return
	 * 
	 */
	private PatientDiagnosis getPatientDiagnosisByPDId(Long fkPatientDiagnosisId, Long patientId) {
		PatientDiagnosis pd;
		if (fkPatientDiagnosisId == null) {
			pd = createPatientDiagnosis(patientId);
		} else {
			pd = patientDiagnosisMapper.selectByPrimaryKey(fkPatientDiagnosisId);
		}
		return pd;
	}

	@Override
	public Map<String, Object> selectDignosisByPatient(Long patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 患者基本信息
		Patient patient = patientService.selectById(patientId);
		PatientDiagnosis patientDiagnosis = patientDiagnosisMapper.selectByPatientId(patientId, UserUtil.getTenantId());
		// 询问病史
		List<MedicalHistoryPO> medicalHistory = medicalHistoryMapper.selectByPatientId(patientId);
		// 添加（腹透史、血透史）等多条病史数据
		if (CollectionUtils.isNotEmpty(medicalHistory)) {
			addMedicalHistory(map, medicalHistory.get(0));
		}
		// 临床诊断
		List<ClinicalDiagnosisResultPO> clinicalDiagnosisResult = clinicalDiagnosisResultMapper.selectByPatientId(patientId);
		// 慢性肾功能衰竭
		List<CrfPO> crf = crfMapper.selectByPatientId(patientId);
		// 慢性肾功能不全急性加重
		List<SeriousCrfPO> seriousCrf = seriousCrfMapper.selectByPatientId(patientId);
		// 急性肾功能衰竭
		List<ArfPO> arf = arfMapper.selectByPatientId(patientId);
		// 病理诊断
		List<PathologicDiagnosisResultPO> pathologicDiagnosisResult = pathologicDiagnosisResultMapper.selectByPatientId(patientId);
		// CKD/AKI
		List<CkdStagePO> ckdStage = ckdStageMapper.selectByPatientId(patientId);
		// 治疗前合并症
		List<CureSymptomAndConditionPO> cureSymptomAndCondition = cureSymptomAndConditionMapper.selectByPatientId(patientId);
		// 其它诊断
		List<OtherDiagnosisResultPO> otherDiagnosisResult = otherDiagnosisResultMapper.selectByPatientId(patientId);

		map.put("patient", patient);
		map.put("patientDiagnosis", patientDiagnosis);
		map.put("medicalHistory", medicalHistory);
		map.put("clinicalDiagnosisResult", clinicalDiagnosisResult);
		map.put("crf", crf);
		map.put("seriousCrf", seriousCrf);
		map.put("arf", arf);
		map.put("ckdStage", ckdStage);
		map.put("pathologicDiagnosisResult", pathologicDiagnosisResult);
		map.put("cureSymptomAndCondition", cureSymptomAndCondition);
		map.put("otherDiagnosisResult", otherDiagnosisResult);
		return map;
	}
}
