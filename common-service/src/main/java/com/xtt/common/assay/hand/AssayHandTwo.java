/**   
s * @Title: OnePatientAssayRecordBusiFactory.java 
 * @Package com.xtt.common.assay.hand
 * Copyright: Copyright (c) 2015
 * @author: ljz   
 * @date: 2017年8月7日 上午10:05:13 
 *
 */
package com.xtt.common.assay.hand;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.PatientAssayRecordPO;

public class AssayHandTwo extends AssayHandFactory {

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record, String labBefore, String labAfter) {// 根据样品关键字
        return diaAbFlag(record.getSampleClass(), labBefore, labAfter);
    }

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> mapPatientId, Date startCreateTime, Date endCreateTime, Long fkPatientId) {

    }

}
