/**   
 * @Title: DictHospitalLabServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月22日 下午12:54:39 
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

import com.xtt.common.assay.service.IDictHospitalLabService;
import com.xtt.common.assay.service.IGenerationDictService;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.DictHospitalLabMapper;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.DictHospitalLab;
import com.xtt.common.dao.po.CmQueryPO;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateUtil;

@Service
public class DictHospitalLabServiceImpl implements IDictHospitalLabService {
    @Autowired
    private DictHospitalLabMapper dictHospitalLabMapper;
    @Autowired(required = true)
    private IGenerationDictService generationDictService;

    @Override
    public DictHospitalLabPO getByItemCode(String itemCode) {
        DictHospitalLabPO query = new DictHospitalLabPO();
        query.setItemCode(itemCode);
        query.setFkTenantId(UserUtil.getTenantId());
        List<DictHospitalLabPO> list = dictHospitalLabMapper.selectByCondition(query);
        if (list != null && !list.isEmpty())
            return list.get(0);
        return null;
    }

    @Override
    public List<DictHospitalLabPO> getAllCategory(DictHospitalLabPO record) {

        return dictHospitalLabMapper.selectAllCategory(record);
    }

    @Override
    public List<DictHospitalLabPO> getByCondition(DictHospitalLabPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return dictHospitalLabMapper.selectByCondition(record);
    }

    @Override
    public void deleteAssayMapping(Long id) {
        dictHospitalLabMapper.delAssayMapping(id);
    }

    @Override
    public String updateDictById(DictHospitalLab record) {
        if (StringUtils.isNotBlank(record.getFkDictCode())) {
            // 检查是否已关联
            DictHospitalLabPO query = new DictHospitalLabPO();
            query.setFkTenantId(UserUtil.getTenantId());
            query.setFkDictCode(record.getFkDictCode());
            List<DictHospitalLabPO> list = this.getByCondition(query);
            if (list != null && !list.isEmpty())
                return CommonConstants.WARNING;
        }
        record.setFkTenantId(UserUtil.getTenantId());
        record.setUpdateTime(new Date());
        record.setUpdateUserId(UserUtil.getLoginUserId());
        dictHospitalLabMapper.updateByPrimaryKeySelective(record);
        return CommonConstants.SUCCESS;
    }

    /**
     * 通过ItemCode来查询isTop，dataType，maxValue，minValue
     */
    @Override
    public List<DictHospitalLabPO> selectAllByItemCode(String itemCode) {
        return dictHospitalLabMapper.selectAllByItemCode(itemCode, UserUtil.getTenantId());
    }

    @Override
    public List<String> selectAllAssayMonth(DictHospitalLab dictHospitalLab) {
        List<String> list = dictHospitalLabMapper.selectAllAssayMonth(dictHospitalLab);
        if (list == null) {
            list = new ArrayList<String>();
        }
        return list;
    }

    /**
     * 修改检查项规则的PersonalMinValue，isTop,PersonalMaxValue,
     */
    @Override
    public void updateDictHospitalLabSomeValue(DictHospitalLab record) {
        DictHospitalLab newRecord = dictHospitalLabMapper.selectByPrimaryKey(record.getId());
        DataUtil.setSystemFieldValue(newRecord);
        newRecord.setPersonalMaxValue(record.getPersonalMaxValue());
        newRecord.setPersonalMinValue(record.getPersonalMinValue());
        newRecord.setIsTop(record.getIsTop());
        dictHospitalLabMapper.updateByPrimaryKey(newRecord);
    }

    /**
     * 查询所有的父节点GroupName
     */
    @Override
    public List<DictHospitalLabPO> selectGroupName() {
        return dictHospitalLabMapper.selectGroupName(UserUtil.getTenantId());

    }

    /**
     * 查询所有
     */
    @Override
    public List<DictHospitalLabPO> selectAll() {
        return dictHospitalLabMapper.selectByCondition(new DictHospitalLab());
    }

    @Override
    public List<DictHospitalLabPO> selectAllCategoryByClass(DictHospitalLab dictHospitalLab) {
        dictHospitalLab.setFkTenantId(UserUtil.getTenantId());
        return dictHospitalLabMapper.selectAllCategoryByClass(dictHospitalLab);
    }

    @Override
    public List<DictHospitalLabPO> selectAllItemByClass(DictHospitalLab dictHospitalLab) {
        dictHospitalLab.setFkTenantId(UserUtil.getTenantId());
        return dictHospitalLabMapper.selectAllItemByClass(dictHospitalLab);
    }

    @Override
    public List<DictHospitalLabPO> selectAllItemByGroupDetail(AssayGroupConfDetail record) {
        record.setFkTenantId(UserUtil.getTenantId());
        return dictHospitalLabMapper.selectAllItemByGroupDetail(record);
    }

    @Override
    public int insertSelective(DictHospitalLabPO record) {
        int num = dictHospitalLabMapper.insertSelective(record);
        if (num > 0) {
            listDictService();
        }
        return num;
    }

    @Override
    public List<DictHospitalLabPO> selectAllGroup(Integer tenantId) {
        return dictHospitalLabMapper.selectAllGroup(tenantId);
    }

    @Override

    public void insertDictHospital(List<DictHospitalLabPO> list) {
        dictHospitalLabMapper.insertDictHospital(list);
        listDictService();
    }

    @Override
    public Long getDictId(DictHospitalLabPO list) {
        if (list.getFkTenantId() == null) {
            list.setFkTenantId(UserUtil.getTenantId());
        }
        return dictHospitalLabMapper.getDictId(list);
    }
    /*	@Override
    	public int deleteByGroupId(String groupId) {
    		// TODO Auto-generated method stub
    		return dictHospitalLabMapper.deleteByGroupId(groupId);
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
    public List<DictHospitalLabPO> selectAdminGroup(Integer fkTenantId) {
        return dictHospitalLabMapper.selectAdminGroup(fkTenantId);
    }

    @Override
    public int deleteById(Long id) {
        return dictHospitalLabMapper.deleteById(id);
    }

    @Override
    public int updateDictHospital(DictHospitalLabPO record) {
        return dictHospitalLabMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int queryByGroupName(DictHospitalLabPO record) {
        return dictHospitalLabMapper.queryByGroupName(record);
    }

    @Override
    public int queryByItemCode(DictHospitalLabPO record) {
        return dictHospitalLabMapper.queryByItemCode(record);
    }

    @Override
    public List<DictHospitalLabPO> selectByFkCodes(Collection<String> dictCodes) {
        DictHospitalLabPO record = new DictHospitalLabPO();
        record.setFkTenantId(UserUtil.getTenantId());
        record.setDictCodes(dictCodes);
        return dictHospitalLabMapper.selectByCondition(record);
    }

    @Override
    public DictHospitalLabPO selectTop(DictHospitalLab record) {

        return dictHospitalLabMapper.selectTop(record);
    }
}
