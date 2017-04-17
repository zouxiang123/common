package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisEntity;
import com.xtt.common.dao.po.CmDiagnosisEntityPO;

@Repository
public interface CmDiagnosisEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisEntity record);

    int insertSelective(CmDiagnosisEntity record);

    CmDiagnosisEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisEntity record);

    int updateByPrimaryKey(CmDiagnosisEntity record);

    /*user define*/
    /**
     * 根据id获取po对象
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    CmDiagnosisEntityPO getById(Long id);

    /**
     * 根据患者对应的诊断数据
     * 
     * @Title: selectEntitiesByPatient
     * @param entity
     * @return
     *
     */
    List<CmDiagnosisEntityPO> selectEntitiesByPatient(CmDiagnosisEntityPO entity);
}