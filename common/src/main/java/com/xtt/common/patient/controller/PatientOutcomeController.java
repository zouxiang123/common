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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.constants.CmDictConsts;
import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.po.PatientOutcomePO;
import com.xtt.common.patient.service.IPatientOutcomeService;
import com.xtt.common.util.DictUtil;
import com.xtt.common.util.UserUtil;

@Controller
@RequestMapping("/patient/outcome/")
public class PatientOutcomeController {
    @Autowired
    private IPatientOutcomeService patientOutcomeService;

    @RequestMapping("record")
    public String list(Model model, Long patientId, String sys) {
        model.addAttribute("items", patientOutcomeService.listByPatientId(patientId));
        model.addAttribute("sysOwner", sys);
        model.addAttribute("patientId", patientId);
        model.addAttribute(CmDictConsts.OUTCOME_RECORD_TYPE, DictUtil.listByPItemCode(CmDictConsts.OUTCOME_RECORD_TYPE));
        return "patient/patient_outcome_record";
    }

    @RequestMapping("save")
    @ResponseBody
    public Map<String, Object> save(PatientOutcomePO record) {
        Map<String, Object> map = new HashMap<>();
        record.setFkTenantId(UserUtil.getTenantId());
        patientOutcomeService.save(record);
        map.put(CommonConstants.STATUS, CommonConstants.SUCCESS);
        return map;
    }
}
