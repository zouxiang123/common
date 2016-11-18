/**   
 * @Title: DictDiagnosisService.java 
 * @Package com.xtt.pd.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月3日 下午2:44:54 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.conf.service.IDiagnosisConfService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDictDiagnosisMapper;
import com.xtt.common.dao.model.CmDictDiagnosis;
import com.xtt.common.dao.po.CmDiagnosisConfPO;
import com.xtt.common.dao.po.CmDictDiagnosisPO;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

/**
 * @ClassName: DictDiagnosisService
 * @date: 2016年7月3日 下午2:44:54
 * @version: V1.0
 *
 */
@Service
public class DictDiagnosisServiceImpl implements IDictDiagnosisService {

	@Autowired
	private CmDictDiagnosisMapper cmDictDiagnosisMapper;
	@Autowired
	private IDiagnosisConfService diagnosisConfService;

	@Override
	public List<CmDictDiagnosisPO> selectAll() {
		return selectByCondition(null);
	}

	@Override
	public List<CmDictDiagnosisPO> selectByCondition(CmDictDiagnosisPO record) {
		if (record == null) {
			record = new CmDictDiagnosisPO();
		}
		return cmDictDiagnosisMapper.selectByCondition(record);
	}

	@Override
	public CmDictDiagnosisPO selectByItemCode(String itemCode) {
		CmDictDiagnosisPO record = new CmDictDiagnosisPO();
		record.setItemCode(itemCode);
		List<CmDictDiagnosisPO> list = selectByCondition(record);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public String saveItem(CmDictDiagnosis record) {
		if (record.getId() == null) {
			if (selectByItemCode(record.getItemCode()) != null) {// 检查编号是否已存在
				return CommonConstants.WARNING;
			}
			DataUtil.setSystemFieldValue(record);
			cmDictDiagnosisMapper.insert(record);
		} else {
			CmDictDiagnosisPO parent = selectByItemCode(record.getpItemCode());
			if (parent.getIsLeaf() == null || parent.getIsLeaf()) {// 如果父节点是叶子节点，更新为非叶子节点
				parent.setIsLeaf(false);
				DataUtil.setSystemFieldValue(parent);
				cmDictDiagnosisMapper.updateByPrimaryKey(parent);
			}
			CmDictDiagnosis old = cmDictDiagnosisMapper.selectByPrimaryKey(record.getId());
			record.setCreateTime(old.getCreateTime());
			record.setCreateUserId(old.getCreateUserId());
			record.setUpdateTime(new Date());
			record.setUpdateUserId(UserUtil.getLoginUserId());
			cmDictDiagnosisMapper.updateByPrimaryKey(record);
		}
		return CommonConstants.SUCCESS;
	}

	@Override
	public String deleteByItemCode(String itemCode) {
		CmDictDiagnosisPO item = selectByItemCode(itemCode);
		if (item != null) {
			List<CmDiagnosisConfPO> confs = diagnosisConfService.selectByItemCode(itemCode);
			if (confs == null || confs.isEmpty()) {
				cmDictDiagnosisMapper.deleteByPrimaryKey(item.getId());
				return CommonConstants.SUCCESS;
			} else {
				return CommonConstants.WARNING;
			}
		} else {
			return CommonConstants.FAILURE;
		}
	}

}
