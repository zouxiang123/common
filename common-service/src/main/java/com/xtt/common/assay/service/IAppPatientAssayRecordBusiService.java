/**   
 * @Title: IAppPatientAssayRecordBusiService.java 
 * @Package com.xtt.common.assay.service
 * Copyright: Copyright (c) 2015
 * @author: xyw   
 * @date: 2018年11月14日 下午5:28:34 
 *
 */
package com.xtt.common.assay.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xtt.common.dao.model.AppPatientAssayRecordBusi;
import com.xtt.common.dao.po.AppPatientAssayRecordBusiPO;

public interface IAppPatientAssayRecordBusiService {
    /**
     * 查询患者的化验单(api用)
     * 
     * @Title: listApiAssayList
     * @param patientId
     * @param startDate
     * @param endDate
     * @return
     *
     */
    List<Map<String, Object>> listApiAssayList(Long patientId, Date startDate, Date endDate);

    /**
     * 根据患者、日期查询化验项(api用)
     * 
     * @Title: listApiAssayItems
     * @param patientId
     * @param assayDate
     * @return
     *
     */
    List<AppPatientAssayRecordBusi> listApiAssayItems(Long patientId, Date assayDate);

    /**
     * 根据自定义条件查询常用项
     * 
     * @Title: listByCondition
     * @param query
     * @return
     *
     */
    List<AppPatientAssayRecordBusiPO> listByCondition(AppPatientAssayRecordBusiPO query);

}
