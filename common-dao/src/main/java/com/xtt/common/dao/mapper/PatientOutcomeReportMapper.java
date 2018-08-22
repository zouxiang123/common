package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.po.PatientOutcomeReport;
import com.xtt.common.dao.po.PatientOutcomeReportPO;

@Repository
public interface PatientOutcomeReportMapper {

    /**
     * 根据条件查询患者转归记录
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientOutcomeReport> listByCondition(PatientOutcomeReport record);

    /**
     * 获取报表数据
     * 
     * @Title: listReportData
     * @param paraMap
     * @return
     *
     */
    List<Map<String, Object>> listReportData(Map<String, Object> paraMap);

    /**
     * 查询当前年份之前所有的转归患者
     * 
     * @Title: getPeritonitisList
     * @param yearStr
     * @param tenantId
     * @return
     *
     */
    List<PatientOutcomeReportPO> listTotByYear(PatientOutcomeReportPO record);

    /**
     * 转归次数
     * 
     * @Title: countPeritonitis
     * @param record
     * @return
     *
     */
    int countTots(PatientOutcomeReportPO record);
}