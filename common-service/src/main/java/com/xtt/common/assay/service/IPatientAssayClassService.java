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

    /**
     * 根据租户id查询for化验项提醒
     * 
     * @Title: listAllforAssayNews
     * @param tenantId
     * @return
     *
     */
    public List<PatientAssayClass> listByTenantIdForAssayNews(Integer tenantId);

    /**
     * 根据化验项分组id删除数据
     * 
     * @Title: deleteByFkAssayGroupConfId
     * @param id
     *
     */
    void deleteByFkAssayGroupConfId(Long id);

    /**
     * 根据化验项同类分组id查询数据
     * 
     * @Title: getByFkAssayGroupConfId
     * @param id
     * @return
     *
     */
    PatientAssayClass getByFkAssayGroupConfId(Long id);

    /**
     * 根据id更新数据
     * 
     * @Title: updateById
     * @param record
     *
     */
    public void updateById(PatientAssayClass record);
}
