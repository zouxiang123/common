package com.xtt.common.assay.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xtt.common.dao.po.PatientAssayNewstPO;
import com.xtt.common.dao.po.PatientNewstPO;
import com.xtt.common.report.service.IPatientAssayNewstService;
import com.xtt.common.util.HttpServletUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;

@Controller
@RequestMapping("/report/patientAssayRemind")
public class PatientAssayRemindController {

    @Autowired
    private IPatientAssayNewstService patientAssayNewstService;

    @RequestMapping("view")
    public ModelAndView select() {
        ModelAndView mav = new ModelAndView("cm/report/patient_assay_remind");

        /*List<DictionaryPO> tempList = DictionaryUtil.getListByType(DictionaryConstants.ASSAYTYPE);
        mav.addObject("listDictionaryPO", tempList);*/
        if (!HttpServletUtil.isFromPC() || UserUtil.isNurse()) {
            // mav.addObject(CommonConstants.PAGE_DEVICE_SUF, CommonConstants.PAGE_DEVICE_PAD);
            mav.addObject("isFromPC", 0);
        } else {
            mav.addObject("isFromPC", 1);
        }

        return mav;
    }

    /**
     * 患者化验提醒管理页面（异常提醒报表管理页面）
     * 
     * @Title: manage
     * @return
     *
     */
    @RequestMapping("manage")
    public ModelAndView manage() {
        ModelAndView mav = new ModelAndView("cm/report/patient_assay_remind_manage");
        if (HttpServletUtil.isFromPC() && UserUtil.isDoctor()) {
            mav.addObject("isFromPC", 1);
        } else {
            mav.addObject("isFromPC", 0);
        }
        return mav;
    }

    /**
     * 搜索逾期患者化验项
     * 
     * @Title: searchPatientOverdue
     * @param patientName
     *            患者名称
     * @return
     */
    @RequestMapping("searchPatientOverdue")
    @ResponseBody
    public HttpResult searchPatientOverdue(PatientNewstPO patientNewst) {
        HttpResult result = HttpResult.getSuccessInstance();
        List<Long> patientArrays = new ArrayList<Long>();
        patientNewst.setIspaging(true);
        patientNewst.setFkTenantId(UserUtil.getTenantId());
        patientNewst = patientAssayNewstService.listOverduePatients(patientNewst);

        List<PatientNewstPO> patients = patientNewst.getResults();
        for (PatientNewstPO p : patients) {
            patientArrays.add(p.getId());
        }

        // 获取患者逾期项
        Map<Long, List<PatientAssayNewstPO>> newstMaps = patientAssayNewstService.findByPatientAssayNewst(patientArrays);
        for (PatientNewstPO p : patients) {
            List<PatientAssayNewstPO> patientAssayNewstArrays = newstMaps.get(p.getId());
            p.setPatientAssayNewstArrays(patientAssayNewstArrays);
        }
        result.setRs(patientNewst);
        return result;
    }

    /**
     * 搜索指定逾期患者化验项
     * 
     * @Title: searchPatientOverdue
     * @param patientName
     *            患者名称
     * @return
     */
    @RequestMapping("searchByPatientOverdue")
    @ResponseBody
    public HttpResult searchByPatientOverdue(Long patientId) {
        HttpResult result = HttpResult.getSuccessInstance();
        List<Long> patientArrays = new ArrayList<Long>();
        patientArrays.add(patientId);

        Map<Long, List<PatientAssayNewstPO>> newstMaps = patientAssayNewstService.findByPatientAssayNewst(patientArrays);
        if (newstMaps != null && newstMaps.size() > 0) {
            result.setRs(newstMaps.get(patientId));
        }
        return result;
    }
}
