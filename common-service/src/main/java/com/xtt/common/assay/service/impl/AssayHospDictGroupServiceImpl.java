/**   
 * @Title: AssayHospDictGroupServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月21日 上午11:47:48 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayHospDictGroupService;
import com.xtt.common.dao.mapper.AssayHospDictGroupMapper;
import com.xtt.common.dao.model.AssayHospDictGroup;
import com.xtt.common.util.UserUtil;

@Service
public class AssayHospDictGroupServiceImpl implements IAssayHospDictGroupService {

    @Autowired
    private AssayHospDictGroupMapper assayHospDictGroupMapper;

    @Override
    public int getCountByGroupId(String groupId) {
        return assayHospDictGroupMapper.getCountByGroupId(groupId, UserUtil.getTenantId());
    }

    @Override
    public int insert(AssayHospDictGroup record) {
        return assayHospDictGroupMapper.insert(record);
    }

    @Override
    public void deleteByTenant(Integer tenantId) {
        assayHospDictGroupMapper.deleteByTenant(tenantId);

    }

    @Override
    public List<AssayHospDictGroup> listByIsAuto(Boolean isAuto) {
        return assayHospDictGroupMapper.listByIsAuto(isAuto, UserUtil.getTenantId());
    }

}
