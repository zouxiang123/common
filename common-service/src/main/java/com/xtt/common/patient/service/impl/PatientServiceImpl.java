/**   
 * @Title: PatientServiceImpl.java 
 * @Package com.xtt.common.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月17日 上午10:02:40 
 *
 */
package com.xtt.common.patient.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayResultService;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.common.service.ICommonService;
import com.xtt.common.common.service.IFamilyInitialService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.PatientHistoryMapper;
import com.xtt.common.dao.mapper.PatientMapper;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.patient.service.IPatientOwnerService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.QRCode.QRCodeUtil;
import com.xtt.platform.util.PinyinUtil;
import com.xtt.platform.util.PrimaryKeyUtil;
import com.xtt.platform.util.lang.StringUtil;

/**
 * @ClassName: PatientServiceImpl
 * @date: 2015年9月17日 上午10:02:40
 * @version: V1.0
 * 
 */
@Service
public class PatientServiceImpl implements IPatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private PatientHistoryMapper patientHistoryMapper;
    @Autowired
    private IPatientAssayResultService patientAssayResultService;
    @Autowired
    ICommonService commonService;
    @Autowired
    private IPatientOwnerService patientOwnerService;
    @Autowired
    private IFamilyInitialService familyInitialService;

    /**
     * 更新缓存数据
     * 
     * @Title: updateCache
     * @param id
     *
     */
    private void updateCache(Long id) {
        // update cache
        PatientPO p = getById(id, false);
        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(p, patientDto);
        PatientCache.refresh(patientDto);
    }

    @Override
    public void savePatient(Patient patient, boolean isImport) {
        DataUtil.setSystemFieldValue(patient);
        patient.setName(StringUtil.stripToNull(patient.getName()));
        Long timeStamp = System.currentTimeMillis();// 时间戳
        Patient prePatient = null;
        if (patient.getId() != null) {
            prePatient = this.getById(patient.getId());
        }
        // 新建或修改了姓名才生成首字母
        if (StringUtil.isNotBlank(patient.getName()) && (prePatient == null || !Objects.equals(prePatient.getName(), patient.getName()))) {
            patient.setName(patient.getName().trim());
            String spellInitials = PinyinUtil.getSpellInitials(patient.getName());
            patient.setInitial(familyInitialService.getInitial(patient.getName().substring(0, 1)));

            if (StringUtil.isNotEmpty(spellInitials)) {
                patient.setSpellInitials(spellInitials);
            }
        }
        if (patient.getId() == null) {
            patient.setId(PrimaryKeyUtil.getPrimaryKey("Patient", UserUtil.getTenantId()));
            patientMapper.insert(patient);
            // 插入数据到所属系统子表
            PatientOwner owner = new PatientOwner();
            owner.setFkPatientId(patient.getId());
            owner.setSysOwner(UserUtil.getSysOwner());
            owner.setFkTenantId(UserUtil.getTenantId());
            owner.setIsEnable(true);
            patientOwnerService.insert(owner);
            // 插入传染病标识数据
            insertAssayResult(patient.getId(), isImport);
        } else {
            patient.setCreateTime(null);
            patient.setCreateUserId(null);
            patientMapper.updateByPrimaryKeySelective(patient);
        }
        // 上传头像
        String newFilename = "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT
                        + "/" + patient.getId() + ".png";
        // 首次创建头像
        if (prePatient == null) {
            String name = patient.getName().length() >= 2 ? patient.getName().substring(patient.getName().length() - 2) : patient.getName();
            BusinessCommonUtil.combineImage(name, newFilename);
            patient.setImagePath(newFilename + "?t=" + timeStamp);
            patientMapper.updateByPrimaryKeySelective(patient);
        }
        String path = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH;
        // 修改头像
        if (!newFilename.equals(patient.getImagePath())) {
            com.xtt.platform.util.io.FileUtil.rename(new File(path + patient.getImagePath()), patient.getId() + ".png");
            patient.setImagePath(newFilename + "?t=" + timeStamp);
            patientMapper.updateByPrimaryKeySelective(patient);
        }

        try {
            if (prePatient == null || (prePatient != null && !Objects.equals(prePatient.getIdNumber(), patient.getIdNumber()))) {
                // 生成二维码
                QRCodeUtil.encode(patient.getIdNumber(), null, path + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT_BARCODE,
                                "/" + patient.getId() + ".png", true);
                patient.setBarcodePath("/" + UserUtil.getTenantId() + "/images/patient/barcode/" + patient.getId() + ".png");
                patientMapper.updateByPrimaryKeySelective(patient);
            }
        } catch (Exception e) {
            LOGGER.error("create QR code failed,case by:", e);
        }
        // 插入数据到历史表
        patientHistoryMapper.copyFormPatient(patient.getId());
        // update cache
        updateCache(patient.getId());
    }

    /**
     * 插入传染病标识数据
     * 
     * @Title: insertAssayResult
     * @param patientId患者id
     * @param isImport
     *
     */
    private void insertAssayResult(Long patientId, boolean isImport) {
        PatientAssayResultPO record = new PatientAssayResultPO();
        record.setFkPatientId(patientId);
        record.setHav(false);
        record.setHbv(false);
        record.setHcv(false);
        record.setHev(false);
        record.setHiv(false);
        record.setHsv(false);
        if (isImport) {// 如果是导入，默认是阴性患者
            record.setNormal(true);
        } else {// 新增患者，默认未检查
            record.setUnknown(true);
        }
        patientAssayResultService.saveAssayResult(record);
    }

    @Override
    public void updatePatient(Patient patient) {
        DataUtil.setUpdateSystemFieldValue(patient);
        patientMapper.updateByPrimaryKey(patient);
        patientHistoryMapper.copyFormPatient(patient.getId());
        // update cache
        updateCache(patient.getId());
    }

    @Override
    public List<PatientPO> listByTenantId(Integer tenantId, Boolean isEnable, String sysOwner) {
        PatientPO patient = new PatientPO();
        patient.setFkTenantId(tenantId);
        patient.setIsEnable(isEnable);
        patient.setSysOwner(sysOwner);
        return listByCondition(patient);
    }

    @Override
    public Integer countPatient(Integer tenantId, String sysOwner, Boolean isEnable) {
        PatientPO patient = new PatientPO();
        patient.setFkTenantId(tenantId);
        patient.setSysOwner(sysOwner);
        patient.setIsEnable(isEnable);
        return patientMapper.countByCondition(patient);
    }

    @Override
    public PatientPO getById(Long id) {
        return getById(id, true);
    }

    /**
     * 是否从缓存中获取数据
     * 
     * @Title: selectById
     * @param id
     * @param fromCache
     * @return
     *
     */
    private PatientPO getById(Long id, boolean fromCache) {
        PatientPO patient = null;
        if (fromCache) {
            PatientDto patientDto = PatientCache.getById(id);
            if (patientDto != null) {
                patient = new PatientPO();
                BeanUtils.copyProperties(patientDto, patient);
                return patient;
            }
        }
        if (patient == null) {
            patient = patientMapper.getById(id);
        }
        if (patient != null) {
            patient.setSexShow(DictUtil.getItemName(CmDictConsts.SEX, patient.getSex()));
        }
        return patient;
    }

    @Override
    public List<PatientPO> listByCondition(PatientPO patent) {
        return init(patientMapper.listByCondition(patent));
    }

    @Override
    public boolean checkIdNumberExist(Long id, String idNumber) {
        List<PatientPO> list = patientMapper.listByIdNumber(idNumber, id);
        return CollectionUtils.isEmpty(list) ? false : true;
    }

    @Override
    public void updateByPrimaryKeySelective(Patient patient) {
        DataUtil.setUpdateSystemFieldValue(patient);
        patientMapper.updateByPrimaryKeySelective(patient);
        patientHistoryMapper.copyFormPatient(patient.getId());
        updateCache(patient.getId());
    }

    @Override
    public Patient login(String account, String password) {
        return patientMapper.login(account, password);
    }

    /**
     * 初始化患者性别，电话号码的显示
     * 
     * @Title: init
     * @param list
     * @return
     *
     */
    private List<PatientPO> init(List<PatientPO> list) {
        if (CollectionUtils.isEmpty(list))
            return list;
        Integer tenantId = UserUtil.getTenantId();
        Map<String, String> sexMap = null;
        if (tenantId == null) {
            sexMap = new HashMap<>(2);
            sexMap.put(CommonConstants.SEX_MAN, "男");
            sexMap.put(CommonConstants.SEX_FEMALE, "女");
        } else {
            sexMap = DictUtil.getMapByPItemCode(CmDictConsts.SEX);
        }
        for (PatientPO patient : list) {
            patient.setSexShow(sexMap.get(patient.getSex()));
            patient.setMobileShow(StringUtil.formatMobile(patient.getMobile()));
        }
        return list;
    }

    @Override
    public List<PatientPO> listAll() {
        return listByCondition(new PatientPO());
    }

    @Override
    public boolean checkMobileExist(String mobile, Long neId) {
        int cnt = patientMapper.getCountByMobile(mobile, neId);
        return cnt > 0 ? true : false;
    }
}
