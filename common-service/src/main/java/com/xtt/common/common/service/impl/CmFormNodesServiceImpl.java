/**   
 * @Title: CmFormNodesServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月22日 下午6:32:55 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ICmFormNodesService;
import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.mapper.CmFormNodesMapper;
import com.xtt.common.dao.po.CmFormNodes;

@Service
public class CmFormNodesServiceImpl implements ICmFormNodesService {
	@Autowired
	private CmFormNodesMapper cmFormNodesMapper;

	@Override
	public List<CmFormNodes> selectByCondition(CmFormNodes record) {
		record.setFkTenantId(UserUtil.getTenantId());
		return cmFormNodesMapper.selectByCondition(record);
	}

	@Override
	public List<CmFormNodes> selectByPItemCode(String itemCode) {
		CmFormNodes record = new CmFormNodes();
		record.setpItemCode(itemCode);
		record.setFkTenantId(UserUtil.getTenantId());
		return cmFormNodesMapper.selectByCondition(record);
	}

	@Override
	public List<CmFormNodes> selectByFormId(Long formId) {
		CmFormNodes record = new CmFormNodes();
		record.setFkFormId(formId);
		return cmFormNodesMapper.selectByCondition(record);
	}

	@Override
	public List<CmFormNodes> selectByRecordId(Long recordId, String sysOwner) {
		CmFormNodes record = new CmFormNodes();
		record.setFkRecordId(recordId);
		record.setSysOwner(sysOwner);
		return cmFormNodesMapper.selectByRecordId(record);
	}

}
