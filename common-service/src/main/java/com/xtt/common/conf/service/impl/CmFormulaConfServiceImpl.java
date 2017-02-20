/**   
 * @Title: CmFormulaConfServiceImpl.java 
 * @Package com.xtt.common.conf.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年12月10日 上午9:50:36 
 *
 */
package com.xtt.common.conf.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.conf.service.ICmFormulaConfService;
import com.xtt.common.dao.mapper.CmFormulaConfMapper;
import com.xtt.common.dao.po.CmFormulaConfPO;
import com.xtt.common.util.UserUtil;

@Service
public class CmFormulaConfServiceImpl implements ICmFormulaConfService {

    @Autowired
    private CmFormulaConfMapper cmFormulaConfMapper;

    @Override
    public List<CmFormulaConfPO> selectByCondition(CmFormulaConfPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return cmFormulaConfMapper.selectByCondition(record);
    }

    @Override
    public void updateByPrimaryKeySelective(List<CmFormulaConfPO> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (CmFormulaConfPO record : list) {
                record.setUpdateTime(new Date());
                record.setUpdateUserId(UserUtil.getLoginUserId());
                cmFormulaConfMapper.updateByPrimaryKeySelective(record);
            }
        }
    }

}
