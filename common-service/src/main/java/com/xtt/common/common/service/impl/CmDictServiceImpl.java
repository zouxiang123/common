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

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.common.service.ICmDictService;
import com.xtt.common.common.service.ICommonCacheService;
import com.xtt.common.conf.service.ISyncGroupService;
import com.xtt.common.dao.mapper.CmDictMapper;
import com.xtt.common.dao.model.CmDict;
import com.xtt.common.dao.po.CmDictPO;
import com.xtt.common.enums.SyncModuleEnum;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.model.MybatisOrderByModel;
import com.xtt.platform.util.BeanUtil;

@Service
public class CmDictServiceImpl implements ICmDictService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmDictServiceImpl.class);
    @Autowired
    private ICommonCacheService commonCacheService;
    @Autowired
    private CmDictMapper cmDictMapper;
    @Autowired
    private ISyncGroupService syncGroupService;

    @Override
    public List<CmDictPO> selectAll(Integer tenantId) {
        CmDictPO cmDictPO = new CmDictPO();
        cmDictPO.setFkTenantId(tenantId);
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
        // 查询同步租户
        List<Integer> syncTenants = syncGroupService.listTenantId(UserUtil.getTenantId(), SyncModuleEnum.cm_dict.getValue());
        boolean needSync = CollectionUtils.isNotEmpty(syncTenants);
        if (record.getId() == null) {
            record.setOperatorId(UserUtil.getLoginUserId());
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            cmDictMapper.insertSelective(record);
            if (needSync) {// 同步数据到同步组中的租户
                syncDict(record, syncTenants);
            }
        } else {
            record.setUpdateUserId(UserUtil.getLoginUserId());
            record.setUpdateTime(new Date());
            cmDictMapper.updateByPrimaryKeySelective(record);
            if (needSync) {// 同步数据到同步组中的租户
                CmDict sourceRecord = cmDictMapper.selectByPrimaryKey(record.getId());
                syncDict(sourceRecord, syncTenants);
            }
        }
        commonCacheService.cacheDict(UserUtil.getTenantId());
        if (needSync) {
            syncTenants.forEach(tenantId -> {
                commonCacheService.cacheDict(tenantId);
            });
        }
    }

    /**
     * 同步字典数据
     * 
     * @Title: syncDict
     * @param source
     * @param tenants
     *
     */
    private void syncDict(CmDict source, List<Integer> tenants) {
        tenants.forEach(tenantId -> {
            CmDict saveRecord = cmDictMapper.getByUniqueCondition(source.getpItemCode(), source.getItemCode(), tenantId);
            if (saveRecord == null) {// 如果不存在,插入一条新的
                saveRecord = new CmDict();
                BeanUtil.copyProperties(source, saveRecord, "id");
                saveRecord.setFkTenantId(tenantId);
                cmDictMapper.insertSelective(saveRecord);
            } else {
                BeanUtil.copyProperties(source, saveRecord, "id");
                saveRecord.setFkTenantId(tenantId);
                cmDictMapper.updateByPrimaryKey(saveRecord);
            }
        });
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
