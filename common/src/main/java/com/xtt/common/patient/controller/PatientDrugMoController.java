/**   
 * @Title: MoDrugController.java 
 * @Package com.xtt.common.mo.controller
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2018年5月11日 下午1:37:23 
 *
 */
package com.xtt.common.patient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("patientDrugMo")
public class PatientDrugMoController {
    /**
     * 跳转到用药医嘱页面
     * 
     * @Title: listView
     * @param model
     * @param patientId
     * @return
     *
     */
    @RequestMapping("listView")
    public String listView(Model model, Long patientId) {
        return "cm/patient/drug_mo_list";
    }
}
