/**   
 * @Title: OutcomeServiceImpl.java 
 * @Package com.xtt.txgl.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年9月29日 上午10:43:55 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.cache.PatientCache;
import com.xtt.common.cache.UserCache;
import com.xtt.common.constants.CmDictConstants;
import com.xtt.common.dao.mapper.PatientOutcomeMapper;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.dao.po.PatientOutcomePO;
import com.xtt.common.patient.service.IPatientOutcomeService;
import com.xtt.common.patient.service.IPatientOwnerService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.CmDictUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientOutcomeServiceImpl implements IPatientOutcomeService {

	@Autowired
	private PatientOutcomeMapper patientOutcomeMapper;
	@Autowired
	private IPatientService patientService;
	@Autowired
	private IPatientOwnerService patientOwnerService;

	@Override
	public void save(PatientOutcomePO record) {
		Map<String, String> sysOwners = CmDictUtil.getNamesByType(CmDictConstants.SYS_OWNER);
		Patient patient = new Patient();
		if (sysOwners.containsKey(record.getType())) {
			// 如果是转回当前系统或者转到其他系统
			patient.setId(record.getFkPatientId());
			patient.setSysOwner(record.getType());
			patient.setFkTenantId(record.getFkTenantId());
			patient.setDelFlag(false);
			updatePatient(patient);
		} else {
			// 其它类别转归
			patient.setId(record.getFkPatientId());
			patient.setSysOwner(record.getSysOwner());
			patient.setFkTenantId(record.getFkTenantId());
			patient.setDelFlag(true);
			updatePatient(patient);
		}
		record.setFkTenantId(UserUtil.getTenantId());
		DataUtil.setSystemFieldValue(record);
		patientOutcomeMapper.insert(record);
	}

	/**
	 * 更新患者数据
	 * 
	 * @Title: updatePatient
	 * @param patient
	 *
	 */
	private void updatePatient(Patient patient) {
		patientService.updateByPrimaryKeySelective(patient);
		PatientOwner owner = new PatientOwner();
		owner.setFkPatientId(patient.getId());
		owner.setSysOwner(patient.getSysOwner());
		owner.setIsEnable(!patient.getDelFlag());
		owner.setFkTenantId(patient.getFkTenantId());
		// 更新患者所属系统信息
		patientOwnerService.updateOwner(owner);
	}

	@Override
	public List<PatientOutcomePO> selectAllByPatientId(Long patientId) {
		PatientOutcomePO record = new PatientOutcomePO();
		record.setFkPatientId(patientId);
		return selectByCondition(record);
	}

	@Override
	public List<PatientOutcomePO> selectByCondition(PatientOutcomePO record) {
		record = record == null ? new PatientOutcomePO() : record;
		record.setFkTenantId(UserUtil.getTenantId());
		return init(patientOutcomeMapper.selectByCondition(record));
	}

	/** 初始化显示 */
	private List<PatientOutcomePO> init(List<PatientOutcomePO> list) {
		if (CollectionUtils.isNotEmpty(list)) {
			Map<String, String> typesMap = CmDictUtil.getNamesByType(CmDictConstants.PATIENT_OUTCOME_TYPE);
			for (PatientOutcomePO po : list) {
				po.setTypeShow(typesMap.get(po.getType()));
				po.setPatientName(PatientCache.getById(po.getFkPatientId()).getName());
				po.setCreateUserName(UserCache.getById(po.getCreateUserId()).getName());
			}
		}
		return list;
	}
}
