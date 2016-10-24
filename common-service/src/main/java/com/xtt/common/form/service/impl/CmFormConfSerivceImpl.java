/**   
 * @Title: FollowUpConfSerivceImpl.java 
 * @Package com.xtt.pd.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 下午4:56:51 
 *
 */
package com.xtt.common.form.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.util.DataUtil;
import com.xtt.common.common.util.UserUtil;
import com.xtt.common.dao.mapper.CmFormItemsMapper;
import com.xtt.common.dao.po.CmFormItemsPO;
import com.xtt.common.dao.po.CmFormPO;
import com.xtt.common.form.service.ICmFormItemsSerivce;
import com.xtt.common.form.service.ICmFormService;
import com.xtt.common.form.service.ICmFormValueService;

@Service
public class CmFormConfSerivceImpl implements ICmFormItemsSerivce {
	@Autowired
	private CmFormItemsMapper cmFormConfMapper;
	@Autowired
	private ICmFormService cmFormService;
	@Autowired
	private ICmFormValueService cmFormValueService;

	@Override
	public List<CmFormItemsPO> selectAll() {
		return selectByCondition(null);
	}

	/** 根据条件查询表单配置数据 */
	@Override
	public List<CmFormItemsPO> selectByCondition(CmFormItemsPO record) {
		if (record == null) {
			record = new CmFormItemsPO();
		}
		record.setFkTenantId(UserUtil.getTenantId());
		return cmFormConfMapper.selectByCondition(record);
	}

	@Override
	public Map<String, Object> saveConf(CmFormItemsPO[] records, String formName, Long formId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (records != null && records.length > 0) {
			String category = records[0].getCategory();
			String sysOwner = records[0].getSysOwner();
			// 更新主表中isnew标识，并插入新的
			cmFormService.updateIsNew(category, sysOwner);
			Integer version = cmFormService.selectMaxVersion(category, sysOwner);
			version = version == null ? 0 : version + 1;
			// 插入新的表单记录
			CmFormPO form = new CmFormPO();
			form.setFormName(formName);
			form.setVersion(version);
			form.setCategory(category);
			form.setSysOwner(sysOwner);
			form.setVersion(version);
			form.setIsNew(true);
			cmFormService.insert(form);

			int tenantId = UserUtil.getTenantId();
			for (CmFormItemsPO record : records) {
				if (record.getId() != null) {
					record.setId(null);
				}
				record.setFkFormId(form.getId());
				record.setFkTenantId(tenantId);
				DataUtil.setSystemFieldValue(record);
				cmFormConfMapper.insert(record);
			}
		}
		return resultMap;
	}

	@Override
	public List<CmFormItemsPO> selectByItemCode(String itemCode) {
		CmFormItemsPO record = new CmFormItemsPO();
		record.setItemCode(itemCode);
		return selectByCondition(record);
	}

	@Override
	public Map<String, Object> deleteConf(CmFormItemsPO[] records) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (records != null && records.length > 0) {
			List<String> errorList = new ArrayList<String>();
			for (CmFormItemsPO record : records) {
				if (CollectionUtils.isNotEmpty(cmFormValueService.selectByItemCode(record.getItemCode(), record.getFkFormId()))) {
					errorList.add(String.format("删除失败，项目'%s'已被使用", record.getItemName()));
				}
			}
			if (!errorList.isEmpty()) {// 如果存在已被使用项目，删除失败
				resultMap.put("errorList", errorList);
			} else {
				for (CmFormItemsPO record : records) {
					this.cmFormConfMapper.deleteByPrimaryKey(record.getId());
				}
			}
		}
		return resultMap;
	}

	@Override
	public void deleteByFormId(Long id) {
		cmFormConfMapper.deleteByFormId(id);
	}

}
