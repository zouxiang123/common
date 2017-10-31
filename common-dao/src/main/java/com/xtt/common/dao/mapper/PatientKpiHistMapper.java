package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientKpiHist;
import com.xtt.common.dao.po.PatientKpiHistPO;

@Repository
public interface PatientKpiHistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientKpiHist record);

    int insertSelective(PatientKpiHist record);

    PatientKpiHist selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientKpiHist record);

    int updateByPrimaryKey(PatientKpiHist record);

    /*user define*/
    /**
     * 根据条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientKpiHistPO> listByCondition(PatientKpiHistPO record);

    /**
     * 根据患者id删除数据
     * 
     * @Title: removeByPatientId
     * @param fkPatientId
     * @param fkTenantId
     * @param sysOwner
     *
     */
    void removeByPatientId(@Param("fkPatientId") Long fkPatientId, @Param("fkTenantId") Integer fkTenantId, @Param("sysOwner") String sysOwner);

    /**
     * 根据条件查询数据，获取一个月的抗凝剂
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientKpiHistPO> listAnticoagulation(PatientKpiHistPO record);
}