/**   
 * @Title: PatientLabelController.java 
 * @Package com.xtt.txgl.patient.controller
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年8月23日 上午10:58:05 
 *
 */
package com.xtt.common.patient.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.constants.CommonConstants;
import com.xtt.common.dao.model.PatientLabel;
import com.xtt.common.dao.po.PatientLabelPO;
import com.xtt.common.patient.service.IPatientLabelService;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/patient/label")
public class PatientLabelController {

    @Autowired
    private IPatientLabelService patientLabelService;

    /**
     * 标签分类
     * 
     * @Title: index
     * @return
     *
     */
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("cm/patient/patient_label");
        mv.addObject(CommonConstants.SYS_OWNER, UserUtil.getSysOwner());
        mv.addObject(CommonConstants.PD_URL_KEY, CommonConstants.PD_URL);
        mv.addObject(CommonConstants.POPS_URL_KEY, CommonConstants.POPS_URL);
        return mv;
    }

    /**
     * 获取患者列表内容
     * 
     * @Title: context
     * @return
     */
    @RequestMapping("listPatient")
    @ResponseBody
    public HttpResult listPatient(PatientLabelPO pl) {
        HttpResult result = HttpResult.getSuccessInstance();
        pl.setIspaging(true);
        pl.setFkTenantId(UserUtil.getTenantId());
        pl.setResults(patientLabelService.listPatient(pl));
        result.setRs(pl);
        return result;
    }

    /**
     * 根据患者id获取对应的标签
     * 
     * @Title: getByPatientId
     * @param patientId
     * @return
     *
     */
    @RequestMapping("getByPatientId")
    @ResponseBody
    public HttpResult getByPatientId(Long patientId) {
        HttpResult result = HttpResult.getSuccessInstance();
        PatientLabelPO label = new PatientLabelPO();
        label.setFkTenantId(UserUtil.getTenantId());
        label.setPatientId(patientId);
        result.setRs(patientLabelService.listByCondition(label));
        return result;
    }

    /**
     * 获取所有标签
     * 
     * @Title: listAll
     * @return
     *
     */
    @RequestMapping("listAll")
    @ResponseBody
    public HttpResult listAll() {
        HttpResult result = HttpResult.getSuccessInstance();
        result.setRs(patientLabelService.listByTenantId(UserUtil.getTenantId()));
        return result;
    }

    /**
     * 保存标签
     * 
     * @Title: save
     * @return
     *
     */
    @RequestMapping("save")
    @ResponseBody
    public HttpResult save(PatientLabel pl) {
        HttpResult result = HttpResult.getSuccessInstance();
        pl.setName(StringUtil.stripToNull(pl.getName()));
        if (patientLabelService.checkNameExists(pl.getName())) {
            result.setErrmsg("标签名称已存在");
            result.setStatus(HttpResult.WARNING);
            return result;
        }
        patientLabelService.insert(pl);
        return result;
    }

    /**
     * 保存患者关联标签
     * 
     * @Title: saveRef
     * @param labelIds
     *            标签ID
     * @param patientIds
     *            患者ID
     * @return
     *
     */
    @RequestMapping("saveRef")
    @ResponseBody
    public HttpResult saveRef(@RequestParam("labelIds") String labelIds, @RequestParam("patientIds") String patientIds) {
        HttpResult result = HttpResult.getSuccessInstance();
        if (StringUtil.isBlank(labelIds) || StringUtil.isBlank(patientIds)) {
            result.setStatus(HttpResult.WARNING);
            result.setErrmsg("患者id不存在或者关联标签为空");
            return result;
        }
        List<Long> labelIdList = Arrays.asList(labelIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        List<Long> patientIdList = Arrays.asList(patientIds.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        patientLabelService.saveRef(patientIdList, labelIdList);
        return result;
    }

    /**
     * 删除标签
     * 
     * @Title: delete
     * @param patientLabelId
     *            标签ID
     * @return
     *
     */
    @RequestMapping("delete")
    @ResponseBody
    public HttpResult delete(Long id) {
        HttpResult result = HttpResult.getSuccessInstance();
        if (id == null) {
            result.setStatus(HttpResult.WARNING);
            result.setErrmsg("id不能为空");
            return result;
        }
        patientLabelService.deleteById(id);
        return result;
    }
}
