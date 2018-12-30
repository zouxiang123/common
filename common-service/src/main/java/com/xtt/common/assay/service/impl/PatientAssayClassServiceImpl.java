package com.xtt.common.assay.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayClassService;
import com.xtt.common.dao.mapper.AssayGroupConfDetailMapper;
import com.xtt.common.dao.mapper.PatientAssayClassMapper;
import com.xtt.common.dao.model.AssayGroupConfDetail;
import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.po.PatientAssayClassPO;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayClassServiceImpl implements IPatientAssayClassService {

    @Autowired
    private PatientAssayClassMapper patientAssayClassMapper;
    @Autowired
    private AssayGroupConfDetailMapper assayGroupConfDetailMapper;

    /**
     * 添加病患化验类
     */
    @Override
    public void savePatientAssayClass(List<PatientAssayClass> details) {
        for (PatientAssayClass record : details) {
            record.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(record);
            if (record.getFkAssayGroupConfId() != null) {
                List<AssayGroupConfDetail> list = assayGroupConfDetailMapper.selectByFkAssayGroupConfId(record.getFkAssayGroupConfId());
                String itemCodes = "";
                for (AssayGroupConfDetail agcd : list) {
                    itemCodes += "," + agcd.getItemCode();
                }
                if (itemCodes.length() > 0) {
                    itemCodes = itemCodes.substring(1);
                }
                record.setItemCode(itemCodes);
            }
            patientAssayClassMapper.insert(record);
        }
    }

    /*
     * 查询病患化验类
     * @see com.xtt.txgl.system.service.IPatientAssayClassService#selectAll()
     */
    @Override
    public List<PatientAssayClassPO> selectAllByAssayClass(String assayClass) {
        PatientAssayClass record = new PatientAssayClass();
        record.setAssayClass(assayClass);
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayClassMapper.selectAllByAssayClass(record);
    }

    /* *
     * 查询所哟病患化验类的GroupName
     */
    @Override
    public List<PatientAssayClassPO> selectAllGroupName(String assayClass) {
        PatientAssayClass record = new PatientAssayClass();
        record.setAssayClass(assayClass);
        record.setFkTenantId(UserUtil.getTenantId());
        return patientAssayClassMapper.selectAllGroupName(record);
    }

    /**
     * 删除表中所有的数据
     */
    @Override
    public void deleteAllByAssayClass(String assayClass) {
        PatientAssayClass record = new PatientAssayClass();
        record.setAssayClass(assayClass);
        record.setFkTenantId(UserUtil.getTenantId());
        patientAssayClassMapper.deleteAllByAssayClass(record);
    }

    /**
     * 通过patientAssayMonthRScope的月份来查询
     */
    @Override
    public List<PatientAssayClassPO> selectByMonth(Integer month) {
        return patientAssayClassMapper.selectByMonth(month, UserUtil.getTenantId());
    }

    @Override
    public List<PatientAssayClass> listByTenantIdForAssayNews(Integer tenantId) {
        return patientAssayClassMapper.listByTenantIdForAssayNews(tenantId);
    }

    @Override
    public void deleteByFkAssayGroupConfId(Long id) {
        patientAssayClassMapper.deleteByFkAssayGroupConfId(id);
    }

    @Override
    public PatientAssayClass getByFkAssayGroupConfId(Long id) {
        return patientAssayClassMapper.getByFkAssayGroupConfId(id);
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
}
