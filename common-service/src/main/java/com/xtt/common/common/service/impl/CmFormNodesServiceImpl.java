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
import com.xtt.common.dao.mapper.CmFormNodesMapper;
import com.xtt.common.dto.FormNodesDto;
import com.xtt.common.util.UserUtil;

@Service
public class CmFormNodesServiceImpl implements ICmFormNodesService {
    @Autowired
    private CmFormNodesMapper cmFormNodesMapper;

    @Override
    public List<FormNodesDto> selectByCondition(FormNodesDto record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return cmFormNodesMapper.selectByCondition(record);
    }

    @Override
    public List<FormNodesDto> selectByPItemCode(String itemCode) {
        FormNodesDto record = new FormNodesDto();
        record.setpItemCode(itemCode);
        record.setFkTenantId(UserUtil.getTenantId());
        return cmFormNodesMapper.selectByCondition(record);
    }

    @Override
    public List<FormNodesDto> selectByFormId(Long formId) {
        FormNodesDto record = new FormNodesDto();
        record.setFkFormId(formId);
        return cmFormNodesMapper.selectByCondition(record);
    }

}
