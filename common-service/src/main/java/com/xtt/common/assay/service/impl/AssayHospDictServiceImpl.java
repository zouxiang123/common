/**   
 * @Title: AssayHospDictServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月21日 上午11:50:40 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.consts.AssayConsts;
import com.xtt.common.assay.service.IAssayHospDictGroupMappingService;
import com.xtt.common.assay.service.IAssayHospDictGroupService;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayRecordService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.AssayHospDictMapper;
import com.xtt.common.dao.model.AssayHospDict;
import com.xtt.common.dao.model.AssayHospDictGroup;
import com.xtt.common.dao.model.AssayHospDictGroupMapping;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class AssayHospDictServiceImpl implements IAssayHospDictService {

    @Autowired
    private AssayHospDictMapper assayHospDictMapper;
    @Autowired
    private IAssayHospDictGroupMappingService assayHospDictGroupMappingService;
    @Autowired
    private IPatientAssayRecordService patientAssayRecordService;
    @Autowired
    private IAssayHospDictGroupService assayHospDictGroupService;

    @Override
    public int insert(AssayHospDict record) {
        return assayHospDictMapper.insert(record);
    }

    @Override
    public int getCountByItemCode(String itemCode) {
        return assayHospDictMapper.getCountByItemCode(itemCode, UserUtil.getTenantId());
    }

    @Override
    public void deleteByTenant(Integer tenantId) {
        assayHospDictMapper.deleteByTenant(tenantId);

    }

    @Override
    public AssayHospDictPO getByItemCode(String itemCode) {
        return assayHospDictMapper.getByItemCode(itemCode, UserUtil.getTenantId());
    }

    @Override
    public List<AssayHospDictPO> getAllCategory(AssayHospDictPO record) {

        return assayHospDictMapper.selectAllCategory(record);
    }

    @Override
    public List<AssayHospDictPO> getByCondition(AssayHospDictPO record) {
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        return assayHospDictMapper.listByCondition(record);
    }

    @Override
    public void deleteMapping(Long id) {
        assayHospDictMapper.deleteAssayMapping(id);
    }

    @Override
    public void saveMappingDictByIds(AssayHospDictPO record) {
        if (CollectionUtils.isNotEmpty(record.getIds()) && StringUtil.isNotBlank(record.getFkDictCode())) {
            String ukCode = record.getFkDictCode().concat(AssayConsts.DICT_UK_SUFFIX);
            for (Long id : record.getIds()) {
                AssayHospDict updateRec = new AssayHospDict();
                updateRec.setId(id);
                updateRec.setFkDictCode(record.getFkDictCode());
                updateRec.setFkDictName(record.getFkDictName());
                updateRec.setFkDictUk(ukCode);
                updateRec.setScalingFactor(record.getScalingFactor());
                updateRec.setUpdateTime(new Date());
                updateRec.setUpdateUserId(UserUtil.getLoginUserId());
                assayHospDictMapper.updateByPrimaryKeySelective(updateRec);
            }
        }
    }

    /**
     * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
     */
    @Override
    public void updateSomeValue(AssayHospDictPO assayHospDict) {
        AssayHospDict newRecord = assayHospDictMapper.selectByPrimaryKey(assayHospDict.getId());
        DataUtil.setSystemFieldValue(newRecord);
        newRecord.setPersonalMaxValue(assayHospDict.getPersonalMaxValue());
        newRecord.setPersonalMinValue(assayHospDict.getPersonalMinValue());
        newRecord.setIsTop(assayHospDict.getIsTop());
        assayHospDictMapper.updateByPrimaryKey(newRecord);

    }

    @Override
    public void insertManual(AssayHospDictPO record) {
        // 判断当前itemCode是否已存在，如果存在，则只插入关联关系，否则两者均插入
        AssayHospDictPO old = getByItemCode(record.getItemCode());
        if (old == null) {
            assayHospDictMapper.insertSelective(record);
        } else {// 更新动作
            record.setId(old.getId());
            DataUtil.setUpdateSystemFieldValue(record);
            assayHospDictMapper.updateByPrimaryKeySelective(record);
        }
        AssayHospDictGroupMapping assayHospDictGroupMapping = new AssayHospDictGroupMapping();
        assayHospDictGroupMapping.setFkItemCode(record.getItemCode());
        assayHospDictGroupMapping.setFkGroupId(record.getGroupId());
        assayHospDictGroupMapping.setFkTenantId(UserUtil.getTenantId());
        DataUtil.setSystemFieldValue(assayHospDictGroupMapping);
        assayHospDictGroupMappingService.insert(assayHospDictGroupMapping);
    }

    @Override
    public Long getDictId(AssayHospDictPO list) {
        if (list.getFkTenantId() == null) {
            list.setFkTenantId(UserUtil.getTenantId());
        }
        return assayHospDictMapper.getDictId(list);
    }

    @Override
    public int deleteById(Long id) {
        return assayHospDictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int queryByGroupName(AssayHospDictPO record) {
        return assayHospDictMapper.queryByGroupName(record);
    }

    @Override
    public int queryByItemCode(AssayHospDictPO record) {
        return assayHospDictMapper.queryByItemCode(record);
    }

    @Override
    public AssayHospDictPO getByGroupIdAndItemCode(AssayHospDictPO record) {
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        return assayHospDictMapper.getByGroupIdAndItemCode(record);
    }

    @Override
    public List<AssayHospDictPO> listAllManualAdd() {
        AssayHospDictPO record = new AssayHospDictPO();
        record.setIsAuto(false);
        return getByCondition(record);
    }

    @Override
    public Integer queryByItemCodeandGroupId(AssayHospDictPO assayHospDictPO) {
        return assayHospDictMapper.queryByItemCodeandGroupId(assayHospDictPO);
    }

    @Override
    public void updateAssay(AssayHospDictPO record) {
        DataUtil.setUpdateSystemFieldValue(record);
        assayHospDictMapper.updateByPrimaryKeySelective(record);
        if (!Objects.equals(record.getItemCode(), record.getOldItemCode())) {// itemCode发生了变更，删除原有对应关系，重新建立code和分组的对应关系
            // 删除对应关系
            assayHospDictGroupMappingService.deleteByGroupIdAndItemCode(record.getGroupId(), record.getOldItemCode(), UserUtil.getTenantId());
            // 新增对应关系
            AssayHospDictGroupMapping assayHospDictGroupMapping = new AssayHospDictGroupMapping();
            assayHospDictGroupMapping.setFkGroupId(record.getGroupId());
            assayHospDictGroupMapping.setFkItemCode(record.getItemCode());
            assayHospDictGroupMapping.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(assayHospDictGroupMapping);
            assayHospDictGroupMappingService.insert(assayHospDictGroupMapping);
        }

    }

    @Override
    public List<AssayHospDictPO> listByIsTop(Boolean isTop) {
        return assayHospDictMapper.listByIsTop(isTop, UserUtil.getTenantId());

    }

    @Override
    public List<AssayHospDictPO> listBasicByCondition(AssayHospDictPO record) {
        if (record.getFkTenantId() == null) {
            record.setFkTenantId(UserUtil.getTenantId());
        }
        return assayHospDictMapper.listBasicByCondition(record);
    }

    @Override
    public List<AssayHospDictPO> listTopForCommonReport(Integer tenantId, Collection<String> itemCodes) {
        AssayHospDictPO query = new AssayHospDictPO();
        query.setFkTenantId(tenantId);
        query.setItemCodes(itemCodes);
        return listProcessedItemCodeRec(query);
    }

    @Override
    public AssayHospDictPO getById(Long id) {
        return assayHospDictMapper.getById(id);
    }

    @Override
    public List<String> listItemCodeBydictCcode(String dictCode) {
        return assayHospDictMapper.listItemCodeByDictCcode(dictCode, UserUtil.getTenantId());
    }

    @Override
    public void insertAuto(Integer tenantId, String dateStr) {
        Date startTime = null;
        Date endTime = null;
        if (StringUtil.isNotBlank(dateStr)) {
            startTime = DateFormatUtil.getStartTime(dateStr);
            endTime = DateFormatUtil.getEndTime(dateStr);
        }
        List<PatientAssayRecordPO> list = patientAssayRecordService.listDict(tenantId, startTime, endTime);
        if (CollectionUtils.isNotEmpty(list)) {
            List<AssayHospDictPO> dictList = new ArrayList<>(list.size());
            list.forEach(par -> {
                AssayHospDictPO dict = new AssayHospDictPO();
                dict.setGroupId(par.getGroupId());
                dict.setGroupName(par.getGroupName());
                dict.setItemCode(par.getItemCode());
                dict.setItemName(par.getItemName());
                dict.setValueType(par.getValueType());
                dict.setMinValue(par.getMinValue());
                dict.setMaxValue(par.getMaxValue());
                dict.setUnit(par.getUnit());
                dict.setFkTenantId(par.getFkTenantId());
                dictList.add(dict);
            });
            insertList(dictList);
        }
    }

    @Override
    public void insertList(List<AssayHospDictPO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        // 固定输出数据
        Date currDate = new Date();
        Set<String> itemCodeSet = new HashSet<>(list.size());
        Set<String> groupIdSet = new HashSet<>(list.size());
        Set<String> mappingSet = new HashSet<>(list.size());
        list.forEach(dict -> {
            String itemCode = dict.getItemCode();
            String groupId = dict.getGroupId();
            if (StringUtil.isNotBlank(dict.getFkDictCode())) {
                dict.setFkDictUk(dict.getFkDictCode().concat(AssayConsts.DICT_UK_SUFFIX));
            }
            if (getCountByItemCode(itemCode) == 0 && !itemCodeSet.contains(itemCode)) {
                if (dict.getMinValue() != null) {// 最小值
                    dict.setPersonalMinValue(dict.getMinValue());
                }
                if (dict.getMaxValue() != null) {// 最大值
                    dict.setPersonalMaxValue(dict.getMaxValue());
                }
                dict.setDateType(Objects.equals(AssayConsts.VALUE_TYPE_NUMBER, dict.getValueType()) ? "n" : "s");
                dict.setIsAuto(true);
                dict.setIsTop(false);// 默认不是常用项目
                dict.setScalingFactor(1f);// 默认换算系数为1
                dict.setCreateTime(currDate);
                dict.setUpdateTime(currDate);
                dict.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                dict.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                insert(dict);
                itemCodeSet.add(itemCode);
            }
            // 分组数据
            if (assayHospDictGroupService.getCountByGroupId(groupId) == 0 && !groupIdSet.contains(groupId)) {
                AssayHospDictGroup assayHospDictGroup = new AssayHospDictGroup();
                assayHospDictGroup.setGroupId(dict.getGroupId());
                assayHospDictGroup.setGroupName(dict.getGroupName());
                assayHospDictGroup.setFkTenantId(dict.getFkTenantId());
                assayHospDictGroup.setIsAuto(true);
                assayHospDictGroup.setCreateTime(currDate);
                assayHospDictGroup.setUpdateTime(currDate);
                assayHospDictGroup.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                assayHospDictGroup.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                assayHospDictGroupService.insert(assayHospDictGroup);
                groupIdSet.add(groupId);
            }
            // 关联数据
            String mappingUkKey = groupId.concat("_").concat(itemCode);
            if (assayHospDictGroupMappingService.getCountByGroupId(groupId, itemCode) == 0 && !mappingSet.contains(mappingUkKey)) {
                AssayHospDictGroupMapping assayHospDictGroupMapping = new AssayHospDictGroupMapping();
                assayHospDictGroupMapping.setFkItemCode(itemCode);
                assayHospDictGroupMapping.setFkGroupId(groupId);
                assayHospDictGroupMapping.setFkTenantId(dict.getFkTenantId());
                assayHospDictGroupMapping.setCreateTime(currDate);
                assayHospDictGroupMapping.setUpdateTime(currDate);
                assayHospDictGroupMapping.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                assayHospDictGroupMapping.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                assayHospDictGroupMappingService.insert(assayHospDictGroupMapping);
                mappingSet.add(mappingUkKey);
            }
        });

    }

    @Override
    public String getFkDictCodeByItemCode(String itemCode, Integer tenantId) {
        AssayHospDictPO dict = assayHospDictMapper.getByItemCode(itemCode, tenantId);
        if (dict != null && StringUtil.isNotBlank(dict.getFkDictCode())) {
            return dict.getFkDictCode();
        }
        return null;
    }

    @Override
    public List<String> listSimilarItemCode(String itemCode, Integer tenantId) {
        AssayHospDictPO dict = assayHospDictMapper.getByItemCode(itemCode, tenantId);
        if (dict == null || StringUtil.isBlank(dict.getFkDictCode())) {
            return Arrays.asList(itemCode);
        }
        return assayHospDictMapper.listItemCodeByDictCcode(dict.getFkDictCode(), tenantId);
    }

    @Override
    public List<AssayHospDictPO> listProcessedItemCodeRec(AssayHospDictPO record) {
        return assayHospDictMapper.listProcessedItemCodeRec(record);
    }

    @Override
    public void autoMappingDict(Integer tenantId) {
        assayHospDictMapper.autoMappingDict(tenantId, AssayConsts.DICT_UK_SUFFIX);
    }

}
