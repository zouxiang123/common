package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayBackInspectioid;

@Repository
public interface PatientAssayBackInspectioidMapper {
    int insert(PatientAssayBackInspectioid record);

    int insertSelective(PatientAssayBackInspectioid record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<PatientAssayBackInspectioid> list);

    /**
     * 根据患者id查询
     * 
     * @Title: selectByPatientId
     * @param patientAssayBackInspectioid
     * @return
     *
     */
    List<PatientAssayBackInspectioid> selectByPatientId(PatientAssayBackInspectioid patientAssayBackInspectioid);
}