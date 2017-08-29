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
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IAssayHospDictService;
import com.xtt.common.assay.service.IGenerationDictService;
import com.xtt.common.assay.service.IPatientAssayBackCommonService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.AssayHospDictMapper;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.AssayHospDict;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.CmQueryPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class AssayHospDictServiceImpl implements IAssayHospDictService {

    @Autowired
    private AssayHospDictMapper assayHospDictMapper;

    @Autowired(required = true)
    private IGenerationDictService generationDictService;
    @Autowired
    private IPatientAssayBackCommonService patientAssayBackCommonService;

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
        AssayHospDictPO query = new AssayHospDictPO();
        query.setItemCode(itemCode);
        query.setFkTenantId(UserUtil.getTenantId());
        List<AssayHospDictPO> list = assayHospDictMapper.selectByCondition(query);
        if (list != null && !list.isEmpty())
            return list.get(0);
        return null;
    }

    @Override
    public List<AssayHospDictPO> getAllCategory(AssayHospDictPO record) {

        return assayHospDictMapper.selectAllCategory(record);
    }

    @Override
    public List<AssayHospDictPO> getByCondition(AssayHospDictPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return assayHospDictMapper.selectByCondition(record);
    }

    @Override
    public void deleteAssayMapping(Long id) {
        assayHospDictMapper.delAssayMapping(id);
    }

    @Override
    public String updateDictById(AssayHospDictPO record) {
        if (StringUtils.isNotBlank(record.getFkDictCode())) {
            // 检查是否已关联
            AssayHospDictPO query = new AssayHospDictPO();
            query.setFkTenantId(UserUtil.getTenantId());
            query.setFkDictCode(record.getFkDictCode());
            List<AssayHospDictPO> list = this.getByCondition(query);
            if (list != null && !list.isEmpty())
                return CommonConstants.WARNING;
        }
        record.setFkTenantId(UserUtil.getTenantId());
        record.setUpdateTime(new Date());
        record.setUpdateUserId(UserUtil.getLoginUserId());
        assayHospDictMapper.updateByPrimaryKeySelective(record);
        return CommonConstants.SUCCESS;
    }

    /**
     * 通过ItemCode来查询isTop，dataType，maxValue，minValue
     */
    @Override
    public List<AssayHospDictPO> selectAllByItemCode(String itemCode) {
        return assayHospDictMapper.selectAllByItemCode(itemCode, UserUtil.getTenantId());
    }

    /**
     * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
     */
    @Override
    public void updateDictHospitalLabSomeValue(AssayHospDictPO assayHospDict) {
        AssayHospDictPO newRecord = assayHospDictMapper.selectByPrimaryKey(assayHospDict.getId());
        List<String> itemCodes = new ArrayList<>();
        itemCodes.add(newRecord.getItemCode());
        Integer tenantId = UserUtil.getTenantId();
        // 当常用化验项发生变化时候，异步调用常用化验项预处理增加或者删除元素
        if (assayHospDict.getIsTop() != newRecord.getIsTop()) {
            new Thread(() -> {
                UserUtil.setThreadTenant(tenantId);
                patientAssayBackCommonService.updateItemCode(itemCodes, assayHospDict.getIsTop(), tenantId);
            }).start();
        }
        DataUtil.setSystemFieldValue(newRecord);
        newRecord.setPersonalMaxValue(assayHospDict.getPersonalMaxValue());
        newRecord.setPersonalMinValue(assayHospDict.getPersonalMinValue());
        newRecord.setIsTop(assayHospDict.getIsTop());
        assayHospDictMapper.updateByPrimaryKey(newRecord);

    }

    /**
     * 查询所有的父节点GroupName
     */
    @Override
    public List<AssayHospDictPO> selectGroupName() {
        return assayHospDictMapper.selectGroupName();

    }

    /**
     * 查询所有
     */
    @Override
    public List<AssayHospDictPO> selectAll() {
        return assayHospDictMapper.selectByCondition(new AssayHospDictPO());
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
        int num = assayHospDictMapper.insertSelective(record);
        if (num > 0) {
            listDictService();
        }
        return num;
    }

    @Override
    public List<AssayHospDictPO> selectAllGroup() {
        return assayHospDictMapper.selectAllGroup();
    }

    @Override

    public void insertDictHospital(List<AssayHospDictPO> list) {
        assayHospDictMapper.insertDictHospital(list);
        listDictService();
    }

    @Override
    public Long getDictId(AssayHospDictPO list) {
        return assayHospDictMapper.getDictId(list);
    }
    /*  @Override
        public int deleteByGroupId(String groupId) {
            // TODO Auto-generated method stub
            return assayHospDictMapper.deleteByGroupId(groupId);
        }*/

    public void listDictService() {
        String currentDate = DateUtil.format(new Date(), "yyyy-MM-dd");
        String dBefore = DateUtil.getSpecifiedDayBefore(currentDate);
        String dLast = DateUtil.getSpecifiedDayAfter(currentDate);
        CmQueryPO query = new CmQueryPO();
        query.setStartDate(dBefore);
        query.setEndDate(dLast);
        generationDictService.lisDictService(query);
    }

    @Override
    public List<AssayHospDictPO> selectAdminGroup() {
        return assayHospDictMapper.selectAdminGroup();
    }

    @Override
    public int deleteById(Long id) {
        return assayHospDictMapper.deleteById(id);
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
    public List<AssayHospDictPO> selectByFkCodes(Collection<String> dictCodes) {
        AssayHospDictPO record = new AssayHospDictPO();
        record.setFkTenantId(UserUtil.getTenantId());
        record.setDictCodes(dictCodes);
        return assayHospDictMapper.selectByCondition(record);
    }

    @Override
    public AssayHospDictPO selectTop(AssayHospDictPO record) {

        return assayHospDictMapper.selectTop(record);
    }

}
