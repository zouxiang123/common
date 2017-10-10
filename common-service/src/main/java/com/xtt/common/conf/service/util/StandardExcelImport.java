/**   
 * @Title: StandardExcelImport.java 
 * @Package com.xtt.common.excel.util
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月17日 下午1:12:38 
 *
 */
package com.xtt.common.conf.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.poi.ss.usermodel.Workbook;

import com.xtt.common.dao.model.CmPatient;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.util.excel.BadInputException;
import com.xtt.common.util.excel.ExcelTools;

public class StandardExcelImport {
    private HashMap<Integer, CmPatient> patients;
    private HashMap<Integer, SysUserPO> doctors;
    private HashMap<Integer, SysUserPO> nurses;
    private HashMap<Integer, String> errorPatientMap;
    private HashMap<Integer, String> errorDoctorMap;
    private HashMap<Integer, String> errorNurseMap;
    private final Workbook workbook;

    public StandardExcelImport(File f) throws FileNotFoundException, IOException {
        workbook = ExcelTools.openExcelFile(f);
        patients = new HashMap<Integer, CmPatient>();
        doctors = new HashMap<Integer, SysUserPO>();
        nurses = new HashMap<Integer, SysUserPO>();
        errorPatientMap = new HashMap<Integer, String>();
        errorDoctorMap = new HashMap<Integer, String>();
        errorNurseMap = new HashMap<Integer, String>();
    }

    public void parse() throws DatatypeConfigurationException {
        StandardExcelTemplate pExcel = new StandardExcelTemplate(workbook, StandardSheetType.patient);
        StandardExcelTemplate dExcel = new StandardExcelTemplate(workbook, StandardSheetType.doctor);
        StandardExcelTemplate nExcel = new StandardExcelTemplate(workbook, StandardSheetType.nurse);
        CmPatient patient;
        SysUserPO user;
        for (int i = 1; i <= pExcel.getLastRowNum(); i++) {
            try {
                patient = pExcel.getPatient(i);
                if (patient != null)
                    patients.put(i, patient);
            } catch (BadInputException e) {
                errorPatientMap.put(i, e.getErrorValue());
            }
        }
        for (int i = 1; i <= dExcel.getLastRowNum(); i++) {
            try {
                user = dExcel.getUser(i);
                if (user != null)
                    doctors.put(i, user);
            } catch (BadInputException e) {
                errorDoctorMap.put(i, e.getErrorValue());
            }
        }
        for (int i = 1; i <= nExcel.getLastRowNum(); i++) {
            try {
                user = nExcel.getUser(i);
                if (user != null)
                    nurses.put(i, user);
            } catch (BadInputException e) {
                errorNurseMap.put(i, e.getErrorValue());
            }
        }
    }

    public void closeWorkbook() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    public HashMap<Integer, CmPatient> getPatients() {
        return patients;
    }

    public void setPatients(HashMap<Integer, CmPatient> patients) {
        this.patients = patients;
    }

    public HashMap<Integer, SysUserPO> getDoctors() {
        return doctors;
    }

    public void setDoctors(HashMap<Integer, SysUserPO> doctors) {
        this.doctors = doctors;
    }

    public HashMap<Integer, SysUserPO> getNurses() {
        return nurses;
    }

    public void setNurses(HashMap<Integer, SysUserPO> nurses) {
        this.nurses = nurses;
    }

    public HashMap<Integer, String> getErrorPatientMap() {
        return errorPatientMap;
    }

    public void setErrorPatientMap(HashMap<Integer, String> errorPatientMap) {
        this.errorPatientMap = errorPatientMap;
    }

    public HashMap<Integer, String> getErrorDoctorMap() {
        return errorDoctorMap;
    }

    public void setErrorDoctorMap(HashMap<Integer, String> errorDoctorMap) {
        this.errorDoctorMap = errorDoctorMap;
    }

    public HashMap<Integer, String> getErrorNurseMap() {
        return errorNurseMap;
    }

    public void setErrorNurseMap(HashMap<Integer, String> errorNurseMap) {
        this.errorNurseMap = errorNurseMap;
    }

}
