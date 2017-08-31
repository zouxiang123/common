/**   
 * @Title: OnePatientAssayRecordBusiFactory.java 
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

public class OneAssayHand extends AssayHandFactory {

    @Override
    public void afterHandDiaAbAlag(Map<Long, List<Date>> map, Date startCreateTime, Date endCreateTime, Long fkPatientId) {

    }

    @Override
    String getDiaAbAlag(PatientAssayRecordPO record) {
        return diaAbFlag(record.getSampleClass());
    }

}
