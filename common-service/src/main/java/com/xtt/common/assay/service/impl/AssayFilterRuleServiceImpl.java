/**   
 * @Title: AssayFilterRuleServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月30日 下午5:00:22 
 *
 */
package com.xtt.common.assay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayFilterRuleService;
import com.xtt.common.dao.mapper.AssayFilterRuleMapper;
import com.xtt.common.dao.model.AssayFilterRule;
import com.xtt.common.util.DataUtil;

@Service
public class AssayFilterRuleServiceImpl implements IAssayFilterRuleService {

    @Autowired
    private AssayFilterRuleMapper assayFilterRuleMapper;

    @Override
    public AssayFilterRule getByTenantId(Integer fkTenantId) {

        return assayFilterRuleMapper.getByTenantId(fkTenantId);
    }

    @Override
    public void updateByfkTenantId(AssayFilterRule assayFilterRule) {
        DataUtil.setUpdateSystemFieldValue(assayFilterRule);
        assayFilterRuleMapper.updateByPrimaryKeySelective(assayFilterRule);

    }

}
