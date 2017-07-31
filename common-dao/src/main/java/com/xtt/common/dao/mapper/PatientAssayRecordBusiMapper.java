package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayRecordBusi;

@Repository
public interface PatientAssayRecordBusiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientAssayRecordBusi record);

    int insertSelective(PatientAssayRecordBusi record);

    PatientAssayRecordBusi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientAssayRecordBusi record);

    int updateByPrimaryKey(PatientAssayRecordBusi record);

    /*user define*/
}