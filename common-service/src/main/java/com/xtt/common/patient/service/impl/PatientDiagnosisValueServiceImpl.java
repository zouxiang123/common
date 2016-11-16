/**   
 * @Title: PatientDiagnosisValueService.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月8日 下午3:09:09 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.CmPatientDiagnosisValueMapper;
import com.xtt.common.dao.po.CmPatientDiagnosisValuePO;
import com.xtt.common.patient.service.IPatientDiagnosisValueService;
import com.xtt.common.util.UserUtil;

/**
 * @ClassName: PatientDiagnosisValueService
 * @date: 2016年7月8日 下午3:09:09
 * @version: V1.0
 *
 */
@Service
public class PatientDiagnosisValueServiceImpl implements IPatientDiagnosisValueService {

	@Autowired
	private CmPatientDiagnosisValueMapper cmPatientDiagnosisValueMapper;

	@Override
	public List<CmPatientDiagnosisValuePO> selectByItemCode(String itemCode) {
		CmPatientDiagnosisValuePO record = new CmPatientDiagnosisValuePO();
		record.setItemCode(itemCode);
		return selectByCondition(record);
	}

	private List<CmPatientDiagnosisValuePO> selectByCondition(CmPatientDiagnosisValuePO record) {
		if (record == null)
			record = new CmPatientDiagnosisValuePO();
		record.setFkTenantId(UserUtil.getTenantId());
		List<CmPatientDiagnosisValuePO> results = cmPatientDiagnosisValueMapper.selectByCondition(record);
		return results == null ? new ArrayList<CmPatientDiagnosisValuePO>() : results;
	}

}
