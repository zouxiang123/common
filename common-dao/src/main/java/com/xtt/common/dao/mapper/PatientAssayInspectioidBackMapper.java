package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayInspectioidBack;

@Repository
public interface PatientAssayInspectioidBackMapper {
    int insert(PatientAssayInspectioidBack record);

    int insertSelective(PatientAssayInspectioidBack record);
}