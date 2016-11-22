/**   
 * @Title: IPatientDiagnosis.java 
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月30日 下午12:30:11 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.model.CkdStage;
import com.xtt.common.dao.model.CureSymptomAndCondition;
import com.xtt.common.dao.model.MedicalHistory;
import com.xtt.common.dao.model.OtherDiagnosisResult;
import com.xtt.common.dao.model.PathologicDiagnosisResult;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientDiagnosis;
import com.xtt.common.dao.po.ArfPO;
import com.xtt.common.dao.po.CkdStagePO;
import com.xtt.common.dao.po.ClinicalDiagnosisResultPO;
import com.xtt.common.dao.po.CrfPO;
import com.xtt.common.dao.po.MedicalHistoryRemarkPO;
import com.xtt.common.dao.po.SeriousCrfPO;

public interface IPatientDiagnosisService {

	/**
	 * 根据患者ID查询处于草稿状态的诊断信息
	 * 
	 * @Title: getPatientDiagnosisByPatientId
	 * @param patientId
	 * @return
	 * 
	 */
	public PatientDiagnosis getPatientDiagnosisByPatientId(Long patientId);

	/**
	 * 根据诊断信息ID查询诊断信息
	 * 
	 * @Title: getPatientDiagnosisByPDId
	 * @param pdId
	 * @return
	 * 
	 */
	public PatientDiagnosis selectById(Long pdId);

	public Integer countByPatientId(Long patientId, Integer tenantId);

	/**
	 * 获取临床诊断数据
	 * 
	 * @Title: getClinicalDiagnosisResultByPatientId
	 * @param patientId
	 * @return
	 *
	 */
	public List<ClinicalDiagnosisResultPO> getClinicalDiagnosisResultByPatientId(Long patientId);

	/**
	 * 获取CRF(慢性肾功能衰竭)
	 * 
	 * @Title: getCkdStageByPatientId
	 * @param patientId
	 * @return
	 *
	 */
	public List<CrfPO> getCrfByPatientId(Long patientId);

	/**
	 * 获取ARF(急性肾功能衰竭 )
	 * 
	 * @Title: getCkdStageByPatientId
	 * @param patientId
	 * @return
	 *
	 */
	public List<ArfPO> getArfByPatientId(Long patientId);

	/**
	 * 获取CKD/AKI分期数据
	 * 
	 * @Title: getCkdStageByPatientId
	 * @param patientId
	 * @return
	 *
	 */
	public List<CkdStagePO> getCkdStageByPatientId(Long patientId);

	/**
	 * 查询患者最新一次的诊断数据
	 * 
	 * @Title: getLastestByPatientId
	 * @param patientId
	 * @return
	 *
	 */
	public Map<String, Object> getLastestByPatientId(Long patientId);

	/**
	 * 获取诊断id查询所有已诊断过的数据
	 * 
	 * @Title: selectAllDiagnosisInfo
	 * @param fkPatientDiagnosisId
	 * @return
	 * 
	 */
	public Map<String, Object> selectAllDiagnosisInfo(Long fkPatientDiagnosisId);

	/**
	 * 保存患者信息 ，首次创建患者，自动开启一次透析活动
	 * 
	 * @Title: saveMedicalHistory
	 * @param Patient
	 * @param isImport是否为导入操作
	 * @return Long 透析活动ID
	 * 
	 */
	public Long savePatient(Patient patient, boolean isImport);

	/**
	 * 保存病史询问记录
	 * 
	 * @Title: saveMedicalHistory
	 * @param MedicalHistory
	 * 
	 */
	public Long saveMedicalHistory(MedicalHistory mh) throws Exception;

	/**
	 * @Title: saveMedicalHistory
	 * @param mh
	 * @param mePo
	 * @return 多表关联保存病史询问记录
	 * @throws Exception
	 *
	 */
	public Long saveMedicalHistory(MedicalHistory mh, MedicalHistoryRemarkPO mePo) throws Exception;

	/**
	 * 保存临床诊断（CRF:慢性肾功能衰竭）
	 * 
	 * @Title: saveCrf
	 * @param CrfPO
	 * 
	 */
	public Long saveCrf(CrfPO model) throws Exception;

	/**
	 * 保存临床诊断（ARF:急性肾功能衰竭）
	 * 
	 * @Title: saveArf
	 * @param ArfPO
	 * 
	 */
	public Long saveArf(ArfPO model) throws Exception;

	/**
	 * 保存临床诊断（Serious CRF:慢性肾功能不全急性加重）
	 * 
	 * @Title: saveSeriousCrf
	 * @param SeriousCrfPO
	 * 
	 */
	public Long saveSeriousCrf(SeriousCrfPO model) throws Exception;

	/**
	 * 保存病理诊断
	 * 
	 * @Title: savePathologicDiagnosis
	 * @param PathologicDiagnosisResult
	 * @return
	 * @throws Exception
	 * 
	 */
	public Long savePathologicDiagnosis(PathologicDiagnosisResult model);

	/**
	 * 保存CKD/AKI
	 * 
	 * @Title: saveCkdStage
	 * @param CkdStage
	 * @return
	 * 
	 */
	public Long saveCkdStage(CkdStage model);

	/**
	 * 保存治疗前并发症
	 * 
	 * @Title: saveCureComplication
	 * @param CureSymptomAndCondition
	 * @return
	 * 
	 */
	public Long saveCureComplication(CureSymptomAndCondition model);

	/**
	 * 保存其它诊断
	 * 
	 * @Title: saveOtherDiagnosis
	 * @param OtherDiagnosis
	 * @return
	 * @throws Exception
	 * 
	 */
	public Long saveOtherDiagnosis(OtherDiagnosisResult model);

	/**
	 * 首次诊断基本信息完成
	 * 
	 * @Title: 首次诊断基本信息完成
	 * 
	 */
	public void saveFirstDone(Long fkPatientDiagnosisId);

	/**
	 * 获取当前患者所有的诊断信息
	 * 
	 * @Title: selectDignosisByPatient
	 * @param patientId
	 * @return
	 *
	 */
	public Map<String, Object> selectDignosisByPatient(Long patientId);
}
