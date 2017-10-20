/**   
 * @Title: IPatientCardService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月17日 下午2:30:29 
 *
 */
package com.xtt.common.patient.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.xtt.common.dao.model.PatientCard;
import com.xtt.common.dao.po.PatientCardPO;

public interface IPatientCardService {
    /**
     * 批量保存患者卡号
     * 
     * @Title: saveBatch
     * @param list
     *
     */
    void saveBatch(List<PatientCardPO> list);

    /**
     * 根据患者id查询或者卡号
     * 
     * @Title: selectByPatientId
     * @param id
     * @return
     *
     */
    List<PatientCardPO> listByPatientId(Long patientId);

    /**
     * 查询所有患者最新的His号
     * 
     * @return
     */
    List<PatientCardPO> listAllNewHisCard();

    /**
     * 根据自定义条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<PatientCardPO> listByCondition(PatientCardPO record);

    /**
     * 查询患者最新的一条hisId
     * 
     * @Title: getNewHisIdByPatientId
     * @param patientId
     * @return
     *
     */
    PatientCardPO getNewHisIdByPatientId(Long patientId);

    /**
     * 根据多个患者id查询最新的一条hisId
     * 
     * @Title: listNewHisIdByPatientIds
     * @param ids
     * @return
     *
     */
    List<PatientCardPO> selectHisId(Collection<Long> patientIds);

    /**
     * @Title: listPatientCard @Description: 获取卡号信息 @param record @return List<PatientCardPO> @throws
     */
    List<PatientCardPO> listPatientCard(PatientCardPO record);

    Map<Long, PatientCardPO> listNewHisIdByPatientIds(Collection<Long> patientIds);

    /**
     * 根据患者卡号和类型去查询
     * 
     * @Title: listByCardNoTypeTenant
     * @param cardNo
     * @param cardType
     * @param tenant
     * @param nePatientId
     *            不等于的患者id
     * @return
     *
     */
    List<PatientCard> listByCardNoTypeTenant(String cardNo, String cardType, Integer tenant, Long nePatientId);

    /**
     * 获取患者的卡号ID
     * 
     * @param record
     * @return
     */
    String getCardNo(PatientCardPO record);

    /**
     * 根据卡号和卡号类型查询患者信息
     * 
     * @param record
     * @return
     */
    List<PatientCardPO> listPatientCardByRecord(PatientCardPO record);
}
