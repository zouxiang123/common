package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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

    /**
     * 年度原发病统计
     * 
     * @Title: countYearDiagnosisByCondition
     * @param map
     * @return
     *
     */
    List<CmDiagnosisEntityValuePO> countYearDiagnosisByCondition(Map<String, Object> map);

    /**
     * 获取本年度原发病发病率最多的病种的item_code
     * 
     * @Title: getItemCodeByYear
     * @return
     *
     */
    CmDiagnosisEntityValuePO getItemCodeByYear(@Param("tenantId") Integer tenantId);

    /**
     * 根据条件统计一种原发病
     * 
     * @Title: countByCondtion
     * @param map
     * @return
     *
     */
    List<CmDiagnosisEntityValuePO> countByCondtion(Map<String, Object> map);

    /**
     * 统计指定年份的所有的原发病的数量
     * 
     * @Title: countAllDiagnosis
     * @param year
     * @param tenantId
     * @return
     *
     */
    Integer countAllDiagnosis(@Param("year") String year, @Param("tenantId") Integer tenantId);
}