/**   
 * @Title: DictHospitalLabServiceImpl.java 
 * @Package com.xtt.txgl.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月22日 下午12:54:39 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.DictHospitalLabMapper;
import com.xtt.common.dao.model.DictHospitalLab;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.patient.service.IDictHospitalLabService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class DictHospitalLabServiceImpl implements IDictHospitalLabService {
	@Autowired
	private DictHospitalLabMapper dictHospitalLabMapper;

	@Override
	public DictHospitalLabPO getByItemCode(String itemCode) {
		DictHospitalLabPO query = new DictHospitalLabPO();
		query.setItemCode(itemCode);
		query.setFkTenantId(UserUtil.getTenantId());
		List<DictHospitalLabPO> list = dictHospitalLabMapper.selectByCondition(query);
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

	@Override
	public List<DictHospitalLabPO> getAllCategory(DictHospitalLabPO record) {

		return dictHospitalLabMapper.selectAllCategory(record);
	}

	@Override
	public List<DictHospitalLabPO> getByCondition(DictHospitalLabPO record) {
		record.setFkTenantId(UserUtil.getTenantId());
		return dictHospitalLabMapper.selectByCondition(record);
	}

	@Override
	public void deleteAssayMapping(Long id) {
		dictHospitalLabMapper.delAssayMapping(id);
	}

	@Override
	public String updateDictById(DictHospitalLab record) {
		if (StringUtils.isNotBlank(record.getFkDictCode())) {
			// 检查是否已关联
			DictHospitalLabPO query = new DictHospitalLabPO();
			query.setFkTenantId(UserUtil.getTenantId());
			query.setFkDictCode(record.getFkDictCode());
			List<DictHospitalLabPO> list = this.getByCondition(query);
			if (list != null && !list.isEmpty())
				return CommonConstants.WARNING;
		}
		record.setFkTenantId(UserUtil.getTenantId());
		record.setUpdateTime(new Date());
		record.setUpdateUserId(UserUtil.getLoginUserId());
		dictHospitalLabMapper.updateByPrimaryKeySelective(record);
		return CommonConstants.SUCCESS;
	}

	/**
	 * 通过ItemCode来查询isTop，dataType，maxValue，minValue
	 */
	@Override
	public List<DictHospitalLabPO> selectAllByItemCode(String itemCode) {
		return dictHospitalLabMapper.selectAllByItemCode(itemCode);
	}

	@Override
	public List<String> selectAllAssayMonth(DictHospitalLab dictHospitalLab) {
		List<String> list = dictHospitalLabMapper.selectAllAssayMonth(dictHospitalLab);
		if (list == null) {
			list = new ArrayList<String>();
		}
		return list;
	}

	/**
	 * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
	 */
	@Override
	public void updateDictHospitalLabSomeValue(DictHospitalLab record) {
		DictHospitalLab newRecord = dictHospitalLabMapper.selectByPrimaryKey(record.getId());
		DataUtil.setSystemFieldValue(newRecord);
		newRecord.setPersonalMaxValue(record.getPersonalMaxValue());
		newRecord.setPersonalMinValue(record.getPersonalMinValue());
		newRecord.setIsTop(record.getIsTop());
		dictHospitalLabMapper.updateByPrimaryKey(newRecord);
	}
}
