package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisEntityValue;
import com.xtt.common.dao.po.CmDiagnosisEntityValuePO;

@Repository
public interface CmDiagnosisEntityValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisEntityValue record);

    int insertSelective(CmDiagnosisEntityValue record);

    CmDiagnosisEntityValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisEntityValue record);

    int updateByPrimaryKeyWithBLOBs(CmDiagnosisEntityValue record);

    int updateByPrimaryKey(CmDiagnosisEntityValue record);

    /**
     * 添加Entity下的Value集合（诊断选项集合）
     * 
     * @Title: insertValueBatch
     * @param valueList
     * @return
     *
     */
    int insertValueBatch(List<CmDiagnosisEntityValuePO> valueList);

    /**
     * 删除Entity下的Value集合（诊断选项集合）
     * 
     * @Title: deleteByEntity
     * @param entityId
     * @return
     *
     */
    int deleteByEntity(Long entityId);
}