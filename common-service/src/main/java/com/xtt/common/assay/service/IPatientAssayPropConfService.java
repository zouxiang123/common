/**   
 * @Title: IPatientAssayPropConfService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年7月12日 上午10:44:17 
 *
 */
package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.model.PatientAssayPropConf;
import com.xtt.common.dao.po.PatientAssayPropConfPO;

public interface IPatientAssayPropConfService {
    void deleteByPrimaryKey(Long id);

    void insert(PatientAssayPropConf record);

    void insertSelective(PatientAssayPropConf record);

    PatientAssayPropConf selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(PatientAssayPropConf record);

    void updateByPrimaryKey(PatientAssayPropConf record);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param record
     * @return
     *
     */
    List<PatientAssayPropConf> listByCond(PatientAssayPropConf record);

    /**
     * 批量保存
     * 
     * @Title: saveBatch
     * @param list
     *
     */
    void insertBatch(List<PatientAssayPropConf> list);

    /**
     * 根据租户号删除数据
     * 
     * @Title: deleteByTenantId
     * @param fkTenantId
     *
     */
    void deleteByTenantId(Integer fkTenantId);

    /**
     * 获取宣教化验提醒标签（宣教计划模板化验标签）
     * 
     * @Title: listPropTemplateLabel
     * @param record
     * @return
     *
     */
    List<PatientAssayPropConfPO> listPropTemplateLabel(PatientAssayPropConf record) throws Exception;
}
