/**   
 * @Title: PatientServiceImpl.java 
 * @Package com.xtt.txgl.patient.service.impl
 * Copyright: Copyright (c) 2015
 * @author: Tik   
 * @date: 2015年9月17日 上午10:02:40 
 *
 */
package com.xtt.common.patient.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xtt.common.assay.service.IPatientAssayResultService;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.common.service.ICommonService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.mapper.CmPatientHistoryMapper;
import com.xtt.common.dao.mapper.CmPatientMapper;
import com.xtt.common.dao.model.CmPatient;
import com.xtt.common.dao.model.CmPatientHistory;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.dao.po.CmPatientPO;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.patient.service.ICmPatientService;
import com.xtt.common.patient.service.IPatientOwnerService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.QRCode.QRCodeUtil;
import com.xtt.platform.util.FamilyUtil;
import com.xtt.platform.util.SpellUtil;

/**
 * @ClassName: PatientServiceImpl
 * @date: 2015年9月17日 上午10:02:40
 * @version: V1.0
 * 
 */
@Service
public class CmPatientServiceImpl implements ICmPatientService {

    @Autowired
    private CmPatientMapper cmPatientMapper;
    @Autowired
    private CmPatientHistoryMapper cmPatientHistoryMapper;
    @Autowired
    private IPatientAssayResultService patientAssayResultService;
    @Autowired
    ICommonService commonService;
    @Autowired
    private IPatientOwnerService patientOwnerService;

    /**
     * 更新缓存数据
     * 
     * @Title: updateCache
     * @param id
     *
     */
    private void updateCache(Long id) {
        // update cache
        CmPatientPO p = selectById(id, false);
        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(p, patientDto);
        PatientCache.refresh(patientDto);
    }

    @Override
    public void savePatient(CmPatient patient, boolean isImport) {
        patient.setFkTenantId(UserUtil.getLoginUser().getTenantId());
        DataUtil.setSystemFieldValue(patient);
        CmPatient prePatient = null;
        if (patient.getId() != null) {
            prePatient = this.selectById(patient.getId());
        }
        // 新建或修改了姓名才生成首字母
        if (prePatient == null && StringUtils.isNotBlank(patient.getName())
                        || prePatient != null && !prePatient.getName().equals(patient.getName())) {
            patient.setName(patient.getName().trim());
            String spellInitials = SpellUtil.getSpellInitials(patient.getName());
            patient.setInitial(FamilyUtil.getInitial(patient.getName().substring(0, 1)).toUpperCase());
            if (StringUtils.isNotEmpty(spellInitials)) {
                patient.setSpellInitials(spellInitials);
            }
        }
        if (patient.getId() == null) {
            patient.setDelFlag(false);
            cmPatientMapper.insert(patient);
            // 插入数据到所属系统子表
            PatientOwner owner = new PatientOwner();
            owner.setFkPatientId(patient.getId());
            owner.setSysOwner(patient.getSysOwner());
            owner.setFkTenantId(UserUtil.getTenantId());
            owner.setIsEnable(true);
            patientOwnerService.insert(owner);
            // 插入传染病标识数据
            insertAssayResult(patient.getId(), isImport);
        } else {
            patient.setCreateTime(null);
            patient.setCreateUserId(null);
            cmPatientMapper.updateByPrimaryKeySelective(patient);
        }
        // 上传头像
        String newFilename = "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT
                        + "/" + patient.getId() + ".png";
        // 首次创建头像
        if (StringUtils.isBlank(patient.getImagePath()) && prePatient == null) {
            // commonService.notifyDB();
            String name = patient.getName().length() >= 2 ? patient.getName().substring(patient.getName().length() - 2) : patient.getName();
            BusinessCommonUtil.combineImage(name, newFilename);
            patient.setImagePath(newFilename);
            cmPatientMapper.updateByPrimaryKeySelective(patient);
        }
        String path = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH;
        // 修改头像
        if (!newFilename.equals(patient.getImagePath())) {
            com.xtt.platform.util.io.FileUtil.rename(new File(path + patient.getImagePath()), patient.getId() + ".png");
            patient.setImagePath(newFilename);
            cmPatientMapper.updateByPrimaryKeySelective(patient);
        }

        try {
            if (prePatient == null || prePatient != null && !prePatient.getIdNumber().equals(patient.getIdNumber())) {
                // 生成二维码
                QRCodeUtil.encode(patient.getIdNumber(), null, path + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT_BARCODE,
                                "/" + patient.getId() + ".png", true);
                patient.setBarcodePath("/" + UserUtil.getTenantId() + "/images/patient/barcode/" + patient.getId() + ".png");
                cmPatientMapper.updateByPrimaryKeySelective(patient);
            }
            CmPatientHistory ph = new CmPatientHistory();
            PropertyUtils.copyProperties(ph, cmPatientMapper.selectById(patient.getId()));
            cmPatientHistoryMapper.insert(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void updatePatient(CmPatient patient) {
        DataUtil.setSystemFieldValue(patient);
        cmPatientMapper.updateByPrimaryKey(patient);
        CmPatientHistory ph = new CmPatientHistory();
        try {
            PropertyUtils.copyProperties(ph, cmPatientMapper.selectById(patient.getId()));
            cmPatientHistoryMapper.insert(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // update cache
        updateCache(patient.getId());
    }

    @Override
    public List<CmPatientPO> getPatientByTenantId(Integer tenantId, Boolean delFlag) {
        CmPatientPO patient = new CmPatientPO();
        patient.setFkTenantId(tenantId);
        patient.setDelFlag(delFlag);
        return cmPatientMapper.selectByCondition(patient);
    }

    @Override
    public Integer getPatientCount(Integer tenantId) {
        CmPatientPO patient = new CmPatientPO();
        patient.setFkTenantId(tenantId);
        patient.setDelFlag(Boolean.FALSE);
        return cmPatientMapper.selectCountByCondition(patient);
    }

    @Override
    public CmPatientPO selectById(Long id) {
        return selectById(id, true);
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
    private CmPatientPO selectById(Long id, boolean fromCache) {
        CmPatientPO patient = null;
        if (fromCache) {
            PatientDto patientDto = PatientCache.getById(id);
            if (patientDto != null) {
                patient = new CmPatientPO();
                BeanUtils.copyProperties(patientDto, patient);
            }
        }
        if (patient == null) {
            patient = cmPatientMapper.selectById(id);
        }
        if (patient != null) {
            patient.setSexShow(DictUtil.getItemName(CmDictConsts.SEX, patient.getSex()));
            patient.setMedicareCardTypeShow(DictUtil.getItemName(CmDictConsts.MEDICARE_CARD_TYPE, patient.getMedicareCardType()));
        }
        return patient;
    }

    @Override
    public List<CmPatientPO> selectByCondition(CmPatient patent) {
        patent.setFkTenantId(UserUtil.getTenantId());
        return cmPatientMapper.selectByCondition(patent);
    }

    @Override
    public CmPatientPO selectPatientByIdNumber(CmPatient patient) {
        List<CmPatientPO> list = cmPatientMapper.selectPatientByIdNumber(patient);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean checkPatientExistByIdNumber(Long id, String idNumber) {
        CmPatientPO patient = new CmPatientPO();
        patient.setId(id);
        patient.setIdNumber(idNumber);
        patient.setFkTenantId(UserUtil.getTenantId());
        List<CmPatientPO> list = cmPatientMapper.selectPatientByIdNumber(patient);
        return CollectionUtils.isEmpty(list) ? false : true;
    }

    @Override
    public void updateByPrimaryKeySelective(CmPatient patient) {
        cmPatientMapper.updateByPrimaryKeySelective(patient);
        updateCache(patient.getId());
    }

    @Override
    public List<CmPatientPO> selectByActive() {
        CmPatient p = new CmPatient();
        p.setDelFlag(false);
        p.setFkTenantId(UserUtil.getTenantId());
        return selectByCondition(p);
    }

    @Override
    public CmPatient login(String account, String password) {
        return cmPatientMapper.login(account, password);
    }

    @Override
    public List<CmPatientPO> getAllPatientByTenantId(Integer tenantId) {
        CmPatientPO patient = new CmPatientPO();
        patient.setFkTenantId(tenantId);
        return cmPatientMapper.selectByCondition(patient);
    }

    @Override
    public void updatePatientType(Integer tenantId) {
        cmPatientMapper.updatePatientType(tenantId);
    }
}
