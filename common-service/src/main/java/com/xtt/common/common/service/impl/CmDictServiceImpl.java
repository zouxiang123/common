/**   
 * @Title: CmDictServiceImpl.java 
 * @Package com.xtt.common.common.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年9月23日 下午4:08:16 
 *
 */
package com.xtt.common.common.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ICmDictService;
import com.xtt.common.dao.mapper.CmDictMapper;
import com.xtt.common.dao.model.CmDict;
import com.xtt.common.dao.po.CmDictPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.model.MybatisOrderByModel;

@Service
public class CmDictServiceImpl implements ICmDictService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmDictServiceImpl.class);

    @Autowired
    private CmDictMapper cmDictMapper;

    @Override
    public List<CmDictPO> selectAll() {
        CmDictPO cmDictPO = new CmDictPO();
        cmDictPO.setFkTenantId(UserUtil.getTenantId());
        cmDictPO.setIsEnable(true);
        cmDictPO.addOrderBy(new MybatisOrderByModel("orderBy"));// 添加根据orderby的排序
        return cmDictMapper.selectByCondition(cmDictPO);
    }

    @Override
    public List<CmDictPO> getByCondition(CmDictPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return cmDictMapper.selectByCondition(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return cmDictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CmDictPO> selectByType(String itemCode) {
        CmDictPO record = new CmDictPO();
        record.setFkTenantId(UserUtil.getTenantId());
        record.setpItemCode(itemCode);
        return getByCondition(record);
    }

    @Override
    public void updateDictionary(CmDict record) {
        if (record.getId() == null) {
            record.setOperatorId(UserUtil.getLoginUserId());
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDictMapper.insertSelective(record);
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDictMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public CmDict getById(Long id) {
        return cmDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CmDictPO> getDictCategory(CmDictPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return cmDictMapper.selectDictCategory(record);
    }

}
