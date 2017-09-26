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
import com.xtt.common.common.service.IFamilyInitialService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.constants.IDownConst;
import com.xtt.common.dao.mapper.PatientHistoryMapper;
import com.xtt.common.dao.mapper.PatientMapper;
import com.xtt.common.dao.mapper.PatientSerialNumberMapper;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientHistory;
import com.xtt.common.dao.model.PatientOwner;
import com.xtt.common.dao.po.PatientAssayResultPO;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.PatientSerialNumberPO;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.patient.service.IPatientCardService;
import com.xtt.common.patient.service.IPatientOwnerService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.thirddata.IPatientThirdSerice;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.common.util.QRCode.QRCodeUtil;
import com.xtt.platform.util.PinyinUtil;
import com.xtt.platform.util.lang.StringUtil;

/**
 * @ClassName: PatientServiceImpl
 * @date: 2015年9月17日 上午10:02:40
 * @version: V1.0
 * 
 */
@Service
public class PatientServiceImpl implements IPatientService {

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
    @Autowired
    private IPatientThirdSerice patientThirdSerice;
    @Autowired
    private IPatientCardService patientCardService;
    @Autowired
    private PatientSerialNumberMapper patientSerialNumberMapper;

    /**
     * 更新缓存数据
     * 
     * @Title: updateCache
     * @param id
     *
     */
    private void updateCache(Long id) {
        // update cache
        PatientPO p = selectById(id, false);
        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(p, patientDto);
        PatientCache.refresh(patientDto);
    }

    @Override
    public void savePatient(Patient patient, boolean isImport) {
        patient.setFkTenantId(UserUtil.getLoginUser().getTenantId());
        DataUtil.setSystemFieldValue(patient);
        Patient prePatient = null;
        if (patient.getId() != null) {
            prePatient = this.selectById(patient.getId());
        }
        // 新建或修改了姓名才生成首字母
        if (prePatient == null && StringUtils.isNotBlank(patient.getName())
                        || prePatient != null && !prePatient.getName().equals(patient.getName())) {
            patient.setName(patient.getName().trim());
            String spellInitials = PinyinUtil.getSpellInitials(patient.getName());
            patient.setInitial(familyInitialService.getInitial(patient.getName().substring(0, 1)));

            if (StringUtils.isNotEmpty(spellInitials)) {
                patient.setSpellInitials(spellInitials);
            }
        }
        if (patient.getId() == null) {
            patient.setDelFlag(false);
            patientMapper.insert(patient);
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
            patientMapper.updateByPrimaryKeySelective(patient);
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
            patientMapper.updateByPrimaryKeySelective(patient);
        }
        String path = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH;
        // 修改头像
        if (!newFilename.equals(patient.getImagePath())) {
            com.xtt.platform.util.io.FileUtil.rename(new File(path + patient.getImagePath()), patient.getId() + ".png");
            patient.setImagePath(newFilename);
            patientMapper.updateByPrimaryKeySelective(patient);
        }

        try {
            if (prePatient == null || prePatient != null && !prePatient.getIdNumber().equals(patient.getIdNumber())) {
                // 生成二维码
                QRCodeUtil.encode(patient.getIdNumber(), null, path + "/" + CommonConstants.IMAGE_FILE_PATH_PATIENT_BARCODE,
                                "/" + patient.getId() + ".png", true);
                patient.setBarcodePath("/" + UserUtil.getTenantId() + "/images/patient/barcode/" + patient.getId() + ".png");
                patientMapper.updateByPrimaryKeySelective(patient);
            }
            PatientHistory ph = new PatientHistory();
            PropertyUtils.copyProperties(ph, patientMapper.selectById(patient.getId()));
            patientHistoryMapper.insert(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // update cache
        updateCache(patient.getId());
    }

    @Override
    public void savePatient(Patient patient, boolean isImport, List<PatientCardPO> cards) {

        // 修改 患者序列号表
        updatePatientSerialNum(patient);
        savePatient(patient, isImport);
        // save patient card data
        if (CollectionUtils.isNotEmpty(cards)) {
            cards.forEach(pc -> {
                pc.setFkPtId(patient.getId());
            });
            patientCardService.saveBatch(cards);
        }

        // 下载该患者的所有的第三方数据
        patientThirdSerice.callInterfacePro(patient.getId(), IDownConst.DOWN_TYPE_PT_ALL_INFO, patient.getSysOwner());
    }

    private void updatePatientSerialNum(Patient patient) {
        // 表示患者没有用序列号
        if (patient.getSerialNum() == null || patient.getSerialNum() == "0") {
            return;
        }
        PatientSerialNumberPO patientSerialNumberPO = new PatientSerialNumberPO();
        patientSerialNumberPO.setFkTenantId(UserUtil.getTenantId());
        // 如果配置显示患者编号则将编号查询出来
        String serialNumPrefix = SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_SERIALNUM_PREFIX);
        serialNumPrefix = serialNumPrefix.equals("0") ? "" : serialNumPrefix;
        // 去除前缀
        patientSerialNumberPO.setSerialNum(patient.getSerialNum().replace(serialNumPrefix, ""));
        List<PatientSerialNumberPO> oldPatientSerialNumberList = patientSerialNumberMapper.selectByCondition(patientSerialNumberPO);
        // 获取当前租户指定序号的序号列表，如果存在则更新否则添加
        if (oldPatientSerialNumberList != null && oldPatientSerialNumberList.size() > 0) {
            if (patient.getId() != null) {
                // 将患者原来的序列号 在序列号表改为可用状态
                Patient p = patientMapper.selectByPrimaryKey(patient.getId());
                if (StringUtils.isNotBlank(p.getSerialNum())) {
                    patientSerialNumberPO.setIsUse(false);
                    patientSerialNumberPO.setSerialNum(p.getSerialNum().replace(serialNumPrefix, ""));
                    patientSerialNumberMapper.updateBySerialNum(patientSerialNumberPO);
                }
            }
            // 将患者现在的序列号 在序列号表里面改为不可用状态
            patientSerialNumberPO.setIsUse(true);
            patientSerialNumberPO.setSerialNum(patient.getSerialNum().replace(serialNumPrefix, ""));
            patientSerialNumberMapper.updateBySerialNum(patientSerialNumberPO);
        } else {
            DataUtil.setSystemFieldValue(patientSerialNumberPO);
            patientSerialNumberPO.setIsUse(true);
            patientSerialNumberMapper.insert(patientSerialNumberPO);
        }

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
        DataUtil.setSystemFieldValue(patient);
        patientMapper.updateByPrimaryKey(patient);
        PatientHistory ph = new PatientHistory();
        try {
            PropertyUtils.copyProperties(ph, patientMapper.selectById(patient.getId()));
            patientHistoryMapper.insert(ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // update cache
        updateCache(patient.getId());
    }

    @Override
    public List<PatientPO> getPatientByTenantId(Integer tenantId, Boolean delFlag) {
        PatientPO patient = new PatientPO();
        patient.setFkTenantId(tenantId);
        patient.setDelFlag(delFlag);
        return patientMapper.selectByCondition(patient);
    }

    @Override
    public Integer getPatientCount(Integer tenantId) {
        PatientPO patient = new PatientPO();
        patient.setFkTenantId(tenantId);
        patient.setDelFlag(Boolean.FALSE);
        return patientMapper.selectCountByCondition(patient);
    }

    @Override
    public PatientPO selectById(Long id) {
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
    private PatientPO selectById(Long id, boolean fromCache) {
        PatientPO patient = null;
        if (fromCache) {
            PatientDto patientDto = PatientCache.getById(id);
            if (patientDto != null) {
                patient = new PatientPO();
                BeanUtils.copyProperties(patientDto, patient);
            }
        }
        if (patient == null) {
            patient = patientMapper.selectById(id);
        }
        if (patient != null) {
            patient.setSexShow(DictUtil.getItemName(CmDictConsts.SEX, patient.getSex()));
            patient.setMedicareCardTypeShow(DictUtil.getItemName(CmDictConsts.MEDICARE_CARD_TYPE, patient.getMedicareCardType()));
        }
        return patient;
    }

    @Override
    public List<PatientPO> selectByCondition(Patient patent) {
        patent.setFkTenantId(UserUtil.getTenantId());
        return patientMapper.selectByCondition(patent);
    }

    @Override
    public PatientPO selectPatientByIdNumber(Patient patient) {
        List<PatientPO> list = patientMapper.selectPatientByIdNumber(patient);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean checkPatientExistByIdNumber(Long id, String idNumber) {
        PatientPO patient = new PatientPO();
        patient.setId(id);
        patient.setIdNumber(idNumber);
        patient.setFkTenantId(UserUtil.getTenantId());
        List<PatientPO> list = patientMapper.selectPatientByIdNumber(patient);
        return CollectionUtils.isEmpty(list) ? false : true;
    }

    @Override
    public void updateByPrimaryKeySelective(Patient patient) {
        patientMapper.updateByPrimaryKeySelective(patient);
        updateCache(patient.getId());
    }

    @Override
    public List<PatientPO> selectByActive() {
        Patient p = new Patient();
        p.setDelFlag(false);
        p.setFkTenantId(UserUtil.getTenantId());
        return selectByCondition(p);
    }

    @Override
    public Patient login(String account, String password) {
        return patientMapper.login(account, password);
    }

    @Override
    public List<PatientPO> getAllPatientByTenantId(Integer tenantId) {
        PatientPO patient = new PatientPO();
        patient.setFkTenantId(tenantId);
        return patientMapper.selectByCondition(patient);
    }

    @Override
    public void updatePatientType(Integer tenantId) {
        patientMapper.updatePatientType(tenantId);
    }

    @Override
    public List<PatientPO> listForDownloadList(String orderBy, Boolean isTemp) {
        PatientPO p = new PatientPO();
        p.setDelFlag(false);
        p.setFkTenantId(UserUtil.getTenantId());
        p.setOrderBy(StringUtil.isBlank(orderBy) ? null : Integer.valueOf(orderBy));
        p.setIsTemp(isTemp);
        return patientMapper.listForDownloadList(p);
    }

    @Override
    public List<PatientPO> listByMobile(String mobile, Long neId) {
        return patientMapper.listByMobile(mobile, neId);
    }
}
