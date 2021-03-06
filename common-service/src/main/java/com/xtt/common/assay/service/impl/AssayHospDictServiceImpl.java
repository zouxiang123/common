/**   
 * @Title: AssayHospDictServiceImpl.java 
 * @Package com.xtt.common.assay.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月21日 上午11:50:40 
 *
 */
package com.xtt.common.assay.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayHospDictGroupMappingService;
import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IPatientAssayReportCommonService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.AssayHospDictMapper;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.AssayHospDict;
import com.xtt.common.dao.model.AssayHospDictGroupMapping;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class AssayHospDictServiceImpl implements IAssayHospDictService {

    @Autowired
    private IPatientAssayReportCommonService patientAssayReportCommonService;
    @Autowired
    private AssayHospDictMapper assayHospDictMapper;
    @Autowired
    private IAssayHospDictGroupMappingService assayHospDictGroupMappingService;

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
    public void deleteAssayMapping(Long id) {
        assayHospDictMapper.deleteAssayMapping(id);
    }

    @Override
    public String updateDictById(AssayHospDictPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        record.setUpdateTime(new Date());
        record.setUpdateUserId(UserUtil.getLoginUserId());
        assayHospDictMapper.updateByPrimaryKeySelective(record);
        return CommonConstants.SUCCESS;
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
    public List<AssayHospDictPO> selectAllCategoryByClass(AssayHospDictPO dictHospitalLab) {
        dictHospitalLab.setFkTenantId(UserUtil.getTenantId());
        return assayHospDictMapper.selectAllCategoryByClass(dictHospitalLab);
    }

    @Override
    public List<AssayHospDictPO> selectAllItemByClass(AssayHospDictPO dictHospitalLab) {
        dictHospitalLab.setFkTenantId(UserUtil.getTenantId());
        return assayHospDictMapper.selectAllItemByClass(dictHospitalLab);
    }

    @Override
    public List<AssayHospDictPO> selectAllItemByGroupDetail(AssayGroupConfDetail record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return assayHospDictMapper.selectAllItemByGroupDetail(record);
    }

    @Override
    public int insertSelective(AssayHospDictPO record) {
        // 数据插入assay_hosp_dict
        int countItemCoe = queryByItemCode(record);
        int num = 0;
        if (countItemCoe == 0) {

            num = assayHospDictMapper.insertSelective(record);
        }
        AssayHospDictGroupMapping assayHospDictGroupMapping = new AssayHospDictGroupMapping();
        assayHospDictGroupMapping.setFkItemCode(record.getItemCode());
        assayHospDictGroupMapping.setFkGroupId(record.getGroupId());
        assayHospDictGroupMapping.setFkTenantId(UserUtil.getTenantId());
        DataUtil.setSystemFieldValue(assayHospDictGroupMapping);
        // 数据插入assay_hosp_dict_group
        assayHospDictGroupMappingService.insert(assayHospDictGroupMapping);
        return num;
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
    public int updateDictHospital(AssayHospDictPO record) {
        return assayHospDictMapper.updateByPrimaryKeySelective(record);
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
        updateDictHospital(record);
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
        return assayHospDictMapper.listTopForCommonReport(tenantId, itemCodes);
    }

    @Override
    public AssayHospDictPO getById(Long id) {
        return assayHospDictMapper.getById(id);
    }

    @Override
    public List<String> listItemCodeByDictCcode(String itemCode, Integer tenantId) {
        return assayHospDictMapper.listItemCodeByDictCcode(itemCode, tenantId);
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
        assayHospDictMapper.autoMappingDict(tenantId, CommonConstants.DICT_UK_SUFFIX);
    }

    @Override
    public void saveMappingDictByIds(AssayHospDictPO record) {
        if (CollectionUtils.isNotEmpty(record.getIds()) && StringUtil.isNotBlank(record.getFkDictCode())) {
            String ukCode = record.getFkDictCode().concat(CommonConstants.DICT_UK_SUFFIX);
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
}
