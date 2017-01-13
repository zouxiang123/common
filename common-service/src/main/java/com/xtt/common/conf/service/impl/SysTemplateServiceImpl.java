/**   
 * @Title: SysTemplateServiceImpl.java 
 * @Package com.xtt.txgl.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月7日 上午11:55:24 
 *
 */
package com.xtt.common.conf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.conf.service.ISysTemplateChildService;
import com.xtt.common.conf.service.ISysTemplateService;
import com.xtt.common.dao.mapper.SysTemplateChildMapper;
import com.xtt.common.dao.mapper.SysTemplateMapper;
import com.xtt.common.dao.model.SysTemplate;
import com.xtt.common.dao.po.SysTemplateChildPO;
import com.xtt.common.dao.po.SysTemplatePO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

/**
 * @ClassName: SysTemplateServiceImpl
 * @date: 2016年4月7日 上午11:55:24
 * @version: V1.0
 * 
 */
@Service
public class SysTemplateServiceImpl implements ISysTemplateService {

	@Autowired
	private SysTemplateMapper sysTemplateMapper;
	@Autowired
	private SysTemplateChildMapper sysTemplateChildMapper;
	@Autowired
	private ISysTemplateChildService sysTemplateChildService;

	@Override
	public List<SysTemplatePO> selectByType(String type, boolean needChild, String sysOwner) {
		List<SysTemplatePO> list = sysTemplateMapper.selectByType(type, UserUtil.getTenantId(), sysOwner);
		if (CollectionUtils.isEmpty(list)) {
			return list;
		}
		if (needChild) {// if need child data
			List<Long> ids = new ArrayList<Long>();
			for (SysTemplatePO st : list) {
				ids.add(st.getId());
			}
			SysTemplateChildPO record = new SysTemplateChildPO();
			record.setFkSysTemplateIds(ids);
			List<SysTemplateChildPO> allChild = sysTemplateChildService.selectByCondition(record);
			for (SysTemplatePO st : list) {
				List<SysTemplateChildPO> childList = new ArrayList<SysTemplateChildPO>();
				for (SysTemplateChildPO c : allChild) {
					if (c.getFkSysTemplateId().equals(st.getId())) {
						childList.add(c);
					}
				}
				st.setChildList(childList);
			}
		}
		return list;
	}

	@Override
	public List<SysTemplate> selectTemplateType(String sysOwner) {
		return sysTemplateMapper.selectTemplateType(UserUtil.getTenantId(), sysOwner);
	}

	@Override
	public int saveTemplate(SysTemplatePO record) {
		int count = 0;
		DataUtil.setSystemFieldValue(record);
		record.setFkTenantId(UserUtil.getTenantId());
		if (record.getId() == null) {
			record.setCount(0);
			sysTemplateMapper.insert(record);
		} else {
			record.setCreateTime(null);
			record.setCreateUserId(null);
			// 删除关联的子表数据
			sysTemplateChildMapper.deleteBySysTemplateId(record.getId());
			count = sysTemplateMapper.updateByPrimaryKeySelective(record);
		}
		// 插入子表数据
		if (CollectionUtils.isNotEmpty(record.getChildList())) {
			for (SysTemplateChildPO stc : record.getChildList()) {
				if (StringUtils.isBlank(stc.getContent()))
					continue;
				stc.setId(null);
				stc.setFkSysTemplateId(record.getId());
				stc.setSysOwner(record.getSysOwner());
				stc.setFkTenantId(UserUtil.getTenantId());
				DataUtil.setSystemFieldValue(stc);
				sysTemplateChildMapper.insert(stc);
			}
		}
		return count;
	}

	@Override
	public int deleteTemplate(SysTemplate record) {
		DataUtil.setSystemFieldValue(record);
		record.setCreateTime(null);
		record.setCreateUserId(null);
		record.setDelFlag(true);
		return sysTemplateMapper.updateByPrimaryKeySelective(record);
	}

}
