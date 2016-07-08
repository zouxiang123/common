/**   
 * @Title: IPatientAssayService.java 
 * @Package com.xtt.txgl.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月25日 下午3:54:11 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.po.PatientAssayResultPO;

public interface IPatientAssayResultService {
	/**
	 * 获取所有患者的检查结果
	 * 
	 * @Title: getAllAssayResult
	 * @return
	 *
	 */
	List<PatientAssayResultPO> getAllAssayResult();

	/**
	 * 获取患者的检查结果
	 * 
	 * @Title: getByPatientId
	 * @return
	 *
	 */
	PatientAssayResultPO getByPatientId(Long patientId);

	/**
	 * 保存数据
	 * 
	 * @Title: saveAssayResult
	 * @param record
	 *
	 */
	void saveAssayResult(PatientAssayResultPO record);
}
