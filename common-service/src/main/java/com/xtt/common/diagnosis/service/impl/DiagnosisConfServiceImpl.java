/**   
 * @Title: DiagnosisConfServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月6日 下午8:27:10 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.util.DataUtil;
import com.xtt.common.common.util.HttpServletUtil;
import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.mapper.CmDiagnosisConfMapper;
import com.xtt.common.dao.po.CmDiagnosisConfPO;
import com.xtt.common.diagnosis.service.IDiagnosisConfService;
import com.xtt.common.patient.service.IPatientDiagnosisValueService;

/**
 * @ClassName: DiagnosisConfServiceImpl
 * @date: 2016年7月6日 下午8:27:10
 * @version: V1.0
 *
 */
@Service
public class DiagnosisConfServiceImpl implements IDiagnosisConfService {

	@Autowired
	private CmDiagnosisConfMapper cmDiagnosisConfMapper;
	@Autowired
	private IPatientDiagnosisValueService patientDiagnosisValueService;

	@Override
	public List<CmDiagnosisConfPO> selectAll() {
		return selectByCondition(null);
	}

	@Override
	public List<CmDiagnosisConfPO> selectByCondition(CmDiagnosisConfPO record) {
		if (record == null) {
			record = new CmDiagnosisConfPO();
		}
		record.setFkTenantId(UserUtil.getTenantId());
		record.setSysOwner(HttpServletUtil.getProjectName());
		return cmDiagnosisConfMapper.selectByCondition(record);
	}

	@Override
	public Map<String, Object> saveConf(CmDiagnosisConfPO[] records) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (records != null && records.length > 0) {
			CmDiagnosisConfPO query = new CmDiagnosisConfPO();
			List<String> errorList = new ArrayList<String>();
			String sysOwer = HttpServletUtil.getProjectName();
			for (CmDiagnosisConfPO record : records) {
				if (record.getId() == null) {
					query.setItemCode(record.getItemCode());
					query.setSysOwner(sysOwer);
					List<CmDiagnosisConfPO> list = selectByCondition(query);
					if (list == null || list.isEmpty()) {// 检查itemCode是否已存在
						record.setSysOwner(HttpServletUtil.getProjectName());
						record.setFkTenantId(UserUtil.getTenantId());
						DataUtil.setSystemFieldValue(record);
						cmDiagnosisConfMapper.insert(record);
					} else {
						errorList.add(String.format("编号为'%s'的项目'%s'已存在", record.getItemCode(), record.getItemName()));
					}
				} else {
					record.setUpdateTime(new Date());
					record.setUpdateUserId(UserUtil.getLoginUserId());
					cmDiagnosisConfMapper.updateByPrimaryKeySelective(record);
				}
			}
			if (!errorList.isEmpty()) {
				resultMap.put("errorList", errorList);
			}
		}
		return resultMap;
	}

	@Override
	public List<CmDiagnosisConfPO> selectByItemCode(String itemCode) {
		CmDiagnosisConfPO record = new CmDiagnosisConfPO();
		record.setItemCode(itemCode);
		record.setSysOwner(HttpServletUtil.getProjectName());
		record.setFkTenantId(UserUtil.getTenantId());
		return selectByCondition(record);
	}

	@Override
	public Map<String, Object> delConf(CmDiagnosisConfPO[] records) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (records != null && records.length > 0) {
			List<String> errorList = new ArrayList<String>();
			for (CmDiagnosisConfPO record : records) {
				if (!patientDiagnosisValueService.selectByItemCode(record.getItemCode()).isEmpty()) {
					errorList.add(String.format("删除失败，项目'%s'已被使用", record.getItemName()));
				}
			}
			if (!errorList.isEmpty()) {// 如果存在已被使用项目，删除失败
				resultMap.put("errorList", errorList);
			} else {
				for (CmDiagnosisConfPO record : records) {
					this.cmDiagnosisConfMapper.deleteByPrimaryKey(record.getId());
				}
			}
		}
		return resultMap;
	}

}
