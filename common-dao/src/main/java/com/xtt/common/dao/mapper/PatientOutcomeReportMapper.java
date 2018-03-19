package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.po.PatientOutcomeReport;

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
}