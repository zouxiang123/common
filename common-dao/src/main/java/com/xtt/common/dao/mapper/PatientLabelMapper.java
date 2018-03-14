package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientLabel;
import com.xtt.common.dao.po.PatientLabelPO;

@Repository
public interface PatientLabelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientLabel record);

    int insertSelective(PatientLabel record);

    PatientLabel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientLabel record);

    int updateByPrimaryKey(PatientLabel record);

    /*user define*/
    /**
     * 根据租户id查询患者标签列表
     * 
     * @Title: listByTenantId
     * @param fkTenantId
     * @param sysOwner
     * @return
     *
     */
    List<PatientLabelPO> listByTenantId(@Param("fkTenantId") Integer fkTenantId, @Param("sysOwner") String sysOwner);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: listByCondition
     * @param record
     * @return
     *
     */
    List<PatientLabelPO> listByCondition(PatientLabelPO record);

    /**
     * 获取标签页患者列表
     * 
     * @Title: listPatient
     * @param record
     * @return
     *
     */
    List<PatientLabelPO> listPatient(PatientLabelPO record);

    /**
     * 获取最大的order_by的值
     * 
     * @Title: getMaxOrderBy
     * @param fkTenantId
     * @param sysOwner
     *
     */
    Integer getMaxOrderBy(@Param("fkTenantId") Integer fkTenantId, @Param("sysOwner") String sysOwner);

    /**
     * 根据自定义条件获取数量
     * 
     * @Title: countByCondition
     * @param record
     * @return
     *
     */
    int countByCondition(PatientLabel record);
}