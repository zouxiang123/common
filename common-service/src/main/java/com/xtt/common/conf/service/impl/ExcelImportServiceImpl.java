/**   
 * @Title: ExcelImportServiceImpl.java 
 * @Package com.xtt.common.excel.service.impl
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2015年12月17日 下午12:54:43 
 *
 */
package com.xtt.common.conf.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.NotOLE2FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xtt.common.conf.service.IExcelImportService;
import com.xtt.common.conf.service.util.StandardExcelImport;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.SysUserPO;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.user.service.IRoleService;
import com.xtt.common.user.service.IUserService;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.excel.BadInputException;
import com.xtt.platform.util.lang.StringUtil;

@Service
public class ExcelImportServiceImpl implements IExcelImportService {
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Override
    public HashMap<String, Object> importExcel(MultipartFile excel, String sysOwner) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        File tempDir = new File(CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + "temp");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        File temp = new File(CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + "temp" + "/" + excel.getOriginalFilename());
        StandardExcelImport sei = null;
        try {
            excel.transferTo(temp);
            sei = new StandardExcelImport(temp);
            sei.parse();
            HashMap<Integer, PatientPO> patients = sei.getPatients();
            HashMap<Integer, SysUserPO> doctors = sei.getDoctors();
            HashMap<Integer, SysUserPO> nurses = sei.getNurses();
            HashMap<Integer, String> errorPatientMap = sei.getErrorPatientMap();
            HashMap<Integer, String> errorDoctorMap = sei.getErrorDoctorMap();
            HashMap<Integer, String> errorNurseMap = sei.getErrorNurseMap();
            int patientSuccessCount = 0;
            int doctorSuccessCount = 0;
            int nurseSuccessCount = 0;
            int patientErrorCount = sei.getErrorPatientMap().size();
            int doctorErrorCount = sei.getErrorDoctorMap().size();
            int nurseErrorCount = sei.getErrorNurseMap().size();
            if (patients != null && patients.size() > 0) {
                for (Entry<Integer, PatientPO> p : patients.entrySet()) {
                    if (StringUtils.isEmpty(p.getValue().getIdNumber()) || p.getValue().getBirthday() == null) {
                        patientErrorCount++;
                        errorPatientMap.put(p.getKey(), "身份证号或生日必填一项");
                    } else if (patientService.checkPatientExistByIdNumber(null, p.getValue().getIdNumber())) {
                        patientErrorCount++;
                        errorPatientMap.put(p.getKey(), "患者已存在");
                    } else {
                        p.getValue().setSysOwner(sysOwner);
                        patientService.savePatient(p.getValue(), true);
                        patientSuccessCount++;
                    }
                }
            }
            if (doctors != null && doctors.size() > 0) {
                for (Entry<Integer, SysUserPO> s : doctors.entrySet()) {
                    if (userService.getUserByAccount(s.getValue().getAccount(), UserUtil.getTenantId(), sysOwner) != null) {
                        doctorErrorCount++;
                        errorDoctorMap.put(s.getKey(), "账户已存在");
                    } else {
                        s.getValue().setSysOwner(sysOwner);
                        saveUser(s.getValue());
                        doctorSuccessCount++;
                    }

                }
            }
            if (nurses != null && nurses.size() > 0) {
                for (Entry<Integer, SysUserPO> s : nurses.entrySet()) {
                    if (userService.getUserByAccount(s.getValue().getAccount(), UserUtil.getTenantId(), sysOwner) != null) {
                        nurseErrorCount++;
                        errorNurseMap.put(s.getKey(), "账户已存在");
                    } else {
                        s.getValue().setSysOwner(sysOwner);
                        saveUser(s.getValue());
                        nurseSuccessCount++;
                    }
                }
            }
            map.put("status", CommonConstants.SUCCESS);
            map.put("patientError", sei.getErrorPatientMap());
            map.put("doctorError", sei.getErrorDoctorMap());
            map.put("nurseError", sei.getErrorNurseMap());
            map.put("patientErrorCount", patientErrorCount);
            map.put("doctorErrorCount", doctorErrorCount);
            map.put("nurseErrorCount", nurseErrorCount);
            map.put("patientCount", patientSuccessCount);
            map.put("doctorCount", doctorSuccessCount);
            map.put("nurseCount", nurseSuccessCount);
        } catch (NotOLE2FileException e) {
            map.put("status", CommonConstants.WARNING);
            map.put("error", "无效的文件");
        } catch (BadInputException e) {
            map.put("status", CommonConstants.WARNING);
            map.put("error", e.getErrorValue());
        } finally {
            if (sei != null) {
                sei.closeWorkbook();
            }
            if (temp != null) {
                temp.delete();
            }
        }
        return map;
    }

    private void saveUser(SysUserPO user) {
        user.setRoleId(roleService.getByConstant(Integer.valueOf(user.getRoleId()), UserUtil.getTenantId(), user.getSysOwner()).getId() + "");
        user.setAccount(StringUtil.trim(user.getAccount()));
        userService.saveUser(user);
    }
}
