/**   
 * @Title: OnePatientAssayRecordBusiFactory.java 
 * @Package com.xtt.common.assay.hand
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月7日 上午10:05:13 
 *
 */
package com.xtt.common.assay.hand;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordPO;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.BeanUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.time.DateFormatUtil;

public class ThreeAssayHand extends AssayHandFactory {
    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, List<PatientAssayRecordPO> listAssayRecord) {
        Date nowDate = new Date();
        if (startCreateTime == null && endCreateTime == null) {
            startCreateTime = DateFormatUtil.getStartTime(nowDate);
            endCreateTime = DateFormatUtil.getEndTime(nowDate);
        }
        List<PatientAssayRecordBusi> listPatientAssayRecordBusi = new ArrayList<>(1008);
        PatientAssayRecordBusi patientAssayRecordBusi;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("查询病人化验检查结果表成功总共查询到" + listAssayRecord.size());
        }
        if (listAssayRecord.size() == 0) {
            return;
        }
        Long id = PrimaryKeyUtil.getPrimaryKey(PatientAssayRecordBusi.class.getSimpleName(), UserUtil.getTenantId(), listAssayRecord.size());
        int i = 1;
        for (PatientAssayRecordPO patientAssayRecord : listAssayRecord) {
            // 检查项目唯一ID为空时候不插入
            if (patientAssayRecord.getInspectionId() == null) {
                continue;
            }
            int countByInspectionId = PatientAssayRecordBusiService.countByInspectionId(patientAssayRecord.getInspectionId());
            if (countByInspectionId != 0) {
                continue;
            } else {
                if (patientAssayRecord.getResult() == null) {
                    continue;
                }
                patientAssayRecordBusi = new PatientAssayRecordBusi();
                BeanUtil.copyProperties(patientAssayRecord, patientAssayRecordBusi);
                patientAssayRecordBusi.setCreateTime(nowDate);
                patientAssayRecordBusi.setUpdateTime(nowDate);
                patientAssayRecordBusi.setCreateUserId(CommonConstants.SYSTEM_USER_ID);
                patientAssayRecordBusi.setUpdateUserId(CommonConstants.SYSTEM_USER_ID);
                patientAssayRecordBusi.setAssayMonth(patientAssayRecord.getAssayMonth());
                patientAssayRecordBusi.setAssayDate(getAssyaDate(patientAssayRecord.getAssayDate()));
                try {
                    String result = patientAssayRecordBusi.getResult();
                    if (result != null) {
                        Double matcherToNum = matcherToNum(result);
                        patientAssayRecordBusi.setResultActual(matcherToNum);
                    }
                } catch (Exception e) {
                    LOGGER.error("result exception:", e);
                }
                patientAssayRecordBusi.setId(id++);
                // 3：根据item_code判断
                listPatientAssayRecordBusi.add(newPatientAssayRecordBusi(patientAssayRecordBusi, patientAssayRecordBusi.getItemCode()));
                i++;
                if (i == 1000) {
                    PatientAssayRecordBusiService.insertList(listPatientAssayRecordBusi);
                    listPatientAssayRecordBusi.clear();
                    i = 1;
                }
                patientAssayRecordBusi = null;
            }
        }
        if (listPatientAssayRecordBusi.size() != 0) {
            PatientAssayRecordBusiService.insertList(listPatientAssayRecordBusi);
        }

        listAssayRecord = null;

    }

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record) {
        // TODO Auto-generated method stub
        return null;
    }

}
