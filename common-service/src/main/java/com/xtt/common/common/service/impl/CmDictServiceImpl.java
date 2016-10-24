/**   
 * @Title: CmDictServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午4:08:16 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ICmDictService;
import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.mapper.CmDictMapper;
import com.xtt.common.dao.po.CmDictPO;

@Service
public class CmDictServiceImpl implements ICmDictService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CmDictServiceImpl.class);

	@Autowired
	private CmDictMapper cmDictMapper;

	@Override
	public List<CmDictPO> selectAll() {
		CmDictPO CmDictPO = new CmDictPO();
		CmDictPO.setFkTenantId(UserUtil.getTenantId());
		CmDictPO.setIsEnable(true);
		return cmDictMapper.selectByCondition(CmDictPO);
	}

}
