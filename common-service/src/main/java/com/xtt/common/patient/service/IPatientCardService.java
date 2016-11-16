/**   
 * @Title: IPatientCardService.java 
 * @Package com.xtt.txgl.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月17日 下午2:30:29 
 *
 */
package com.xtt.common.patient.service;

import java.util.List;

import com.xtt.common.dao.po.PatientCardPO;

public interface IPatientCardService {
	/**
	 * 根据条件查询患者卡号
	 * 
	 * @Title: selectByCardNo
	 * @param ptCard
	 * @return
	 *
	 */
	List<PatientCardPO> selectByCardNo(PatientCardPO ptCard);

	String savePatientCard(List<PatientCardPO> newPatientCardList);

	/**
	 * 根据患者id查询或者卡号
	 * 
	 * @Title: selectByPatientId
	 * @param id
	 * @return
	 *
	 */
	List<PatientCardPO> selectByPatientId(Long patientId);

}
