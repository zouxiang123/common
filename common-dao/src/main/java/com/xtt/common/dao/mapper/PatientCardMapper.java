package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientCard;
import com.xtt.common.dao.po.PatientCardPO;

@Repository
public interface PatientCardMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_card
     *
     * @mbggenerated Tue May 17 10:25:21 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_card
     *
     * @mbggenerated Tue May 17 10:25:21 CST 2016
     */
    int insert(PatientCard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_card
     *
     * @mbggenerated Tue May 17 10:25:21 CST 2016
     */
    int insertSelective(PatientCard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_card
     *
     * @mbggenerated Tue May 17 10:25:21 CST 2016
     */
    PatientCard selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_card
     *
     * @mbggenerated Tue May 17 10:25:21 CST 2016
     */
    int updateByPrimaryKeySelective(PatientCard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_card
     *
     * @mbggenerated Tue May 17 10:25:21 CST 2016
     */
    int updateByPrimaryKey(PatientCard record);

    /*user define*/
    /**
     * 根据自定义条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<PatientCardPO> selectByCondition(PatientCard record);

    /**
     * 获取卡号信息
     * 
     * @Title: listPatientCard
     * @param record
     * @return
     *
     */
    List<PatientCardPO> listPatientCard(PatientCardPO record);

    /**
     * 根据卡号和卡号类型查询卡是否存在
     * 
     * @Title: listByCardNoTypeTenant
     * @param cardNo
     * @param cardType
     * @param tenantId
     * @param nePatientId
     *            不等于的患者id
     * @return
     *
     */
    List<PatientCard> listByCardNoTypeTenant(@Param("cardNo") String cardNo, @Param("cardType") String cardType, @Param("tenantId") Integer tenantId,
                    @Param("nePatientId") Long nePatientId);
}