package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientHistory;

@Repository
public interface PatientHistoryMapper {

    int insert(PatientHistory record);

    int insertSelective(PatientHistory record);

    /*user define*/
    /**
     * 复制患者信息到历史表
     * 
     * @Title: copyFormPatient
     * @param id
     *            患者id
     *
     */
    void copyFormPatient(Long id);
}