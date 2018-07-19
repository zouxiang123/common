/**   
 * @Title: PatientController.java 
 * @Package com.xtt.common.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月16日 下午2:28:18 
 *
 */
package com.xtt.common.patient.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.common.service.ICommonService;
import com.xtt.common.common.service.ISysDbSourceService;
import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CmSysParamConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.County;
import com.xtt.common.dao.model.Patient;
import com.xtt.common.dao.model.PatientCard;
import com.xtt.common.dao.model.Province;
import com.xtt.common.dao.po.CmQueryPO;
import com.xtt.common.dao.po.PatientCardPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.dao.po.PatientSerialNumberPO;
import com.xtt.common.dto.SysParamDto;
import com.xtt.common.patient.service.IPatientCardService;
import com.xtt.common.patient.service.IPatientSerialNumberService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.BusinessCommonUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.FileUtil;
import com.xtt.common.util.SysParamUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.framework.core.redis.RedisCacheUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/patient/")
public class PatientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IPatientCardService patientCardService;
    @Autowired
    private ICommonService commonService;
    @Autowired
    ISysDbSourceService sysDbSourceService;
    @Autowired
    private IPatientSerialNumberService patientSerialNumberService;
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 患者基本信息编辑
     * 
     * @Title: findPatient
     * @param patientId
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("editPatient")
    public ModelAndView editPatient(Long patientId, String sysOwner) throws Exception {
        ModelAndView model = new ModelAndView("cm/patient/patient_edit");
        PatientPO patient = null;
        if (patientId != null) {
            patient = patientService.selectById(patientId);
            // 根据id获取该患者的相关卡号
            List<PatientCardPO> cardList = patientCardService.listByPatientId(patientId);
            model.addObject("ptCardList", cardList);// 卡号信息
        }
        // 添加省市区的数据，如果是新增患者，默认取第一个省
        List<Province> provinceList = commonService.getProvinceList();
        model.addObject("provinceList", provinceList);
        if (patient == null) {
            patient = new PatientPO();
            patient.setSysOwner(sysOwner);
            patient.setProvince(provinceList.get(0).getId());
        }
        List<County> countyList = commonService.getCountyList(patient.getProvince());
        model.addObject("countyList", countyList);

        model.addObject(CmDictConsts.MEDICARE_CARD_TYPE, DictUtil.listByPItemCode(CmDictConsts.MEDICARE_CARD_TYPE));
        // 如果配置显示患者编号则将编号查询出来
        String serialNumPrefix = SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_SERIALNUM_PREFIX);
        serialNumPrefix = "0".equals(serialNumPrefix) ? "" : serialNumPrefix;
        model.addObject("serialNumPrefix", serialNumPrefix);

        SysParamDto sysParam = SysParamUtil.getByName(UserUtil.getTenantId(), CmSysParamConsts.showPatientSerialNum);
        if (Objects.equals(sysParam.getParamValue(), CmSysParamConsts.showPatientSerial)) {
            PatientSerialNumberPO patientSerialNumberPO = new PatientSerialNumberPO();
            patientSerialNumberPO.setIsUse(false);// 将未使用的编号查询出来
            patientSerialNumberPO.setFkTenantId(UserUtil.getTenantId());
            List<PatientSerialNumberPO> psnList = patientSerialNumberService.selectByCondition(patientSerialNumberPO);
            // 如果编号全部用完了，则新增患者编号
            if (CollectionUtils.isEmpty(psnList)) {
                patientSerialNumberService.insert();
            }
            psnList = patientSerialNumberService.selectByCondition(patientSerialNumberPO);
            for (PatientSerialNumberPO psn : psnList) {
                psn.setSerialNum(serialNumPrefix + psn.getSerialNum());
            }
            model.addObject("psnList", psnList);
            String patientOutComeSerialNumMultiplex = SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_OUTCOME_SERIALNUM_MULTIPLEXING);
            if (StringUtil.isNotBlank(patientOutComeSerialNumMultiplex) && patientOutComeSerialNumMultiplex.equals("1")) {
                model.addObject("isSerialNumMultiplex", true);
            } else {
                model.addObject("isSerialNumMultiplex", false);
                model.addObject("newestSerialNum", psnList.get(0).getSerialNum());
            }
        }
        model.addObject("sysParam", sysParam);
        // 读取是否开启接口服务的配置
        model.addObject("interfaceOpenCardText", SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_INTERFACE));
        // 费用类型
        model.addObject("chargeTypes", DictUtil.listByPItemCode(CmDictConsts.PATIENT_CHARGE_TYPE));
        model.addObject("patientId", patientId);
        model.addObject("patient", patient);
        // 调用第三方接口地址(获取病患基本信息)
        String ifPath = DictUtil.getItemName(CmDictConsts.URL, CmDictConsts.URL_IF_PATIENT);
        model.addObject(CmDictConsts.URL_IF_PATIENT, ifPath + UserUtil.getTenantId());
        return model;
    }

    @RequestMapping("patientDetail")
    public ModelAndView patientDetail(Long patientId) throws Exception {
        ModelAndView model = new ModelAndView("cm/patient/patient_detail");
        model.addAllObjects(findPatientApi(patientId));
        return model;
    }

    /**
     * 患者基本信息详情API
     * 
     * @Title: findPatient
     * @param patientId
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("api/patientDetail")
    @ResponseBody
    public Map<String, Object> findPatientApi(@RequestParam(value = "patientId", required = false) Long patientId) throws Exception {
        Map<String, Object> retMap = new HashMap<String, Object>();
        PatientPO patient = patientService.selectById(patientId);
        retMap.put("patientId", patientId);
        retMap.put("patient", patient);
        retMap.put("patientCardList", patientCardService.listByPatientId(patientId));
        // 卡号类型
        retMap.put(CmDictConsts.MEDICARE_CARD_TYPE,
                        DictUtil.listByPItemCode(CmDictConsts.MEDICARE_CARD_TYPE, patient == null ? null : patient.getMedicareCardType()));
        retMap.put(CommonConstants.API_STATUS, CommonConstants.SUCCESS);
        return retMap;
    }

    /**
     * 验证身份证号码是否存在
     * 
     * @Title: checkPatientExistByIdNumber
     * @param patient
     * @return
     * 
     */
    @RequestMapping("checkPatientExistByIdNumber")
    @ResponseBody
    public HashMap<String, Object> checkPatientExistByIdNumber(Long id, String idNumber) {
        boolean exist = patientService.checkPatientExistByIdNumber(id, idNumber);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("exist", exist);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 保存患者信息
     * 
     * @Title: savePatientImage
     * @param patient
     * @return
     * @throws IOException
     * @throws IllegalStateException
     * 
     */
    @RequestMapping("savePatientImage")
    @ResponseBody
    public HashMap<String, Object> savePatientImage(MultipartFile image, String id) throws IllegalStateException, IOException {
        HashMap<String, Object> map = new HashMap<String, Object>();

        String path = CommonConstants.BASE_PATH + "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/";
        if (id == null) {
            String newFilename = "/patient/" + System.currentTimeMillis() + ".png";
            FileUtil.uploadFile(image, path + newFilename);
            BusinessCommonUtil.compressPic(path, newFilename);
            map.put("status", CommonConstants.SUCCESS);
            map.put("filepath", "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + newFilename);
        } else {
            String newFilename = "/patient/" + id + ".png";
            FileUtil.uploadFile(image, path + newFilename);
            BusinessCommonUtil.compressPic(path, newFilename);
            map.put("status", CommonConstants.SUCCESS);
            map.put("filepath", "/" + UserUtil.getTenantId() + "/" + CommonConstants.IMAGE_FILE_PATH + "/" + newFilename);
        }
        return map;
    }

    /**
     * 调用第三方接口获取病患基本资料
     * 
     * @param cardNo
     * @return
     * @throws Exception
     */
    @RequestMapping("getWsPatientInfo")
    @ResponseBody
    public HashMap<String, Object> getWsPatientInfo(String cardNo, String cardType) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        CmQueryPO query = new CmQueryPO();
        query.setCardNo(cardNo);
        // 将门诊号，住院号颠倒。
        if (cardType.equals(CommonConstants.PATIENT_MEDICARE_CARD_TYPE_HOSPITAL)) {
            query.setCardType(CommonConstants.PATIENT_HOSPITALIZATION);// 2代表住院号
        } else if (cardType.equals(CommonConstants.PATIENT_MEDICARE_CARD_TYPE_OUTPATIENT)) {
            query.setCardType(CommonConstants.PATIENT_OUTPATIENT);// 1代表门诊
        }
        query.setCardType(cardType);
        query.setSysOwner(UserUtil.getSysOwner());
        // 先判断有没有配置开启新增患者的接口
        String paramValue = SysParamUtil.getValueByName(CmSysParamConsts.PATIENT_INTERFACE);
        // 1代表开关开启了。
        if (StringUtil.isNoneBlank(paramValue)) {
            PatientPO patient = sysDbSourceService.patientDB(query);
            if (patient != null) {
                // 默认拉取的是身份证
                patient.setIdType(PatientPO.ID_TYPE);
            }
            // 添加省市区的数据，如果是新增患者，默认取第一个省
            List<Province> provinceList = commonService.getProvinceList();
            Integer provinceId = patient == null ? provinceList.get(0).getId() : patient.getProvince();
            List<County> countyList = commonService.getCountyList(provinceId);
            map.put("patient", patient);
            map.put("provinceList", provinceList);
            map.put("countyList", countyList);
            map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
            return map;
        } else {
            map.put("wsdlStatus", CommonConstants.WARNING);
            return map;
        }
    }

    /**
     * 保存患者信息
     * 
     * @Title: savePatient
     * @param patient
     * @return
     * 
     */
    @RequestMapping("savePatient")
    @ResponseBody
    public HashMap<String, Object> savePatient(PatientPO patient) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        patient.setIdNumber(StringUtil.stripToNull(patient.getIdNumber()));
        // 判断证件号是否已存在
        if (StringUtil.isNotBlank(patient.getIdNumber())) {
            Patient query = new Patient();
            query.setFkTenantId(UserUtil.getTenantId());
            query.setIdNumber(patient.getIdNumber());
            PatientPO p = patientService.selectPatientByIdNumber(query);
            if (p != null && (patient.getId() == null || !Objects.equals(p.getId(), patient.getId()))) {
                if (patient.getId() == null && p.getDelFlag() != null && p.getDelFlag()) {// 新增患者,且证件号码对应的患者已转归
                    map.put(CommonConstants.ERROR_MESSAGE, String.format("证件号码“%s”对应的患者“%s”已转归，不能新增", patient.getIdNumber(), p.getName()));
                    /* map.put("delFlag", p.getDelFlag());
                    map.put("patientId", p.getId());*/
                } else {
                    map.put(CommonConstants.ERROR_MESSAGE, "证件号码已存在");
                }
                map.put(CommonConstants.STATUS, CommonConstants.REPETITION);
                return map;
            }
        }
        if (StringUtil.isNotBlank(patient.getTempImagePath())) {
            patient.setImagePath(patient.getTempImagePath());
        }

        // 判断手机号是否存在
        if (StringUtil.isNotBlank(patient.getMobile())) {
            List<PatientPO> patients = patientService.listByMobile(patient.getMobile(), patient.getId());
            if (CollectionUtils.isNotEmpty(patients)) {
                map.put(CommonConstants.STATUS, CommonConstants.REPETITION);
                map.put(CommonConstants.ERROR_MESSAGE, String.format("和患者“%s”的联系方式重复", patients.get(0).getName()));
                return map;

            }
        }
        /*
         *  process patient card data
         *  remove id is empty cardNo data
         *  check duplicates cardNo
        */
        List<PatientCardPO> patientCardList = patient.getPatientCardList();
        if (CollectionUtils.isNotEmpty(patientCardList)) {
            Iterator<PatientCardPO> it = patientCardList.iterator();
            PatientCardPO pc;
            Set<String> tempCards = new HashSet<>(patientCardList.size());
            while (it.hasNext()) {
                pc = it.next();
                if (StringUtil.isNotBlank(pc.getCardNo())) {
                    if (!pc.getDelFlag()) {
                        String checkCard = pc.getCardNo() + "_" + pc.getCardType();
                        if (!tempCards.contains(checkCard)) {
                            List<PatientCard> listByCardNoType = patientCardService.listByCardNoTypeTenant(pc.getCardNo(), pc.getCardType(),
                                            UserUtil.getTenantId(), patient.getId());
                            if (CollectionUtils.isNotEmpty(listByCardNoType)) {
                                map.put(CommonConstants.STATUS, CommonConstants.WARNING);
                                return map;
                            }
                            tempCards.add(checkCard);
                        } else {
                            map.put(CommonConstants.STATUS, CommonConstants.WARNING);
                            return map;
                        }
                    }
                } else if (pc.getId() == null) {
                    it.remove();
                }
            }
        }

        try {
            patientService.savePatient(patient, false, patient.getPatientCardList());
            sysLogService.insertSysLog(CommonConstants.SYS_LOG_TYPE_2,
                            String.format("对患者（编号：%s 姓名：%s）基本信息进行了编辑动作", patient.getId(), patient.getName()), UserUtil.getSysOwner());
            map.put("fkPatientId", patient.getId());
            map.put("status", CommonConstants.SUCCESS);
            // 用户信息发布到推送模块进行推送
            Map<String, String> m = new HashMap<String, String>();
            m.put("patientId", String.valueOf(patient.getId()));
            m.put("tenantId", String.valueOf(UserUtil.getTenantId()));
            m.put("sysOwner", UserUtil.getSysOwner());
            RedisCacheUtil.publish(CommonConstants.APP_PUSH_PATIENT, m);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                LOGGER.warn("failed to save patient, duplicate key excepton:", e);
                map.put(CommonConstants.ERROR_MESSAGE, "证件号码已存在");
                map.put(CommonConstants.STATUS, CommonConstants.REPETITION);
                return map;
            } else {
                throw e;
            }
        }
        return map;
    }

    /**
     * 根据id获取患者数据
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    @RequestMapping("getById")
    @ResponseBody
    public HttpResult getById(Long id) {
        HttpResult result = HttpResult.getSuccessInstance();
        result.setRs(patientService.selectById(id));
        return result;
    }

    /**
     * app患者数据初始化用一次
     * 
     * @Title: initAppPatientList
     * @return
     *
     */
    @RequestMapping("initAppPatientList")
    @ResponseBody
    public String initAppPatientList() {
        // 用户信息发布到推送模块进行推送
        Map<String, String> m = new HashMap<String, String>();
        m.put("patientId", CommonConstants.APP_PUSH_PATIENT_INIT);
        m.put("tenantId", String.valueOf(UserUtil.getTenantId()));
        m.put("sysOwner", UserUtil.getSysOwner());
        RedisCacheUtil.publish(CommonConstants.APP_PUSH_PATIENT, m);
        return CommonConstants.SUCCESS;

    }
}
