package com.xtt.common.assay.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayClassService;
import com.xtt.common.dao.mapper.PatientAssayClassMapper;
import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.po.PatientAssayClassPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayClassServiceImpl implements IPatientAssayClassService {

    @Autowired
    private PatientAssayClassMapper patientAssayClassMapper;

    /**
     * 添加病患化验类
     */
    @Override
    public void saveList(List<PatientAssayClass> details) {
        Integer tenantId = UserUtil.getTenantId();
        // 先删除当前租户下的提醒配置数据
        PatientAssayClass delCondition = new PatientAssayClass();
        delCondition.setFkTenantId(tenantId);
        patientAssayClassMapper.deleteByCondition(delCondition);
        if (CollectionUtils.isNotEmpty(details)) {
            for (PatientAssayClass record : details) {
                record.setFkTenantId(tenantId);
                DataUtil.setSystemFieldValue(record);
                patientAssayClassMapper.insert(record);
            }
        }
    }

    @Override
    public List<PatientAssayClass> listByTenantIdForAssayNews(Integer tenantId) {
        return patientAssayClassMapper.listByTenantIdForAssayNews(tenantId);
    }

    @Override
    public void updateById(PatientAssayClass record) {
        patientAssayClassMapper.updateByPrimaryKey(record);

    }

    /**
     * 查询化验项提醒数量
     * 
     * @Title: countByCondition
     * @param fkTenantId
     * @return
     *
     */
    @Override
    public int countByCondition() {
        return patientAssayClassMapper.countByCondition(UserUtil.getTenantId());
    }

    @Override
    public List<PatientAssayClassPO> listByTenantId(Integer tenantId) {
        PatientAssayClassPO query = new PatientAssayClassPO();
        query.setFkTenantId(tenantId);
        return patientAssayClassMapper.listByCondition(query);
    }
}
