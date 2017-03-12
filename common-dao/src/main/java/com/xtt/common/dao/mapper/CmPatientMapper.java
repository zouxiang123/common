package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmPatient;
import com.xtt.common.dao.po.CmPatientPO;

@Repository
public interface CmPatientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmPatient record);

    int insertSelective(CmPatient record);

    CmPatient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmPatient record);

    int updateByPrimaryKey(CmPatient record);

    /**
     * 查询该租户的所有患者
     * 
     * @Title: selectPatientByTenantId
     * @param tenantId
     * @return
     * 
     */
    List<CmPatientPO> selectPatientByTenantId(Integer tenantId);

    /**
     * 根据id查询数据，包含省市区，等信息
     * 
     * @Title: selectById
     * @param id
     * @return
     *
     */
    CmPatientPO selectById(Long id);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: selectByCondition
     * @param patent
     * @return
     *
     */
    List<CmPatientPO> selectByCondition(CmPatient patent);

    /**
     * 根据条件查询患者人数
     * 
     * @Title: selectPatientCount
     * @param patent
     * @return
     *
     */
    Integer selectCountByCondition(CmPatient patent);

    /**
     * 根据患者idNumber查询数据
     * 
     * @Title: selectPatientByIdNumber
     * @param patent
     * @return
     *
     */
    List<CmPatientPO> selectPatientByIdNumber(CmPatient patent);

    /**
     * 患者的登陆
     * 
     * @Title: login
     * @param account
     * @param password
     * @return
     *
     */
    CmPatient login(@Param("account") String account, @Param("password") String password);

    /**
     * 更新当前租户下患者类别标识(住院还是随访)
     * 
     * @Title: updatePatientType
     * @param tenantId
     *
     */
    void updatePatientType(Integer tenantId);
}
