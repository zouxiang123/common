/**   
 * @Title: CmDictIcdController.java 
 * @Package com.xtt.common.conf.controller
 * Copyright: Copyright (c) 2015
 * @author: zx   
 * @date: 2018年9月29日 上午9:54:51 
 *
 */
package com.xtt.common.conf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.conf.service.ICmDictIcdService;
import com.xtt.common.dao.model.CmDictIcd;
import com.xtt.common.dao.po.CmDiagnosisIcdPO;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/dict/icd/")
public class CmDictIcdController {
    @Autowired
    private ICmDictIcdService cmDictIcdService;

    @RequestMapping("list")
    @ResponseBody
    public HttpResult list(CmDictIcd cmDictIcd) {
        HttpResult rs = HttpResult.getSuccessInstance();
        rs.setRs(cmDictIcdService.listByItemName(cmDictIcd));
        return rs;

    }

    @RequestMapping("getIcdData")
    @ResponseBody
    public HttpResult getIcdData(Long patientId) {
        HttpResult rs = HttpResult.getSuccessInstance();
        rs.setRs(cmDictIcdService.listByfkPatientId(patientId));
        return rs;
    }

    @RequestMapping("getById")
    @ResponseBody
    public HttpResult getById(CmDiagnosisIcdPO cmDiagnosisIcdPO) {
        HttpResult rs = HttpResult.getSuccessInstance();
        rs.setRs(cmDictIcdService.getById(cmDiagnosisIcdPO));
        return rs;

    }

    @RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public HttpResult deleteByPrimaryKey(CmDiagnosisIcdPO cmDiagnosisIcdPO) {
        HttpResult rs = HttpResult.getSuccessInstance();
        rs.setRs(cmDictIcdService.deleteByPrimaryKey(cmDiagnosisIcdPO));
        return rs;

    }
}
