
package com.xtt.common.assay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtt.common.assay.service.IPatientAssayConfService;
import com.xtt.common.dao.model.PatientAssayConf;
import com.xtt.common.util.DataUtil;
import com.xtt.common.util.UserUtil;
import com.xtt.platform.util.http.HttpResult;
import com.xtt.platform.util.lang.StringUtil;

@Controller
@RequestMapping("/assay/assayConf")
public class AssayConfController {

    @Autowired
    private IPatientAssayConfService patientAssayConfService;

    /**
     * 跳转到管理员中间页面（兼容老样式）
     * 
     * @Title: adminView
     * @return
     *
     */
    @RequestMapping("adminView")
    public String adminView() {
        return "assay/assay_conf_admin";
    }

    /**
     * 跳转到化验配置中心
     * 
     * @Title: view
     * @return
     *
     */
    @RequestMapping("view")
    public String view() {
        return "assay/assay_conf";
    }

    /**
     * 获取月份截止数据
     * 
     * @Title: getItem
     * @return
     *
     */
    @RequestMapping("getItem")
    @ResponseBody
    public HttpResult getItem() {
        HttpResult result = HttpResult.getSuccessInstance();
        PatientAssayConf patientAssayConf = patientAssayConfService.selectByTenantId(UserUtil.getTenantId());
        result.setRs(patientAssayConf);
        return result;
    }

    /**
     * 修改时间配置
     */
    @RequestMapping("update")
    @ResponseBody
    public HttpResult update(PatientAssayConf patientAssayConf) {
        HttpResult result = HttpResult.getSuccessInstance();
        if (StringUtil.isBlank(patientAssayConf.getEndDate())) {
            result.setStatus(HttpResult.WARNING);
            result.setErrmsg("结束时间不能为空");
            return result;
        }
        // 判断是否要新建
        if (patientAssayConf.getId() == null) {
            patientAssayConf.setFkTenantId(UserUtil.getTenantId());
            DataUtil.setSystemFieldValue(patientAssayConf);
            patientAssayConfService.insert(patientAssayConf);
        } else {
            patientAssayConfService.update(patientAssayConf);
        }
        return result;
    }
}
