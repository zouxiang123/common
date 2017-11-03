/**   
 * @Title: PatientDiagnosisController2.java
 * @Package com.xtt.common.diagnosis.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月18日 上午10:11:46 
 *
 */
package com.xtt.common.diagnosis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.xtt.common.api.DiagnosisApi;
import com.xtt.common.cache.PatientCache;
import com.xtt.common.common.service.ISysLogService;
import com.xtt.common.constants.CmDiagnosisConstants;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.CmDiagnosisEntityPO;
import com.xtt.common.dao.po.CmDiagnosisEntityValuePO;
import com.xtt.common.dao.po.CmDiagnosisHistAllergyPO;
import com.xtt.common.dao.po.CmDiagnosisHistHdPO;
import com.xtt.common.dao.po.CmDiagnosisHistKtPO;
import com.xtt.common.dao.po.CmDiagnosisHistPO;
import com.xtt.common.dao.po.CmDiagnosisHistPdPO;
import com.xtt.common.dao.po.CmDiagnosisHistPestilencePO;
import com.xtt.common.dao.po.CmDiagnosisHistSurgeryPO;
import com.xtt.common.dao.po.CmDiagnosisHistTumourPO;
import com.xtt.common.dao.po.CmDictDiagnosisPO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistAllergyService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistEntityService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistHdService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistKtService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistPdService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistPestilenceService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistSurgeryService;
import com.xtt.common.diagnosis.service.ICmDiagnosisHistTumourService;
import com.xtt.common.diagnosis.service.IDictDiagnosisService;
import com.xtt.common.dto.PatientDto;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/patient/diagnosis/")
public class PatientDiagnosisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientDiagnosisController.class);

    @Autowired
    private IPatientService patientService;
    @Autowired
    private ICmDiagnosisHistService cmDiagnosisHistService;
    @Autowired
    private ICmDiagnosisHistSurgeryService cmDiagnosisHistSurgeryService;
    @Autowired
    private ICmDiagnosisHistHdService cmDiagnosisHistHdService;
    @Autowired
    private ICmDiagnosisHistPdService cmDiagnosisHistPdService;
    @Autowired
    private ICmDiagnosisHistKtService cmDiagnosisHistKtService;
    @Autowired
    private ICmDiagnosisHistAllergyService cmDiagnosisHistAllergyService;
    @Autowired
    private ICmDiagnosisHistPestilenceService cmDiagnosisHistPestilenceService;
    @Autowired
    private ICmDiagnosisHistEntityService cmDiagnosisHistEntityService;
    @Autowired
    private IDictDiagnosisService dictDiagnosisService;
    @Autowired
    private ICmDiagnosisHistTumourService cmDiagnosisHistTumourService;
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 
     * @Title: newPatient
     * @param patientId
     *            患者ID
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("view")
    public ModelAndView view(Long patientId) throws Exception {
        ModelAndView model = new ModelAndView("diagnosis/diagnosis_info");
        PatientPO patient = patientService.selectById(patientId);
        model.addObject("patient", patient);
        model.addObject("patientId", patientId);
        // 病史相关字典选项列表
        // 首次透析方式
        model.addObject(CmDictConsts.FIRST_DIALYSIS_METHOD, DictUtil.listByPItemCode(CmDictConsts.FIRST_DIALYSIS_METHOD, ""));
        // 血透开始原因
        model.addObject("xtStartReason", DictUtil.listByPItemCode(CmDictConsts.BS_KSYY, ""));
        // 血透结束原因
        model.addObject("xtEndReason", DictUtil.listByPItemCode(CmDictConsts.BS_JSYY, ""));
        // 腹透开始原因
        model.addObject("ftStartReason", DictUtil.listByPItemCode(CmDictConsts.BS_KSYY, ""));
        // 腹透结束原因
        model.addObject("ftEndReason", DictUtil.listByPItemCode(CmDictConsts.BS_JSYY, ""));
        // 肾移植结束原因
        model.addObject("syzEndReason", DictUtil.listByPItemCode(CmDictConsts.BS_JSYY, ""));
        // 过敏原因
        model.addObject("gmResouce", DictUtil.listByPItemCode(CmDictConsts.BS_GMY, ""));
        // 传染病诊断名称
        model.addObject("bs_crbzdmc", DictUtil.listByPItemCode(CmDictConsts.BS_CRBZDMC, ""));
        // 传染病活动状态
        model.addObject("bs_crbhdzt", DictUtil.listByPItemCode(CmDictConsts.BS_CRBHDZT, ""));
        // 传染病治疗情况
        model.addObject("bs_crbzlqk", DictUtil.listByPItemCode(CmDictConsts.BS_CRBZLQK, ""));

        return model;
    }

    @RequestMapping("search")
    @ResponseBody
    public HashMap<String, Object> search(Long patientId, String diagnosisType, Long id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        PatientPO patient = patientService.selectById(patientId);
        map.put("patient", patient);
        if (diagnosisType.equals(CmDiagnosisConstants.HIST_FIRST_DIALYSIS)) {
            // 病史主表数据（包括：首次透析日期和类型）
            CmDiagnosisHistPO item = new CmDiagnosisHistPO();
            if (id != null) {
                item = cmDiagnosisHistService.selectById(id);
                item.setFirstTreatmentTypeShow(DictUtil.getItemName(CmDictConsts.FIRST_DIALYSIS_METHOD, item.getFirstTreatmentType()));
            }
            map.put("item", item);
        } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_SURGERY)) {
            // 病史之手术史
            CmDiagnosisHistSurgeryPO item = new CmDiagnosisHistSurgeryPO();
            if (id != null) {
                item = cmDiagnosisHistSurgeryService.selectById(id);
            }
            map.put("item", item);
        } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_HD)) {
            // 病史之血透史
            CmDiagnosisHistHdPO item = new CmDiagnosisHistHdPO();
            if (id != null) {
                item = cmDiagnosisHistHdService.selectById(id);
                item.setStartReasonShow(DictUtil.getItemName(CmDictConsts.BS_KSYY, item.getStartReason()));
                item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
            }
            map.put("item", item);
        } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_PD)) {
            // 病史之腹透史
            CmDiagnosisHistPdPO item = new CmDiagnosisHistPdPO();
            if (id != null) {
                item = cmDiagnosisHistPdService.selectById(id);
                item.setStartReasonShow(DictUtil.getItemName(CmDictConsts.BS_KSYY, item.getStartReason()));
                item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
            }
            map.put("item", item);
        } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_KT)) {
            // 病史之肾移植史
            CmDiagnosisHistKtPO item = new CmDiagnosisHistKtPO();
            if (id != null) {
                item = cmDiagnosisHistKtService.selectById(id);
                item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
            }
            map.put("item", item);
        } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_ALLERGY)) {
            // 病史之过敏史
            CmDiagnosisHistAllergyPO item = new CmDiagnosisHistAllergyPO();
            if (id != null) {
                item = cmDiagnosisHistAllergyService.selectById(id);
                item.setAllergensShow(DictUtil.getItemName(CmDictConsts.BS_GMY, item.getAllergens()));
            }
            map.put("item", item);
        } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_PESTILENCE)) {
            // 病史之传染病史
            CmDiagnosisHistPestilencePO item = new CmDiagnosisHistPestilencePO();
            if (id != null) {
                item = cmDiagnosisHistPestilenceService.selectById(id);
                String diagnosticName = item.getDiagnosticName();
                if (StringUtils.isNotBlank(diagnosticName)) {
                    String diagnosticNameShow = "";
                    for (String value : diagnosticName.trim().split(",")) {
                        diagnosticNameShow += "," + DictUtil.getItemName(CmDictConsts.BS_CRBZDMC, value);
                    }
                    diagnosticNameShow = diagnosticNameShow.substring(1);
                    item.setDiagnosticNameShow(diagnosticNameShow);
                }
                item.setActivityStateShow(DictUtil.getItemName(CmDictConsts.BS_CRBHDZT, item.getActivityState()));
                item.setTreatmentShow(DictUtil.getItemName(CmDictConsts.BS_CRBZLQK, item.getTreatment()));
            }
            map.put("item", item);
        } else if (Objects.equals(diagnosisType, CmDiagnosisConstants.HIST_TUMOUR)) {
            // 病史之肿瘤史
            CmDiagnosisHistTumourPO item = new CmDiagnosisHistTumourPO();
            if (id != null) {
                item = cmDiagnosisHistTumourService.getById(id);
            }
            map.put("item", item);
        } else if (diagnosisType.equals(CmDiagnosisConstants.DIAGNOSIS_ENTITY)) {
            // 获取患者诊断diagnosis entity动态选项数据
            CmDiagnosisEntityPO item = new CmDiagnosisEntityPO();
            if (id != null) {
                item = cmDiagnosisHistEntityService.selectById(id);
            }
            map.put("item", item);
        }
        map.put("diagnosisType", diagnosisType);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 根据患者获取诊断病史之手术史数据
     * 
     * @Title: getSurgeries
     * @param patientId
     * @param diagnosisType
     * @param itemCode
     * @return
     *
     */
    @RequestMapping("getDiagnosisData")
    @ResponseBody
    public HashMap<String, Object> getDiagnosisData(Long patientId, String diagnosisType, String itemCode) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 如果itemCode为null，则表示第一次加载，获取该患者的所有诊断数据
        HashMap<String, Object> items = new HashMap<String, Object>();
        CmDiagnosisEntityPO entity = new CmDiagnosisEntityPO();
        entity.setFkPatientId(patientId);
        entity.setItemCode(StringUtils.isBlank(itemCode) ? null : itemCode);
        if (StringUtils.isBlank(diagnosisType)) {
            // 获取诊断所有已添加的结构集合数据
            // 病史主表数据（包括：首次透析日期和类型）
            List<CmDiagnosisHistPO> diagnosisHistList = cmDiagnosisHistService.selectByPatient(patientId);
            for (CmDiagnosisHistPO item : diagnosisHistList) {
                item.setFirstTreatmentTypeShow(DictUtil.getItemName(CmDictConsts.FIRST_DIALYSIS_METHOD, item.getFirstTreatmentType()));
            }
            items.put(CmDiagnosisConstants.HIST_FIRST_DIALYSIS, diagnosisHistList);
            // 病史之手术史
            List<CmDiagnosisHistSurgeryPO> diagnosisHistSurgeryList = cmDiagnosisHistSurgeryService.selectSurgeriesByPatient(patientId);
            items.put(CmDiagnosisConstants.HIST_SURGERY, diagnosisHistSurgeryList);
            // 病史之血透史
            List<CmDiagnosisHistHdPO> diagnosisHistHdList = cmDiagnosisHistHdService.selectHdsByPatient(patientId);
            for (CmDiagnosisHistHdPO item : diagnosisHistHdList) {
                item.setStartReasonShow(DictUtil.getItemName(CmDictConsts.BS_KSYY, item.getStartReason()));
                item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
            }
            items.put(CmDiagnosisConstants.HIST_HD, diagnosisHistHdList);
            // 病史之腹透史
            List<CmDiagnosisHistPdPO> diagnosisHistPdList = cmDiagnosisHistPdService.selectPdsByPatient(patientId);
            for (CmDiagnosisHistPdPO item : diagnosisHistPdList) {
                item.setStartReasonShow(DictUtil.getItemName(CmDictConsts.BS_KSYY, item.getStartReason()));
                item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
            }
            items.put(CmDiagnosisConstants.HIST_PD, diagnosisHistPdList);
            // 病史之肾移植史
            List<CmDiagnosisHistKtPO> diagnosisHistKtList = cmDiagnosisHistKtService.selectKtsByPatient(patientId);
            for (CmDiagnosisHistKtPO item : diagnosisHistKtList) {
                item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
            }
            items.put(CmDiagnosisConstants.HIST_KT, diagnosisHistKtList);
            // 病史之过敏史
            List<CmDiagnosisHistAllergyPO> diagnosisHistAllergyList = cmDiagnosisHistAllergyService.selectAllergiesByPatient(patientId);
            for (CmDiagnosisHistAllergyPO item : diagnosisHistAllergyList) {
                item.setAllergensShow(DictUtil.getItemName(CmDictConsts.BS_GMY, item.getAllergens()));
            }
            items.put(CmDiagnosisConstants.HIST_ALLERGY, diagnosisHistAllergyList);
            // 病史之传染病史
            List<CmDiagnosisHistPestilencePO> diagnosisHistPestilenceList = cmDiagnosisHistPestilenceService.selectPestilencesByPatient(patientId);
            for (CmDiagnosisHistPestilencePO item : diagnosisHistPestilenceList) {
                String diagnosticName = item.getDiagnosticName();
                if (StringUtils.isNotBlank(diagnosticName)) {
                    String diagnosticNameShow = "";
                    for (String value : diagnosticName.trim().split(",")) {
                        diagnosticNameShow += "," + DictUtil.getItemName(CmDictConsts.BS_CRBZDMC, value);
                    }
                    diagnosticNameShow = diagnosticNameShow.substring(1);
                    item.setDiagnosticNameShow(diagnosticNameShow);
                }
                item.setActivityStateShow(DictUtil.getItemName(CmDictConsts.BS_CRBHDZT, item.getActivityState()));
                item.setTreatmentShow(DictUtil.getItemName(CmDictConsts.BS_CRBZLQK, item.getTreatment()));
            }
            items.put(CmDiagnosisConstants.HIST_PESTILENCE, diagnosisHistPestilenceList);
            // 病史之肿瘤史
            items.put(CmDiagnosisConstants.HIST_TUMOUR, cmDiagnosisHistTumourService.listByPatientId(patientId));
            // 获取患者诊断对应的diagnosis entity所有动态选项数据
            items.put(CmDiagnosisConstants.DIAGNOSIS_ENTITY, cmDiagnosisHistEntityService.selectEntitiesByPatient(entity));
            map.put("items", items);
            // 加载所有数据的同时加载诊断字典数据集合
            List<CmDictDiagnosisPO> dictDiagnosisList = dictDiagnosisService.selectTreeList("100");
            map.put("dictDiagnosisList", dictDiagnosisList);
        } else { // 如果不为null，则获取诊断字典itemCode对应的diagnosis entity数据
            if (diagnosisType.equals(CmDiagnosisConstants.HIST_FIRST_DIALYSIS)) {
                // 病史主表数据（包括：首次透析日期和类型）
                List<CmDiagnosisHistPO> diagnosisHistList = cmDiagnosisHistService.selectByPatient(patientId);
                for (CmDiagnosisHistPO item : diagnosisHistList) {
                    item.setFirstTreatmentTypeShow(DictUtil.getItemName(CmDictConsts.FIRST_DIALYSIS_METHOD, item.getFirstTreatmentType()));
                }
                items.put(CmDiagnosisConstants.HIST_FIRST_DIALYSIS, diagnosisHistList);
            } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_SURGERY)) {
                // 病史之手术史
                List<CmDiagnosisHistSurgeryPO> diagnosisHistSurgeryList = cmDiagnosisHistSurgeryService.selectSurgeriesByPatient(patientId);
                items.put(CmDiagnosisConstants.HIST_SURGERY, diagnosisHistSurgeryList);
            } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_HD)) {
                // 病史之血透史
                List<CmDiagnosisHistHdPO> diagnosisHistHdList = cmDiagnosisHistHdService.selectHdsByPatient(patientId);
                for (CmDiagnosisHistHdPO item : diagnosisHistHdList) {
                    item.setStartReasonShow(DictUtil.getItemName(CmDictConsts.BS_KSYY, item.getStartReason()));
                    item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
                }
                items.put(CmDiagnosisConstants.HIST_HD, diagnosisHistHdList);
            } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_PD)) {
                // 病史之腹透史
                List<CmDiagnosisHistPdPO> diagnosisHistPdList = cmDiagnosisHistPdService.selectPdsByPatient(patientId);
                for (CmDiagnosisHistPdPO item : diagnosisHistPdList) {
                    item.setStartReasonShow(DictUtil.getItemName(CmDictConsts.BS_KSYY, item.getStartReason()));
                    item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
                }
                items.put(CmDiagnosisConstants.HIST_PD, diagnosisHistPdList);
            } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_KT)) {
                // 病史之肾移植史
                List<CmDiagnosisHistKtPO> diagnosisHistKtList = cmDiagnosisHistKtService.selectKtsByPatient(patientId);
                for (CmDiagnosisHistKtPO item : diagnosisHistKtList) {
                    item.setEndReasonShow(DictUtil.getItemName(CmDictConsts.BS_JSYY, item.getEndReason()));
                }
                items.put(CmDiagnosisConstants.HIST_KT, diagnosisHistKtList);
            } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_ALLERGY)) {
                // 病史之过敏史
                List<CmDiagnosisHistAllergyPO> diagnosisHistAllergyList = cmDiagnosisHistAllergyService.selectAllergiesByPatient(patientId);
                for (CmDiagnosisHistAllergyPO item : diagnosisHistAllergyList) {
                    item.setAllergensShow(DictUtil.getItemName(CmDictConsts.BS_GMY, item.getAllergens()));
                }
                items.put(CmDiagnosisConstants.HIST_ALLERGY, diagnosisHistAllergyList);
            } else if (diagnosisType.equals(CmDiagnosisConstants.HIST_PESTILENCE)) {
                // 病史之传染病史
                List<CmDiagnosisHistPestilencePO> diagnosisHistPestilenceList = cmDiagnosisHistPestilenceService
                                .selectPestilencesByPatient(patientId);
                for (CmDiagnosisHistPestilencePO item : diagnosisHistPestilenceList) {
                    String diagnosticName = item.getDiagnosticName();
                    if (StringUtils.isNotBlank(diagnosticName)) {
                        String diagnosticNameShow = "";
                        for (String value : diagnosticName.trim().split(",")) {
                            diagnosticNameShow += "," + DictUtil.getItemName(CmDictConsts.BS_CRBZDMC, value);
                        }
                        diagnosticNameShow = diagnosticNameShow.substring(1);
                        item.setDiagnosticNameShow(diagnosticNameShow);
                    }
                    item.setActivityStateShow(DictUtil.getItemName(CmDictConsts.BS_CRBHDZT, item.getActivityState()));
                    item.setTreatmentShow(DictUtil.getItemName(CmDictConsts.BS_CRBZLQK, item.getTreatment()));
                }
                items.put(CmDiagnosisConstants.HIST_PESTILENCE, diagnosisHistPestilenceList);
            } else if (Objects.equals(CmDiagnosisConstants.HIST_TUMOUR, diagnosisType)) {
                // 病史之肿瘤史
                items.put(CmDiagnosisConstants.HIST_TUMOUR, cmDiagnosisHistTumourService.listByPatientId(patientId));
            } else if (diagnosisType.equals(CmDiagnosisConstants.DIAGNOSIS_ENTITY)) {
                // 获取患者诊断diagnosis entity动态选项数据
                items.put(CmDiagnosisConstants.DIAGNOSIS_ENTITY, cmDiagnosisHistEntityService.selectEntitiesByPatient(entity));
                // 加载所有数据的同时加载诊断字典数据集合
                List<CmDictDiagnosisPO> dictDiagnosisList = dictDiagnosisService.selectTreeList("100");
                map.put("dictDiagnosisList", dictDiagnosisList);
            }
            map.put("items", items);
        }
        map.put("diagnosisType", diagnosisType);
        map.put("itemCode", itemCode);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }

    /**
     * 保存病史询问
     * 
     * @Title: saveHistFirstDialysis
     * @param hist
     *            病史主表数据（首次透析日期和类型）
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistFirstDialysis")
    @ResponseBody
    public HashMap<String, Object> saveHistFirstDialysis(CmDiagnosisHistPO hist) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = hist.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistService.updateCmDiagnosisHist(hist);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(hist.getFkPatientId(), "病史", action);
        return map;
    }

    /**
     * 删除病史询问
     * 
     * @Title: deleteMedicalHistory
     * @param id
     *            病史主表数据Id
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteMedicalHistory")
    @ResponseBody
    public HashMap<String, Object> deleteMedicalHistory(Long patientId, Long id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "病史", "删除");
        return map;
    }

    /**
     * 保存病史之手术史
     * 
     * @Title: saveHistSurgery
     * @param histSurgery
     *            病史之手术史
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistSurgery")
    @ResponseBody
    public HashMap<String, Object> saveHistSurgery(CmDiagnosisHistSurgeryPO histSurgery) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = histSurgery.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistSurgeryService.saveItem(histSurgery);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(histSurgery.getFkPatientId(), "病史之手术史", action);
        return map;
    }

    /**
     * 删除病史之手术史
     * 
     * @Title: deleteHistSurgery
     * @param id
     *            病史之手术史Id
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteHistSurgery")
    @ResponseBody
    public HashMap<String, Object> deleteHistSurgery(Long patientId, Long id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistSurgeryService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "病史之手术史", "删除");
        return map;
    }

    /**
     * 保存病史之血透史
     * 
     * @Title: saveHistHd
     * @param histHd
     *            病史之血透史
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistHd")
    @ResponseBody
    public HashMap<String, Object> saveHistHd(CmDiagnosisHistHdPO histHd) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = histHd.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistHdService.saveItem(histHd);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(histHd.getFkPatientId(), "病史之血透史", action);
        return map;
    }

    /**
     * 删除病史之血透史
     * 
     * @Title: deleteHistHd
     * @param id
     *            病史之血透史Id
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteHistHd")
    @ResponseBody
    public HashMap<String, Object> deleteHistHd(Long patientId, Long id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistHdService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "病史之血透史", "删除");
        return map;
    }

    /**
     * 保存病史之腹透史
     * 
     * @Title: saveHistPd
     * @param histPd
     *            病史之腹透史
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistPd")
    @ResponseBody
    public HashMap<String, Object> saveHistPd(CmDiagnosisHistPdPO histPd) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = histPd.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistPdService.saveItem(histPd);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(histPd.getFkPatientId(), "病史之腹透史", action);
        return map;
    }

    /**
     * 删除病史之腹透史
     * 
     * @Title: deleteHistPd
     * @param id
     *            病史之腹透史Id
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteHistPd")
    @ResponseBody
    public HashMap<String, Object> deleteHistPd(Long patientId, Long id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistPdService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "病史之腹透史", "删除");
        return map;
    }

    /**
     * 保存病史之肾移植史
     * 
     * @Title: saveHistKt
     * @param histKt
     *            病史之肾移植史
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistKt")
    @ResponseBody
    public HashMap<String, Object> saveHistKt(CmDiagnosisHistKtPO histKt) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = histKt.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistKtService.saveItem(histKt);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(histKt.getFkPatientId(), "病史之肾移植史", action);
        return map;
    }

    /**
     * 删除病史之肾移植史
     * 
     * @Title: deleteHistKt
     * @param id
     *            病史之肾移植史Id
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteHistKt")
    @ResponseBody
    public HashMap<String, Object> deleteHistKt(Long patientId, Long id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistKtService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "病史之肾移植史", "删除");
        return map;
    }

    /**
     * 保存病史之过敏史
     * 
     * @Title: saveHistAllergy
     * @param histAllergy
     *            病史之过敏史
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistAllergy")
    @ResponseBody
    public HashMap<String, Object> saveHistAllergy(CmDiagnosisHistAllergyPO histAllergy) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = histAllergy.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistAllergyService.saveItem(histAllergy);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(histAllergy.getFkPatientId(), "病史之过敏史", action);
        return map;
    }

    /**
     * 删除病史之过敏史
     * 
     * @Title: deleteHistAllergy
     * @param id
     *            病史之过敏史Id
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteHistAllergy")
    @ResponseBody
    public HashMap<String, Object> deleteHistAllergy(Long patientId, Long id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistAllergyService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "病史之过敏史", "删除");
        return map;
    }

    /**
     * 保存病史之传染病史
     * 
     * @Title: saveHistPestilence
     * @param histPestilence
     *            病史之传染病史
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistTumour")
    @ResponseBody
    public HashMap<String, Object> saveHistTumour(CmDiagnosisHistTumourPO record) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = record.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistTumourService.save(record);
        map.put("status", CommonConstants.SUCCESS);

        insertDiagnosisLog(record.getFkPatientId(), "病史之肿瘤史", action);
        return map;
    }

    /**
     * 删除病史之肿瘤史
     * 
     * @Title: deleteHistTumour
     * @param id
     *            病史之肿瘤史Id
     * @return
     * 
     */
    @RequestMapping("deleteHistTumour")
    @ResponseBody
    public HashMap<String, Object> deleteHistTumour(Long patientId, Long id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistTumourService.removeById(id);
        map.put("status", CommonConstants.SUCCESS);

        insertDiagnosisLog(patientId, "病史之肿瘤史", "删除");
        return map;
    }

    /**
     * 保存病史之肿瘤史
     * 
     * @Title: saveHistPestilence
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveHistPestilence")
    @ResponseBody
    public HashMap<String, Object> saveHistPestilence(CmDiagnosisHistPestilencePO histPestilence) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = histPestilence.getId() == null ? "新增" : "编辑";
        cmDiagnosisHistPestilenceService.saveItem(histPestilence);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(histPestilence.getFkPatientId(), "病史之传染病史", action);
        return map;
    }

    /**
     * 删除病史之传染病史
     * 
     * @Title: deleteHistPestilence
     * @param id
     *            病史之传染病史Id
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteHistPestilence")
    @ResponseBody
    public HashMap<String, Object> deleteHistPestilence(Long patientId, Long id) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistPestilenceService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "病史之传染病史", "删除");
        return map;
    }

    /**
     * 保存诊断之诊断树下的选项集合
     * 
     * @Title: saveDiagnosisEntity
     * @param diagnosisEntity
     *            诊断树之选项数据
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("saveDiagnosisEntity")
    @ResponseBody
    public HashMap<String, Object> saveDiagnosisEntity(CmDiagnosisEntityPO diagnosisEntity) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String action = diagnosisEntity.getId() == null ? "新增" : "编辑";
        if (diagnosisEntity.getValueMap() != null && diagnosisEntity.getValueMap().size() > 0) {
            List<CmDiagnosisEntityValuePO> valueList = Lists.newArrayList();
            for (List<CmDiagnosisEntityValuePO> values : diagnosisEntity.getValueMap().values()) {
                for (CmDiagnosisEntityValuePO value : values) {
                    if (StringUtils.isNotBlank(value.getItemValue()) || StringUtils.isNotBlank(value.getContent())) {
                        if (StringUtils.isNotBlank(value.getItemValue())) {
                            String itemValue = value.getItemValue();
                            value.setItemCode(itemValue.split("_")[0]);
                            value.setItemValue(itemValue.split("_")[1]);
                        }
                        value.setFkTenantId(UserUtil.getTenantId());
                        DataUtil.setSystemFieldValue(value);
                        value.setFkPatientId(diagnosisEntity.getFkPatientId());
                        if (diagnosisEntity.getId() != null) {
                            value.setFkEntityId(diagnosisEntity.getId());
                        }
                        valueList.add(value);
                    }
                }
            }
            if (valueList.size() == 0) {
                map.put("message", "当前提交的数据为空！");
                map.put("status", CommonConstants.WARNING);
                return map;
            }
            diagnosisEntity.setValueList(valueList);
        } else {
            map.put("message", "当前提交的数据为空！");
            map.put("status", CommonConstants.WARNING);
            return map;
        }
        cmDiagnosisHistEntityService.saveItem(diagnosisEntity);
        map.put("message", "保存成功！");
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(diagnosisEntity.getFkPatientId(), "诊断" + diagnosisEntity.getItemName(), action);
        return map;
    }

    /**
     * 删除诊断之诊断树下的选项集合
     * 
     * @Title: deleteDiagnosisEntity
     * @param id
     *            诊断树之选项数据Id
     * @param itemName
     *            诊断树节点名称
     * @return
     * @throws Exception
     * 
     */
    @RequestMapping("deleteDiagnosisEntity")
    @ResponseBody
    public HashMap<String, Object> deleteDiagnosisEntity(Long patientId, Long id, String itemName) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        cmDiagnosisHistEntityService.deleteById(id);
        map.put("status", CommonConstants.SUCCESS);
        insertDiagnosisLog(patientId, "诊断" + itemName, "删除");
        return map;
    }

    /**
     * 插入修改诊断的日志
     * 
     * @Title: insertDiagnosisLog
     * @param patientId
     * @param typeDesc
     *            描述
     * @param actionName
     *            动作名称
     *
     */
    private void insertDiagnosisLog(Long patientId, String typeDesc, String actionName) {
        PatientDto patient = PatientCache.getById(patientId);
        sysLogService.insertSysLog(CommonConstants.SYS_LOG_TYPE_3,
                        String.format("对患者（编号：%s 姓名：%s）%s进行了%s动作", patient.getId(), patient.getName(), typeDesc, actionName), UserUtil.getSysOwner());
    }

    /**
     * 获取患者最新一次的诊断字符串
     * 
     * @Title: getLatestStrByPatientIds
     * @param param
     * @return
     *
     */
    @RequestMapping("getLatestStrByPatientIds")
    @ResponseBody
    public HttpResult getLatestStrByPatientIds(@RequestBody DiagnosisApi param) {
        LOGGER.info("getLatestStrByPatientIds , request param is :" + param.toString());
        HttpResult result = HttpResult.getSuccessInstance();
        Map<Long, Map<String, String>> map = cmDiagnosisHistEntityService.getLatestStrByPatientIds(param);
        result.setRs(map);
        return result;
    }

}
