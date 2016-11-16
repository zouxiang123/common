/**   
 * @Title: PdItemsServiceImpl.java 
 * @Package com.xtt.pd.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年6月28日 下午5:10:03 
 *
 */
package com.xtt.common.form.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmFormBaseItemsMapper;
import com.xtt.common.dao.model.CmFormBaseItems;
import com.xtt.common.dao.po.CmFormBaseItemsPO;
import com.xtt.common.dao.po.CmFormItemsPO;
import com.xtt.common.form.service.ICmFormBaseItemsService;
import com.xtt.common.form.service.ICmFormItemsSerivce;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmFormBaseItemsServiceImpl implements ICmFormBaseItemsService {
	@Autowired
	private CmFormBaseItemsMapper cmFormBaseItemsMapper;
	@Autowired
	private ICmFormItemsSerivce cmFormConfSerivce;

	@Override
	public List<CmFormBaseItemsPO> selectAll() {
		return selectByCondition(null);
	}

	@Override
	public List<CmFormBaseItemsPO> selectByCondition(CmFormBaseItemsPO record) {
		if (record == null) {
			record = new CmFormBaseItemsPO();
		}
		record.setFkTenantId(UserUtil.getTenantId());
		return cmFormBaseItemsMapper.selectByCondition(record);
	}

	@Override
	public CmFormBaseItemsPO selectByItemCode(String itemCode, String sysOwner) {
		CmFormBaseItemsPO record = new CmFormBaseItemsPO();
		record.setItemCode(itemCode);
		record.setSysOwner(sysOwner);
		List<CmFormBaseItemsPO> list = selectByCondition(record);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public String saveItem(CmFormBaseItemsPO record) {
		record.setFkTenantId(UserUtil.getTenantId());
		if (record.getId() == null) {
			if (selectByItemCode(record.getItemCode(), record.getSysOwner()) != null) {// 检查编号是否已存在
				return CommonConstants.WARNING;
			}
			DataUtil.setSystemFieldValue(record);
			cmFormBaseItemsMapper.insert(record);
		} else {
			CmFormBaseItemsPO parent = selectByItemCode(record.getpItemCode(), record.getSysOwner());
			if (parent.getIsLeaf()) {// 如果父节点是叶子节点，更新为非叶子节点
				parent.setIsLeaf(false);
				cmFormBaseItemsMapper.updateByPrimaryKey(parent);
			}
			CmFormBaseItems old = cmFormBaseItemsMapper.selectByPrimaryKey(record.getId());
			record.setCreateTime(old.getCreateTime());
			record.setCreateUserId(old.getCreateUserId());
			record.setUpdateTime(new Date());
			record.setUpdateUserId(UserUtil.getLoginUserId());
			cmFormBaseItemsMapper.updateByPrimaryKey(record);
		}
		return CommonConstants.SUCCESS;
	}

	@Override
	public String deleteByItemCode(String itemCode, String sysOwner) {
		CmFormBaseItemsPO item = selectByItemCode(itemCode, sysOwner);
		if (item != null) {
			List<CmFormItemsPO> confs = cmFormConfSerivce.selectByItemCode(itemCode, sysOwner);
			if (confs == null || confs.isEmpty()) {
				cmFormBaseItemsMapper.deleteByPrimaryKey(item.getId());
				return CommonConstants.SUCCESS;
			} else {
				return CommonConstants.WARNING;
			}
		} else {
			return CommonConstants.FAILURE;
		}
	}
}
