package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.po.PatientAssayClassPO;

public interface IPatientAssayClassService {

    /**
     * 添加病患化验类
     */
    public void saveList(List<PatientAssayClass> details);

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
     * 根据id更新数据
     * 
     * @Title: updateById
     * @param record
     *
     */
    public void updateById(PatientAssayClass record);

    /**
     * 查询化验项提醒数量
     * 
     * @Title: countByCondition
     * @param fkTenantId
     * @return
     *
     */
    public int countByCondition();

    public List<PatientAssayClassPO> listByTenantId(Integer tenantId);
}
