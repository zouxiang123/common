/**   
 * @Title: AssayGroupServiceImpl.java 
 * @Package com.xtt.common.system.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年7月18日 上午9:33:49 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayGroupService;
import com.xtt.common.dao.mapper.AssayGroupConfDetailMapper;
import com.xtt.common.dao.mapper.AssayGroupConfMapper;
import com.xtt.common.dao.model.AssayGroupConf;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.po.AssayGroupConfPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

/**
 * @ClassName: AssayGroupServiceImpl
 * @date: 2016年7月18日 上午9:33:49
 * @version: V1.0
 *
 */
@Service
public class AssayGroupServiceImpl implements IAssayGroupService {

    @Autowired
    private AssayGroupConfMapper assayGroupConfMapper;

    @Autowired
    private AssayGroupConfDetailMapper assayGroupConfDetailMapper;

    @Override
    public List<AssayGroupConf> selectByCondition(AssayGroupConf record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return assayGroupConfMapper.selectByCondition(record);
    }

    @Override
    public AssayGroupConf selectByPrimaryKey(Long id) {
        return assayGroupConfMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(AssayGroupConfPO record) {

        // 保存同类分组名
        record.setFkTenantId(UserUtil.getTenantId());
        if (record.getId() == null) {
            DataUtil.setSystemFieldValue(record);
            assayGroupConfMapper.insert(record);
        } else {
            AssayGroupConf old = assayGroupConfMapper.selectByPrimaryKey(record.getId());
            DataUtil.setSystemFieldValue(record);
            record.setId(old.getId());
            record.setCreateTime(old.getCreateTime());
            record.setCreateUserId(old.getCreateUserId());
            assayGroupConfMapper.updateByPrimaryKey(record);
        }

        // 保存同类化验项
        assayGroupConfDetailMapper.deleteByFkAssayGroupConfId(record.getId());
        for (AssayGroupConfDetail detail : record.getDetails()) {
            DataUtil.setSystemFieldValue(detail);
            detail.setFkTenantId(UserUtil.getTenantId());
            detail.setFkAssayGroupConfId(record.getId());
            assayGroupConfDetailMapper.insert(detail);
        }
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        assayGroupConfMapper.deleteByPrimaryKey(id);
        assayGroupConfDetailMapper.deleteByFkAssayGroupConfId(id);
    }

    @Override
    public List<AssayGroupConfDetail> selectDetail(Long fkAssayGroupConfId) {
        return assayGroupConfDetailMapper.selectByFkAssayGroupConfId(fkAssayGroupConfId);
    }

}
