package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.MedicalHistoryRemark;
import com.xtt.common.dao.po.MedicalHistoryPO;
import com.xtt.common.dao.po.MedicalHistoryRemarkPO;
@Repository
public interface MedicalHistoryRemarkMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MedicalHistoryRemark record);

    int insertSelective(MedicalHistoryRemark record);

    MedicalHistoryRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MedicalHistoryRemark record);

    int updateByPrimaryKey(MedicalHistoryRemark record);
    
    List<MedicalHistoryRemarkPO> selectRemarkKey(MedicalHistoryPO medicalHistory);
    
    int insertSelectivePO(MedicalHistoryRemarkPO mePo);
    
}