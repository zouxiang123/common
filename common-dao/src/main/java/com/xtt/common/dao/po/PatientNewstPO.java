/**   
 * @Title: PatientNewstPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年12月2日 下午3:47:08 
 *
 */
package com.xtt.common.dao.po;

import java.util.List;

import com.xtt.common.dao.model.Patient;

public class PatientNewstPO extends Patient {
    /**
     * 化验逾期项
     */
    private List<PatientAssayNewstPO> patientAssayNewstArrays;

    public List<PatientAssayNewstPO> getPatientAssayNewstArrays() {
        return patientAssayNewstArrays;
    }

    public void setPatientAssayNewstArrays(List<PatientAssayNewstPO> patientAssayNewstArrays) {
        this.patientAssayNewstArrays = patientAssayNewstArrays;
    }

}
