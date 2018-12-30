package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.po.PatientCountPO;
import com.xtt.common.dao.po.PatientPO;

@Repository
public interface PatientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

    /**
     * 查询该租户的所有患者
     * 
     * @Title: selectPatientByTenantId
     * @param tenantId
     * @return
     * 
     */
    List<PatientPO> selectPatientByTenantId(Integer tenantId);

    /**
     * 根据id查询数据，包含省市区，等信息
     * 
     * @Title: selectById
     * @param id
     * @return
     *
     */
    PatientPO selectById(Long id);

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: selectByCondition
     * @param patent
     * @return
     *
     */
    List<PatientPO> selectByCondition(Patient patent);

    /**
     * 根据条件查询患者人数
     * 
     * @Title: selectPatientCount
     * @param patent
     * @return
     *
     */
    Integer selectCountByCondition(Patient patent);

    /**
     * 根据患者idNumber查询数据
     * 
     * @Title: selectPatientByIdNumber
     * @param patent
     * @return
     *
     */
    List<PatientPO> selectPatientByIdNumber(Patient patent);

    /**
     * 患者的登陆
     * 
     * @Title: login
     * @param account
     * @param password
     * @return
     *
     */
    Patient login(@Param("account") String account, @Param("password") String password);

    /**
     * 更新当前租户下患者类别标识(住院还是随访)
     * 
     * @Title: updatePatientType
     * @param tenantId
     *
     */
    void updatePatientType(Integer tenantId);

    /**
     * 患者名单下载
     * 
     * @Title: listForDownloadList
     * @param patient
     * @return
     *
     */
    List<PatientPO> listForDownloadList(PatientPO patient);

    /**
     * 通过手机号查询患者信息
     * 
     * @param mobile
     * @param neId
     * @return
     */
    List<PatientPO> listByMobile(@Param("mobile") String mobile, @Param("neId") Long neId);

    /**
     * 获取所有的未转归患者，包含传染病结果数据
     * 
     * @Title: listAllActiveWithAssayResult
     * @param query
     * @return
     *
     */
    List<PatientPO> listAllActiveWithAssayResult(PatientPO query);

    /**
     * 根据姓名或者拼音首字母查询数据
     * 
     * @Title: listByNameOrInitials
     * @param param
     * @param tenantId
     * @param sysOwner
     * @return
     *
     */
    List<PatientPO> listByNameOrInitials(@Param("param") String param, @Param("fkTenantId") Integer tenantId, @Param("sysOwner") String sysOwner);

    /**
     * 死亡患者列表
     * 
     * @Title: selectDeadPatients
     * @param patientCountPO
     * @return
     *
     */
    List<PatientCountPO> listDeadPatients(PatientCountPO patientCountPO);

    /**
     * 查询诊断患者
     * 
     * @Title: listDiagnosisPatientByCondtion
     * @param map
     * @return
     *
     */
    List<PatientPO> listDiagnosisPatientByCondtion(Map<String, Object> map);
}
