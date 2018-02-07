package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayClass;
import com.xtt.common.dao.po.PatientAssayClassPO;

@Repository
public interface PatientAssayClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientAssayClass record);

    int insertSelective(PatientAssayClass record);

    PatientAssayClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientAssayClass record);

    int updateByPrimaryKey(PatientAssayClass record);

    /*user define*/
    /**
     * 根据自定义条件删除数据
     * 
     * @Title: deleteByCondition
     * @param record
     *
     */
    void deleteByCondition(PatientAssayClass record);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondiction
     * @param record
     * @return
     *
     */
    List<PatientAssayClassPO> listByCondition(PatientAssayClass record);

    /**
     * 根据租户id查询for化验项提醒
     * 
     * @Title: listByTenantIdForAssayNews
     * @param fkTenantId
     * @return
     *
     */
    List<PatientAssayClass> listByTenantIdForAssayNews(Integer fkTenantId);

    /**
     * 查询化验项提醒数量
     * 
     * @Title: countByCondition
     * @param fkTenantId
     * @return
     *
     */
    int countByCondition(@Param(value = "fkTenantId") Integer fkTenantId);

}