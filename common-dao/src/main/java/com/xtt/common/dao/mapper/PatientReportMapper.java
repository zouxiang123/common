/**   
 * @Title: PatientReportMapper.java 
 * @Package com.xtt.common.dao.mapper
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年9月20日 上午10:31:03 
 *
 */
package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientReportMapper {
    /**
     * 年龄段统计
     * 
     * @Title: listAgeRange
     * @param map
     * @return
     * 
     */
    public List<Map<String, Object>> listAgeRange(Map<String, Object> map);

    /**
     * 性别统计
     * 
     * @Title: listSex
     * @param map
     * @return
     * 
     */
    public List<Map<String, Object>> listSex(Map<String, Object> map);

    /**
     * 平均年龄
     * 
     * @Title: listAvgAge
     * @param tenantId
     *            租户Id
     * @param isTemp
     *            患者长期/临时
     * @return
     * 
     */
    public List<Map<String, Object>> listAvgAge(@Param("tenantId") Integer tenantId, @Param("isTemp") Object isTemp);

    /**
     * 医保信息统计
     * 
     * @param map
     * @return
     */
    public List<Map<String, Object>> listMedical(Map<String, Object> map);

    /**
     * 患者信息统计：透析龄统计
     * 
     * @Title: selectPatientDialysisRange
     * @param map
     * @return
     * 
     */
    public List<Map<String, Object>> selectPatientDialysisAgeRange(Map<String, Object> map);

    /**
     * 患者民族统计报表
     * 
     * @Title: selectPatientNation
     * @param map
     * @return
     *
     */
    public List<Map<String, Object>> selectPatientNation(Map<String, Object> map);

    /***
     * 患者文化程度统计报表
     * 
     * @Title: selectPatientCulture
     * @param map
     * @return
     *
     */
    public List<Map<String, Object>> selectPatientCulture(Map<String, Object> map);
}
