package com.xtt.common.assay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtt.common.assay.service.IPatientAssayConfService;
import com.xtt.common.dao.model.PatientAssayConf;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/assay/patientAssayConf")
public class PatientAssayConfController {

    @Autowired
    private IPatientAssayConfService patientAssayConfService;

    @RequestMapping("view")
    public Map<String, Object> view() {

        Map<String, Object> map = new HashMap<String, Object>();
        PatientAssayConf patientAssayConf = patientAssayConfService.selectByTenantId(UserUtil.getTenantId());
        map.put("patientAssayConf", patientAssayConf);
        return map;
    }

    /**
     * 修改时间配置
     */
    @RequestMapping("update")
    public void update(PatientAssayConf patientAssayConf) {
        if (StringUtil.isBlank(patientAssayConf.getEndDate())) {
            return;
        }
        // 判断是否要新建
        if (patientAssayConf.getId() == null) {
            patientAssayConf.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(patientAssayConf);
            patientAssayConfService.insert(patientAssayConf);
        } else {
            patientAssayConfService.update(patientAssayConf);
        }
    }
}
