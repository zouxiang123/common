package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDictDiagnosis;
import com.xtt.common.dao.po.CmDictDiagnosisPO;

@Repository
public interface CmDictDiagnosisMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDictDiagnosis record);

    int insertSelective(CmDictDiagnosis record);

    CmDictDiagnosis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDictDiagnosis record);

    int updateByPrimaryKey(CmDictDiagnosis record);

    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<CmDictDiagnosisPO> selectByCondition(CmDictDiagnosisPO record);

    /**
     * 根据itemCode获取父节点的信息
     * 
     * @Title: selectPInfo
     * @param itemCode
     * @return
     *
     */
    CmDictDiagnosisPO selectPInfo(String itemCode);
}