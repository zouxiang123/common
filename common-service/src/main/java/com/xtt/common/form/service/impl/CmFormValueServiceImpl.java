/**   
 * @Title: CmFormValueServiceImpl.java 
 * @Package com.xtt.common.form.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月29日 上午9:00:44 
 *
 */
package com.xtt.common.form.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.mapper.CmFormValueMapper;
import com.xtt.common.dao.po.CmFormValuePO;
import com.xtt.common.form.service.ICmFormValueService;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class CmFormValueServiceImpl implements ICmFormValueService {
	@Autowired
	private CmFormValueMapper cmFormValueMapper;

	@Override
	public List<CmFormValuePO> selectByItemCode(String itemCode, Long formId) {
		if (StringUtil.isBlank(itemCode)) {
			return new ArrayList<>();
		}
		CmFormValuePO record = new CmFormValuePO();
		record.setItemCode(itemCode);
		record.setFkFormId(formId);
		return selectByCondition(record);
	}

	@Override
	public List<CmFormValuePO> selectByFormId(Long formId) {
		if (formId == null) {
			return new ArrayList<>();
		}
		CmFormValuePO record = new CmFormValuePO();
		record.setFkFormId(formId);
		return selectByCondition(record);
	}

	@Override
	public List<CmFormValuePO> selectByCondition(CmFormValuePO record) {
		record.setFkTenantId(UserUtil.getTenantId());
		return cmFormValueMapper.selectByCondition(record);
	}

	@Override
	public void batchInsert(Collection<CmFormValuePO> records) {
		for (CmFormValuePO record : records) {
			cmFormValueMapper.insert(record);
		}
	}

	@Override
	public void deleteByRecordId(Long recordId, String sysOwner) {
		cmFormValueMapper.deleteByRecordId(recordId, sysOwner);
	}

}
