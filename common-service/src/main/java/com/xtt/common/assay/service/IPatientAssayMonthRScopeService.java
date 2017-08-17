package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.po.PatientAssayMonthRScopePO;

public interface IPatientAssayMonthRScopeService {

    /**
     * 查询
     */
    public List<PatientAssayMonthRScopePO> selectAll();

    /**
     * 修改PatientAssayMonthRScopePO
     */
    public void updateAssayMonth(String[] assayClass);

    /**
     * 通过月份来查询开始时间和结束时间
     */
    public List<PatientAssayMonthRScopePO> selectStartTimeEndTimeByMonth(Integer monthValue);

    /**
     * 通过固定月份来查询化验类
     */
    public List<PatientAssayMonthRScopePO> selectAssayClassByMonth(Integer monthValue);

}
