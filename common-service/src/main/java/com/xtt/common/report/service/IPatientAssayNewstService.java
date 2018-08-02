/**   
 * @Title: IPatientAssayNewstService.java 
 * @Package com.xtt.txgl.report.service
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年12月1日 下午7:02:13 
 *
 */
package com.xtt.common.report.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.PatientAssayNewstPO;
import com.xtt.common.dao.po.PatientNewstPO;

public interface IPatientAssayNewstService {

    /**
     * 定时任务调用 [动态计算每天自动产生最新患者化验结果数据]
     * 
     * @Title: insertAuto
     * @param tenantId
     * @return
     */
    public Map<String, String> insertAuto(Integer tenantId);

    /**
     * 根据患者获取化验单项目 检查情况
     * 
     * @Title: findByPatientAssayNewst
     * @param patientIds
     * @return
     *
     */
    public Map<Long, List<PatientAssayNewstPO>> findByPatientAssayNewst(List<Long> patientIds);

    /**
     * listOverduePatients
     * 
     * @Title: listOverduePatients
     * @param patient
     * @return
     *
     */
    public PatientNewstPO listOverduePatients(PatientNewstPO patientNewst);

}
