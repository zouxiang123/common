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

import org.apache.commons.collections4.CollectionUtils;
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
            record.setChildValueNode(false);
            cmFormBaseItemsMapper.insert(record);
            CmFormBaseItemsPO parent = selectByItemCode(record.getpItemCode(), record.getSysOwner());
            // 如果父节点是叶子节点，更新为非叶子节点
            if (parent.getIsLeaf()) {
                parent.setIsLeaf(false);
                // 如果父节点是叶子节点，而且父节点的父节点标识“子节点全是value节点标识”为true，则更新其为false
                CmFormBaseItemsPO topParent = selectByItemCode(parent.getpItemCode(), parent.getSysOwner());
                if (topParent.getChildValueNode()) {
                    topParent.setChildValueNode(false);
                    cmFormBaseItemsMapper.updateByPrimaryKey(topParent);
                }
            }
            // 如果父节点的子节点全是叶子节点，则更新“子节点全是value节点标识”为true
            CmFormBaseItemsPO query = new CmFormBaseItemsPO();
            query.setpItemCode(parent.getItemCode());
            query.setSysOwner(parent.getSysOwner());
            List<CmFormBaseItemsPO> items = selectByCondition(query);
            boolean allChildIsleaf = true;// 所有的子节点是否为叶子节点
            if (CollectionUtils.isNotEmpty(items)) {
                for (CmFormBaseItemsPO item : items) {
                    if (!item.getIsLeaf()) {
                        allChildIsleaf = false;
                        break;
                    }
                }
            }
            parent.setChildValueNode(allChildIsleaf);
            cmFormBaseItemsMapper.updateByPrimaryKey(parent);
        } else {
            CmFormBaseItems old = cmFormBaseItemsMapper.selectByPrimaryKey(record.getId());
            record.setChildValueNode(old.getChildValueNode());
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
