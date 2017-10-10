/**   
 * @Title: AssayHospDictGroupMappingServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月21日 上午11:49:17 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayHospDictGroupMappingService;
import com.xtt.common.dao.mapper.AssayHospDictGroupMappingMapper;
import com.xtt.common.dao.model.AssayHospDictGroupMapping;
import com.xtt.common.util.UserUtil;

@Service
public class AssayHospDictGroupMappingServiceImpl implements IAssayHospDictGroupMappingService {

    @Autowired
    private AssayHospDictGroupMappingMapper assayHospDictGroupMappingMapper;

    @Override
    public int insert(AssayHospDictGroupMapping record) {
        return assayHospDictGroupMappingMapper.insert(record);
    }

    @Override
    public void insertList(List<AssayHospDictGroupMapping> list) {
        assayHospDictGroupMappingMapper.insertList(list);

    }

    @Override
    public int getCountByGroupId(String fkGroupId, String fkItemCode) {
        return assayHospDictGroupMappingMapper.getCountByGroupId(fkGroupId, fkItemCode, UserUtil.getTenantId());
    }

    @Override
    public void deleteByTenant(Integer tenantId) {
        assayHospDictGroupMappingMapper.deleteByTenant(tenantId);

    }

    @Override
    public void deleteByGroupIdAndItemCode(String groupId, String itemCode, Integer fkTenantId) {
        assayHospDictGroupMappingMapper.deleteByGroupIdAndItemCode(groupId, itemCode, fkTenantId);

    }

}
