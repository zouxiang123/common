package com.xtt.common.assay.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayMonthRScopeService;
import com.xtt.common.dao.mapper.PatientAssayMonthRScopeMapper;
import com.xtt.common.dao.po.PatientAssayMonthRScopePO;
import com.xtt.common.util.UserUtil;

@Service
public class PatientAssayMonthRScopeServiceImpl implements IPatientAssayMonthRScopeService {

    @Autowired
    private PatientAssayMonthRScopeMapper patientAssayMonthRScopeMapper;

    /*
     * 查询所有(non-Javadoc)
     * @see com.xtt.txgl.system.service.IPatientAssayMonthRScopeService#selectAll()
     */
    @Override
    public List<PatientAssayMonthRScopePO> selectAll() {
        return patientAssayMonthRScopeMapper.selectAll(UserUtil.getTenantId());
    }

    /* 
     *  修改PatientAssayMonthRScopePO
     */
    @Override
    public void updateAssayMonth(String[] assayClass) {
        for (int i = 0; i < assayClass.length; i++) {
            patientAssayMonthRScopeMapper.updateMonth(i + 1, assayClass[i], new Date(System.currentTimeMillis()), UserUtil.getLoginUserId(),
                            UserUtil.getTenantId());
        }
    }

    /**
     * 通过月份来查询开始时间和结束时间
     */
    @Override
    public List<PatientAssayMonthRScopePO> selectStartTimeEndTimeByMonth(Integer monthValue) {
        List<PatientAssayMonthRScopePO> PatientAssayMonthRScopePOList = patientAssayMonthRScopeMapper.selectStartTimeEndTimeByMonth(monthValue,
                        UserUtil.getTenantId());
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < PatientAssayMonthRScopePOList.size(); i++) {
            PatientAssayMonthRScopePOList.get(i).setShowStartTime(sm.format(PatientAssayMonthRScopePOList.get(i).getStartTime()));
            PatientAssayMonthRScopePOList.get(i).setShowEndTime(sm.format(PatientAssayMonthRScopePOList.get(i).getEndTime()));
        }
        return PatientAssayMonthRScopePOList;
    }

    /**
     * 通过月份来查询化验类
     */
    @Override
    public List<PatientAssayMonthRScopePO> selectAssayClassByMonth(Integer monthValue) {
        return patientAssayMonthRScopeMapper.selectAssayClassByMonth(monthValue, UserUtil.getTenantId());

    }

}
