/**   
 * @Title: SyncGroupServiceImpl.java 
 * @Package com.xtt.common.conf.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年6月22日 上午11:14:55 
 *
 */
package com.xtt.common.conf.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.conf.service.ISyncGroupService;
import com.xtt.common.dao.mapper.SyncGroupMapper;
import com.xtt.common.dao.mapper.SyncGroupTenantMapper;
import com.xtt.common.dao.mapper.SyncModuleMapper;
import com.xtt.common.dao.model.SyncGroup;
import com.xtt.common.dao.model.SyncGroupTenant;
import com.xtt.common.dao.model.SyncModule;
import com.xtt.common.dao.po.SyncGroupPO;
import com.xtt.common.enums.SyncModuleEnum;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.PinyinUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class SyncGroupServiceImpl implements ISyncGroupService {
    @Autowired
    private SyncGroupMapper syncGroupMapper;
    @Autowired
    private SyncGroupTenantMapper syncGroupTenantMapper;
    @Autowired
    private SyncModuleMapper syncModuleMapper;

    @Override
    public List<SyncGroupPO> listByGroupIdForShow(Integer fkGroupId) {
        List<SyncGroupPO> list = syncGroupMapper.listByGroupIdForShow(fkGroupId);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(record -> {
                record.setModuleName(SyncModuleEnum.getNameByValues(record.getModules()));
            });
        }
        return list;
    }

    @Override
    public SyncGroupPO getById(Long id) {
        return syncGroupMapper.getById(id);
    }

    @Override
    public void saveOrUpdate(SyncGroupPO record) {
        if (record.getId() == null) {
            record.setId(PrimaryKeyUtil.getPrimaryKey("SyncGroup", UserUtil.getTenantId()));
            record.setFkGroupId(UserUtil.getTenantId());
            DataUtil.setAllSystemFieldValue(record);
            syncGroupMapper.insert(record);
        } else {
            DataUtil.setUpdateSystemFieldValue(record);
            syncGroupMapper.updateByPrimaryKeySelective(record);
            // 删除组和租户及模块之间的关联
            removeAssociate(record.getId());
        }
        // 建立和模块之间的关联
        if (StringUtil.isNotBlank(record.getModules())) {
            String[] moduleArr = record.getModules().split(",");
            Long id = PrimaryKeyUtil.getPrimaryKey("SyncModule", UserUtil.getTenantId(), moduleArr.length);
            for (String module : moduleArr) {
                SyncModule moduleRecord = new SyncModule();
                moduleRecord.setId(id);
                moduleRecord.setFkSyncGroupId(record.getId());
                moduleRecord.setModule(module);
                syncModuleMapper.insert(moduleRecord);
                id++;
            }
        }
        // 建立和租户之间的关联
        if (StringUtil.isNotBlank(record.getTenants())) {
            String[] tenantArr = record.getTenants().split(",");
            for (String tenant : tenantArr) {
                SyncGroupTenant tenantRecord = new SyncGroupTenant();
                tenantRecord.setFkSyncGroupId(record.getId());
                tenantRecord.setFkTenantId(Integer.valueOf(tenant));
                syncGroupTenantMapper.insert(tenantRecord);
            }
        }
    }

    @Override
    public List<Integer> listTenantId(Integer tenantId, String... modules) {
        if (modules != null && modules.length > 0) {
            SyncGroupTenant groupTenant = syncGroupTenantMapper.getByTenantId(tenantId);
            if (groupTenant != null) {
                Long syncGroupId = groupTenant.getFkSyncGroupId();
                List<SyncModule> syncModuleList = syncModuleMapper.listBySyncGroupId(syncGroupId);
                if (CollectionUtils.isNotEmpty(syncModuleList)) {
                    Set<String> syncModules = new HashSet<>(syncModuleList.size());
                    syncModuleList.forEach(record -> {
                        syncModules.add(record.getModule());
                    });
                    // 如果当前同步组包含所有的模块,获取需要同步的租户
                    if (syncModules.containsAll(Arrays.asList(modules))) {
                        List<SyncGroupTenant> syncGroupTenants = syncGroupTenantMapper.listBySyncGroupId(syncGroupId);
                        if (CollectionUtils.isNotEmpty(syncGroupTenants) && syncGroupTenants.size() > 1) {// 存在需要同步的租户，且同步的租户数目至少要有两家
                            List<Integer> tenants = new ArrayList<>(syncGroupTenants.size() - 1);
                            syncGroupTenants.forEach(record -> {// 不包含当前租户
                                if (!Objects.equals(tenantId, record.getFkTenantId()))
                                    tenants.add(record.getFkTenantId());
                            });
                            return tenants;
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void removeById(Long id) {
        SyncGroup group = syncGroupMapper.selectByPrimaryKey(id);
        if (group != null) {
            // 删除组和租户及模块之间的关联
            removeAssociate(group.getId());
            syncGroupMapper.deleteByPrimaryKey(group.getId());
        }
    }

    /**
     * 删除组和租户及模块之间的关联
     * 
     * @Title: removeAssociate
     * @param syncGroupId
     *
     */
    private void removeAssociate(Long syncGroupId) {
        syncGroupTenantMapper.deleteBySyncGroupId(syncGroupId);
        syncModuleMapper.deleteBySyncGroupId(syncGroupId);
    }

    @Override
    public List<Map<String, String>> listCanAssociateTenants(Long syncGroupId) {
        return syncGroupMapper.listCanAssociateTenants(syncGroupId, UserUtil.getTenantId());
    }

    @Override
    public String getSyncCode(String name) {
        String initialStr = "";
        if (StringUtil.isNotBlank(name)) {
            String initials = PinyinUtil.getShortPinyin(name.replaceAll(",|\"|'", ""));
            initialStr = initials.length() > 10 ? initials.substring(0, 10) : initials;
        }
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return initialStr.concat("_").concat(dateStr);
    }
}
