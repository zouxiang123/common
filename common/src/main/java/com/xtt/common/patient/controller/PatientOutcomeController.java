/**   
 * @Title: PatientOutcomeController.java 
 * @Package com.xtt.common.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年1月5日 下午3:55:34 
 *
 */
package com.xtt.common.patient.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.cache.PatientCache;
import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientOutcomePO;
import com.xtt.common.dao.po.PatientPO;
import com.xtt.common.patient.service.IPatientOutcomeService;
import com.xtt.common.patient.service.IPatientService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.PushUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/patient/outcome/")
public class PatientOutcomeController {
    @Autowired
    private IPatientOutcomeService patientOutcomeService;
    @Autowired
    private IPatientService patientService;

    @RequestMapping("record")
    public String list(Model model, Long patientId, String sysOwner) {
        model.addAttribute("items", patientOutcomeService.selectAllByPatientId(patientId));
        model.addAttribute("sysOwner", sysOwner);
        model.addAttribute("patientId", patientId);
        model.addAttribute("patient", PatientCache.getById(patientId));
        model.addAttribute(CmDictConsts.PATIENT_OUTCOME_TYPE, DictUtil.listByPItemCode(CmDictConsts.PATIENT_OUTCOME_TYPE));
        return "cm/patient/patient_outcome_list";
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> save(PatientOutcomePO record) {
        Map<String, Object> map = new HashMap<>();
        record.setFkTenantId(UserUtil.getTenantId());
        patientOutcomeService.save(record);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        // 用户信息发布到推送模块进行推送
        Map<String, String> m = new HashMap<String, String>();
        m.put("patientId", String.valueOf(record.getFkPatientId()));
        m.put("tenantId", String.valueOf(UserUtil.getTenantId()));
        m.put("sysOwner", UserUtil.getSysOwner());
        PushUtil.pushAppData(m, CommonConstants.APP_PUSH_PATIENT);
        // 转归原因：死亡，更新患者主表:死亡日期dead_date
        if (Objects.equals(record.getType(), CommonConstants.PATIENT_OUTCOME_TYPE_DEAD)) {
            PatientPO patientPO = patientService.selectById(record.getFkPatientId());
            if (patientPO != null) {
                patientPO.setDeadDate(record.getRecordDate());
                patientService.updateByPrimaryKeySelective(patientPO);
            }
        }

        return map;
    }
}
