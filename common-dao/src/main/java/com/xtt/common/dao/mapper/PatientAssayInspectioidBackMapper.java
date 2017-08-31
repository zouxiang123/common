package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayInspectioidBack;

@Repository
public interface PatientAssayInspectioidBackMapper {
    int insert(PatientAssayInspectioidBack record);

    int insertSelective(PatientAssayInspectioidBack record);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param list
     *
     */
    void insertList(List<PatientAssayInspectioidBack> list);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientAssayInspectioidBack> listByCondition(PatientAssayInspectioidBack record);
}