/**   
 * @Title: IPatientDiagnosis.java 
 * @Package com.xtt.txgl.doctor.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月30日 下午12:30:11 
 *
 */
package com.xtt.common.patient.service;

import java.util.Map;

import com.xtt.common.dao.model.PatientDiagnosis;

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
	public PatientDiagnosis getPatientDiagnosisByPDId(Long pdId);

	public Integer countByPatientId(Long patientId, Integer tenantId);

	/**
	 * 查询患者最新一次的诊断数据
	 * 
	 * @Title: getLastestByPatientId
	 * @param patientId
	 * @return
	 *
	 */
	public Map<String, Object> getLastestByPatientId(Long patientId);
}
