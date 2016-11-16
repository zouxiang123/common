/**   
 * @Title: CmFormServiceImpl.java 
 * @Package com.xtt.common.form.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月29日 下午4:26:17 
 *
 */
package com.xtt.common.form.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmFormMapper;
import com.xtt.common.dao.po.CmFormPO;
import com.xtt.common.form.service.ICmFormItemsSerivce;
import com.xtt.common.form.service.ICmFormService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class CmFormServiceImpl implements ICmFormService {
	@Autowired
	private CmFormMapper cmFormMapper;
	@Autowired
	private ICmFormItemsSerivce cmFormConfSerivce;

	@Override
	public List<CmFormPO> selectByCategory(String category, String sysOwner) {
		if (StringUtil.isBlank(category)) {
			return new ArrayList<>();
		}
		CmFormPO record = new CmFormPO();
		record.setCategory(category);
		record.setSysOwner(sysOwner);
		return selectByCondition(record);
	}

	@Override
	public List<CmFormPO> selectByCondition(CmFormPO record) {
		record.setFkTenantId(UserUtil.getTenantId());
		return cmFormMapper.selectByCondition(record);
	}

	@Override
	public Integer selectMaxVersion(String category, String sysOwner) {
		CmFormPO record = new CmFormPO();
		record.setCategory(category);
		record.setSysOwner(sysOwner);
		record.setFkTenantId(UserUtil.getTenantId());
		return cmFormMapper.selectMaxVersion(record);
	}

	@Override
	public CmFormPO selectById(Long id) {
		return cmFormMapper.selectById(id);
	}

	@Override
	public void updateIsNew(String category, String sysOwner) {
		cmFormMapper.updateIsNew(category, sysOwner, UserUtil.getTenantId());
	}

	@Override
	public void insert(CmFormPO record) {
		record.setFkTenantId(UserUtil.getTenantId());
		DataUtil.setSystemFieldValue(record);
		cmFormMapper.insert(record);
	}

	@Override
	public String delById(Long id) {
		cmFormConfSerivce.deleteByFormId(id);
		cmFormMapper.deleteByPrimaryKey(id);
		return CommonConstants.SUCCESS;
	}

}
