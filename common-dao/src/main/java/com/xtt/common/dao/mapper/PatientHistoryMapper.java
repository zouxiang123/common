package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientHistory;

@Repository
public interface PatientHistoryMapper {

	int insert(PatientHistory record);

	int insertSelective(PatientHistory record);
}