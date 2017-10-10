package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmPatientHistory;

@Repository
public interface CmPatientHistoryMapper {

    int insert(CmPatientHistory record);

    int insertSelective(CmPatientHistory record);
}