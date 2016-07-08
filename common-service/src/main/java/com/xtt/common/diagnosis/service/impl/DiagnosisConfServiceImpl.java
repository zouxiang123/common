/**   
 * @Title: DiagnosisConfServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月6日 下午8:27:10 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xtt.common.dao.po.CmDiagnosisConfPO;
import com.xtt.common.diagnosis.service.IDiagnosisConfService;

/**
 * @ClassName: DiagnosisConfServiceImpl
 * @date: 2016年7月6日 下午8:27:10
 * @version: V1.0
 *
 */
@Service
public class DiagnosisConfServiceImpl implements IDiagnosisConfService {

	@Override
	public List<CmDiagnosisConfPO> selectAll() {
		return null;
	}

	@Override
	public Map<String, Object> saveConf(CmDiagnosisConfPO[] records) {
		return null;
	}

	@Override
	public List<CmDiagnosisConfPO> selectByItemCode(String itemCode) {
		return null;
	}

	@Override
	public Map<String, Object> delConf(CmDiagnosisConfPO[] records) {
		return null;
	}

	@Override
	public List<CmDiagnosisConfPO> selectByCondition(CmDiagnosisConfPO record) {
		return null;
	}

}
