/**   
 * @Title: CmDiagnosisHistTumourServiceImpl.java 
 * @Package com.xtt.common.diagnosis.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年4月1日 上午9:36:35 
 *
 */
package com.xtt.common.diagnosis.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.cache.UserCache;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmDiagnosisHistTumourMapper;
import com.xtt.common.dao.model.CmDiagnosisHistTumour;
import com.xtt.common.dao.po.CmDiagnosisHistTumourPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistTumourService;
import com.xtt.common.dto.SysUserDto;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class CmDiagnosisHistTumourServiceImpl implements ICmDiagnosisHistTumourService {
    @Autowired
    private CmDiagnosisHistTumourMapper cmDiagnosisHistTumourMapper;

    @Override
    public CmDiagnosisHistTumourPO getById(Long id) {
        return cmDiagnosisHistTumourMapper.getById(id);
    }

    @Override
    public String removeById(Long id) {
        CmDiagnosisHistTumour record = cmDiagnosisHistTumourMapper.selectByPrimaryKey(id);
        if (record != null) {
            cmDiagnosisHistTumourMapper.deleteByPrimaryKey(id);
            return CommonConstants.SUCCESS;
        }
        return CommonConstants.WARNING;
    }

    @Override
    public List<CmDiagnosisHistTumourPO> listByPatientId(Long patientId) {
        CmDiagnosisHistTumourPO record = new CmDiagnosisHistTumourPO();
        return listByCondition(record);
    }

    @Override
    public String save(CmDiagnosisHistTumourPO record) {
        record.setFkTenantId(UserUtil.getTenantId());
        if (record.getId() == null) {
            DataUtil.setAllSystemFieldValue(record);
            cmDiagnosisHistTumourMapper.insertSelective(record);
        } else {
            CmDiagnosisHistTumour oldRecord = cmDiagnosisHistTumourMapper.selectByPrimaryKey(record.getId());
            if (oldRecord == null) {
                return CommonConstants.WARNING;
            }
            oldRecord.setRecordDate(record.getRecordDate());
            oldRecord.setRecordType(record.getRecordType());
            DataUtil.setUpdateSystemFieldValue(record);
            cmDiagnosisHistTumourMapper.updateByPrimaryKey(oldRecord);
        }
        return CommonConstants.SUCCESS;
    }

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    private List<CmDiagnosisHistTumourPO> listByCondition(CmDiagnosisHistTumourPO record) {
        return init(cmDiagnosisHistTumourMapper.listByCondition(record));
    }

    /**
     * 初始化数据 (createUserName)
     * 
     * @Title: init
     * @param record
     * @return
     *
     */
    private List<CmDiagnosisHistTumourPO> init(List<CmDiagnosisHistTumourPO> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Set<Long> ids = new HashSet<>(list.size());
            list.forEach(record -> {
                ids.add(record.getCreateUserId());
            });
            Map<Long, SysUserDto> userMap = UserCache.getById(ids);
            list.forEach(record -> {
                SysUserDto user = userMap.get(record.getCreateUserId());
                if (user != null) {
                    record.setCreateUserName(user.getName());
                }
            });
        }
        return list;
    }
}
