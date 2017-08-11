package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.po.PatientAssayClassPO;

public interface IPatientAssayClassService {

    /**
     * 添加病患化验类
     */
    public void savePatientAssayClass(List<PatientAssayClass> details);

    /*
     * 查询所有病患化验类
     */
    public List<PatientAssayClassPO> selectAllByAssayClass(String assayC);

    /**
     * 查询所有病患化验类的父节点
     */
    public List<PatientAssayClassPO> selectAllGroupName(String assayClsss);

    /**
     * 删除表中所有的数据
     */
    public void deleteAllByAssayClass(String assayClass);

    /**
     * 通过patientAssayMonthRScope的月份来查询
     */
    public List<PatientAssayClassPO> selectByMonth(Integer month);
}
