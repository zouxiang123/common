/**   
 * @Title: PatientCardServiceImpl.java 
 * @Package com.xtt.txgl.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年8月17日 下午2:33:33 
 *
 */
package com.xtt.common.patient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.dao.mapper.PatientCardMapper;
import com.xtt.common.dao.model.PatientCard;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.patient.service.IPatientCardService;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.time.DateFormatUtil;

@Service
public class PatientCardServiceImpl implements IPatientCardService {
    @Autowired
    private PatientCardMapper patientCardMapper;

    @Override
    public List<PatientCardPO> selectByCardNo(PatientCardPO record) {
        return patientCardMapper.selectByCardNo(record);
    }

    /* 
     * 保存多卡号信息
     */
    @Override
    public String savePatientCard(List<PatientCardPO> ptCardList) {
        String msg = "";
        for (PatientCardPO patientCardPO : ptCardList) {
            // 检查一个患者不能添加重复的卡号
            List<PatientCardPO> selectByCardNo = patientCardMapper.selectByCardNo(patientCardPO);
            int size = selectByCardNo.size();
            String cardNo = patientCardPO.getCardNo();
            Long id = patientCardPO.getId();
            if (cardNo != null && !cardNo.equals("")) {
                // 1.更新数据
                if (id != null && !id.equals("")) {
                    if (size <= 1) {
                        PatientCard patientCard = patientCardMapper.selectByPrimaryKey(id);
                        if (patientCard != null) {
                            patientCardPO.setCreateUserId(patientCard.getCreateUserId());
                            patientCardPO.setCreateTime(patientCard.getCreateTime());
                        }
                        patientCardPO.setUpdateUserId(UserUtil.getLoginUserId());
                        patientCardPO.setUpdateTime(DateFormatUtil.getDate());
                        patientCardMapper.updateByPrimaryKey(patientCardPO);
                    } else {
                        msg = "修改的卡号信息已存在";
                    }
                    // 2.在批量插入页面提交过来的病患卡号数据
                } else {
                    if (size < 1) {
                        patientCardPO.setDelFlag(true);
                        patientCardPO.setFkTenantId(UserUtil.getTenantId());
                        patientCardPO.setCreateUserId(UserUtil.getLoginUserId());
                        patientCardPO.setCreateTime(DateFormatUtil.getDate());
                        patientCardPO.setUpdateUserId(UserUtil.getLoginUserId());
                        patientCardPO.setUpdateTime(DateFormatUtil.getDate());
                        patientCardMapper.insert(patientCardPO);
                    } else {
                        msg = "插入的卡号信息已存在";
                    }
                }
            }
        }
        return msg;
    }

    @Override
    public List<PatientCardPO> selectByPatientId(Long patientId) {
        PatientCardPO record = new PatientCardPO();
        record.setFkPtId(patientId);
        return selectByCardNo(record);
    }

}
