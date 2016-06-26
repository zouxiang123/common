/**   
 * @Title: SysParamServiceImpl.java 
 * @Package com.xtt.txgl.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年11月3日 下午3:19:56 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ISysParamService;
import com.xtt.common.dao.mapper.SysParamMapper;
import com.xtt.common.dao.model.SysParam;
import com.xtt.common.dao.po.SysParamPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.constants.CommonConstants;
import com.xtt.common.util.user.UserUtil;

@Service
public class SysParamServiceImpl implements ISysParamService {
	@Autowired
	private SysParamMapper sysParamMapper;

	@Override
	public SysParam getByName(String name) {
		return sysParamMapper.selectByName(name, UserUtil.getTenantId());
	}

	@Override
	public SysParam getByName(String name, Integer tenantId) {
		return sysParamMapper.selectByName(name, tenantId);
	}

	@Override
	public List<SysParamPO> getByTenantId(Integer tenantId) {
		String projectName = HttpServletUtil.getContextAttrByName(CommonConstants.PROJECT_NAME).toString();
		return sysParamMapper.selectByTenantId(tenantId);
	}

	@Override
	public String saveParam(SysParam param) {
		if (param.getId() == null) {
			if (StringUtils.isNotBlank(SysParamUtil.getValueByName(param.getParamName()))) {
				return CommonConstants.WARNING;
			}
			param.setFkTenantId(UserUtil.getTenantId());
			DataUtil.setSystemFieldValue(param);
			sysParamMapper.insert(param);
		} else {
			param.setUpdateUserId(UserUtil.getLoginUserId());
			param.setUpdateTime(new Date());
			sysParamMapper.updateByPrimaryKeySelective(param);
		}
		return CommonConstants.SUCCESS;
	}
}
