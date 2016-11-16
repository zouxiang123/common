/**   
 * @Title: PatientAssayResultServiceImpl.java 
 * @Package com.xtt.txgl.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月25日 下午3:54:55 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.PatientAssayResultMapper;
import com.xtt.common.dao.model.PatientAssayResult;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.patient.service.IPatientAssayResultService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayResultServiceImpl implements IPatientAssayResultService {
	@Autowired
	private PatientAssayResultMapper patientAssayResultMapper;

	@Override
	public List<PatientAssayResultPO> getAllAssayResult() {
		PatientAssayResultPO record = new PatientAssayResultPO();
		record.setFkTenantId(UserUtil.getTenantId());
		record.setIsEnabled(true);
		return patientAssayResultMapper.selectByCondition(record);
	}

	@Override
	public PatientAssayResultPO getByPatientId(Long patientId) {
		PatientAssayResultPO record = new PatientAssayResultPO();
		record.setFkPatientId(patientId);
		record.setFkTenantId(UserUtil.getTenantId());
		record.setIsEnabled(true);
		List<PatientAssayResultPO> list = patientAssayResultMapper.selectByCondition(record);
		if (list == null || list.isEmpty())
			return null;
		return list.get(0);
	}

	@Override
	public void saveAssayResult(PatientAssayResultPO record) {
		if (record.getId() != null) {
			PatientAssayResult pre = patientAssayResultMapper.selectByPrimaryKey(record.getId());
			pre.setIsEnabled(false);
			DataUtil.setSystemFieldValue(pre);
			patientAssayResultMapper.updateByPrimaryKey(pre);
		}
		record.setId(null);
		record.setFkTenantId(UserUtil.getTenantId());
		record.setOperatorId(UserUtil.getLoginUserId());
		record.setIsEnabled(Boolean.TRUE);
		DataUtil.setSystemFieldValue(record);
		patientAssayResultMapper.insertSelective(record);

	}

}
