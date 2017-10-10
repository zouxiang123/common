package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHistTumour;
import com.xtt.common.dao.po.CmDiagnosisHistTumourPO;

@Repository
public interface CmDiagnosisHistTumourMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHistTumour record);

    int insertSelective(CmDiagnosisHistTumour record);

    CmDiagnosisHistTumour selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHistTumour record);

    int updateByPrimaryKey(CmDiagnosisHistTumour record);

    /*user define*/
    /**
     * 根据id获取数据
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    CmDiagnosisHistTumourPO getById(Long id);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<CmDiagnosisHistTumourPO> listByCondition(CmDiagnosisHistTumourPO record);
}